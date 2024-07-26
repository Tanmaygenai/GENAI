import React, { useState } from 'react'
import { useForm } from "react-hook-form"
import { useHistory, useLocation } from 'react-router-dom';
import step1 from '../../assets/img/icons/step_c1.svg';
import step2 from '../../assets/img/icons/step_c2.svg';
import step3 from '../../assets/img/icons/step_c3.svg';
import step4 from '../../assets/img/icons/step_c4.svg';
import step5 from '../../assets/img/icons/step_c5.svg';
import step6 from '../../assets/img/icons/step_c6.svg';
import step7 from '../../assets/img/icons/step_c7.svg';
import step8 from '../../assets/img/icons/step_c8.svg';
import step9 from '../../assets/img/icons/check.svg';
import step10 from '../../assets/img/icons/step_c1.svg';
import step11 from '../../assets/img/icons/step_c1.svg';
import step12 from '../../assets/img/icons/step_c1.svg';
import BusinessInfo from '../../assets/img/icons/BusinessInfo.svg'
import AddBuilding from '../../assets/img/icons/AddBuilding.svg'
import AddLocation from '../../assets/img/icons/AddLocation.svg'
import EligibilityCheck from '../../assets/img/icons/EligibilityCheck.svg'
import EffectiveDateAndLocationForm from '../subforms/EffectiveDateAndLocationForm';
import InsuredInformationForm from '../subforms/InsuredInformationForm';
import ProductAndClassificationForm from '../subforms/ProductAndClassificationForm';
import RiskInformationForm from '../subforms/RiskInformationForm';
import LossHistoryForm from '../subforms/LossHistoryForm';
import QuoteForm from '../subforms/QuoteForm';
import CheckForm from '../subforms/CheckForm';
import CoverageForm from '../subforms/CoverageForm';
import PropertyCoverageForm from '../subforms/PropertyCoverageForm';
import ComPropertyForm from '../subforms/ComPropertyForm';
import { useEffect } from 'react';
import { CommPropModalDefaultValue, modalDefaultValue ,CommAutoModalDefaultValue} from '../../utility/modalDefaultValue';
import BusinessInformationForm from '../subforms/BusinessInformationForm';
import VehiclesForm from '../subforms/VehiclesForm';
import DriverForm from '../subforms/DriverForm';
import BuildingsForm from '../subforms/BuildingsForm';
import LocationsForm from '../subforms/LocationsForm';
import { CommercialAutoModalStateResopnseJson } from "../../utility/resopnseModalStateComAuto";
import {StateName} from '../../dummydata/StateList';
import { CommercialPropertyResopnseJson } from '../../utility/resopnseJsonComProperty';
import CryptoJS from "crypto-js";
import API_Headers from '../../API_Headers';
import ComPropertyQuickSaveForLaterService from '../services/ComPropertyQuickSaveForLaterService';

