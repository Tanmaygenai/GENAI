import React, { useState } from 'react';
import Layout from '../../layout/Layout';
import { CommercialAutoResopnseJson } from '../../utility/resopnseJsonComAuto';
import Stepper from '../stepper/Stepper';
import { useEffect } from 'react';
import { useHistory, useLocation } from 'react-router-dom';
import ComAutoIndicationService from '../services/ComAutoIndicationService';
import CryptoJS from "crypto-js";
import { StateName } from '../../dummydata/StateList'
import API_Headers from '../../API_Headers';
import Papa from 'papaparse';
import dropdown_val_liability from '../../dummydata/LiabilityLimit.csv';
import dropdown_val_MedicalPayments from '../../dummydata/MedicalPayments.csv';
import dropdown_val_CollDeductible from '../../dummydata/CollDeductible.csv';
import dropdown_val_CompDeductible from '../../dummydata/CompDeductible.csv';
import VehicleCodes from '../../dummydata/VehicleCodes.csv';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import useLoader from '../../context/loader';
import { CommercialAutoModalStateResopnseJson } from "../../utility/resopnseModalStateComAuto";
const CommercialAuto = () => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    // const steps = ['Effective Date & Location', 'Product & Classification', 'Risk Information', 'Coverage', 'Vehicles', 'Drivers'];
    const steps = ['Quote and Insured Information', 'Business Information', 'Risk Information', 'Coverage', 'Vehicles', 'Drivers'];
    // const steps = ['Insured Information', 'Business Information', 'Risk Information', 'Coverage', 'Vehicles', 'Drivers'];
    const [vehicleCSV, setVehicleCSV] = useState([])
    const history = useHistory();
    const { state } = useLocation();
    //const { modalState } = useLocation();
    var { quoteId } = useLocation();
    const { setLoader } = useLoader()
    var [ policyNumber, setPolicyNumber ] = useState();
    let [modalState, setModalState] = useState([])

    useEffect(() => {
        if (state!== undefined && state.policyNumber!== null && state.policyNumber!== undefined) {
        const responseModalStateJson = CommercialAutoModalStateResopnseJson(state)
           setModalState(responseModalStateJson.modalState);
           setPolicyNumber(state.policyNumber);
        }        
    }, []);

    if (quoteId != null || quoteId != undefined) {
        sessionStorage.setItem("quoteNumber", quoteId)
    }
    else {
        if (typeof quoteId === 'undefined') {
            if (sessionStorage.getItem("quoteNumber") != null)
                quoteId = sessionStorage.getItem("quoteNumber")
        }
        else {
            quoteId = 0
        }
    }

    useEffect(() => {
        var commonConfig = { delimiter: "," };
        var vehicleCSV
        Papa.parse(
            VehicleCodes,
            {
                ...commonConfig,
                header: true,
                download: true,
                complete: (result) => {
                    setVehicleCSV(result.data)
                }
            }
        );
    }, [])

    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [headers, setHeaders] = useState([])
    const [dropdownLiabilityVal, setDropdownLiabilityVal] = useState([]);
    const [dropdownMedicalPayVal, setDropdownMedicalPayVal] = useState([]);
    const [dropdownCollDeductibleVal, setDropdownCollDeductibleVal] = useState([]);
    const [dropdownCompDeductibleVal, setDropdownCompDeductibleVal] = useState([]);
    var commonConfig = { delimiter: "," };

    useEffect(() => {
        Papa.parse(
            dropdown_val_liability,
            {
                ...commonConfig,
                header: true,
                download: true,
                complete: (result) => {
                    var libVal = []
                    if (result.data.length > 0) {
                        result.data.map((res) => {
                            if (res.Values !== "-select-" && res.Values !== "" && res.Values !== undefined) {
                                libVal.push(res.Values)
                                setDropdownLiabilityVal(libVal)
                            }
                        })
                    }
                }
            }
        );
    }, [])

    useEffect(() => {
        Papa.parse(
            dropdown_val_MedicalPayments,
            {
                ...commonConfig,
                header: true,
                download: true,
                complete: (result) => {
                    var medicVal = []
                    if (result.data.length > 0) {
                        result.data.map((res) => {
                            if (res.Values !== "-select-" && res.Values !== "" && res.Values !== undefined) {
                                medicVal.push(res.Values)
                                setDropdownMedicalPayVal(medicVal)
                            }
                        })
                    }
                }
            }
        );
    }, [])

    useEffect(() => {
        Papa.parse(
            dropdown_val_CollDeductible,
            {
                ...commonConfig,
                header: true,
                download: true,
                complete: (result) => {
                    var collVal = []
                    if (result.data.length > 0) {
                        result.data.map((res) => {
                            if (res.Values !== "-select-" && res.Values !== "" && res.Values !== undefined) {
                                collVal.push(res.Values)
                                setDropdownCollDeductibleVal(collVal)
                            }
                        })
                    }
                }
            }
        );
    }, [])

    useEffect(() => {
        Papa.parse(
            dropdown_val_CompDeductible,
            {
                ...commonConfig,
                header: true,
                download: true,
                complete: (result) => {
                    var comVal = []
                    if (result.data.length > 0) {
                        result.data.map((res) => {
                            if (res.Values !== "-select-" && res.Values !== "" && res.Values !== undefined) {
                                comVal.push(res.Values)
                                setDropdownCompDeductibleVal(comVal)
                            }
                        })
                    }
                }
            }
        );
    }, [])

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
        data = {
            ...data,
            "busState": getStateCode(data.busState),
            "AddedVehicles": data.AddedVehicles.map((addedVehicle) => ({
                ...addedVehicle,
                "RegisteredState": getStateCode(addedVehicle.RegisteredState) ? getStateCode(addedVehicle.RegisteredState) : addedVehicle.RegisteredState 
            }))
        }
        var territoryCd;
        if (data.busCity === "San Mateo" && data.busState === "CA" && data.busPostalCode === "99999") {
            territoryCd = "AAA"
        } else if (data.busCity === "San Carlos" || data.busCity === "San Mateo" && data.busPostalCode === "90000" || data.busPostalCode === "90004") {
            territoryCd = "BBB"
        } else if (data.busCity === "Foster City" && data.busState === "CA" && data.busPostalCode === "94404") {
            territoryCd = "CCC"
        } else if (data.busCity === "Freedom City" && data.busState === "CO" && data.busPostalCode === "90002") {
            territoryCd = "DDD"
        } else if (data.busCity === "Foster City" && data.busState === "CA" && data.busPostalCode === "94405") {
            territoryCd = "DD1"
        }
        else {
            territoryCd = ""
        }
        data = { ...data, "territoryCd": territoryCd }
        try {
            data.vehicleCSVData = vehicleCSV
            data.organization = sessionStorage.getItem("organization") !== undefined ? sessionStorage.getItem("organization") : ""
            data.producerCode = sessionStorage.getItem("producerCode") !== undefined ? sessionStorage.getItem("producerCode") : ""
            sessionStorage.removeItem("quoteNumber")
            const responseJson = CommercialAutoResopnseJson(data, state, quoteId)
            const data1 = CryptoJS.AES.encrypt(
                JSON.stringify(responseJson),
                secretPass
            ).toString();
            var premiumData = "";
            if(policyNumber=== undefined){
                premiumData = await ComAutoIndicationService.getPremiumDetails(
                data1,
                headers
            );
          }else if(policyNumber!== undefined){
                premiumData = await ComAutoIndicationService.getPremiumDetailsEndorse(policyNumber,
                data1,
                headers
            );
           }
            setLoader(false)
            const addedVehicles = data.AddedVehicles;

            let allVehiclesEligible = true;

            for (const vehicle of addedVehicles) {
                if (
                    dropdownCompDeductibleVal.length > 0 &&
                    !dropdownCompDeductibleVal.includes(vehicle.ComprehensiveDeductible)
                ) {
                    allVehiclesEligible = false;
                    break;
                }

                if (
                    dropdownCollDeductibleVal.length > 0 &&
                    !dropdownCollDeductibleVal.includes(vehicle.CollisionDeductible)
                ) {
                    allVehiclesEligible = false;
                    break;
                }
            }
            if (premiumData.data.message === "" || premiumData.data.message === null || premiumData.data.message === undefined) {
                if (
                    dropdownLiabilityVal.length > 0 &&
                    dropdownLiabilityVal.includes(data.LiabilityLimit) &&
                    dropdownMedicalPayVal.length > 0 &&
                    dropdownMedicalPayVal.includes(data.MedicalPayments) &&
                    allVehiclesEligible
                ) {
                    history.push({
                        pathname: "/premiumDataMsg",
                        state: { detail: premiumData.data, responseJson: responseJson, eligibility: true }
                    });
                } else {
                    history.push({
                        pathname: "/premiumDataMsg",
                        state: { detail: premiumData.data, responseJson: responseJson, eligibility: false }
                    });
                }
            } else if(premiumData.data.message === "Endorsement issued successfully") {
                history.push({
                    pathname: "/premiumDataMsg",
                    state: { detail: premiumData.data, responseJson: responseJson, eligibility: true }
                });
            }
            else {
                setLoader(false)
                setErrorModal({ show: true, title: "Error", body: premiumData.data.message })
                //alert(premiumData.data.message)
            }
        } catch (error) {
            setLoader(false)
            if (error == "Error: Network Error") {
                history.push("/SystemMaintenance");
            } else {
                setErrorModal({ show: true, title: "Error", body: error.toString() })
                // alert(error)
            }
        }

    }
    useEffect(() => {
        const loggedInUser = sessionStorage.getItem("userName");
        if (!loggedInUser) {
            history.push("/");
        }

    }, [])
    return (
        <Layout TabName='Commercial Auto' BreadCrum={['My Dashboard', 'Quick Quote', 'Commercial Auto']} sidebarQQSopen={true}>
            <Stepper state={state} steps={steps} TabName='Commercial Auto' onSubmit={onSubmit} modalState={modalState} />
            <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
        </Layout>

    )
};

export default CommercialAuto;
