import React, { useEffect, useState, useRef } from 'react'
import { useHistory, useLocation } from 'react-router-dom';
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import { FormControl } from 'react-bootstrap';
import API_Headers from '../../API_Headers';
import UploadService from '../services/UploadService';
import dropdown_val from '../../dummydata/dropdown_data.csv';
import DropDown from '../../components/dropdown/DropDown';
import Papa from 'papaparse';
import $ from "jquery"
import useLoader from "../../context/loader"

const UploadPropertyAcord = () => {
    const { setLoader } = useLoader();
    const { register, handleSubmit, formState: { errors }, setValue } = useForm();
    const [dragActive, setDragActive] = useState(false);
    const [selectedFile, setSelectedFile] = useState(null);
    const inputRef = useRef(null);
    const [headers, setHeaders] = useState([])
    const history = useHistory();
    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

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
    const handleDrag = function (e) {
        e.preventDefault();
        e.stopPropagation();
        if (e.type === "dragenter" || e.type === "dragover") {
            setDragActive(true);
        } else if (e.type === "dragleave") {
            setDragActive(false);
        }
    };

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

    const [multipleImages, setMultipleImages] = useState([]);
    const onSubmit = async () => {
        const formData = new FormData();
        formData.append("file", selectedFile);
        setLoader(true);
        try {
            const responseData = await UploadService.uploadPdf(formData, headers);
            setLoader(false);
            history.push({
                pathname: "/propertyQuote",
                state: responseData.data
            });
        } catch (error) {
            if (error == "Error: Network Error") {
                history.push("/SystemMaintenance");
            } else {
                alert(error);
            }
        }

    };


    return (
        <Layout TabName='Upload Acord Form' BreadCrum={['My Dashboard', 'Admin', 'Upload Acord Form']}>
            <div className="coverContainer">
                <div class="commonform_box">
                    <div class="inf">

                        <form class="allForm" onSubmit={handleSubmit(onSubmit)} onDragEnter={handleDrag}>
                            <div className='grid3'>

                            </div>
                            <div className="fullhead">Acord Pdf:</div><br />

                            <div className="grid2">
                                <div className="fullhead">

                                    <span> Drag & Drop your pdf here*</span>
                                </div>
                                <FormControl className='file-upload' labelClass="form-label" ref={inputRef} type="file" onChange={handleChange} />
                                <div className="form-element-btn">
                                    <button type="submit" class="btn blue" id="">Upload Document</button>

                                </div>
                            </div>

                            {dragActive && <div id="drag-file-element" onDragEnter={handleDrag} onDragLeave={handleDrag} onDragOver={handleDrag} onDrop={handleDrop}></div>}
                        </form>

                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default UploadPropertyAcord
