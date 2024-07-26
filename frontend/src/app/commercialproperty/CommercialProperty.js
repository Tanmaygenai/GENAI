import React, { useState } from 'react';
import Layout from '../../layout/Layout';
import Stepper from '../stepper/Stepper';
import { useEffect } from 'react';
import { useHistory, useLocation } from 'react-router-dom';
import { CommercialPropertyResopnseJson } from '../../utility/resopnseJsonComProperty';
import ComPropertyIndicationService from '../services/ComPropertyIndicationService';
import CryptoJS from "crypto-js";
import {StateName} from '../../dummydata/StateList'
import API_Headers from '../../API_Headers';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import useLoader from "../../context/loader"

const CommercialProperty = () => {
    const {setLoader} = useLoader()
    const secretPass = process.env.REACT_APP_Secret_Key;
    const steps = ['Quote and Insured Information', 'Business Information','Risk Information','Add Location' ,'Add Building'];
    const history = useHistory();
    const { state } = useLocation();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    var{quoteId} = useLocation();
    if(quoteId!=null || quoteId!= undefined){
        sessionStorage.setItem("quoteNumber",quoteId)
    }
    else{
        if (typeof quoteId === 'undefined')
        { 
            if(sessionStorage.getItem("quoteNumber")!=null) 
            quoteId=sessionStorage.getItem("quoteNumber")
        }
        else{
            quoteId=0 
        }
        
    }
    const [headers, setHeaders] = useState([])

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
    function getStateCode(stateName) {
        const state = StateName.find(obj => obj.name == stateName);
        if (state) {
          return state.code;
        } else {
          return null;
        }
      }
    const onSubmit = async (data) => {
        setLoader(true)
        data = {...data, 
            "busState":getStateCode(data.busState),
            "primaryState":getStateCode(data.primaryState) 
        }
        try {
            sessionStorage.removeItem("quoteNumber")
            const responseJson = CommercialPropertyResopnseJson(data, state, data?.temp_id)
            const data1 = CryptoJS.AES.encrypt(
                JSON.stringify(responseJson),
                secretPass
              ).toString();
             const premiumData = await ComPropertyIndicationService.getPremiumDetails(
                 data1,
                 headers
             );
             setLoader(false)
             if(premiumData.data.message === "" || premiumData.data.message === null || premiumData.data.message === undefined){
                history.push({
                    pathname: "/premiumDataMsg",
                    state: { detail: premiumData.data, responseJson: responseJson }
                });
             } else {
                setErrorModal({ show: true, title: "Error", body: premiumData.data.message })
             }
        } catch (error) {
            setLoader(false)
            if (error == "Error: Network Error") {
                history.push("/SystemMaintenance");
            } else {
                alert(error)
            }
        }
    };
    useEffect(() => {
        const loggedInUser = sessionStorage.getItem("userName");
        if (!loggedInUser) {
            history.push("/");
        }

    }, [])
    return (
        <Layout TabName='Commercial Property' BreadCrum={['My Dashboard', 'Quick Quote', 'Commercial Property']} sidebarQQSopen={true}>
            <Stepper state={state} steps={steps} TabName='Commercial Property' onSubmit={onSubmit} />
            <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
        </Layout>

    )
};

export default CommercialProperty;
