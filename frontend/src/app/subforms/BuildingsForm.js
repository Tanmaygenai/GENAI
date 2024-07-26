import React from "react";
import BuildingLocationTable from "../../components/tables/BuildingLocationTable";

const BuildingsForm = ({modalState, openModal, setModalState}) => {
  
    return (
        <div className='grid1'>
            <div className="form-element-btn-border">
                <a href="# " onClick={() => { openModal('mainModal', "Add New Building") }} className="btn outline">Add Building</a>
            </div>
            <BuildingLocationTable buildingLoc={modalState.BuildingLocData} modalState={modalState} setModalState={setModalState} openModal={openModal} />
        </div>
    )
}
export default BuildingsForm;
