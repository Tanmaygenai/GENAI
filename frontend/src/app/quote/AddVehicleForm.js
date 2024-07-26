import React from 'react';
import VehiclesFormAutomatic from '../subforms/VehiclesFormAutomatic';

const AddVehicleForm = ({openModal,modalState,getValues,setValue,setModalState, state}) => {
  return (
    <VehiclesFormAutomatic  openModal={openModal} state={state} modalState={modalState} getValues={getValues} setValue={setValue} setModalState={setModalState} />
  );
}

export default AddVehicleForm;
