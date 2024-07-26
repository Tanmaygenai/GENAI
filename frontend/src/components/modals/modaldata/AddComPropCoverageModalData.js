import React from 'react';
// import Check from '../../check/Check';
import DropDown from '../../dropdown/DropDown';
import FormControl from '../../formcontrol/FormControl';
import { drop_data } from '../../../dummydata/data';
import Papa from 'papaparse';
import dropdown_val from '../../../dummydata/commercialProperty.csv';
import { useState } from 'react';
import { useEffect } from 'react';
const AddComPropCoverageModalData = ({ register, errors }) => {
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
    return (
        <div className="grid3">
            <div className='form-element form-select'>
                <DropDown fieldName='CoverageTypes' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    {/* {drop_data.PropCoverages.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                    {dropdownVal.map((value, ind) => {
                                            return value.PropCoverages!== "" && value.PropCoverages!== null && value.PropCoverages!== undefined ? (
                                                <option value={value.PropCoverages === "-select-" ? "" : value.PropCoverages}>{value.PropCoverages}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                </DropDown>
            </div>
            <div className='form-element form-select'>
                <DropDown fieldName='BuildingLimit' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    {/* {drop_data.BuildingLimits.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                {dropdownVal.map((value, ind) => {
                                            return value.BuildingLimits!== ""&& value.BuildingLimits!== null && value.BuildingLimits!== undefined ? (
                                                <option value={value.BuildingLimits === "-select-" ? "" : value.BuildingLimits}>{value.BuildingLimits}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                </DropDown>
            </div>
            <div className='form-element form-select'>
                <DropDown fieldName='BuildingDeductible' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    {/* {drop_data.BuildingDeductible.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                {dropdownVal.map((value, ind) => {
                                            return value.BuildingDeductible!== "" && value.BuildingDeductible!== null && value.BuildingDeductible!== undefined? (
                                                <option value={value.BuildingDeductible === "-select-" ? "" : value.BuildingDeductible}>{value.BuildingDeductible}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                </DropDown>
            </div>
        </div>
    );
};

export default AddComPropCoverageModalData;
