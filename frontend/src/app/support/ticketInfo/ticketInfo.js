import React, { useState, useEffect } from "react";
import { EditorState, convertToRaw, convertFromRaw, RichUtils } from "draft-js";
import { Editor, ContentState } from "react-draft-wysiwyg";
import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css";
import { convertToHTML } from "draft-convert";
import { useLocation, useHistory } from "react-router-dom";
import moment from "moment"
import ContactUsService from "../../services/ContactUsService";
import CryptoJS from "crypto-js";
import Layout from "../../../layout/Layout";
import GetCaseDetailsService from "../../services/GetCaseDetails";
import API_Headers from "../../../API_Headers";
import SuccessMessageModal from "../../../components/modals/popupmodal/SuccessMessageModal";
import TicketCloseModal from "../../../components/modals/popupmodal/TicketCloseModal";
import TicketReopenModal from "../../../components/modals/popupmodal/TicketReopenModal";
import useLoader from "../../../context/loader"

const TicketInfo = () => {
  const {setLoader} = useLoader()
  const location = useLocation();
  const history = useHistory();
  const secretPass = process.env.REACT_APP_Secret_Key;
  const caseId = sessionStorage.getItem("ticketId")
  const [editorState, setEditorState] = useState(() =>
    EditorState.createEmpty()
  );

  const [convertedContent, setConvertedContent] = useState(null);
  const [headers, setHeaders] = useState([]);
  const [userDetails, setUserDetails] = useState({});
  const [userDescription, setUserDescription] = useState([]);
  const [showCloseTicketModal, setShowCloseTicketModal] = useState({ show: false })
  const [showReopenTicketModal, setShowReopenTicketModal] = useState({ show: false })
  const [emptyCheck, setEmptyCheck] = useState(false);
  const [modalData, setModalData] = useState({ show: false, title: "", body: "" });
  useEffect(() => {
    setLoader(true)
    API_Headers().then((val) => setHeaders(val));
  }, []);

  const fetchUserDetails = () => {
    if (Object.keys(headers).length > 0) {
      const payload = {
        role: sessionStorage.getItem("roleType"),
        email: sessionStorage.getItem("email"),
        caseId: caseId,
      };
      const response = GetCaseDetailsService.getCaseDetails(payload, headers);
      response.then((res) => {
        setUserDetails(res?.data[0]);
        setLoader(false)
      });
    }
  }

  useEffect(() => {
    fetchUserDetails()
  }, [headers]);

  useEffect(() => {
    const contentState = editorState.getCurrentContent();
    const rawContentState = convertToRaw(contentState);
    const serializedContent = JSON.stringify(rawContentState, (key, value) => {
      if (typeof value === 'string') {
        return value.replace(/\u2028/g, '\\u2028').replace(/\u2029/g, '\\u2029');
      }
      return value;
    });
    setConvertedContent(serializedContent);
  }, [editorState]);

  function isValidJSON(jsonString) {
    try {
      JSON.parse(jsonString);
      return true;
    } catch (error) {
      return false;
    }
  }
  useEffect(() => {
    if (userDetails !== {}) {
      var userDes;
      if (userDetails.description) {
        if (isValidJSON(userDetails.description)) {
          userDes = JSON.parse(userDetails.description);
        } else {
          userDes = [];
        }
      } else {
        userDes = [];
      }
      setUserDescription(userDes);
    }
  }, [userDetails]);

  const handleReply = async () => {
    // setIsLoaded(false)
    setLoader(true)
    if (JSON.parse(convertedContent).blocks[0].text !== "") {
      const updatedData = {
        ...userDetails,
        email: sessionStorage.getItem("email"),
        description:
          userDescription.length > 0
            ? JSON.stringify([
              ...userDescription,
              {
                id: userDescription[userDescription.length - 1].id + 1,
                sender: sessionStorage.getItem("email"),
                message: convertedContent,
                createdAt: new Date()
              },
            ])
            : JSON.stringify([
              ...userDescription,
              {
                id: 1,
                sender: sessionStorage.getItem("email"),
                message: convertedContent,
                createdAt: new Date()
              },
            ]),
      };
      const data1 = CryptoJS.AES.encrypt(
        JSON.stringify(updatedData),
        secretPass
      ).toString();
      try {
        const responseData = await ContactUsService.contactUs(data1, headers);
        if (responseData?.status == 200) {
          setLoader(false)
          setEmptyCheck(false)
          fetchUserDetails();
          const emptyEditorState = EditorState.createEmpty();
          setEditorState(emptyEditorState);
          setModalData({ show: true, title: "Contact Us", body: "You comment has been added" })
        }
      } catch (error) {
        setLoader(false)
        if (error == "Error: Network Error") {
          history.push("/SystemMaintenance");
        } else {
          alert(error.response.data.error);
        }
      }
    } else {
      setLoader(false)
      setEmptyCheck(true)
    }
  };

  const options = {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    hour12: true,
  };

  return (
    <Layout TabName="Ticket" BreadCrum={["My Dashboard", "Support", "Ticket"]}>
      <div
        class="tabContent extraWidth tasklist_table supportpage ticketinfoSupport"

      >
        <div className="d-flex-box">
          <div>
            <h1 className="heading">
              CASE ID {caseId || ""}
              <span>
                Info
              </span>
            </h1>
          </div>
          <div>
            {userDetails?.status === 'Close' && userDetails?.closedBy && (
              <p style={{ color: 'red' }}>Ticket closed by {userDetails.closedBy} on {Intl.DateTimeFormat('en-US', options).format(new Date(userDetails?.closedAt))} </p>

            )}
            {userDetails?.status === 'Close' && userDetails?.closedAt && !userDetails?.closedBy && (
              <p style={{ color: 'red' }}>Automatically closed on {Intl.DateTimeFormat('en-US', options).format(new Date(userDetails?.closedAt))} due to inactivity</p>

            )

            }
          </div>
          <div>
            {userDetails?.status === 'Close' ? (
              <button onClick={() => setShowReopenTicketModal({ ...showReopenTicketModal, show: true, caseId: sessionStorage.getItem("ticketId"), body: "Do you want to reopen this issue?" })} className="btn blue">Reopen Issue</button>
            ) : (
              <button onClick={() => setShowCloseTicketModal({ ...showCloseTicketModal, show: true, caseId: sessionStorage.getItem("ticketId"), body: "Do you want to mark the case as resolved?" })} className="btn blue">Close Issue</button>
            )}
          </div>
        </div>

        <div className="textbox"
        >
          <p className="details">
            Case Details
          </p>
          <div className="textFeild">
            <div>
              <div className="case-key">
                Subject
                <p style={{ fontWeight: 'normal' }}>{userDetails?.message || ""}</p>
              </div>
              <div className="case-key">
                Case ID
                <p style={{ fontWeight: 'normal' }}>{userDetails?.caseId || ""}</p>
              </div>
              <div className="case-key">
                Created At
                <p style={{ fontWeight: 'normal' }}>{userDetails?.createdDate ? Intl.DateTimeFormat('en-US', options).format(new Date(userDetails?.createdDate)):"-"}</p>
              </div>
            </div>
            <div className=" boxborder"
            >
              <div className="case-key">
                Status
                <p style={{ fontWeight: 'normal' }}>{userDetails?.status || ""}</p>
              </div>
              <div className="case-key">
                Severity
                <p style={{ fontWeight: 'normal' }}>{userDetails?.severity || ""}</p>
              </div>
              <div className="case-key">
                Category
                <p style={{ fontWeight: 'normal' }}>{userDetails?.category || ""}</p>
              </div>
            </div>
            <div className=" boxborder"  >
              <div className="case-key">
                Agent
                <p style={{ fontWeight: 'normal' }}>{userDetails?.agencyName || ""}</p>
              </div>
              <div className="case-key">
                Additional contacts
                <p style={{ fontWeight: 'normal' }}>{userDetails?.phoneNumber || ""}</p>
              </div>
              <div className="case-key">
                Opened by
                <p style={{ fontWeight: 'normal' }}>{userDetails?.email || ""}</p>
              </div>
            </div>
          </div>
        </div>

        <div className="correspondencebox"
        >
          <div className="correspondenceboxheading"
          >
            <div>
              <p className="fonttext" >
                Correspondence
              </p>
            </div>
          </div>

          {userDescription &&
            userDescription.length > 0 &&
            userDescription.map((ele) => (
              <div
                className="d-flex bordersolid">
                <div className="leftside"
                >
                  <div className="word-break" >
                    {ele?.sender || ""}
                    <p>{ele?.createdAt ? Intl.DateTimeFormat('en-US', options).format(new Date(ele?.createdAt)) : Intl.DateTimeFormat('en-US', options).format(new Date(ele?.createdDate))}</p>
                  </div>
                </div>
                <div className="rightside p-32-15" >
                  <div className="pt-20 text" >
                    {
                      ele?.message ? !isValidJSON(ele.message) ?
                        <div style={{ paddingTop: "10px" }}>
                          {(ele.message).toString()}
                        </div>
                        :
                        <Editor
                          toolbarHidden
                          editorState={ele?.message ? EditorState.createWithContent(convertFromRaw(JSON.parse(ele?.message))) : ''}
                          readOnly
                        ></Editor>
                        :
                        ""
                    }
                  </div>
                </div>
              </div>
            ))}
          {userDetails?.status != 'Close' ? (
            <div style={{ border: 'solid', color: "grey", borderWidth: 'thin', marginLeft: '2%', marginRight: '2%', marginTop: '2%' }}>
              <Editor
                editorStyle={{ background: 'white' }}
                editorState={editorState}
                onEditorStateChange={setEditorState}
                wrapperClassName="wrapper-class"
                editorClassName="editor-class"
                toolbarClassName="toolbar-class"
                toolbar={{
                  link: { showOpenOptionOnHover: true },
                }}
              />
              <div className="text-right-w-100">
                <button
                  className="btn blue mt-1 mr-15"
                  onClick={handleReply}

                >
                  Reply
                </button>
              </div>
            </div>
          ) : null}
        </div>
        <SuccessMessageModal showModal={modalData.show} modalTitle={modalData.title} modalBody={modalData.body} setShowModal={setModalData} />
        <TicketCloseModal showModal={showCloseTicketModal} setShowModal={setShowCloseTicketModal} fetchUserDetails={fetchUserDetails} />
        <TicketReopenModal showModal={showReopenTicketModal} setShowModal={setShowReopenTicketModal} fetchUserDetails={fetchUserDetails} />
      </div>
    </Layout>
  );
};

export default TicketInfo;
