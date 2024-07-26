import React, { useEffect, useState } from 'react'
import FormControl from '../../formcontrol/FormControl';
import { Col, Row } from 'react-bootstrap'
import DropDown from '../../dropdown/DropDown';
import { drop_data } from '../../../dummydata/data';
import dropdown_val from '../../../dummydata/commercialAuto.csv';
import DateInput from '../../dateinput/DateInput';
import $ from "jquery"
import Papa from 'papaparse';

const AddDriverModalData = ({ register, errors, setValue,modalState }) => {
    
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
        $('#mainModal').on('mousemove',function () {
            var dateOfBirth = $('#Driver_Date_of_Birth').val()
            var ageInMilliseconds = new Date() - new Date(dateOfBirth);
            var age = Math.floor(ageInMilliseconds / 1000 / 60 / 60 / 24 / 365);
            $('#age').val(age)
            setValue('age',age)
        });
        if(modalState.DriverData && modalState.editIndex!==null){   
            setValue('Driver_First_Name', modalState.DriverData[modalState.editIndex].FirstName)
            setValue('Driver_Last_Name', modalState.DriverData[modalState.editIndex].LastName)
            setValue('Driver_Date_of_Birth',modalState.DriverData[modalState.editIndex].DOB)
            setValue('total_driver_points',modalState.DriverData[modalState.editIndex].TotalDriverPoints )
            setValue('DriverLicenseState',modalState.DriverData[modalState.editIndex].DriverLicenseState)
            setValue('DriverLicenseNumber',modalState.DriverData[modalState.editIndex].DriverLicenseNumber)
            setValue('Driver_US_Experience', modalState.DriverData[modalState.editIndex].DriverUSExperience)
        
         }
    },[modalState.editIndex])

   
    function updateAge(e) {
        alert(e)
    }
    return (
        <div class="grid3">

            
            <div class="form-element has-value">
                <FormControl fieldName="Driver_First_Name"
                 id="Driver_First_Name" 
                 label="First Name"  
                 register={register} 
                 errors={errors} 
                 type='text'  
                 showLabel={true} 
                 className="form-field" 
                 required={true} 
                 lableClass='form-label' 
                 activeBold={true} 
                 defaultValue={modalState.editIndex === null ? "" : modalState.DriverData[modalState.editIndex].FirstName}/>
            </div>
            <div class="form-element has-value">
                <FormControl fieldName="Driver_Last_Name"
                 id="Driver_Last_Name"
                  label="Last Name" 
                  defaultValue={modalState.editIndex === null ? "" : modalState.DriverData[modalState.editIndex].LastName} register={register} errors={errors} type='text' min={0} showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
            </div>

            <div class="grid1">

                <DateInput
                    fieldName="Driver_Date_of_Birth" label="Date Of Birth" defaultValue={modalState.editIndex === null ? "" : modalState.DriverData[modalState.editIndex].DOB} register={register} errors={errors}
                    required={true} setValue={setValue} id='Driver_Date_of_Birth' minDate="-100y"
                    yearRange="-100:+60" onChange={(e) => updateAge(e.target.value)}

                />
            </div>

            <div class="form-element has-value">
                <FormControl fieldName="age" 
                label="Age" 
                id='age' defaultValue={modalState.editIndex === null ? "" : modalState.DriverData[modalState.editIndex].Age} register={register} errors={errors} type='number' min={0} showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
            </div>

            <div class="form-element has-value">
                <FormControl fieldName="total_driver_points" 
                id="total_driver_points" 
                label="Total Driver Points" defaultValue={modalState.editIndex === null ? "" : modalState.DriverData[modalState.editIndex].TotalDriverPoints} register={register} errors={errors} type='number' min={0} showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
            </div>

            <div class="form-element has-value">
                <FormControl fieldName='DriverLicenseNumber'
                id="DriverLicenseNumber"
                label="Driver License Number" defaultValue={modalState.editIndex === null ? "" : modalState.DriverData[modalState.editIndex].DriverLicenseNumber} register={register} errors={errors} type='text' min={0} showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
            </div>

            <div class='form-element form-select'>
                <DropDown fieldName='DriverLicenseState'
                 id="DriverLicenseState" 
                 label="Driver License State" 
                 defaultValue={modalState.editIndex === null ? "" : modalState.DriverData[modalState.editIndex].DriverLicenseState}
                 register={register}
                 errors={errors} 
                 lableClass='form-label' 
                 activeBold={true}
                 >
                   {dropdownVal.map((value, ind) => {
                        return value.State !== "" && value.State !== null && value.State !== undefined ? (
                            <option value={value.State === "-select-" ? "" : value.State}>{value.State}</option>
                        ) :
                            <></>
                    }
                    )}
                </DropDown>
            </div>



            <div class="form-element form-select">
                {/* <FormControl fieldName="Driver_US_Experience"
                 id="Driver_US_Experience" 
                 label="Years of US driving experience" 
                 defaultValue={modalState.editIndex === null ? "" : modalState.DriverData[modalState.editIndex].DriverUSExperience} register={register} errors={errors} type='number' min={0} showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} /> */}
                <DropDown fieldName="Driver_US_Experience"
                id="Driver_US_Experience" 
                label="Years of US driving experience"
                defaultValue={modalState.editIndex === null ? "" : modalState.DriverData[modalState.editIndex].DriverUSExperience} 
                 register={register}
                 errors={errors} 
                 lableClass='form-label' 
                 activeBold={true}
                 >
                   {dropdownVal.map((value, ind) => {
                        return value.DriverExperience !== "" && value.DriverExperience !== null && value.DriverExperience !== undefined ? (
                            <option value={value.DriverExperience === "-select-" ? "" : value.DriverExperience}>{value.DriverExperience}</option>
                        ) :
                            <></>
                    }
                    )}
                </DropDown>
            </div>
        </div>
    )
}
export default AddDriverModalData
