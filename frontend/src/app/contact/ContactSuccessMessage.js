import React from 'react';
import Layout from '../../layout/Layout'
import { useLocation } from 'react-router-dom';

const ContactSuccessMessage = ({register, errors}) => {
const {state} = useLocation();
    return (
    <Layout TabName='Contact Us Message' BreadCrum={['My Dashboard', 'Contact Us Message']}>
       <div class="policy_wrap">
             <div class="coverContainer">
                <div class="policynumber">
                    Thank you for contacting us.
                    We will reach out to you shortly.
                </div>
            </div>
       </div>
    </Layout>
    );
};

export default ContactSuccessMessage;
