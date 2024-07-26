import React, { useEffect, useState } from 'react'
import FormControl from '../../formcontrol/FormControl';
import DropDown from '../../dropdown/DropDown';
import { drop_data } from '../../../dummydata/data';
import $ from 'jquery'
import Papa from 'papaparse';
import dropdown_val from '../../../dummydata/excessLiability.csv';
    
const CommercialAutoData = ({ modalState, claimData, register, errors, control, getValues, setValue, DisableBtn, setDisableBtn }) => {
    const [toRender, setToRender] = useState("")
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
        var TotalVeh = (+$("#LightVehicle").val()) + (+$("#MediumVehicle").val()) + (+$("#HeavyVehicle").val()) + (+$("#ExtraHeavyVehicle").val())
        TotalVeh < 1 ? setDisableBtn(true) : setDisableBtn(false)
    })

    return (
        <>
            <div class="grid3">
                <div class="form-element form-select">
                    <DropDown fieldName='Combined Single Limit' register={register} errors={errors}
                        lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex === null ? "$1,000,000" : modalState.AddPolicyData[modalState.editIndex].CombinedSingleLimit}
                    >
                        {/* {drop_data.CSL.map((value, ind) => (
                                <option value={value}>{value}</option>
                        ))}      */}
                {dropdownVal.map((value, ind) => {
                                            return value.CSL!== "" && value.CSL!== null && value.CSL!== undefined? (
                                                <option value={value.CSL === "-select-" ? "" : value.CSL}>{value.CSL}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                    </DropDown>
                </div>
                <div class="form-element">
                    <FormControl fieldName="auto_underlying_premium" label="Underlying Premium" pattern={/^[0-9]*$/i}
                        register={register} errors={errors} type='text' showLabel={true}
                        className="form-field" required={true} lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex === null ? "" : modalState.AddPolicyData[modalState.editIndex].underlying_premium}
                    />
                </div>
                <div class="form-element">
                    <FormControl fieldName="LightVehicle" label="Light Vehicles" id="LightVehicle"
                        register={register} errors={errors} type='Number' showLabel={true} onChange={(e)=>setToRender(e.target.value)}
                        className="form-field" required={true} lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex === null ? 0 : modalState.AddPolicyData[modalState.editIndex].LightVehicle}
                    />
                </div>
                <div class="form-element">
                    <FormControl fieldName="MediumVehicle" label="Medium Vehicles" id="MediumVehicle"
                        register={register} errors={errors} type='Number' showLabel={true} onChange={(e)=>setToRender(e.target.value)}
                        className="form-field" required={true} lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex === null ? 0 : modalState.AddPolicyData[modalState.editIndex].MediumVehicle}
                    />
                </div>
                <div class="form-element">
                    <FormControl fieldName="HeavyVehicle" label="Heavy Vehicles" id="HeavyVehicle"
                        register={register} errors={errors} type='Number' showLabel={true} onChange={(e)=>setToRender(e.target.value)}
                        className="form-field" required={true} lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex === null ? 0 : modalState.AddPolicyData[modalState.editIndex].HeavyVehicle}
                    />
                </div>
                <div class="form-element">
                    <FormControl fieldName="ExtraHeavyVehicle" label="Extra Heavy Vehicles" id="ExtraHeavyVehicle"
                        register={register} errors={errors} type='Number' showLabel={true} onChange={(e)=>setToRender(e.target.value)}
                        className="form-field" required={true} lableClass='form-label' activeBold={true}
                        defaultValue={modalState.editIndex === null ? 0 : modalState.AddPolicyData[modalState.editIndex].ExtraHeavyVehicle}
                    />
                </div>
                <div></div>
                <div></div>
                {DisableBtn && <div><p style={{color:'red'}}>Please select at least one vehicle type</p></div>}
            </div>
        </>
    )
}

export default CommercialAutoData
