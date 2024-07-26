import React from 'react';
import Layout from '../../layout/Layout'
import { useLocation } from 'react-router-dom';

const ReportLossSuccessMessage = ({register, errors}) => {
const {state} = useLocation();
    return (
    <Layout TabName='REPORT A LOSS MESSAGE' BreadCrum={['My Dashboard', 'Report A Loss Message']}>
       <div class="policy_wrap">
             <div class="coverContainer">
                <div class="policynumber">
                    Thank you for submitting the details.
                    Your Loss Notice Number is <span><strong>{state}</strong></span> generated, claim representative will contact you shortly.
                </div>
            </div>
       </div>
    </Layout>
    );
};

export default ReportLossSuccessMessage;
