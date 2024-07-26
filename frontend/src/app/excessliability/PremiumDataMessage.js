import React, { useEffect, useState } from 'react';
import Layout from '../../layout/Layout'
import { useParams } from 'react-router'
import { useLocation } from 'react-router-dom';
import { useHistory } from 'react-router-dom';
import ExcessService from "../services/ExcessService";
import ComAutoIndicationService from '../services/ComAutoIndicationService';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import LoaderModal from '../../components/modals/popupmodal/LoaderModal';
import ComAutoService from '../services/ComAutoService';
import CryptoJS from "crypto-js";
import API_Headers from '../../API_Headers';
import useLoader from '../../context/loader'
import PolicyService from '../services/PolicyService';

const PremiumDataMessage = ({ register, errors }) => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    const { setLoader } = useLoader()
    const { premiumData } = useParams();
    const { state } = useLocation();

    var responseJson = state.responseJson;
    var error = state.detail.error && state.detail.error.errorDesc;
    const history = useHistory();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    var policyType = responseJson.application.policyInfo.policyType
    const [headers, setHeaders] = useState([])
    const [quoteNumber, setQuoteNumber] = useState("");

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

    const handlePushQuote = async () => {
        //setLoaderData({ show: true })
        setLoader(true)
        var responseData = "";
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(responseJson),
            secretPass
        ).toString();
        try {
            if (policyType === 'Commercial Auto') {
                // setLoaderData({ show: true })
                responseData = await ComAutoService.sendComAutoData(data1, headers);
                setLoader(false)
                if (responseData.data !== null && responseData.data !== undefined && responseData.data !== "") {
                    setQuoteNumber(responseData.data)
                }
                // history.push({ pathname: "/commercialAutoQuoteStatus", state: responseData.data });
            } else if (policyType === 'Commercial Property') {
                responseData = await ExcessService.sendExcessData(data1, headers);

                setLoader(false)
                if (responseData.data !== null && responseData.data !== undefined && responseData.data !== "") {
                    setQuoteNumber(responseData.data)
                }
            } else {
                responseData = await ExcessService.sendExcessData(data1, headers);
                setLoader(false)
                if (responseData.data !== null && responseData.data !== undefined && responseData.data !== "") {
                    setQuoteNumber(responseData.data)
                }
            }
        }
        catch (error) {
            setLoader(false)
            if (error == 'Error: Network Error') {
                useHistory.push("/SystemMaintenance");
            }
            else {
                alert(error.response.data.error);
            }
        }
    }

    // useEffect(async () => {
    //     if (headers.length !== 0) {
    //         var responseData = "";
    //         const data1 = CryptoJS.AES.encrypt(
    //             JSON.stringify(responseJson),
    //             secretPass
    //         ).toString();
    //         try {
    //             if (state.detail.workFlow == "DB") {
    //                 if (policyType === 'Commercial Auto') {
    //                     responseData = await ComAutoService.sendComAutoData(data1, headers);
    //                     if (responseData.data !== null && responseData.data !== undefined && responseData.data !== "") {
    //                         setQuoteNumber(responseData.data)
    //                     }
    //                     // history.push({ pathname: "/commercialAutoQuoteStatus", state: responseData.data });
    //                 } else {
    //                     responseData = await ExcessService.sendExcessData(data1, headers);
    //                 }
    //             }
    //             // setLoaderData({ show: false })
    //             // setErrorModal({ show: true, title: "Quote Success Message", body: "Generated Quote Number : " + responseData.data })
    //         }
    //         catch (error) {
    //             if (error == 'Error: Network Error') {
    //                 useHistory.push("/SystemMaintenance");
    //             }
    //             else {
    //                 alert(error.response.data.error);
    //             }
    //         }
    //     }
    // }, [headers])

    
    const handleCreatePolicy = async () => {
        const transactionIdGW = state.detail.locator
        const premiumAmt = state.detail.fullTermAmount
        state.responseJson.application.policyPremiumInfo["fullTermAmount"] = state.detail.fullTermAmount
        state.responseJson.application.policyPremiumInfo["locator"] = state.detail.locator
        const data = state.responseJson
        
        var responseData = "";
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(data),
            secretPass
        ).toString();
        try {
            if (policyType === 'Commercial Auto') {
                setLoader(true)
                responseData = await PolicyService.createPolicy(data1, headers);
                if (responseData.data !== null && responseData.data !== undefined && responseData.data !== "") {
                    // setQuoteNumber(responseData.data)
                    setErrorModal({ show: true, title: "Policy Success Message", body: responseData.data })
                    setLoader(false)
                } else {
                    setLoader(false)
                    setErrorModal({ show: true, title: "Couldn't create policy", body: "Some Error!" })
                }
            } else if (policyType === 'Commercial Property') {
                setLoader(true)
                responseData = await PolicyService.createPolicy(data1, headers);
                if (responseData.data !== null && responseData.data !== undefined && responseData.data !== "") {
                    // setQuoteNumber(responseData.data)
                    setErrorModal({ show: true, title: "Policy Success Message", body: responseData.data })
                    setLoader(false)
                } else {
                    setLoader(false)
                    setErrorModal({ show: true, title: "Couldn't create policy", body: "Some Error!" })
                }
            }
            else {
                setLoader(true)
                // responseData = await ExcessService.sendExcessData(data1, headers);
                responseData = await PolicyService.createPolicy(data1, headers);
                if (responseData.data !== null && responseData.data !== undefined && responseData.data !== "") {
                    // setQuoteNumber(responseData.data)
                    setErrorModal({ show: true, title: "Policy Success Message", body: responseData.data })
                    setLoader(false)
                } else {
                    setLoader(false)
                    setErrorModal({ show: true, title: "Couldn't create policy", body: "Some Error!" })
                }
            }
        }
        catch (error) {
            setLoader(false)
            if (error == 'Error: Network Error') {
                useHistory.push("/SystemMaintenance");
            }
            else {
                console.log(error)
                setErrorModal({ show: true, title: "Policy Error Message", body: error.response.data.error })
                // alert(error.response.data.error);
            }
        }
    }

    return (
        <Layout TabName='PREMIUM DETAILS' BreadCrum={['My Dashboard', 'Premium Details']}>
            <div class="policy_wrap">
                <div class="coverContainer">
                    <div class="policynumber"><span>PREMIUM DETAILS </span></div>
                    {policyType === 'Commercial Auto' && (
                        <>
                            <div class="shortdetails">
                                {
                                    state.detail.workFlow === "SOC" ?
                                        state.detail.message === "Endorsement issued successfully" ?
                                            <div>
                                                <span><b>Endorsement has been accepted. Your endorsement number is : {state.detail.locator}</b></span>
                                                <br></br>
                                            </div>
                                            :
                                            <div>
                                                <ul>
                                                    <li><span>Total Premium:  </span>{Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(state.detail.fullTermAmount)}</li>
                                                </ul>
                                                <span><b>Policy has been successfully created. Your Policy number is : {state.detail.locator}</b></span>
                                                <br></br>
                                            </div>
                                        :
                                        <ul>
                                            <li><span>Total Premium:  </span>{Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(state.detail.fullTermAmount)}</li>
                                        </ul>
                                }
                                <div style={{ color: "#000080" }}>
                                    {
                                        state.detail.workFlow === "GW" ?
                                            <div>
                                                <br></br>
                                                <ul>
                                                    <span><b>Quote has been successfully created. Your Quote number is : {state.detail.locator} </b></span>
                                                </ul>
                                                <br></br>
                                            </div>
                                            :
                                            state.detail.workFlow === "DB" ?
                                                quoteNumber !== "" ?
                                                    <div>
                                                        <br></br>
                                                        <span><b>Quote has been successfully created. Your Quote number is : {quoteNumber}</b></span>
                                                        <br></br>
                                                    </div>
                                                    :
                                                    <></>
                                                :
                                                <></>
                                    }
                                    {state.detail.workFlow === "GW" ?
                                        state.eligibility ?
                                            <span><b>Based on the limits chosen, you are eligible for policy issuance. Please proceed if you would like to create the policy.</b></span>
                                            :
                                            <span><b>Based on the limits chosen, you are NOT eligible for policy issuance. An underwriter will contact you for next steps.</b></span>
                                        :
                                        quoteNumber !== "" ?
                                            state.eligibility ?
                                                <span><b>Based on the limits chosen, you are eligible for policy issuance. Please proceed if you would like to create the policy.</b></span>
                                                :
                                                <span><b>Based on the limits chosen, you are NOT eligible for policy issuance. An underwriter will contact you for next steps.</b></span>
                                            :
                                            <></>
                                    }
                                </div>
                            </div>
                            <div>
                                {
                                    state.detail.workFlow === "GW" ?
                                        state.eligibility ?
                                            <>
                                                <button class="btn blue" onClick={history.goBack}>Back</button>
                                                &ensp; &ensp;
                                                <button class="btn blue" onClick={() => { handleCreatePolicy() }}>Create Policy</button>
                                            </>
                                            :
                                            <>
                                                <button class="btn blue" onClick={history.goBack}>Back</button>
                                                &ensp; &ensp;
                                                <button class="btn blue" onClick={() => { history.push('/dashboard') }}>Go To Home Page</button>
                                            </>
                                        :
                                        state.detail.workFlow === "DB" ?
                                            quoteNumber !== "" ?
                                                state.eligibility ?
                                                    <>
                                                        <button class="btn blue" onClick={history.goBack}>Back</button>
                                                        &ensp; &ensp;
                                                        <button class="btn blue" onClick={() => { handleCreatePolicy() }}>Create Policy</button>
                                                    </>
                                                    :
                                                    <>
                                                        <button class="btn blue" onClick={history.goBack}>Back</button>
                                                        &ensp; &ensp;
                                                        <button class="btn blue" onClick={() => { history.push('/dashboard') }}>Go To Home Page</button>
                                                    </>
                                                :
                                                <>
                                                    <button class="btn blue" onClick={history.goBack}>Back</button>
                                                    &ensp; &ensp;
                                                    <button class="btn blue" onClick={() => { handlePushQuote() }}>Submit Quote</button>
                                                </>
                                            :
                                            <>
                                                <button class="btn blue" onClick={history.goBack}>Back</button>
                                                &ensp; &ensp;
                                                <button class="btn blue" onClick={() => { history.push('/dashboard') }}>Go To Home Page</button>
                                            </>
                                }

                            </div>
                        </>
                    )}
                    {policyType === 'Commercial Property' && (
                        <>
                            <div class="shortdetails">
                                <ul>
                                    <li><span>Total Premium:  </span>{Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(state.detail.fullTermAmount)}</li>
                                </ul>
                                <div style={{ color: "#000080" }}>
                                    {

                                        quoteNumber !== "" ?
                                            <div>
                                                <br></br>
                                                <span><b>Quote has been successfully created. Your Quote number is : {quoteNumber}</b></span>
                                                <br></br>
                                            </div>
                                            :
                                            <></>

                                    }

                                </div>
                            </div>
                            <div>
                                {

                                    quoteNumber !== "" ?
                                        <>
                                            <button class="btn blue" onClick={history.goBack}>Back</button>
                                            &ensp; &ensp;
                                            <button class="btn blue" onClick={() => { history.push('/dashboard') }}>Go To Home Page</button>
                                        </>
                                        :
                                        <>
                                            <button class="btn blue" onClick={history.goBack}>Back</button>
                                            &ensp; &ensp;
                                            <button class="btn blue" onClick={() => { handlePushQuote() }}>Submit Quote</button>
                                        </>

                                }

                            </div>
                        </>
                    )}
                    {policyType === 'Commercial Excess Liability' && (
                        <>
                            <div class="shortdetails">
                                <ul>
                                    <li><span>Total Premium:  </span>{Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(state.detail.fullTermAmount)}</li>
                                </ul>
                                <div style={{ color: "#000080" }}>
                                    {

                                        quoteNumber !== "" ?
                                            <div>
                                                <br></br>
                                                <span><b>Quote has been successfully created. Your Quote number is : {quoteNumber}</b></span>
                                                <br></br>
                                            </div>
                                            :
                                            <></>

                                    }

                                </div>
                            </div>
                            <div>
                                {

                                    quoteNumber !== "" ?
                                        <>
                                            <button class="btn blue" onClick={history.goBack}>Back</button>
                                            &ensp; &ensp;
                                            <button class="btn blue" onClick={() => { history.push('/dashboard') }}>Go To Home Page</button>
                                        </>
                                        :
                                        <>
                                            <button class="btn blue" onClick={history.goBack}>Back</button>
                                            &ensp; &ensp;
                                            <button class="btn blue" onClick={() => { handlePushQuote() }}>Submit Quote</button>
                                        </>

                                }

                            </div>
                        </>
                    )}
                    <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
                    {/* <LoaderModal showModal={loaderData.show} setShowModal={setLoaderData} /> */}
                </div>
            </div>
        </Layout>
    );
};

export default PremiumDataMessage;
