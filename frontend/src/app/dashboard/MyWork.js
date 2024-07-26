import React, { useEffect, useState } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import FormControl from "../../components/formcontrol/FormControl"
import DropDown from '../../components/dropdown/DropDown';
import { drop_data } from '../../dummydata/data';
import LoginService from '../services/LoginService';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { Link } from 'react-router-dom';
import TabsTable from './tabstable/TabsTable'
import API_Headers from '../../API_Headers';
const MyWork = () => {
    const [openWorkDBItem, setOpenWorkDBItem] = useState([]);
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [response, setResponse] = useState({})
    const [headers, setHeaders] = useState([])

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
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
            alert(error);
        }
    };
    return (
        <Layout TabName='MY TRANSACTIONS' BreadCrum={['My Dashboard']} sidebarQQSopen='true'>
            <div className="coverContainer">
                <div className="dashboard">
                    <div className="left">
                        <TabsTable openWorkDBItem={openWorkDBItem} setOpenWorkDBItem={setOpenWorkDBItem} />
                    </div>
                </div>
                
            </div>
        </Layout>
    )
};

export default MyWork;
