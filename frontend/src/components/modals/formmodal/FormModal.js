import React, { useState } from 'react'
import AddAutomaticDriverModalData from '../modaldata/AddAutomaticDriverModalData';
import CommercialAutoData from '../modaldata/CommercialAutoData';
import CommercialMonolineGLData from '../modaldata/CommercialMonolineGLData';
import ExcessData from '../modaldata/ExcessData';
import TPCIMonoLineProperty from '../modaldata/TPCIMonoLineProperty';
import TPCIPackage from '../modaldata/TPCIPackage';
import TPCIMonoLineLiability from '../modaldata/TPCIMonoLineLiability';
import ViewPolicyDetailsModal from '../modaldata/ViewPolicyDetailsModal';
import OtherData from '../modaldata/OtherData';
import RiskComplyMessage from '../modaldata/RiskComplyMessage';
import { useHistory } from 'react-router-dom';
import AddComPropCoverageModalData from '../modaldata/AddComPropCoverageModalData';
import AddDriverModalData from '../modaldata/AddDriverModalData';
import AddVehicleModalData from '../modaldata/AddVehicleModalData';
import AddLocationModalData from '../modaldata/AddLocationModalData';
import AddAutomaticVehicleModalData from '../modaldata/AddAutomaticVehicleModalData';
import AddAutomaticBuildingLocationModalData from '../modaldata/AddAutomaticBuildingLocationModalData';
import AddBuildingLocationModalData from '../modaldata/AddBuildingLocationModalData';
import AddAutomaticLocationModalData from '../modaldata/AddAutomaticLocationModalData';

