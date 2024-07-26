import { v4 as uuid } from 'uuid';
export const CommercialPropertyResopnseJson = (data, state,quoteId) => {
    if (typeof quoteId === 'undefined'){quoteId=0}
    const unique_id = uuid();
    let addedBuildings = []
    if(data?.AddedLocations && data?.AddedLocations.length > 0) {
        addedBuildings = data?.AddedLocations.map((val, ind) => {
        var buildings = []
        data?.BuildingLocData && data?.BuildingLocData.length > 0 && data?.BuildingLocData.forEach((value, index) => {
            if (value?.LocationNo === val.LocationDesc)
                buildings.push({
                    "riskTypeCd": "CommercialProperty",
                    "riskIdRef": index + 1,
                    "locationRef": index + 1,
                    "buildingDescription": value?.LocationNo || '***',
                    "protectiveDevices": value?.ProtectiveDevices || '***',
                    "sprinklered": value?.Sprinklered || '***',
                    "yearBuilt": value?.YearBuilt || '***',
                    "squareFootage": value?.SquareFootage || '***',
                    "estimatedReplacement": value?.EstimatedReplacement || '***',
                    "constructionType": value?.ConstructionType || '***',
                    "roofType": value?.RoofType || '***',
                    "coverages":
                        [
                            {
                                "coverageCd": "BLDG",
                                "desc": "Building Coverage",
                                "limits": [
                                    {
                                        "limitCd": "Limit1",
                                        "value": value?.BuildingLimit || '***'
                                    },
                                    {
                                        "limitCd": "Limit2",
                                        "value": ""
                                    }
                                ],
                                "deductibles": [
                                    {
                                        "deductibleCd": "Deductible1",
                                        "value": value?.BuildingDeductible || '***'
                                    }
                                ]
                            },
                            {
                                "coverageCd": "BPP",
                                "desc": "Business Personal Property Coverage",
                                "limits": [
                                    {
                                        "limitCd": "Limit1",
                                        "value": value?.BusinessPersonalPropertyLimit || '***'
                                    },
                                    {
                                        "limitCd": "Limit2",
                                        "value": ""
                                    }
                                ],
                                "deductibles": [
                                    {
                                        "deductibleCd": "Deductible1",
                                        "value": value?.BusinessPersonalPropertyDeductible || '***'
                                    }
                                ]
                            }
                        ]
                })
        })
        return {
            "expandedProperty": data?.ExpandedProperty || '***',
            "propertyDeductible": data?.PropertyDeductible || '***',
            "sewerDischarge": data?.SewerDischarge || '***',
            "windHailDeductible": data?.WindHailDeductible || '***',
            buildings,
        }
        }) 
    } else {
        addedBuildings = [{
            "expandedProperty": data?.ExpandedProperty || '***',
            "propertyDeductible": data?.PropertyDeductible || '***',
            "sewerDischarge": data?.SewerDischarge || '***',
            "windHailDeductible": data?.WindHailDeductible || '***',
        }]
    }

    const addedLocations = data?.AddedLocations && data?.AddedLocations.length > 0 ? data?.AddedLocations.map((val, ind) => {
        return {
            "locationNumber": ind + 1,
            "locationDesc" : val?.LocationDesc || '***',
            "addr": {
                "addrTypeCd": "LocationAddr",
                "addr1": val?.StreetName || '***',
                "city": val?.City || '***',
                "postalCode": val?.PostalCode || '***',
                "state": val?.State || '***',
                "country": val?.Country || '***',
            }
        }
    }) : []



    const obj = {
        "application": {
            "id":quoteId,
            "externalId": unique_id,
            "step": data.step,
            "policyInfo": {
                "policyType": "Commercial Property",
                "effectiveDt": data?.EffectiveDate || '***',
                "controllingState": data?.busState || '***'
            },
            "insuredInfo": {
                "commercialName": data?.InsuredName || '***',
                "insuredPhoneNo": data?.insuredPhone || '***',
                "insuredEmail": data?.insuredEmail || '***',
                "dba": data?.dba || '***',
                "addr": [
                    {
                        "addrTypeCd": "Mailing Address",
                        "addr1": data?.insuredAddress || '***',
                        "addr2": "",
                        "city": data?.busCity || '***',
                        "state": data?.busState || '***',
                        "postalCode": data?.busPostalCode || '***',
                        "country": data?.busCountry || '***'
                    },
                    {
                        "addrTypeCd": "Primary Business Address",
                        "addr1": data?.insuredAddress || '***',
                        "addr2": "",
                        "city": data?.primaryCity || '***',
                        "state": data?.primaryState || '***',
                        "postalCode": data?.primaryPostcode || '***',
                        "country": data?.primaryCountry || '***'
                    }
                ],
                "primaryBusinessInfo": {
                    "businessType": data?.PrimaryBusType || '***',
                    "businessSubType": data?.PrimaryBusSubType || '***',
                    "zipcode": data?.primaryZip || '***',
                    "businessYear": data?.busStartYear || '***',
                    "employees": data?.NoofEmployees || '***',
                    "sale": data?.TotalSales || '***'
                },
            },

            "locations": addedLocations || [],
            "insuranceLine": [
                {
                    "lineCd": "Commercial Property",
                    "risk": addedBuildings || []
                },
            ],

        }
    }
    return (obj)

};
