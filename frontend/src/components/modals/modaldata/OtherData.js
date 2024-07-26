import React, { useEffect, useState } from 'react'
import TypeAHeadForm from '../../typeahead/TypeAHeadForm';
import { CountryName } from '../../../dummydata/CountryData';
import FormControl from '../../formcontrol/FormControl';
import DropDown from '../../dropdown/DropDown';
import { formFunctions } from '../../../utility/jqueryFunctions';
import DateInput from '../../dateinput/DateInput';
import NumberFormat from 'react-number-format';
import { drop_data } from '../../../dummydata/data';
import { classCodes } from '../../../dummydata/classcodes';
import $ from "jquery"
import Papa from 'papaparse';
import dropdown_val from '../../../dummydata/excessLiability.csv';

const OtherData = ({ modalState, claimData, register, errors, control, getValues, trigger, classCodeDescription, setClassCodeDescription, setValue }) => {
    const CountryData = CountryName.map(val => val.name)
    const [selectedValue, setSelectedValue] = useState("")
    const classCodeData = []
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

    useEffect(() => {
        formFunctions();
        setValue("class_code_or_Description", modalState.editIndex === null ? modalState.ClaimData.selectedVal : modalState.AddPolicyData[modalState.editIndex].class_code_or_Description)
        trigger("class_code_or_Description")
        setSelectedValue(modalState.editIndex === null ? "" : modalState.AddPolicyData[modalState.editIndex].PolicyType)
    }, [])

    useEffect(() => {
        $('#selectPolicyTypeValues').on('change', function () {
            setSelectedValue($(this).val());
        });
    })
    function formatVehicleData(e) {
        var data = e.target.value;
        var vehicleData = data
        if (vehicleData.slice(0, 1) == 0) {
            vehicleData = ''
        }
        e.target.value = vehicleData;
    }
    return (
        <div >
            <div class="grid2">
                <TypeAHeadForm
                    defaultInputValue={modalState.editIndex === null ? modalState.ClaimData.selectedVal : modalState.AddPolicyData[modalState.editIndex].class_code_or_Description}
                    register={register}
                    typeAheadClass="classCodeGroupSelection"
                    errors={errors} control={control}
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
                    <FormControl
                        defaultValue={modalState.editIndex === null ? "" : modalState.AddPolicyData[modalState.editIndex].underlying_premium}
                        fieldName="other_underlying_premium"
                        label="Underlying Premium"
                        id="underlying_gl_premium"
                        register={register} errors={errors}
                        type='text'
                        showLabel={true}
                        className="form-field" required={true}
                        lableClass='form-label'
                        activeBold={true} onChange={(e) => { formatVehicleData(e) }}
                    />
                </div>
            </div>
            <div className='form-element form-select'>
                <DropDown fieldName='Policy Type' id='selectPolicyTypeValues'
                    register={register} errors={errors} lableClass='form-label' activeBold={true}
                    defaultValue={modalState.editIndex !== null && modalState.AddPolicyData[modalState.editIndex].PolicyType}
                >
                    {/* {drop_data.values.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                    {dropdownVal.map((value, ind) => {
                                            return value.values!== "" && value.values!== null && value.values!== undefined? (
                                                <option value={value.values === "-select-" ? "" : value.values}>{value.values}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                </DropDown>
            </div>

            {(selectedValue === 'Auto Liability Limit') &&
                <div class="form-element form-select">
                    <DropDown fieldName='Auto Limit' register={register}
                        errors={errors} lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex !== null && modalState.AddPolicyData[modalState.editIndex].AutoLimit}
                    >
                        {/* {drop_data.CSL.map((value, ind) => (
                            <option value={value}>{value}</option>
                        ))} */}
                    {dropdownVal.map((value, ind) => {
                                            return value.CSL!== "" && value.CSL!== null && value.CSL!== undefined? (
                                                <option value={value.CSL === "-select-" ? "" : value.CSL}>{value.CSL}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                    </DropDown>
                </div>
            }
            {(selectedValue === 'Auto Liability Limit') &&
                <div class="form-element form-select">
                    <DropDown fieldName='Each Occurrence Limit' register={register}
                        errors={errors} lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex !== null && modalState.AddPolicyData[modalState.editIndex].EachOccurrenceLimit}
                    >
                        {dropdownVal.map((value, ind) => {
                                            return value.CSL!== "" && value.CSL!== null && value.CSL!== undefined? (
                                                <option value={value.CSL === "-select-" ? "" : value.CSL}>{value.CSL}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                    </DropDown>
                </div>
            }
            {(selectedValue === 'Auto Liability Limit') &&
                <div class="form-element form-select">
                    <DropDown fieldName='General Aggregate Limit' register={register}
                        errors={errors} lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex !== null && modalState.AddPolicyData[modalState.editIndex].GeneralAggregateLimit}
                    >
                        {/* {drop_data.GAL.map((value, ind) => (
                            <option value={value}>{value}</option>
                        ))} */}
                    {dropdownVal.map((value, ind) => {
                                            return value.GAL!== "" && value.GAL!== null && value.GAL!== undefined? (
                                                <option value={value.GAL === "-select-" ? "" : value.GAL}>{value.GAL}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                    </DropDown>
                </div>
            }
            {(selectedValue === 'Employee Benefits Liability' || selectedValue === 'Hired & Non-Owned') &&
                <div class="form-element form-select">
                    <DropDown fieldName='Occurrence Limit/Each Accident' register={register}
                        errors={errors} lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex !== null && modalState.AddPolicyData[modalState.editIndex].OccurrenceLimitEachAccident}
                    >
                        {dropdownVal.map((value, ind) => {
                                            return value.CSL!== "" && value.CSL!== null && value.CSL!== undefined? (
                                                <option value={value.CSL === "-select-" ? "" : value.CSL}>{value.CSL}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                    </DropDown>
                </div>
            }
            {(selectedValue === 'Liquor Liability') &&
                <div class="form-element form-select">
                    <DropDown fieldName='Occurrence Limit/Each Accident' register={register}
                        errors={errors} lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex !== null && modalState.AddPolicyData[modalState.editIndex].OccurrenceLimitEachAccident}
                    >
                        {/* {drop_data.LL.map((value, ind) => (
                            <option value={value}>{value}</option>
                        ))} */}
                    {dropdownVal.map((value, ind) => {
                                            return value.LL!== "" && value.LL!== null && value.LL!== undefined ? (
                                                <option value={value.LL === "-select-" ? "" : value.LL}>{value.LL}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                    </DropDown>
                </div>
            }

        </div>
    )
}

export default OtherData