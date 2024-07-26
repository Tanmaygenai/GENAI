import React ,{useState} from "react";
import VehicleTable from "../../components/tables/VehicleTable";
import VehicleDetails from "../commercialauto/VehicleDetails";
import csvIcon from "../../assets/img/icons/csv.png";



const VehiclesForm = ({modalState, openModal,setValue, getValues,setModalState,}) => {

    const [downloadTriggered, setDownloadTriggered] = useState(false);

    const handleDownload = () => {
        setDownloadTriggered(true);
    };

    return (
        <>
        <div className='grid1'>
            <div className="form-element-btn-border">
                <a href="# " onClick={() => { openModal('mainModal', "Add New Vehicle") }} className="btn outline">Add Vehicle</a>
                <img src={csvIcon} style={{ width: '2%', marginLeft:'50px' }} /><a href='javascript:void(0);' style={{ color: 'red'}} onClick={() => handleDownload()} >Download Sample Vehicle File</a>
            </div>
        <div>
                <VehicleDetails modalState={modalState} openModal={openModal} setValue={setValue} getValues={getValues} setModalState={setModalState} downloadTriggered={downloadTriggered}  setDownloadTriggered={setDownloadTriggered}/> 
                <VehicleTable vehicles={modalState.VehicleData} modalState={modalState} setModalState={setModalState} openModal={openModal}/>
            </div>
        </div>
        </>
    )
}
export default VehiclesForm;
