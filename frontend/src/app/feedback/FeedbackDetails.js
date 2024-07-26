import React from 'react'
import { useForm } from "react-hook-form"
import { useLocation, useHistory } from 'react-router-dom';
import Layout from "../../layout/Layout"
import { Form } from 'react-bootstrap'

const FeedbackDetails = () => {
    const location = useLocation();
    const history = useHistory();
    const { register, handleSubmit, reset, formState: { errors }, setValue } = useForm();

    const handleBack = () => {
        history.push('/feedback')
    }
    return (
        <Layout TabName='AGENT FEEDBACK' BreadCrum={['My Dashboard', 'Agent Feedback']}>
            <div class="coverContainer">
                <div style={{ width: "70%" }}>
                    <div class="commonform_box">
                        <div class="inf">
                            <form class="allForm" >
                                <div className='grid2'>
                                <div className="form-element">
                                    <h1>Agent Name :</h1>
                                    <Form.Control as="textarea" rows={1} size="sm" fieldName="username" readOnly={true} defaultValue={location.state.userName} label='Agent' id='username' register={register} errors={errors}  showLabel={true} className="form-field" lableClass='form-label' activeBold={false} />
                                </div>
                                <div className="form-element">
                                <h1>Feedback Date :</h1>
                                    <Form.Control as="textarea" rows={1} size="sm" fieldName="date" readOnly={true} defaultValue={location.state.feedbackDate} label='Feedback Date' id='date' register={register} errors={errors}  showLabel={true} className="form-field" lableClass='form-label' activeBold={false} />
                                </div>
                                </div>
                                <div className="form-element">
                                <h1>Description :</h1>
                                    <Form.Control as="textarea" rows={1} size="sm" fieldName="description" readOnly={true} defaultValue={location.state.description} label='Description' id='Description' register={register} errors={errors} type="text" showLabel={true} className="form-field" lableClass='form-label' activeBold={false} />
                                </div>
                                <h1>Feedback :</h1>
                                <Form.Control as="textarea" rows={10} size="sm" fieldName="feedback" readOnly={true} defaultValue={location.state.feedback} id='feedback' register={register} errors={errors} type="text" showLabel={true} className="form-field" lableClass='form-label' activeBold={false} />
                                <div class="form-element-btn">
                                    <button onClick={() => handleBack()} className='btn blue' id="">Back</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            {/* <FeedbackSuccessModal showModal={modalData.show} modalTitle={modalData.title} modalBody={modalData.body} setShowModal={setModalData} /> */}
        </Layout>
    )
}

export default FeedbackDetails;
