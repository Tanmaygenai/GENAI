import React, { useEffect, useState } from 'react'
import { drop_data } from '../../../dummydata/data';
import DropDown from '../../dropdown/DropDown';
import FormControl from "../../formcontrol/FormControl"
import Papa from 'papaparse';
import dropdown_val from '../../../dummydata/commercialProperty.csv';
const AddBuildingLocationModalData = ({ register, errors, setValue, modalState }) => {

 
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
          setDropdownVal(result.data)
        }
      }
    );
  }, [])

  useEffect(() => {
    if (modalState.BuildingLocData && modalState.editIndex !== null) {
      setValue('ProtectiveDevices', modalState.BuildingLocData[modalState.editIndex].ProtectiveDevices)
      setValue('ConstructionType', modalState.BuildingLocData[modalState.editIndex].ConstructionType)
      setValue('Sprinklered', modalState.BuildingLocData[modalState.editIndex].Sprinklered)
      setValue('RoofType', modalState.BuildingLocData[modalState.editIndex].RoofType)
      setValue('YearBuilt', modalState.BuildingLocData[modalState.editIndex].YearBuilt)
      setValue('SquareFootage', modalState.BuildingLocData[modalState.editIndex].SquareFootage)
      setValue('EstimatedReplacement', modalState.BuildingLocData[modalState.editIndex].EstimatedReplacement)
      setValue('BuildingLimit', modalState.BuildingLocData[modalState.editIndex].BuildingLimit)
      setValue('BuildingDeductible', modalState.BuildingLocData[modalState.editIndex].BuildingDeductible)
      setValue('BusinessPersonalPropertyLimit', modalState.BuildingLocData[modalState.editIndex].BusinessPersonalPropertyLimit)
      setValue('BusinessPersonalPropertyDeductible', modalState.BuildingLocData[modalState.editIndex].BusinessPersonalPropertyDeductible)
    }
  }, [modalState.editIndex])


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
        <div className='form-element form-select'>
          <DropDown fieldName='ProtectiveDevices' label='Protective Devices' register={register} errors={errors} lableClass='form-label' activeBold={false}
            defaultValue={modalState.editIndex === null ? "" : modalState.BuildingLocData[modalState.editIndex].ProtectiveDevices} >
            {/* {drop_data.ProtectiveDevices.map((value, ind) => (
              <option value={value}>{value}</option>
            ))} */}
            {dropdownVal.map((value, ind) => {
              return value.ProtectiveDevices !== "" && value.ProtectiveDevices !== null && value.ProtectiveDevices !== undefined ? (
                <option value={value.ProtectiveDevices === "-select-" ? "" : value.ProtectiveDevices}>{value.ProtectiveDevices}</option>
              ) :
                <></>
            }
            )}
          </DropDown>
        </div>

        <div className='form-element form-select'>
          <DropDown fieldName='ConstructionType' label='Construction Type' register={register} errors={errors} lableClass='form-label' activeBold={true}
            defaultValue={modalState.editIndex === null ? "" : modalState.BuildingLocData[modalState.editIndex].ConstructionType}>
            {/* {drop_data.ConstructionType.map((value, ind) => (
              <option value={value}>{value}</option>
            ))} */}
            {dropdownVal.map((value, ind) => {
              return value.ConstructionType !== "" && value.ConstructionType !== null && value.ConstructionType !== undefined? (
                <option value={value.ConstructionType === "-select-" ? "" : value.ConstructionType}>{value.ConstructionType}</option>
              ) :
                <></>
            }
            )}
          </DropDown>
        </div>
        <div className='form-element form-select'>
          <DropDown fieldName='Sprinklered' label='Fire Sprinkler' register={register} errors={errors} lableClass='form-label' activeBold={true}
            defaultValue={modalState.editIndex === null ? "" : modalState.BuildingLocData[modalState.editIndex].Sprinklered}>
            {/* {drop_data.Sprinklered.map((value, ind) => (
              <option value={value}>{value}</option>
            ))} */}
            {dropdownVal.map((value, ind) => {
              return value.Sprinklered !== "" && value.Sprinklered !== null && value.Sprinklered !== undefined? (
                <option value={value.Sprinklered === "-select-" ? "" : value.Sprinklered}>{value.Sprinklered}</option>
              ) :
                <></>
            }
            )}
          </DropDown>
        </div>
        <div className='form-element form-select'>
          <DropDown fieldName='RoofType' label='Roof Type' register={register} errors={errors} lableClass='form-label' activeBold={true}
            defaultValue={modalState.editIndex === null ? "" : modalState.BuildingLocData[modalState.editIndex].RoofType}>
            {/* {drop_data.RoofType.map((value, ind) => (
              <option value={value}>{value}</option>
            ))} */}
            {dropdownVal.map((value, ind) => {
              return value.RoofType !== "" && value.RoofType !== null && value.RoofType !== undefined? (
                <option value={value.RoofType === "-select-" ? "" : value.RoofType}>{value.RoofType}</option>
              ) :
                <></>
            }
            )}
          </DropDown>
        </div>
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
        <div className='form-element form-select'>
          <DropDown fieldName='BuildingLimit' label='Building Limit' register={register} lableClass='form-label' activeBold={false} required={false}
            defaultValue={modalState.editIndex === null ? '' : modalState.BuildingLocData[modalState.editIndex].BuildingLimit}>
            {/* {drop_data.BuildingLimits.map((value, ind) => (
              <option value={value}>{value}</option>
            ))} */}
            {dropdownVal.map((value, ind) => {
              return value.BuildingLimits !== "" && value.BuildingLimits !== null && value.BuildingLimits !== undefined? (
                <option value={value.BuildingLimits === "-select-" ? "" : value.BuildingLimits}>{value.BuildingLimits}</option>
              ) :
                <></>
            }
            )}
          </DropDown>
        </div>
        <div className='form-element form-select'>
          <DropDown fieldName='BuildingDeductible' label='Building Deductible' register={register} lableClass='form-label' activeBold={false} required={false}
            defaultValue={modalState.editIndex === null ? "" : modalState.BuildingLocData[modalState.editIndex].BuildingLimit}>
            {/* {drop_data.BuildingDeductible.map((value, ind) => (
              <option value={value}>{value}</option>
            ))} */}
            {dropdownVal.map((value, ind) => {
              return value.BuildingDeductible !== "" && value.BuildingDeductible !== null && value.BuildingDeductible !== undefined? (
                <option value={value.BuildingDeductible === "-select-" ? "" : value.BuildingDeductible}>{value.BuildingDeductible}</option>
              ) :
                <></>
            }
            )}
          </DropDown>
        </div>
        <div className='form-element'>
          <label className='form-label' style={{ color: '#CF0918' }}><b>Business Personal Property</b></label>
        </div>
        <div className='form-element form-select'>
          <DropDown fieldName='BusinessPersonalPropertyLimit' label='Business Personal Property Limit' register={register} lableClass='form-label' activeBold={false} required={false}>
            {/* {drop_data.BuildingLimits.map((value, ind) => (
              <option value={value}>{value}</option>
            ))} */}
            {dropdownVal.map((value, ind) => {
              return value.BuildingLimits !== "" && value.BuildingLimits !== null && value.BuildingLimits !== undefined? (
                <option value={value.BuildingLimits === "-select-" ? "" : value.BuildingLimits}>{value.BuildingLimits}</option>
              ) :
                <></>
            }
            )}
          </DropDown>
        </div>
        <div className='form-element form-select'>
          <DropDown fieldName='BusinessPersonalPropertyDeductible' label='Business Personal Property Deductible' register={register} lableClass='form-label' activeBold={false} required={false}>
            {/* {drop_data.BuildingDeductible.map((value, ind) => (
              <option value={value}>{value}</option>
            ))} */}
            {dropdownVal.map((value, ind) => {
              return value.BuildingDeductible !== "" && value.BuildingDeductible !== null && value.BuildingDeductible !== undefined? (
                <option value={value.BuildingDeductible === "-select-" ? "" : value.BuildingDeductible}>{value.BuildingDeductible}</option>
              ) :
                <></>
            }
            )}
          </DropDown>
        </div>

      </div>
    </div>
  );
};

export default AddBuildingLocationModalData;