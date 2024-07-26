import React, { useEffect, useState } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import FormControl from "../../components/formcontrol/FormControl"
import DropDown from '../../components/dropdown/DropDown';
import { drop_data } from '../../dummydata/data';
import LoginService from '../services/LoginService';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { Link } from 'react-router-dom';
import API_Headers from '../../API_Headers';
import useMetaData from "../../context/metaData";

const Admin = () => {
    const { theme } = useMetaData();
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [response, setResponse] = useState({})
    const [headers, setHeaders] = useState([])

    console.log('theme by admin--->', theme)

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
    // useEffect(() => {
    //     console.log('thehemememe--->', theme)
    //     const base64String = `data:image/jpeg;base64,${theme.filecontent}`;
    //     // Decode the Base64 string into binary data
    //     const binaryString = atob(base64String);
    //     // Parse the binary data into an object (assuming the data is in JSON format)
    //     const obj = JSON.parse(binaryString);

    //     console.log('obj---->', obj);
    // }, [theme])

    const onSubmit = async (data) => {
        setResponse(data)
        try {
            const responseData = await LoginService.createUser(data, headers);
            if (responseData.data === 'User already exist') {
                setErrorModal({ show: true, title: "Error", body: responseData.data })
            } else {
                setErrorModal({ show: true, title: "Success", body: "User created successfully" })
            }
        }
        catch (error) {
            setErrorModal({ show: true, title: "Some Error!", body: error })
        }
    };
    return (
        <Layout TabName='Admin' BreadCrum={['My Dashboard', 'Admin']}>
            <div className="coverContainer">
                <div class="commonform_box">
                    <div class="inf">
                        <ul>
                            <li><Link to={{ pathname: '/userManagement' }}><u>User Management</u></Link></li>
                            <li><Link to={{ pathname: '/content' }}><u>Content Management</u></Link></li>
                            <li><Link to={{ pathname: '/producerManagement' }}><u>Producer Management</u></Link></li>
                            <li><Link to={{ pathname: '/dataDownload' }}><u>Data Download</u></Link></li>
                            <li><Link to={{ pathname: '/apiDocumentation' }}><u>REST API Documentation</u></Link></li>
                            <li><Link to={{ pathname: '/configDocument' }}><u>Config Dropdown Data Management</u></Link></li>
                            <li><Link to={{ pathname: '/approvalConfigDocument' }}><u>Config Approval Data Management</u></Link></li>
                            <li><Link to={{ pathname: '/upload-logo' }}><u>Upload Logo</u></Link></li>
                            <li><Link to={{ pathname: '/logo-branding' }}><u>Theme Settings</u></Link></li>
                            <li><Link to={{ pathname: '/applicationProperties' }}><u>Application Properties</u></Link></li>
                            <li><Link to={{ pathname: '/feedback' }}><u>Agent Feedback</u></Link></li>
                            <li><Link to={{ pathname: '/suggestion' }}><u>Agent Suggestion</u></Link></li>
                        </ul>
                    </div>
                </div>
            </div>

        </Layout>
    );
};

export default Admin;
