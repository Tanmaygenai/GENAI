import React from 'react'
import LocationsFormAutomatic from '../subforms/LocationsFormAutomatic';

const AddLocationForm = ({openModal, modalState, setModalState ,street,locationState,city,zip,description}) => {
 const state={street,locationState,city,zip,description}
  return (
    <LocationsFormAutomatic openModal={openModal} modalState={modalState} setModalState={setModalState} state={state}/>
  )
}

export default AddLocationForm