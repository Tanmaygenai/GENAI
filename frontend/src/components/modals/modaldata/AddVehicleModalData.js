import React, { useEffect, useState } from 'react'
// import Check from '../../check/Check';
import DropDown from '../../dropdown/DropDown';
import FormControl from '../../formcontrol/FormControl';
import { drop_data } from '../../../dummydata/data';
import AutoComplete from "react-google-autocomplete";
import Papa from 'papaparse';
import dropdown_val from '../../../dummydata/commercialAuto.csv';
import VINService from '../../../app/services/VINService';
import API_Headers from '../../../API_Headers';
import CryptoJS from "crypto-js";
import $ from "jquery";
import VehicleCodes from '../../../dummydata/VehicleCodes.csv';
const GOOGLE_MAPS_API_KEY = "AIzaSyDCttdJ2FGaxhCc5hkh9vOzRWTGCPy_x-o";

const AddVehicleModalData = ({ register, errors, setValue, modalState,getValues }) => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    const [headers, setHeaders] = useState([])
    const [effCity, setEffCity] = useState("")
    const [effState, setEffState] = useState("")
    const [effZipCode, setEffZipCode] = useState("")
    const [effCountry, setEffCountry] = useState("")
    const [state, setState] = useState("")
    const [vinDetails, setvinDetails] = useState({});
    const [displayVINDiv, setDisplayVINDiv] = useState(false)
    const [vehicleInfo, setVehicleInfo] = useState([]);
    const [displayVehicleInfo, setDisplayVehicleInfo] = useState(false);
    const [vehicleCode, setVehicleCode] = useState([]);
    const [vehicleCSV, setVehicleCSV] = useState([]);
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
        var commonConfig = { delimiter: "," };
        var vehicleCSV
        Papa.parse(
            VehicleCodes,
            {
                ...commonConfig,
                header: true,
                download: true,
                complete: (result) => {
                    setVehicleCSV(result.data)
                    setVehicleCode(result.data)
                }
            }
        );
    }, [])

    function fillInAddress(place, e) {

        let address1 = "";
        let address2 = "";
        let postcode = "";
        let city = "";
        let state = "";
        let country = "";
        if (place.address_components) {
            for (const component of place.address_components) {
                const componentType = component.types[0];

                switch (componentType) {
                    case "street_number": {
                        address1 = `${component.long_name} ${address1}`;
                        break;
                    }

                    case "route": {
                        address1 += component.short_name;
                        break;
                    }

                    case "sublocality_level_1": {
                        address2 = component.long_name;
                        break;
                    }

                    case "postal_code": {
                        postcode = `${component.long_name}${postcode}`;
                        setEffZipCode(postcode)
                        break;
                    }

                    case "postal_code_suffix": {
                        postcode = `${postcode}-${component.long_name}`;
                        //setEffZipCode(postcode)
                        break;
                    }
                    case "locality": {
                        city = component.long_name;
                        sessionStorage.setItem("city", city);
                        setEffCity(city)
                        break;
                    }
                    case "administrative_area_level_1": {
                        state = component.long_name
                        setEffState(state)
                        break;
                    }
                    case "country":
                        country = component.long_name
                        setEffCountry(country)
                        break;
                }
            }
        }

        setState(state)

        setValue('Address', address1)
        setValue('zip_code', postcode)
        setValue('City', city)
        setValue('State', state)
        setValue('country', country)
        e.value = address1

    }
    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

    const getVinDetails = async (index) => {
        setvinDetails([])
        setDisplayVINDiv(false)
        const vin = $(`#VIN`).val();

        var vinDetails = {
            "vin": vin
        }
        try {
            const data1 = CryptoJS.AES.encrypt(
                JSON.stringify(vinDetails),
                secretPass
            ).toString();
            const responseData = await VINService.fetchVINDetails(data1, headers);

            setvinDetails(responseData.data)

            setValue(`Year`, responseData.data.year)
            setValue(`Make`, responseData.data.make)
            setValue(`Model`, responseData.data.model)
            setValue(`bodyType`, responseData.data.bodyType)
            setValue(`CostNew`, responseData.data.costNew)
            setDisplayVINDiv(true)
        }
        catch (error) {
        }
    }

    useEffect(() => {

        if (modalState.VehicleData && modalState.editIndex !== null) {
            setValue('Year', modalState.VehicleData[modalState.editIndex].Year)
            setValue('VIN', modalState.VehicleData[modalState.editIndex].VIN)
            setValue('Make', modalState.VehicleData[modalState.editIndex].Make)
            setValue('Model', modalState.VehicleData[modalState.editIndex].Model)
            setValue('Address', modalState.VehicleData[modalState.editIndex].Address)
            setValue('City', modalState.VehicleData[modalState.editIndex].City)
            setValue('State', modalState.VehicleData[modalState.editIndex].State)
            setValue('zip_code', modalState.VehicleData[modalState.editIndex].PostalCode)
            setValue('country', modalState.VehicleData[modalState.editIndex].Country)
            setValue('RegisteredState', modalState.VehicleData[modalState.editIndex].RegisteredState)
            setValue('VehicleUse', modalState.VehicleData[modalState.editIndex].VehicleUse)
            setValue('VehicleOwnership', modalState.VehicleData[modalState.editIndex].VehicleOwnership)
            setValue('MainDriverofVehicle', modalState.VehicleData[modalState.editIndex].MainDriverofVehicle)
            setValue('Howmanypeoplewilldriveyourvehicle', modalState.VehicleData[modalState.editIndex].Howmanypeoplewilldriveyourvehicle)
            setValue('CostNew', modalState.VehicleData[modalState.editIndex].CostNew)

            setValue('Vehicleconditionwhenpurchased', modalState.VehicleData[modalState.editIndex].Vehicleconditionwhenpurchased)
            setValue('Fleet', modalState.VehicleData[modalState.editIndex].Fleet)
            setValue('PrimaryUse', modalState.VehicleData[modalState.editIndex].PrimaryUse)
            setValue('Radiusofoperation', modalState.VehicleData[modalState.editIndex].Radiusofoperation)
            setValue('UsageType', modalState.VehicleData[modalState.editIndex].UsageType)
            setValue('Sizeclass', modalState.VehicleData[modalState.editIndex].Sizeclass)

            setValue('Comprehensive', modalState.VehicleData[modalState.editIndex].Comprehensive)
            setValue('ComprehensiveDeductible', modalState.VehicleData[modalState.editIndex].ComprehensiveDeductible)
            setValue('Collision', modalState.VehicleData[modalState.editIndex].Collision)
            setValue('CollisionDeductible', modalState.VehicleData[modalState.editIndex].CollisionDeductible)
            setValue('Experience', modalState.VehicleData[modalState.editIndex].Experience)
            setValue('VehicleType', modalState.VehicleData[modalState.editIndex].VehicleType)
            setValue('vehicleCode', modalState.VehicleData[modalState.editIndex].vehicleCode)
        }

    }, [modalState.editIndex])

    const changeCode = () => {
        const Fleet = getValues("Fleet")
        const PrimaryUse = getValues("PrimaryUse")
        const Radiusofoperation = getValues("Radiusofoperation")
        const Sizeclass = getValues("Sizeclass")
        const VehicleType = getValues("VehicleType")
        const Experience = getValues("Experience")

        console.log(Fleet)
        console.log(PrimaryUse)
        console.log(Radiusofoperation)
        console.log(Sizeclass)
        console.log(VehicleType)
        console.log(Experience)
        const item = vehicleCode.filter((item, index) => {
            if (Fleet !== null || VehicleType !== null) {
                if (Experience !== null || Radiusofoperation !== null) {
                    return item
                } else {
                    return item;
                }
            } else {
                return;
            }

            // if (item.Fleet == Fleet && item.PrimaryUse == PrimaryUse &&
            //     item.Radius == Radiusofoperation && item.SizeClass == Sizeclass &&
            //     item.Type == UsageType) {
            //     return item
            // }
        })

        if (item.length > 0) {
            setVehicleCode(item)
        } else {
            setVehicleCode(vehicleCSV)
        }
    }
    return (
        <div className="grid1">
            <div className="grid4">
                {/* <div className="form-element">
                    <FormControl fieldName="Year"
                        id="Year"
                        label="Year"
                        register={register}
                        errors={errors}
                        type='number'
                        min={0}
                        maxLength={4}
                        minLength={4}
                        showLabel={true}
                        className="form-field"
                        required={true}
                        lableClass='form-label'
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Year}
                        activeBold={true}
                    />
                </div> */}
                <div className="form-element">
                    <FormControl fieldName="VIN"
                        id="VIN"
                        label="VIN"
                        register={register}
                        errors={errors}
                        type='text'
                        showLabel={true}
                        className="form-field"
                        required={true}
                        lableClass='form-label'
                        activeBold={true}
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].VIN}
                    />
                </div>
                <div class="form-element">
                    <button type='button' className='btn blue' id={"getVinDetails" + modalState.editIndex} onClick={(e) => getVinDetails(modalState.editIndex)}>Get VIN Details</button>
                </div>
            </div>

            {
                vinDetails !== null && displayVINDiv ?
                    <div className="grid3">
                        <div className="form-element">
                            <FormControl fieldName="Year"
                                id="Year"
                                label="Year"
                                register={register}
                                errors={errors}
                                type='number'
                                min={0}
                                maxLength={4}
                                minLength={4}
                                showLabel={true}
                                className="form-field"
                                required={true}
                                lableClass='form-label'
                                defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Year}
                                activeBold={true}
                            />
                        </div>
                        <div className='form-element'>
                            <FormControl fieldName='Make'
                                id="Make"
                                defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Make}
                                register={register}
                                errors={errors}
                                lableClass='form-label'
                                activeBold={true}
                                showLabel={true}
                                className="form-field"
                                required={true}>
                                {/* {drop_data.Make.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                                {/* {dropdownVal.map((value, ind) => {
                        return value.Make !== "" && value.Make !== null && value.Make !== undefined ? (
                            <option value={value.Make === "-select-" ? "" : value.Make}>{value.Make}</option>
                        ) :
                            <></>
                    }
                    )} */}
                            </FormControl>
                        </div>
                        <div className='form-element form-select' >
                            <DropDown fieldName='Vehicle Type'
                                id="VehicleType"
                                defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].VehicleType}
                                register={register}
                                errors={errors}
                                lableClass='form-label'
                                activeBold={true}>
                                {/* {drop_data.VehicleType.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                                {dropdownVal.map((value, ind) => {
                                    return value.VehicleType !== "" && value.VehicleType !== null && value.VehicleType !== undefined ? (
                                        <option value={value.VehicleType === "-select-" ? "" : value.VehicleType}>{value.VehicleType}</option>
                                    ) :
                                        <></>
                                }
                                )}
                            </DropDown>
                        </div>
                        <div className="form-element">
                            <FormControl fieldName="Model"
                                id="Model"
                                label="Model"
                                defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Model}
                                register={register}
                                errors={errors}
                                type='text'
                                showLabel={true}
                                className="form-field"
                                required={true}
                                lableClass='form-label' activeBold={true} />
                        </div>
                        <div className="form-element">
                            <FormControl fieldName="Cost New"
                                id="CostNew"
                                label="Cost New"
                                defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].CostNew}
                                register={register}
                                errors={errors}
                                type='text'
                                showLabel={true}
                                className="form-field"
                                required={true}
                                lableClass='form-label'
                                activeBold={true} />
                        </div>
                    </div> :
                    <></>
            }


            <div className="grid3">
                <div class='form-element'>
                    <label className='form-label'>Garage Address <b>*</b></label>
                    <AutoComplete
                        apiKey={GOOGLE_MAPS_API_KEY}
                        placeholder=""
                        fieldName="Address"
                        id="Address"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Address}
                        className='form-control form-field'
                        style={{ width: "100%" }}
                        label="Address"
                        onPlaceSelected={(e, place) => {
                            fillInAddress(e, place)
                        }}
                        options={{
                            types: ["(regions)"],
                            componentRestrictions: { country: ["us", "ca"] },
                            fields: ["address_components", "geometry"],
                            types: ["address"]
                        }}
                    />
                </div>
                <div className="grid3">
                    <div className='form-element has-value'>
                        <FormControl fieldName="City"
                            label='City'
                            id='city'
                            defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].City}
                            register={register}
                            errors={errors}
                            type="text"
                            showLabel={true}
                            className="form-field"
                            required={true}
                            lableClass='form-label'
                            activeBold={true}
                        />
                    </div>
                    <div className='form-element has-value'>
                        <FormControl fieldName="State"
                            label='State'
                            id='state'
                            defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].State}
                            register={register}
                            errors={errors}
                            type="text"
                            showLabel={true}
                            className="form-field"
                            required={true}
                            lableClass='form-label'
                            activeBold={true}
                        />
                    </div>
                    <div className='form-element has-value'>
                        <FormControl fieldName="zip_code"
                            label='Postcode'
                            id='postcode'
                            defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].PostalCode}
                            register={register}
                            errors={errors}
                            type="text"
                            showLabel={true}
                            className="form-field"
                            required={true}
                            lableClass='form-label'
                            activeBold={true}
                        />
                    </div>
                </div>
                <div className='form-element has-value'>
                    <FormControl fieldName="country"
                        label='Country'
                        id='country'
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Country}
                        register={register}
                        errors={errors}
                        type="text"
                        showLabel={true}
                        className="form-field"
                        required={true}
                        lableClass='form-label'
                        activeBold={true}
                    />
                </div>
            </div>

            <div className="grid3">
                <div className='form-element form-select'>
                    <DropDown fieldName='Registered State'
                        id="RegisteredState"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].RegisteredState}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {/* {drop_data.State.map((value, ind) => (
                        <option value={value.key}>{value.value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.State !== "" && value.State !== null && value.State !== undefined ? (
                                <option value={value.State === "-select-" ? "" : value.State}>{value.State}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select'>
                    <DropDown fieldName='Vehicle Use'
                        id="VehicleUse"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].VehicleUse}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>{dropdownVal.map((value, ind) => {
                            return value.VehicleUse !== "" && value.VehicleUse !== null && value.VehicleUse !== undefined ? (
                                <option value={value.VehicleUse === "-select-" ? "" : value.VehicleUse}>{value.VehicleUse}</option>
                            ) :
                                <></>
                        }
                        )}
                        {/* {drop_data.VehicleUse.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}

                    </DropDown>
                </div>
                <div className='form-element form-select'>
                    <DropDown fieldName='Vehicle Ownership'
                        id="VehicleOwnership"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].VehicleOwnership}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {/* {drop_data.VehicleOwnership.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.VehicleOwnership !== "" && value.VehicleOwnership !== null && value.VehicleOwnership !== undefined ? (
                                <option value={value.VehicleOwnership === "-select-" ? "" : value.VehicleOwnership}>{value.VehicleOwnership}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
            </div>

            <div className='grid3'>
                <div className='form-element form-select'>
                    <DropDown fieldName='Main Driver of Vehicle'
                        id="MainDriverofVehicle"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].MainDriverofVehicle}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {/* {drop_data.MainDriverofVehicle.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.MainDriverofVehicle !== "" && value.MainDriverofVehicle !== null && value.MainDriverofVehicle !== undefined ? (
                                <option value={value.MainDriverofVehicle === "-select-" ? "" : value.MainDriverofVehicle}>{value.MainDriverofVehicle}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select'>
                    <DropDown fieldName='How many people will drive your vehicle'
                        id="Howmanypeoplewilldriveyourvehicle"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Howmanypeoplewilldriveyourvehicle}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {/* {drop_data.NumberOfDrivers.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.NumberOfDrivers !== "" && value.NumberOfDrivers !== null && value.NumberOfDrivers !== undefined ? (
                                <option value={value.NumberOfDrivers === "-select-" ? "" : value.NumberOfDrivers}>{value.NumberOfDrivers}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select'>
                    <DropDown fieldName='Vehicle condition when purchased'
                        id="Vehicleconditionwhenpurchased"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Vehicleconditionwhenpurchased}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}
                    >
                        {dropdownVal.map((value, ind) => {
                            return value.Vehicleconditionwhenpurchased !== "" && value.Vehicleconditionwhenpurchased !== null && value.Vehicleconditionwhenpurchased !== undefined ? (
                                <option value={value.Vehicleconditionwhenpurchased === "-select-" ? "" : value.Vehicleconditionwhenpurchased}>{value.Vehicleconditionwhenpurchased}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select' onChange={() => changeCode()}>
                    <DropDown fieldName='Fleet'
                        id="Fleet"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Fleet}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {/* {drop_data.Fleet.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.FleetLimit !== "" && value.FleetLimit !== null && value.FleetLimit !== undefined ? (
                                <option value={value.FleetLimit === "-select-" ? "" : value.FleetLimit}>{value.FleetLimit}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select' onChange={() => changeCode()}>
                    <DropDown fieldName='Primary Use'
                        id="PrimaryUse"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].PrimaryUse}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {/* {drop_data.PrimaryUse.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.PrimaryUse !== "" && value.PrimaryUse !== null && value.PrimaryUse !== undefined ? (
                                <option value={value.PrimaryUse === "-select-" ? "" : value.PrimaryUse}>{value.PrimaryUse}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select' onChange={() => changeCode()}>
                    <DropDown fieldName='Radius of operation'
                        id="Radiusofoperation"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Radiusofoperation}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {/* {drop_data.Radiusofoperation.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.Radiusofoperation !== "" && value.Radiusofoperation !== null && value.Radiusofoperation !== undefined ? (
                                <option value={value.Radiusofoperation === "-select-" ? "" : value.Radiusofoperation}>{value.Radiusofoperation}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select' onChange={() => changeCode()}>
                    <DropDown fieldName=' Usage Type'
                        id=" UsageType"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].UsageType}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {dropdownVal.map((value, ind) => {
                            return value.UsageType !== "" && value.UsageType !== null && value.UsageType !== undefined ? (
                                <option value={value.UsageType === "-select-" ? "" : value.UsageType}>{value.UsageType}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select' onChange={() => changeCode()}>
                    <DropDown fieldName='Size class'
                        id="Sizeclass"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Sizeclass}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {/* {drop_data.Sizeclass.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.Sizeclass !== "" && value.Sizeclass !== null && value.Sizeclass !== undefined ? (
                                <option value={value.Sizeclass === "-select-" ? "" : value.Sizeclass}>{value.Sizeclass}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select' onChange={() => changeCode()}>
                    <DropDown fieldName='Experience'
                        id="Experience"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Experience}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {/* {drop_data.Experience.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.Experience !== "" && value.Experience !== null && value.Experience !== undefined ? (
                                <option value={value.Experience === "-select-" ? "" : value.Experience}>{value.Experience}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select'>
                    <DropDown fieldName='Vehicle Code'
                        id="vehicleCode"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].vehicleCode}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}
                        required={false}
                    >
                        {vehicleCode.map((value, ind) => {
                            return value !== "" && value !== null && value !== undefined && value.Code !== undefined
                                && value.Code !== '' && value.Code.length > 0 ? (
                                <option value={value.Code}>{value.Code}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select'>
                    <DropDown fieldName='Comprehensive'
                        id="Comprehensive"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Comprehensive}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}
                        required={false}
                    >
                        {/* {drop_data.Comprehensive.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.Comprehensive !== "" && value.Comprehensive !== null && value.Comprehensive !== undefined ? (
                                <option value={value.Comprehensive === "-select-" ? "" : value.Comprehensive}>{value.Comprehensive}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select'>
                    <DropDown fieldName='Comprehensive Deductible'
                        id="ComprehensiveDeductible"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].ComprehensiveDeductible}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {/* {drop_data.ComprehensiveDeductible.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.ComprehensiveDeductible !== "" && value.ComprehensiveDeductible !== null && value.ComprehensiveDeductible !== undefined ? (
                                <option value={value.ComprehensiveDeductible === "-select-" ? "" : value.ComprehensiveDeductible}>{value.ComprehensiveDeductible}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select'>
                    <DropDown fieldName='Collision'
                        id="Collision"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].Collision}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}
                        required={false}
                    >
                        {/* {drop_data.Collision.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.Collision !== "" && value.Collision !== null && value.Collision !== undefined ? (
                                <option value={value.Collision === "-select-" ? "" : value.Collision}>{value.Collision}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
                <div className='form-element form-select'>
                    <DropDown fieldName='Collision Deductible'
                        id="CollisionDeductible"
                        defaultValue={modalState.editIndex === null ? "" : modalState.VehicleData[modalState.editIndex].CollisionDeductible}
                        register={register}
                        errors={errors}
                        lableClass='form-label'
                        activeBold={true}>
                        {/* {drop_data.CollisionDeductible.map((value, ind) => (
                        <option value={value}>{value}</option>
                    ))} */}
                        {dropdownVal.map((value, ind) => {
                            return value.Collisiondeductible !== "" && value.Collisiondeductible !== null && value.Collisiondeductible !== undefined ? (
                                <option value={value.Collisiondeductible === "-select-" ? "" : value.Collisiondeductible}>{value.Collisiondeductible}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                </div>
            </div>


        </div>
    );
};

export default AddVehicleModalData;
