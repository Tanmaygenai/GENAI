import React, { useState, useEffect } from 'react'
import $ from 'jquery'
import FormControl from '../../components/formcontrol/FormControl';
import DropDown from '../../components/dropdown/DropDown';
import DateInput from '../../components/dateinput/DateInput';
import { drop_data } from '../../dummydata/data';
import { classCodes } from '../../dummydata/classcodes';
import TypeAHeadForm from '../../components/typeahead/TypeAHeadForm';
import { formFunctions } from '../../utility/jqueryFunctions';
import AutoComplete from "react-google-autocomplete";
import ISOService from '../services/ISOService';
import { useLocation } from 'react-router-dom';
import CryptoJS from "crypto-js";
import API_Headers from '../../API_Headers';
import Papa from 'papaparse';
import dropdown_val from '../../dummydata/commercialAuto.csv';
import classNames from 'classnames';
import useLoader from '../../context/loader'
const GOOGLE_MAPS_API_KEY = "AIzaSyDCttdJ2FGaxhCc5hkh9vOzRWTGCPy_x-o";

const InsuredInformationForm = ({ state, formData, register, errors, TabName, setValue, getValues, modalState, setModalState, control, trigger }) => {
  // To convert the targeted data in to the required string form
  // const [effDate, setEffDate] = useState("")
  const { setLoader } = useLoader()
  const secretPass = process.env.REACT_APP_Secret_Key;
  const [businessAddress, setBusinessAddress] = useState({ address1: "", address2: "", postcode: "", state: "", city: "", country: "" })
  const [primaryBusinessAddress, setPrimaryBusinessAddress] = useState({ address1: "", address2: "", postcode: "", state: "", city: "", country: "" })
  const [displayFireLineMessage, setDisplayFireLineMessage] = useState(false);
  const [FireLineMessage, setFireLineMessage] = useState("");
  const excessStates = ['Arizona', 'California', 'Colorado', 'Nevada', 'Washington']
  const [classGroup, setClassGroup] = useState("")
  const [selectedKey, setSelectedKey] = useState("")
  const [insuredState, setState] = useState("")
  var classCodeData = []
  var classCodeGroup = []
  var keyWords = []
  var enableProducts = []
  const location = useLocation();
  const [dropdownVal, setDropdownVal] = useState([]);
  const [indicationData, setIndicationData] = useState('')
  const [newAddressType, setNewAddressType] = useState('')
  const [savedData, setSavedData] = useState({})
  var commonConfig = { delimiter: "," };
  var indData = [];

    useEffect(() => {
      if (state.policyNumber!== null && state.policyNumber!== undefined) {
          indData = JSON.parse(state.indicationData)
          setIndicationData(indData);
      }        
  }, []);

  useEffect(() => {
    if(!!state.quoteData) {
      const data = JSON.parse(state.quoteData);
      setSavedData(data);
      const insuredInfo = data.application && data?.application['insuredInfo'] && data.application['insuredInfo'];
      if(insuredInfo['commercialName']) {
          setValue('InsuredName', insuredInfo.commercialName)
      }
      if(insuredInfo['dba']) {
          setValue('dba', insuredInfo.dba)
      }
      if(data.application && data?.application['policyInfo'] && data.application['policyInfo']['effectiveDt']) {
          setValue('EffectiveDate', data.application['policyInfo'].effectiveDt)
      }
      if(insuredInfo['insuredPhoneNo']) {
          setValue('insuredPhone', insuredInfo.insuredPhoneNo)
      }
      if(insuredInfo['insuredEmail']) {
          setValue('insuredEmail', insuredInfo.insuredEmail)
      }
      if(insuredInfo.addr[0].city) {
          setValue('busCity', insuredInfo.addr[0].city)
      }
      if(insuredInfo.addr[0].state) {
          setValue('busState', insuredInfo.addr[0].state)
      }
      if(insuredInfo.addr[0].postalCode) {
          setValue('busPostalCode', insuredInfo.addr[0].postalCode)
      }
      if(insuredInfo.addr[0].country) {
          setValue('busCountry', insuredInfo.addr[0].country)
      }
      if(insuredInfo.addr[1].city) {
          setValue('primaryCity', insuredInfo.addr[1].city)
      }
      if(insuredInfo.addr[1].state) {
          setValue('primaryState', insuredInfo.addr[1].state)
      }
      if(insuredInfo.addr[1].postalCode) {
          setValue('primaryPostcode', insuredInfo.addr[1].postalCode)
      }
      if(insuredInfo.addr[0].country) {
          setValue('primaryCountry', insuredInfo.addr[0].country)
      }
    }
  }, [state])
  
  
  useEffect(() => {
    if (state.policyNumber!== null && state.policyNumber!== undefined) {
        setValue('QuoteType', JSON.parse(state.indicationData).application.policyInfo.QuoteType =="Full" ? "Full Application" : "Quick Quote")
        setValue('TermType', JSON.parse(state.indicationData).application.policyInfo.TermType)
        setValue('Gender', JSON.parse(state.indicationData).application.insuredInfo.gender)
        setValue('MaritalStatus', JSON.parse(state.indicationData).application.insuredInfo.maritalStatus)
        setValue('Occupation', JSON.parse(state.indicationData).application.insuredInfo.occupation)
        setValue('AddressType', JSON.parse(state.indicationData).application.locations[0].addressType)
        setValue('busCountryCode', JSON.parse(state.indicationData).application.locations[0].addr.state)
  
    }
  }, [dropdownVal, setValue])
  

  useEffect(() => {
    if(!!Object.keys(formData).length) {
      setBusinessAddress({ ...businessAddress, address1: formData?.insuredAddress, address2: "", postcode: formData?.busPostalCode, city: formData?.busCity, state: state, country: formData?.busCountry })
      setValue("insuredAddress", formData?.insuredAddress)
      setValue("busCity", formData?.busCity)
      setValue("busState", formData?.busState)
      setValue("busPostalCode", formData?.busPostalCode)
      setValue("busCountry", formData?.busCountry)
      trigger("insuredAddress")
      trigger("busCity")
      trigger("busState")
      trigger("busPostalCode")
      trigger("busCountry")
    }

    setLoader(false)
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

  classCodes.map((val, ind) => {
    // TO get the class group from entered key words
    let temp = val["Key Words"].split(", ")
    if (temp.includes(selectedKey)) {
      classCodeGroup.push(val["Class Group"])
    }
    // To convert the keywords from class code json in the form of keywords array
    let temp2 = val["Key Words"].split(", ")
    keyWords.push(...temp2)

    // To get the data of products
    if (val["Class Group"] === classGroup) {
      enableProducts.push(val["Class Group Level"])
    }
  })
  classCodeGroup = new Set(classCodeGroup)
  classCodeGroup = [...classCodeGroup]
  keyWords = new Set(keyWords)
  keyWords = [...keyWords]

  const onSelectKeyWord = (selectedVal = "") => {
    setSelectedKey(selectedVal)
  }

  const onSelectClassGroup = (selectedVal = "") => {
    setClassGroup(selectedVal)
  }

  useEffect(() => {
    $(".classCodeGroupSelection").children().children().focus()
    formFunctions();
  }, [selectedKey])

  useEffect(() => {
    if (TabName === 'Commercial Excess Liability') {
      setValue("insuredAddress", state.application && state.application.insuredInfo.addr[0].addr1 && state.application.insuredInfo.addr[0].addr1)
    } else if (TabName === 'Commercial Auto') {
      setValue("insuredAddress", state.indicationData ? JSON.parse(state.indicationData).application.locations[0].addr.addr1 : state.application && state.application.locations[0].addr.addr1 && state.application.locations[0].addr.addr1)
    } else if (TabName === 'Commercial Property') {
      setValue("insuredAddress", state.application && state.application.insuredInfo.addr[0].addr1 && state.application.insuredInfo.addr[0].addr1)
    }
  }, [])

  state.classCodes ?
    state.classCodes.map((val, ind) => {
      if (val["Class Group"] === state.classGroup && (val["Class Code Level"][state.product] === "Yes")) {
        classCodeData.push(val["Class Code"] + " " + val["Class Name"])
      }
    })
    :
    classCodes.map((val, ind) => {
      classCodeData.push(val["Class Code"] + " " + val["Class Name"])
    })

  const [headers, setHeaders] = useState([])

  useEffect(() => {
    API_Headers().then((val) => setHeaders(val))
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
    let countryCode = ""
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
            sessionStorage.setItem("city", city);
            break;
          }
          case "administrative_area_level_1": {
            state = component.long_name
            countryCode = component.short_name
            break;
          }
          case "country":
            country = component.long_name
            break;
        }
      }
    }
    setState(state)
    if (e.name === "MailingAddress") {
      setBusinessAddress({ ...businessAddress, address1: address1, address2: address2, postcode: postcode, city: city, state: state, country: country })
      setValue("insuredAddress", address1)
      setValue("busCity", city)
      setValue("busState", state)
      setValue("busPostalCode", postcode)
      setValue("busCountry", country)
      setValue("busCountryCode", countryCode ? countryCode : state)
      trigger("insuredAddress")
      trigger("busCity")
      trigger("busState")
      trigger("busPostalCode")
      trigger("busCountry")
      trigger("busCountryCode")
    }
    e.value = address1

    if (e.name === "PrimaryBusinessAddress") {
      setPrimaryBusinessAddress({ ...primaryBusinessAddress, address1: address1, address2: address2, postcode: postcode, city: city, state: state, country: country })
      setValue("insuredAddress", address1)
      setValue("primaryCity", city)
      setValue("primaryState", state)
      setValue("primaryPostcode", postcode)
      setValue("primaryCountry", country)
      setValue("busCountryCode", countryCode ? countryCode : state)
      trigger("insuredAddress")
      trigger("primaryCity")
      trigger("primaryState")
      trigger("primaryPostcode")
      trigger("primaryCountry")
      trigger("busCountryCode")
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
    trigger("insuredAddress")
    trigger("primaryCity")
    trigger("primaryState")
    trigger("primaryPostcode")
    trigger("primaryCountry")
    var ISOrequestJson = {
      'insuredName': getValues('InsuredName'),
      'dba': getValues('dba'),
      'effectiveDate': getValues('EffectiveDate'),
      'primaryBusinessAddress': businessAddress
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
  let classCd = "";
  if (state.application && state.application.commercialPolicyInfo) {
    let classDesc = state.application.commercialPolicyInfo.primaryGLClassCd;
    let arr = classDesc.split(" ");
    classCodes.map((val, ind) => {
      if (val["Class Code"] === arr[0]) {
        classCd = val["Class Group"]
      }
    })
  }

  return (
    <div>
      {TabName === 'Commercial Excess Liability' &&
        <div>
          <div class="grid2">
            <div class="form-element">
              <FormControl fieldName="InsuredName" label='Insured Name' id='insuredName' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} defaultValue={state.application && state.application.insuredInfo.commercialName} />
            </div>
            <DateInput
              fieldName="EffectiveDate" register={register} errors={errors}
              required={true} setValue={setValue} id='effDate' minDate={new Date()}
              yearRange="-0:+60"
              // defaultValue={state.application ? state.application.policyInfo.effectiveDt : location.state.effectiveDt}
              defaultValue={state.application && state.application.policyInfo.effectiveDt}
            />
          </div>
          <div class="grid2">
            <div class="fullhead" style={{ color: '#CF0918' }}>Business Address:</div>
            <div class='form-element'>
              <label className='form-label'>Address <b>*</b></label>
              <AutoComplete
                name="MailingAddress"
                apiKey={GOOGLE_MAPS_API_KEY}
                placeholder=""
                defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.locations[0].addr.addr1 : state.application && state.application.insuredInfo.addr[0].addr1 && state.application.insuredInfo.addr[0].addr1}
                className='form-control form-field'
                style={{ width: "100%" }}
                label="Address"
                setValue={setValue}
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
            <div></div>
            <div className="grid3">
              <div class={businessAddress.city.length === 0 ? 'form-element' : 'form-element has-value'}>
                <FormControl fieldName="busCity" label='City' id='city' defaultValue={state.application && state.application.insuredInfo.addr[0].city && state.application.insuredInfo.addr[0].city} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
              </div>
              <div class={businessAddress.state.length === 0 ? 'form-element' : 'form-element has-value'}>
                <FormControl fieldName="busState" label='State' id='state' defaultValue={state.application && state.application.insuredInfo.addr[0].state && state.application.insuredInfo.addr[0].state} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
              </div>
              <div class={businessAddress.postcode.length === 0 ? 'form-element' : 'form-element has-value'}>
                <FormControl fieldName="busPostalCode" label='Postcode' id='postcode' defaultValue={state.application && state.application.insuredInfo.addr[0].postalCode && state.application.insuredInfo.addr[0].postalCode} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
              </div>
            </div>
            <div></div>
            <div className="grid1">
              <div class={businessAddress.country.length === 0 ? 'form-element' : 'form-element has-value'}>
                <FormControl fieldName="busCountry" label='Country' id='country' defaultValue={state.application && state.application.insuredInfo.addr[0].country && state.application.insuredInfo.addr[0].country} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
              </div>
            </div>
          </div>
          <div class="grid2">
            <div>
              <div class="fullhead">Classification:</div>
              <div>
                {selectedKey.length === 0 &&
                  <TypeAHeadForm register={register}
                    typeAheadClass='form-field'
                    errors={errors} control={control}
                    fieldName="class_code_or_Description_key"
                    Data={keyWords}
                    minLength={3}
                    getValues={getValues}
                    lableClass='form-label'
                    label='Class Code or Description with key'
                    activeBold={true}
                    onSelect={(e) => onSelectKeyWord(e)}
                    defaultInputValue={classCd}
                  />
                }
                {
                  selectedKey.length !== 0 &&
                  <TypeAHeadForm register={register}
                    typeAheadClass="classCodeGroupSelection"
                    errors={errors} control={control}
                    fieldName="class_code_or_Description_group"
                    Data={classCodeGroup}
                    minLength={0}
                    getValues={getValues}
                    lableClass='form-label'
                    label='Class Code or Description with group'
                    activeBold={true}
                    placeholder={selectedKey}
                    onSelect={onSelectClassGroup}
                    defaultInputValue={classCd}
                  />
                }
              </div>
            </div>
          </div>
        </div>
      }
      {TabName === 'Commercial Auto' &&
        <div>
          <div class="grid2">
            <div class="form-element">
              <FormControl fieldName="InsuredName" validateFullName={true} label='Insured Name' id='insuredName' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} defaultValue={state.insuredName ? state.insuredName : formData?.InsuredName} />
            </div>
            <div class="form-element">
              <FormControl fieldName="dba" label='DBA' id='dba' register={register} errors={errors} type="text" showLabel={true} className="form-field" lableClass='form-label' activeBold={true} defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuredInfo.dba : formData?.dba} />
            </div>
          </div>
          <div class="grid3">
            {/* <DateInput fieldName="EffectiveDate" register={register} errors={errors} required={true} setValue={setValue} id='effDate' minDate={new Date()} yearRange="-0:+60" /> */}
            <DateInput
              fieldName="EffectiveDate" register={register} errors={errors}
              required={true} setValue={setValue} id='effDate' minDate={new Date()}
              yearRange="-0:+60"
              // defaultValue={state.application && state.application.policyInfo.effectiveDt.substring(6, 10) + "-" + state.application.policyInfo.effectiveDt.substring(0, 2) + "-" + state.application.policyInfo.effectiveDt.substring(3, 5)}
              defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.policyInfo.effectiveDt : state.application && state.application.policyInfo.effectiveDt.substring(0, 4) + "-" + state.application.policyInfo.effectiveDt.substring(5, 7) + "-" + state.application.policyInfo.effectiveDt.substring(8, 10)}
            />
            <div className="form-element form-select">
              {dropdownVal.length > 0 ? (
                  <DropDown
                      defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.policyInfo.QuoteType =="Full" ? "Full Application" : "Quick Quote" : formData?.QuoteType }
                      fieldName="QuoteType"
                      label="Quote Type"
                      //size="sm"
                      register={register}
                      required={true}
                      setValue={setValue}
                      errors={errors}
                      lableClass="form-label"
                      activeBold={false}
                      showLabel={true}
                  >
                      {dropdownVal.map((value, ind) => {
                          return value.QuoteType !== "" && value.QuoteType !== null && value.QuoteType !== undefined ? (
                              <option value={value.QuoteType === "-select-" ? "" : value.QuoteType}>
                                  {value.QuoteType}
                              </option>
                          ):
                          <></>
                          
                      })}
                  </DropDown>
              ) : null}
              {errors.QuoteType && (
                <div className="invalid-feedback">
                  {errors.QuoteType.message}
                </div>
              )}
            </div>
            <div className="form-element form-select">
              {dropdownVal.length > 0 ? (
                  <DropDown
                      defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.policyInfo.TermType : formData?.busPostalCode }
                      fieldName="TermType"
                      label="Term Type"
                      register={register}
                      setValue={setValue}
                      errors={errors}
                      lableClass="form-label"
                      activeBold={false}
                      showLabel={true}
                  >
                      {dropdownVal.map((value, ind) => {
                          return value.TermType !== "" && value.TermType !== null && value.TermType !== undefined ? (
                              <option value={value.TermType === "-select-" ? "" : value.TermType}>
                                  {value.TermType}
                              </option>
                          ):
                          <></>
                          
                      })}
                  </DropDown>
              ) : null}
              {errors.TermType && (
                <div className="invalid-feedback">
                  {errors.TermType.message}
                </div>
              )}
            </div>
          </div>
          <div class="grid2">
            {/* <DateInput fieldName="EffectiveDate" register={register} errors={errors} required={true} setValue={setValue} id='effDate' minDate={new Date()} yearRange="-0:+60" /> */}
            <DateInput
              fieldName="Dob"
              register={register}
              errors={errors}
              minAge={18}
              required={true}
              defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuredInfo.dob : formData?.busPostalCode}
              setValue={setValue}
              id="dateOfBirth"
            />
            <div className="form-element form-select">
              {dropdownVal.length > 0 ? (
                  <DropDown
                      defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuredInfo.gender : formData?.busPostalCode}
                      fieldName="Gender"
                      label="Gender"
                      register={register}
                      setValue={setValue}
                      errors={errors}
                      lableClass="form-label"
                      activeBold={false}
                      showLabel={true}
                  >
                      {dropdownVal.map((value, ind) => {
                          return value.Gender !== "" && value.Gender !== null && value.Gender !== undefined ? (
                              <option value={value.Gender === "-select-" ? "" : value.Gender}>
                                  {value.Gender}
                              </option>
                          ):
                          <></>
                          
                      })}
                  </DropDown>
              ) : null}
              {errors.Gender && (
                <div className="invalid-feedback">
                  {errors.Gender.message}
                </div>
              )}
            </div>
          </div>
          <div class="grid2">
          <div required={true} className="form-element form-select">
              {dropdownVal.length > 0 ? (
                  <DropDown
                      defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuredInfo.maritalStatus : formData?.busPostalCode}
                      fieldName="MaritalStatus"
                      label="Marital Status"
                      register={register}
                      setValue={setValue}
                      errors={errors}
                      lableClass="form-label"
                      activeBold={false}
                      showLabel={true}
                  >
                      {dropdownVal.map((value, ind) => {
                          return value.MaritalStatus !== "" && value.MaritalStatus !== null && value.MaritalStatus !== undefined ? (
                              <option value={value.MaritalStatus === "-select-" ? "" : value.MaritalStatus}>
                                  {value.MaritalStatus}
                              </option>
                          ):
                          <></>
                          
                      })}
                  </DropDown>
              ) : null}
              {errors.MaritalStatus && (
                <div className="invalid-feedback">
                  {errors.MaritalStatus.message}
                </div>
              )}
            </div>
            <div required={true} className="form-element form-select">
              {dropdownVal.length > 0 ? (
                  <DropDown
                      defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuredInfo.occupation : formData?.busPostalCode}
                      fieldName="Occupation"
                      label="Occupation"
                      register={register}
                      setValue={setValue}
                      errors={errors}
                      lableClass="form-label"
                      activeBold={false}
                      showLabel={true}
                  >
                      {dropdownVal.map((value, ind) => {
                          return value.Occupation !== "" && value.Occupation !== null && value.Occupation !== undefined ? (
                              <option value={value.Occupation === "-select-" ? "" : value.Occupation}>
                                  {value.Occupation}
                              </option>
                          ):
                          <></>
                          
                      })}
                  </DropDown>
              ) : null}
              {errors.Occupation && (
                <div className="invalid-feedback">
                  {errors.Occupation.message}
                </div>
              )}
            </div>
          </div>
          <div class="grid2">
          <div className="form-element form-select">
              {dropdownVal.length > 0 ? (
                  <DropDown
                      defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.locations[0].addressType : formData?.busPostalCode}
                      fieldName="AddressType"
                      label="Address Type"
                      register={register}
                      setValue={setValue}
                      errors={errors}
                      lableClass="form-label"
                      activeBold={false}
                      showLabel={true}
                  >
                      {dropdownVal.map((value, ind) => {
                          return value.AddressType !== "" && value.AddressType !== null && value.AddressType !== undefined ? (
                              <option value={value.AddressType === "-select-" ? "" : value.AddressType}>
                                  {value.AddressType}
                              </option>
                          ):
                          <></>
                          
                      })}
                  </DropDown>
              ) : null}
              {errors.AddressType && (
                <div className="invalid-feedback">
                  {errors.AddressType.message}
                </div>
              )}
            </div>
            <div class="fullhead" style={{ color: '#CF0918' }}></div>
            <div class='form-element'>
              <label className='form-label'>Address <b>*</b></label>
              <AutoComplete
                name="MailingAddress"
                apiKey={GOOGLE_MAPS_API_KEY}
                placeholder=""
                defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.locations[0].addr.addr1 : formData?.insuredAddress}
                className='form-control form-field'
                style={{ width: "100%" }}
                label="Address"
                setValue={setValue}
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
            <div></div>
            <div className="grid3">
              <div class={businessAddress.city.length === 0 ? 'form-element' : 'form-element has-value'}>
                <FormControl fieldName="busCity" label='City' id='city' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.locations[0].addr.city : state.application && state.application.locations[0].addr.city && state.application.locations[0].addr.city} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
              </div>
              <div class={businessAddress.state.length === 0 ? 'form-element' : 'form-element has-value'}>
                <FormControl fieldName="busState" label='State' id='state' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.locations[0].addr.state : state.application && state.application.locations[0].addr.state && state.application.locations[0].addr.state} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
              </div>
              <div class={businessAddress.postcode.length === 0 ? 'form-element' : 'form-element has-value'}>
                <FormControl fieldName="busPostalCode" label='Postcode' id='postcode' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.locations[0].addr.postalCode : state.application && state.application.locations[0].addr.postalCode && state.application.locations[0].addr.postalCode} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
              </div>
            </div>
            <div></div>
            <div className="grid1">
              <div class={businessAddress.country.length === 0 ? 'form-element' : 'form-element has-value'}>
                <FormControl fieldName="busCountry" label='Country' id='country' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.locations[0].addr.busCountry : state.application && state.application.locations[0].addr.busCountry && state.application.locations[0].addr.busCountry} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
              </div>
            </div>
          </div>
        </div>
      }
      {TabName === 'Commercial Property' &&
        <div>
          <div class="grid2">
            <div class="form-element">
              <FormControl fieldName="InsuredName" defaultValue={savedData.application && savedData.application.insuredInfo.commercialName} label='Insured Name' id='insuredName' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
            </div>
            <div class="form-element">
              <FormControl fieldName="dba" defaultValue={savedData.application && savedData.application.insuredInfo.dba} label='DBA' id='dba' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={false} lableClass='form-label' activeBold={false} />
            </div>
          </div>
          <div class="grid2"><DateInput fieldName="EffectiveDate" defaultValue={savedData.application && savedData.application.policyInfo.effectiveDt} register={register} errors={errors} required={true} setValue={setValue} id='effDate' minDate={new Date()} yearRange="-0:+60" /></div>
          <div class="grid2">
            <div className="form-element">
              <FormControl fieldName="insuredPhone" defaultValue={savedData.application && savedData.application.insuredInfo.insuredPhoneNo} label='Phone' id='phone_primary' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} onChange={(e) => { formatPhoneNo(e) }} />
            </div>
            <div class="form-element">
              <FormControl fieldName="insuredEmail" defaultValue={savedData.application && savedData.application.insuredInfo.insuredEmail} label='Email Address' id='email_address' register={register} errors={errors} type="Email" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
            </div>
          </div>

          <div style={{ color: '#CF0918' }}>Mailing Address:</div><br />


          <div className="grid3">
            <div class='form-element'>
              <label className='form-label'>Address <b>*</b></label>
              <AutoComplete
                name="MailingAddress"
                apiKey={GOOGLE_MAPS_API_KEY}
                defaultValue={savedData.application && savedData.application.insuredInfo.addr[0].addr1}
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
              <FormControl fieldName="busCity" label='City' id='city' defaultValue={savedData.application && savedData.application.insuredInfo.addr[0].city} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
            </div>
            <div class={businessAddress.state.length === 0 ? 'form-element' : 'form-element has-value'}>
              <FormControl fieldName="busState" label='State' id='state' defaultValue={savedData.application && savedData.application.insuredInfo.addr[0].state} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
            </div>
            <div class={businessAddress.postcode.length === 0 ? 'form-element' : 'form-element has-value'}>
              <FormControl fieldName="busPostalCode" label='Postcode' id='postcode' defaultValue={savedData.application && savedData.application.insuredInfo.addr[0].postalCode} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
            </div>
            <div class={businessAddress.country.length === 0 ? 'form-element' : 'form-element has-value'}>
              <FormControl fieldName="busCountry" label='Country' id='country' defaultValue={savedData.application && savedData.application.insuredInfo.addr[0].country} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
            </div>
          </div>
          <div> <button type="button" className="btn blue" disabled={businessAddress.state === "" ? true : false} onClick={handleCheckEligibility}>Copy to Primary Business Address</button></div><br />


          <div style={{ color: '#CF0918' }}>Primary Business Address:</div><br />




          <div className="grid3">
            <div class={primaryBusinessAddress.address1.length === 0 ? 'form-element' : 'form-element has-value'}>
              <label className='form-label'>Address <b>*</b></label>
              <AutoComplete
                name="PrimaryBusinessAddress"
                defaultValue={!!savedData.application && !!savedData.application.insuredInfo ? savedData.application.insuredInfo.addr[0].addr1 : primaryBusinessAddress.address1 }
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
                defaultValue={savedData.application && savedData.application.insuredInfo.addr[1].city} register={register} errors={errors}
                type="text" showLabel={true} className="form-field" required={true}
                lableClass='form-label' activeBold={true}
              />
            </div>
            <div class={primaryBusinessAddress.state.length === 0 ? 'form-element' : 'form-element has-value'}>
              <FormControl fieldName="primaryState" label='State' id='primaryState'
                defaultValue={savedData.application && savedData.application.insuredInfo.addr[1].state} register={register} errors={errors}
                type="text" showLabel={true} className="form-field" required={true}
                lableClass='form-label' activeBold={true}
              />
            </div>
            <div class={primaryBusinessAddress.postcode.length === 0 ? 'form-element' : 'form-element has-value'}>
              <FormControl fieldName="primaryPostcode" label='Postcode' id='primaryPostcode'
                defaultValue={savedData.application && savedData.application.insuredInfo.addr[1].postalCode} register={register} errors={errors} type="text"
                showLabel={true} className="form-field" required={true} lableClass='form-label'
                activeBold={true}
              />
            </div>
            <div class={primaryBusinessAddress.country.length === 0 ? 'form-element' : 'form-element has-value'}>
              <FormControl fieldName="primaryCountry" label='Country' id='primaryCountry'
                defaultValue={savedData.application && savedData.application.insuredInfo.addr[0].country} register={register} errors={errors} type="text"
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
        </div>

      }
    </div>
  )
}
export default InsuredInformationForm
