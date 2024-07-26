import React from "react";
import VehiclesTableAutomatic from "../../components/tables/VehiclesTableAutomatic";

const VehiclesFormAutomatic = ({modalState, openModal,setValue, getValues,setModalState}) => {
    return (
        <div className='grid1'>
            <div className="form-element-btn-border">
                <a href="# " onClick={() => { openModal('mainModal', "Add Vehicle") }} className="btn outline">Add Vehicle</a>
            </div>
            <VehiclesTableAutomatic vehicles={modalState.VehicleData} modalState={modalState} setModalState={setModalState} openModal={openModal}/>
        </div>
    )
}
export default VehiclesFormAutomatic;
