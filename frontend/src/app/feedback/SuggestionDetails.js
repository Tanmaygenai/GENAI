import React from 'react';
import { useForm } from "react-hook-form"
import { useLocation, useHistory } from 'react-router-dom';
import Layout from "../../layout/Layout"
import { Form } from 'react-bootstrap'
const SuggestionDetails = () => {
    const location = useLocation();
    const history = useHistory();
    const { register, handleSubmit, reset, formState: { errors }, setValue } = useForm();

    const handleBack = () => {
        history.push('/suggestion')
    }
  return (
    <Layout TabName='AGENT Suggestion' BreadCrum={['My Dashboard', 'Agent Suggestion']}>
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
                                <h1>Suggestion Date :</h1>
                                    <Form.Control as="textarea" rows={1} size="sm" fieldName="date" readOnly={true} defaultValue={location.state.suggestionDate} label='Feedback Date' id='date' register={register} errors={errors}  showLabel={true} className="form-field" lableClass='form-label' activeBold={false} />
                                </div>
                                </div>
                                <h1>Suggestion :</h1>
                                <Form.Control as="textarea" rows={10} size="sm" fieldName="suggestion" defaultValue={location.state.suggestion} readOnly={true} id='suggestion' register={register} errors={errors} type="text" showLabel={false} className="form-field" lableClass='form-label' activeBold={true} />
                                <div class="form-element-btn">
                                    <button onClick={() => handleBack()} className='btn blue' id="">Back</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
           </Layout>
  );
}

export default SuggestionDetails;
