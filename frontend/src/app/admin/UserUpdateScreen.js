import React, { useEffect, useState } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import FormControl from "../../components/formcontrol/FormControl"
import DropDown from '../../components/dropdown/DropDown';
import { drop_data } from '../../dummydata/data';
import LoginService from '../services/LoginService';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { useHistory, useLocation } from 'react-router-dom';
import CryptoJS from "crypto-js";
import API_Headers from '../../API_Headers';
const UserUpdateScreen = () => {
    const history = useHistory();
    const secretPass = process.env.REACT_APP_Secret_Key;
    const handleBack = () => {
        history.push({
            pathname: "/userManagement/userUpdate"
        });
    }
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [response, setResponse] = useState({})
    const [headers, setHeaders] = useState([])

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
    const location = useLocation();

    const onSubmit = async (data) => {
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(data),
            secretPass
          ).toString();
        if (data.password !== data.confirmpassword) {
            setErrorModal({ show: true, title: "Error", body: "Password didnot Match" })
        } else {
            try {
                const responseData = await LoginService.updateUser(data1, headers);
                history.push({
                    pathname: "/userManagement/userUpdate"
                });
                if (responseData.data === 'User updation failed') {
                    setErrorModal({ show: true, title: "Error", body: responseData.data })
                } else {
                    setErrorModal({ show: true, title: "Success", body: "User updated successfully" })
                }
            }
            catch (error) {
                setErrorModal({ show: true, title: "Error", body: error })
            }
        }
    };

    return (
        <Layout TabName='User Update Screen' BreadCrum={['My Dashboard', 'Admin', 'User Management', 'User Update', 'User Update Screen']}>
            <div class="commonform_box">
                <div class="inf">
                    <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                        <div class="fullhead">Update Agent / Admin Screen</div>
                        <br />
                        <div className="grid2">
                            <div class="form-element">
                                <FormControl fieldName="userName" label='User Name' id='username' register={register} defaultValue={location.state.user[2]} errors={errors} type="text" showLabel={true} className="form-field" lableClass='form-label' activeBold={false} />
                            </div>
                        </div>
                        <div className="grid2">
                            <div class="form-element">
                                <FormControl fieldName="password" label='Password' id='password' register={register} errors={errors} type="password" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                        </div>
                        <div className="grid2">
                            <div class="form-element">
                                <FormControl fieldName="confirmpassword" label='Confirm Password' register={register} id='confirmpassword' errors={errors} type="password" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
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

export default UserUpdateScreen;
