import React, { useEffect, useState } from 'react'
import { useForm } from "react-hook-form"
import { useHistory } from 'react-router-dom';
import Layout from "../../layout/Layout"
import FormControl from "../../components/formcontrol/FormControl"
import DateInput from "../../components/dateinput/DateInput"
import TextArea from "../../components/textarea/TextArea"
import ReportLossService from '../services/ReportLossService';
import classNames from 'classnames';
import $ from "jquery"
import { fileFunction, formFunctions } from '../../utility/jqueryFunctions';
import DropDown from '../../components/dropdown/DropDown';
import { drop_data } from '../../dummydata/data';
//import ReportLossCode from './ReportLossCode';
import { loss_cause_code } from '../../dummydata/LossCauseCodes';
import AutoComplete from "react-google-autocomplete";
import CryptoJS from "crypto-js";
import API_Headers from '../../API_Headers';
import Papa from 'papaparse';
import dropdown_val from '../../dummydata/lossNotice.csv';
const ReportLoss = () => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    const [headers, setHeaders] = useState([])

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
    const { register, handleSubmit, reset, formState: { errors }, setValue } = useForm();
    const [showResults, setShowResults] = React.useState(false);
    const [response, setResponse] = useState({})
    const GOOGLE_MAPS_API_KEY = "AIzaSyDCttdJ2FGaxhCc5hkh9vOzRWTGCPy_x-o";

    const onClick = () => setShowResults(true)
    var noticeNumber = ""
    const history = useHistory();
    const [flag, setFlag] = useState(false)
    const [lossCauseDropDownField, setLossCauseDropDownField] = useState("")
    const [subLossCauseDropDownField, setSubLossCauseDropDownField] = useState("")
    const [prodType, setProdType] = useState('')
    const [lossCauseValue, setLossCauseValue] = useState('')
    const [locationState, setLocationState] = useState({ address1: "", address2: "", postcode: "", state: "", city: "", country: "" })
    const [dropdownVal, setDropdownVal] = useState([]);
    var commonConfig = { delimiter: "," };

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
    function checkPolicyNumber(e) {
        alert(e.target.value)
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

    function formatTime(e) {
        var time = e.target.value;
        var timeClone = time
        timeClone = timeClone.split("")
        timeClone.length == 3 && timeClone[2] !== ":" && timeClone.splice(2, 0, ":")
        timeClone.length == 6 && timeClone[5] !== "-" && timeClone.splice(5, 0, ":")
        timeClone = timeClone.join("")
        if (timeClone.length === 8) {
            timeClone = timeClone.slice(0, 8)
            timeClone = timeClone + " PDT"
        }
        if (timeClone.length > 12) {
            timeClone = timeClone.slice(0, 12)
        }
        e.target.value = timeClone;
    }
    function fillInAddress(place, e) {
        if (e.name === "insuredAddress") {
            setLocationState({ ...locationState, address1: "", address2: "", postcode: "", state: "", city: "", country: "" })
            setValue("insuredAddress", "")
        }
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
        e.name === "insuredAddress" &&
            setLocationState({ ...locationState, address1: address1, address2: address2, postcode: postcode, city: city, state: state, country: country })
            setValue("insuredAddress", address1)
            setValue("insuredCity", city)
            setValue("insuredState", state)
            setValue("insuredZip", postcode)
            setValue("country", country)
            e.value = address1
    }
    const onSubmit = async (data) => {
        var newData = {...data,policyNumber: data.policyNumber.trim()}
        setResponse(newData)
        reset();
        try {
            const data1 = CryptoJS.AES.encrypt(
                JSON.stringify(newData),
                secretPass
              ).toString();
            const responseData = await ReportLossService.reportLoss(data1, headers);
            noticeNumber = responseData.data;
            history.push({ pathname: "/reportLossSuccessMsg/" + noticeNumber, state: noticeNumber });
        }
        catch (error) {
            if (error == 'Error: Network Error') {
                history.push("/SystemMaintenance");
            }
            else {
                alert(error.response.data.error);
            }
        }
    }
    useEffect(() => {
        const loggedInUser = sessionStorage.getItem("userName");
        if (!loggedInUser) {
            history.push("/");
        }
        $('#policyNo').on('change', function () {
        });
        $('#autoClaimSelect').on('change', function () {
            setFlag($(this).val());
        });
        $('#productType').on('change', function () {
            setProdType($(this).val());
            setLossCauseValue('');
            setSubLossCauseDropDownField('')
            $(this).val() === 'Commercial Auto' ? setLossCauseDropDownField('CALossCauseCode')
                : $(this).val() === 'Package' ? setLossCauseDropDownField('PackageLossCauseCode')
                    : $(this).val() === 'Commercial Excess Liability' ? setLossCauseDropDownField('ExcessLossCauseCode')
                        : setLossCauseDropDownField('')
        });


        $('#lossCause').on('change', function () {
            setLossCauseValue($(this).val());
            $(this).val() === 'Cargo/Inland Marine' ? setSubLossCauseDropDownField('Cargo')
                : $(this).val() === 'Collision - Insured Caused' || $(this).val() === 'Collision - OP Caused' ? setSubLossCauseDropDownField('Collision')
                    : $(this).val() === 'Debris/Rock - Insured Caused' ? setSubLossCauseDropDownField('Debris')
                        : $(this).val() === 'Vehicle vs. Pedestrian' ? setSubLossCauseDropDownField('Vehicle')
                            : $(this).val() === 'Advert' ? setSubLossCauseDropDownField('Advertising')
                                : $(this).val() === 'Atmospheric' ? setSubLossCauseDropDownField('Atmospheric')
                                    : $(this).val() === 'Biological' ? setSubLossCauseDropDownField('BiologicalHazards')
                                        : $(this).val() === 'Breach' ? setSubLossCauseDropDownField('BreachofContract')
                                            : $(this).val() === 'Climate' ? setSubLossCauseDropDownField('ClimatologicalHazard')
                                                : $(this).val() === 'Collapse' ? setSubLossCauseDropDownField('Collapse')
                                                    : $(this).val() === 'Contamination' ? setSubLossCauseDropDownField('Contamination')
                                                        : $(this).val() === 'CrimePers' ? setSubLossCauseDropDownField('Crime - Personal')
                                                            : $(this).val() === 'CrimeProp' ? setSubLossCauseDropDownField('Crime - Property')
                                                                : $(this).val() === 'Cyber' ? setSubLossCauseDropDownField('Cyber')
                                                                    : $(this).val() === 'Vehicles' ? setSubLossCauseDropDownField('Damage Caused by Vehicles')
                                                                        : $(this).val() === 'Decay' ? setSubLossCauseDropDownField('Decay')
                                                                            : $(this).val() === 'Deterioration' ? setSubLossCauseDropDownField('Deterioration/Hidden or Latent Defect')
                                                                                : $(this).val() === 'Discrimination' ? setSubLossCauseDropDownField('Discrimination')
                                                                                    : $(this).val() === 'Defects' ? setSubLossCauseDropDownField('Defects')
                                                                                        : ($(this).val() === 'Earthquake' && { prodType } === 'Package') ? setSubLossCauseDropDownField('EarthquakePkg')
                                                                                            : $(this).val() === 'ElecDisturbance' ? setSubLossCauseDropDownField('Electrical Disturbance')
                                                                                                : $(this).val() === 'ElecSurge' ? setSubLossCauseDropDownField('Electrical Power Surge')
                                                                                                    : $(this).val() === 'ErrorOmit' ? setSubLossCauseDropDownField('Errors and Omissions')
                                                                                                        : $(this).val() === 'Explosion' ? setSubLossCauseDropDownField('ExplosionPkg')
                                                                                                            : $(this).val() === 'FaillingObject' ? setSubLossCauseDropDownField('Falling Objects')
                                                                                                                : $(this).val() === 'Fire' && { prodType } === 'Package' ? setSubLossCauseDropDownField('FirePkg')
                                                                                                                    : $(this).val() === 'Flood' && { prodType } === 'Package' ? setSubLossCauseDropDownField('FloodPkg')
                                                                                                                        : $(this).val() === 'GlassBreak' && { prodType } === 'Package' ? setSubLossCauseDropDownField('GlassBreakage')
                                                                                                                            : $(this).val() === 'Government' ? setSubLossCauseDropDownField('Government Actions')
                                                                                                                                : $(this).val() === 'Harassment' ? setSubLossCauseDropDownField('Harassment')
                                                                                                                                    : $(this).val() === 'ExplosionIndustrial' ? setSubLossCauseDropDownField('Industrial Accident')
                                                                                                                                        : $(this).val() === 'ProdOps' ? setSubLossCauseDropDownField('Injury from Products and Operations')
                                                                                                                                            : $(this).val() === 'LeakageProtect' ? setSubLossCauseDropDownField('Leakage from Fire Protection Devices')
                                                                                                                                                : $(this).val() === 'Liquor' ? setSubLossCauseDropDownField('Liquor Violation')
                                                                                                                                                    : $(this).val() === 'MarringScratching' ? setSubLossCauseDropDownField('Marring or Scratching')
                                                                                                                                                        : $(this).val() === 'MassMovementDry' ? setSubLossCauseDropDownField('Mass Movement-Dry')
                                                                                                                                                            : $(this).val() === 'MassMovementWet' ? setSubLossCauseDropDownField('Mass Movement-Wet')
                                                                                                                                                                : $(this).val() === 'Mechanical' ? setSubLossCauseDropDownField('Mechanical Breakdown')
                                                                                                                                                                    : $(this).val() === 'MysteryDisappeare' ? setSubLossCauseDropDownField('Mysterious Disappearance')
                                                                                                                                                                        : $(this).val() === 'Neglect' ? setSubLossCauseDropDownField('Neglect')
                                                                                                                                                                            : $(this).val() === 'Nuclear' ? setSubLossCauseDropDownField('Nuclear Hazard')
                                                                                                                                                                                : $(this).val() === 'Other' ? setSubLossCauseDropDownField('Other')
                                                                                                                                                                                    : $(this).val() === 'PersonalInjury' ? setSubLossCauseDropDownField('Personal Injury')
                                                                                                                                                                                        : $(this).val() === 'RiotCivil' ? setSubLossCauseDropDownField('Riot or Civil Commotion')
                                                                                                                                                                                            : $(this).val() === 'Settling' ? setSubLossCauseDropDownField('Settling, Cracking, Shrinking or Expansion')
                                                                                                                                                                                                : $(this).val() === 'Sinkhole' ? setSubLossCauseDropDownField('Sinkhole Collapse')
                                                                                                                                                                                                    : $(this).val() === 'Storm' ? setSubLossCauseDropDownField('Storm')
                                                                                                                                                                                                        : $(this).val() === 'Terrorism' ? setSubLossCauseDropDownField('Terrorism')
                                                                                                                                                                                                            : $(this).val() === 'Transport' ? setSubLossCauseDropDownField('Transport-Accident')
                                                                                                                                                                                                                : $(this).val() === 'Vandalism' ? setSubLossCauseDropDownField('Vandalism')
                                                                                                                                                                                                                    : $(this).val() === 'Volcanic' ? setSubLossCauseDropDownField('Volcanic Eruption')
                                                                                                                                                                                                                        : $(this).val() === 'War' ? setSubLossCauseDropDownField('War and Military Action')
                                                                                                                                                                                                                            : $(this).val() === 'Water' ? setSubLossCauseDropDownField('WaterPkg')
                                                                                                                                                                                                                                : $(this).val() === 'WearTear' ? setSubLossCauseDropDownField('Wear and Tear')
                                                                                                                                                                                                                                    : $(this).val() === 'Wind' ? setSubLossCauseDropDownField('WindPkg')
                                                                                                                                                                                                                                        : $(this).val() === 'WorkerComp' ? setSubLossCauseDropDownField('Workers Compensation and Similar Laws')
                                                                                                                                                                                                                                            : setSubLossCauseDropDownField('')
        });

    })


    return (
        <Layout TabName='REPORT A LOSS' BreadCrum={['My Dashboard', 'Report A Loss']}>
            <div class="coverContainer">
                <div class="commonform_box">
                    <div class="inf">
                        <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                            <div class="grid3">
                                <div class="form-element">
                                    <FormControl fieldName="policyNumber" label='Policy Number' id='policyNo' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                </div>
                                <div className="form-element">
                                    <FormControl fieldName="insuredName" label='Policy Holder&apos; Name' id='policy_holder_name' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                </div>
                            </div>

                            <div class="grid3">

                                <div className='form-element form-select'>
                                    <DropDown fieldName='productType' id='productType' label='Product Type' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                        {/* {drop_data.ProductType.map((value, ind) => (
                                            <option value={value}>{value}</option>
                                        ))} */}
                                        {dropdownVal.map((value, ind) => {
                                            return value.ProductType!== "" && value.ProductType!== null && value.ProductType!== undefined ? (
                                                <option value={value.ProductType === "-select-" ? "" : value.ProductType}>{value.ProductType}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                    </DropDown>
                                </div>
                                {lossCauseDropDownField.length !== 0 &&
                                    <div className='form-element form-select'>
                                        <DropDown defaultValue='' fieldName='lossCause' id='lossCause' label='Loss Cause' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                            {/* {loss_cause_code[lossCauseDropDownField].map((value, ind) => (
                                                <option value={value}>{value}</option>
                                            ))} */}
                                            {dropdownVal.map((value, ind) => {
                                            return value[lossCauseDropDownField]!== "" && value[lossCauseDropDownField]!== null && value[lossCauseDropDownField]!== undefined ? (
                                                <option value={value[lossCauseDropDownField] === "-select-" ? "" : value[lossCauseDropDownField]}>{value[lossCauseDropDownField]}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                        </DropDown>
                                    </div>
                                }
                                {subLossCauseDropDownField.length !== 0 &&
                                    <div className='form-element form-select'>
                                        <DropDown defaultValue='' fieldName='subLossCauseCd' id='subLossCauseCd' label='Sub Loss Cause' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                            {/* {loss_cause_code[subLossCauseDropDownField].map((value, ind) => (
                                                <option value={value}>{value}</option>
                                            ))} */}
                                            {dropdownVal.map((value, ind) => {
                                            return value[subLossCauseDropDownField]!== "" && value[subLossCauseDropDownField]!== null && value[subLossCauseDropDownField]!== undefined ? (
                                                <option value={value[subLossCauseDropDownField] === "-select-" ? "" : value[subLossCauseDropDownField]}>{value[subLossCauseDropDownField]}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                        </DropDown>
                                    </div>
                                }
                            </div>


                            <div class="grid2">
                                <div class="fullhead">Insured&apos;s:</div>
                                <div class="grid1">
                                    <div class="form-element">
                                        <label className='form-label'>Address <b>*</b></label>
                                        {/* adding typehead */}
                                        <AutoComplete
                                            name="insuredAddress"
                                            defaultValue={locationState.address1}
                                            apiKey={GOOGLE_MAPS_API_KEY}
                                            placeholder=""
                                            className='form-control form-field'
                                            style={{ width: "100%" }}
                                            label="Address"
                                            // onKeyUp={(e) => changeAddressManually(e)}
                                            // {...register("insuredAddress", {
                                            //     required: 'Insured Street Address is required',
                                            // })}
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
                                        {errors.insuredAddress &&
                                            <div style={{ width: "100%", "marginTop": "0.25rem", "fontSize": ".875em", "color": "#dc3545" }}>
                                                {errors.insuredAddress.message}
                                            </div>
                                        }
                                    </div>
                                    <div className="grid3">
                                        <div class={locationState.city.length === 0 ? "form-element" : "form-element has-value"}>
                                            <FormControl fieldName="insuredCity" label='City' id='city' defaultValue={locationState.city} register={register} errors={errors} type="text" showLabel={true} className="form-field insuredStreetAddress" required={true} lableClass='form-label' activeBold={true} />
                                        </div>
                                        <div class={locationState.state.length === 0 ? "form-element" : "form-element has-value"}>
                                            <FormControl fieldName="insuredState" label='State' id='insuredState' defaultValue={locationState.state} register={register} errors={errors} type="text" showLabel={true} className="form-field insuredStreetAddress" required={true} lableClass='form-label' activeBold={true} />
                                        </div>
                                        <div class={locationState.postcode.length === 0 ? "form-element" : "form-element has-value"}>
                                            <FormControl fieldName="insuredZip" label='Zip' id='zip' defaultValue={locationState.postcode} register={register} errors={errors} type="text" showLabel={true} className="form-field insuredStreetAddress" required={true} lableClass='form-label' activeBold={true} />
                                        </div>
                                    </div>
                                    <div class={locationState.country.length === 0 ? 'form-element' : 'form-element has-value'}>
                                        <FormControl fieldName="country" label='Country' id='country' defaultValue={locationState.country}
                                            register={register} errors={errors} type="text" showLabel={true} className="form-field"
                                            required={true} lableClass='form-label' activeBold={true}
                                        />
                                    </div>
                                </div>
                            </div>
                            <div class="grid4">
                                <div class="form-element">
                                    <FormControl fieldName="contactName" label='Contact Name' id='Contact Name' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                </div>
                                <div className="form-element">
                                    <FormControl fieldName="primaryPhone" label='Phone (Primary)' id='phone_primary' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} onChange={(e) => { formatPhoneNo(e) }} />
                                </div>
                                <div className="form-element">
                                    <FormControl fieldName="secondaryPhone" label='Phone (Secondary)' id='phone_secondery' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} onChange={(e) => { formatPhoneNo(e) }} />
                                </div>
                            </div>
                            <div class="grid2">
                                <div class="fullhead">Location of Loss:</div>
                            </div>
                            <div>
                                <div class="fullhead">
                                    <div class="form-element form-textarea">
                                        <TextArea fieldName="lossDesc" label='Loss Description (Describe What Happened)' id='long_desc' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                </div>
                            </div>
                            <div class="grid3">
                                <div className='form-element form-select'>
                                    <label className='form-label'> Is Auto Claim </label>
                                    <select id='autoClaimSelect'
                                        name="IsAutoClaim"
                                        size="sm"
                                        {...register('IsAutoClaim', {
                                            required: 'IsAutoClaim is required',
                                        })}
                                        className={classNames(" form-field form-control", {
                                            "is-invalid": errors.IsAutoClaim,
                                        })}
                                    >
                                        {/* <option value="">Select</option>
                                        <option value="Yes" >Yes</option>
                                        <option value="No" >No</option> */}
                                        {dropdownVal.map((value, ind) => {
                                            return value.AutoClaim!== "" && value.AutoClaim!== null && value.AutoClaim!== undefined ? (
                                                <option value={value.AutoClaim === "-select-" ? "" : value.AutoClaim}>{value.AutoClaim}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                    </select>
                                    {errors.IsAutoClaim && (
                                        <div className="invalid-feedback">
                                            {errors.IsAutoClaim.message}
                                        </div>
                                    )}
                                </div>
                            </div>
                            {flag === 'Yes' ?
                                <div class="grid2">
                                    <div class="fullhead">Auto Claim Details:</div>
                                    <div class="form-element">
                                        <FormControl fieldName="vinNumber" label='VIN' id='vinNumber' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                    <div class="form-element">
                                        <FormControl autoComplete="never" fieldName="Year,Make,Model and License Plate Number" label='Year,Make,Model and License Plate Number' maxLength="100" register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                </div>
                                :
                                <>
                                </>
                            }
                            <div class="grid4">
                                <div class="fullhead">Reporter Information</div>
                                <div class="form-element">
                                    <FormControl fieldName="reportedBy" label='Name' id='name' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                </div>
                                <div class="form-element form-select">
                                    <DropDown fieldName='relationshipToInsured' label='Relationship to Insured' id='relations_to_insured' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                        {/* {drop_data.InsuredRelationship.map((value, ind) => (
                                            <option value={value}>{value}</option>
                                        ))} */}
                                        {dropdownVal.map((value, ind) => {
                                            return value.InsuredRelationship!== "" && value.InsuredRelationship!== null && value.InsuredRelationship!== undefined? (
                                                <option value={value.InsuredRelationship === "-select-" ? "" : value.InsuredRelationship}>{value.InsuredRelationship}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                    </DropDown>
                                </div>
                                <DateInput fieldName="reportedDt" label='Reported Date' register={register} errors={errors} required={true} setValue={setValue} id='reportedDate' />

                                <div class="form-element">
                                    <FormControl fieldName="reportedTime" label='Reported Time' id='reported_time' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} onChange={(e) => { formatTime(e) }} />
                                </div>
                                <div class="form-element">
                                    <FormControl fieldName="reporterEmail" label='Email Address' id='email_address' register={register} errors={errors} type="Email" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                </div>
                                <div className='form-element form-select'>
                                    <DropDown fieldName='claimantTypeCode' label='Claimant Type' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                        {/* {drop_data.ClaimantType.map((value, ind) => (
                                            <option value={value}>{value}</option>
                                        ))} */}
                                        {dropdownVal.map((value, ind) => {
                                            return value.ClaimantType!== "" && value.ClaimantType!== null && value.ClaimantType!== undefined ? (
                                                <option value={value.ClaimantType === "-select-" ? "" : value.ClaimantType}>{value.ClaimantType}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                    </DropDown>
                                </div>
                            </div>
                            <div class="form-element-btn">
                                <button type="submit" className='btn blue' id="">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="reportnote">
                    <h4>Call and speak to a Exavalu Claims Representative if you have any concerns or questions</h4>
                    <p><strong>Phone:</strong> <a href="tel:123-456-7890">123-456-7890</a><br />
                        <strong>Toll Free:</strong> <a href="tel:123-456-7890">123-456-7890</a><br />
                        <strong>Fax:</strong> 123-456-7890</p>
                    <p><strong>Email:</strong> <a href="mailto:test@test.com">test@test.com</a></p>
                </div>
            </div>
        </Layout>
    )
}

export default ReportLoss
