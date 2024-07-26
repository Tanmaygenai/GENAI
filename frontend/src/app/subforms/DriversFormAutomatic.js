import React from 'react'
import DriversTableAutomatic from '../../components/tables/DriversTableAutomatic';

const DriversFormAutomatic = ({ modalState, openModal,setValue, getValues,setModalState}) => {

    return (
        <>
            <div className='grid2'>
                <div className="form-element-btn-border">
                    <a href="# " onClick={() => { openModal('mainModal', "Add Driver") }} className="btn outline">Add Driver</a>
                </div>
                <DriversTableAutomatic drivers={modalState.DriverData} modalState={modalState} setModalState={setModalState} openModal={openModal}/>
            </div>
        </>
    )
}

export default DriversFormAutomatic
