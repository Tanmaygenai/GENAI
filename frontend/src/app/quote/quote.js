import React, { useEffect, useState } from 'react';
import { useForm } from "react-hook-form"
import Layout from '../../layout/Layout';
import FormControl from '../../components/formcontrol/FormControl';
import DateInput from '../../components/dateinput/DateInput';
import AutoComplete from "react-google-autocomplete";
import ISOService from '../services/ISOService';
import CryptoJS from "crypto-js";
import API_Headers from '../../API_Headers';
import DropDown from '../../components/dropdown/DropDown';
import Papa from 'papaparse';
import dropdown_val from '../../dummydata/dropdown_data.csv';

const Quote = () => {
    const { register, handleSubmit, reset, formState: { errors }, setValue } = useForm();
    const GOOGLE_MAPS_API_KEY = "AIzaSyDCttdJ2FGaxhCc5hkh9vOzRWTGCPy_x-o";
    const secretPass = process.env.REACT_APP_Secret_Key;
    const [headers, setHeaders] = useState([])
    const [dropdownVal, setDropdownVal] = useState([]);
    const [businessAddress, setBusinessAddress] = useState({ address1: "", address2: "", postcode: "", state: "", city: "", country: "" })
    const [primaryBusinessAddress, setPrimaryBusinessAddress] = useState({ address1: "", address2: "", postcode: "", state: "", city: "", country: "" })
    const [displayFireLineMessage, setDisplayFireLineMessage] = useState(false);
    const [FireLineMessage, setFireLineMessage] = useState("");

    var commonConfig = { delimiter: "," };
    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

    useEffect(() => {
        Papa.parse(
            dropdown_val,
            {
                ...commonConfig,
                header: true,
                download: true,
                complete: (result) => {
                    setDropdownVal(result.data)
                }
            }
        );
    }, [])

    function fillInAddress(place, e) {
        e.name === "MailingAddress" ?
            setBusinessAddress({ ...businessAddress, address1: "", address2: "", postcode: "", city: "", state: "", country: "" })
            : setPrimaryBusinessAddress({ ...primaryBusinessAddress, address1: "", address2: "", postcode: "", city: "", state: "", country: "" })
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
                        state = component.short_name
                        break;
                    }
                    case "country":
                        country = component.long_name
                        break;
                }
            }
        }
        if (e.name === "MailingAddress") {
            setBusinessAddress({ ...businessAddress, address1: address1, address2: address2, postcode: postcode, city: city, state: state, country: country })
            setValue("insuredAddress", address1)
            setValue("busCity", city)
            setValue("busState", state)
            setValue("busPostalCode", postcode)
            setValue("busCountry", country)

        }
        e.value = address1
        if (e.name === "PrimaryBusinessAddress") {
            setPrimaryBusinessAddress({ ...primaryBusinessAddress, address1: address1, address2: address2, postcode: postcode, city: city, state: state, country: country })
            setValue("insuredAddress", address1)
            setValue("primaryCity", city)
            setValue("primaryState", state)
            setValue("primaryPostcode", postcode)
            setValue("primaryCountry", country)

        }
    }

    function formatPhoneNo(e) {
        var phone = e.target.value;
        var phoneClone = phone
        phoneClone = phoneClone.split("")
        phoneClone.length == 4 && phoneClone[3] !== "-" && phoneClone.splice(3, 0, "-")
        phoneClone.length == 8 && phoneClone[7] !== "-" && phoneClone.splice(7, 0, "-")
        phoneClone = phoneClone.join("")
        if (phoneClone.length > 11) {
            phoneClone = phoneClone.slice(0, 12)
        }
        e.target.value = phoneClone;
    }

    const handleCheckEligibility = async () => {
        setPrimaryBusinessAddress({ ...primaryBusinessAddress, address1: businessAddress.address1, address2: businessAddress.address2, postcode: businessAddress.postcode, city: businessAddress.city, state: businessAddress.state, country: businessAddress.country })
        setValue("insuredAddress", businessAddress.address1)
        setValue("primaryCity", businessAddress.city)
        setValue("primaryState", businessAddress.state)
        setValue("primaryPostcode", businessAddress.postcode)
        setValue("primaryCountry", businessAddress.country)

        var ISOrequestJson = {
            // 'insuredName': getValues('InsuredName'),
            // 'dba': getValues('dba'),
            // 'effectiveDate': getValues('EffectiveDate'),
            // 'primaryBusinessAddress': businessAddress
        }
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(ISOrequestJson),
            secretPass
        ).toString();
        try {
            const responseData = await ISOService.getFireLineDetails(data1, headers);
            const message = responseData.data;
            if (message != "") {
                setDisplayFireLineMessage(true)
                setFireLineMessage(responseData.data)
            } else {
                setFireLineMessage("")
                setDisplayFireLineMessage(false)
            }
        }
        catch (Error) {
            alert(Error);
        }
    }
    return (
        <>
            <Layout TabName='New Quote' BreadCrum={['My Dashboard', 'Quick Quote']} sidebarQQSopen={true}>
                <div class="coverContainer">
                    <div class="commonform_box">
                        <div class="inf">
                            <form class="allForm" >
                                <div class="fullhead" style={{ color: '#CF0918' }}>Insured Information:</div><br />
                                <div class="grid2">
                                    <div class="form-element">
                                        <FormControl fieldName="InsuredName" label='Insured Name' id='insuredName' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                    <div class="form-element">
                                        <FormControl fieldName="dbs" label='DBA' id='dba' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>

                                </div>
                                <div class="grid2">
                                    {/* <DateInput fieldName="Effective Date" register={register} errors={errors} required={true} setValue={setValue} id='effDate' minDate={new Date()} yearRange="-0:+60" /> */}
                                    <DateInput
                                        fieldName="Effective Date" register={register} errors={errors}
                                        required={true} setValue={setValue} id='effDate' minDate={new Date()}
                                        yearRange="-0:+60"
                                    // defaultValue={state.application && state.application.policyInfo.effectiveDt.substring(6, 10) + "-" + state.application.policyInfo.effectiveDt.substring(0, 2) + "-" + state.application.policyInfo.effectiveDt.substring(3, 5)}

                                    />
                                </div>
                                <div class="fullhead" style={{ color: '#CF0918' }}>Mailing Address:</div><br />
                                <div className="grid3">
                                    <div class='form-element'>
                                        <label className='form-label'>Address <b>*</b></label>
                                        <AutoComplete
                                            name="MailingAddress"
                                            apiKey={GOOGLE_MAPS_API_KEY}
                                            // defaultValue={state.application && state.application.insuredInfo.addr[0].addr1}
                                            placeholder=""
                                            className='form-control form-field'
                                            style={{ width: "100%" }}
                                            label="Address"
                                            onPlaceSelected={(e, place) => {
                                                fillInAddress(e, place)
                                            }}
                                            options={{
                                                types: ["(regions)"],
                                                componentRestrictions: { country: ["us", "ca"] },
                                                fields: ["address_components", "geometry"],
                                                types: ["address"]
                                            }}
                                        />
                                    </div>
                                    <div class={businessAddress.city.length === 0 ? 'form-element' : 'form-element has-value'}>
                                        <FormControl fieldName="busCity" label='City' id='city' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                    <div class={businessAddress.state.length === 0 ? 'form-element' : 'form-element has-value'}>
                                        <FormControl fieldName="busState" label='State' id='state' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                    <div class={businessAddress.postcode.length === 0 ? 'form-element' : 'form-element has-value'}>
                                        <FormControl fieldName="busPostalCode" label='Postcode' id='postcode' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                    <div class={businessAddress.country.length === 0 ? 'form-element' : 'form-element has-value'}>
                                        <FormControl fieldName="busCountry" label='Country' id='country' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                </div>
                                <div> <button type="button" className="btn blue" disabled={businessAddress.state === "" ? true : false} onClick={handleCheckEligibility}>Copy to Primary Business Address</button></div><br />
                                <div style={{ color: '#CF0918' }}>Primary Business Address:</div><br />




                                <div className="grid3">
                                    <div class={primaryBusinessAddress.address1.length === 0 ? 'form-element' : 'form-element has-value'}>
                                        <label className='form-label'>Address <b>*</b></label>
                                        <AutoComplete
                                            name="PrimaryBusinessAddress"
                                            // defaultValue={!state.application ? primaryBusinessAddress.address1 : state.application.insuredInfo.addr[0].addr1}
                                            apiKey={GOOGLE_MAPS_API_KEY}
                                            placeholder=""
                                            className='form-control form-field'
                                            style={{ width: "100%" }}
                                            label="Address"
                                            onPlaceSelected={(e, place) => {
                                                fillInAddress(e, place)
                                            }}
                                            options={{
                                                types: ["(regions)"],
                                                componentRestrictions: { country: ["us", "ca"] },
                                                fields: ["address_components", "geometry"],
                                                types: ["address"]
                                            }}
                                        />
                                    </div>
                                    <div class={primaryBusinessAddress.city.length === 0 ? 'form-element' : 'form-element has-value'}>
                                        <FormControl fieldName="primaryCity" label='City' id='primaryCity'
                                            register={register} errors={errors}
                                            type="text" showLabel={true} className="form-field" required={true}
                                            lableClass='form-label' activeBold={true}
                                        />
                                    </div>
                                    <div class={primaryBusinessAddress.state.length === 0 ? 'form-element' : 'form-element has-value'}>
                                        <FormControl fieldName="primaryState" label='State' id='primaryState'
                                            register={register} errors={errors}
                                            type="text" showLabel={true} className="form-field" required={true}
                                            lableClass='form-label' activeBold={true}
                                        />
                                    </div>
                                    <div class={primaryBusinessAddress.postcode.length === 0 ? 'form-element' : 'form-element has-value'}>
                                        <FormControl fieldName="primaryPostcode" label='Postcode' id='primaryPostcode'
                                            register={register} errors={errors} type="text"
                                            showLabel={true} className="form-field" required={true} lableClass='form-label'
                                            activeBold={true}
                                        />
                                    </div>
                                    <div class={primaryBusinessAddress.country.length === 0 ? 'form-element' : 'form-element has-value'}>
                                        <FormControl fieldName="primaryCountry" label='Country' id='primaryCountry'
                                            register={register} errors={errors} type="text"
                                            showLabel={true} className="form-field" required={true} lableClass='form-label'
                                            activeBold={true}
                                        />
                                    </div>
                                </div>
                                {
                                    displayFireLineMessage ?
                                        <div><label><b style={{ color: 'red' }}>{FireLineMessage} </b></label></div>
                                        : <div></div>
                                }
                                <div class="fullhead" style={{ color: '#CF0918' }}>Business Information:</div><br />
                                <div className='grid3'>
                                    <div className="form-element form-select">
                                        <DropDown fieldName='PrimaryBusType' label='Primary Business Type' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                                            {/* {drop_data.PrimaryBusinessType.map((value, ind) => (
                                <option value={value}>{value}</option>
                            ))} */}
                                            {dropdownVal.map((value, ind) => {
                                                return value.PrimaryBusinessType.length !== 0 ? (
                                                    <option value={value.PrimaryBusinessType === "-select-" ? "" : value.PrimaryBusinessType}>{value.PrimaryBusinessType}</option>
                                                ) :
                                                    <></>
                                            }
                                            )}
                                        </DropDown>
                                    </div>
                                    <div className="form-element form-select">
                                        <DropDown fieldName='PrimaryBusSubType' label='Primary Business Sub Type' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                                            {/* {drop_data.PrimaryBusinessSubType.map((value, ind) => (
                                <option value={value}>{value}</option>
                            ))} */}
                                            {dropdownVal.map((value, ind) => {
                                                return value.PrimaryBusinessType.length !== 0 ? (
                                                    <option value={value.PrimaryBusinessType === "-select-" ? "" : value.PrimaryBusinessType}>{value.PrimaryBusinessType}</option>
                                                ) :
                                                    <></>
                                            }
                                            )}
                                        </DropDown>
                                    </div>
                                    <div class='form-element'>
                                        <FormControl fieldName="primaryZip" label='Primary Business Zip' id='zip' register={register} errors={errors} type="text" showLabel={true} className="form-field insuredStreetAddress" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                    <div class='form-element'>
                                        <FormControl fieldName="busStartYear" label='Business Start Year' id='year' register={register} errors={errors} type="text" showLabel={true} className="form-field insuredStreetAddress" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                    <div class='form-element'>
                                        <FormControl fieldName="NoofEmployees" label='No of Employees' id='employee' register={register} errors={errors} type="text" showLabel={true} className="form-field insuredStreetAddress" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                    <div class='form-element'>
                                        <FormControl fieldName="TotalSales" label='Total Sales' id='sales' register={register} errors={errors} type="text" showLabel={true} className="form-field insuredStreetAddress" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                    <div className='form-element form-select'>
                                        <DropDown fieldName='BusinessYears' label='How long you have been in buisiness?' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                            {/* {drop_data.BusinessYears.map((value, ind) => (
                                <option value={value.replace(/[ (/,.?'")$&-]/gi, "")}>{value}</option>                            
                                ))} */}
                                            {dropdownVal.map((value, ind) => {
                                                return value.BusinessYears.length !== 0 ? (
                                                    <option value={value.BusinessYears === "-select-" ? "" : value.BusinessYears}>{value.BusinessYears}</option>
                                                ) :
                                                    <></>
                                            }
                                            )}
                                        </DropDown>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </Layout>
        </>
    );
}

export default Quote;
