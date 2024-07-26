import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useHistory, useLocation } from "react-router-dom";
import Layout from "../../layout/Layout";
import FormControl from "../../components/formcontrol/FormControl";
import DateInput from "../../components/dateinput/DateInput";
import CryptoJS from "crypto-js";
import API_Headers from "../../API_Headers";
import DropDown from "../../components/dropdown/DropDown";
import Papa from "papaparse";
import dropdown_val from "../../dummydata/commercialProperty.csv";
import AddLocationForm from "./AddLocationForm";
import AddBuildingForm from "./AddBuildingForm";
import { CommercialPropertyResopnseJson } from "../../utility/resopnseJsonComProperty";
import ComPropertyIndicationService from "../services/ComPropertyIndicationService";
import useLoader from '../../context/loader'
// { prop =openModal, modalState, setModalState}
const PropertyQuote = () => {
    const {setLoader} = useLoader();
    const history = useHistory();
    const { state } = useLocation();
    const {
        register,
        handleSubmit,
        reset,
        formState: { errors },
        setValue,
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
    const [effectiveDate, setEffectiveDate] = useState("");
    const [street, setStreet] = useState("");
    const [city, setCity] = useState("");
    const [locationState, setLocationState] = useState("");
    const [zip, setZip] = useState("");
    const [description, setDescription] = useState("");
   

    var commonConfig = { delimiter: "," };
    useEffect(() => {
        API_Headers().then((val) => setHeaders(val));
    }, []);

    useEffect(() => {

        Papa.parse(dropdown_val, {
            ...commonConfig,
            header: true,
            download: true,
            complete: (result) => {
                const newData = result.data.filter(
                    (val) => val?.PrimaryBusinessType != ""
                );
                setDropdownVal(result.data);
                
            },
        });
    }, []);

    useEffect(() => {
        if (dropdownVal.length > 0) {
            setValue('PrimaryBusType', dropdownVal[1].PrimaryBusinessType)
            setValue('PrimaryBusSubType', dropdownVal[1].PrimaryBusinessSubType)
            setValue('PropertyDeductible', dropdownVal[1].PropertyDeductible)
            setValue('ExpandedProperty', dropdownVal[1].ExpandedProperty)
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

        const fetchStateData = () => {
            const buildingData = [...modalState.BuildingLocData]
            let pdfData = {
                "LocationNo": "",
                "ProtectiveDevices": "",
                "ConstructionType": "",
                "Sprinklered": "",
                "RoofType": "",
                "YearBuilt": "",
                "SquareFootage": "",
                "EstimatedReplacement": "",
                "BuildingLimit": "",
                "BuildingDeductible": "",
                "BusinessPersonalPropertyLimit": "",
                "BusinessPersonalPropertyDeductible": ""
            }
            const locationData = [...modalState.LocationData]
            let locData = {
                "LocationDesc": "",
                "StreetName": "",
                "City": "",
                "State": "",
                "PostalCode": "",
                "Country": "United States"
            }
            state.map((obj, index) => {
                if (
                    obj["STREET ADDRESS: "] !== undefined &&
                    obj["STREET ADDRESS: "] !== ""
                ) {
                    const insuranceInfo = obj["STREET ADDRESS: "].split(",");
                    setValue("insuredAddress", insuranceInfo[0]);
                    setValue("busCity", insuranceInfo[1]);
                    setValue("busState", insuranceInfo[2]);
                    setValue("busPostalCode", insuranceInfo[3]);
                    setValue("busCountry", insuranceInfo[4]);
                    setValue("insuredAddress", insuranceInfo[0]);
                    setValue("primaryCity", insuranceInfo[1]);
                    setValue("primaryState", insuranceInfo[2]);
                    setValue("primaryPostcode", insuranceInfo[3]);
                    setValue("primaryCountry", insuranceInfo[4]);
                    locData = {
                        ...locData,
                        "StreetName": insuranceInfo[0],
                        "City": insuranceInfo[1],
                        "State": insuranceInfo[2],
                        "PostalCode": insuranceInfo[3],
                    }

                } else if (obj["EFFECTIVE DATE "] !== undefined) {
                    setEffectiveDate(obj["EFFECTIVE DATE "]);
                } else if (
                    obj["TOTAL AREA "] !== undefined &&
                    obj["TOTAL AREA "] !== ""
                ) {
                    pdfData = { ...pdfData, SquareFootage: obj["TOTAL AREA "] } // setSqFoot(obj["TOTAL AREA "]);
                } else if (obj["LIMIT "] !== undefined && obj["LIMIT "] !== "$ ") {
                    pdfData = { ...pdfData, BuildingLimit: obj["LIMIT "] } //setBuildingLimit(obj["LIMIT "]);
                } else if (
                    obj["DEDUCTIBLE "] !== undefined &&
                    obj["DEDUCTIBLE "] !== "$ "
                ) {
                    pdfData = { ...pdfData, BuildingDeductible: obj["DEDUCTIBLE "] } //setDeductible(obj["DEDUCTIBLE "]);
                } else if (
                    obj["BLDG DESCRIPTION: "] !== undefined &&
                    obj["BLDG DESCRIPTION: "] !== ""
                ) {
                    locData = { ...locData, LocationDesc: obj["BLDG DESCRIPTION: "] }
                    pdfData = { ...pdfData, LocationNo: obj["BLDG DESCRIPTION: "] } // setDescription(obj["BLDG DESCRIPTION: "]);
                } else if (obj["APPLICANT (First Named Insured) "] !== undefined) {
                    setValue("InsuredName", obj["APPLICANT (First Named Insured) "]);
                } else if (obj["ROOF TYPE "] !== undefined && obj["ROOF TYPE "] !== "") {
                    pdfData = { ...pdfData, RoofType: obj["ROOF TYPE "] } // setRoof(obj["ROOF TYPE "]);
                } else if (obj["YR BUILT "] !== undefined && obj["YR BUILT "] !== "") {
                    // setYearBuilt(obj["YR BUILT "]);
                    pdfData = { ...pdfData, YearBuilt: obj["YR BUILT "] }
                } else if (
                    obj["CONSTRUCTION TYPE "] !== undefined &&
                    obj["CONSTRUCTION TYPE "] !== ""
                ) {
                    pdfData = { ...pdfData, ConstructionType: obj["CONSTRUCTION TYPE "] } // setConstruction(obj["CONSTRUCTION TYPE "]);
                }
            });
            buildingData.push(pdfData)
            locationData.push(locData)
            setModalState({ ...modalState, BuildingLocData: buildingData, LocationData: locationData })
        }

        useEffect(() => {
            if (modalState.BuildingLocData.length == 0) {
                fetchStateData()
            }
        }, []);
        const submitData = async (data) => {
            let newData = data;
            newData = {
                ...data,
                LocationData: modalState.LocationData,
                BuildingLocData: modalState.BuildingLocData,
            };

        };
        const onSubmit = async (data) => {
            setLoader(true);
            let newData = data;
            newData = {
                ...data,
                AddedLocations: modalState.LocationData,
                BuildingLocData: modalState.BuildingLocData,
            };

            try {
                sessionStorage.removeItem("quoteNumber");
                const responseJson = CommercialPropertyResopnseJson(newData, state);
                const data1 = CryptoJS.AES.encrypt(
                    JSON.stringify(responseJson),
                    secretPass
                ).toString();
                const premiumData =
                    await ComPropertyIndicationService.getPremiumDetails(data1, headers);
                    setLoader(false)
                history.push({
                    pathname: "/premiumDataMsg",
                    state: { detail: premiumData.data, responseJson: responseJson },
                });
            } catch (error) {
                setLoader(false)
                if (error == "Error: Network Error") {
                    history.push("/SystemMaintenance");
                } else {
                    alert(error);
                }
            }
        };
        return (
            <div class="coverContainer">
                <div class="commonform_box">
                    <div class="inf">
                        <form class="allForm" onSubmit={handleSubmit(submitData)}>
                            <div class="fullhead" style={{ color: "#CF0918" }}>
                                Insured Information:
                            </div>
                            <br />
                            <div class="grid2">
                                <div class="form-element">
                                    <FormControl
                                        fieldName="InsuredName"
                                        label="Insured Name"
                                        id="insuredName"
                                        register={register}
                                        errors={errors}
                                        type="text"
                                        showLabel={true}
                                        className="form-field"
                                        required={true}
                                        lableClass="form-label"
                                        activeBold={true}
                                    >

                                    </FormControl>
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
                            </div>
                            <div class="grid2">
                                <DateInput
                                    fieldName="Effective Date"
                                    register={register}
                                    errors={errors}
                                    required={true}
                                    setValue={setValue}
                                    id="effDate"
                                    minDate={new Date()}
                                    yearRange="-0:+60"

                                    defaultValue={effectiveDate}
                                ></DateInput>
                            </div>
                            <div class="grid2">
                                <div className="form-element">
                                    <FormControl
                                        fieldName="insuredPhone"
                                        label="Phone"
                                        id="phone_primary"
                                        register={register}
                                        errors={errors}
                                        type="text"
                                        showLabel={true}
                                        className="form-field"
                                        required={true}
                                        lableClass="form-label"
                                        activeBold={true}
                                        onChange={(e) => {
                                            formatPhoneNo(e);
                                        }}
                                    />
                                </div>
                                <div class="form-element">
                                    <FormControl
                                        fieldName="insuredEmail"
                                        label="Email Address"
                                        id="email_address"
                                        register={register}
                                        errors={errors}
                                        type="Email"
                                        showLabel={true}
                                        className="form-field"
                                        required={true}
                                        lableClass="form-label"
                                        activeBold={true}
                                    />
                                </div>
                            </div>
                            <div class="fullhead" style={{ color: "#CF0918" }}>
                                Mailing Address:
                            </div>
                            <br />
                            <div className="grid3">
                                <div class="form-element">
                                    <label className="form-label">
                                        Address <b>*</b>
                                    </label>

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
                            <div style={{ color: "#CF0918" }}>Primary Business Address:</div>
                            <br />

                            <div className="grid3">
                                <div
                                    class={
                                        primaryBusinessAddress.address1.length === 0
                                            ? "form-element"
                                            : "form-element has-value"
                                    }
                                >
                                    <label className="form-label">
                                        Address <b>*</b>
                                    </label>
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
                                        primaryBusinessAddress.city.length === 0
                                            ? "form-element"
                                            : "form-element has-value"
                                    }
                                >
                                    <FormControl
                                        fieldName="primaryCity"
                                        label="City"
                                        id="primaryCity"
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
                                        primaryBusinessAddress.state.length === 0
                                            ? "form-element"
                                            : "form-element has-value"
                                    }
                                >
                                    <FormControl
                                        fieldName="primaryState"
                                        label="State"
                                        id="primaryState"
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
                                        primaryBusinessAddress.postcode.length === 0
                                            ? "form-element"
                                            : "form-element has-value"
                                    }
                                >
                                    <FormControl
                                        fieldName="primaryPostcode"
                                        label="Postcode"
                                        id="primaryPostcode"
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
                                        primaryBusinessAddress.country.length === 0
                                            ? "form-element"
                                            : "form-element has-value"
                                    }
                                >
                                    <FormControl
                                        fieldName="primaryCountry"
                                        label="Country"
                                        id="primaryCountry"
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
                            {displayFireLineMessage ? (
                                <div>
                                    <label>
                                        <b style={{ color: "red" }}>{FireLineMessage} </b>
                                    </label>
                                </div>
                            ) : (
                                <div></div>
                            )}
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
                                                return value.PrimaryBusinessType !== "" && value.PrimaryBusinessType !== null && value.PrimaryBusinessType !== undefined? (
                                                    <option
                                                        value={value.PrimaryBusinessType}
                                                        selected={ind == 1 ? true : false}
                                                    >
                                                        {value.PrimaryBusinessType}
                                                    </option>
                                                ):
                                                <></>
                                            })}
                                        </DropDown>
                                    ) : null}
                                </div>
                                <div className="form-element form-select">
                                    {dropdownVal.length > 0 ? (
                                        <DropDown defaultValue={dropdownVal[1].PrimaryBusinessSubType} fieldName='PrimaryBusSubType' label='Primary Business Sub Type' register={register} errors={errors} lableClass='form-label' activeBold={false} showLabel={true} >
                                            {dropdownVal.map((value, ind) => {
                                                return value.PrimaryBusinessSubType !== "" && value.PrimaryBusinessSubType !== null && value.PrimaryBusinessSubType !== undefined ? (
                                                    <option selected={ind == 1 ? true : false} value={value.PrimaryBusinessSubType === "-select-" ? "" : value.PrimaryBusinessSubType}>{value.PrimaryBusinessSubType}</option>
                                                ):
                                                <></>
                                            }
                                            )}
                                        </DropDown>
                                    ) : null}
                                </div>
                                <div class="form-element">
                                    <FormControl
                                        fieldName="primaryZip"
                                        label="Primary Business Zip"
                                        id="zip"
                                        register={register}
                                        errors={errors}
                                        type="text"
                                        showLabel={true}
                                        className="form-field insuredStreetAddress"
                                        required={false}
                                        lableClass="form-label"
                                        activeBold={false}
                                    />
                                </div>
                                <div class="form-element">
                                    <FormControl
                                        fieldName="busStartYear"
                                        label="Business Start Year"
                                        id="year"
                                        register={register}
                                        errors={errors}
                                        type="text"
                                        showLabel={true}
                                        className="form-field insuredStreetAddress"
                                        required={false}
                                        lableClass="form-label"
                                        activeBold={false}
                                    />
                                </div>
                                <div class="form-element">
                                    <FormControl
                                        fieldName="NoofEmployees"
                                        label="No of Employees"
                                        id="employee"
                                        register={register}
                                        errors={errors}
                                        type="text"
                                        showLabel={true}
                                        className="form-field insuredStreetAddress"
                                        required={false}
                                        lableClass="form-label"
                                        activeBold={false}
                                    />
                                </div>
                                <div class="form-element">
                                    <FormControl
                                        fieldName="TotalSales"
                                        label="Total Sales"
                                        id="sales"
                                        register={register}
                                        errors={errors}
                                        type="text"
                                        showLabel={true}
                                        className="form-field insuredStreetAddress"
                                        required={false}
                                        lableClass="form-label"
                                        activeBold={false}
                                    />
                                </div>
                            </div>
                            <div class="fullhead" style={{ color: "#CF0918" }}>
                                Risk Information:
                            </div>
                            <br />
                            <div className="grid2">
                                <div className="form-element form-select">
                                    {dropdownVal.length > 0 ? (
                                        <DropDown defaultValue={dropdownVal[1].PropertyDeductible} fieldName='PropertyDeductible' label='Property Deductible (for all buildings)' register={register} errors={errors} lableClass='form-label' activeBold={false}>

                                            {dropdownVal.map((value, ind) => {
                                                return value.PropertyDeductible !== "" && value.PropertyDeductible !== null && value.PropertyDeductible !== undefined ?(
                                                    <option selected={ind == 1} value={value.PropertyDeductible === "-select-" ? "" : value.PropertyDeductible}>{value.PropertyDeductible}</option>
                                                ):
                                                <></>
                                            }
                                            )}
                                        </DropDown>
                                    ) : null}
                                </div>
                                <div className="form-element form-select">
                                    {dropdownVal.length > 0 ? (
                                        <DropDown defaultValue={dropdownVal[1].ExpandedProperty} fieldName='ExpandedProperty' label='Expanded Property' register={register} errors={errors} lableClass='form-label' activeBold={false}>
                                            {dropdownVal.map((value, ind) => {
                                                return value.ExpandedProperty !== "" && value.ExpandedProperty !== null && value.ExpandedProperty !== undefined ?  (
                                                    <option selected={ind == 1} value={value.ExpandedProperty === "-select-" ? "" : value.ExpandedProperty}>{value.ExpandedProperty}</option>
                                                ) :
                                                    <></>
                                            }
                                            )}
                                        </DropDown>
                                    ) : null}
                                </div>
                            </div>
                            <div class="fullhead" style={{ color: "#CF0918" }}>
                                Add Location:
                            </div>
                            <br />
                            <AddLocationForm
                                openModal={openModal}
                                modalState={modalState}
                                setModalState={setModalState}
                                locationState={locationState}
                                street={street}
                                city={city}
                                zip={zip}
                                description={description}
                            />
                            <br />
                            <div class="fullhead" style={{ color: "#CF0918" }}>
                                Add Building:
                            </div>
                            <br />
                            <AddBuildingForm
                                openModal={openModal}
                                modalState={modalState}
                                setModalState={setModalState}

                            />
                        </form>
                        <br />
                    </div>
                </div>
                <br />
                <div style={{marginLeft:'20px'}}>
                    <button
                        onClick={handleBack}
                        className="btn blue"
                        style={{ marginRight: "70%" }}
                    >
                        Cancel
                    </button>
                    <button
                        onClick={handleSubmit(onSubmit)}
                        type="submit"
                        className="btn blue"
                        // style={{marginLeft:'70%'}}
                    >
                        Quick Indication
                    </button>
                </div>
            </div>
        );
    };
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

export default PropertyQuote;
