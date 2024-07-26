import React, { useEffect } from 'react'
import TypeAHeadForm from '../../components/typeahead/TypeAHeadForm';
import { CountryName } from '../../dummydata/CountryData';
import { Occupation } from '../../dummydata/Occupation';
import FormControl from '../../components/formcontrol/FormControl';
import DropDown from '../../components/dropdown/DropDown';
import { formFunctions } from '../../utility/jqueryFunctions';
import DateInput from '../../components/dateinput/DateInput';
import { drop_data } from '../../dummydata/data';
import Papa from 'papaparse';
import dropdown_val from '../../dummydata/miscellaneous.csv';
import { useState } from 'react';

const ProductAndClassificationForm = ({ state={}, register, errors, control, TabName, getValues, setValue }) => {
    const CountryData = CountryName.map(val => val.name)
    const OccupationData = Occupation.map(val => val)
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
        formFunctions()
    }, [])

    return (
        <>
            {TabName === 'TPCI' &&
                <div className='grid4'>
                    <div className='form-element form-select'>
                        <DropDown fieldName='Product Selection' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                            <option value=""></option>
                            {/* <option value="Monoline Property">Monoline Property </option> */}
                            <option value="Monoline GL">Monoline GL</option>
                            {/* <option value="TPCI Package">TPCI Package</option> */}
                        </DropDown>
                    </div>
                    <div className='form-element form-select'>
                        <DropDown fieldName='Term' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                            {/* {drop_data.Term.map((value, ind) => (
                                <option value={value}>{value}</option>
                            ))} */}
                        {dropdownVal.map((value, ind) => {
                                            return value.Term!== "" && value.Term!== null && value.Term!== undefined? (
                                                <option value={value.Term === "-select-" ? "" : value.Term}>{value.Term}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                        </DropDown>
                    </div>
                    <DateInput fieldName="Expiration Date" register={register} errors={errors} required={true} setValue={setValue} />

                    {/* <TypeAHeadForm register={register} errors={errors} control={control} fieldName="class_code_or_Description" Data={CountryData} minLength={3} getValues={getValues} className="form-field" lableClass='form-label' label='Class Code or Description' activeBold={true} /> */}
                    <div className='form-element form-select'>
                        <DropDown defaultValue={state.state} fieldName='primary_state' label='Primary State' id='primary_state' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                            {/* {drop_data.State.map((value, ind) => (
                                <option value={value.value}>{value.value}</option>
                            ))} */}
                        {dropdownVal.map((value, ind) => {
                                            return value.State!== "" ? (
                                                <option value={value.State === "-select-" ? "" : value.State}>{value.State}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                        </DropDown>
                    </div>
                    <div className="form-element">
                        <FormControl fieldName="primary_zip" label='Primary Zip' id='primary_zip' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                    </div>
                    <div className="form-element">
                        <FormControl fieldName="number_additional_location" label='Number Additional Location' id='number_additional_location' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                    </div>
                </div>
            }
           
            {TabName === 'Commercial Auto' &&
                <div className='grid2'>
                    <TypeAHeadForm register={register} errors={errors} control={control} fieldName="primary_occupation" Data={OccupationData} minLength={0} getValues={getValues} className="form-field" lableClass='form-label' label='Primary Occupation' activeBold={true} />
                    <div className="form-element">
                        <FormControl fieldName="buisness_primary_zip" label='Buisness Primary Zip' id='buisness_primary_zip' register={register} errors={errors} type="number" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} maxLength={5} minLength={5} min={0} />
                    </div>
                </div>
            }
        </>
    )
}

export default ProductAndClassificationForm