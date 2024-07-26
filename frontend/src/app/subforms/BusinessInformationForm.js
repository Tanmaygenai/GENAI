import React, { useEffect, useState } from 'react'
import DropDown from '../../components/dropdown/DropDown';
import { formFunctions } from '../../utility/jqueryFunctions';
import { drop_data } from '../../dummydata/data';
import FormControl from "../../components/formcontrol/FormControl"
import Papa from 'papaparse';
import dropdown_val from '../../dummydata/commercialAuto.csv';
const BusinessInformationForm = ({ state, register, errors, TabName, setValue, formData }) => {

    useEffect(() => {
        formFunctions()
    }, [])
    const [dropdownVal, setDropdownVal] = useState([]);
    const [savedData, setSavedData] = useState({})
    var commonConfig = { delimiter: "," };

    useEffect(() => {
        if (state.policyNumber!== null && state.policyNumber!== undefined) {
            setValue('PrimaryBusinessType', JSON.parse(state.indicationData).application.insuredInfo.primaryBusinessInfo.businessType)
            setValue('ProductType', JSON.parse(state.indicationData).application.insuredInfo.primaryBusinessInfo.productType)
            setValue('FleetLimit', JSON.parse(state.indicationData).application.insuredInfo.primaryBusinessInfo.fleetLimit)
            setValue('PrimaryBusinessSubType', JSON.parse(state.indicationData).application.insuredInfo.primaryBusinessInfo.businessSubType)
            setValue('BusinessYears', JSON.parse(state.indicationData).application.insuredInfo.primaryBusinessInfo.businessYears)
      
        }
      }, [dropdownVal, setValue])

      useEffect(() => {
        if(!!state.quoteData) {
          const data = JSON.parse(state.quoteData);
          setSavedData(data);
          const insuredInfo = data.application && data?.application['insuredInfo'] && data.application['insuredInfo'].primaryBusinessInfo
          if(insuredInfo) {
              setValue('PrimaryBusType', data.application['insuredInfo'].primaryBusinessInfo.businessType)
          }
          if(insuredInfo) {
              setValue('PrimaryBusSubType', data.application['insuredInfo'].primaryBusinessInfo.businessSubType)
          }
          if(insuredInfo) {
              setValue('primaryZip', data.application['insuredInfo'].primaryBusinessInfo.zipcode)
          }
          if(insuredInfo) {
              setValue('busStartYear', data.application['insuredInfo'].primaryBusinessInfo.businessYear)
          }
          if(insuredInfo) {
              setValue('NoofEmployees', data.application['insuredInfo'].primaryBusinessInfo.employees)
          }
          if(insuredInfo) {
              setValue('TotalSales', data.application['insuredInfo'].primaryBusinessInfo.sale)
          }
        }
      }, [state])

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
    return (

        <div>
            {
                TabName === 'Commercial Property' &&
                <div className='grid3'>
                    <div className="form-element form-select">
                        <DropDown fieldName='PrimaryBusType' label='Primary Business Type' defaultValue={savedData.application && savedData.application.insuredInfo.primaryBusinessInfo.businessType} register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                            {/* {drop_data.PrimaryBusinessType.map((value, ind) => (
                                <option value={value}>{value}</option>
                            ))} */}
                            {dropdownVal.map((value, ind) => {
                                return value.PrimaryBusinessType !== "" && value.PrimaryBusinessType !== null && value.PrimaryBusinessType !== undefined ? (
                                    <option selected={savedData?.application?.insuredInfo?.primaryBusinessInfo?.businessType == value.PrimaryBusinessType} value={value.PrimaryBusinessType === "-select-" ? "" : value.PrimaryBusinessType}>{value.PrimaryBusinessType}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                    </div>
                    <div className="form-element form-select">
                        <DropDown fieldName='PrimaryBusSubType' label='Primary Business Sub Type' defaultValue={savedData.application && savedData.application.insuredInfo.primaryBusinessInfo.businessSubType} register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                            {/* {drop_data.PrimaryBusinessSubType.map((value, ind) => (
                                <option value={value}>{value}</option>
                            ))} */}
                            {dropdownVal.map((value, ind) => {
                                return value.PrimaryBusinessSubType !== "" && value.PrimaryBusinessSubType !== null && value.PrimaryBusinessSubType !== undefined ? (
                                    <option selected={savedData?.application?.insuredInfo?.primaryBusinessInfo?.businessSubType == value.PrimaryBusinessSubType} value={value.PrimaryBusinessSubType === "-select-" ? "" : value.PrimaryBusinessSubType}>{value.PrimaryBusinessSubType}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                    </div>
                    <div class='form-element'>
                        <FormControl fieldName="primaryZip" label='Primary Business Zip' id='zip' defaultValue={savedData.application && savedData.application.insuredInfo.primaryBusinessInfo.zipcode} register={register} errors={errors} type="text" showLabel={true} className="form-field insuredStreetAddress" required={true} lableClass='form-label' activeBold={true} />
                    </div>
                    <div class='form-element'>
                        <FormControl fieldName="busStartYear" label='Business Start Year' id='year' defaultValue={savedData.application && savedData.application.insuredInfo.primaryBusinessInfo.businessYear} register={register} errors={errors} type="text" showLabel={true} className="form-field insuredStreetAddress" required={true} lableClass='form-label' activeBold={true} />
                    </div>
                    <div class='form-element'>
                        <FormControl fieldName="NoofEmployees" label='No of Employees' id='employee' defaultValue={savedData.application && savedData.application.insuredInfo.primaryBusinessInfo.employees} register={register} errors={errors} type="text" showLabel={true} className="form-field insuredStreetAddress" required={true} lableClass='form-label' activeBold={true} />
                    </div>
                    <div class='form-element'>
                        <FormControl fieldName="TotalSales" label='Total Sales' id='sales' defaultValue={savedData.application && savedData.application.insuredInfo.primaryBusinessInfo.sale} register={register} errors={errors} type="text" showLabel={true} className="form-field insuredStreetAddress" required={true} lableClass='form-label' activeBold={true} />
                    </div>
                </div>
            }
            {/* for com Auto */}
            {TabName === 'Commercial Auto' &&
                <div className='grid4'>
                    <div className="form-element form-select">
              {dropdownVal.length > 0 ? (
                  <DropDown
                      defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.locations[0].addressType : formData?.PrimaryBusinessType}
                      fieldName="PrimaryBusinessType"
                      label="Primary Business Type"
                      register={register}
                      setValue={setValue}
                      errors={errors}
                      lableClass="form-label"
                      activeBold={false}
                      showLabel={true}
                  >
                      {dropdownVal.map((value, ind) => {
                          return value.PrimaryBusinessType !== "" && value.PrimaryBusinessType !== null && value.PrimaryBusinessType !== undefined ? (
                              <option value={value.PrimaryBusinessType === "-select-" ? "" : value.PrimaryBusinessType}>
                                  {value.PrimaryBusinessType}
                              </option>
                          ):
                          <></>
                          
                      })}
                  </DropDown>
              ) : null}
              {errors.PrimaryBusinessType && (
                <div className="invalid-feedback">
                  {errors.PrimaryBusinessType.message}
                </div>
              )}
            </div>
                <div className='form-element form-select'>
                {dropdownVal.length > 0 ? (
                    <DropDown fieldName='ProductType' label='Product Type' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuredInfo.primaryBusinessInfo.productType : formData?.ProductType} register={register} errors={errors} lableClass='form-label' activeBold={true}>
                        {dropdownVal.map((value, ind) => {
                            return value.ProductType !== "" && value.ProductType !== null && value.ProductType !== undefined ? (
                                <option value={value.ProductType === "-select-" ? "" : value.ProductType}>{value.ProductType}</option>
                            ) :
                                <></>
                        }
                        )}
                    </DropDown>
                    ) : null}
                    {errors.ProductType && (
                      <div className="invalid-feedback">
                        {errors.ProductType.message}
                      </div>
                    )}
                </div>
                    <div className='form-element form-select'>
                        {dropdownVal.length > 0 ? (
                        <DropDown fieldName='FleetLimit' label='Fleet Limit' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuredInfo.primaryBusinessInfo.fleetType : formData?.FleetLimit} register={register} errors={errors} lableClass='form-label' activeBold={true}>
                            {dropdownVal.map((value, ind) => {
                                return value.FleetLimit !== "" && value.FleetLimit !== null && value.FleetLimit !== undefined ? (
                                    <option value={value.FleetLimit === "-select-" ? "" : value.FleetLimit}>{value.FleetLimit}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                        ) : null}
                        {errors.FleetLimit && (
                          <div className="invalid-feedback">
                            {errors.FleetLimit.message}
                          </div>
                        )}
                    </div>
                    <div className='form-element form-select'>
                        {dropdownVal.length > 0 ? (
                        <DropDown fieldName='PrimaryBusinessSubType' label='Primary Business Sub Type' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuredInfo.primaryBusinessInfo.businessSubType : formData?.PrimaryBusinessSubType} register={register} errors={errors} lableClass='form-label' activeBold={true} setValue={setValue}>
                            {/* {drop_data.PrimaryBusinessSubType.map((value, ind) => (
                                <option value={value.replace(/[ (/,.?'")$&-]/gi, "")}>{value}</option>                            
                                ))} */}
                            {dropdownVal.map((value, ind) => {
                                return value.PrimaryBusinessSubType !== "" && value.PrimaryBusinessSubType !== null && value.PrimaryBusinessSubType !== undefined ? (
                                    <option value={value.PrimaryBusinessSubType === "-select-" ? "" : value.PrimaryBusinessSubType}>{value.PrimaryBusinessSubType}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                        ) : null}
                        {errors.PrimaryBusinessType && (
                          <div className="invalid-feedback">
                            {errors.PrimaryBusinessType.message}
                          </div>
                        )}
                    </div>
                    <div className='form-element form-select'>
                        {dropdownVal.length > 0 ? (
                        <DropDown 
                        fieldName='BusinessYears' 
                        label='How long you have been in buisiness?' 
                        defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuredInfo.primaryBusinessInfo.businessYears : formData?.BusinessYears} 
                        register={register} 
                        errors={errors} 
                        lableClass='form-label' 
                        activeBold={true} 
                        setValue={setValue}>
                            {/* {drop_data.BusinessYears.map((value, ind) => (
                                <option value={value.replace(/[ (/,.?'")$&-]/gi, "")}>{value}</option>                            
                                ))} */}
                            {dropdownVal.map((value, ind) => {
                                return value.BusinessYears !== "" && value.BusinessYears !== null && value.BusinessYears !== undefined ? (
                                    <option value={value.BusinessYears === "-select-" ? "" : value.BusinessYears}>{value.BusinessYears}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                        ) : null}
                        {errors.BusinessYears && (
                          <div className="invalid-feedback">
                            {errors.BusinessYears.message}
                          </div>
                        )}
                    </div>
                    <div className="form-element">
                        <FormControl fieldName="buisness_primary_zip" label='Buisness Primary Zip' buisness_primary_zip id='buisness_primary_zip' defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.insuredInfo.primaryBusinessInfo.primaryOperationsZip : formData?.buisness_primary_zip} register={register} errors={errors} type="number" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} maxLength={5} minLength={5} min={0} />
                    </div>
                </div>
            }
            {/* end for com auto */}

        </div>
    )
}
export default BusinessInformationForm
