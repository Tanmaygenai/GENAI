import { v4 as uuid } from 'uuid';
export const ExcessLiablityResopnseJson = (data, state,quoteId) => {
    if (typeof quoteId === 'undefined'){quoteId=0}
    const unique_id = uuid();
    var underlyingPolicies= data.AddedPolicies.map((val,ind)=>{
        if (val.AddUnderlyingPolicies==="Commercial Auto"){
            return{
                "autoUnderLyingPolicy":{
                    "insuranceType": "Commercial Auto",
                    "combinedSingleLimit": val.CombinedSingleLimit ? val.CombinedSingleLimit : "",
                    "underlyingPremium": val.underlying_premium ? val.underlying_premium : "",
                    "vehicles":{
                        "lightVehicles": val.LightVehicle ? val.LightVehicle : "",
                        "mediumVehicles": val.MediumVehicle ? val.MediumVehicle : "",
                        "heavyVehicles": val.HeavyVehicle ? val.HeavyVehicle : "",
                        "extraHeavyVehicles": val.ExtraHeavyVehicle ? val.ExtraHeavyVehicle : ""
                    }
                }
            }
        }
        else if(val.AddUnderlyingPolicies==="Commercial Monoline GL (or GL Portion of Commercial Package)"){
            return{
                "monolineUnderLyingInsurance": {
                    "insuranceType": "Commercial Monoline",
                    "classCd": val.class_code_or_Description ? val.class_code_or_Description : "",
                    "underlyingPremium": val.underlying_premium ? val.underlying_premium : "",
                    "limits": {
                      "eachOccLmt": val.EachOccurrenceLimit ? val.EachOccurrenceLimit :"",
                      "prodAndComp": val.ProductsandCompletedOpsAggregateLimit ? val.ProductsandCompletedOpsAggregateLimit : "",
                      "generalAggr": val.GeneralAggregateLimit ? val.GeneralAggregateLimit : "",
                      "personalAndAdv": val.PersonalAdvertisingLimit ? val.PersonalAdvertisingLimit : "",
                    }
                  }
            }
        }
        else if(val.AddUnderlyingPolicies==="Excess/Umbrella"){
            return{
                "excessUnderLyingInsurance": {
                    "insuranceType": "Commercial Excess",
                    "underlyingPremium": val.underlying_premium ? val.underlying_premium : "",
                    "liabilityLimit": val.LimitofLiability ? val.LimitofLiability : "",
                  },
            }
        }
        else if(val.AddUnderlyingPolicies==="Other" ){
            let limits={}
            if(val.PolicyType==="Farm"){
                limits= {
                    "eachOccLmt": val.EachOccurrenceLimit ? val.EachOccurrenceLimit :"",
                    "generalAggr": val.GeneralAggregateLimit ? val.GeneralAggregateLimit : ""
                }
            }
            else if(val.PolicyType==="Auto Liability Limit"){
                limits= {
                    "eachOccLmt": val.EachOccurrenceLimit ? val.EachOccurrenceLimit :"",
                    "generalAggr": val.GeneralAggregateLimit ? val.GeneralAggregateLimit : "",
                    "autoLimit": val.AutoLimit ? val.AutoLimit : ""
                }
            }else{
                limits= {
                    "employeeBenefitLiabLimit": val.OccurrenceLimitEachAccident ? val.OccurrenceLimitEachAccident : "",
                }
            }
            return{
                "otherUnderLyingInsurance": {
                    "insuranceType": val.PolicyType ? val.PolicyType : "",
                    "classCd": val.class_code_or_Description ? val.class_code_or_Description : "",
                    "underlyingPremium": val.underlying_premium ? val.underlying_premium : "",
                    "limits": limits
                }
            }
        }
        
    })
    var arrA=[]
    underlyingPolicies.filter((up) => (up.autoUnderLyingPolicy ? arrA.push(up.autoUnderLyingPolicy) : ""))

    var arrM=[]
    underlyingPolicies.filter((up) => (up.monolineUnderLyingInsurance ? arrM.push(up.monolineUnderLyingInsurance) : ""))

    var arrE=[]
    underlyingPolicies.filter((up) => (up.excessUnderLyingInsurance ? arrE.push(up.excessUnderLyingInsurance) : ""))
    
    var arrO=[]
    underlyingPolicies.filter((up) => (up.otherUnderLyingInsurance ? arrO.push(up.otherUnderLyingInsurance) : ""))
    var underlyingPoliciesArr=[]
    const underlyingPoliciesObj = {
        autoUnderLyingPolicy: arrA,
        monolineUnderLyingInsurance: arrM,
        excessUnderLyingInsurance: arrE,
        otherUnderLyingInsurance: arrO,
    };
    underlyingPoliciesArr.push(underlyingPoliciesObj)
    const obj = {
        "application": {
            "id":quoteId,
            "externalId": unique_id,
            "policyPremiumInfo": {
                "liabilityLimit":data.liabilityLimit,
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
                "policyType": "Commercial Excess Liability",
                "productRef": "",
                "effectiveDt": data.EffectiveDate,
                "expirationDt": "",
                "controllingState": data.busState,
                "extendedAttributes": [
                    {
                        "name": "attribute1",
                        "value": ""
                    },
                    {
                        "name": "attribute2",
                        "value": ""
                    }
                ]
            },
            "insuredInfo": {
                "entityType": "",
                "commercialName":data.InsuredName,
                "dba": "",
                "givenName": "",
                "surName": "",
                "addr": [
                    {
                        "addrTypeCd": "",
                        "addr1": data.insuredAddress,
                        "addr2": "",
                        "city": data.busCity,
                        "state": data.State,
                        "postalCode": data.busPostalCode,
                        "country": data.busCountry,
                    }
                ],
                "primaryPhone": "",
                "secondaryPhone": "",
                "emailAddress": ""
            },
            "uwQuestions": [
                {
                    "name": "QUES10",
                    "value": ""
                },
                {
                    "name": "QUES11",
                    "value": ""
                }
            ],
            "locations": [
                {
                    "locationNumber": null,
                    "addr": {
                        "addrTypeCd": "",
                        "addr1": "",
                        "addr2": "",
                        "city": "",
                        "state": "",
                        "postalCode": ""
                    }
                }
            ],
            "insuranceLine": [
                {
                    "lineCd": "Commercial Auto",
                    "coverages": [
                        {
                            "coverageCd": "COV1",
                            "limits": [
                                {
                                    "limitCd": "Limit1",
                                    "value": ""
                                },
                                {
                                    "limitCd": "Limit2",
                                    "value": ""
                                }
                            ],
                            "deductibles": [
                                {
                                    "deductibleCd": "Deductible1",
                                    "value": ""
                                }
                            ]
                        },
                        {
                            "coverageCd": "COV2",
                            "limits": [
                                {
                                    "limitCd": "Limit1",
                                    "value": ""
                                },
                                {
                                    "limitCd": "Limit2",
                                    "value": ""
                                }
                            ],
                            "deductibles": [
                                {
                                    "deductibleCd": "Deductible1",
                                    "value": ""
                                }
                            ]
                        }
                    ],
                    "risks": [
                        {
                            "riskTypeCd": "General",
                            "locationRef": null,
                            "coverages": [
                                {
                                    "coverageCd": "COV1",
                                    "limits": [
                                        {
                                            "limitCd": "Limit1",
                                            "value": ""
                                        },
                                        {
                                            "limitCd": "Limit2",
                                            "value": ""
                                        }
                                    ],
                                    "deductibles": [
                                        {
                                            "deductibleCd": "Deductible1",
                                            "value": ""
                                        }
                                    ]
                                },
                                {
                                    "coverageCd": "COV2",
                                    "limits": [
                                        {
                                            "limitCd": "Limit1",
                                            "value": ""
                                        },
                                        {
                                            "limitCd": "Limit2",
                                            "value": ""
                                        }
                                    ],
                                    "deductibles": [
                                        {
                                            "deductibleCd": "Deductible1",
                                            "value": ""
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            "riskTypeCd": "CommercialVehicle",
                            "locationRef": 1,
                            "vehicleInfo": {
                                "vehicleNumber": 1,
                                "manufacturer": "",
                                "model": "",
                                "modelYear": "",
                                "vin": "",
                                "grossVehicleWeight": "",
                                "gvwGroup": "",
                                "useCd": "",
                                "drivingRadius": "",
                                "vehicleSymbol": "",
                                "unitType": "",
                                "costNew": "",
                                "addr": [
                                    {
                                        "addrTypeCd": "",
                                        "addr1": "",
                                        "addr2": "",
                                        "city": "",
                                        "state": "",
                                        "postalCode": ""
                                    }
                                ]
                            },
                            "coverages": [
                                {
                                    "coverageCd": "COV1",
                                    "limits": [
                                        {
                                            "limitCd": "Limit1",
                                            "value": ""
                                        },
                                        {
                                            "limitCd": "Limit2",
                                            "value": ""
                                        }
                                    ],
                                    "deductibles": [
                                        {
                                            "deductibleCd": "Deductible1",
                                            "value": ""
                                        }
                                    ]
                                },
                                {
                                    "coverageCd": "COV2",
                                    "limits": [
                                        {
                                            "limitCd": "Limit1",
                                            "value": ""
                                        },
                                        {
                                            "limitCd": "Limit2",
                                            "value": ""
                                        }
                                    ],
                                    "deductibles": [
                                        {
                                            "deductibleCd": "Deductible1",
                                            "value": ""
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ],
            "drivers": [
                {
                    "driverNumber": null,
                    "givenName": "",
                    "surname": "",
                    "birthDt": "",
                    "age": null,
                    "licenseState": "",
                    "licenseType": "",
                    "yearsOfUSExperience": null,
                    "excludedFlag": false
                }
            ],
            "additionalInterests": [
                {
                    "interestType": "",
                    "interestName": "",
                    "addr": {
                        "addrTypeCd": "AdditonalInterestAddr",
                        "addr1": "",
                        "addr2": "",
                        "city": "",
                        "state": "",
                        "postalCode": ""
                    },
                    "interestDetails": [
                        {
                            "name": "Designation Of Premises",
                            "value": ""
                        }
                    ]
                }
            ],
            "commercialPolicyInfo": {
                "descOfOperations": "",
                "businessEstDt": "",
                "yearsExperience": null,
                "fullTimeEmployees": null,
                "primaryGLClassCd": data.class_code_or_Description,
                "primaryOccupation": "",
                "secondaryOccupation": ""
            },
            "underlyingPolicies": underlyingPoliciesArr
        }
    }
    return (obj)
};
