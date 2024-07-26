import React, { useEffect, useState } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import FormControl from "../../components/formcontrol/FormControl"
import DropDown from '../../components/dropdown/DropDown';
import { drop_data } from '../../dummydata/data';
import LoginService from '../services/LoginService';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { Link, useHistory } from 'react-router-dom';
import API_Headers from '../../API_Headers';
const UserManagement = () => {
    // const history = useHistory();
    // const handleBack = () => {
    //     history.push({
    //         pathname: "/admin"
    //     });
    // }
    // const { register, handleSubmit, formState: { errors } } = useForm();
    // const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    // const [response, setResponse] = useState({})
    const [headers, setHeaders] = useState([])

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
    // const onSubmit = async (data) => {
    //     setResponse(data)
    //     console.log('data in admin page ::', data)
    //     try {
    //         const responseData = await LoginService.createUser(data, headers);
    //         console.log(responseData.data);
    //         if (responseData.data === 'User already exist') {
    //             setErrorModal({ show: true, title: "Error", body: responseData.data })
    //         } else {
    //             setErrorModal({ show: true, title: "Success", body: "User created successfully" })
    //         }
    //     }
    //     catch (error) {
    //         console.log(error);
    //     }
    // };

    return (
        <Layout TabName='User Management' BreadCrum={['My Dashboard', 'Admin', 'User Management']}>
            <div className="coverContainer">
                <div class="commonform_box">
                    <div class="inf">
                        <ul>
                            <li><Link to={{ pathname: '/userManagement/userRegistration' }}><u>User Registration</u></Link></li>
                            <li><Link to={{ pathname: '/userManagement/userUpdate' }}><u>User Update</u></Link></li>
                        </ul>
                    </div>
                </div>
            </div>

        </Layout>
    );
};

export default UserManagement;
