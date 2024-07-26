import { v4 as uuid } from 'uuid';
import { StateName } from "../dummydata/StateList";
import { vehicleCondition, vehicleType, fleet, termType, quoteType, radiusOfOperation, driverExperience, usageType, sizeClass, primaryUse } from '../dummydata/commercialAutoData';

export const CommercialAutoModalStateResopnseJson = (state) => {
    console.log("json", state)
    //if (typeof quoteId === 'undefined') { quoteId = 0 }
    // console.log("id from in json is :",state.application.id)
    //console.log("modalState", modalState)

    const unique_id = uuid();
    var controllingState = ""
    // StateName.map((val, ind) => {
    //     if (val.name === data.busState)
    //         controllingState = val.code
    // })
    const getVehicleType = (vehicleTypeData) => {
        console.log(vehicleTypeData)
        const vehicleTypeCode = vehicleType.filter((item, index) => {
            if (vehicleTypeData === item.name) {
                return item
            }
        })
        return vehicleTypeCode[0]?.code
    }

    const getDate = (date) => {
        const currentDate = new Date(date);

        const year = currentDate.getUTCFullYear();
        const month = (currentDate.getUTCMonth() + 1).toString().padStart(2, '0');
        const day = currentDate.getUTCDate().toString().padStart(2, '0');
        const hours = currentDate.getUTCHours().toString().padStart(2, '0');
        const minutes = currentDate.getUTCMinutes().toString().padStart(2, '0');
        const seconds = currentDate.getUTCSeconds().toString().padStart(2, '0');

        const formattedOutput = `${year}-${month}-${day}`;

        return formattedOutput
    }

    const getLicenseState = (stateName) => {
        const stateCode = StateName.filter((item, index) => {
            if (item.name === stateName) {
                return item
            }
        })
        return stateCode[0]?.code
    }

    // const getVehicleCondition = (vehicleConddata) => {
    //     const vehicleCond = vehicleCondition.filter((item, index) => {
    //         if (vehicleConddata === item.name) {
    //             return item
    //         }
    //     })
    //     return vehicleCond[0]?.code
    // }

    // const getFleet = (fleetData) => {
    //     console.log(fleetData)
    //     const fleetCode = fleet.filter((item, index) => {
    //         if (fleetData === item.name) {
    //             return item
    //         }
    //     })
    //     console.log(fleetCode)
    //     return fleetCode[0]?.code
    // }

    // const getTermType = (termTypeData) => {
    //     console.log(termTypeData)
    //     const termTypeCode = termType.filter((item, index) => {
    //         if (termTypeData === item.name) {
    //             return item
    //         }
    //     })
    //     console.log(termTypeCode)
    //     return termTypeCode[0]?.code
    // }

    // const getQuoteType = (quoteTypeData) => {
    //     console.log(quoteTypeData)
    //     const quoteTypeCode = quoteType.filter((item, index) => {
    //         if (quoteTypeData === item.name) {
    //             return item
    //         }
    //     })
    //     console.log(quoteTypeCode)
    //     return quoteTypeCode[0]?.code
    // }
    // const getRadiusofoperation = (RadiusofoperationData) => {
    //     console.log(RadiusofoperationData)
    //     const RadiusofoperationCode = radiusOfOperation.filter((item, index) => {
    //         if (RadiusofoperationData === item.name) {
    //             return item
    //         }
    //     })
    //     console.log(RadiusofoperationCode)
    //     return RadiusofoperationCode[0]?.code
    // }
    const getdriverExperience = (driverExperienceData) => {
        console.log(driverExperienceData)
        const driverExperienceCode = driverExperience.filter((item, index) => {
            if (driverExperienceData === item.name) {
                return item
            }
        })
        console.log(driverExperienceCode)
        return driverExperienceCode[0]?.code
    }
    // const getusageType = (usageTypeData) => {
    //     console.log(usageTypeData)
    //     const usageTypeCode = usageType.filter((item, index) => {
    //         if (usageTypeData === item.name) {
    //             return item
    //         }
    //     })
    //     console.log(usageTypeCode)
    //     return usageTypeCode[0]?.code
    // }
    // const getsizeClass = (sizeClassData) => {
    //     console.log(sizeClassData)
    //     const sizeClassCode = sizeClass.filter((item, index) => {
    //         if (sizeClassData === item.name) {
    //             return item
    //         }
    //     })
    //     console.log(sizeClassCode)
    //     return sizeClassCode[0]?.code
    // }
    // const getprimaryUse = (primaryUseData) => {
    //     console.log(primaryUseData)
    //     const primaryUseCode = primaryUse.filter((item, index) => {
    //         if (primaryUseData === item.name) {
    //             return item
    //         }
    //     })
    //     console.log(primaryUseCode)
    //     return primaryUseCode[0]?.code
    // }

    const drivers = JSON.parse(state.indicationData).application.drivers.map((val, ind) => {
        console.log("drivervals",val)
        return {
            "Age": val.age,
            "TotalDriverPoints": val.totalPoints,
            "FirstName": val.givenName,
            "LastName": val.surname,
            "DOB": getDate(val.birthDt),
            "DriverLicenseState": val.licenseState,
            "DriverLicenseNumber": val.licenseNumber,
            "DriverUSExperience": val.yearsOfUSExperience
        }
    })
    const vehicles = JSON.parse(state.indicationData).application.insuranceLine[0].risks.map((val, ind) => {
        console.log("val", val)
        console.log("registeredstate", val.vehicleInfo.registeredState)
        return {
            "Year": val.vehicleInfo.modelYear,
            "VIN": val.vehicleInfo.vin,
            "Make": val.vehicleInfo.manufacturer,
            "Collision Deductible": val.vehicleInfo.CollisionDeductible,
            "Model": val.vehicleInfo.model,
            "RegisteredState": val.vehicleInfo.registeredState,
            "VehicleUse": val.vehicleInfo.vehicleUse,
            "VehicleOwnership": val.vehicleInfo.vehicleOwnership,
            "MainDriverofVehicle": val.vehicleInfo.mainDriverofVehicle,
            "Howmanypeoplewilldriveyourvehicle": val.vehicleInfo.numberOfDrivers,
            "CostNew": val.vehicleInfo.costNew,
            "Address": val.vehicleInfo.garageAddress.addr1,
            "City": val.vehicleInfo.garageAddress.city,
            "State": val.vehicleInfo.garageAddress.state,
            "PostalCode": val.vehicleInfo.garageAddress.postalCode,
            "Country": val.vehicleInfo.garageAddress.busCountry,
            "Vehicleconditionwhenpurchased": val.vehicleInfo.vehicleconditionwhenpurchased,
            "Fleet": val.vehicleInfo.fleet,
            "PrimaryUse": val.vehicleInfo.primaryUse,
            "Radiusofoperation": val.vehicleInfo.radiusofoperation,
            "UsageType": val.vehicleInfo.usageType,
            "Sizeclass": val.vehicleInfo.sizeclass,
            "Comprehensive": val.vehicleInfo.comprehensive,
            "ComprehensiveDeductible": val.vehicleInfo.comprehensiveDeductible,
            "Collision": val.vehicleInfo.collision,
            //"Experience": val[ind].vehicleInfo.,
            "CollisionDeductible": val.vehicleInfo.CollisionDeductible,
            "VehicleType": val.vehicleInfo.VehicleType
        }
    })


    const obj = {
        "modalState" : {
            "addPolicyTitle": "",
            "AddPolicyData": [],
            "Other_Policy_Data": [],
            "ModalData": [],
            "DriverData": drivers,
            "VehicleData": vehicles,
            "BuildingLocData": [],
            "ClaimData": [],
            "editIndex": null,
            "LocationData": [],
            "CoverageData": [],
            "BuildingRiskData": []
        }
    }
    return (obj)

};