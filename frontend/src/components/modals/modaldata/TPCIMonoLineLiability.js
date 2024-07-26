import React, { useEffect } from 'react'
import TypeAHeadForm from '../../typeahead/TypeAHeadForm';
import { CountryName } from '../../../dummydata/CountryData';
import FormControl from '../../formcontrol/FormControl';
import DropDown from '../../dropdown/DropDown';
import { formFunctions } from '../../../utility/jqueryFunctions';
import DateInput from '../../dateinput/DateInput';

const TPCIMonoLineLiability = ({ register, errors, control, getValues, setValue }) => {
    const CountryData = CountryName.map(val => val.name)
    useEffect(() => {
        formFunctions();
    }, [])

    return (
        <div class="grid3">
            <TypeAHeadForm register={register} errors={errors} control={control} fieldName="class_code_or_Description" Data={CountryData} minLength={3} getValues={getValues} className="form-field" lableClass='form-label' label='Class Code or Description' activeBold={true} />
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

export default TPCIMonoLineLiability