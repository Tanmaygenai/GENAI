import React, { useEffect } from 'react'
import FormControl from '../../formcontrol/FormControl';
import DropDown from '../../dropdown/DropDown';
import { drop_data } from '../../../dummydata/data';
import { formFunctions } from '../../../utility/jqueryFunctions';
import $ from "jquery"
import Papa from 'papaparse';
import dropdown_val from '../../../dummydata/excessLiability.csv';
import { useState } from 'react';
const ExcessData = ({ modalState, claimData, register, errors, control, getValues, setValue }) => {

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
        $(".classCodeGroupSelection").children().children().focus()
    }, [])

    return (
        <div class="grid2">
            <div class="form-element">
                <FormControl               
                    defaultValue={modalState.editIndex === null ? "" : modalState.AddPolicyData[modalState.editIndex].underlying_premium}
                    fieldName="underlying_premium"
                    typeAheadClass="classCodeGroupSelection"
                    label="Underlying Premium"
                    id="premium"
                    register={register} errors={errors}
                    type='text'
                     showLabel={true}
                    className="form-field" required={true}
                    lableClass='form-label'
                    activeBold={true}
                    pattern={/^[0-9]*$/i}
                />
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='Limit of Liability' register={register}
                errors={errors} lableClass='form-label' activeBold={true}
                defaultValue={modalState.editIndex!==null ? modalState.AddPolicyData[modalState.editIndex].LimitofLiability : "$1,000,000"}
                >
                    {/* {drop_data.ELL.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))}    */}
                
                       {dropdownVal.map((value, ind) => {
                                            return value.ELL!== "" && value.ELL!== null && value.ELL!== undefined? (
                                                <option value={value.ELL === "-select-" ? "" : value.ELL}>{value.ELL}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                </DropDown>
            </div>
        </div>
    )
}

export default ExcessData
