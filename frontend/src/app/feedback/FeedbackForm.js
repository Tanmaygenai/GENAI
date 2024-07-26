import React, { useEffect, useState } from 'react'
import { useForm } from "react-hook-form"
import { useHistory } from 'react-router-dom';
import Layout from "../../layout/Layout"
import TextArea from '../../components/textarea/TextArea';
import API_Headers from '../../API_Headers';
import CryptoJS from "crypto-js";
import FormControl from "../../components/formcontrol/FormControl"
import FeedbackService from '../services/FeedbackService';
import FeedbackSuccessModal from '../../components/modals/popupmodal/FeedbackSuccessModal';
const FeedbackForm = () => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    const [headers, setHeaders] = useState([])
    const [modalData, setModalData] = useState({ show: false, title: "", body: "" });
    const history=useHistory()
    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
    const { register, handleSubmit, reset, formState: { errors }, setValue } = useForm();
    const onSubmit = async (data) => {
        const feedbackData={
            ...data,
            userName: sessionStorage.getItem("userName"),
            feedbackDate: new Date(),
        }
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(feedbackData),
            secretPass
        ).toString();

        try {
            const responseData = await FeedbackService.agentFeedback(data1, headers);
            if (responseData.data=== "Success") {              
                setModalData({ show: true, title: "Submitted Successfully", body: "Thank you for your feedback"  })
            }
            else {               
                setModalData({ show: true, title: "Error occured while submitting feedback!" })
            }
        }
        catch (error) {
            if (error == 'Error: Network Error') {
                history.push("/SystemMaintenance");
            }
            else {
                alert(error.response.data.error);
            }
        }
    }
    return (
        <Layout TabName='AGENT FEEDBACK' BreadCrum={['My Dashboard', 'Agent Feedback']}>
            <div class="coverContainer">
                <div style={{ width: "70%" }}>
                    <div class="commonform_box">
                        <div class="inf">
                            <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                                <h1 style={{ fontWeight: "bolder", fontSize: "15px" }}>Provide your feedback here</h1>
                                <br />
                                <div className="form-element">
                                    <FormControl fieldName="description" label='Description' id='Description' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                </div>
                                <TextArea fieldName="feedback" Placeholder='Enter your feedback' label='Enter your feedback' id='feedback' register={register} errors={errors} type="text" showLabel={false} className="form-field" lableClass='form-label' activeBold={true} />
                                <div class="form-element-btn">
                                    <button type="submit" className='btn blue' id="">Submit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <FeedbackSuccessModal showModal={modalData.show} modalTitle={modalData.title} modalBody={modalData.body} setShowModal={setModalData} />
        </Layout>
    )
}

export default FeedbackForm