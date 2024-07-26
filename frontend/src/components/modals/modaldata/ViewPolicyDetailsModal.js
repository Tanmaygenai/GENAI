import React, { useEffect } from 'react'
import { formFunctions } from '../../../utility/jqueryFunctions';

const ViewPolicyDetailsModal = ({ claimData, register, errors, control, getValues, setValue }) => {
    useEffect(() => {
        formFunctions();
    }, [])
    return (
        <div class="details">
            <div class="shortdetails">
                <ul>
                    <li><span>Adjustor Assigned : </span> <p>{claimData.assignedAdjustor}</p></li>
                    <li><span>Loss Description : </span> <p>{claimData.shortDesc}</p></li>
                    <li><span>Loss Date : </span> <p>{claimData.lossDt}</p></li>
                    <li><span>Status of Claim : </span> <p>{claimData.statusCd}</p></li>
                </ul>
            
                <div class="reportnote">
                    <h4>Call and speak to a Exavalu Claims Manager if you have any concerns or questions</h4>
                    <p><strong>Phone:</strong> <a href="tel:818.466.5900">818.466.5900</a><br />
                        <strong>Toll Free:</strong> <a href="tel:877.353.8672">877.353.8672</a><br />
                        <strong>Fax:</strong> 818.616.7048</p>
                    <p><strong>Email:</strong> <a href="tel:877.353.8672">claims@exa-ins.com</a></p><a href="tel:877.353.8672">
                    </a></div><a href="tel:877.353.8672">
                </a>
            </div>    
        </div>
    )
}

export default ViewPolicyDetailsModal