const Stepper = ({ state = { locationState: "", state: "", classGroup: "", classCodes: [], product: "" }, openModal, modalState, setModalState, resetmodal = () => { }, steps, TabName, onSubmit = () => { } }) => {
    const { register, formState: { errors }, handleSubmit, reset, control, setValue, getValues, trigger } = useForm();
    const [formData, setFormData] = useState({})
    const [activeStep, setActiveStep] = useState(0)
    var [ policyNumbers, setPolicyNumbers ] = useState();
    const history = useHistory();
    const [headers, setHeaders] = useState([])
    const secretPass = process.env.REACT_APP_Secret_Key;
    const [tempId, setTempId] = useState(0)
    const [previousQuoteData, setPreviousQuoteData] = useState({})

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

    useEffect(() => {
        if (state.policyNumber!== null && state.policyNumber!== undefined) {
        const responseModalStateJson = CommercialAutoModalStateResopnseJson(state)
           setModalState(responseModalStateJson.modalState);
        }        
    }, []);

    useEffect(() => {
      if(TabName == "Commercial Property" && !!state.quoteData) {
        const uncompleteQuote = JSON.parse(state.quoteData)
        setPreviousQuoteData(uncompleteQuote);
        if(!!uncompleteQuote.application.step && uncompleteQuote.application.step > 0) {
            setActiveStep(uncompleteQuote.application.step)
            let activeClassSteps = {...activeClass}
            for (let index = 0; index < uncompleteQuote.application.step; index++) {
                activeClassSteps = {
                    ...activeClassSteps,
                    [steps[index]]: 'stepblk past'
                }
            }
            setActiveClass({ ...activeClassSteps, [steps[uncompleteQuote.application.step]]: 'stepblk actv' })
        }
      }
    }, [state])
    

    useEffect(() => {
        if (state.policyNumber!== null && state.policyNumber!== undefined) {
            setPolicyNumbers(state.policyNumber);
        }        
    }, []);

    useEffect(() => {
        state.application && modalState.AddPolicyData.length === 0 && TabName === 'Commercial Excess Liability' && modalDefaultValue(state, modalState, setModalState)
        state.application && modalState.AddPolicyData.length === 0 && TabName === 'Commercial Property' && CommPropModalDefaultValue(state, modalState, setModalState)
        state.application && modalState.AddPolicyData.length === 0 && TabName === 'Commercial Auto' && CommAutoModalDefaultValue(state, modalState, setModalState)
    },[])
    const step_icons = {
        "Effective Date & Location": step1,
        "Quote and Insured Information": EligibilityCheck,
        "Product & Classification": step2,
        "Risk Information": step3,
        "Coverage": step4,
        "Property Coverage": step4,
        "Vehicles": step5,
        "Drivers": step6,
        "Loss History": step7,
        "Quote": step8,
        "Check": step9,
        "Add Location": AddLocation,
        "Add Building": AddBuilding,
        "Add Coverage": step12,
        "Business Information" : BusinessInfo
    }
    const initialActiveClass = {
        "Quote and Insured Information": "stepblk actv",
        "Effective Date & Location": "stepblk",
        "Product & Classification": "stepblk",
        "Risk Information": "stepblk",
        "Coverage": "stepblk",
        "Property Coverage": "stepblk",
        "Vehicles": "stepblk",
        "Drivers": "stepblk",
        "Loss History": "stepblk",
        "Quote": "stepblk",
        "Check": "stepblk",
        "Add Location": "stepblk",
        "Add Building": "stepblk",
        "Add Coverage": "stepblk",
        "Business Information" : "stepblk",
    }

    const [activeClass, setActiveClass] = useState(initialActiveClass)

    function getStateCode(stateName) {
        const state = StateName.find(obj => obj.name == stateName);
        if (state) {
          return state.code;
        } else {
          return null;
        }
    }

    function mergeObjects(obj1, obj2) {
        // Create a new object to hold the merged result
        const result = {};
      
        // Iterate through all properties of obj1
        for (let key in obj1) {
          if (obj1.hasOwnProperty(key)) {
            // If the property exists in obj2 and both values are objects, recursively merge them
            if (obj2.hasOwnProperty(key) && typeof obj1[key] === 'object' && typeof obj2[key] === 'object') {
              result[key] = mergeObjects(obj1[key], obj2[key]);
            } else {
              // Otherwise, use the value from obj1
              result[key] = obj1[key];
            }
          }
        }
      
        // Iterate through all properties of obj2
        for (let key in obj2) {
          if (obj2.hasOwnProperty(key)) {
            // If the property doesn't exist in result, add it
            if (!result.hasOwnProperty(key)) {
              // If the value is an object, clone it to avoid mutating the original object
              result[key] = typeof obj2[key] === 'object' ? { ...obj2[key] } : obj2[key];
            }
          }
        }
      
        return result;
      }

    const handleNext = async (data) => {
        steps[activeStep] === "Risk Information" && TabName === 'Commercial Excess Liability' && openModal('mainModal', "Risk Comply Message")
        if (TabName === 'Commercial Excess Liability' && steps[activeStep] === "Risk Information") {
            setValue("AddedPolicies", modalState.AddPolicyData)
        }
        else if (TabName === 'Commercial Auto' && steps[activeStep] === "Vehicles") {
            setValue("AddedVehicles", modalState.VehicleData)
        }
        else if (TabName === 'Commercial Auto' && steps[activeStep] === "Drivers") {
            setValue("AddedDrivers", modalState.DriverData)
        }
        else if (TabName === 'Commercial Property' && steps[activeStep] === "Add Location") {
            setValue("AddedLocations", modalState.LocationData)
        }
        else if (TabName === 'Commercial Property' && steps[activeStep] === "Add Building") {
            setValue("AddedBuildingLocation", modalState.BuildingLocData)
        }
        else if (TabName === 'Commercial Property' && steps[activeStep] === "Add Coverage") {
            setValue("AddedBuildingCoverages", modalState.CoverageData)
        }
        else if (TabName === 'Commercial Property' && steps[activeStep] === "Risk Information") {
            setValue("BuildingRiskData", modalState.BuildingRiskData)
        }
        if(TabName == 'Commercial Property') {
            if (TabName === 'Commercial Property' && steps[activeStep] === "Add Location") {
                data = { ...data, "AddedLocations": modalState.LocationData }
            } else if (TabName === 'Commercial Property' && steps[activeStep] === "Add Building") {
                data = { 
                    ...data, 
                    "BuildingLocData": modalState.BuildingLocData ,
                    insuredName: data.InsuredName,
                    state: data.busState,
                    effectiveDt: data.EffectiveDate,
                    product: 'Commercial Property',
                    tempId: tempId
                }
            }
            const updatedHeaders = {
                                    ...headers,
                                    insuredName: data.InsuredName,
                                    state: data.busState,
                                    effectiveDt: data.EffectiveDate,
                                    product: 'Commercial Property',
                                    tempId: state.id ? state.id : activeStep == 0 || tempId == 0 ? 0 : tempId 
                                }
            data = {...data,
                step: activeStep+1,
                "busState":getStateCode(data.busState),
                "primaryState":getStateCode(data.primaryState) 
            }
            let responseJson = CommercialPropertyResopnseJson(data, state, '')
            if(!!state.id && Object.keys(previousQuoteData).length > 0) {
                responseJson = mergeObjects(previousQuoteData, responseJson)
                responseJson.application.step = activeStep+1;
            }
            const data1 = CryptoJS.AES.encrypt(
                JSON.stringify(responseJson),
                secretPass
              ).toString();
              try {
                  const premiumData = await ComPropertyQuickSaveForLaterService.saveDetails(
                     data1,
                     updatedHeaders
                  );
                  setTempId(premiumData?.data?.id)
              } catch (error) {
                setTempId(0)
              }
        }
        setFormData(data)
        setActiveStep(activeStep + 1);        
        setActiveClass({ ...activeClass, [steps[activeStep]]: 'stepblk past', [steps[activeStep + 1]]: 'stepblk actv' })
    };

    const submitData =  async (data) => {
        var newData = data
        if (TabName === 'Commercial Excess Liability' && steps[activeStep] === "Risk Information") {
            newData = { ...data, "AddedPolicies": modalState.AddPolicyData }
        }
        else if (TabName === 'Commercial Auto' && steps[activeStep] === "Drivers") {
            newData = { ...data, "DriverData": modalState.DriverData }
        }
        else if (TabName === 'Commercial Property' && steps[activeStep] === "Add Location") {
            newData = { ...data, "LocationData": modalState.LocationData }
        }
        else if (TabName === 'Commercial Property' && steps[activeStep] === "Add Coverage") {
            newData = { ...data, "CoverageData": modalState.CoverageData }
        }
        else if (TabName === 'Commercial Property' && steps[activeStep] === "Add Building") {
            newData = { 
                ...data, 
                "BuildingLocData": modalState.BuildingLocData ,
                insuredName: data.InsuredName,
                state: data.busState,
                effectiveDt: data.EffectiveDate,
                product: 'Commercial Property',
                tempId: tempId
            }
        }
        else if (TabName === 'Commercial Property' && steps[activeStep] === "Risk Information") {
            newData = { ...data, "BuildingRiskData": modalState.BuildingRiskData }
        }
        onSubmit(newData)
    }

    const handleBack = () => {
        setActiveStep(activeStep - 1);
        setActiveClass({ ...activeClass, [steps[activeStep]]: 'stepblk', [steps[activeStep - 1]]: 'stepblk actv' })
    }

    const handleBackToIndication = () => {
        history.push({
            pathname: "/eligibilitycheck",
            data: { state: state }
        });
    }

    return (
        <div className="coverContainer">
            <div className="stepswrap commercial">
                {steps.includes("Quote and Insured Information") ?
                    <div className={activeClass["Quote and Insured Information"]}>
                        <a href="# ">
                            <div className="icon"><img src={EligibilityCheck} alt="Exavalu" title="Exavalu" /></div>
                            <p>Quote and Insured Information</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Effective Date & Location") ?
                    <div className={activeClass["Effective Date & Location"]}>
                        <a href="# ">
                            <div className="icon"><img src={step1} alt="Exavalu" title="Exavalu" /></div>
                            <p>Effective Date &<br />
                                Location</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Business Information") ?
                    <div className={activeClass["Business Information"]}>
                        <a href="# ">
                            <div className="icon"><img src={BusinessInfo} alt="Exavalu" title="Exavalu" /></div>
                            <p>Business Information</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Product & Classification") ?
                    <div className={activeClass["Product & Classification"]}>
                        <a href="# ">
                            <div className="icon"><img src={step2} alt="Exavalu" title="Exavalu" /></div>
                            <p>Product &<br />
                                Classification</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Risk Information") ?
                    <div className={activeClass["Risk Information"]}>
                        <a href="# ">
                            <div className="icon"><img src={step3} alt="Exavalu" title="Exavalu" /></div>
                            <p>Risk Information</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Add Location") ?
                    <div className={activeClass["Add Location"]}>
                        <a href="# ">
                            <div className="icon"><img src={AddLocation} alt="Exavalu" title="Exavalu" /></div>
                            <p>Add Location</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Add Building") ?
                    <div className={activeClass["Add Building"]}>
                        <a href="# ">
                            <div className="icon"><img src={AddBuilding} alt="Exavalu" title="Exavalu" /></div>
                            <p>Add Building</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Add Coverage") ?
                    <div className={activeClass["Add Coverage"]}>
                        <a href="# ">
                            <div className="icon"><img src={step12} alt="Exavalu" title="Exavalu" /></div>
                            <p>Add Coverage</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Coverage") ?
                    <div className={activeClass["Coverage"]}>
                        <a href="# ">
                            <div className="icon"><img src={step4} alt="Exavalu" title="Exavalu" /></div>
                            <p>Coverage</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Property Coverage") ?
                    <div className={activeClass["Property Coverage"]}>
                        <a href="# ">
                            <div className="icon"><img src={step4} alt="Exavalu" title="Exavalu" /></div>
                            <p>Property Coverage</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Vehicles") ?
                    <div className={activeClass["Vehicles"]}>
                        <a href="# ">
                            <div className="icon"><img src={step5} alt="Exavalu" title="Exavalu" /></div>
                            <p>Vehicles</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Drivers") ?
                    <div className={activeClass["Drivers"]}>
                        <a href="# ">
                            <div className="icon"><img src={step6} alt="Exavalu" title="Exavalu" /></div>
                            <p>Drivers</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Loss History") ?
                    <div className={activeClass["Loss History"]}>
                        <a href="# ">
                            <div className="icon"><img src={step7} alt="Exavalu" title="Exavalu" /></div>
                            <p>Loss History</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Quote") ?
                    <div className={activeClass["Quote"]}>
                        <a href="# ">
                            <div className="icon"><img src={step8} alt="Exavalu" title="Exavalu" /></div>
                            <p>Quote</p>
                        </a>
                    </div> : null
                }
                {steps.includes("Check") ?
                    <div className={activeClass["Check"]}>
                        <a href="# ">
                            <div className="icon"><img src={step9} alt="Exavalu" title="Exavalu" /></div>
                            <p>Check</p>
                        </a>
                    </div> : null
                }
            </div>
            <div className="commonform_box">
                <div className="head"><span><img src={step_icons[steps[activeStep]]} alt="Exavalu" title="Exavalu" /></span>{steps[activeStep]}</div>
                <div className="inf">
                    <div className="riskinfo">
                        <form onSubmit={handleSubmit(submitData)} className="allForm">
                            {steps[activeStep] === "Effective Date & Location" ? <EffectiveDateAndLocationForm state={state} register={register} errors={errors} TabName={TabName} control={control} getValues={getValues} setValue={setValue} trigger={trigger} formData={formData}/> : null}
                            {steps[activeStep] === "Quote and Insured Information" ? <InsuredInformationForm formData={formData} state={state} register={register} errors={errors} TabName={TabName} control={control} getValues={getValues} setValue={setValue} modalState={modalState} setModalState={setModalState} trigger={trigger} /> : null}
                            {steps[activeStep] === "Business Information" ? <BusinessInformationForm formData={formData} state={state} register={register} errors={errors} TabName={TabName} setValue={setValue}/> : null }
                            {steps[activeStep] === "Product & Classification" ? <ProductAndClassificationForm state={state} register={register} errors={errors} TabName={TabName} control={control} getValues={getValues} setValue={setValue} /> : null}
                            {steps[activeStep] === "Risk Information" ? <RiskInformationForm state={state} register={register} errors={errors} TabName={TabName} openModal={openModal} modalState={modalState} setModalState={setModalState} control={control} getValues={getValues} setValue={setValue} formData={formData} /> : null}
                            {steps[activeStep] === "Loss History" ? <LossHistoryForm register={register} errors={errors} /> : null}
                            {steps[activeStep] === "Quote" ? <QuoteForm /> : null}
                            {steps[activeStep] === "Coverage" ? <CoverageForm state={state} register={register} errors={errors} TabName={TabName} formData={formData} setValue={setValue} /> : null}
                            {steps[activeStep] === "Property Coverage" ? <PropertyCoverageForm register={register} errors={errors} TabName={TabName} /> : null}
                            {steps[activeStep] === "Vehicles" ? <VehiclesForm openModal={openModal} modalState={modalState} getValues={getValues} setValue={setValue} setModalState={setModalState} state={state} /> : null}
                            {steps[activeStep] === "Drivers" ? <DriverForm openModal={openModal} modalState={modalState} getValues={getValues} setValue={setValue} setModalState={setModalState}/> : null}
                            {steps[activeStep] === "Check" ? <CheckForm /> : null}
                            {steps[activeStep] === "Add Location" ? <LocationsForm state={state} openModal={openModal} modalState={modalState} setModalState={setModalState} /> : null}
                            {steps[activeStep] === "Add Building" ? <BuildingsForm openModal={openModal} modalState={modalState} setModalState={setModalState} /> : null}
                            {steps[activeStep] === "Add Coverage" ? <ComPropertyForm openModal={openModal} modalState={modalState} /> : null}
                            
                            <div className="form-element-btn-left">
                                {activeStep !== 0 ?
                                    <button type="button" className="btn blue" onClick={handleBack}>
                                        Back
                                    </button>
                                    :
                                    ''                                     
                                  }
                            </div>
                            <div className="form-element-btn">
                                {activeStep !== steps.length - 1 ?
                                    <button type="submit" className="btn blue" onClick={handleSubmit(handleNext)}>
                                        Next
                                    </button>
                                    :
                                    policyNumbers!== null && policyNumbers!== undefined ?
                                    <button type="submit" className="btn blue" onClick={() => handleSubmit(submitData)}>                      
                                        Endorse
                                    </button>
                                    :
                                    <button type="submit" className="btn blue" onClick={() => handleSubmit(submitData)}>                      
                                        Create Quote
                                    </button>
                                }
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Stepper
