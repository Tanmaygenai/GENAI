


import React, { useEffect } from 'react'
import FormControl from '../../components/formcontrol/FormControl';
import DropDown from '../../components/dropdown/DropDown';
import { formFunctions } from '../../utility/jqueryFunctions';

const PropertyCoverageForm = ({ register, errors, TabName}) => {
    useEffect(() => {
        formFunctions()
    }, [])

    return (
        <>
            {TabName === 'TPCI' &&
                <div className='grid4'>
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
                    <div className="form-element">
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
                    <div className="form-element">
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
                </div>
            }

        </>
    )
}

export default PropertyCoverageForm