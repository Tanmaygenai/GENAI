import React from 'react';

const VehicleTable = ({ vehicles,modalState, setModalState, openModal }) => {
    const deleteModalData= (ind)=>{
        const clonePol = [...modalState.VehicleData]
        clonePol.splice(ind,1)
        setModalState({ ...modalState, VehicleData: clonePol, addPolicyTitle: "Add New Vehicle" })
        
    }
    const changeModalData= async(ind,val)=>{
        await setModalState({...modalState, editIndex:ind})
       await openModal('mainModal','Add New Vehicle',ind)
    }
    if (vehicles != null || vehicles != undefined) {
    if (vehicles.length === 0) {
        return null
    }
    else {
        return (
            <table className="driverresults">
                <thead>
                    <tr>
                     
                        <th>SL.</th>
                        <th>Year</th>
                        <th>VIN</th>
                        <th>Make</th>
                        <th>Model</th>
                        {/* <th>Garage Address</th> */}
                        <th>Registered State</th>
                        {/* <th>Vehicle Use</th> */}
                        {/* <th>Vehicle Ownership</th> */}
                        {/* <th>Main Driver of Vehicle</th> */}
                        <th>Number Of Drivers</th>
                        {/* <th>Cost New</th> */}
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    {vehicles.map((val, ind) => {
                        return (
                            <tr>
                                <td>{ind + 1}</td>
                                <td>{val.Year}</td>
                                <td>{val.VIN}</td>
                                <td>{val.Make}</td>
                                <td>{val.Model}</td>
                                {/* <td>{val.Address},{val.City},{val.State},{val.PostalCode},{val.Country}</td> */}
                                <td>{val.RegisteredState}</td>
                                {/* <td>{val.VehicleUse}</td> */}
                                {/* <td>{val.VehicleOwnership}</td>
                                <td>{val.MainDriverofVehicle}</td> */}
                                <td>{val.Howmanypeoplewilldriveyourvehicle}</td>
                                {/* <td>{val.CostNew}</td> */}
                                <td>
                                    <a type='button' onClick={() => changeModalData(ind,val)}>Change</a>
                                        &ensp;
                                    <a type='button' onClick={() => deleteModalData(ind)}>Delete</a>
                                    </td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        )
    }
}
else{
    return null
}
};

export default VehicleTable;