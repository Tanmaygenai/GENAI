import React from 'react'
import { useParams } from 'react-router'
import { useState } from 'react';
import { useEffect } from 'react';
import Claim from './Claim';
import PolicyService from '../services/PolicyService';
import ClaimService from '../services/ClaimService';
import { useHistory, useLocation } from 'react-router-dom';
import PolicyDocDownloadService from '../services/PolicyDocDownloadService'
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { saveAs } from 'file-saver';
import CryptoJS from "crypto-js";
import API_Headers from '../../API_Headers';
import useLoader from "../../context/loader"

const Policy = ({ openModal, modalState }) => {
    const {setLoader} = useLoader();
    const { id } = useParams();
    const {state} = useLocation()
    const [error, setError] = useState(null);
    const [user, setUser] = useState({ policyListItems: [{}] });
    const [basicPolicy, setBasicPolicy] = useState([]);
    const [systemId, setSystemId] = useState([]);
    const [insured, setInsured] = useState([]);
    const [addresses, setAddresses] = useState([]);
    const [businessAddress, setBusinessAddress] = useState([]);
    const [mailingAddress, setMailingAddress] = useState([]);
    const [showClaims, setShowClaims] = useState(false);
    const history = useHistory();
    const [claimDetails, setClaimsDetails] = useState([]);
    const [documentData, setUserDocData] = useState([]);
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const policySearchURL = process.env.REACT_APP_PolicyDocDownload_URL;
    const [showDocument, setShowDocument] = useState(false);
    const [dispPolicy, setDispPolicy] = useState(false);
    const cabinateFileDownloadURL = process.env.REACT_APP_Policy_FileDownload_URL;
    const secretPass = process.env.REACT_APP_Secret_Key;
    const [headers, setHeaders] = useState([])

    useEffect(() => {
        setLoader(true)
        API_Headers().then((val) => setHeaders(val))
    }, [])
    useEffect(() => {
        const loggedInUser = sessionStorage.getItem("userName");
        if (!loggedInUser) {
            history.push("/");
        }
    }, []);

    useEffect(() => {
        if(headers.length !== 0){
        loadUser();
        }
    }, [headers])

    const viewClaimDetails = async () => {
        try {
            const data1 = CryptoJS.AES.encrypt(
                JSON.stringify(state),
                secretPass
              ).toString();
            const claimRespData = await ClaimService.getClaimsFromDB(data1,headers);
            if(claimRespData.data.length >= 1){
                setShowClaims("Yes");
                setClaimsDetails(claimRespData.data)
            } else {
                setShowClaims("No");
                setErrorModal({ show: true, title: "Error", body: "No claim is associated with the policy number: " + state })
            }
        } catch (error) {
            setLoader(false)
            setErrorModal({ show: true, title: "Error", body: "" + error.response.data.error })
            setShowClaims("No");
        }
    }


    const viewPolicyDetails = async () => {
        try {
            const data1 = CryptoJS.AES.encrypt(
                JSON.stringify(state),
                secretPass
              ).toString();
            const userDocData = await PolicyDocDownloadService.getPolicyDocById(data1,headers);
            setUserDocData(userDocData.data);

            if (userDocData.data != "") {
                setErrorModal({ show: true, title: "Policy Documents", body: "Policy documents ready for download. Kindly click below Link to view the Policy Documents." })
            }
            else {
                setErrorModal({ show: true, title: "Error", body: "Policy Document Not Found for policy number : " + state })
            }
        } catch (error) {
            setErrorModal({ show: true, title: "Error", body: "Policy Document Not Found." })
        }
    }

    const downloadPolicy = async (name, id) => {
        const docsData=PolicyService.getPolicyFileDownload(id, headers);
        setTimeout(
            displayPDF(), 5000
        )
        function displayPDF() {
            var blob = new Blob([docsData.data], { type: "application/octet-stream" });
            saveAs(blob, name);
        }

    }
    const loadUser = async () => {
        try {
            const data1 = CryptoJS.AES.encrypt(
                JSON.stringify(state),
                secretPass
            ).toString();
            const policyData = await PolicyService.getPolicyFromDB(data1, headers);
            setLoader(false)
            setUser(policyData.data);
            if(Object.keys(user).length > 0) {
                setDispPolicy(true)
            }
        } catch (error) {
            setLoader(false)
            setError(error);
        }
    };

    let itemRows = [];

    if (documentData.length > 0) {
        documentData.map((item, ind) => {
            let row = (
                <tr key={Math.random()}>
                    <td>{ind + 1}</td>
                    <td>{item.name}</td>
                    <td key={Math.random()}><button type="button" onClick={() => downloadPolicy(item.name, item.id)}>Download File</button></td>
                </tr>
            );
            itemRows.push(row);
        })
    }

    return (

        <>

            {(dispPolicy === false) ?
                        <div class="policy_wrap">
                            {
                                <div class="alert alert-danger" role="alert">
                                    Entered Policy Number {state} not found
                                </div>
                            }
                        </div>

                        :
                        <div>
                            <div class="policy_wrap">
                                <div class="coverContainer">
                                    <div class="policynumber"><span>Policy Number: <strong>{user.policyNumber}</strong></span></div>
                                    <div class="shortdetails">
                                        <ul>
                                            <li><span>Name Insured:</span><p>{user.insuredName}</p></li>
                                            {/* <li><span>Product:</span> <p></p></li> */}
                                            <li><span>Effective Date:</span>{user.effectiveDate}</li>
                                            <li><span>Term:</span> <p>{user.term}</p></li>
                                            <li><span>Expiration Date:</span> <p>{user.expirationDate}</p></li>
                                            <li><span>Mailing Address:</span> <p> {user.mailingAddress}</p></li>
                                            <li><span>Business Address:</span> <p>{user.businessAddress}</p></li>
                                        </ul>
                                    </div>
                                    <div>
                                        <button class="btn blue" onClick={viewPolicyDetails}>View Policy</button>
                                    </div>
                                    <br />
                                    {(documentData.length > 0)
                                        ? <div class="grid1">
                                            <table class="table table-hover">
                                                <thead class="thead-light">
                                                    <tr class="table-primary">
                                                        <th scope="col">#</th>
                                                        <th scope="col">File Name</th>
                                                        <th scope="col">Download URL</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    {itemRows}
                                                </tbody>
                                            </table>
                                        </div>
                                        : null
                                    }
                                </div>
                            </div>
                            <div class="text-center">
                                <button class="btn blue" onClick={viewClaimDetails}>View Claim Details</button>
                            </div>
                            {showClaims
                                ? <div class="claim_details_wrap">
                                    <div class="coverContainer">
                                        {claimDetails.map((claimData, index) => {
                                            return (
                                                <Claim key={index} claimData={claimData} openModal={openModal} modalState={modalState} />
                                            );
                                        })}
                                    </div>

                                </div>
                                : null
                            }
                            <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
                        </div>

            }
        </>

    )

}
export default Policy;