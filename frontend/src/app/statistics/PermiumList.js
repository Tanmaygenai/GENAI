import { useState } from 'react';
import { useEffect } from 'react';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import CognitoUserService from '../services/CognitoUserService';
import API_Headers from '../../API_Headers';
import useLoader from "../../context/loader"

const PerformenceStaticsTableByPremium = ({ responseJson, totalPremium }) => {
    const { setLoader } = useLoader();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [rowData, setRowData] = useState([]);
    const [userCognitoList, setUserCognitoList] = useState([]);
    const [sorted, setSorted] = useState(false);
    const [headers, setHeaders] = useState([])

    useEffect(() => {
        setLoader(true)
        API_Headers().then((val) => {
            setHeaders(val)
        })
    }, [])

    useEffect(() => {
        if (headers.length !== 0) {
            const responseData = CognitoUserService.getCognitoUsers(headers);
            responseData.then((res1) => {
                setUserCognitoList(res1.data)
            })
        }
    }, [headers])
    useEffect(() => {
        if (responseJson.data.length !== 0 && totalPremium !== null && userCognitoList.length !== 0) {
            var ir = []
            var ul = []
            userCognitoList.map((user, ind) => {
                responseJson.data.map((res) => {
                    if (res.userName === user.userName) {
                        if (!ul.includes(user.userName)) {
                            if (res.submissionStatus === "Yes") {
                                ul.push(user.userName)
                                ir.push({
                                    "userName": user.userName,
                                    "groupCd": user.groupCd,
                                    "agencyCd": user.agencyCd,
                                    "email": user.email,
                                    "userPrem": parseInt(res.premium),
                                    // "userPremPer": (res.premium / totalPremium )* 100      
                                })
                            }
                        } else {

                            ir.map((obj, index) => {
                                if (obj['userName'] === user.userName) {
                                    if (res.submissionStatus === "Yes") {
                                        obj["userPrem"] += parseInt(res.premium)
                                    }
                                }
                            })

                        }
                    }
                })
            })
            setRowData(ir)
            setLoader(false)
        }
    }, [responseJson, totalPremium, userCognitoList])
    useEffect(() => {
        if (rowData.length !== 0) {
            rowData.sort((a, b) => {
                return b.userPrem - a.userPrem;
            });
        }
        setRowData(rowData)
        setSorted(true)
    }, [rowData])

    return (
        <div>
            {(rowData.length !== 0 ?
                <div class="tabContent extraWidth" style={{ display: "block" }}>
                    <table>
                        <thead>
                            <tr>
                                <th>User Id</th>
                                <th>Group Id</th>
                                <th>Agency Id</th>
                                <th>Email Id</th>
                                <th>Total Premium</th>
                                <th>Premium %</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                sorted && rowData.length !== 0 ?
                                    rowData.map((item, ind) => {
                                        if (ind < 10) {
                                            return (
                                                <tr>
                                                    <td>{item.userName}</td>
                                                    <td>{item.groupCd}</td>
                                                    <td>{item.agencyCd}</td>
                                                    <td>{item.email}</td>
                                                    <td>{Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(item.userPrem)}</td>
                                                    <td>{(item.userPrem * 100 / totalPremium).toFixed(2)}</td>
                                                </tr>
                                            )
                                        }
                                    })
                                    :
                                    ''
                            }
                        </tbody>
                    </table>
                    <br />
                    <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
                </div> :
                <div class="tabContent extraWidth" style={{ display: "block", color: '#CF0918' }}>
                    <p>No rows to display</p>
                </div>
            )
            }
        </div>
    )
}
export default PerformenceStaticsTableByPremium;