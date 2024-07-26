import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useHistory, useLocation } from "react-router-dom";
import Layout from "../../layout/Layout";
import FormControl from "../../components/formcontrol/FormControl";
import DateInput from "../../components/dateinput/DateInput";
import AutoComplete from "react-google-autocomplete";
import ISOService from "../services/ISOService";
import CryptoJS from "crypto-js";
import API_Headers from "../../API_Headers";
import DropDown from "../../components/dropdown/DropDown";
import Papa from "papaparse";
import dropdown_val from "../../dummydata/commercialAuto.csv";
import { CommercialPropertyResopnseJson } from "../../utility/resopnseJsonComProperty";
import ComPropertyIndicationService from "../services/ComPropertyIndicationService";
import Check from "../../components/check/Check";
import AddDriverForm from "./AddDriverForm";
import AddVehicleForm from "./AddVehicleForm";
import { CommercialAutoResopnseJson } from "../../utility/resopnseJsonComAuto";
import ComAutoIndicationService from "../services/ComAutoIndicationService";
import useLoader from "../../context/loader"

const AutoQuote = () => {
    const {setLoader} = useLoader()
    const history = useHistory();
    const { state } = useLocation();
    const {
        register,
        handleSubmit,
        reset,
        formState: { errors },
        setValue,
        getValues
    } = useForm();
    const GOOGLE_MAPS_API_KEY = "AIzaSyDCttdJ2FGaxhCc5hkh9vOzRWTGCPy_x-o";
    const secretPass = process.env.REACT_APP_Secret_Key;
    const [headers, setHeaders] = useState([]);
    const [dropdownVal, setDropdownVal] = useState([]);
    const [businessAddress, setBusinessAddress] = useState({
        address1: "",
        address2: "",
        postcode: "",
        state: "",
        city: "",
        country: "",
    });
    const [primaryBusinessAddress, setPrimaryBusinessAddress] = useState({
        address1: "",
        address2: "",
        postcode: "",
        state: "",
        city: "",
        country: "",
    });
    const [displayFireLineMessage, setDisplayFireLineMessage] = useState(false);
    const [FireLineMessage, setFireLineMessage] = useState("");
    const [insuredInfo, setInsuredInfo] = useState([]);
    const [insuredName, setInsuredName] = useState("");
    const [dba, setDba] = useState("");
    const [effectiveDate, setEffectiveDate] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [mailingAddress, setMailingAddress] = useState("");
    const [mailingCity, setMailingCiy] = useState("");
    const [mailingState, setMailingState] = useState("");
    const [mailingZip, setMailingZip] = useState("");
    const [mailingCountry, setMailingCountry] = useState("");
    const [shippingAddress, setShippingAddress] = useState("");
    const [shippingCity, setShippingCiy] = useState("");
    const [shippingState, setShippingState] = useState("");
    const [shippingZip, setShippingZip] = useState("");
    const [shippingCountry, setShippingCountry] = useState("");
    const [street, setStreet] = useState("");
    const [city, setCity] = useState("");
    const [locationState, setLocationState] = useState("");
    const [zip, setZip] = useState("");
    const [description, setDescription] = useState("");
    const [propertyDeductible, setPropertyDeductible] = useState([]);
    const [expandedProperty, setExpandedProperty] = useState([]);
    const [businessType, setBusinessType] = useState([]);
    const [construction, setConstruction] = useState("");
    const [roof, setRoof] = useState("");
    const [yearBuilt, setYearBuilt] = useState("");
    const [sqFoot, setSqFoot] = useState("");
    const [buildingLimit, setBuildingLimit] = useState("");
    const [deductible, setDeductible] = useState("");
    const [comAutoRiskAccepted, setComAutoRiskAccepted] = useState(true);
    console.log(state)
    useEffect(() => {
        state.map((obj, index) => {
            if (obj['NAME (First Named Insured) AND MAILING ADDRESS (including ZIP+4) '] !== undefined) {
                setInsuredInfo(obj['NAME (First Named Insured) AND MAILING ADDRESS (including ZIP+4) '].split(','))

            }
            else if (obj['PROPOSED EFF DATE '] !== undefined) {

                setEffectiveDate(obj['PROPOSED EFF DATE '])

            }
            else if (obj['BUSINESS PHONE #: '] !== undefined) {
                setValue('insuredPhone', obj['BUSINESS PHONE #: '])
            }
            else if (obj['STREET '] !== undefined) {
                setStreet(obj['STREET '])
            }
            else if (obj['CITY: '] !== undefined) {
                setCity(obj['CITY: '])
            }
            else if (obj['STATE: '] !== undefined) {
                setLocationState(obj['STATE: '])
            } else if (obj['ZIP: '] !== undefined) {
                setZip(obj['ZIP: '])
            } 

        })
    }, [])

    useEffect(() => {
        if (insuredInfo.length !== 0) {
            setValue('InsuredName', insuredInfo[0])
            setValue("insuredAddress", insuredInfo[1]);
            setValue("busCity", insuredInfo[2]);
            setValue("busState", insuredInfo[3]);
            setValue("busPostalCode", insuredInfo[4]);
            setValue("busCountry", insuredInfo[5]);
            // setValue("insuredAddress", insuredInfo[0]);
            // setValue("primaryCity", insuredInfo[1]);
            // setValue("primaryState", insuredInfo[2]);
            // setValue("primaryPostcode", insuredInfo[3]);
            // setValue("primaryCountry", insuredInfo[4]);
            // setStreet(insuredInfo[0]);
            // setCity(insuredInfo[1]);
            // setLocationState(insuredInfo[2]);
            // setZip(insuredInfo[3]);
        }
    }, [insuredInfo]);
    var commonConfig = { delimiter: "," };
    useEffect(() => {
        API_Headers().then((val) => setHeaders(val));
    }, []);
    function verifyCheckBox(e) {
        if (e.target.checked) {
            setComAutoRiskAccepted(true);
        }
        else {
            setComAutoRiskAccepted(false);
        }
    }
    useEffect(() => {
        Papa.parse(dropdown_val, {
            ...commonConfig,
            header: true,
            download: true,
            complete: (result) => {
                setDropdownVal(result.data);
            },
        });
    }, []);
// console.log(dropdownVal[1].MedicalPayment)
    useEffect(() => {
        if (dropdownVal.length > 0) {
            setValue('PrimaryBusType', dropdownVal[1].PrimaryBusinessType)
            setValue('PrimaryBusSubType', dropdownVal[1].PrimaryBusinessSubType)
            setValue('LiabilityLimit', dropdownVal[1].Liability)
            setValue('MedicalPayments', dropdownVal[1].MedicalPayment)
            setValue('ComprehensiveDeductible', dropdownVal[1].ComprehensiveDeductible)
            setValue('CollisionDeductible', dropdownVal[1].Collisiondeductible)
            setValue('TowingAndLabor', dropdownVal[1].TowingandLabor)
            setValue('RentalReimbursement', dropdownVal[1].RentalReimbursement)
            setValue('NonOwnedAuto', dropdownVal[1].NonOwnedauto)

        }
    }, [dropdownVal, setValue])


    function fillInAddress(place, e) {
        e.name === "MailingAddress"
            ? setBusinessAddress({
                ...businessAddress,
                address1: "",
                address2: "",
                postcode: "",
                city: "",
                state: "",
                country: "",
            })
            : setPrimaryBusinessAddress({
                ...primaryBusinessAddress,
                address1: "",
                address2: "",
                postcode: "",
                city: "",
                state: "",
                country: "",
            });
        let address1 = "";
        let address2 = "";
        let postcode = "";
        let city = "";
        let state = "";
        let country = "";
        if (place.address_components) {
            for (const component of place.address_components) {
                const componentType = component.types[0];
                switch (componentType) {
                    case "street_number": {
                        address1 = `${component.long_name} ${address1}`;
                        break;
                    }

                    case "route": {
                        address1 += component.short_name;
                        break;
                    }

                    case "sublocality_level_1": {
                        address2 = component.long_name;
                        break;
                    }

                    case "postal_code": {
                        postcode = `${component.long_name}${postcode}`;
                        break;
                    }

                    case "postal_code_suffix": {
                        postcode = `${postcode}-${component.long_name}`;
                        break;
                    }
                    case "locality": {
                        city = component.long_name;
                        break;
                    }
                    case "administrative_area_level_1": {
                        state = component.short_name;
                        break;
                    }
                    case "country":
                        country = component.long_name;
                        break;
                }
            }
        }
        if (e.name === "MailingAddress") {
            setBusinessAddress({
                ...businessAddress,
                address1: address1,
                address2: address2,
                postcode: postcode,
                city: city,
                state: state,
                country: country,
            });
            setValue("insuredAddress", address1);
            setValue("busCity", city);
            setValue("busState", state);
            setValue("busPostalCode", postcode);
            setValue("busCountry", country);
        }
        e.value = address1;
        if (e.name === "PrimaryBusinessAddress") {
            setPrimaryBusinessAddress({
                ...primaryBusinessAddress,
                address1: address1,
                address2: address2,
                postcode: postcode,
                city: city,
                state: state,
                country: country,
            });
            setValue("insuredAddress", address1);
            setValue("primaryCity", city);
            setValue("primaryState", state);
            setValue("primaryPostcode", postcode);
            setValue("primaryCountry", country);
        }
    }

    function formatPhoneNo(e) {
        var phone = e.target.value;
        var phoneClone = phone;
        phoneClone = phoneClone.split("");
        phoneClone.length == 4 &&
            phoneClone[3] !== "-" &&
            phoneClone.splice(3, 0, "-");
        phoneClone.length == 8 &&
            phoneClone[7] !== "-" &&
            phoneClone.splice(7, 0, "-");
        phoneClone = phoneClone.join("");
        if (phoneClone.length > 11) {
            phoneClone = phoneClone.slice(0, 12);
        }
        e.target.value = phoneClone;
    }



    const handleBack = () => {
        history.push({
            pathname: "/dashboard",
        });
    };
    const Comp = ({ openModal, state, modalState, setModalState }) => {
        const submitData = async (data) => {
            let newData = data;
            newData = {
                ...data,
                DriverData: modalState.DriverData,
                AddedVehicles: modalState.VehicleData,
            };
            // newData = { ...data, "BuildingLocData": modalState.BuildingLocData }
        };
        const onSubmit = async (data) => {
            setLoader(true)
            let newData = data;
            newData = {
                ...data,
                DriverData: modalState.DriverData,
                AddedVehicles: modalState.VehicleData,
            };
            // newData = { ...data, "BuildingLocData": modalState.BuildingLocData }
            try {
                sessionStorage.removeItem("quoteNumber")
                const responseJson = CommercialAutoResopnseJson(newData, state)
                const data1 = CryptoJS.AES.encrypt(
                    JSON.stringify(responseJson),
                    secretPass
                  ).toString();
                const premiumData = await ComAutoIndicationService.getPremiumDetails(
                    data1,
                    headers
                );
                setLoader(false)
                history.push({
                    pathname: "/premiumDataMsg",
                    state: { detail: premiumData.data, responseJson: responseJson }
                });
            } catch (error) {
                setLoader(false)
                if (error == "Error: Network Error") {
                    history.push("/SystemMaintenance");
                } else {
                    alert(error)
                    console.log(error)
                }
            }
        }
        return (
            <>
                
                    <div class="coverContainer">
                        <div class="commonform_box">
                            <div class="inf">
                                <form class="allForm" >
                                    <div class="fullhead" style={{ color: '#CF0918' }}>Quote and Insured Information:</div><br />
                                    <div class="grid2">
                                        <div class="form-element">
                                            <FormControl fieldName="InsuredName" label='Insured Name' id='insuredName' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                        </div>
                                        <div class="form-element">
                                            <FormControl fieldName="dba" label='DBA' id='dba' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                        </div>
    
                                    </div>
                                    <div class="form-element">
                                        <FormControl
                                            fieldName="dba"
                                            label="DBA"
                                            id="dba"
                                            register={register}
                                            errors={errors}
                                            type="text"
                                            showLabel={true}
                                            className="form-field"
                                            required={false}
                                            lableClass="form-label"
                                            activeBold={false}
                                        />
                                    </div>
                               
                                <div class="grid2">
                                    {/* <DateInput fieldName="Effective Date" register={register} errors={errors} required={true} setValue={setValue} id='effDate' minDate={new Date()} yearRange="-0:+60" /> */}
                                    <DateInput
                                        fieldName="Effective Date"
                                        register={register}
                                        errors={errors}
                                        required={true}
                                        setValue={setValue}
                                        id="effDate"
                                        minDate={new Date()}
                                        yearRange="-0:+60"
                                        // onClick={(e)=>console.log(e.target.value)}
    
                                        defaultValue={effectiveDate}
                                    ></DateInput>
                                </div>
    
                                <div class="fullhead" style={{ color: "#CF0918" }}>
                                    Business Address:
                                </div>
                                <br />
                                <div className="grid3">
                                    <div class="form-element">
                                        <label className="form-label">
                                            Address <b>*</b>
                                        </label>
                                        {/* <AutoComplete
                                                name="MailingAddress"
                                                apiKey={GOOGLE_MAPS_API_KEY}
                                                defaultValue={addr}
                                                placeholder=""
                                                className='form-control form-field'
                                                style={{ width: "100%" }}
                                                label="Address"
                                                onPlaceSelected={(e, addr) => {
                                                    fillInAddress(e, addr)
                                                }}
                                                onChange={(e,addr)=>{
                                                    fillInAddress(e, addr)
                                                }}
                                                options={{
                                                    types: ["(regions)"],
                                                    componentRestrictions: { country: ["us", "ca"] },
                                                    fields: ["address_components", "geometry"],
                                                    types: ["address"]
                                                }}
                                            /> */}
                                        <FormControl
                                            fieldName="insuredAddress"
                                            name="insuredAddress"
                                            label="Address"
                                            id="address"
                                            register={register}
                                            errors={errors}
                                            type="text"
                                            showLabel={true}
                                            className="form-field"
                                            required={true}
                                            lableClass="form-label"
                                            activeBold={true}
                                        ></FormControl>
                                    </div>
                                    <div
                                        class={
                                            businessAddress.city.length === 0
                                                ? "form-element"
                                                : "form-element has-value"
                                        }
                                    >
                                        <FormControl
                                            fieldName="busCity"
                                            label="City"
                                            id="city"
                                            register={register}
                                            errors={errors}
                                            type="text"
                                            showLabel={true}
                                            className="form-field"
                                            required={true}
                                            lableClass="form-label"
                                            activeBold={true}
                                        />
                                    </div>
                                    <div
                                        class={
                                            businessAddress.state.length === 0
                                                ? "form-element"
                                                : "form-element has-value"
                                        }
                                    >
                                        <FormControl
                                            fieldName="busState"
                                            label="State"
                                            id="state"
                                            register={register}
                                            errors={errors}
                                            type="text"
                                            showLabel={true}
                                            className="form-field"
                                            required={true}
                                            lableClass="form-label"
                                            activeBold={true}
                                        />
                                    </div>
                                    <div
                                        class={
                                            businessAddress.postcode.length === 0
                                                ? "form-element"
                                                : "form-element has-value"
                                        }
                                    >
                                        <FormControl
                                            fieldName="busPostalCode"
                                            label="Postcode"
                                            id="postcode"
                                            register={register}
                                            errors={errors}
                                            type="text"
                                            showLabel={true}
                                            className="form-field"
                                            required={true}
                                            lableClass="form-label"
                                            activeBold={true}
                                        />
                                    </div>
                                    <div
                                        class={
                                            businessAddress.country.length === 0
                                                ? "form-element"
                                                : "form-element has-value"
                                        }
                                    >
                                        <FormControl
                                            fieldName="busCountry"
                                            label="Country"
                                            id="country"
                                            register={register}
                                            errors={errors}
                                            type="text"
                                            showLabel={true}
                                            className="form-field"
                                            required={true}
                                            lableClass="form-label"
                                            activeBold={true}
                                        />
                                    </div>
                                </div>
    
                                <div class="fullhead" style={{ color: "#CF0918" }}>
                                    Business Information:
                                </div>
                                <br />
                                <div className="grid3">
                                    <div className="form-element form-select">
                                        {dropdownVal.length > 0 ? (
                                            <DropDown
                                                defaultValue={dropdownVal[1].PrimaryBusinessType}
                                                fieldName="PrimaryBusType"
                                                label="Primary Business Type"
                                                register={register}
                                                setValue={setValue}
                                                errors={errors}
                                                lableClass="form-label"
                                                activeBold={false}
                                                showLabel={true}
                                            >
                                                {dropdownVal.map((value, ind) => {
                                                    return (
                                                        <option
                                                            value={value.PrimaryBusinessType}
                                                            selected={ind == 1 ? true : false}
                                                        >
                                                            {value.PrimaryBusinessType}
                                                        </option>
                                                    );
                                                })}
                                            </DropDown>
                                        ) : null}
                                    </div>
                                    <div className="form-element form-select">
                                        {dropdownVal.length > 0 ? (
                                            <DropDown defaultValue={dropdownVal[1].PrimaryBusinessSubType} fieldName='PrimaryBusSubType' label='Primary Business Sub Type' register={register} errors={errors} lableClass='form-label' activeBold={false} showLabel={true} >
                                                {dropdownVal.map((value, ind) => {
                                                    return (
                                                        <option selected={ind == 1 ? true : false} value={value.PrimaryBusinessSubType === "-select-" ? "" : value.PrimaryBusinessSubType}>{value.PrimaryBusinessSubType}</option>
                                                    )
                                                }
                                                )}
                                            </DropDown>
                                        ) : null}
                                    </div>
    
                                </div>
                                <div class="fullhead" style={{ color: "#CF0918" }}>
                                    Risk Information:
                                </div>
                                <br />
                                <p><b>For indication, please read the following:</b></p>
                                <ul>
                                    <li>• Indication is for an annual policy term</li>
                                    <li>• Indication does not include TRIA charge</li>
                                    <li>• Underlying policies are concurrent, occurrence (not claims made) and defense is outside</li>
                                    <li>• Underlying company carries an AM Best rating of B++ VI or better</li>
                                    <li>• No single loss in excess of $50,000 incurred in the past 5 years</li>
                                    <li>• For Auto: no livery, residential delivery, school buses, towing or sand/gravel haulers</li>
                                    <li>• For General Liability: no multi-unit residential development or cannabis industry exposures</li>
                                    <li>• There may be other risk attributes that make this risk ineligible</li>
                                    <li>• Indication is not binding</li>
                                </ul>
                                <br />
                                <p><b>Does this risk comply with our underwriting guidelines ?</b></p>
                                <Check id="comAutoRiskCheckBox" register={register} fieldName="Risk Comply" value="Yes" showLabel={false} type="checkbox" onClick={(e) => verifyCheckBox(e)} checked={comAutoRiskAccepted} />
                                <br />
                                {!comAutoRiskAccepted && <b style={{ color: 'red' }}>Please Accept</b>}
    
                                <div class="fullhead" style={{ color: "#CF0918" }}>
                                    Coverage
                                </div>
                                <br />
                                <div className='grid4'>
                        <div className='form-element form-select'>
                            <DropDown fieldName='LiabilityLimit' label='Liability Limit'  register={register} errors={errors} lableClass='form-label' activeBold={true}>
                              
                                {dropdownVal.map((value, ind) => {
                                                return value.Liability!== "" && value.Liability!== null && value.Liability!== undefined? (
                                                    <option value={value.Liability === "-select-" ? "" : value.Liability}>{value.Liability}</option>
                                                ) :
                                                    <></>
                                            }
                                            )}
                            </DropDown>
                        </div>
                      
                        <div className='form-element form-select'>
                            <DropDown fieldName='MedicalPayments'  register={register} errors={errors} lableClass='form-label' activeBold={true}>
                            
                             {dropdownVal.map((value, ind) => {
                                                return value.MedicalPayment!== "" && value.MedicalPayment!== null && value.MedicalPayment!== undefined ? (
                                                    <option value={value.MedicalPayment === "-select-" ? "" : value.MedicalPayment}>{value.MedicalPayment}</option>
                                                ) :
                                                    <></>
                                            }
                                            )}
                            </DropDown>
                        </div>
                        <div className='form-element form-select'>
                            <DropDown fieldName='ComprehensiveDeductible'  register={register} errors={errors} lableClass='form-label' activeBold={true}>
                           
                             {dropdownVal.map((value, ind) => {
                                                return value.ComprehensiveDeductible!== "" && value.ComprehensiveDeductible!== null && value.ComprehensiveDeductible!== undefined ? (
                                                    <option value={value.ComprehensiveDeductible === "-select-" ? "" : value.ComprehensiveDeductible}>{value.ComprehensiveDeductible}</option>
                                                ) :
                                                    <></>
                                            }
                                            )}
                            </DropDown>
                        </div>
                       
                        
                        <div className='form-element form-select'>
                            <DropDown fieldName='CollisionDeductible'  register={register} errors={errors} lableClass='form-label' activeBold={true}>
                            {/* {drop_data.Collisiondeductible.map((value, ind) => (
                                    <option value={value==='No Coverage'? 'NONE':value.replace(/[ (/,.?'")$&-]/gi, "")}>{value}</option>                            
                                    ))} */}
                            {dropdownVal.map((value, ind) => {
                                                return value.Collisiondeductible!== "" && value.Collisiondeductible!== null && value.Collisiondeductible!== undefined? (
                                                    <option value={value.Collisiondeductible === "-select-" ? "" : value.Collisiondeductible}>{value.Collisiondeductible}</option>
                                                ) :
                                                    <></>
                                            }
                                            )}
                            </DropDown>
                        </div>
                        <div className='form-element form-select'>
                            <DropDown fieldName='TowingAndLabor'  register={register} errors={errors} lableClass='form-label' activeBold={false}>
                           
                            {dropdownVal.map((value, ind) => {
                                                return value.TowingandLabor!== "" && value.TowingandLabor!== null && value.TowingandLabor!== undefined? (
                                                    <option value={value.TowingandLabor === "-select-" ? "" : value.TowingandLabor}>{value.TowingandLabor}</option>
                                                ) :
                                                    <></>
                                            }
                                            )}
                            </DropDown>
                        </div>
                        <div className='form-element form-select'>
                            <DropDown fieldName='RentalReimbursement'  register={register} errors={errors} lableClass='form-label' activeBold={false}>
                            
                            {dropdownVal.map((value, ind) => {
                                                return value.RentalReimbursement!== "" && value.RentalReimbursement!== null && value.RentalReimbursement!== undefined? (
                                                    <option value={value.RentalReimbursement === "-select-" ? "" : value.RentalReimbursement}>{value.RentalReimbursement}</option>
                                                ) :
                                                    <></>
                                            }
                                            )}
                            </DropDown>
                        </div>
                        <div className="form-element">
                            <FormControl fieldName="number_of_hired_vehicles" label='Number of Hired Vehicles'  id='number_of_hired_vehicles' register={register} errors={errors} type="number" showLabel={true} className="form-field" lableClass='form-label' activeBold={false} />
                        </div>
                        <div className='form-element form-select'>
                            <DropDown fieldName='NonOwnedAuto'  register={register} errors={errors} lableClass='form-label' activeBold={false}>
                            
                            {dropdownVal.map((value, ind) => {
                                                return value.NonOwnedauto!== "" && value.NonOwnedauto!== null && value.NonOwnedauto!== undefined? (
                                                    <option value={value.NonOwnedauto === "-select-" ? "" : value.NonOwnedauto}>{value.NonOwnedauto}</option>
                                                ) :
                                                    <></>
                                            }
                                            )}
                            </DropDown>
                        </div>
                    </div>
                    <div class="fullhead" style={{ color: "#CF0918" }}>
                                    Add Vehicle:
                                </div>
                                <br />
                                <AddVehicleForm
                                    openModal={openModal}
                                    modalState={modalState}
                                    setModalState={setModalState}
                                    getValues={getValues} 
                                    setValue={setValue}
                                    state={state}
                                />
                                <br />
                                <div class="fullhead" style={{ color: "#CF0918" }}>
                                    Add Driver:
                                </div>
                                <br />
                                <AddDriverForm
                                    openModal={openModal}
                                    modalState={modalState}
                                    setModalState={setModalState}
                                    getValues={getValues} 
                                    setValue={setValue}
                                    state={state}
                                />
                            </form>
                            <br />
                        </div>
                    </div>
                    <br />
                    <div class="grid6">
                        <button
                            onClick={handleBack}
                            className="btn blue"
                            style={{ marginRight: "5%" }}
                        >
                            Cancel
                        </button>
                        <button
                            onClick={handleSubmit(onSubmit)}
                            type="submit"
                            className="btn blue"
                        >
                            Quick Indication
                        </button>
                    </div>
                </div>
                </>
            );
    }
    
    return (
        <>
            <Layout
                TabName="New Quote"
                BreadCrum={["My Dashboard", "Quick Quote"]}
                sidebarQQSopen={true}
            >
                <Comp state={state} />
            </Layout>
        </>
    );
    };
    


export default AutoQuote;
