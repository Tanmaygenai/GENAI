import React, { useEffect, useState } from 'react'
import { useForm } from "react-hook-form"
import Layout from "../../layout/Layout"
import { fileFunction, formFunctions } from '../../utility/jqueryFunctions';
import TypeAHeadForm from '../../components/typeahead/TypeAHeadForm';
import { classCodes } from '../../dummydata/classcodes';
import EligibilityCheckService from '../services/EligibilityCheckService';
import { useHistory } from 'react-router-dom';
import { drop_data } from '../../dummydata/data';
import $ from "jquery"
import { Link } from 'react-router-dom';
import AutoComplete from "react-google-autocomplete";
import FormControl from '../../components/formcontrol/FormControl';
import DateInput from "../../components/dateinput/DateInput"

const CommercialUmbrella = () => {
  const { register, handleSubmit, reset, formState: { errors }, setValue, control, getValues } = useForm();
  const [state, setState] = useState("")
  const [classGroup, setClassGroup] = useState("")
  const [selectedKey, setSelectedKey] = useState("")
  const [locationState, setLocationState] = useState({ address1: "", address2: "", postcode: "", state: "", city: "", country: "" })
  const history = useHistory();
  const tpciStates = ['Arizona', 'California']
  const excessStates = ['Arizona', 'California', 'Colorado', 'Nevada', 'Washington']
  const GOOGLE_MAPS_API_KEY = "AIzaSyDCttdJ2FGaxhCc5hkh9vOzRWTGCPy_x-o";
  const [effCity, setEffCity] = useState("")
  const [effState, setEffState] = useState("")
  const [effZipCode, setEffZipCode] = useState("")
  const [effCountry, setEffCountry] = useState("")
  const [classCodeLevel, setClassCodeLevel] = useState({ "Prop": false, "Liab": false, "Pkg": false, "Exc": false })
  const [classCodeProduct, setClassCodeProduct] = useState({ "Excess": false, "CPP": false })
  const [insuredName, setInsuredName] = useState('')
  // State data to use in state typeahead
  var stateData = drop_data.State.map((value, ind) => (value.value))

  // Evant when state is selcted by typeahead
  const onSelectState = (selectedVal = "") => {
    setState(selectedVal);
  }

  var classCodeGroup = []
  var keyWords = []
  var enableProducts = []

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

  var CCLevel = { "Prop": false, "Liab": false, "Pkg": false, "Exc": false }
  var CCProduct = { "Excess": false, "CPP": false }
  const onSelectClassGroup = (selectedVal = "") => {
    setClassGroup(selectedVal)
    classCodes.map((val, ind) => {
      if (selectedVal === val['Class Group']) {
        if (val['Class Code Level'].Exc === "Yes")
          CCLevel.Exc = true
        if (val['Class Code Level'].Liab === "Yes")
          CCLevel.Liab = true
        if (val['Class Code Level'].Pkg === "Yes")
          CCLevel.Pkg = true
        if (val['Class Code Level'].Prop === "Yes")
          CCLevel.Prop = true
        if (val.Product === "Excess")
          CCProduct.Excess = true
        if (val.Product === "CPP")
          CCProduct.CPP = true
      }
    })
    setClassCodeLevel(CCLevel)
    setClassCodeProduct(CCProduct)
  }
  const onSelectKeyWord = (selectedVal = "") => {
    setSelectedKey(selectedVal)
  }

  const onKeyReset = () => {
    setSelectedKey("")
    setClassGroup("")
    setValue("class_code_or_Description_key", "")
    setValue("class_code_or_Description_group", "")
  }

  useEffect(() => {
    const loggedInUser = sessionStorage.getItem("userName");
    $(".classCodeGroupSelection").children().children().focus()

    formFunctions();
  }, [selectedKey])
  var disable;
  (state !== "" && (tpciStates.includes(state) || excessStates.includes(state))) ? disable = false : disable = true
  //----------------------------LOCATION FUNCTIONS -----------------------------------------------------------

  function updateInsuredName(e){
    setInsuredName(e.target.value)
  }
  function fillInAddress(place, e) {

    setLocationState({ ...locationState, address1: "", address2: "", postcode: "", city: "", state: "", country: "" })
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
            setEffZipCode(postcode)
            break;
          }

          case "postal_code_suffix": {
            postcode = `${postcode}-${component.long_name}`;
            //setEffZipCode(postcode)
            break;
          }
          case "locality": {
            city = component.long_name;
            sessionStorage.setItem("city", city);
            setEffCity(city)
            break;
          }
          case "administrative_area_level_1": {
            state = component.long_name
            setEffState(state)
            break;
          }
          case "country":
            country = component.long_name
            setEffCountry(country)
            break;
        }
      }
    }
    setLocationState({ ...locationState, address1: address1, address2: address2, postcode: postcode, city: city, state: state, country: country })
    setState(state)
    e.value = address1
  }

  return (

    <Layout TabName='Commercial Umbrella' BreadCrum={['My Dashboard', 'Quick Quote', 'Commercial Umbrella']}>
      <div class="coverContainer">
        <div class="commonform_box">
          <div class="inf">
            <form class="allForm">
              <div class="grid2">
                <div class="form-element">
                      <FormControl fieldName="InsuredName" label='Insured Name' id='insuredName' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} onChange={(e) => {updateInsuredName(e)}}/>
                  </div>
                <div><DateInput fieldName="Effective Date" register={register} errors={errors} required={true} setValue={setValue} id='effDate' minDate={new Date()} yearRange="-0:+60" /></div>
                <div class="fullhead">Location:</div>
                <div class="grid1">
                  <div class='form-element'>
                    <label className='form-label'>Address <b>*</b></label>
                    <AutoComplete
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
                  <div className="grid3">
                    <div class={locationState.city.length === 0 ? 'form-element' : 'form-element has-value'}>
                      <FormControl fieldName="city" label='City' id='city' defaultValue={locationState.city} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                    </div>
                    <div class={locationState.state.length === 0 ? 'form-element' : 'form-element has-value'}>
                      <FormControl fieldName="state" label='State' id='state' defaultValue={locationState.state} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                    </div>
                    <div class={locationState.postcode.length === 0 ? 'form-element' : 'form-element has-value'}>
                      <FormControl fieldName="postcode" label='Postcode' id='postcode' defaultValue={locationState.postcode} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                    </div>
                  </div>
                  <div class={locationState.country.length === 0 ? 'form-element' : 'form-element has-value'}>
                    <FormControl fieldName="country" label='Country' id='country' defaultValue={locationState.country} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                  </div>
                </div>
                <div></div>
                {state !== "" && disable == true ? <p><strong>Eligible Products:</strong><br />Exavalu is not licensed to provide insurance in the state of {state}</p> : ''}
              </div>
              <br />
              <div class="grid2">
                <div>
                  <div class="fullhead">Classification:</div>
                  <div>
                    {selectedKey.length === 0 &&
                      <TypeAHeadForm register={register}
                        disable={disable}
                        typeAheadClass={state !== "" && disable == true ? 'form-field disbleField' : 'form-field'}
                        errors={errors} control={control}
                        fieldName="class_code_or_Description_key"
                        Data={keyWords}
                        minLength={3}
                        getValues={getValues}
                        lableClass={state !== "" && disable == true ? 'form-label disbleField' : 'form-label'}
                        label='Class Code or Description with key'
                        activeBold={true}
                        onSelect={(e) => onSelectKeyWord(e)}
                      />
                    }
                    {
                      selectedKey.length !== 0 &&
                      <TypeAHeadForm register={register}
                        typeAheadClass="classCodeGroupSelection"
                        disable={disable}
                        errors={errors} control={control}
                        fieldName="class_code_or_Description_group"
                        Data={classCodeGroup}
                        minLength={0}
                        getValues={getValues}
                        lableClass='form-label'
                        label='Class Code or Description with group'
                        activeBold={true}
                        onSelect={onSelectClassGroup}
                        placeholder={selectedKey}
                      />
                    }
                  </div>
                </div>
                <div>
                  <div class="grid2">
                    <div>
                      {
                        selectedKey.length !== 0 &&
                        <div className='form-element'>
                          <div class="fullhead" style={{ visibility: "hidden" }}>reset</div>
                          <button type="button" onClick={onKeyReset} class="btn blue" id="">Reset</button>
                        </div>
                      }
                    </div>
                    {state !== "" && classGroup !== "" &&
                      <div>
                        {disable === false ? <p><strong>Select Product:</strong></p> : ''}
                        <p>
                          <>
                            {
                                enableProducts[0] === "Yes" && excessStates.includes(state) && classCodeLevel.Exc === true && classCodeProduct.Excess === true ?
                                  <Link to={{ pathname: "/excessliability", state: {locationState:locationState, state: state, classGroup: classGroup, classCodes: classCodes, product: "Exc", insuredName: insuredName } }}>
                                    <span className='text-success'>&#10004;</span>
                                    &ensp; Commercial Umbrella<br />
                                  </Link>
                                  : <><span className='text-danger'>&#10006;</span>&ensp; Commercial Umbrella<br /></>
                            }
                          </>
                        </p>
                      </div>
                    }
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </Layout>
  )
}

export default CommercialUmbrella