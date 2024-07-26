import React, { useEffect, useState } from "react"
import { useForm } from "react-hook-form";
import { useLocation } from "react-router-dom";

import FormControl from "../../../components/formcontrol/FormControl";
import ErrorMessageModal from "../../../components/modals/popupmodal/ErrorMessageModal";
import Layout from "../../../layout/Layout";
import PolicyService from "../../services/PolicyService";

import PolicyDocDownloadService from '../../services/PolicyDocDownloadService';
import Claim from '../../policysearch/Claim';
import CryptoJS from "crypto-js";
import API_Headers from "../../../API_Headers";
const SearchPolicy = () => {
    const { state } = useLocation();
    const secretPass = process.env.REACT_APP_Secret_Key;
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [response, setResponse] = useState([])
    const [data, setData] = useState("")
    const [policyDetails, setPolicysDetails] = useState([]);
    const [showClaims, setShowClaims] = useState(false);

    useEffect(() => {
        if(state.policyNumber !== null){
        API_Headers().then((val) => {
            getDataForPolicy(state.policyNumber,val)
        })
    }
    }, [])
    async function getDataForPolicy(policyId, headers) {
        try {
            const data1 = CryptoJS.AES.encrypt(
                JSON.stringify(policyId),
                secretPass
            ).toString();
            const responseData = await PolicyService.getPolicyFromDB(data1, headers);
            if(responseData.data){
                setResponse(responseData.data)
            }else{
                setErrorModal({ show: true, title: "Error", body: "Policy Document Not Found." })
            }
        }
        catch (error) {
            setErrorModal({ show: true, title: "Error", body: "Policy Document Not Found." })
        }
    }
    
    return (
        <Layout TabName='Search Policy' BreadCrum={['My Dashboard', 'Search Policy']}>
            <div className="coverContainer">
                <div class="policy_wrap" >
                    <div class="coverContainer">
                        {
                            response.length !== 0 && state.policyNumber !== null?
                            <>
                                <div class="policynumber"><span>Policy Number: <strong>{response.quoteNumber}</strong></span></div>
                                <div class="shortdetails">
                            <ul>
                                <li><span>Name Insured:</span><p>{response.insuredName}</p></li>
                                <li><span>Effective Date:</span>{response.effectiveDate}</li>
                                {/* <li><span>Term:</span> <p>{response.term}</p></li> */}
                                <li><span>Expiration Date:</span> <p>{response.expirationDate}</p></li>
                                {/* <li><span>Mailing Address:</span> <p> {response.mailingAddress}</p></li> */}
                                <li><span>Business Address:</span> <p>{response.busAddress}</p></li>
                            </ul>
                        </div>
                            </>
                            :
                            []
                        }
                        <br />
                        {/* <div class="text-left">
                            <button class="btn blue" onClick={()=>viewPolicyDetails}>View Policy</button>
                        </div> */}
                    </div>
                </div>

                <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
            </div>
        </Layout>
    )
}
export default SearchPolicy;
