import { v4 as uuid } from 'uuid';
import { StateName } from "../dummydata/StateList";
import { vehicleCondition, vehicleType, fleet, termType, quoteType, radiusOfOperation, driverExperience, usageType, sizeClass, primaryUse } from '../dummydata/commercialAutoData';

export const CommercialAutoResopnseJson = (data, state, quoteId) => {
    if (typeof quoteId === 'undefined') { quoteId = 0 }
    // console.log("id from in json is :",state.application.id)
    console.log(data)

    const unique_id = uuid();
    var controllingState = ""
    StateName.map((val, ind) => {
        if (val.name === data.busState)
            controllingState = val.code
    })
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

        const formattedOutput = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}+05:30`;

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
    const getVehicleCondition = (vehicleConddata) => {
        const vehicleCond = vehicleCondition.filter((item, index) => {
            if (vehicleConddata === item.name) {
                return item
            }
        })
        return vehicleCond[0]?.code
    }

    const getFleet = (fleetData) => {
        console.log(fleetData)
        const fleetCode = fleet.filter((item, index) => {
            if (fleetData === item.name) {
                return item
            }
        })
        console.log(fleetCode)
        return fleetCode[0]?.code
    }

    const getTermType = (termTypeData) => {
        console.log(termTypeData)
        const termTypeCode = termType.filter((item, index) => {
            if (termTypeData === item.name) {
                return item
            }
        })
        console.log(termTypeCode)
        return termTypeCode[0]?.code
    }

    const getQuoteType = (quoteTypeData) => {
        console.log(quoteTypeData)
        const quoteTypeCode = quoteType.filter((item, index) => {
            if (quoteTypeData === item.name) {
                return item
            }
        })
        console.log(quoteTypeCode)
        return quoteTypeCode[0]?.code
    }
    const getRadiusofoperation = (RadiusofoperationData) => {
        console.log(RadiusofoperationData)
        const RadiusofoperationCode = radiusOfOperation.filter((item, index) => {
            if (RadiusofoperationData === item.name) {
                return item
            }
        })
        console.log(RadiusofoperationCode)
        return RadiusofoperationCode[0]?.code
    }
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
    const getusageType = (usageTypeData) => {
        console.log(usageTypeData)
        const usageTypeCode = usageType.filter((item, index) => {
            if (usageTypeData === item.name) {
                return item
            }
        })
        console.log(usageTypeCode)
        return usageTypeCode[0]?.code
    }
    const getsizeClass = (sizeClassData) => {
        console.log(sizeClassData)
        const sizeClassCode = sizeClass.filter((item, index) => {
            if (sizeClassData === item.name) {
                return item
            }
        })
        console.log(sizeClassCode)
        return sizeClassCode[0]?.code
    }
    const getprimaryUse = (primaryUseData) => {
        console.log(primaryUseData)
        const primaryUseCode = primaryUse.filter((item, index) => {
            if (primaryUseData === item.name) {
                return item
            }
        })
        console.log(primaryUseCode)
        return primaryUseCode[0]?.code
    }

    const drivers = data.DriverData.map((val, ind) => {
        return {
            "driverNumber": ind + 1,
            "age": val.Age,
            "totalPoints": val.TotalDriverPoints,
            "givenName": val.FirstName,
            "surname": val.LastName,
            "birthDt": getDate(val.DOB),
            "licenseState": getLicenseState(val.DriverLicenseState) ? getLicenseState(val.DriverLicenseState) : val.DriverLicenseState,
            "licenseNumber": val.DriverLicenseNumber,
            "driverExperience": val.driverExperience,
            "yearsOfUSExperience": val.DriverUSExperience
        }
    })
    const vehicles = data.AddedVehicles.map((val, ind) => {
        return {
            "riskTypeCd": "CommercialVehicle",
            "vehicleInfo": {
                "vehicleNumber": ind + 1,
                "manufacturer": val.Make,
                "modelYear": val.Year,
                "vin": val.VIN,
                "model": val.Model,
                "registeredState": val.RegisteredState,
                "vehicleUse": val.VehicleUse,
                "vehicleOwnership": val.VehicleOwnership,
                "mainDriverofVehicle": val.MainDriverofVehicle,
                "numberOfDrivers": val.Howmanypeoplewilldriveyourvehicle,
                "costNew": val.CostNew.includes("usd") ? val.CostNew : val.CostNew + " usd",
                "garageAddress": val.GarageAddress,
                "garageAddress": {
                    "addr1": val.Address,
                    "city": val.City,
                    "state": val.State,
                    "postalCode": val.PostalCode,
                    "busCountry": val.Country,
                },

                "grossVehicleWeight": val["Gross Weight"],
                "drivingRadius": val.Radius,
                "vehicleSymbol": val.Symbol,
                "Vehicleconditionwhenpurchased": getVehicleCondition(val.Vehicleconditionwhenpurchased) ? getVehicleCondition(val.Vehicleconditionwhenpurchased) : val.Vehicleconditionwhenpurchased,
                "Fleet": getFleet(val.Fleet) ? getFleet(val.Fleet) : val.Fleet,
                "PrimaryUse": getprimaryUse(val.PrimaryUse) ? getprimaryUse(val.PrimaryUse) : val.PrimaryUse,
                "Radiusofoperation": getRadiusofoperation(val.Radiusofoperation) ? getRadiusofoperation(val.Radiusofoperation) : val.Radiusofoperation,
                "UsageType": getusageType(val.UsageType),
                "VehicleType": getVehicleType(val.VehicleType) ? getVehicleType(val.VehicleType) : val.VehicleType,
                "Sizeclass": getsizeClass(val.Sizeclass) ? getsizeClass(val.Sizeclass) : val.Sizeclass,
                "VehicleCode": val.vehicleCode,
                'Comprehensive': val.Comprehensive,
                'ComprehensiveDeductible': (val.ComprehensiveDeductible).substring(1).replace(',','') !== "00" ? (val.ComprehensiveDeductible).substring(1).replace(',','') : val.ComprehensiveDeductible,
                'Collision': val.Collision,
                'Experience': getdriverExperience(val.Experience) ? getdriverExperience(val.Experience) : val.Experience,
                'CollisionDeductible': val.CollisionDeductible
                //"costNew": val["Vehicle Cost"],
            },
            "commercialPolicyInfo": {
                "fullTimeEmployees": data.number_of_Employees, //fetch it from coverage screen-number of employees
                "primaryOccupation": data.primary_occupation,//fetch it from Product and classification screen- primary occupation
                "secondaryOccupation": data.secondry_occupation, // fetch it from Product and classification screen- secondary occupation

            },
        }
    })


    const obj = {
        "application": {
            "id": quoteId,
            "externalId": unique_id,
            "ProductType": data.ProductType,
            "policyPremiumInfo": {
                "fullTermAmount": null,
                "triaPremium": null,
                "policyFees": {
                    "Policy": "",
                    "PUC": "",
                    "Fraud": "",
                    "DMV": "",
                    "Cargo": "",
                    "DOT": ""
                }
            },
            "policyInfo": {
                "policyType": "Commercial Auto",
                "effectiveDt": data.EffectiveDate,
                "controllingState": data.busState,
                "territoryCd": data.territoryCd,
                "QuoteType": getQuoteType(data.QuoteType),
                "TermType": getTermType(data.TermType),
                "orgType": "individual",
                "contactSubtype": "Company"
                //"controllingState":controllingState
            },
            "insuredInfo": {
                "commercialName": data.InsuredName,
                "givenName": data.InsuredName.split(" ")[0].trim(),
                "surName": data.InsuredName.split(" ")[1].trim(),
                "dba": data.dba,
                "organization": data.organization,
                // "producerCode": data.producerCode,
                "producerCode": "100-002541",
                "dob": data.Dob,
                "occupation": data.Occupation,
                "maritalStatus": data.MaritalStatus,
                "gender": data.Gender,
                "primaryBusinessInfo": {
                    "businessType": data.PrimaryBusinessType,
                    "productType": data.ProductType,
                    "fleetLimit": data.FleetLimit,
                    "businessSubType": data.PrimaryBusinessSubType,
                    "businessYears": data.BusinessYears,
                    "primaryOperationsZip": data.buisness_primary_zip

                },
            },
            "locations": [
                {
                    "locationNumber": 1,
                    "AddressType": data.AddressType,
                    "addr": {
                        "addr1": data.insuredAddress,
                        "city": data.busCity,
                        "state": data.busState,
                        "postalCode": data.busPostalCode,
                        "busCountry": data.busCountry,
                    }
                }
            ],
            "insuranceLine": [
                {
                    "lineCd": "Commercial Auto",
                    "towingAndLaborInd": data.TowingandLabor,
                    "liabilityLimit": data.LiabilityLimit,
                    "rentalReimbursementInd": data.RentalReimbursement,
                    "medicalPayments": data.MedicalPayments,
                    "nonOwnedInd": true,
                    // "comprehensiveDed": data.ComprehensiveDeductible,
                    // "collisionDed": data.CollisionDeductible,
                    "nonOwnedAuto": data.NonOwnedAuto,
                    "numberOfHiredVehicles": data.number_of_hired_vehicles,
                    "coverages": [
                        {
                            "coverageCd": "Liability",
                            "limits": [
                                {
                                    "limitCd": "Limit1",
                                    "value": data.LiabilityLimit
                                },
                            ],
                            "deductibles": [
                                {
                                    "deductibleCd": "Deductible1",
                                    "value": ""
                                }
                            ]
                        },


                    ],
                    "risks": vehicles,

                }
            ],
            "drivers": drivers,
        }
    }
    return (obj)

};