import React, { useEffect, useState } from "react";
import LocationsTableAutomatic from "../../components/tables/LocationsTableAutomatic";

const LocationsFormAutomatic = ({ modalState, openModal, setModalState, state }) => {
       
    return (
        <div className='grid1'>
            <div className="form-element-btn-border">
                <a href="# " onClick={() => { openModal('mainModal', "Add Location") }} className="btn outline">Add Location</a>
            </div>
            <LocationsTableAutomatic locations={modalState.LocationData} modalState={modalState} setModalState={setModalState} openModal={openModal} state={state} />
        </div>
    )
}
export default LocationsFormAutomatic;
