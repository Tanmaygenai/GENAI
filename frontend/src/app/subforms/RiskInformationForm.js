import React, { useEffect, useState } from 'react';
import DropDown from '../../components/dropdown/DropDown';
import Check from '../../components/check/Check';
import BuildingLocationTable from '../../components/tables/BuildingLocationTable';
import FormControl from '../../components/formcontrol/FormControl';
import TypeAHeadForm from '../../components/typeahead/TypeAHeadForm'
import { CountryName } from '../../dummydata/CountryData'
import { drop_data } from '../../dummydata/data';
import AddPolicyTable from '../../components/tables/AddPolicyTable';
import { classCodes } from '../../dummydata/classcodes';
import $ from 'jquery';
import Papa from 'papaparse';
import dropdown_auto_val from '../../dummydata/commercialAuto.csv';
import dropdown_prop_val from '../../dummydata/commercialProperty.csv';
import dropdown_excess_val from '../../dummydata/excessLiability.csv';

const RiskInformationForm = ({ state, modalState, setModalState, openModal, register = {}, errors = {}, TabName = '', control, getValues, formData, setValue }) => {
    const [comAutoRiskAccepted, setComAutoRiskAccepted] = useState(true);
    const [savedData, setSavedData] = useState({})
    const [excessFlag, setExcessFlag] = useState(false);
    var flag = false;
    const [autoDropdownVal, setAutoDropdownVal] = useState([]);
    const [propertyDropdownVal, setPropertyDropdownVal] = useState([]);
    const [excessLDropdownVal, setExcessLDropdownVal] = useState([]);
    var commonConfig = { delimiter: "," };

    useEffect(() => {
      if(!!state.quoteData) {
        const data = JSON.parse(state.quoteData);
        setSavedData(data);
        const insuredInfo = data.application && data?.application['insuranceLine'] && data.application['insuranceLine'][0] && data.application['insuranceLine'][0]['risk'][0] && data.application['insuranceLine'][0]['risk'][0];
        if(insuredInfo.propertyDeductible) {
            setValue('PropertyDeductible', insuredInfo.propertyDeductible)
        }
        if(insuredInfo.expandedProperty) {
            setValue('ExpandedProperty', insuredInfo.expandedProperty)
        }
      }
    }, [state])
    

    useEffect(() => {
        Papa.parse(
            dropdown_auto_val,
            {
                ...commonConfig,
                header: true,
                download: true,
                complete: (result) => {
                    setAutoDropdownVal(result.data)
                }
            }
        );
    }, [])
    useEffect(() => {
        Papa.parse(
            dropdown_prop_val,
            {
                ...commonConfig,
                header: true,
                download: true,
                complete: (result) => {
                    setPropertyDropdownVal(result.data)
                }
            }
        );
    }, [])
    useEffect(() => {
        Papa.parse(
            dropdown_excess_val,
            {
                ...commonConfig,
                header: true,
                download: true,
                complete: (result) => {
                    setExcessLDropdownVal(result.data)
                }
            }
        );
    }, [])
    useEffect(() => {
        if (TabName === 'Commercial Excess Liability') {
            if (state.application) {
                if (state.application.underlyingPolicies.length > 0) {
                    setExcessFlag(true);
                } else {
                    setExcessFlag(false);
                }
            }
        }
    }, [])

    useEffect(() => {
        if (TabName === 'Commercial Excess Liability') {
            if (modalState.AddPolicyData) {
                if (modalState.AddPolicyData.length > 0) {
                    setExcessFlag(true);
                } else {
                    setExcessFlag(false);
                }
            }
        }
    })

    const classCodeData = []
    var product;
    if (TabName === 'Commercial Excess Liability') {
        product = "Exc";
    }
    classCodes ?
        classCodes.map((val, ind) => {
            if (val["Class Group"] === formData.class_code_or_Description_group && (val["Class Code Level"][product] === "Yes")) {
                classCodeData.push(val["Class Code"] + " " + val["Class Name"])
            }
        })
        :
        classCodes.map((val, ind) => {
            classCodeData.push(val["Class Code"] + " " + val["Class Name"])
        })
    const CountryData = CountryName.map(val => val.name)
    var classCodeValues = {
        selectedVal: getValues("class_code_or_Description"),
        allVal: classCodeData
    }
    function verifyCheckBox(e) {
        if (e.target.checked) {
            setComAutoRiskAccepted(true);
        }
        else {
            setComAutoRiskAccepted(false);
        }
    }

    return (
        <>
            {/* Risk Infromation for Excess Liability Form */}
            {TabName === 'Commercial Excess Liability' &&
                <>
                    <div className="grid2">
                        <div className='form-element form-select'>
                            <DropDown fieldName='liabilityLimit' id='liabilityLimit' label='Limit of Liability' register={register} errors={errors} lableClass='form-label' activeBold={true} defaultValue={state.application && state.application.policyPremiumInfo.liabilityLimit && state.application.policyPremiumInfo.liabilityLimit}>
                                {/* {drop_data.ExcessLiabilityLimits.map((value, ind) => (
                                    <option value={value}>{value}</option>
                                ))} */}
                                {excessLDropdownVal.map((value, ind) => {
                                    return value.ExcessLiabilityLimits !== "" && value.ExcessLiabilityLimits !== null && value.ExcessLiabilityLimits !== undefined ? (
                                        <option value={value.ExcessLiabilityLimits === "-select-" ? "" : value.ExcessLiabilityLimits}>{value.ExcessLiabilityLimits}</option>
                                    ) :
                                        <></>
                                }
                                )}
                            </DropDown>
                        </div>
                        {/* <div> Add Underlying Policies </div> */}
                        <div className='excessRiskRadioBtn'>
                            <Check register={register} fieldName="Add Underlying Policies" value="Commercial Auto" type="radio" checked={excessFlag} showLabel={false} onClick={() => openModal('mainModal', "Commercial Auto", classCodeValues)} />
                            <Check register={register} fieldName="Add Underlying Policies" value="Commercial Monoline GL (or GL Portion of Commercial Package)" type="radio" checked={excessFlag} showLabel={false} onClick={() => openModal('mainModal', "Commercial Monoline GL (or GL Portion of Commercial Package)", classCodeValues)} />
                            <Check register={register} fieldName="Add Underlying Policies" value="Excess/Umbrella" type="radio" checked={excessFlag} showLabel={false} onClick={() => openModal('mainModal', "Excess/Umbrella", classCodeValues)} />
                            <br />
                            <Check register={register} errors={errors} fieldName="Add Underlying Policies" value="Other" type="radio" checked={excessFlag} showLabel={false} onClick={() => openModal('mainModal', "Other", classCodeValues)} />
                        </div>
                    </div>
                    <AddPolicyTable policies={modalState.AddPolicyData} modalState={modalState} setModalState={setModalState} openModal={openModal} />
                </>
            }

            {/* Risk Informmation for commercial Auto Form */}
            {TabName === 'Commercial Auto' &&
                <>
                    <p><b>For indication, please read the following:</b></p>
                    <ul>
                        <li>• Indication is for an annual policy term</li>
                        <li>• Indication does not include TRIA charge</li>
                        <li>• Underlying policies are concurrent, occurrence (not claims made) and defense is outside</li>
                        <li>• Underlying company carries an AM Best rating of B++ VI or better</li>
                        <li>• No single loss in excess of $50,000 incurred in the past 5 years</li>
                        <li>• For Auto: no livery, residential delivery, school buses, towing or sand/gravel haulers</li>
                        <li>• For General Liability: no multi-unit residential development or cannabis industry exposures</li>
                        <li>• There may be other risk attributes that make this risk ineligible</li>
                        <li>• Indication is not binding</li>
                    </ul>
                    <br />
                    <p><b>Does this risk comply with our underwriting guidelines ?</b></p>

                    {/* <div className="form-element form-inline-select">
                        <DropDown fieldName='Risk Comply' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={false} >
                            <option value="Yes">Yes</option>
                            <option value="No">No</option>
                        </DropDown>
                    </div> */}
                    <Check id="comAutoRiskCheckBox" register={register} fieldName="Risk Comply" value="Yes" showLabel={false} type="checkbox" onClick={(e) => verifyCheckBox(e)} checked={comAutoRiskAccepted} />
                    <br />
                    {!comAutoRiskAccepted && <b style={{ color: 'red' }}>Please Accept</b>}
                </>
            }
            {/* {/ Risk Informmation for TPCI Form /} */}
            {TabName === 'TPCI' &&
                <>
                    {/* <!--  *(If Monoline Property) --> */}
                    {formData.ProductSelection === 'Monoline Property' &&
                        <>
                            <div className='grid1'>
                                <div className="form-element-btn-border">
                                    <a href="# " onClick={() => { openModal('mainModal', "Monoline Property") }} className="btn outline">Add Building/Location</a>
                                </div>
                                <BuildingLocationTable buildingLoc={modalState.BuildingLocData} />
                            </div>
                        </>
                    }
                    {/* <!--  *(If Monoline GL) --> */}
                    {formData.ProductSelection === 'Monoline GL' &&
                        <>
                            <div className='grid1'>
                                <div className="form-element-btn-border">
                                    <a href="# " onClick={() => { openModal('mainModal', "Monoline Liability") }} className="btn outline">Add Building/Location</a>
                                </div>
                                <BuildingLocationTable buildingLoc={modalState.BuildingLocData} />
                            </div>
                        </>}

                    {/* <!--  *(If TPCI Package) --> */}
                    {formData.ProductSelection === 'TPCI Package' &&
                        <>
                            <div className='grid1'>
                                <div className="form-element-btn-border">
                                    <a href="# " onClick={() => { openModal('mainModal', "TPCI Package") }} className="btn outline">Add Building/Location</a>
                                </div>
                                <BuildingLocationTable buildingLoc={modalState.BuildingLocData} />
                            </div>
                            <div class='grid4'>
                                <RIMonolinePropAndTPCI openModal={openModal} modalState={modalState} register={register} errors={errors} />
                                <TypeAHeadForm register={register} errors={errors} control={control} fieldName="class_code" Data={CountryData} minLength={3} getValues={getValues} className="form-field" lableClass='form-label' label='Class Code' activeBold={true} />
                                <RIMonolineGLAndTPCI register={register} errors={errors} />
                            </div>
                        </>
                    }
                </>
            }
            {TabName === 'Commercial Property' &&
                <div className="grid2">
                    <div className='form-element form-select'>
                        <DropDown fieldName='PropertyDeductible' defaultValue={savedData.application && savedData.application.insuranceLine[0].risk[0].propertyDeductible} label='Property Deductible (for all buildings)' register={register} errors={errors} lableClass='form-label' activeBold={true}>
                            {/* {drop_data.PropertyDeductible.map((value, ind) => (
                                <option value={value}>{value}</option>
                            ))} */}
                            {propertyDropdownVal.map((value, ind) => {
                                return value.PropertyDeductible !== "" && value.PropertyDeductible !== null && value.PropertyDeductible !== undefined ? (
                                    <option selected={savedData?.application && savedData?.application?.insuranceLine && savedData?.application?.insuranceLine[0]?.risk[0]?.propertyDeductible == value.PropertyDeductible} value={value.PropertyDeductible === "-select-" ? "" : value.PropertyDeductible}>{value.PropertyDeductible}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                    </div>
                    <div className='form-element form-select'>
                        <DropDown fieldName='ExpandedProperty' label='Expanded Property' defaultValue={savedData.application && savedData.application.insuranceLine[0].risk[0].expandedProperty} register={register} errors={errors} lableClass='form-label' activeBold={true}>
                            {/* {drop_data.ExpandedProperty.map((value, ind) => (
                                <option value={value}>{value}</option>
                            ))} */}
                            {propertyDropdownVal.map((value, ind) => {
                                return value.ExpandedProperty !== "" && value.ExpandedProperty !== null && value.ExpandedProperty !== undefined ? (
                                    <option selected={savedData?.application && savedData?.application?.insuranceLine && savedData.application.insuranceLine[0].risk[0].expandedProperty == value.ExpandedProperty} value={value.ExpandedProperty === "-select-" ? "" : value.ExpandedProperty}>{value.ExpandedProperty}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                    </div>
                </div>
            }
        </>
    )
}

export default RiskInformationForm


const RIMonolinePropAndTPCI = ({ modalState, openModal, register = {}, errors = {} }) => {
    return <>

        <div class="fullhead">Building Details</div>
        <div class="form-element">
            <FormControl fieldName="distance_to_brush" label='Distance To Brush' id='distance_to_brush' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
        </div>
        <div class="form-element">
            <FormControl fieldName="distance_to_water" label='Distance To Water' id='distance_to_water' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
        </div>
        <div class="form-element form-select">
            <DropDown fieldName='Protection Class' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                <option value=""></option>
                <option value="More Options">More Options</option>
            </DropDown>
        </div>
        <div class="form-element">
            <FormControl fieldName="of_stories" label='# Of Stories' id='of_stories' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
        </div>
        <div class="fullhead">Year Build Latest Improvement/Update Year</div>
        <div class="form-element">
            <FormControl fieldName="wiring" label='Wiring' id='wiring' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
        </div>
        <div class="form-element">
            <FormControl fieldName="roofing" label='Roofing' id='roofing' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
        </div>
        <div class="form-element">
            <FormControl fieldName="plumbing" label='Plumbing' id='plumbing' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
        </div>
        <div class="form-element">
            <FormControl fieldName="heating" label='Heating' id='heating' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
        </div>
        <div class="form-element form-select">
            <DropDown fieldName='Construction Type' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                <option value=""></option>
                <option value="More Options">More Options</option>
            </DropDown>
        </div>
        <div class="form-element">
            <FormControl fieldName="total_area" label='Total Area' id='total_area' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
        </div>
        <div class="form-element form-select">
            <DropDown fieldName='Sprinklered' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                <option value=""></option>
                <option value="More Options">More Options</option>
            </DropDown>
        </div>
        <div class="form-element form-select">
            <DropDown fieldName='Roof Type' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                <option value=""></option>
                <option value="More Options">More Options</option>
            </DropDown>
        </div>
        <div class="form-element form-select">
            <DropDown fieldName='Alarm Description' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                <option value=""></option>
                <option value="More Options">More Options</option>
            </DropDown>
        </div>
    </>;
};


const RIMonolineGLAndTPCI = ({ register = {}, errors = {} }) => {
    return <>
        <div class="form-element">
            <FormControl fieldName="total_payroll_for_operations" label='Total Payroll for operations' id='total_payroll_for_operations' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
        </div>
        <div class="form-element">
            <FormControl fieldName="total_subcontractor_cost_for_operations" label='Total Subcontractor Cost For Operations' id='total_subcontractor_cost_for_operations' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
        </div>
        <div class="form-element">
            <FormControl fieldName="annual_sales" label='Annual Sales/receipts for operations' id='annual_sales' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
        </div>
    </>
};
