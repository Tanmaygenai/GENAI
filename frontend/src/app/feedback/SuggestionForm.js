import React, { useEffect, useState } from 'react'
import { useForm } from "react-hook-form"
import { useHistory } from 'react-router-dom';
import Layout from "../../layout/Layout"
import TextArea from '../../components/textarea/TextArea';
import API_Headers from '../../API_Headers';
import CryptoJS from "crypto-js";
import FormControl from "../../components/formcontrol/FormControl"
import SuggestionService from '../services/SuggestionService';
import SuggestionSuccessModal from '../../components/modals/popupmodal/SuggestionSuccessModal';
const SuggestionForm = () => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    const [headers, setHeaders] = useState([])
    const [modalData, setModalData] = useState({ show: false, title: "", body: "" });
    const history=useHistory()
    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
    const { register, handleSubmit, reset, formState: { errors }, setValue } = useForm();
    const onSubmit = async (data) => {
        const suggestionData={
            ...data,
            userName: sessionStorage.getItem("userName"),
            suggestionDate: new Date(),
        }
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(suggestionData),
            secretPass
        ).toString();

        try {
            const responseData = await SuggestionService.agentSuggestion(data1, headers);
            if (responseData.data=== "Success") {              
                setModalData({ show: true, title: "Submitted Successfully", body: "Thank you for your suggestion"  })
            }
            else {               
                setModalData({ show: true, title: "Error occured while submitting suggestion!" })
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
        <Layout TabName='AGENT Suggestion' BreadCrum={['My Dashboard', 'Agent Suggestion']}>
            <div class="coverContainer">
                <div style={{ width: "70%" }}>
                    <div class="commonform_box">
                        <div class="inf">
                            <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                                <h1 style={{ fontWeight: "bolder", fontSize: "15px" }}>Provide your suggestions here</h1>
                                <br />
                                
                                <TextArea fieldName="suggestion" Placeholder='Enter your suggestions here' label='Enter your suggestions here' id='suggestion' register={register} errors={errors} type="text" showLabel={false} className="form-field" lableClass='form-label' activeBold={true} />
                                <div class="form-element-btn">
                                    <button type="submit" className='btn blue' id="">Submit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <SuggestionSuccessModal showModal={modalData.show} modalTitle={modalData.title} modalBody={modalData.body} setShowModal={setModalData} />
        </Layout>
    )
}

export default SuggestionForm