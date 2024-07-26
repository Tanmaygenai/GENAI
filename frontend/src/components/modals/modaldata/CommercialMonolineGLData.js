import React, { useEffect, useState } from 'react'
import TypeAHeadForm from '../../typeahead/TypeAHeadForm';
import FormControl from '../../formcontrol/FormControl';
import DropDown from '../../dropdown/DropDown';
import { formFunctions } from '../../../utility/jqueryFunctions';
import { drop_data } from '../../../dummydata/data';
import { classCodes } from '../../../dummydata/classcodes';
import $ from 'jquery'
import Papa from 'papaparse';
import dropdown_val from '../../../dummydata/excessLiability.csv';

const CommercialMonolineGLData = ({ modalState, claimData, register, errors, control, getValues, setValue, trigger }) => {

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
        trigger("class_code_or_Description")
    }, [])

    var primaryOpVal = 'No'
    if (modalState.AddPolicyData.length > 0) {
        var hasGLCode = false;
        modalState.AddPolicyData.map((val, ind) => {
            if (val.AddUnderlyingPolicies === "Commercial Monoline GL (or GL Portion of Commercial Package)") {
                hasGLCode = true;
            }
        })
        if (hasGLCode === false) {
            primaryOpVal = 'Yes'
        }
    } else {
        primaryOpVal = 'Yes'
    }

    const classCodeData = []

    claimData.allVal ?
        claimData.allVal.map((val, ind) => {
            classCodeData.push(claimData.allVal[ind])
        })
        :
        classCodes.map((val, ind) => {
            let classCd = modalState.AddPolicyData[modalState.editIndex].class_code_or_Description.split(" ");
            if (val["Class Code"] === classCd[0] && (val["Class Code Level"]["Exc"] === "Yes")) {
                classCodeData.push(val["Class Code"] + " " + val["Class Name"])
            }
        })

    return (
        <div >
            <div class="grid2">
                <TypeAHeadForm register={register}
                    defaultInputValue={modalState.editIndex === null ? modalState.ClaimData.selectedVal : modalState.AddPolicyData[modalState.editIndex].class_code_or_Description}
                    errors={errors} control={control}
                    typeAheadClass="classCodeGroupSelection"
                    fieldName="class_code_or_Description"
                    Data={classCodeData}
                    minLength={0}
                    getValues={getValues}
                    className="form-field"
                    lableClass='form-label'
                    label='Class Code or Description'
                    activeBold={true}
                />
                <div class="form-element">
                    <FormControl fieldName="gl_underlying_premium" label="Underlying GL Premium" pattern={/^[0-9]*$/i}
                        register={register} errors={errors} type='text' showLabel={true}
                        className="form-field" required={true} lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex === null ? '' : modalState.AddPolicyData[modalState.editIndex].underlying_premium}
                    />
                </div>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='Each Occurrence Limit' register={register} errors={errors}
                    lableClass='form-label' activeBold={true}
                    defaultValue={modalState.editIndex !== null ? modalState.AddPolicyData[modalState.editIndex].EachOccurrenceLimit : '$1,000,000'}
                >
                    {/* {drop_data.EachOccLimit.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                
                    {dropdownVal.map((value, ind) => {
                                            return value.EachOccLimit!== "" && value.EachOccLimit!== null && value.EachOccLimit!== undefined? (
                                                <option value={value.EachOccLimit === "-select-" ? "" : value.EachOccLimit}>{value.EachOccLimit}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                </DropDown>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='Products and Completed Ops Aggregate Limit' register={register} errors={errors}
                    lableClass='form-label' activeBold={true}
                    defaultValue={modalState.editIndex !== null ? modalState.AddPolicyData[modalState.editIndex].ProductsandCompletedOpsAggregateLimit : '$2,000,000'}
                >
                    {/* {drop_data.ProdAndCompLimit.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                {dropdownVal.map((value, ind) => {
                                            return value.ProdAndCompLimit!== "" && value.ProdAndCompLimit!== null && value.ProdAndCompLimit!== undefined? (
                                                <option value={value.ProdAndCompLimit === "-select-" ? "" : value.ProdAndCompLimit}>{value.ProdAndCompLimit}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                </DropDown>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='General Aggregate Limit' register={register} errors={errors}
                    lableClass='form-label' activeBold={true}
                    defaultValue={modalState.editIndex !== null ? modalState.AddPolicyData[modalState.editIndex].GeneralAggregateLimit : '$2,000,000'}
                >
                    {/* {drop_data.GenAggLimit.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                 {dropdownVal.map((value, ind) => {
                                            return value.GenAggLimit!== "" && value.GenAggLimit!== null && value.GenAggLimit!== undefined? (
                                                <option value={value.GenAggLimit === "-select-" ? "" : value.GenAggLimit}>{value.GenAggLimit}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                </DropDown>
            </div>
            <div class="form-element form-select">
                <DropDown fieldName='Personal & Advertising Limit' register={register} errors={errors}
                    lableClass='form-label' activeBold={true}
                    defaultValue={modalState.editIndex !== null ? modalState.AddPolicyData[modalState.editIndex].PersonalAdvertisingLimit : '$1,000,000'}
                >
                    {/* {drop_data.PersAdvInjLimit.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                
                      {dropdownVal.map((value, ind) => {
                                            return value.PersAdvInjLimit!== "" && value.PersAdvInjLimit!== null && value.PersAdvInjLimit!== undefined ? (
                                                <option value={value.PersAdvInjLimit === "-select-" ? "" : value.PersAdvInjLimit}>{value.PersAdvInjLimit}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                </DropDown>
            </div>

        </div>
    )
}

export default CommercialMonolineGLData