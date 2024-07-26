import React from 'react'

const DriversTableAutomatic = ({ drivers,modalState, setModalState, openModal }) => {
    const deleteModalData= (ind)=>{
        const clonePol = [...modalState.DriverData]
        clonePol.splice(ind,1)
        setModalState({ ...modalState, DriverData: clonePol, addPolicyTitle: "Add Driver" })
        
    }
    const changeModalData= async(ind,val)=>{
        console.log(modalState)
       await setModalState({...modalState, editIndex:ind})
       await openModal('mainModal','Add Driver',ind)
    }

    if (drivers != null || drivers != undefined) {
    if (drivers.length === 0) {
        return null
    }
    else {
        return (
        <table className="driverresults">
            <thead>
                <tr>
                    <th>SL.</th>
                    <th>Driver Age</th>
                    <th>Driver Point</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date of Birth</th>
                    <th>License State</th>
                    <th>License Number</th>
                    <th>Years of US driving experience</th>
                    <th>Action</th>
                   
                </tr>
            </thead>
            <tbody>
                {drivers.map((val, ind) => {
                    return (
                        <tr>
                            <td>{ind + 1}</td>
                            <td>{val.Age}</td>
                            <td>{val.TotalDriverPoints}</td>
                            <td>{val.FirstName}</td>
                            <td>{val.LastName}</td>
                            <td>{val.DOB}</td>
                            <td>{val.DriverLicenseState}</td>
                            <td>{val.DriverLicenseNumber}</td>
                            <td>{val.DriverUSExperience}</td>
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
}

export default DriversTableAutomatic
