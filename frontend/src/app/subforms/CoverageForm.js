import React, { useEffect, useState } from 'react'
import FormControl from '../../components/formcontrol/FormControl';
import DropDown from '../../components/dropdown/DropDown';
import { drop_data } from '../../dummydata/data';
import Papa from 'papaparse';
import dropdown_val from '../../dummydata/commercialAuto.csv';
import CoverageLiability from "./CoverageLiability.json"

const CoverageForm = ({ state, register, errors, TabName, formData, setValue  }) => {
    const [dropdownVal, setDropdownVal] = useState([]);
    const [liabilities, setLiabilities] = useState([])
    var commonConfig = { delimiter: "," };
    const {busCountryCode} = formData

    useEffect(() => {
        if (state.policyNumber!== null && state.policyNumber!== undefined) {
            setValue('LiabilityLimit', JSON.parse(state.indicationData).application.insuranceLine[0].liabilityLimit)
			setValue('MedicalPayments', JSON.parse(state.indicationData).application.insuranceLine[0].medicalPayments)
            setValue('TowingandLabor', JSON.parse(state.indicationData).application.insuranceLine[0].towingAndLaborInd)
            setValue('RentalReimbursement', JSON.parse(state.indicationData).application.insuranceLine[0].rentalReimbursementInd)
            setValue('NonOwnedAuto', JSON.parse(state.indicationData).application.insuranceLine[0].nonOwnedAuto)
      
        }
      }, [dropdownVal, setValue])

    useEffect(() => {
        if(Object.keys(CoverageLiability).includes(busCountryCode)) {
            const liabilities = CoverageLiability[busCountryCode]
            setLiabilities(liabilities)
        } else {
            const liabilities = CoverageLiability["common_states"]
            setLiabilities(liabilities)
        }
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
    }, [formData])
    return (
        <>
            {TabName === 'Commercial Auto' &&
                <div className='grid4'>
                    <div className='form-element form-select'>
                        <DropDown fieldName='LiabilityLimit' label='Liability Limit' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuranceLine[0].coverages[0].limits[0].value : formData?.LiabilityLimit} register={register} errors={errors} lableClass='form-label' activeBold={true} setValue={setValue}>
                            {/* {drop_data.Liability.map((value, ind) => (
                                <option value={value.replace(/[ (/,.?'")$&-]/gi, "")}>{value}</option>                            
                                ))} */}
                            {liabilities.map((value, ind) => {
                                return value !== "" && value !== null && value !== undefined ? (
                                    <option value={value === "-select-" ? "" : value}>{value}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>   
                    </div>

                    <div className='form-element form-select'>
                    {dropdownVal.length > 0 ? (
                        <DropDown fieldName='Medical Payments' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuranceLine[0].medicalPayments : formData?.MedicalPayments} register={register} errors={errors} lableClass='form-label' activeBold={true} setValue={setValue}>
                            {/* {drop_data.MedicalPayment.map((value, ind) => (
                                <option value={value==='No Coverage'? 'NONE':value.replace(/[ (/,.?'")$&-]/gi, "")}>{value}</option>                            
                                ))} */}
                            {dropdownVal.map((value, ind) => {
                                return value.MedicalPayment !== "" && value.MedicalPayment !== null && value.MedicalPayment !== undefined ? (
                                    <option value={value.MedicalPayment === "-select-" ? "" : value.MedicalPayment}>{value.MedicalPayment}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                        ) : null}
                        {errors.MedicalPayment && (
                            <div className="invalid-feedback">
                            {errors.MedicalPayment.message}
                            </div>
                        )}
                    </div>

                    <div className='form-element form-select'>
                    {dropdownVal.length > 0 ? (
                        <DropDown fieldName='Towing and Labor' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuranceLine[0].towingAndLaborInd : formData?.TowingandLabor} register={register} errors={errors} lableClass='form-label' activeBold={true} setValue={setValue}>
                            {/* {drop_data.TowingandLabor.map((value, ind) => (
                                <option value={value==='No Coverage'? 'NONE':value.replace(/[ (/,.?'")$&-]/gi, "")}>{value}</option>                            
                                ))} */}
                            {dropdownVal.map((value, ind) => {
                                return value.TowingandLabor !== "" && value.TowingandLabor !== null && value.TowingandLabor !== undefined ? (
                                    <option value={value.TowingandLabor === "-select-" ? "" : value.TowingandLabor}>{value.TowingandLabor}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                        ) : null}
                        {errors.TowingandLabor && (
                            <div className="invalid-feedback">
                            {errors.TowingandLabor.message}
                            </div>
                        )}
                    </div>
                    <div className='form-element form-select'>
                    {dropdownVal.length > 0 ? (
                        <DropDown fieldName='Rental Reimbursement' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuranceLine[0].rentalReimbursementInd : formData?.RentalReimbursement} register={register} errors={errors} lableClass='form-label' activeBold={true} setValue={setValue}>
                            {/* {drop_data.RentalReimbursement.map((value, ind) => (
                                <option value={value==='No Coverage'? 'NONE':value.replace(/[ (/,.?'")$&-]/gi, "")}>{value}</option>                            
                                ))} */}
                            {dropdownVal.map((value, ind) => {
                                return value.RentalReimbursement !== "" && value.RentalReimbursement !== null && value.RentalReimbursement !== undefined ? (
                                    <option value={value.RentalReimbursement === "-select-" ? "" : value.RentalReimbursement}>{value.RentalReimbursement}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                        ) : null}
                        {errors.RentalReimbursement && (
                            <div className="invalid-feedback">
                            {errors.RentalReimbursement.message}
                            </div>
                        )}
                    </div>




                    <div className="form-element">
                        <FormControl fieldName="number_of_hired_vehicles" label='Number of Hired Vehicles' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuranceLine[0].numberOfHiredVehicles : formData?.number_of_hired_vehicles} id='number_of_hired_vehicles' register={register} errors={errors} type="number" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} setValue={setValue} />
                    </div>
                    <div className='form-element form-select'>
                    {dropdownVal.length > 0 ? (
                        <DropDown fieldName='Non-Owned Auto' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuranceLine[0].nonOwnedAuto : formData?.NonOwnedAuto} register={register} errors={errors} lableClass='form-label' activeBold={true} setValue={setValue}>
                            {/* {drop_data.NonOwnedauto.map((value, ind) => (
                                <option value={value}>{value}</option>                            
                                ))} */}
                            {dropdownVal.map((value, ind) => {
                                return value.NonOwnedauto !== "" && value.NonOwnedauto !== null && value.NonOwnedauto !== undefined ? (
                                    <option value={value.NonOwnedauto === "-select-" ? "" : value.NonOwnedauto}>{value.NonOwnedauto}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                        ) : null}
                        {errors.NonOwnedauto && (
                            <div className="invalid-feedback">
                            {errors.NonOwnedauto.message}
                            </div>
                        )}
                    </div>
                </div>
            }
            {/* <!--  *(If Monoline Property) --> */}
            {TabName === 'TPCI' &&
                <div className='grid4'>
                    {formData.ProductSelection === 'Monoline Property' &&
                        <>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Building Coverage' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Yes">Yes</option>
                                    <option value="No">No</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Building Limit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Building Limit 1">Building Limit 1</option>
                                    <option value="Building Limit 2">Building Limit 2</option>
                                    <option value="Building Limit 3">Building Limit 3</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Valuation Type' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Valuation Type 1">Valuation Type 1</option>
                                    <option value="Valuation Type 2">Valuation Type 2</option>
                                    <option value="Valuation Type 3">Valuation Type 3</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Building Deductible' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Building Deductible 1">Building Deductible 1</option>
                                    <option value="Building Deductible 2">Building Deductible 2</option>
                                    <option value="Building Deductible 3">Building Deductible 3</option>
                                </DropDown>
                            </div>
                            <div class="form-element">
                                <FormControl fieldName="buisness_pers_property_limit" label='Buisness Pers. Property Limit' id='buisness_pers_property_limit' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Buisness Pers. Property Deductible' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Buisness Pers. Property Deductible 1">Buisness Pers. Property Deductible 1</option>
                                    <option value="Buisness Pers. Property Deductible 2">Buisness Pers. Property Deductible 2</option>
                                    <option value="Buisness Pers. Property Deductible 3">Buisness Pers. Property Deductible 3</option>
                                </DropDown>
                            </div>
                            <div class="form-element">
                                <FormControl fieldName="number_of_hired_vehicles" label='Number of Hired Vehicles' id='number_of_hired_vehicles' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Non-Owned Auto' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Non-Owned Auto 1">Non-Owned Auto 1</option>
                                    <option value="Non-Owned Auto 2">Non-Owned Auto 2</option>
                                    <option value="Non-Owned Auto 3">Non-Owned Auto 3</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Equipment Breakdown' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Equipment Breakdown 1">Equipment Breakdown 1</option>
                                    <option value="Equipment Breakdown 2">Equipment Breakdown 2</option>
                                    <option value="Equipment Breakdown 3">Equipment Breakdown 3</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Blanket Coverage' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Yes">Yes</option>
                                    <option value="No">No</option>
                                </DropDown>
                            </div>
                        </>
                    }
                    {/* <!--  *(If Monoline GL)	--> */}
                    {formData.ProductSelection === 'Monoline GL' &&
                        <>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Blanket Coverage' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Yes">Yes</option>
                                    <option value="No">No</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Blanket Coverage' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Yes">Yes</option>
                                    <option value="No">No</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='General Aggregate Limit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="General Aggregate Limit 1">General Aggregate Limit 1</option>
                                    <option value="General Aggregate Limit 2">General Aggregate Limit 2</option>
                                    <option value="General Aggregate Limit 3">General Aggregate Limit 3</option>

                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Each Occurance' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Each Occurance 1">Each Occurance 1</option>
                                    <option value="Each Occurance 2">Each Occurance 2</option>
                                    <option value="Each Occurance 3">Each Occurance 3</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Pro. & Comp. Ops. Liability Limit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="more options">More Options</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Personal & Adv. Injury Limit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="more options">More Options</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Medical Payments Limit (per pers.)' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="more options">More Options</option>
                                </DropDown>
                            </div>
                        </>
                    }
                    {/* <!--  *(If TPCI Package) --> */}
                    {formData.ProductSelection === 'TPCI Package' &&
                        <>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Building Coverage' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="Yes">Yes</option>
                                    <option value="No">No</option>

                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Building Limit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="more options">More Options</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Valuation Type' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="more options">More Options</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Building Deductible' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="more options">More Options</option>
                                </DropDown>
                            </div>
                            <div class="form-element">
                                <FormControl fieldName="buisness_pers_property_limit" label='Buisness Pers. Property Limit' id='buisness_pers_property_limit' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Buisness Pers. Property Deductible' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="more options">More Options</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Equipment Breakdown' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="more options">More Options</option>
                                </DropDown>
                            </div>
                            <div className='form-element form-select'>
                                <DropDown fieldName='Blanket Coverage' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                                    <option value=""></option>
                                    <option value="more options">More Options</option>
                                </DropDown>
                            </div>
                        </>
                    }
                </div>
            }
        </>
    )
}

export default CoverageForm