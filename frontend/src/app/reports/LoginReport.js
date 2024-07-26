import { useState } from 'react';
import { useEffect } from 'react';
import { useForm } from "react-hook-form"
import FormControl from "../../components/formcontrol/FormControl"
import { Pagination } from 'react-bootstrap';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import Layout from '../../layout/Layout';
import LoginService from '../services/LoginService';
import CognitoUserService from '../services/CognitoUserService';
import API_Headers from "../../API_Headers";
import useLoader from "../../context/loader"

const LoginReportDetails = () => {
    const {setLoader} = useLoader()
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [userRow, setUserRow] = useState([]);
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [activePage, setActivePage] = useState(1)
    const [userList, setUserList] = useState([]);
    const [userNames, setUserNames] = useState([]);
    const [userCognitoList, setUserCognitoList] = useState([]);
    const [headers, setHeaders] = useState([])
    useEffect(() => {
        setLoader(true)
        API_Headers().then((val) => setHeaders(val))
    }, [])

    useEffect(() => {
        if (headers.length !== 0) {
            try {
                const responseData = LoginService.getLoginUser(headers);
                responseData.then((res) => {
                    setUserList(res.data.sort(function (a, b) {
                        return new Date(b.lastUsed) - new Date(a.lastUsed);
                    }))
                })
            } catch (error) {

            }
            try {
                const responseData1 = CognitoUserService.getCognitoUsers(headers);
                responseData1.then((res1) => {
                    var u = []
                    setUserCognitoList(res1.data)
                    res1.data.map((us) => {
                        u.push(us.userName)
                    })
                    setUserNames(u)
                })
            } catch (error) {
                setErrorModal({ show: true, title: "Error", body: "User not found!" })
            }
        }
    }, [headers])

    useEffect(() => {
        if (userCognitoList.length > 0 && userNames.length > 0) {
            var ur = []
            var un = []
            userCognitoList.map((user) => {
                if (userList.length > 0) {
                    userList.map((r) => {
                        if (userNames.includes(r.userName)) {
                            if (!un.includes(r.userName)) {
                                var index = userNames.indexOf(r.userName)
                                const date = new Date(r.lastUsed)
                                const convertedDate = ("00" + (date.getMonth() + 1)).slice(-2)
                                    + "-" + ("00" + date.getDate()).slice(-2)
                                    + "-" + date.getFullYear() + " "
                                    + ("00" + date.getHours()).slice(-2) + ":"
                                    + ("00" + date.getMinutes()).slice(-2)
                                    + ":" + ("00" + date.getSeconds()).slice(-2);
                                const tableR = <tr>
                                    <td>{r.userName}</td>
                                    <td>{userCognitoList[index].email}</td>
                                    <td>{userCognitoList[index].userStatus}</td>
                                    <td>{convertedDate}</td>
                                </tr>
                                ur.push(tableR)
                                un.push(r.userName)
                            }
                        }
                    })
                    if (!un.includes(user.userName)) {
                        const tableR = <tr>
                            <td>{user.userName}</td>
                            <td>{user.email}</td>
                            <td>{user.userStatus}</td>
                            <td>Yet to start using</td>
                        </tr>
                        ur.push(tableR)
                        un.push(user.userName)
                    }
                } else {
                    if (!un.includes(user.userName)) {
                        const tableR = <tr>
                            <td>{user.userName}</td>
                            <td>{user.email}</td>
                            <td>{user.userStatus}</td>
                            <td>Yet to start using</td>
                        </tr>
                        ur.push(tableR)
                        un.push(user.userName)
                    }
                }
            })
            setUserRow(ur)
            setLoader(false)
        }
    }, [userCognitoList, userNames])


    const onSearch = (data) => {
        var ur = []
        var un = []
        var c = 0
        if (data.userName !== "") {
            userCognitoList.map((user, ind) => {
                c = c + 1
                if (userList.length > 0) {
                    userList.map((r) => {
                        if (userNames.includes(data.userName) && r.userName == data.userName) {
                            if (!un.includes(data.userName)) {
                                c = c - 1
                                var index = userNames.indexOf(data.userName)
                                const date = new Date(r.lastUsed)
                                const convertedDate = ("00" + (date.getMonth() + 1)).slice(-2)
                                    + "-" + ("00" + date.getDate()).slice(-2)
                                    + "-" + date.getFullYear() + " "
                                    + ("00" + date.getHours()).slice(-2) + ":"
                                    + ("00" + date.getMinutes()).slice(-2)
                                    + ":" + ("00" + date.getSeconds()).slice(-2);
                                const tableR = <tr>
                                    <td>{userCognitoList[index].userName}</td>
                                    <td>{userCognitoList[index].email}</td>
                                    <td>{userCognitoList[index].userStatus}</td>
                                    <td>{convertedDate}</td>
                                </tr>
                                ur.push(tableR)
                                un.push(data.userName)
                            }
                        }
                    })
                    if (userNames.includes(data.userName)) {
                        if (!un.includes(data.userName)) {
                            c = c - 1
                            var index = userNames.indexOf(data.userName)
                            const tableR = <tr>
                                <td>{userCognitoList[index].userName}</td>
                                <td>{userCognitoList[index].email}</td>
                                <td>{userCognitoList[index].userStatus}</td>
                                <td>Yet to start using</td>
                            </tr>
                            ur.push(tableR)
                            un.push(data.userName)
                        }
                    }
                }
                else if (userNames.includes(data.userName)) {
                    if (!un.includes(data.userName)) {
                        c = c - 1
                        var index = userNames.indexOf(data.userName)
                        const tableR = <tr>
                            <td>{userCognitoList[index].userName}</td>
                            <td>{userCognitoList[index].email}</td>
                            <td>{userCognitoList[index].userStatus}</td>
                            <td>Yet to start using</td>
                        </tr>
                        ur.push(tableR)
                        un.push(data.userName)
                    }
                }
                if (userCognitoList.length === c) {
                    setErrorModal({ show: true, title: "Error", body: "User not found!" })
                }
            })

            setUserRow(ur)
            setLoader(false)
        }
        else {
            userCognitoList.map((user) => {
                if (userList.length > 0) {
                    userList.map((r) => {
                        if (userNames.includes(r.userName)) {
                            if (!un.includes(r.userName)) {
                                var index = userNames.indexOf(r.userName)
                                const date = new Date(r.lastUsed)
                                const convertedDate = ("00" + (date.getMonth() + 1)).slice(-2)
                                    + "-" + ("00" + date.getDate()).slice(-2)
                                    + "-" + date.getFullYear() + " "
                                    + ("00" + date.getHours()).slice(-2) + ":"
                                    + ("00" + date.getMinutes()).slice(-2)
                                    + ":" + ("00" + date.getSeconds()).slice(-2);
                                const tableR = <tr>
                                    <td>{r.userName}</td>
                                    <td>{userCognitoList[index].email}</td>
                                    <td>{userCognitoList[index].userStatus}</td>
                                    <td>{convertedDate}</td>
                                </tr>
                                ur.push(tableR)
                                un.push(r.userName)
                            }
                        }
                    })
                    if (!un.includes(user.userName)) {
                        const tableR = <tr>
                            <td>{user.userName}</td>
                            <td>{user.email}</td>
                            <td>{user.userStatus}</td>
                            <td>Yet to start using</td>
                        </tr>
                        ur.push(tableR)
                        un.push(user.userName)
                    }
                } else {
                    if (!un.includes(user.userName)) {
                        const tableR = <tr>
                            <td>{user.userName}</td>
                            <td>{user.email}</td>
                            <td>{user.userStatus}</td>
                            <td>Yet to start using</td>
                        </tr>
                        ur.push(tableR)
                        un.push(user.userName)
                    }
                }
            })
            setUserRow(ur)
            setLoader(false)
        }
    }

    var noOfRowsPerPage = 10;
    var noOfPagesToDisplay = 25;
    let items = [];
    for (let number = 1; ((number - 1 - (1 / 10)) <= (userRow.length / 10) && number <= noOfPagesToDisplay); number++) {
        items.push(
            <Pagination.Item key={number} active={number === activePage} onClick={() => setActivePage(number)}>
                {number}
            </Pagination.Item>,
        );
    }
    return (
        <Layout TabName='Login Report' BreadCrum={['My Dashboard', 'Admin', 'Login Report']} sidebarQQSopen='true'>
            <div className="coverContainer">
                <div className="dashboard">
                    <div className="left">
                        <div class="inf">
                            <form class="allForm" onSubmit={handleSubmit(onSearch)}>
                                <div className="grid2">
                                    <div class="form-element">
                                        <FormControl fieldName="userName" label='User Name' id='username' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={false} lableClass='form-label' activeBold={false} />
                                    </div>
                                    <div class="form-element">
                                        <button type="submit" className='btn blue' id="">Search</button>
                                    </div>
                                </div>
                                {userRow.length !== 0 ?
                                        <div class="tabContent extraWidth" style={{ display: "block" }}>
                                            <table class='table table-hover'>
                                                <thead>
                                                    <tr>
                                                        <th>User Id</th>
                                                        <th>Email Id</th>
                                                        <th>Status</th>
                                                        <th>Last Used</th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    {
                                                        userRow.map((item, ind) => {
                                                            return activePage === parseInt(ind / noOfRowsPerPage) + 1 ?
                                                                <>{item}</>
                                                                :
                                                                ''
                                                        })
                                                    }
                                                </tbody>
                                            </table>
                                            <br />
                                            <Pagination size="sm" style={{ justifyContent: "center" }}>
                                                {items}
                                            </Pagination>
                                            <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
                                        </div>
                                        :
                                        <div class="tabContent extraWidth" style={{ display: "block", color: 'red' }}><p>No rows found!</p></div>
                                }

                            </form>
                        </div>
                    </div>
                </div>
                <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
            </div>
        </Layout>

    )
}
export default LoginReportDetails;