import React, { useEffect, useState } from "react";
import Layout from "../../layout/Layout";
import { useHistory } from "react-router-dom";
import axios from "axios";
import PerformenceStaticsTableByPremium from "./PermiumList";
import PerformenceStaticsTableByQuote from "./QuoteList";
import dash1 from "../../assets/img/icons/dash1.png";
import dash2 from "../../assets/img/icons/dash2.png";
import dash3 from "../../assets/img/icons/dash3.png";
import API_Headers from '../../API_Headers';
import CognitoUserService from "../services/CognitoUserService";
import { map } from "jquery";
import LoginService from "../services/LoginService";

const Statistics = () => {
    const dashboardURL = process.env.REACT_APP_OPEN_WORK_ITEM_DB_URL;
    const userStatsURL = process.env.REACT_APP_USER_STATS_URL;
    const history = useHistory();
    const [responseJson, setResponseJson] = useState({ data: [] });
    const [userStatsJson, setUserStatsJson] = useState({ data: [] });
    const [headers, setHeaders] = useState([])
    const [cognitoData, setCognitoData] = useState([]);
    const [loginData, setLoginData] = useState([]);
    const [agentCount, setAgentCount] = useState(0);
    const [adminCount, setAdminCount] = useState(0);
    const [agencyCount, setAgencyCount] = useState(0);
    const [loggedOnceAdmin, setLoggedOnceAdmin] = useState(0);
    const [loggedOnceAgent, setLoggedOnceAgent] = useState(0);
    useEffect(() => {
        API_Headers().then((val) => {
            setHeaders(val)
        })
    }, [])

    useEffect(() => {
        if (headers.length !== 0) {
            premiumChart();
            userStats();
            const loggedInUser = sessionStorage.getItem("userName");
            if (!loggedInUser) {
                history.push("/");
            }
        }
    }, [headers]);

    useEffect(async () => {
        if (headers.length !== 0) {
            try {
                const response = await CognitoUserService.getCognitoUsers(headers);
                const response1 = await LoginService.getLoginUser(headers);
                setCognitoData(response.data);
                setLoginData(response1.data);
            } catch (error) {
                console.error("Error fetching Cognito users and Login Users:", error);
            }
        }
    }, [headers]);

    useEffect(() => {
        if (cognitoData.length !== 0 && loginData.length !== 0) {
            // total count for all admin and agents
            const adminUsers = cognitoData.filter((user) => user.role === "admin");
            const agentUsers = cognitoData.filter((user) => user.role === "agent");
            //neglecting the null values and duplicate values
            const agencyUsers = new Set(cognitoData.map((user) => user.organization).filter(Boolean)).size;

            setAdminCount(adminUsers.length);
            setAgentCount(agentUsers.length);
            setAgencyCount(agencyUsers);

            let loggedOnceAdmin = 0;
            let loggedOnceAgent = 0;
            const logindatamap = loginData.map((user) => user.userName);
            logindatamap.forEach((userLogin) => {
                const foundUser = cognitoData.find((user) => user.userName === userLogin);
                if (foundUser) {
                    if (foundUser.role === "admin") {
                        loggedOnceAdmin += 1;
                    } else if (foundUser.role === "agent") {
                        loggedOnceAgent += 1;
                    }
                }
            });

            setLoggedOnceAdmin(loggedOnceAdmin);
            setLoggedOnceAgent(loggedOnceAgent);
        }
    }, [cognitoData, loginData]);


    var quoteCount = 0;
    var totalPremium = 0;
    responseJson.data.forEach((val, ind) => {

        if (val.submissionStatus === "Yes") {
            quoteCount = quoteCount + 1;
            totalPremium = totalPremium + parseInt(val.premium);
        }
    });

    var dailyLogin = 0;
    var weeklyLogin = 0;
    var monthlyLogin = 0;
    var yearlyLogin = 0;

    const todayDate = new Date().toLocaleDateString();
    const todayDate1 = todayDate.split('/')
    var curr = new Date;
    var firstday = new Date(curr.setDate(curr.getDate() - curr.getDay())).toLocaleDateString();
    var lastday = new Date(curr.setDate(curr.getDate() - curr.getDay() + 6)).toLocaleDateString();
    const weekStart = firstday.split('/')
    const weekEnd = lastday.split('/')

    userStatsJson.data.forEach((val, ind) => {
        const date_in_use = new Date(val.last_used).toLocaleDateString()
        const date1 = date_in_use.split('/')
        console.log(date1)
        console.log(weekStart)
        console.log(weekEnd)
        if (date1[0] === todayDate1[0] && date1[1] === todayDate1[1] && date1[2] === todayDate1[2]) {
            dailyLogin = dailyLogin + 1
        }

        if (
            (parseInt(date1[0]) === parseInt(weekStart[0]) && parseInt(date1[0]) === parseInt(weekEnd[0])
                && (parseInt(date1[2]) === parseInt(weekStart[2]) && parseInt(date1[2]) === parseInt(weekEnd[2]))
                && parseInt(date1[1]) >= parseInt(weekStart[1]) && parseInt(date1[1]) <= parseInt(weekEnd[1])) ||
            (parseInt(date1[0]) >= parseInt(weekStart[0]) && parseInt(date1[0]) <= parseInt(weekEnd[0])
                && (parseInt(date1[2]) === parseInt(weekStart[2]) && parseInt(date1[2]) === parseInt(weekEnd[2])) &&
                (parseInt(date1[1]) >= parseInt(weekStart[1]) || parseInt(date1[1]) <= parseInt(weekEnd[1]))) ||
            ((parseInt(date1[2]) >= parseInt(weekStart[2]) && parseInt(date1[2]) <= parseInt(weekEnd[2])) &&
                (parseInt(date1[1]) >= parseInt(weekStart[1]) || parseInt(date1[1]) <= parseInt(weekEnd[1]))
            )
        ) {
            weeklyLogin = weeklyLogin + 1;
        }
        if ((date1[0] === todayDate1[0]) && (date1[2] === todayDate1[2])) {
            monthlyLogin = monthlyLogin + 1
        }
        if (date1[2] === todayDate1[2]) {
            yearlyLogin = yearlyLogin + 1
        }
    });


    const premiumChart = () => {
        axios
            .post(dashboardURL, sessionStorage.getItem("roleType"), {
                headers: headers,
            })
            .then((res) => {
                setResponseJson(res);
            })
            .catch((err) => {
            });
    };

    const userStats = () => {
        axios
            .get(userStatsURL, {
                headers: headers,
            })
            .then((res) => {
                setUserStatsJson(res);
            })
            .catch((err) => {
            });
    };

    return (
        <Layout
            TabName="PERFORMANCE STATISTICS"
            BreadCrum={["Statistics"]}
            sidebarQQSopen="true"
        >
            <div className="coverContainer">
                <div className="dashboard">
                    <div className="left">
                        <h1>Top 10 users with maximum premium</h1>
                        {
                            responseJson.data.length !== 0 ?
                                <PerformenceStaticsTableByPremium responseJson={responseJson} totalPremium={totalPremium} />
                                :
                                <div style={{ color: 'red' }}>No data to display!</div>
                        }
                        <br />
                        <h1>Top 10 users with maximum quotes</h1>
                        {
                            responseJson.data.length !== 0 ?
                                <PerformenceStaticsTableByQuote responseJson={responseJson} quoteCount={quoteCount} />
                                :
                                <div style={{ color: 'red' }}>No data to display!</div>
                        }
                        <br />
                        <h1>User Login Stats</h1>
                        <div className="statwrap-4">
                            <div className="statbx" style={{ border: "1px solid black" }}>
                                <div className="icon">
                                    <span>
                                        <img src={dash1} />
                                    </span>{" "}
                                    <p>Login Today</p>
                                </div>
                                <div className="num">{dailyLogin}</div>
                            </div>
                            <div className="statbx" style={{ border: "1px solid black" }}>
                                <div className="icon">
                                    <span>
                                        <img src={dash2} />
                                    </span>{" "}
                                    <p>Login This Week</p>
                                </div>
                                <div className="num">{weeklyLogin}</div>
                            </div>
                            <div className="statbx" style={{ border: "1px solid black" }}>
                                <div className="icon">
                                    <span>
                                        <img src={dash3} />
                                    </span>{" "}
                                    <p>Login This Month</p>
                                </div>
                                <div className="num">{monthlyLogin}</div>
                            </div>
                            <div className="statbx" style={{ border: "1px solid black" }}>
                                <div className="icon">
                                    <span>
                                        <img src={dash1} />
                                    </span>{" "}
                                    <p>Login This Year</p>
                                </div>
                                <div className="num">{yearlyLogin}</div>
                            </div>
                            <div className="statbx" style={{ border: "1px solid black" }}>
                                <div className="icon">
                                    <span>
                                        <img src={dash3} />
                                    </span>{" "}
                                    <p>Admin Count</p>
                                </div>
                                <div className="num">{adminCount}</div>
                            </div>
                            <div className="statbx" style={{ border: "1px solid black" }}>
                                <div className="icon">
                                    <span>
                                        <img src={dash3} />
                                    </span>{" "}
                                    <p>Agent Count</p>
                                </div>
                                <div className="num">{agentCount}</div>
                            </div>
                            <div className="statbx" style={{ border: "1px solid black" }}>
                                <div className="icon">
                                    <span>
                                        <img src={dash3} />
                                    </span>{" "}
                                    <p>Agency Count</p>
                                </div>
                                <div className="num">{agencyCount}</div>
                            </div>
                            <div className="statbx" style={{ border: "1px solid black" }}>
                                <div className="icon">
                                    <span>
                                        <img src={dash3} />
                                    </span>{" "}
                                    <p>Logged-In Admin Count</p>
                                </div>
                                <div className="num">{loggedOnceAdmin}</div>
                            </div>
                            <div className="statbx" style={{ border: "1px solid black" }}>
                                <div className="icon">
                                    <span>
                                        <img src={dash3} />
                                    </span>{" "}
                                    <p>Logged-In Agent Count</p>
                                </div>
                                <div className="num">{loggedOnceAgent}</div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </Layout>
    );
};

export default Statistics;
