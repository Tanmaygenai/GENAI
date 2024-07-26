import React, { useEffect } from 'react'
import { useForm } from "react-hook-form";
import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import Layout from "../../layout/Layout"
import classNames from 'classnames';
import { fileFunction, formFunctions } from '../../utility/jqueryFunctions';
import FormControl from "../../components/formcontrol/FormControl";
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import MultipleFile from '../../components/multiplefile/MultipleFile';
import UploadCabinateService from '../services/UploadCabinateService';
import API_Headers from '../../API_Headers';
const Content = () => {
  const { register, handleSubmit, formState: { errors } } = useForm();
  const history = useHistory();
  const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
  const [headers, setHeaders] = useState([])

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
  const [multipleImages, setMultipleImages] = useState([]);
  const onSubmit = async (data) => {
    const file = data.file[0];
    let payload = new FormData();
    payload.append('file', file);
    payload.append('classification', data.classification);
    payload.append('description', data.description);
    try {
      const responseData = await UploadCabinateService.attachDocument1(payload, headers)
      if (responseData.data == "Success") {
        setErrorModal({ show: true, title: "Success Message", body: "Document uploaded successfully" })
      } else {
        setErrorModal({ show: true, title: "Network Error", body: "Document upload failed. Please try after some time" })
      }
      setErrorModal({ show: true, title: "Success Message", body: "Document uploaded successfully" })
    }
    catch (Error) {
      let error = Error.response;
      setErrorModal({ show: true, title: "Network Error", body: "Document upload failed. Please try after some time" });
    }
  };


  const handleBack = () => {
    history.push({
        pathname: "/admin"
    });
}


  useEffect(() => {
    const loggedInUser = sessionStorage.getItem("userName");
    if (!loggedInUser) {
      history.push("/");
    }
    fileFunction();
  }, [])
  return (
    <Layout TabName='Content Management' BreadCrum={['My Dashboard', 'Admin', 'Content Management']}>
      <div className="coverContainer">
        <div class="commonform_box">
          <div class="inf">
            <form class="allForm" onSubmit={handleSubmit(onSubmit)}>

              <div style={{ color: '#CF0918' }}><label><b>Cabinate Document:</b></label></div>
              <br />
              <div className="grid3">

                <div class="inputfile-box">
                  <label for="file">
                    <span id="reporter_information_file-name" class="file-box">Upload Document <b>*</b></span>
                    <span class="file-button">
                      Select File
                    </span>
                  </label>
                  <input type="file"
                    id="file"
                    {...register("file", { required: "Document is Required." })}
                    className={classNames('inputfile', {
                      "is-invalid": errors.file,
                    })}
                  />{errors.file && (
                    <div className="invalid-feedback">
                      {errors.file.message}
                    </div>
                  )}
                  <label style={{ color: '#CF0918' }}><b>Types of files supported(doc,XML,PDF,png,jpg).</b></label><br/>
                  <label style={{ color: '#CF0918' }}><b>Max file size for upload is 5MB.</b></label>
                </div>
                {/* <MultipleFile multipleImages={multipleImages} setMultipleImages={setMultipleImages} register={register} errors={errors} /> */}
                <div class="form-element">
                  <FormControl fieldName="classification" label='classification' id='classification' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                </div>
                <div class="form-element">
                  <FormControl fieldName="description" label='Description' id='description' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                </div>
                <br />
                <div className="form-element-btn">
                  <button type="submit" class="btn blue" id="">Attach Document</button>
                </div>
                <div className="form-element-btn-left">
                  <button type="submit" class="btn blue" id="" onClick={handleBack}>Back</button>
                </div>
              </div>
            </form>
          </div>
        </div>
        <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
      </div>
    </Layout>

  );
}

export default Content
