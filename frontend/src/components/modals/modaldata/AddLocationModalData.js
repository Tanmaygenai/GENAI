import React, { useEffect, useState } from 'react'
import FormControl from '../../formcontrol/FormControl';
import AutoComplete from "react-google-autocomplete";
import DropDown from '../../dropdown/DropDown';

const AddLocationModalData = ({register, errors, setValue, modalState}) => {
  const [locationState, setLocationState] = useState({ address1: "", address2: "", postcode: "", state: "", city: "", country: "" })
  const GOOGLE_MAPS_API_KEY = "AIzaSyDCttdJ2FGaxhCc5hkh9vOzRWTGCPy_x-o";
  const [effCity, setEffCity] = useState("")
  const [effState, setEffState] = useState("")
  const [effZipCode, setEffZipCode] = useState("")
  const [effCountry, setEffCountry] = useState("")
  const [state, setState] = useState("")
  useEffect(()=>{
      if(modalState.LocationData && modalState.editIndex!==null){  
        setValue('LocationDesc', modalState.LocationData[modalState.editIndex].LocationDesc)
        setValue('Address',modalState.LocationData[modalState.editIndex].StreetName)
        setValue('City',modalState.LocationData[modalState.editIndex].City)
        setValue('zip_code',modalState.LocationData[modalState.editIndex].PostalCode)
        setValue('State',modalState.LocationData[modalState.editIndex].State)
        setValue('country',modalState.LocationData[modalState.editIndex].Country)
      }
  },[modalState.editIndex])

  useEffect(()=>{
    setLocationState({ ...locationState, address1: "", address2: "", postcode: "", city: "", state: "", country: "" })
  },[])

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
      setValue('Address',address1)
      setValue('zip_code',postcode)
      setValue('City',city)
      setValue('State',state)
      setValue('country',country)
      e.value = address1
    }

  return (
      <div className="grid1">
          <div className="form-element">
              <FormControl fieldName="LocationDesc" label="Location Description" defaultValue={modalState.editIndex === null ? "" : modalState.LocationData[modalState.editIndex].LocationDesc} register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
          </div>
          <div class="grid1">
                <div class='form-element'>
                  <label className='form-label'>Address <b>*</b></label>
                  <AutoComplete
                    apiKey={GOOGLE_MAPS_API_KEY}
                    placeholder=""
                    fieldName="Address"
                    id="Address"
                    className='form-control form-field'
                    style={{ width: "100%" }}
                    label="Address"
                    onPlaceSelected={(e, place) => {
                      fillInAddress(e, place)
                    }}
                    defaultValue={modalState.editIndex === null ? "" : modalState.LocationData[modalState.editIndex].StreetName}
                    options={{
                      types: ["(regions)"],
                      componentRestrictions: { country: ["us", "ca"] },
                      fields: ["address_components", "geometry"],
                      types: ["address"]
                    }}
                  />
                </div>
                <div className="grid3">
                  <div className='form-element has-value'>
                    <FormControl fieldName="City" label='City' id='city' defaultValue={modalState.editIndex === null ? "" : modalState.LocationData[modalState.editIndex].City} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                  </div>
                  <div className='form-element has-value'>
                    <FormControl fieldName="State" label='State' id='state' defaultValue={modalState.editIndex === null ? "" : modalState.LocationData[modalState.editIndex].State} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                  </div>
                  <div className='form-element has-value'>
                    <FormControl fieldName="zip_code" label='Postcode' id='postcode' defaultValue={modalState.editIndex === null ? "" : modalState.LocationData[modalState.editIndex].PostalCode} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                  </div>
                </div>
                <div className='form-element has-value'>
                  <FormControl fieldName="country" label='Country' id='country' defaultValue={modalState.editIndex === null ? "" : modalState.LocationData[modalState.editIndex].Country} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                </div>
              </div>
      </div>
  );
};

export default AddLocationModalData;