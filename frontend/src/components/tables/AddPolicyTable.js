import React from 'react'

const AddPolicyTable = ({policies, modalState, setModalState, openModal}) => {
    const deleteModalData= (ind)=>{
        const clonePol = [...modalState.AddPolicyData]
        clonePol.splice(ind,1)
        setModalState({ ...modalState, AddPolicyData: clonePol, addPolicyTitle: "" })
    }
    const changeModalData= async(ind,val)=>{
        let policyName = modalState.AddPolicyData[ind].AddUnderlyingPolicies
        await setModalState({...modalState, editIndex:ind})
        await openModal('mainModal',policyName,ind)
    }

    if (policies.length === 0) {
        return null
    }
    else {
        return (
            <table className="driverresults">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Policy Type</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {policies.map((val, ind) => {
                        return (
                            <tr>
                                <td>{ind + 1}</td>
                                <td>{val.AddUnderlyingPolicies} {val.PolicyType === undefined ? null : `– ${val.PolicyType}`}</td>
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

export default AddPolicyTable