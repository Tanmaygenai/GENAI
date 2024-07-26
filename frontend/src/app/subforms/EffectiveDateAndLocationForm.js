import React, { useState, useEffect } from 'react'
import DropDown from '../../components/dropdown/DropDown';
import DateInput from '../../components/dateinput/DateInput';
import { drop_data } from '../../dummydata/data';
import { classCodes } from '../../dummydata/classcodes';
import TypeAHeadForm from '../../components/typeahead/TypeAHeadForm';
import API_Headers from '../../API_Headers';
import Papa from 'papaparse';
import dropdown_val from '../../dummydata/excessLiability.csv';

const EffectiveDateAndLocationForm = ({ state, register, errors, TabName, setValue, getValues, control, trigger, formData }) => {
  const classCodeData = []
  const [headers, setHeaders] = useState([])
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
  var product;
  if (TabName === 'Commercial Excess Liability') {
    product = "Exc";
  }
  classCodes ?
    classCodes.map((val, ind) => {
      if (val["Class Group"] === formData.class_code_or_Description_group && (val["Class Code Level"][product] === "Yes")) {
        classCodeData.push(val["Class Code"] + " " + val["Class Name"])
      }
    })
    :
    classCodes.map((val, ind) => {
      classCodeData.push(val["Class Code"] + " " + val["Class Name"])
    })
  useEffect(() => {
    if(!!Object.keys(formData).length) {
      setValue("class_code_or_Description", formData.class_code_or_Description)
      setValue("EffectiveDate", formData.EffectiveDate)
    }

  }, [])



  useEffect(() => {
    API_Headers().then((val) => setHeaders(val))
  }, [])

  return (
    <div>
      {TabName === 'Commercial Excess Liability' &&
        <div>
          <div className='grid2'>
            <DateInput
              fieldName="Effective Date" register={register} errors={errors}
              required={true} setValue={setValue} id='effDate' minDate={new Date()}
              yearRange="-0:+60"
              defaultValue={formData.EffectiveDate && formData.EffectiveDate}
            />
            {<TypeAHeadForm register={register}
              errors={errors} control={control}
              fieldName="class_code_or_Description"
              Data={classCodeData}
              minLength={0}
              getValues={getValues}
              setValue={setValue}
              className="form-field"
              lableClass='form-label'
              label='Class Code or Description'
              activeBold={true}
              placeholder={formData.class_code_or_Description_group ? formData.class_code_or_Description_group : ""}
              defaultInputValue={formData.class_code_or_Description_group && state.application && state.application.commercialPolicyInfo.primaryGLClassCd}
            />}
          </div>
          <div className='grid2'>
            <div className='form-element form-select'>
              {<DropDown fieldName='State' id='state' label='State' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                {/* {drop_data.State.map((value, ind) => (
                  <option value={value.value}>{value.value}</option>
                ))} */}
                {dropdownVal.map((value, ind) => {
                  return value.State !== "" && value.State !== null && value.State !== undefined ? (
                    <option selected={value.State == formData.State} value={value.State === "-select-" ? "" : value.State}>{value.State}</option>
                  ) :
                    <></>
                }
                )}
              </DropDown>}
            </div>
          </div>
        </div>
      }
    </div>
  )
}
export default EffectiveDateAndLocationForm