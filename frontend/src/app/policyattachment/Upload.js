import React, { useEffect } from 'react'
import { useForm } from "react-hook-form";
import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import UploadService from '../services/UploadService';
import Layout from "../../layout/Layout"
import classNames from 'classnames';
import { fileFunction, formFunctions } from '../../utility/jqueryFunctions';
import FormControl from "../../components/formcontrol/FormControl";
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import MultipleFile from '../../components/multiplefile/MultipleFile';
import API_Headers from '../../API_Headers';
import DropDown from '../../components/dropdown/DropDown';
import dropdown_val from '../../dummydata/addAttachment.csv';
import Papa from 'papaparse';
import {fileTypeMappings,statusMappings,documentTypeMappings} from '../../utility/UploadFileMapping';
import { json } from 'react-router';
import useLoader from "../../context/loader"

const Upload = () => {
  const { state,register, handleSubmit, formState: { errors } } = useForm();
  const history = useHistory();
  const { setLoader } = useLoader()
  const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
  const [headers, setHeaders] = useState([])
  const [dropdownVal, setDropdownVal] = useState([]);
  var commonConfig = { delimiter: "," };

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
  const [multipleImages, setMultipleImages] = useState([]);
  const onSubmit = async (data) => {
    const file = data.file[0];
    let payload = new FormData();
    for (let key of Object.keys(multipleImages)) {
      if (key !== 'length') {
        payload.append('document', multipleImages[key]);
      }
    }
    payload.append('transactionNumber', data.TransactionNumber);
    payload.append('transactionIndicator',data.TransactionIndicator);
    payload.append('documentType',documentTypeMappings[data.DocumentType]);
    payload.append('fileType',fileTypeMappings[data.FileType]);
    payload.append('status',statusMappings[data.status]);
    setLoader(true)
    try {
      const responseData = await UploadService.attachDocument(payload, headers);
      const errorMessage = responseData.data;
      setLoader(false)
      if (errorMessage.includes("Error attaching document: Exception encountered trying to add document with doc UID: ")) {
          const startIndex = errorMessage.lastIndexOf("/") + 1;
          const fileName = errorMessage.substring(startIndex);
          const formattedErrorMessage = `A file with name "${fileName}" is already attached`;
          setErrorModal({
              show: true,
              title: "Error Attaching Document",
              body: formattedErrorMessage
          });
      } else {
          const expectedMessage = "Document Attached Successfully for transaction number: " + data.TransactionNumber;
          if (responseData.data === expectedMessage || responseData.data === "Document Attached Successfully") {
              setErrorModal({
                  show: true,
                  title: "Success Message",
                  body: "Document uploaded successfully"
              });
          } else {
              setErrorModal({
                  show: true,
                  title: "Not Found",
                  body: responseData.data
              });
          }
      }
  } catch (Error) {
    setLoader(false)
    let error = Error.response;
    setErrorModal({ show: true, title: "Network Error", body: "Document upload failed. Please try after some time" });
  }};

  const handleBack = () => {
    history.push({
        pathname: "/dashboard"
    });
}
  useEffect(() => {
    const loggedInUser = sessionStorage.getItem("userName");
    if (!loggedInUser) {
      history.push("/");
    }
    fileFunction();
  }, [])

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
  return (
    <Layout TabName='Add Attachment' BreadCrum={['My Dashboard', 'Add Attachment']}>
      <div className="coverContainer">
        <div class="commonform_box">
          <div class="inf">
            <form class="allForm" onSubmit={handleSubmit(onSubmit)}>

              <div className="fullhead">Policy Document:</div><br />
              <div className="grid2">
                {/* <div class="inputfile-box">
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
                </div> */}
                <div>  <MultipleFile multipleImages={multipleImages} setMultipleImages={setMultipleImages} register={register} errors={errors} /></div>
                <div class="form-element">
                  <FormControl fieldName="TransactionNumber" label='Quote/Policy/Claim Number' id='TransactionNumber' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                </div>
                <div className="form-element form-select">
                        <DropDown fieldName='FileType' label='File Type' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                            {dropdownVal.map((value, ind) => {
                                return value.FileType!== "" && value.FileType!== null && value.FileType!== undefined ? (
                                    <option value={value.FileType === "-select-" ? "" : value.FileType}>{value.FileType}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                    </div>
                    <div className="form-element form-select">
                        <DropDown fieldName='status' label='Status' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                            {/* {drop_data.PrimaryBusinessSubType.map((value, ind) => (
                                <option value={value}>{value}</option>
                            ))} */}
                            {dropdownVal.map((value, ind) => {
                                return value.status!== "" && value.status!== null && value.status!== undefined ? (
                                    <option value={value.status === "-select-" ? "" : value.status}>{value.status}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                    </div>
                    <div className="form-element form-select">
                        <DropDown fieldName='DocumentType' label='Document Type' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                            {/* {drop_data.PrimaryBusinessSubType.map((value, ind) => (
                                <option value={value}>{value}</option>
                            ))} */}
                            {dropdownVal.map((value, ind) => {
                                return value.DocumentType!== "" && value.DocumentType!== null && value.DocumentType!== undefined ? (
                                    <option value={value.DocumentType === "-select-" ? "" : value.DocumentType}>{value.DocumentType}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                    </div>
                    <div className="form-element form-select">
                        <DropDown fieldName='TransactionIndicator' label='Transaction Indicator' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={true} >
                            {/* {drop_data.PrimaryBusinessSubType.map((value, ind) => (
                                <option value={value}>{value}</option>
                            ))} */}
                            {dropdownVal.map((value, ind) => {
                                return value.TransactionIndicator!== "" && value.TransactionIndicator!== null && value.TransactionIndicator!== undefined ? (
                                    <option value={value.TransactionIndicator === "-select-" ? "" : value.TransactionIndicator}>{value.TransactionIndicator}</option>
                                ) :
                                    <></>
                            }
                            )}
                        </DropDown>
                    </div>
              </div>
              <br />
              <div className="form-element-btn">
                <button type="submit" class="btn blue" id="">Attach Document</button>

              </div>
              <div className="form-element-btn-left">
                <button type="submit" class="btn blue" id="" onClick={handleBack}>Back</button>
              </div>
            </form>
          </div>
        </div>
        <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
      </div>
    </Layout>

  );
}

export default Upload
