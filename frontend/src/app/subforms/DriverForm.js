import React from 'react'
import DriverTable from '../../components/tables/DriverTable';
import DriverDetails from '../commercialauto/DriverDetails';
import { useState } from 'react';
import csvIcon from "../../assets/img/icons/csv.png";

const DriverForm = ({ modalState, openModal,setValue, getValues,setModalState}) => {

    const [downloadTriggered, setDownloadTriggered] = useState(false);

    const handleDownload = () => {
        setDownloadTriggered(true);
    };

    return (
        <>
           <div className='grid1'>
            <div className="form-element-btn-border">
                <a href="# " onClick={() => { openModal('mainModal', "Add New Driver") }} className="btn outline">Add Driver</a>
                <img src={csvIcon} style={{ width: '2%', marginLeft:'50px' }} /><a href='javascript:void(0);' style={{ color: 'red'}} onClick={() => handleDownload()}>Download Sample Driver File</a>
            </div>
            <div>
                <DriverDetails modalState={modalState} openModal={openModal} setValue={setValue} getValues={getValues} setModalState={setModalState} downloadTriggered={downloadTriggered}  setDownloadTriggered={setDownloadTriggered}/> 
                <DriverTable drivers={modalState.DriverData} modalState={modalState} setModalState={setModalState} openModal={openModal}/>
                </div>
            </div>
        </>
    )
}

export default DriverForm
