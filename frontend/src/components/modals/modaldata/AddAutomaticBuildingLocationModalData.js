import React, { useEffect, useState } from 'react'
import { drop_data } from '../../../dummydata/data';
import DropDown from '../../dropdown/DropDown';
import FormControl from "../../formcontrol/FormControl"
import Papa from 'papaparse';
import dropdown_val from '../../../dummydata/commercialProperty.csv';
const AddAutomaticBuildingLocationModalData = ({ register, errors, setValue, modalState }) => {
 

  const [defaultValues, setDefaultValues] = useState({
    ProtectiveDevices: '',
    ConstructionType: '',
    Sprinklered: '',
    RoofType: '',
    BuildingLimit: '',
    BuildingDeductible: '',
    BusinessPersonalPropertyLimit: '',
    BuildingDeductible: '',
  })
  function validateYearBuilt(e) {
    var enteredNumber = e.target.value;
    const re = /[0-9]+/g;
    // if value is not blank, then test the regex
    if (enteredNumber === '' || re.test(enteredNumber)) {
      if (enteredNumber.length > 4) {
        enteredNumber = enteredNumber.slice(0, 4)
      }
    } else {
      enteredNumber = ''
    }
    e.target.value = enteredNumber
  }

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
         
          const newData=result.data
          if (modalState.editIndex == null) {
            setDefaultValues({
              ...defaultValues,
              ProtectiveDevices: newData[1].ProtectiveDevices,
              ConstructionType: newData[1].ConstructionType,
              Sprinklered: newData[1].Sprinklered,
              RoofType: newData[1].RoofType,
              BuildingLimit: newData[1].BuildingLimits,
              BuildingDeductible: newData[1].BuildingDeductible,
            })
          }
          const updatedNewData = newData.map((obj, index) => {
            return Object.fromEntries(
              Object.entries(obj).map(([key, value]) => [key.trim(), value.trim().charAt(0).toUpperCase() + value.trim().slice(1).toLowerCase()])
            );
          })
          setDropdownVal(updatedNewData);
          
        }
      }
    );
  }, [])

  useEffect(() => {
    if (dropdownVal.length > 0) {
        setValue('BuildingLimit', dropdownVal[1].BuildingLimits)
        setValue('BuildingDeductible', dropdownVal[1].BuildingDeductible)
        setValue('BusinessPersonalPropertyDeductible', dropdownVal[1].BuildingDeductible)
        setValue('BusinessPersonalPropertyLimit', dropdownVal[1].BuildingLimits)
    }
}, [dropdownVal, setValue])

  useEffect(() => {
    if (modalState.BuildingLocData && modalState.editIndex !== null && dropdownVal.length > 0) {
      
      let newState = modalState.BuildingLocData[modalState.editIndex]
      newState = Object.fromEntries(
        Object.entries(newState).map(([key, value]) => [key.trim(), value.trim().charAt(0).toUpperCase() + value.trim().slice(1).toLowerCase()])
      );
      let obj = { ...defaultValues }
      if (newState.ProtectiveDevices == "") {
        obj = { ...obj, ProtectiveDevices: dropdownVal[1].ProtectiveDevices }
        setValue('ProtectiveDevices', dropdownVal[1].ProtectiveDevices)
      } else {
        obj = {
          ...obj,
          ProtectiveDevices: newState.ProtectiveDevices,
        }
        setValue('ProtectiveDevices', newState.ProtectiveDevices)
      }
      if (newState.ConstructionType == "") {
        obj = { ...obj, ConstructionType: dropdownVal[1].ConstructionType }
        setValue('ConstructionType', dropdownVal[1].ConstructionType)
      } else {
        obj = {
          ...obj,
          ConstructionType: newState.ConstructionType,
        }
        setValue('ConstructionType', newState.ConstructionType)
      }
      if (newState.Sprinklered == "") {
        obj = { ...obj, Sprinklered: dropdownVal[1].Sprinklered }
        setValue('Sprinklered', dropdownVal[1].Sprinklered)
      } else {
        obj = {
          ...obj,
          Sprinklered: newState.Sprinklered,
        }
        setValue('Sprinklered', newState.Sprinklered)
      }
      if (newState.RoofType == "") {
        obj = { ...obj, RoofType: dropdownVal[1].RoofType }
        setValue('RoofType', dropdownVal[1].RoofType)
      } else {
        obj = {
          ...obj,
          RoofType: newState.RoofType,
        }
        setValue('RoofType', newState.RoofType)
      }
      if (newState.BuildingLimit == "") {
        obj = { ...obj, BuildingLimit: dropdownVal[1].BuildingLimits }
        setValue('BuildingLimit', dropdownVal[1].BuildingLimits)
      } else {
        obj = {
          ...obj,
          BuildingLimit: newState.BuildingLimit,
        }
        setValue('BuildingLimit', newState.BuildingLimit)
      }
      if (newState.BuildingDeductible == "") {
        obj = { ...obj, BuildingDeductible: dropdownVal[1].BuildingDeductible }
        setValue('BuildingDeductible', dropdownVal[1].BuildingDeductible)
      } else {
        obj = {
          ...obj,
          BuildingDeductible: newState.BuildingDeductible,
        }
        setValue('BuildingDeductible', newState.BuildingDeductible)
      }
      // if (newState.BusinessPersonalPropertyLimit == "") {
      //   obj = { ...obj, BusinessPersonalPropertyLimit: "" }
      //   setValue('BusinessPersonalPropertyLimit', "")
      // } else {
      //   obj = {
      //     ...obj,
      //     BusinessPersonalPropertyLimit: newState.BusinessPersonalPropertyLimit,
      //   }
      //   setValue('BusinessPersonalPropertyLimit', newState.BusinessPersonalPropertyLimit)
      // }
      if (newState.BuildingDeductible == "") {
        obj = { ...obj, BuildingDeductible: dropdownVal[1].BuildingDeductible }
        setValue('BuildingDeductible', dropdownVal[1].BuildingDeductible)
      } else {
        obj = {
          ...obj,
          BuildingDeductible: newState.BuildingDeductible,
        }
        setValue('BuildingDeductible', newState.BuildingDeductible)
      }
      
      setDefaultValues(obj)
      setValue('YearBuilt', newState.YearBuilt)
      setValue('SquareFootage', newState.SquareFootage)
      setValue('EstimatedReplacement', newState.EstimatedReplacement)
    }
  }, [modalState.editIndex, modalState, dropdownVal])

  function handleNumber(e) {
    var enteredNumber = e.target.value;
    const re = /[0-9]+/g;
    // if value is not blank, then test the regex
    if (enteredNumber === '' || re.test(enteredNumber)) {
      if (enteredNumber.length > 6) {
        enteredNumber = enteredNumber.slice(0, 6)
      }
    } else {
      enteredNumber = ''
    }
    e.target.value = enteredNumber
  }

  return (
    <div>
      <div className="grid2">
        <div class="form-element form-select">
          <DropDown fieldName='Location' label='Available Locations' id='Location' register={register} errors={errors} lableClass='form-label' activeBold={true}
            defaultValue={modalState.editIndex === null ? "" : modalState.BuildingLocData[modalState.editIndex].LocationNo} >
            {modalState.LocationData.map((value, ind) => (
              <option value={value.LocationDesc}>{value.LocationDesc}</option>
            ))}
          </DropDown>
        </div>
      </div>

      <div className="grid3">
        {!!defaultValues.ProtectiveDevices.length && (
          <div className='form-element form-select'>
            <DropDown fieldName='ProtectiveDevices' label='Protective Devices' register={register} errors={errors} lableClass='form-label' activeBold={false}
              defaultValue={defaultValues.ProtectiveDevices} >

              {dropdownVal.map((value, ind) => {
                return value.ProtectiveDevices !== "" && value.ProtectiveDevices !== null && value.ProtectiveDevices !== undefined ? (
                  <option selected={defaultValues.ProtectiveDevices == value.ProtectiveDevices ? true : false} value={value.ProtectiveDevices === "-select-" ? "" : value.ProtectiveDevices}>{value.ProtectiveDevices}</option>
                ) :
                  null
              }
              )}
            </DropDown>
          </div>
        )}

        {!!defaultValues.ConstructionType.length && (
          <div className='form-element form-select'>
            <DropDown defaultValue={defaultValues.ConstructionType} fieldName='ConstructionType' label='Construction Type' register={register} errors={errors} lableClass='form-label' activeBold={true}>
              {dropdownVal.map((value, ind) => {
                return value.ConstructionType !== "" && value.ConstructionType !== null && value.ConstructionType !== undefined?(
                  <option selected={defaultValues.ConstructionType == value.ConstructionType ? true : false} value={value.ConstructionType}>{value.ConstructionType}</option>
                ):
                <></>
              }
              )}
            </DropDown>
          </div>
        )}
        {!!defaultValues.Sprinklered.length && (
          <div className='form-element form-select'>
            <DropDown defaultValue={defaultValues.Sprinklered} fieldName='Sprinklered' label='Fire Sprinkler' register={register} errors={errors} lableClass='form-label' activeBold={true}>
              {dropdownVal.map((value, ind) => {
                return value.Sprinklered !== "" && value.Sprinklered !== null && value.Sprinklered !== undefined?(
                  <option selected={defaultValues.Sprinklered == value.Sprinklered ? true : false} value={value.Sprinklered}>{value.Sprinklered}</option>
                ):
                <></>
              }
              )}
            </DropDown>
          </div>
        )}
        {!!defaultValues.RoofType.length && (
          <div className='form-element form-select'>
            <DropDown defaultValue={defaultValues.RoofType} fieldName='RoofType' label='Roof Type' register={register} errors={errors} lableClass='form-label' activeBold={true}>
              {dropdownVal.map((value, ind) => {
                return value.RoofType !== "" && value.RoofType !== null && value.RoofType !== undefined? (
                  <option selected={defaultValues.RoofType == value.RoofType ? true : false} value={value.RoofType}>{value.RoofType}</option>
                ):
                <></>
              }
              )}
            </DropDown>
          </div>
        )}
        <div className='form-element'>
          <FormControl fieldName="YearBuilt" label='Year Built' defaultValue={modalState.editIndex === null ? "" : modalState.BuildingLocData[modalState.editIndex].YearBuilt} id='YearBuilt' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} onChange={(e) => { validateYearBuilt(e) }} />
        </div>
        <div className='form-element'>
          <FormControl fieldName="SquareFootage" label='Square Footage' id='SquareFootage' defaultValue={modalState.editIndex === null ? "" : modalState.BuildingLocData[modalState.editIndex].SquareFootage} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} onChange={(e) => { handleNumber(e) }} />
        </div>
        <div className='form-element'>
          <FormControl fieldName="EstimatedReplacement" label='Estimated Replacement Cost Value' defaultValue={modalState.editIndex === null ? "" : modalState.BuildingLocData[modalState.editIndex].EstimatedReplacement} id='EstimatedReplacement' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={false} lableClass='form-label' activeBold={false} onChange={(e) => { handleNumber(e) }} />
        </div>
        <div></div><div></div>
        <label className='form-label' style={{ color: '#CF0918' }}><b>Coverage Details *</b></label>
        <div></div><div></div>
        <div className='form-element'>
          <label className='form-label' style={{ color: '#CF0918' }}><b>Building Coverage</b></label>
        </div>
        {!!defaultValues.BuildingLimit.length && (
          <div className='form-element form-select'>
            <DropDown defaultValue={defaultValues.BuildingLimit} fieldName='BuildingLimit' label='Building Limit' register={register} lableClass='form-label' activeBold={false} required={false}>
              {dropdownVal.map((value, ind) => {
                return value.BuildingLimits !== "" && value.BuildingLimits !== null && value.BuildingLimits !== undefined? (
                  <option selected={defaultValues.BuildingLimit == value.BuildingLimits ? true : false} value={value.BuildingLimits}>{value.BuildingLimits}</option>
                ):
                <></>
              }
              )}
            </DropDown>
          </div>
        )}
        {!!defaultValues.BuildingDeductible.length && (
          <div className='form-element form-select'>
            <DropDown defaultValue={defaultValues.BuildingDeductible} fieldName='BuildingDeductible' label='Building Deductible' register={register} lableClass='form-label' activeBold={false} required={false}>
              {dropdownVal.map((value, ind) => {
                return value.BuildingDeductible !== "" && value.BuildingDeductible !== null && value.BuildingDeductible !== undefined? (
                  <option selected={defaultValues.BuildingDeductible == value.BuildingDeductible ? true : false} value={value.BuildingDeductible}>{value.BuildingDeductible}</option>
                ):
                <></>
              }
              )}
            </DropDown>
          </div>
        )}
        <div className='form-element'>
          <label className='form-label' style={{ color: '#CF0918' }}><b>Business Personal Property</b></label>
        </div>
        {!!defaultValues.BuildingLimit.length && (
          <div className='form-element form-select'>
            <DropDown defaultValue={defaultValues.BuildingLimit} fieldName='BusinessPersonalPropertyLimit' label='Business Personal Property Limit' register={register} lableClass='form-label' activeBold={false} required={false}>
              {dropdownVal.map((value, ind) => {
                return value.BuildingLimits !== "" && value.BuildingLimits !== null && value.BuildingLimits !== undefined? (
                  <option selected={defaultValues.BuildingLimit == value.BuildingLimits ? true : false} value={value.BuildingLimits}>{value.BuildingLimits}</option>
                ):
                <></>
              }
              )}
            </DropDown>
          </div>
        )}
        {!!defaultValues.BuildingDeductible.length && (
          <div className='form-element form-select'>
            <DropDown defaultValue={defaultValues.BuildingDeductible} fieldName='BusinessPersonalPropertyDeductible' label='Business Personal Property Deductible' register={register} lableClass='form-label' activeBold={false} required={false}>
              {dropdownVal.map((value, ind) => {
                return value.BuildingDeductible !== "" && value.BuildingDeductible !== null && value.BuildingDeductible !== undefined? (
                  <option selected={defaultValues.BuildingDeductible == value.BuildingDeductible ? true : false} value={value.BuildingDeductible}>{value.BuildingDeductible}</option>
                ):
                <></>
              }
              )}
            </DropDown>
          </div>
        )}

      </div>
    </div>
  );
};

export default AddAutomaticBuildingLocationModalData;
