
export const modalDefaultValue = (state, modalState, setModalState) => {

    var updatedArray = []
    state.application.underlyingPolicies.map((val, ind) => {

        if (val.autoUnderLyingPolicy) {
            updatedArray.push({
                AddUnderlyingPolicies: "Commercial Auto",
                CombinedSingleLimit: val.autoUnderLyingPolicy.combinedSingleLimit,
                underlying_premium: val.autoUnderLyingPolicy.underlyingPremium,
                LightVehicle: val.autoUnderLyingPolicy.vehicles.lightVehicles,
                MediumVehicle: val.autoUnderLyingPolicy.vehicles.mediumVehicles,
                HeavyVehicle: val.autoUnderLyingPolicy.vehicles.heavyVehicles,
                ExtraHeavyVehicle: val.autoUnderLyingPolicy.vehicles.extraHeavyVehicles,
            })
        }
        else if (val.monolineUnderLyingInsurance) {
            updatedArray.push({
                AddUnderlyingPolicies: "Commercial Monoline GL (or GL Portion of Commercial Package)",
                class_code_or_Description: val.monolineUnderLyingInsurance.classCd,
                underlying_premium: val.monolineUnderLyingInsurance.underlyingPremium,
                EachOccurrenceLimit: val.monolineUnderLyingInsurance.limits.eachOccLmt,
                ProductsandCompletedOpsAggregateLimit: val.monolineUnderLyingInsurance.limits.prodAndComp,
                GeneralAggregateLimit: val.monolineUnderLyingInsurance.limits.generalAggr,
                PersonalAdvertisingLimit: val.monolineUnderLyingInsurance.limits.personalAndAdv
            })
        }
        else if (val.excessUnderLyingInsurance) {
            updatedArray.push({
                AddUnderlyingPolicies: "Excess/Umbrella",
                underlying_premium: val.excessUnderLyingInsurance.underlyingPremium,
                LimitofLiability: val.excessUnderLyingInsurance.liabilityLimit
            })
        }
        else if (val.otherUnderLyingInsurance) {
            updatedArray.push({
                AddUnderlyingPolicies: "Other",
                PolicyType: val.otherUnderLyingInsurance.insuranceType,
                class_code_or_Description: val.otherUnderLyingInsurance.classCd,
                underlying_premium: val.otherUnderLyingInsurance.underlyingPremium,
                OccurrenceLimitEachAccident: val.otherUnderLyingInsurance.limits.employeeBenefitLiabLimit && val.otherUnderLyingInsurance.limits.employeeBenefitLiabLimit,
                EachOccurrenceLimit: val.otherUnderLyingInsurance.limits.eachOccLmt && val.otherUnderLyingInsurance.limits.eachOccLmt,
                GeneralAggregateLimit: val.otherUnderLyingInsurance.limits.generalAggr && val.otherUnderLyingInsurance.limits.generalAggr,
                AutoLimit: val.otherUnderLyingInsurance.limits.autoLimit && val.otherUnderLyingInsurance.limits.autoLimit,
            })
        }
    })
    setModalState({ ...modalState, AddPolicyData: updatedArray })
}


export const CommPropModalDefaultValue = (state, modalState, setModalState) => {
    var updatedLocArray = []
    var updatedBulArray = []
    state.application.locations.map((val, ind) => { 
        updatedLocArray.push({
            LocationDesc: val.locationDesc,
            StreetName: val.addr.addr1,
            City: val.addr.city,
            State: val.addr.state,
            PostalCode: val.addr.postalCode,
            Country: val.addr.country
        })
    })

    state.application.insuranceLine.map((val, ind) => { 
        val.risk.map((riskVal, riskInd) =>{
            riskVal.buildings.forEach((buildingVal, buildingInd) =>{
                updatedBulArray.push({
                    LocationNo:buildingVal.buildingDescription,
                    ProtectiveDevices:buildingVal.protectiveDevices,
                    ConstructionType:buildingVal.constructionType,
                    RoofType:buildingVal.roofType,
                    YearBuilt:buildingVal.yearBuilt,
                    Sprinklered:buildingVal.sprinklered,
                    SquareFootage:buildingVal.squareFootage,
                    EstimatedReplacement:buildingVal.estimatedReplacement,
                    BuildingLimit: buildingVal.coverages[0].limits[0].value,
                    BuildingDeductible: buildingVal.coverages[0].deductibles[0].value,
                    BusinessPersonalPropertyLimit: buildingVal.coverages[1].limits[0].value,
                    BusinessPersonalPropertyDeductible: buildingVal.coverages[1].deductibles[0].value,
                })
            })
        })
        
    }) 
    setModalState({ ...modalState, LocationData: updatedLocArray, BuildingLocData: updatedBulArray })
}

export const CommAutoModalDefaultValue = (state, modalState, setModalState) => {
    var updatedVehicleArray = []
    var updatedDriverArray = []
    state.application.insuranceLine[0].risks.map((val, ind) => {
        updatedVehicleArray.push({
            Make: val.vehicleInfo.manufacturer,
            Model:val.vehicleInfo.model,
            Year:val.vehicleInfo.modelYear,
            VIN:val.vehicleInfo.vin,
            MainDriverofVehicle:val.vehicleInfo.mainDriverofVehicle,
            VehicleUse:val.vehicleInfo.vehicleUse,
            Howmanypeoplewilldriveyourvehicle:val.vehicleInfo.numberOfDrivers,
            VehicleOwnership:val.vehicleInfo.vehicleOwnership,
            RegisteredState:val.vehicleInfo.registeredState,
            GarageAddress:val.vehicleInfo.garageAddress,
            CostNew:val.vehicleInfo.costNew,
            Address:val.vehicleInfo.garageAddress.addr1,
            City:val.vehicleInfo.garageAddress.city,
            State:val.vehicleInfo.garageAddress.state,
            PostalCode:val.vehicleInfo.garageAddress.postalCode,
            Country:val.vehicleInfo.garageAddress.busCountry,

         })
    })

    state.application.drivers.map((val, ind) => {
        updatedDriverArray.push({
            Age: val.age,
            TotalDriverPoints:val.totalPoints,
            FirstName:val.givenName,
            LastName:val.surname,
            DOB:val.birthDt,
            DriverLicenseState:val.licenseState,
            DriverLicenseNumber:val.licenseNumber,
            DriverUSExperience:val.yearsOfUSExperience,
         })
    })
   
    setModalState({ ...modalState, VehicleData: updatedVehicleArray, DriverData: updatedDriverArray })
}