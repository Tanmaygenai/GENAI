import React from 'react'
import Layout from '../../layout/Layout'
import Stepper from '../stepper/Stepper';
import { useEffect } from 'react';
import { useHistory, useLocation } from 'react-router-dom';

const TPCI = () => {
    const {state} = useLocation()
    const steps = ['Effective Date & Location', 'Product & Classification', 'Risk Information', 'Loss History', 'Quote'];
    const history = useHistory();

    const onSubmit=async(data)=>{
    }
    useEffect(() => {
        const loggedInUser = sessionStorage.getItem("userName");
        // if (!loggedInUser) {
        //     history.push("/");
        // }
        
            }, [])
    return (
        <Layout TabName='Commercial Package' BreadCrum={['My Dashboard', 'Quick Quote', 'TPCI']} sidebarQQSopen={true}>
            <Stepper state={state} steps={steps} TabName='TPCI' onSubmit={onSubmit} />
        </Layout>
    )
}

export default TPCI
