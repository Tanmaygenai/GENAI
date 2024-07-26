import React, { useEffect,useState } from 'react'
import TypeAHeadForm from '../../typeahead/TypeAHeadForm';
import { CountryName } from '../../../dummydata/CountryData';
import FormControl from '../../formcontrol/FormControl';
import DropDown from '../../dropdown/DropDown';
import { formFunctions } from '../../../utility/jqueryFunctions';
import DateInput from '../../dateinput/DateInput';
import { drop_data } from '../../../dummydata/data';
import $ from "jquery"
import classNames from 'classnames';
import Papa from 'papaparse';
import dropdown_val from '../../../dummydata/dropdown_data.csv';

const TPCIMonoLineProperty = ({ register, errors, control, getValues, setValue }) => {
    const CountryData = CountryName.map(val => val.name)
    const [flag, setFlag] = useState(false)
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
        formFunctions();
         $('#bldgCoverage').on('change', function () {
            setFlag($(this).val());
        });
    }, [])

     return <>
            <div class="grid3">
                <div className="form-element">
                    <FormControl fieldName="Address" label="Address" register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                </div>
                <div className="form-element">
                    <FormControl fieldName="City" label="City" register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                </div>
                <div className="form-element">
                    <FormControl fieldName="State" label="State" register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                </div>
                <div className="form-element">
                    <FormControl fieldName="zip_code" label='Zip Code' id='state' register={register} errors={errors} type="number" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} maxLength={5} minLength={5} min={0} />
                </div>
            </div>
            <div class="fullhead">Building Details</div>
            <div class="grid3">
                <div class="form-element form-select">
                    <DropDown fieldName='Construction Type' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                        {/* {drop_data.ConstructionType.map((value, ind) => (
                            <option value={value}>{value}</option>
                         ))} */}
                        {dropdownVal.map((value, ind) => {
                                            return value.ConstructionType!== "" ? (
                                                <option value={value.ConstructionType === "-select-" ? "" : value.ConstructionType}>{value.ConstructionType}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                    </DropDown>
                </div>
                <div class="form-element">
                    <FormControl fieldName="total_area" label="Total Area" id="underlying_premium" register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                </div>
                <div class="form-element form-select">
                    <DropDown fieldName='Sprinklered' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                        {/* {drop_data.Sprinklered.map((value, ind) => (
                            <option value={value}>{value}</option>
                         ))} */}
                    {dropdownVal.map((value, ind) => {
                                            return value.Sprinklered!== "" ? (
                                                <option value={value.Sprinklered === "-select-" ? "" : value.Sprinklered}>{value.Sprinklered}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                    </DropDown>
                </div>
                <div class="form-element form-select">
                    <DropDown fieldName='roof_type' label="Roof Type" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                        {/* {drop_data.RoofType.map((value, ind) => (
                            <option value={value}>{value}</option>
                         ))} */}
                    {dropdownVal.map((value, ind) => {
                                            return value.RoofType!== "" ? (
                                                <option value={value.RoofType === "-select-" ? "" : value.RoofType}>{value.RoofType}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                    </DropDown>
                </div>
                <div class="form-element form-select">
                    <DropDown fieldName='alarm_description' label="Alarm Description" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                       {/* {drop_data.AlarmDescription.map((value, ind) => (
                           <option value={value}>{value}</option>
                        ))} */}
                {dropdownVal.map((value, ind) => {
                                            return value.AlarmDescription!== "" ? (
                                                <option value={value.AlarmDescription === "-select-" ? "" : value.AlarmDescription}>{value.AlarmDescription}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                    </DropDown>
                </div>
                <div class="form-element">
                  <FormControl fieldName="bpp_limit" label="Business Personal Property Limit" id="bpp_limit" register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
              </div>
               <div className='form-element form-select'>
                      <label className='form-label'> Building Coverage </label>
                      <select id='bldgCoverage'
                          name="BuildingCoverage"
                          size="sm"
                          {...register('BuildingCoverage', {
                              required: 'BuildingCoverage is required',
                          })}
                          className={classNames(" form-field form-control", {
                              "is-invalid": errors.BuildingCoverage,
                          })}
                      >
                          <option value="">Select</option>
                          <option value="Yes" >Yes</option>
                          <option value="No" >No</option>
                      </select>
                      {errors.BuildingCoverage && (
                          <div className="invalid-feedback">
                              {errors.BuildingCoverage.message}
                          </div>
                      )}
                  </div>
            </div>
                { flag === 'Yes' ?
                    <div class="grid3">
                        <div class="form-element">
                            <FormControl fieldName="building_limit" label="Building Limit" id="building_limit" register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                        </div>
                       <div class="form-element form-select">
                           <DropDown fieldName='valuation_type' label="Valuation Type" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                               {/* {drop_data.ValuationType.map((value, ind) => (
                                  <option value={value}>{value}</option>
                               ))} */}
                            {dropdownVal.map((value, ind) => {
                                            return value.ValuationType!== "" ? (
                                                <option value={value.ValuationType === "-select-" ? "" : value.ValuationType}>{value.ValuationType}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                           </DropDown>
                       </div>
                       <div class="form-element form-select">
                          <DropDown fieldName='building_deductible' label="Building Deductible" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                              <option value=""></option>
                              <option value="$500">$500</option>
                              <option value="$1,000">$1,000</option>
                          </DropDown>
                      </div>
                    </div>
                     :
                        <>
                        </>
                }
                <div class="grid3">
                <div class="form-element form-select">
                <DropDown fieldName='building_bpp_deductible' label="Business Personal Property Deductible" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="500">$500</option>
                    <option value="1000">$1,000</option>
                    <option value="2500">$2,500</option>
                    <option value="5000">$5,000</option>
                    <option value="7500">$7,500</option>
                    <option value="10000">$10,000</option>
                    <option value="12500">$12,500</option>
                    <option value="15000">$15,000</option>
                    <option value="20000">$20,000</option>
                    <option value="25000">$25,000</option>
                    <option value="50000">$50,000</option>
                </DropDown>
              </div>
              <div class="form-element form-select">
                 <DropDown fieldName='equipment_breakdown' label="Equipment Breakdown" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="500">$500</option>
                    <option value="1000">$1,000</option>
                    <option value="2500">$2,500</option>
                    <option value="5000">$5,000</option>
                    <option value="7500">$7,500</option>
                    <option value="10000">$10,000</option>
                    <option value="12500">$12,500</option>
                    <option value="15000">$15,000</option>
                    <option value="20000">$20,000</option>
                    <option value="25000">$25,000</option>
                    <option value="50000">$50,000</option>
                 </DropDown>
              </div>
              <div class="form-element form-select">
                   <DropDown fieldName='blanket_coverage' label="Blanket Coverage" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                      <option value=""></option>
                      <option value="NONE">No Blanket</option>
                      <option value="BLDG">All Buildings</option>
                      <option value="CONT">All Contents</option>
                      <option value="BOTH">All Buildings and All Contents (separately)</option>
                   </DropDown>
               </div>
            </div>
        </>
}
export default TPCIMonoLineProperty