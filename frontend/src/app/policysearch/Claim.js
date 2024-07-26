import React from 'react'

const Claim = ({ openModal, modalState, ...props }) => {
    const claimData = props.claimData;


    return (
        <div class="claimbox">
            <div class="claimnumber text-center"><span>Claim Number:{claimData.claimNumber} </span></div>
            <div class="details">
                <p></p>
            </div>
            <div class="shortdetails">
                <ul>
                    <li><span>Adjustor Assigned : </span> <p>{claimData.assignedAdjustor}</p></li>
                    <li><span>Loss Description : </span> <p>{claimData.shortDescription}</p></li>
                    <li><span>Loss Date : </span> <p>{claimData.lossDate}</p></li>
                    <li><span>Status of Claim : </span> <p>{claimData.statusCode}</p></li>
                    <li><span>Phone : </span> <p>{claimData.phoneNumber}</p></li>
                </ul>
            </div>
            <div className="form-element-btn-border text-center">
                {/* <button onClick={() => { openModal('mainModal', "Claim Details", claimData) }} className="btn outline">View Details</button> */}
            </div>
        </div>
    )
}
export default Claim;