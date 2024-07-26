import React, { useEffect, useState, useRef } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import ConfigDocumentService from '../services/ConfigDocumentService';
import ConfigUploadModal from '../../components/modals/popupmodal/ConfigUploadModal';
import { FormControl } from 'react-bootstrap';
import API_Headers from '../../API_Headers';
import { useHistory } from 'react-router-dom';
import { saveAs } from 'file-saver';
import csvIcon from '../../assets/img/icons/csv.png'
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import useLoader from "../../context/loader"

const ConfigDocument = () => {
    const { setLoader } = useLoader();
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [dragActive, setDragActive] = useState(false);
    const [selectedFile, setSelectedFile] = useState(null);
    const inputRef = useRef(null);
    const user = sessionStorage.getItem("userName");
    const [uploadStatus, setUploadStatus] = useState({ show: false });
    const [headers, setHeaders] = useState([])
    const [modalData, setErrorModal] = useState({ show: false, title: "", body: "" });
    const history = useHistory();
    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

    const handleDrag = function (e) {
        e.preventDefault();
        e.stopPropagation();
        if (e.type === "dragenter" || e.type === "dragover") {
            setDragActive(true);
        } else if (e.type === "dragleave") {
            setDragActive(false);
        }
    };

    const handleBack = () => {
        history.push({
            pathname: "/admin"
        });
    }
    const handleDrop = function (e) {
        e.preventDefault();
        e.stopPropagation();
        setDragActive(false);
        if (e.dataTransfer.files && e.dataTransfer.files[0]) {
            // handleFiles(e.dataTransfer.files);
        }
    };

    const handleChange = function (e) {
        e.preventDefault();
        if (e.target.files && e.target.files[0]) {
            setSelectedFile(e.target.files[0]);
        }
    };

    const onSubmit = async () => {

        setLoader(true)
        if (selectedFile !== null && selectedFile !== "" && selectedFile !== undefined) {
            const formData = new FormData();
            formData.append("file", selectedFile);
            if (selectedFile.name === "dropdown_data.xlsx") {
                try {
                    const responseData = await ConfigDocumentService.uploadDocument(formData, headers);
                    if (responseData.data === "Object uploaded successfully.") {
                        try {
                            const responseData = ConfigDocumentService.getDocument(headers);
                            responseData.then((res) => {
                                console.log(res.data)
                                if (res.data === "Files created and saved successfully") {
                                    setLoader(false)
                                    setUploadStatus({ show: true })
                                } else {
                                    setLoader(false)
                                    setErrorModal({ show: true, title: "Config Doc upload & save Failure", body: res.data })
                                }
                            })
                        } catch (error) {
                            setLoader(false)
                            setErrorModal({ show: true, title: "Config Doc Upload Failure", body: "Couldn't store the config file in the given directory!" })
                        }
                    } else {
                        setLoader(false)
                        setErrorModal({ show: true, title: "Config Doc Upload Failure", body: responseData.data })
                    }
                } catch (error) {
                    setLoader(false)
                    setErrorModal({ show: true, title: "Config Doc Upload Failure", body: error })
                }
            }
            else {
                setLoader(false)
                setErrorModal({ show: true, title: "Config Doc Upload Failure", body: "Please select a xlxs file named 'dropdown_data.xlsx' !" })
            }
        } else {
            setLoader(false)
            setErrorModal({ show: true, title: "Config Doc Upload Failure", body: "Please select a xlsx file!" })
        }

    };

    const handleDownload = async () => {

        setLoader(true)
        try {
            const response = await ConfigDocumentService.downloadDocument(headers);
            if (response.data) {
                const blob = new Blob([response.data], { type: "application/octet-stream" });
                const url = URL.createObjectURL(blob);
                const link = document.createElement("a");
                link.href = url;
                link.download = "dropdown_data.xlsx";
                link.click();
                setLoader(false)
            } else {
                setLoader(false)
                setErrorModal({ show: true, title: "Config Doc Download Failure", body: "Error: Response data is undefined" })
            }
        } catch (error) {
            setLoader(false)
            setErrorModal({ show: true, title: "Config Doc Download Failure", body: error })
        }
    };

    return (
        <Layout TabName='Config Dropdown Data Management' BreadCrum={['My Dashboard', 'Admin', 'Config Dropdown Data Management']}>
            <div className="coverContainer">
                <div class="commonform_box">
                    <div class="inf">
                        <form class="allForm" onSubmit={handleSubmit(onSubmit)} onDragEnter={handleDrag}>
                            <div className="fullhead">Config Dropdown Document:</div><br />

                            <div className="grid2">
                                <div className="fullhead">
                                    <span style={{ color: "red" }}>File name should be "dropdown_data.xlsx"</span><br />
                                    <span> Drag & Drop a .xlsx file here*</span>
                                </div>
                                <div className="form-element">
                                    <FormControl className='file-upload' labelClass="form-label" register={register} errors={errors} ref={inputRef} type="file" onChange={handleChange} />
                                </div>
                                <div className="form-element-left">
                                    <div><img src={csvIcon} style={{ width: '5%' }} /><a href='javascript:void(0);' style={{ color: 'red' }} onClick={handleDownload}>Download Sample Config File</a></div>
                                </div>
                                <div class="form-element-btn">
                                    <button type="submit" className='btn blue' id="">Upload Doc</button>
                                </div>
                                <div className="form-element-btn-left">
                                    <button type="submit" class="btn blue" id="" onClick={handleBack}>Back</button>
                                </div>
                            </div>
                            {dragActive && <div id="drag-file-element" onDragEnter={handleDrag} onDragLeave={handleDrag} onDragOver={handleDrag} onDrop={handleDrop}></div>}
                        </form>
                        {/* </>
                        } */}
                        <ConfigUploadModal showModal={uploadStatus} setShowModal={setUploadStatus} />
                        <ErrorMessageModal showModal={modalData.show} modalTitle={modalData.title} modalBody={modalData.body} setShowModal={setErrorModal} />
                    </div>
                </div>
            </div>
            {/* </div>
            </div> */}
        </Layout>
    )
}

export default ConfigDocument
