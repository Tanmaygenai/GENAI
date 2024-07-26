import React, { useEffect, useState } from "react";
import Layout from "../../layout/Layout";
import $ from "jquery";
import TrophyIcon from "../../assets/img/allCardIcon/trophy.svg";
import People from "../../assets/img/allCardIcon/people-svg.svg";
import { useHistory } from "react-router-dom";
import { Bar, Line, Doughnut } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend, registerables } from "chart.js";
import DashboardService from "../services/DashboardService";
import CognitoUserService from "../services/CognitoUserService";
import API_Headers from "../../API_Headers";
import { Card, Col, Row } from "react-bootstrap";
import useLoader from '../../context/loader'
import { CircularProgressbar, buildStyles } from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';


ChartJS.register(ArcElement, Tooltip, Legend, ...registerables);

const Dashboard = () => {
  const history = useHistory();
  const { setLoader } = useLoader()
  const [lnCount, setLNCount] = useState(0);
  const [monthlyReportBarData, setMonthlyReportBarData] = useState([]);
  const [monthlyPremiumBarData, setMonthlyPremiumBarData] = useState([]);
  const [graphDataItem, setGraphDataItem] = useState([]);
  const [titleFont, setTitleFont] = useState(12);
  const [totalPremPerProd, setTotalPremPerProd] = useState([])

  const [responseJson, setResponseJson] = useState({ data: [] });
  const [rankedUsers, setRankedUsers] = useState([]);
  const [loggedInUserRankData, setLoggedInUserRankData] = useState({});
  const [quoteCount, setQuoteCount] = useState(0)
  const [totalPremium, setTotalPremium] = useState(0)
  const progressPercentage = (500 / 1000) * 100;
  const [pieChartData, setPieChartData] = useState({
    labels: [],
    datasets: [
      {
        label: "",
        data: [],
        backgroundColor: [],
        borderColor: [],
        borderWidth: 0,
      },
    ],
  });
  // const headers = API_Headers();
  const [headers, setHeaders] = useState([]);

  useEffect(() => {
    API_Headers().then((val) => setHeaders(val));
  }, []);

  useEffect(() => {
    pieChart();
    let calQuote = 0
    let calTotalPremium = 0
    responseJson.data.forEach((val, ind) => {
      if (val.submissionStatus === "Yes") {
        calQuote++;
        calTotalPremium = calTotalPremium + parseInt(val.premium);
      }
    });
    setQuoteCount(calQuote)
    setTotalPremium(calTotalPremium)
  }, [responseJson]);

  useEffect(() => {
    if (headers.length !== 0) {
      monthlyPremiumChart();
      premiumChart();
      getLNCount(sessionStorage.getItem("roleType"));
      getChartData();
      monthlySubReportChart();
      totalPremiumReportFunc()
      premiumFetchData();

      const loggedInUser = sessionStorage.getItem("userName");
      if (!loggedInUser) {
        history.push("/");
      }
      $("body").on("click", ".tabMenu .item a", function () {
        var indx = $(this).parent().index();
        $(".tabMenu .item a").removeClass("actv");
        $(this).addClass("actv");
        $(".tabContent").hide();
        $(".tabContent").eq(indx).fadeIn();
      });
    }
  }, [headers]);

  useEffect(() => {
    const handleResize = () => {
      if (window.innerWidth >= 2440) {
        setTitleFont(24)
      } else if (window.innerWidth < 2440 && window.innerWidth >= 1560) {
        setTitleFont(18)
      }
    };
    window.addEventListener('resize', handleResize);
    handleResize();
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, [])

  const calculateRanksAndPoints = (premiumData, cognitoData) => {
    if (!premiumData || !Array.isArray(premiumData) || premiumData.length === 0 ||
      !cognitoData || !Array.isArray(cognitoData) || cognitoData.length === 0) {
      console.log("Premium data or Cognito data is empty or invalid.");
      return;
    }
    const agentUsers = cognitoData.filter((user) => user.role === "agent");
    if (agentUsers.length === 0) {
      console.log("No agent users found in Cognito data.");
      return;
    }
    const agentPremiumData = premiumData.filter((quote) => {
      return agentUsers.some((agent) => agent.userName === quote.userName);
    });
    if (agentPremiumData.length === 0) {
      console.log("No premium data found for agent users.");
      return;
    }
    const userMap = new Map();
    agentPremiumData.forEach((quote) => {
      const userName = quote.userName;
      const premium = parseFloat(quote.premium);
      const quoteCount = 1;

      if (userMap.has(userName)) {
        const existingData = userMap.get(userName);
        const totalPremium = existingData.totalPremium + premium;
        const totalQuotes = existingData.totalQuotes + quoteCount;
        userMap.set(userName, { totalPremium, totalQuotes });
      } else {
        userMap.set(userName, { totalPremium: premium, totalQuotes: quoteCount });
      }
    });
    if (userMap.size === 0) {
      console.log("No data found to calculate ranks and points.");
      return;
    }
    const usersWithPoints = Array.from(userMap.entries()).map(([userName, data]) => {
      const { totalPremium, totalQuotes } = data;
      const point = Math.round((totalPremium * totalQuotes) / 100);
      return { userName, totalPremium, totalQuotes, point };
    });
    const sortedUsers = usersWithPoints.sort((a, b) => b.point - a.point);
    if (sortedUsers.length === 0) {
      console.log("No users with points found.");
      return;
    }
    const rankedUsers = sortedUsers.map((user, index) => ({ ...user, rank: index + 1 }));
    const loggedInUser = sessionStorage.getItem("userName");
    const loggedInUserRole = sessionStorage.getItem("roleType");
    let loggedInUserData = {
      userName: loggedInUser,
      totalPremium: 0,
      totalQuotes: 0,
      point: 0,
      rank: 0,
    };
    if (loggedInUserRole === "admin") {
      loggedInUserData.point = "N/A";
      loggedInUserData.rank = "N/A";
    } else {
      const foundUser = rankedUsers.find((user) => user.userName === loggedInUser);
      if (foundUser) {
        loggedInUserData = foundUser;
      }
    }
    let currentRank = 1;
    let prevPoint = sortedUsers[0].point;
    for (let i = 0; i < sortedUsers.length; i++) {
      const user = sortedUsers[i];
      if (user.point < prevPoint) {
        currentRank++;
      }
      user.rank = currentRank;
      prevPoint = user.point;
      user.rank = currentRank;
      prevPoint = user.point;
    }
    if (rankedUsers.length === 0) {
      console.log("No ranked users data to set.");
      return;
    }
    if (loggedInUserData === null || loggedInUserData === undefined) {
      console.log("No logged-in user data to set.");
      return;
    }
    setRankedUsers(rankedUsers);
    setLoggedInUserRankData(loggedInUserData);
  }

  const getLNCount = (role) => {
    const responseData = DashboardService.getLossNoticeCount(role, headers);
    responseData
      .then((res) => {
        setLNCount(res.data);
      })
      .catch((err) => {
        console.log(err)
        alert(err);
      });
  };

  const totalPremiumReportFunc = () => {
    var totalPremiumReport = []
    const roleType = sessionStorage.getItem("roleType");
    const totalPremiumData = DashboardService.getTotalPremiumReport(headers, roleType);
    totalPremiumData
      .then((reportResponse) => {
        reportResponse.data.map((res) => {
          totalPremiumReport.push({
            product: res[0],
            premium: res[1]
          })
        })
        setTotalPremPerProd(totalPremiumReport)
      })
      .catch((err) => {
      });
  }
  const monthlySubReportChart = () => {
    const reportState = {
      labels: "",
      datasets: [
        {
          label: "Indications",
          backgroundColor: "#767171",
          borderColor: "rgba(0,0,0,1)",
          borderWidth: 1,
          data: "",
        },
        {
          label: "Submissions",
          backgroundColor: "#252424",
          borderColor: "rgba(0,0,0,1)",
          borderWidth: 1,
          data: "",
        },
        {
          label: "Pending Submissions",
          backgroundColor: "#CF0918",
          borderColor: "rgba(0,0,0,1)",
          borderWidth: 1,
          data: "",
        },
      ],
    };

    const roleType = sessionStorage.getItem("roleType");
    const resData = DashboardService.getMonthlyReport(headers, roleType);
    try {
      resData
        .then((reportResponse) => {
          const indicationCount = [];
          const submissionCount = [];
          const pending = [];
          const month = [];
          reportResponse.data.forEach((val) => {
            month.push(val[0]);
            indicationCount.push(val[1]);
            submissionCount.push(val[2]);
            pending.push(val[3]);
            reportState.datasets[0].data = indicationCount;
            reportState.datasets[1].data = submissionCount;
            reportState.datasets[2].data = pending;
            reportState.labels = month;
            setMonthlyReportBarData(reportState);
          });
        })
        .catch((err) => {
          console.log(err)
          alert(err);
        });
    } catch (error) {
      console.log(error)
    }

  };

  const pieChart = () => {
    var comAuto = 0;
    var comProperty = 0;
    var comExcess = 0;

    responseJson.data.map((val, ind) => {
      if (val.product === "Commercial Auto") {
        comAuto++;
      } else if (val.product === "Commercial Property") {
        comProperty++;
      } else if (val.product === "Commercial Excess Liability") {
        comExcess++;
      }
    });
    var newPieData = {
      labels: ["Auto", "Property", "Liability"],
      datasets: [
        {
          label: "Product Classification",
          data: [comAuto, comProperty, comExcess],
          backgroundColor: ["#767171", "#252424", "#CF0918"],
          borderColor: ["rgba(0,0,0,1)", "rgba(0,0,0,1)", "rgba(0,0,0,1)"],
          borderWidth: 1,
        },
      ],
    };
    setPieChartData(newPieData);
  };

  const premiumChart = () => {
    setLoader(true)
    const responseData = DashboardService.getDashboardReport(
      sessionStorage.getItem("roleType"),
      headers
    );
    responseData
      .then((res) => {
        setResponseJson(res);
        setLoader(false)
      })
      .catch((err) => {
        setLoader(true)
      });
  };

  const monthlyPremiumChart = async () => {
    const reportState = {
      labels: "",
      datasets: [
        {
          label: "Auto Premium",
          backgroundColor: "#767171",
          borderColor: "rgba(0,0,0,1)",
          borderWidth: 1,
          data: "",
        },
        {
          label: "Property Premium",
          backgroundColor: "#252424",
          borderColor: "rgba(0,0,0,1)",
          borderWidth: 1,
          data: "",
        },
        {
          label: "Liability Premium",
          backgroundColor: "#CF0918",
          borderColor: "rgba(0,0,0,1)",
          borderWidth: 1,
          data: "",
        },
      ],
    };
    const roleType = sessionStorage.getItem("roleType");
    const resData = DashboardService.getMonthlyPremiumCharReport(
      headers,
      roleType
    );
    resData
      .then((reportResponse) => {
        const autoPremium = [];
        const propPremium = [];
        const liabPremium = [];
        const month = [];
        reportResponse.data.forEach((val) => {
          month.push(val[0]);
          autoPremium.push(val[1]);
          propPremium.push(val[2]);
          liabPremium.push(val[3]);
          reportState.datasets[0].data = autoPremium;
          reportState.datasets[1].data = propPremium;
          reportState.datasets[2].data = liabPremium;
          reportState.labels = month;
          setMonthlyPremiumBarData(reportState);
        });
      })
      .catch((err) => {
        console.log(err)
        alert(err);
      });
  };
  const getChartData = async () => {
    try {
      const responseData = await DashboardService.getChartData(null, headers);
      let data = Object.keys(responseData.data).map((keyName, i) => {
        return {
          [keyName.split("_").join(" ")]: responseData.data[keyName],
        };
      });
      const orderOfKeys = ["indications", "quotes", "applications", "policy", "submissions"];
      let arrangedCaseArray = orderOfKeys.map(key => data.find(obj => Object.keys(obj)[0] === key));
      arrangedCaseArray = arrangedCaseArray.filter((val) => !("submissions" in val))
      setGraphDataItem(arrangedCaseArray);
    } catch (error) {
      console.log(error)
      alert(error);
    }
  };

  const sumTotalPremium = () => {
    if (totalPremPerProd.length > 0) {
      var sum = 0
      totalPremPerProd.map((tp) => {
        sum = sum + parseFloat(tp.premium)
      })
      return sum
    }
  }


  const premiumFetchData = async () => {
    try {
      const res = await DashboardService.getQuoteReport(headers);
      const cognitoUserData = await CognitoUserService.getCognitoUsers(headers)
      calculateRanksAndPoints(res.data, cognitoUserData.data);
    } catch (error) {
      console.log(error)
      alert(error)
    }
  };


  return (
    <Layout
      TabName="PERFORMANCE STATISTICS"
      BreadCrum={["My Dashboard"]}
      sidebarQQSopen="true"
    >
      <div className="dashboardSection">
        <Row >
          <Col lg={9} xs={12}>
            <div className="ps-3">
              <Row className="mb-1 desktop-screen-gap" style={{ gap: "10px 0" }}>
                <Col lg={4}>
                  <Card className="px-2 bgcolorQuotes"
                  >
                    <div className="statwrap p-2">
                      <div className="icon">
                        <div className="d-flex gap-2">
                          <div className="num text-light">{quoteCount}</div>
                        </div>
                        <p>Total Quotes</p>
                      </div>
                    </div>
                  </Card>
                </Col>
                <Col lg={4}>
                  <Card className="px-2 bgcolornotices" >
                    <div className="statwrap p-2">
                      <div className="icon">
                        <div className="d-flex gap-2">
                          <div className="num text-light">{lnCount}</div>
                        </div>
                        <p>Total Loss Notices</p>
                      </div>
                    </div>
                  </Card>
                </Col>
                <Col lg={4}>
                  <Card className="px-2 bgcolorpremium" >
                    <div className="statwrap p-2">
                      <div className="icon">
                        <div className="d-flex gap-2">
                          <div className="num text-light">
                            {Intl.NumberFormat("en-US", {
                              style: "currency",
                              currency: "USD",
                            }).format(totalPremium)}
                          </div>
                        </div>
                        <p className="">Total Premium</p>
                      </div>
                    </div>
                  </Card>
                </Col>
              </Row>
              <Row className="desktop-screen-graph">
                <Col className="mb-1 firstgraph" lg={6} xs={12} >
                  <Card className="px-3">
                    {monthlyReportBarData !== undefined && (
                      <div className="linechart-height">
                        <Bar
                          data={!!Object.keys(monthlyReportBarData).length ? monthlyReportBarData : {
                            labels: [], // Empty labels array
                            datasets: [
                              {
                                label: 'No Data Available',
                                data: [], // Empty data array
                              },
                            ],
                          }}
                          options={{
                            plugins: {
                              title: {
                                display: true,
                                text: "MONTHLY SUBMISSION REPORT",
                                font: {
                                  size: titleFont,
                                },
                              },
                              legend: {
                                display: true,
                                position: "top",
                                labels: {
                                  font: {
                                    size: titleFont,
                                  },
                                },
                              },
                            },
                            maintainAspectRatio: false,
                          }}
                        />
                      </div>
                    )}
                  </Card>
                </Col>
                <Col className="mb-1 secondgraph" lg={6} xs={12}>
                  <Card className="px-3">
                    <div className="">
                      {monthlyPremiumBarData !== undefined && (
                        <div className="linechart-height">
                          <Line
                            data={!!Object.keys(monthlyPremiumBarData).length ? monthlyPremiumBarData : {
                              labels: [], // Empty labels array
                              datasets: [
                                {
                                  label: 'No Data Available',
                                  data: [], // Empty data array
                                },
                              ],
                            }}
                            options={{
                              responsive: true,
                              plugins: {
                                title: {
                                  display: true,
                                  text: "PREMIUM REPORT BY PRODUCT",
                                  font: {
                                    size: titleFont,
                                  },
                                },
                                legend: {
                                  display: true,
                                  position: "top",
                                  labels: {
                                    font: {
                                      size: titleFont,
                                    },
                                  },
                                },
                              },

                              maintainAspectRatio: false,
                            }}
                          />
                        </div>
                      )}
                    </div>
                  </Card>
                </Col>
                <Col className="desktop-space">
                  <Card className="desktop-p">
                    <div className="dashboard">
                      <div className="left">
                        {graphDataItem.length !== 0 ? (
                          <div className="graphDash">
                            <div className="caseSummary">
                              <h1>Case Summary</h1>
                              <div className="graphArea">
                                {graphDataItem.map((item, index) => {
                                  return (
                                    <>
                                      <div className="graphData">
                                        <h1>{Object.keys(item)}</h1>
                                        <div className="icon">
                                          <span class="checkmark">
                                            <div class="checkmark_circle"></div>
                                            <div class="checkmark_stem"></div>
                                            <div class="checkmark_kick"></div>
                                          </span>
                                          {index < graphDataItem.length - 1 ? (
                                            <>
                                              <div className="line"></div>
                                              {/* <img src={EKG} /> */}
                                            </>
                                          ) : (
                                            []
                                          )}
                                        </div>
                                        <p>{item[Object.keys(item)]}</p>
                                      </div>
                                    </>
                                  );
                                })}
                              </div>
                            </div>
                          </div>
                        ) : null}
                      </div>
                    </div>
                  </Card>
                </Col>
              </Row>

            </div>
          </Col>
          <Col lg={3} xs={12} className="sm-4-px" >
            <Card className="desktop-space-m" style={{ marginBottom: "20px" }}>
              <div className="">
                {
                  <div className="pb-1 piechart-height">
                    <Doughnut
                      data={pieChartData}
                      options={{
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                          title: {
                            display: true,
                            text: "QUOTES BY PRODUCT",
                            font: {
                              size: titleFont,
                            },
                          },
                          legend: {
                            display: true,
                            position: "top",
                            labels: {
                              font: {
                                size: titleFont,
                              },
                            }
                          },
                        },
                      }}
                    />
                  </div>
                }
              </div>
            </Card>
            <Card className="mb-1 desktop-space-m">
              <div className="py-3 px-3 my-score">
                <h1 className="text-center mb-1 title-text">My Score </h1>
                <div className="d-flex justify-content-between">
                  <div className="px-3">
                    <div className="circular-progress-container">
                      <img className="icons-img" src={TrophyIcon} />
                      <CircularProgressbar
                        value={progressPercentage}
                        text={`${progressPercentage}%`}
                        width={20}
                        strokeWidth={5}
                        styles={buildStyles({
                          textColor: '#fff',
                          pathColor: '#00ADEF',
                          trailColor: '#eee',
                        })}
                      />
                    </div>
                    <h2 className="text-success">{loggedInUserRankData.rank}</h2>
                    <h3>Rank</h3>
                  </div>
                  <div className="px-3">
                    <div className="circular-progress-container">
                      <img className="icons-img" src={People} />
                      <CircularProgressbar
                        value={progressPercentage}
                        text={`${progressPercentage}%`}
                        width={20}
                        strokeWidth={5}
                        styles={buildStyles({
                          textColor: '#fff',
                          pathColor: '#CF0918',
                          trailColor: '#eee',
                        })}
                      />
                    </div>
                    <h2 className="text-danger">{loggedInUserRankData.point}</h2>
                    <h3>Point</h3>
                  </div>
                </div>
              </div>
            </Card>
            <Card className="desktop-space-m">
              <div className="py-3 px-2 agent-summary">
                <h1 className="text-center mb-1 title-text">Agent Summary </h1>
                <div>
                  <ul>
                    <li>
                      <label>Auto Premium</label>
                      <b>
                        {totalPremPerProd.length > 0 &&
                          totalPremPerProd.find((tp) => tp.product === 'Commercial Auto')?.premium != null &&
                          totalPremPerProd.find((tp) => tp.product === 'Commercial Auto')?.premium !== '' &&
                          totalPremPerProd.find((tp) => tp.product === 'Commercial Auto')?.premium !== undefined
                          ? parseFloat(totalPremPerProd.find((tp) => tp.product === 'Commercial Auto')?.premium).toLocaleString('en-US', { style: 'currency', currency: 'USD' })
                          : '$0.00'}
                      </b>
                    </li>
                    <li>
                      <label>Property Premium</label>
                      <b>
                        {totalPremPerProd.length > 0 &&
                          totalPremPerProd.find((tp) => tp.product === 'Commercial Property')?.premium != null &&
                          totalPremPerProd.find((tp) => tp.product === 'Commercial Property')?.premium !== '' &&
                          totalPremPerProd.find((tp) => tp.product === 'Commercial Property')?.premium !== undefined
                          ? parseFloat(totalPremPerProd.find((tp) => tp.product === 'Commercial Property')?.premium).toLocaleString('en-US', { style: 'currency', currency: 'USD' })
                          : '$0.00'}
                      </b>
                    </li>
                    <li>
                      <label>Liability Premium</label>
                      <b>
                        {totalPremPerProd.length > 0 &&
                          totalPremPerProd.find((tp) => tp.product === 'Commercial Excess Liability')?.premium != null &&
                          totalPremPerProd.find((tp) => tp.product === 'Commercial Excess Liability')?.premium !== '' &&
                          totalPremPerProd.find((tp) => tp.product === 'Commercial Excess Liability')?.premium !== undefined
                          ? parseFloat(totalPremPerProd.find((tp) => tp.product === 'Commercial Excess Liability')?.premium).toLocaleString('en-US', { style: 'currency', currency: 'USD' })
                          : '$0.00'}
                      </b>
                    </li>
                    <li>
                      <label>Accumulated Commission</label>
                      <b>
                        {totalPremPerProd.length > 0 &&
                          sumTotalPremium() / 5 != null &&
                          sumTotalPremium() / 5 !== ''
                          ? (sumTotalPremium() / 5).toLocaleString('en-US', { style: 'currency', currency: 'USD' })
                          : '$0.00'}
                      </b>
                    </li>

                  </ul>
                </div>
              </div>
            </Card>
          </Col>
        </Row>
      </div>
    </Layout>
  );
};

export default Dashboard;
