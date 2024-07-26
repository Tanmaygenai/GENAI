import React from "react";
import BuildingsFormAutomatic from "../subforms/BuildingsFormAutomatic";

const AddBuildingForm = ({openModal, modalState, setModalState,roof,construction,sqFoot,yearBuilt,buildingLimit,deductible,description }) => {
    // let state = {roof,construction,sqFoot,yearBuilt,buildingLimit,deductible,description}
    // state = Object.fromEntries(
    //   Object.entries(state).map(([key, value]) => [key.trim(), value.trim().charAt(0).toUpperCase() + value.trim().slice(1).toLowerCase()])
    // );
  return (
      <BuildingsFormAutomatic openModal={openModal} modalState={modalState} setModalState={setModalState} 
      // state={state}
      />
    )
  }
  
  export default AddBuildingForm