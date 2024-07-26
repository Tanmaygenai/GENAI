import React from 'react';
import DriversFormAutomatic from '../subforms/DriversFormAutomatic';

const AddDriverForm = ({openModal,modalState,getValues,setValue,setModalState, state}) => {
  return (
    <DriversFormAutomatic state={state} openModal={openModal} modalState={modalState} getValues={getValues} setValue={setValue} setModalState={setModalState} />
  );
}

export default AddDriverForm;
