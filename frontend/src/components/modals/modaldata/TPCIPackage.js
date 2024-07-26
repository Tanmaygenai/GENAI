import React, { useEffect } from 'react'
import TypeAHeadForm from '../../typeahead/TypeAHeadForm';
import { CountryName } from '../../../dummydata/CountryData';
import FormControl from '../../formcontrol/FormControl';
import DropDown from '../../dropdown/DropDown';
import { formFunctions } from '../../../utility/jqueryFunctions';
import DateInput from '../../dateinput/DateInput';

const TPCIPackage = ({ register, errors, control, getValues, setValue }) => {
    const CountryData = CountryName.map(val => val.name)
    useEffect(() => {
        formFunctions();
    }, [])

    return (
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
            <div class="form-element form-select">
                <DropDown fieldName='Construction Type' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                </DropDown>
            </div>
            <div class="form-element">
                <FormControl fieldName="total_area" label="Total Area" id="underlying_premium" register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='Sprinklered' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="Yes">Yes</option>
                    <option value="No">No</option>
                </DropDown>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='roof_type' label="Roof Type" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="Masonary">Masonary</option>
                </DropDown>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='alarm_description' label="Alarm Description" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="Yes">Yes</option>
                    <option value="No">No</option>
                </DropDown>
            </div>
            <TypeAHeadForm register={register} errors={errors} control={control} fieldName="class_code_or_Description" Data={CountryData} minLength={3} getValues={getValues} className="form-field" lableClass='form-label' label='Class Code or Description' activeBold={true} />
               <div class="form-element">
                   <FormControl fieldName="total_payroll" label="Total Payroll for all operations" id="total_payroll" register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
               </div>
              <div class="form-element">
                  <FormControl fieldName="total_subcontractor" label="Total subcontractor cost for all operations" id="total_subcontractor" register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
              </div>
               <div class="form-element">
                 <FormControl fieldName="annual_sales" label="Annual Sales/receipts for all operations" id="annual_sales" register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
              </div>
              <div class="form-element form-select">
                   <DropDown fieldName='building_coverage' label="Building Coverage" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                       <option value=""></option>
                       <option value="Yes">Yes</option>
                       <option value="No">No</option>
                   </DropDown>
               </div>
               <div class="form-element form-select">
                   <DropDown fieldName='building_limit' label="Building Limit" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                       <option value=""></option>
                       <option value="$10,000">$10,000</option>
                       <option value="$100,000">$100,000</option>
                   </DropDown>
               </div>
              <div class="form-element form-select">
                  <DropDown fieldName='valuation_type' label="Valuation Type" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                      <option value=""></option>
                      <option value="$10,000">$10,000</option>
                      <option value="$100,000">$100,000</option>
                  </DropDown>
              </div>
              <div class="form-element form-select">
                 <DropDown fieldName='building_deductible' label="Building Deductible" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                     <option value=""></option>
                     <option value="$500">$500</option>
                     <option value="$1,000">$1,000</option>
                 </DropDown>
             </div>
              <div class="form-element">
                 <FormControl fieldName="bpp_limit" label="Business Personal Property Limit" id="bpp_limit" register={register} errors={errors} type='text' showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
             </div>
             <div class="form-element form-select">
               <DropDown fieldName='building_bpp_deductible' label="Business Personal Property Deductible" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                   <option value=""></option>
                   <option value="$500">$500</option>
                   <option value="$1,000">$1,000</option>
                   <option value="$2,500">$2,500</option>
               </DropDown>
             </div>
             <div class="form-element form-select">
                <DropDown fieldName='equipment_breakdown' label="Equipment Breakdown" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="$500">$500</option>
                    <option value="$1,000">$1,000</option>
                    <option value="$2,500">$2,500</option>
                    <option value="$5,000">$5,000</option>
                </DropDown>
             </div>
             <div class="form-element form-select">
                  <DropDown fieldName='blanket_coverage' label="Blanket Coverage" register={register} errors={errors} lableClass='form-label' activeBold={true}>
                     <option value=""></option>
                     <option value="Yes">Yes</option>
                     <option value="No">No</option>
                  </DropDown>
              </div>
<div class="form-element form-select">
               <DropDown fieldName='General Aggregate Limit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                   <option value=""></option>
                   <option value="$10,000">$10,000</option>
                   <option value="$100,000">$100,000</option>
               </DropDown>
           </div>
           <div class="form-element form-select">
                <DropDown fieldName='Each Occurance' label='Each Occurance' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                   <option value="$10,000">$15,000</option>
                   <option value="$100,000">$150,000</option>
                </DropDown>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='Products & Completed Ops Aggregate Limit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="$10,000">$50,000</option>
                    <option value="$100,000">$100,000</option>
                </DropDown>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='Personal & Advertising Limit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="$100,000">$100,000</option>
                    <option value="$200,000">$200,000</option>
                </DropDown>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='Medical Payments Limit (per person)' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="$250,000">$250,000</option>
                    <option value="$500,000">$500,000</option>
                </DropDown>
            </div>
            <div class="form-element form-select">
               <DropDown fieldName='General Aggregate Limit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                   <option value=""></option>
                   <option value="$10,000">$10,000</option>
                   <option value="$100,000">$100,000</option>
               </DropDown>
           </div>
           <div class="form-element form-select">
                <DropDown fieldName='Each Occurance' label='Each Occurance' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                   <option value="$10,000">$15,000</option>
                   <option value="$100,000">$150,000</option>
                </DropDown>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='Products & Completed Ops Aggregate Limit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="$10,000">$50,000</option>
                    <option value="$100,000">$100,000</option>
                </DropDown>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='Personal & Advertising Limit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="$100,000">$100,000</option>
                    <option value="$200,000">$200,000</option>
                </DropDown>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='Medical Payments Limit (per person)' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    <option value=""></option>
                    <option value="$250,000">$250,000</option>
                    <option value="$500,000">$500,000</option>
                </DropDown>
            </div>
        </div>
    )
}

export default TPCIPackage