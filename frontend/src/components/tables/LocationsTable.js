import React from 'react';

const LocationsTable = ({ locations, modalState, setModalState, openModal }) => {
    const deleteModalData= (ind)=>{
        const clonePol = [...modalState.LocationData]
        clonePol.splice(ind,1)
        setModalState({ ...modalState, LocationData: clonePol, addPolicyTitle: "Add Location" })
    }
    const changeModalData= async(ind,val)=>{
        await setModalState({...modalState, editIndex:ind})
        await openModal('mainModal','Add Location',ind)
    }

    const handleViewMap = (ind) => {
        const address = modalState.LocationData[ind];
        setModalState({ ...modalState, selectedAddress: address });
    
        const mapUrl = `/map`;
        const width = window.innerWidth;
        const height = window.innerHeight;
        const popupWindow = window.open(mapUrl, 'MapWithStreetView', `width=${width},height=${height}`);
        if (popupWindow) {
            popupWindow.myData = { address: address };
        }
    };
    
    if (locations != null || locations != undefined) {
        if (locations.length === 0) {
            return null
        }
        else {
            return (
                <table className="driverresults">
                    <thead>
                        <tr>
                            <th>Location No</th>
                            <th>Address</th>
                            <th>City</th>
                            <th>State</th>
                            <th>ZipCode</th>
                            <th>Country</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        
                        {locations.map((val, ind) => {
                            return (
                                <tr>
                                    <td>{ind + 1}</td>
                                    <td>{val.StreetName}</td>
                                    <td>{val.City}</td>
                                    <td>{val.State}</td>
                                    <td>{val.PostalCode}</td>
                                    <td>{val.Country}</td>
                                    <td>
                                    <a type='button' onClick={() => changeModalData(ind,val)}>Change</a>
                                        &ensp;
                                    <a type='button' onClick={() => deleteModalData(ind)}>Delete</a>
                                    &ensp;
                                    <a type='button' onClick={() => handleViewMap(ind)}>View Map</a>
                                    </td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>
            )
        }
    }else{
        return null
    }
}

export default LocationsTable;
