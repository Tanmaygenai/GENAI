import React, { useState } from "react"
import { useForm } from "react-hook-form";
import { useHistory } from 'react-router-dom';
import FormControl from "../../../components/formcontrol/FormControl";

const OpendTab = ({ TabName }) => {
    const history = useHistory();
    const { register, handleSubmit, formState: { errors } } = useForm();
    const onSubmit = async (data) => {
        history.push({
            pathname: "/searchPolicy",
            state: {
                policyNumber: data.policyNumber
            }
        });
    };
    return (
        <div className="commonheader">
            {TabName}
            {
                TabName === 'MY TRANSACTIONS' ?
                    <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                        <div className="commonheader-left">
                            <FormControl fieldName="policyNumber" label='Policy Number' id='policyNumber' register={register} errors={errors} type="text" showLabel={false} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                        </div>
                        <button style={{  }} type="submit" class="btn blue" id="">Search</button>
                    </form>
                    : []
            }
        </div >
    )
}
export default OpendTab;