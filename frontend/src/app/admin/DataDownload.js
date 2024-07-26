import React, { useEffect } from 'react'
import { useForm } from "react-hook-form";
import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import Layout from "../../layout/Layout"
import FormControl from "../../components/formcontrol/FormControl";
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import DataDownloadDetails from './DataDownloadDetails';
import API_Headers from '../../API_Headers';
const DataDownload = () => {
    const { register, handleSubmit, reset, formState: { errors }, setValue, control, getValues } = useForm();
    const history = useHistory();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [isLoaded, setIsLoaded] = useState(false)
    const [systemId, setSystemId] = useState('');


    const [headers, setHeaders] = useState([])

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])


    const clearIsLoaded = (data) => {
        setIsLoaded(false);

    };


    const onSubmit = async (data) => {
        clearIsLoaded(data);
        setSystemId(data.systemId)
        setIsLoaded(true);

    };

    const handleBack = () => {
        history.push({
            pathname: "/admin"
        });
    }


    return (
        <Layout TabName='Data Download' BreadCrum={['My Dashboard', 'Admin', 'Data Download']}>
            <div className="coverContainer">
                <div class="commonform_box">
                    <div class="inf">
                        <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                            <br />
                            <div className="grid3">
                                <div class="form-element">
                                    <FormControl fieldName="systemId" label='System Id' id='systemId' setValue={setValue} setState={setSystemId} register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                </div>
                                <br />
                                <div className="form-element-btn">
                                    <button type="submit" class="btn blue" id="">Search</button>
                                </div>
                                <div className="form-element-btn-left">
                                    <button type="submit" class="btn blue" id="" onClick={handleBack}>Back</button>
                                </div>
                            </div>
                            
                        </form>
                    </div>
                </div>
                <br />
                <br />
                <br />
                <div>
                    {
                        (isLoaded === false) ?
                            <div class="coverContainer"></div> :
                            <DataDownloadDetails systemId={systemId} ></DataDownloadDetails>
                    }
                </div>
                <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
            </div>
        </Layout>

    );
}

export default DataDownload
