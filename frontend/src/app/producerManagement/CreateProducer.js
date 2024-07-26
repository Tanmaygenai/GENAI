import React, { useEffect, useState } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import FormControl from "../../components/formcontrol/FormControl"
import DropDown from '../../components/dropdown/DropDown';
import { drop_data } from '../../dummydata/data';
import LoginService from '../services/LoginService';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { useHistory } from 'react-router-dom';
import ProducerManagementService from '../services/ProducerManagementService';
import API_Headers from '../../API_Headers';
import CryptoJS from "crypto-js";
const CreateProducer = () => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    const history = useHistory();
    const handleBack = () => {
        history.push({
            pathname: "/producerManagement"
        });
    }
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [response, setResponse] = useState({})
    const [headers, setHeaders] = useState([])

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
    const onSubmit = async (data) => {
        setResponse(data)
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(data),
            secretPass
          ).toString();
        try {
            const responseData = await ProducerManagementService.createProducer(data1, headers);
            if (responseData.data === 'Producer already exist') {
                setErrorModal({ show: true, title: "Error", body: responseData.data })
            } else if(responseData.data === 'Producer created successfully'){
                setErrorModal({ show: true, title: "Success", body: "Producer created successfully" })
            } else {
                setErrorModal({ show: true, title: "Error", body: responseData.data })
            }
        }
        catch (error) {
            alert(error);
        }
    };

    return (
        <Layout TabName='New Producer' BreadCrum={['My Dashboard', 'Admin', 'Producer Management', 'New Producer']}>
            <div class="commonform_box">
                <div class="inf">
                    <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                        <div class="fullhead">New Producer</div>
                        <br />
                        <div className="grid2">
                            <div class="form-element">
                                <FormControl fieldName="producer_code" label='Producer Code' id='producer_code' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                            <div class="form-element">
                                <FormControl fieldName="agency_code" label='Agency Code' id='agency_code' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                            <div class="form-element">
                                <FormControl fieldName="group_code" label='Group Code' id='group_code' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                            <div class="form-element">
                                <FormControl fieldName="name" label='Name' id='name' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                            <div class="form-element">
                                <FormControl fieldName="email" label='Email' id='email' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                        </div>
                        <div class="form-element-btn">
                            <button type="submit" className='btn blue' id="">Submit</button>
                        </div>
                        <div className="form-element-btn-left">
                            <button type="submit" class="btn blue" id="" onClick={handleBack}>Back</button>
                        </div>
                    </form>
                </div>
                <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
            </div>
        </Layout>
    );
};

export default CreateProducer;
