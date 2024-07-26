import React, { useEffect, useState } from 'react'
import { useForm } from "react-hook-form"
import { useHistory } from 'react-router-dom';
import DropDown from '../../components/dropdown/DropDown';
import Layout from "../../layout/Layout"
import FormControl from "../../components/formcontrol/FormControl"
import TextArea from "../../components/textarea/TextArea";
import ContactUsService from '../services/ContactUsService';
import CryptoJS from "crypto-js";
import API_Headers from '../../API_Headers';
import CognitoUserService from '../services/CognitoUserService';
import dropdown_val from '../../dummydata/miscellaneous.csv';
import Papa from 'papaparse';
import SuccessMessageModal from '../../components/modals/popupmodal/SuccessMessageModal';
import useLoader from "../../context/loader"

const ContactForm = () => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    const {setLoader} = useLoader()
    const [phoneNumber, setPhoneNumber] = useState("");
    const [modalData, setModalData] = useState({ show: false, title: "", body: "" });
    const [headers, setHeaders] = useState([])

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
    const { register, handleSubmit, reset, formState: { errors }, setValue } = useForm();

    const [dropdownVal, setDropdownVal] = useState([]);
    var commonConfig = { delimiter: "," };
    useEffect(() => {
        Papa.parse(
            dropdown_val,
            {
                ...commonConfig,
                header: true,
                download: true,
                complete: (result) => {
                    setDropdownVal(result.data)
                }
            }
        );
    }, [])
    useEffect(() => {
        if (headers.length !== 0) {
            const responseData = CognitoUserService.getCognitoUsers(headers);
            responseData.then((res) => {
                res.data.map((rd) => {
                    if (rd.userName == sessionStorage.getItem("userName")) {
                        setPhoneNumber(rd.phone)
                    }
                })
            })
        }
    }, [headers])

    var caseId = ""
    const history = useHistory();

    const onSubmit = async (data) => {
        setLoader(true)
        const updatedData = {
            ...data,
            userName: sessionStorage.getItem("userName"),
            email: sessionStorage.getItem("email"),
            phoneNumber: phoneNumber,
            createdDate: new Date(),
            description: JSON.stringify([{
                id: 1,
                sender: sessionStorage.getItem("email"),
                message: data.description,
                createdAt: new Date()
            }]),
            status: 'Open'
        }
        reset();
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(updatedData),
            secretPass
        ).toString();
        try {
            const responseData = await ContactUsService.contactUs(data1, headers);
            caseId = responseData.data;
            if (caseId !== "") {
                setLoader(false)
                setModalData({ show: true, title: "Contact Us", body: "Your ticket has created with the Case ID : " + caseId })
            }
            else {
                setLoader(false)
                setModalData({ show: true, title: "Error occured while craeting the case!" })
            }
        }
        catch (error) {
                setLoader(false)
            if (error == 'Error: Network Error') {
                history.push("/SystemMaintenance");
            }
            else {
                alert(error.response.data.error);
            }
        }
    }
    useEffect(() => {
        const loggedInUser = sessionStorage.getItem("userName");
        if (!loggedInUser) {
            history.push("/");
        }
    })



    return (
        <Layout TabName='Contact Us' BreadCrum={['My Dashboard', 'Contact Us']}>
            <div class="coverContainer">
                <h1 style={{ fontWeight: "bolder", fontSize: "16px" }}>We'd like to hear from you. Please complete the form below
                    so we can respond to your enquiry
                </h1>
                    <div style={{ width: "100%", display: "inline-flex" }}>
                    <div style={{ width: "60%" }}>
                        <div class="commonform_box">

                            <div class="inf">
                                <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                                    <h1 style={{ fontWeight: "bolder", fontSize: "15px" }}>Send us a message</h1>

                                    <div className="form-element">
                                        <FormControl fieldName="name" label='Name' id='name' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                    <div className="form-element">
                                        <FormControl fieldName="agencyName" label='Agency&apos;s Name' id='agency_name' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>
                                    <div className="form-element form-select">
                                        <DropDown fieldName='category' label='Category' id='category' defaultValue="" register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                                        {dropdownVal.map((value, ind) => {
                                            return value.Category!== "" && value.Category !==null && value.Category !== undefined  ? (
                                                <option value={value.Category === "-select-" ? "" : value.Category}>{value.Category}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                        </DropDown>
                                    </div>
                                    <div className="form-element form-select">
                                        <DropDown fieldName='severity' label='Severity' id='severity' defaultValue="" register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                                        {dropdownVal.map((value, ind) => {
                                            return value.Severity!== "" && value.Severity !==null && value.Severity !== undefined  ? (
                                                <option value={value.Severity === "-select-" ? "" : value.Severity}>{value.Severity}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                        </DropDown>
                                    </div>
                                    <div className="form-element">
                                        <FormControl fieldName="message" label='Subject' id='message' maxLength={60} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                    </div>

                                    <TextArea fieldName="description" Placeholder='Enter your description' label='Enter your description' id='description' register={register} errors={errors} type="text" showLabel={false} className="form-field" lableClass='form-label' activeBold={true} />

                                    <div class="form-element-btn">
                                        <button type="submit" className='btn blue' id="">Submit</button>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                    <div style={{ width: "30%", marginLeft: "30px" }}>
                        <div class="commonform_box">
                            <div class="inf">
                                <h1 style={{ fontWeight: "bolder", fontSize: "16px" }}>Write us a message</h1>
                                <li mail to="portal.dev.exavalu.com">portal.dev.exavalu.com</li>
                                <br />
                                <h1 style={{ fontWeight: "bolder", fontSize: "16px" }}>Call us</h1>
                                <p>Telephone: <span style={{ fontWeight: "bolder" }}>+1 (111-222-333)</span></p>
                                <p>----------------------</p>
                                <h1 style={{ fontWeight: "bolder", fontSize: "16px" }}>Our Offices</h1>
                                <h3 style={{ color: "red", fontWeight: "bolder" }}>United States of America</h3>
                                <h3>5000 Birch Street, West Tower, Suite 3000 Newport Beach, CA 92660, USA</h3>
                                <br />
                                <h3 style={{ color: "red", fontWeight: "bolder" }}>India</h3>
                                <h3>BENGAL ECO INTELLIGENT PARK, Unit No.: C, 6th Floor, EM Block, Plot 3, Sector V, Salk Lake City,
                                    Kolkata, West Bengal, India
                                    Pin Code - 700091
                                </h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <SuccessMessageModal showModal={modalData.show} modalTitle={modalData.title} modalBody={modalData.body} setShowModal={setModalData} />
        </Layout>
    )
}

export default ContactForm
