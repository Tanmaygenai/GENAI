import React, { useEffect, useState, useRef } from "react";
import Layout from "../../layout/Layout";
import { useForm } from "react-hook-form";
import { SketchPicker, BlockPicker, HuePicker } from "react-color";
import API_Headers from "../../API_Headers";
import { useHistory } from "react-router-dom";
import useLoader from "../../context/loader";
import UpdateThemeService from "../services/UpdateTheme";
import useMetaData from "../../context/metaData";
import SuccessMessageModal from "../../components/modals/popupmodal/SuccessMessageModal";
import GetThemeService from "../services/GetThemeService";

const UpdateTheme = () => {
  const { theme, setTheme } = useMetaData();
  const { setLoader } = useLoader();
  const {
    handleSubmit,
    formState: { errors },
  } = useForm();
  const [headers, setHeaders] = useState([]);
  const [modalData, setModalData] = useState({
    show: false,
    title: "",
    body: "",
  });
  const defaultTheme = {
    navbar: {
      background: "#fff",
      text: "#000",
    },
    headerSection: {
      background: "#000",
      text: "#fff",
    },
    sidebar: {
      background: "#fff",
      text: "#000",
    },
    button: {
      background: '#CF0918',
      text: '#ffffff'
    }
  };
  const [color, setColor] = useState(defaultTheme);
  const [displayColorPicker, setDisplayColorPicker] = useState({
    navbar: {
      background: false,
      text: false,
    },
    headerSection: {
      background: false,
      text: false,
    },
    sidebar: {
      background: false,
      text: false,
    },
    button: {
      background: false,
      text: false,
    },
  });
  const history = useHistory();

  useEffect(() => {
    if (!!Object.keys(theme).length) {
      setColor(theme);
    }
  }, [theme]);

  useEffect(() => {
    API_Headers().then((val) => setHeaders(val));
  }, []);

  const handleBack = () => {
    history.push({
      pathname: "/admin",
    });
  };

  const onSubmit = async () => {
    setLoader(true);
    const payload = JSON.stringify(color);
    const res = await UpdateThemeService.updateTheme(payload, headers);
    if (res.status == 200) {
      setModalData({
        title: "Update Theme",
        show: true,
        body: "You have updated the theme successfully.",
      });
      getTheme();
    }
    setLoader(false);
  };

  const getTheme = async () => {
    const res = await GetThemeService.getTheme(headers);
    setTheme(res?.data);
  };

  const handleClick = (section, property) => {
    const updatedDisplay = { ...displayColorPicker };
    updatedDisplay[section][property] = !displayColorPicker[section][property];
    setDisplayColorPicker(updatedDisplay);
  };

  const handleColorChange = (colorInp, property, section) => {
    const updatedColor = { ...color };
    updatedColor[section][property] = colorInp.hex;
    setColor(updatedColor);
  };

  const handleClose = (section, property) => {
    const updatedDisplay = { ...displayColorPicker };
    updatedDisplay[section][property] = false;
    setDisplayColorPicker(updatedDisplay);
  };

  return (
    <Layout
      TabName="Upload Logo"
      BreadCrum={["My Dashboard", "Admin", "Upload Logo"]}
    >
      <div className="coverContainer">
        <div class="commonform_box">
          <div class="inf">
            <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
              <div className="">
                <div className="form-element">
                  <div>
                    <div style={{ fontSize: "22px", fontWeight: "bold" }}>
                      Exavalu
                    </div>
                    <div>Technology•Innovation</div>
                  </div>
                  <div className="theme-change-box" >
                    <div
                      className="theme-color-box mt-2"
                    >
                      <div className="mb-1 pl-1 pr-1 pt-1 title" >
                        <h3>Navbar</h3>
                      </div>
                      <div className="mb-2 pl-1 pr-1">
                        <div>
                          <div>Background Color</div>
                          <div>
                            <div
                              style={{
                                padding: "5px",
                                background: "#fff",
                                borderRadius: "1px",
                                boxShadow: "0 0 0 1px rgba(0,0,0,.1)",
                                display: "inline-block",
                                cursor: "pointer",
                              }}
                              onClick={() =>
                                handleClick("navbar", "background")
                              }
                            >
                              <div
                                style={{
                                  width: "36px",
                                  height: "14px",
                                  borderRadius: "2px",
                                  background: `${color.navbar.background}`,
                                }}
                              />
                            </div>
                            {displayColorPicker.navbar.background ? (
                              <div
                                style={{ position: "absolute", zIndex: "2" }}
                              >
                                <div
                                  style={{
                                    position: "fixed",
                                    top: "0px",
                                    right: "0px",
                                    bottom: "0px",
                                    left: "0px",
                                  }}
                                  onClick={() =>
                                    handleClose("navbar", "background")
                                  }
                                />
                                <SketchPicker
                                  color={color.navbar.background}
                                  onChange={(e) =>
                                    handleColorChange(e, "background", "navbar")
                                  }
                                />
                              </div>
                            ) : null}
                          </div>
                        </div>
                      </div>
                      <div className=" mb-2 pl-1 pr-1">
                        <div>
                          <div>Text Color</div>
                          <div>
                            <div
                              style={{
                                padding: "5px",
                                background: "#fff",
                                borderRadius: "1px",
                                boxShadow: "0 0 0 1px rgba(0,0,0,.1)",
                                display: "inline-block",
                                cursor: "pointer",
                              }}
                              onClick={() => handleClick("navbar", "text")}
                            >
                              <div
                                style={{
                                  width: "36px",
                                  height: "14px",
                                  borderRadius: "2px",
                                  background: `${color.navbar.text}`,
                                }}
                              />
                            </div>
                            {displayColorPicker.navbar.text ? (
                              <div
                                style={{ position: "absolute", zIndex: "2" }}
                              >
                                <div
                                  style={{
                                    position: "fixed",
                                    top: "0px",
                                    right: "0px",
                                    bottom: "0px",
                                    left: "0px",
                                  }}
                                  onClick={() => handleClose("navbar", "text")}
                                />
                                <SketchPicker
                                  color={color.navbar.text}
                                  onChange={(e) =>
                                    handleColorChange(e, "text", "navbar")
                                  }
                                />
                              </div>
                            ) : null}
                          </div>
                        </div>
                      </div>
                    </div>
                    <div
                      className="theme-color-box mt-2"
                    >
                      <div className="mb-1 pl-1 pr-1 pt-1 title" >
                        <h3>Sidebar</h3>
                      </div>
                      <div className="mb-2 pl-1 pr-1">
                        <div>
                          <div>Background Color</div>
                          <div>
                            <div
                              style={{
                                padding: "5px",
                                background: "#fff",
                                borderRadius: "1px",
                                boxShadow: "0 0 0 1px rgba(0,0,0,.1)",
                                display: "inline-block",
                                cursor: "pointer",
                              }}
                              onClick={() =>
                                handleClick("sidebar", "background")
                              }
                            >
                              <div
                                style={{
                                  width: "36px",
                                  height: "14px",
                                  borderRadius: "2px",
                                  background: `${color.sidebar.background}`,
                                }}
                              />
                            </div>
                            {displayColorPicker.sidebar.background ? (
                              <div
                                style={{ position: "absolute", zIndex: "2" }}
                              >
                                <div
                                  style={{
                                    position: "fixed",
                                    top: "0px",
                                    right: "0px",
                                    bottom: "0px",
                                    left: "0px",
                                  }}
                                  onClick={() =>
                                    handleClose("sidebar", "background")
                                  }
                                />
                                <SketchPicker
                                  color={color.sidebar.background}
                                  onChange={(e) =>
                                    handleColorChange(
                                      e,
                                      "background",
                                      "sidebar"
                                    )
                                  }
                                />
                              </div>
                            ) : null}
                          </div>
                        </div>
                      </div>
                      <div className=" mb-2 pl-1 pr-1">
                        <div>
                          <div>Text Color</div>
                          <div>
                            <div
                              style={{
                                padding: "5px",
                                background: "#fff",
                                borderRadius: "1px",
                                boxShadow: "0 0 0 1px rgba(0,0,0,.1)",
                                display: "inline-block",
                                cursor: "pointer",
                              }}
                              onClick={() => handleClick("sidebar", "text")}
                            >
                              <div
                                style={{
                                  width: "36px",
                                  height: "14px",
                                  borderRadius: "2px",
                                  background: `${color.sidebar.text}`,
                                }}
                              />
                            </div>
                            {displayColorPicker.sidebar.text ? (
                              <div
                                style={{ position: "absolute", zIndex: "2" }}
                              >
                                <div
                                  style={{
                                    position: "fixed",
                                    top: "0px",
                                    right: "0px",
                                    bottom: "0px",
                                    left: "0px",
                                  }}
                                  onClick={() => handleClose("sidebar", "text")}
                                />
                                <SketchPicker
                                  color={color.sidebar.text}
                                  onChange={(e) =>
                                    handleColorChange(e, "text", "sidebar")
                                  }
                                />
                              </div>
                            ) : null}
                          </div>
                        </div>
                      </div>
                    </div>
                    <div
                      className="theme-color-box mt-2 "
                    >
                      <div className="mb-1 pl-1 pr-1 pt-1 title " >
                        <h3>Header</h3>
                      </div>
                      <div className="mb-2 pl-1 pr-1">
                        <div>
                          <div>Background Color</div>
                          <div>
                            <div
                              style={{
                                padding: "5px",
                                background: "#fff",
                                borderRadius: "1px",
                                boxShadow: "0 0 0 1px rgba(0,0,0,.1)",
                                display: "inline-block",
                                cursor: "pointer",
                              }}
                              onClick={() =>
                                handleClick("headerSection", "background")
                              }
                            >
                              <div
                                style={{
                                  width: "36px",
                                  height: "14px",
                                  borderRadius: "2px",
                                  background: `${color.headerSection.background}`,
                                }}
                              />
                            </div>
                            {displayColorPicker.headerSection.background ? (
                              <div
                                style={{ position: "absolute", zIndex: "2" }}
                              >
                                <div
                                  style={{
                                    position: "fixed",
                                    top: "0px",
                                    right: "0px",
                                    bottom: "0px",
                                    left: "0px",
                                  }}
                                  onClick={() =>
                                    handleClose("headerSection", "background")
                                  }
                                />
                                <SketchPicker
                                  color={color.headerSection.background}
                                  onChange={(e) =>
                                    handleColorChange(
                                      e,
                                      "background",
                                      "headerSection"
                                    )
                                  }
                                />
                              </div>
                            ) : null}
                          </div>
                        </div>
                      </div>
                      <div className=" mb-2 pl-1 pr-1">
                        <div>
                          <div>Text Color</div>
                          <div>
                            <div
                              style={{
                                padding: "5px",
                                background: "#fff",
                                borderRadius: "1px",
                                boxShadow: "0 0 0 1px rgba(0,0,0,.1)",
                                display: "inline-block",
                                cursor: "pointer",
                              }}
                              onClick={() =>
                                handleClick("headerSection", "text")
                              }
                            >
                              <div
                                style={{
                                  width: "36px",
                                  height: "14px",
                                  borderRadius: "2px",
                                  background: `${color.headerSection.text}`,
                                }}
                              />
                            </div>
                            {displayColorPicker.headerSection.text ? (
                              <div
                                style={{ position: "absolute", zIndex: "2" }}
                              >
                                <div
                                  style={{
                                    position: "fixed",
                                    top: "0px",
                                    right: "0px",
                                    bottom: "0px",
                                    left: "0px",
                                  }}
                                  onClick={() =>
                                    handleClose("headerSection", "text")
                                  }
                                />
                                <SketchPicker
                                  color={color.headerSection.text}
                                  onChange={(e) =>
                                    handleColorChange(
                                      e,
                                      "text",
                                      "headerSection"
                                    )
                                  }
                                />
                              </div>
                            ) : null}
                          </div>
                        </div>
                      </div>
                    </div>
                    <div
                      className="theme-color-box mt-2 "
                    >
                      <div className="mb-1 pl-1 pr-1 pt-1 title " >
                        <h3>Button</h3>
                      </div>
                      <div className="mb-2 pl-1 pr-1">
                        <div>
                          <div>Background Color</div>
                          <div>
                            <div
                              style={{
                                padding: "5px",
                                background: "#fff",
                                borderRadius: "1px",
                                boxShadow: "0 0 0 1px rgba(0,0,0,.1)",
                                display: "inline-block",
                                cursor: "pointer",
                              }}
                              onClick={() =>
                                handleClick("button", "background")
                              }
                            >
                              <div
                                style={{
                                  width: "36px",
                                  height: "14px",
                                  borderRadius: "2px",
                                  background: `${color.button.background}`,
                                }}
                              />
                            </div>
                            {displayColorPicker.button.background ? (
                              <div
                                style={{ position: "absolute", zIndex: "2" }}
                              >
                                <div
                                  style={{
                                    position: "fixed",
                                    top: "0px",
                                    right: "0px",
                                    bottom: "0px",
                                    left: "0px",
                                  }}
                                  onClick={() =>
                                    handleClose("button", "background")
                                  }
                                />
                                <SketchPicker
                                  color={color.button.background}
                                  onChange={(e) =>
                                    handleColorChange(
                                      e,
                                      "background",
                                      "button"
                                    )
                                  }
                                />
                              </div>
                            ) : null}
                          </div>
                        </div>
                      </div>
                      <div className=" mb-2 pl-1 pr-1">
                        <div>
                          <div>Text Color</div>
                          <div>
                            <div
                              style={{
                                padding: "5px",
                                background: "#fff",
                                borderRadius: "1px",
                                boxShadow: "0 0 0 1px rgba(0,0,0,.1)",
                                display: "inline-block",
                                cursor: "pointer",
                              }}
                              onClick={() =>
                                handleClick("button", "text")
                              }
                            >
                              <div
                                style={{
                                  width: "36px",
                                  height: "14px",
                                  borderRadius: "2px",
                                  background: `${color.button.text}`,
                                }}
                              />
                            </div>
                            {displayColorPicker.button.text ? (
                              <div
                                style={{ position: "absolute", zIndex: "2" }}
                              >
                                <div
                                  style={{
                                    position: "fixed",
                                    top: "0px",
                                    right: "0px",
                                    bottom: "0px",
                                    left: "0px",
                                  }}
                                  onClick={() =>
                                    handleClose("button", "text")
                                  }
                                />
                                <SketchPicker
                                  color={color.headerSection.text}
                                  onChange={(e) =>
                                    handleColorChange(
                                      e,
                                      "text",
                                      "button"
                                    )
                                  }
                                />
                              </div>
                            ) : null}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="form-element-btn">
                  <button type="submit" className="btn blue" id="">
                    Update Theme
                  </button>
                </div>
                <div className="form-element-btn-left">
                  <button
                    type="submit"
                    class="btn blue"
                    id=""
                    onClick={handleBack}
                  >
                    Back
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <SuccessMessageModal
        showModal={modalData.show}
        modalTitle={modalData.title}
        modalBody={modalData.body}
        setShowModal={setModalData}
      />
    </Layout>
  );
};

export default UpdateTheme;