const FormModal = ({ modalState, modalTitle, trigger, claimData, register, errors, control, getValues, setValue }) => {
    const [DisableBtn, setDisableBtn] = useState(false)
    var size = 'medium'
    modalTitle === "Commercial Auto" || modalTitle === "Add Vehicle" ||modalTitle === "Add New Vehicle" || modalTitle === "Add New Building" || modalTitle === "Add Building"? size = 'xxlarge' : size = 'medium'
    const history = useHistory();
    const handleNo = () => {
        alert('Thank you for your submission. Unfortunately, we are unable to offer an indication due to the risk being outside of our underwriting criteria.')
        history.push('/dashboard')
    }
    return (
        <div className="sds-modal sds-modal-center class_dtls" id="mainModal">
            <div className={"sds-modal-box " + size}>
                <div className="sds-modal-content">
                    <a href="" className="sds-modal-close sds-modal-exit" onClick={(e) => e.preventDefault()}><img alt="" src="" /></a>
                    <div className="popup_dtls">
                        <h4 className="md_title"><span>{modalTitle}</span></h4>
                        <>
                            {modalTitle === "Add Driver" ?
                                <AddAutomaticDriverModalData register={register} errors={errors} setValue={setValue} modalState={modalState} />
                                : null
                            }
                            {modalTitle === "Add New Driver" ?
                                <AddDriverModalData register={register} errors={errors} setValue={setValue} modalState={modalState}/>
                                : null
                            }
                            {modalTitle === "Add Vehicle" ?
                                <AddAutomaticVehicleModalData register={register} errors={errors} setValue={setValue} modalState={modalState} getValues={getValues} />
                                : null
                            }
                            {modalTitle === "Add New Vehicle" ?
                                <AddVehicleModalData register={register} errors={errors} setValue={setValue} getValues={getValues} modalState={modalState} />
                                : null
                            }
                            {modalTitle === "Add Building" ?
                                <AddAutomaticBuildingLocationModalData register={register} errors={errors} setValue={setValue} modalState={modalState} />
                                : null
                            }
                            {modalTitle === "Add New Building" ?
                                <AddBuildingLocationModalData register={register} errors={errors} setValue={setValue} modalState={modalState}/>
                                : null
                            }
                            {modalTitle === "Add Location" ?
                                <AddAutomaticLocationModalData register={register} errors={errors} setValue={setValue} modalState={modalState} />
                                : null
                            }
                            {modalTitle === "Add New Location" ?
                                <AddLocationModalData register={register} errors={errors} setValue={setValue} modalState={modalState}/>
                                : null
                            }
                            {modalTitle === "Add Coverage" ?
                                <AddComPropCoverageModalData register={register} errors={errors} />
                                : null
                            }
                            {modalTitle === "Commercial Monoline GL (or GL Portion of Commercial Package)" ?
                                <CommercialMonolineGLData modalState={modalState} claimData={claimData} register={register} errors={errors} control={control} getValues={getValues} setValue={setValue} trigger={trigger} />
                                : null
                            }
                            {modalTitle === "Other" ?
                                <OtherData modalState={modalState} claimData={claimData} register={register} errors={errors} control={control} getValues={getValues} setValue={setValue} trigger={trigger} />
                                : null
                            }
                            {modalTitle === "Commercial Auto" ?
                                <CommercialAutoData modalState={modalState} claimData={claimData} register={register} errors={errors} control={control} getValues={getValues} setValue={setValue} DisableBtn={DisableBtn} setDisableBtn={setDisableBtn} />
                                : null
                            }
                            {modalTitle === "Excess/Umbrella" ?
                                <ExcessData modalState={modalState} claimData={claimData} register={register} errors={errors} control={control} getValues={getValues} setValue={setValue} />
                                : null
                            }
                            {modalTitle === "Monoline Property" ?
                                <TPCIMonoLineProperty register={register} errors={errors} control={control} getValues={getValues} setValue={setValue} />
                                : null
                            }
                            {modalTitle === "Monoline Liability" ?
                                <TPCIMonoLineLiability register={register} errors={errors} control={control} getValues={getValues} setValue={setValue} />
                                : null
                            }
                            {modalTitle === "TPCI Package" ?
                                <TPCIPackage register={register} errors={errors} control={control} getValues={getValues} setValue={setValue} />
                                : null
                            }
                            {modalTitle === "Claim Details" ?
                                <ViewPolicyDetailsModal claimData={claimData} register={register} errors={errors} control={control} getValues={getValues} setValue={setValue} />
                                : null
                            }

                            {modalTitle === "Risk Comply Message" ?
                                <RiskComplyMessage />
                                : null
                            }
                        </>
                        {modalTitle === "Risk Comply Message" ?
                            <div className="form-element-btn">
                                <button type="submit" className="btn blue" id="">
                                    Yes
                                </button>
                                &nbsp; &nbsp;
                                <button type="submit" className="btn blue" id="" onClick={() => handleNo()}>
                                    No
                                </button>
                            </div>
                            :
                            <div className="form-element-btn">
                                <button type="submit" className="btn blue" id="">
                                    {(modalTitle === "Commercial Monoline GL (or GL Portion of Commercial Package)" || modalTitle === "Other"
                                        || modalTitle === "Commercial Auto" || modalTitle === "Excess/Umbrella" || modalTitle === "Commercial Property"
                                        || modalTitle === "Commercial Umbrella") && <>Save Change</>}
                                    {(modalTitle === "Add Driver") && <>Add Driver</>}
                                    {(modalTitle === "Add New Driver") && <>Add Driver</>}
                                    {(modalTitle === "Add Vehicle") && <>Add Vehicle</>}
                                    {(modalTitle === "Add New Vehicle") && <>Add Vehicle</>}
                                    {(modalTitle === "Add New Building") && <>Add Building</>}
                                    {(modalTitle === "Add Building") && <>Add Building</>}
                                    {(modalTitle === "Add Location") && <>Add Location</>}
                                    {(modalTitle === "Add New Location") && <>Add Location</>}
                                    {(modalTitle === "Add Coverage") && <>Add Coverage</>}
                                </button>
                            </div>
                        }
                    </div>
                </div>
            </div>
        </div>
    )
}

export default FormModal

