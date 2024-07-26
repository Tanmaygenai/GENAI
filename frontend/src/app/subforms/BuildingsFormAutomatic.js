import React from "react";
import BuildingLocationTableAutomatic from "../../components/tables/BuildingLocationTableAutomatic";

const BuildingsFormAutomatic = ({modalState, openModal, setModalState}) => {
    
    return (
        <div className='grid1'>
            <div className="form-element-btn-border">
                <a href="# " onClick={() => { openModal('mainModal', "Add Building") }} className="btn outline">Add Building</a>
            </div>
            <BuildingLocationTableAutomatic buildingLoc={modalState.BuildingLocData} modalState={modalState} setModalState={setModalState} openModal={openModal} />
        </div>
    )
}
export default BuildingsFormAutomatic;
