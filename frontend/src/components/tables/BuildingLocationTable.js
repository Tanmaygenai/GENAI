import React from 'react';

const BuildingLocationTable = ({ buildingLoc, modalState, setModalState, openModal }) => {
    const deleteModalData= (ind)=>{
        const clonePol = [...modalState.BuildingLocData]
        clonePol.splice(ind,1)
        setModalState({ ...modalState, BuildingLocData: clonePol, addPolicyTitle: "Add New Building" })
    }
    const changeModalData= async(ind,val)=>{
        await setModalState({...modalState, editIndex:ind})
        await openModal('mainModal','Add New Building',ind)
    }

    if (buildingLoc.length === 0) {
        return null
    }
    else {
        return (
            <table className="driverresults">
                <thead>
                    <tr>
                        <th>SL.</th>
                        <th>Location Details</th>
                        <th>Protective Devices</th>
                        <th>Construction Type</th>
                        <th>Sprinklered</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    {buildingLoc.map((val, ind) => {
                        return (
                            <tr>
                                <td>{ind + 1}</td>
                                <td>{val.LocationNo}</td>
                                <td>{val.ProtectiveDevices}</td>
                                <td>{val.ConstructionType}</td>
                                <td>{val.Sprinklered}</td>
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
};

export default BuildingLocationTable;