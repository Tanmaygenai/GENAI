import { useState } from 'react';
import { useEffect } from 'react';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import CognitoUserService from '../services/CognitoUserService';
import API_Headers from '../../API_Headers';
import useLoader from "../../context/loader"

const PerformenceStaticsTableByQuote = ({ responseJson, quoteCount }) => {
    const {setLoader} = useLoader()
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
        if (responseJson.data.length !== 0 && quoteCount !== null) {
            var ir = []
            var ul = []
            if (responseJson.data.length !== 0 && quoteCount !== null && userCognitoList.length !== 0) {
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
                                    "userQuotes": 1,
                                    // "userQuotesPer": (1 / quoteCount) * 100
                                })
                            }
                            } else {
                                if (res.submissionStatus === "Yes") {
                                    ir[ul.indexOf(user.userName)].userQuotes += 1
                                }
                                // ir[ul.indexOf(user.userName)].userQuotesPer = (ir[ul.indexOf(user.userName)].userQuotes / quoteCount) * 100
                            }
                        }
                    })
                })
                setRowData(ir)
                setLoader(false)
            }
        }
    }, [responseJson, quoteCount, userCognitoList])

    useEffect(() => {
        if (rowData.length !== 0) {
            rowData.sort((a, b) => {
                return b.userQuotes - a.userQuotes;
            });
        }
        setRowData(rowData)
        setSorted(true)
    }, [rowData])

    return (
        <div>
            {(sorted && rowData.length !== 0 ?
                        <div class="tabContent extraWidth" style={{ display: "block" }}>
                            <table>
                                <thead>
                                    <tr>
                                        <th>User Id</th>
                                        <th>Group Id</th>
                                        <th>Agency Id</th>
                                        <th>Email Id</th>
                                        <th>Total Quotes</th>
                                        <th>Quotes %</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    {
                                        sorted && rowData.length !== 0 ?
                                            rowData.map((item, ind) => {
                                                if (ind < 10) {
                                                    return <tr>
                                                        <td>{item.userName}</td>
                                                        <td>{item.groupCd}</td>
                                                        <td>{item.agencyCd}</td>
                                                        <td>{item.email}</td>
                                                        <td>{item.userQuotes}</td>
                                                        <td>{((item.userQuotes / quoteCount) * 100).toFixed(2)}</td>
                                                    </tr>
                                                }
                                            })
                                            :
                                            ''
                                    }
                                </tbody>
                            </table>
                            <br />
                            <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
                        </div>
                        :
                        <div class="tabContent extraWidth" style={{ display: "block", color: '#CF0918' }}><p>No rows to display</p></div>
                    )
            }
        </div>

    )
}
export default PerformenceStaticsTableByQuote;