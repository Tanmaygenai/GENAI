import React, { useEffect, useState, useRef } from 'react'
import { useHistory, useLocation } from 'react-router-dom';
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import ConfigDocumentService from '../services/ConfigDocumentService';
import ConfigUploadModal from '../../components/modals/popupmodal/ConfigUploadModal';
import { FormControl } from 'react-bootstrap';
import API_Headers from '../../API_Headers';
import MultipleFile from '../../components/multiplefile/MultipleFile';
import TextractService from '../services/TextractService';
import UploadService from '../services/UploadService';
import dropdown_val from '../../dummydata/dropdown_data.csv';
import DropDown from '../../components/dropdown/DropDown';
import Papa from 'papaparse';
import $ from "jquery"
import useLoader from "../../context/loader"

const UploadAutoAcord = () => {
    const {setLoader} = useLoader()
    const { register, handleSubmit, formState: { errors }, setValue } = useForm();
    const [dragActive, setDragActive] = useState(false);
    const [selectedFile, setSelectedFile] = useState(null);
    const [lob, setLob] = useState('')
    const inputRef = useRef(null);
    const user = sessionStorage.getItem("userName");
    const [uploadStatus, setUploadStatus] = useState({ show: false });
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
        setLoader(true)
        const formData = new FormData();
        for (let key of Object.keys(multipleImages)) {
            if (key !== 'length') {
                formData.append("file", multipleImages[key]);
            }
        }
        // setIsLoaded(true);
        const responseData = await UploadService.uploadPdf(formData, headers);
        setLoader(false)
console.log(responseData.data)
        responseData.data.map((obj, index) => {
            history.push({
                pathname: "/autoQuote",
                state: responseData.data
            });
            
        })


    };
    

    return (
        <Layout TabName='Upload Acord Form' BreadCrum={['My Dashboard', 'Admin', 'Upload Acord Form']}>
            <div className="coverContainer">
                <div class="commonform_box">
                    <div class="inf">

                        <form class="allForm" onSubmit={handleSubmit(onSubmit)} >
                            <div style={{ fontSize: '20px' }}><span>Commercial Auto</span></div><br />
                            <div className="fullhead">Acord Pdf:</div><br />
                            <div className="grid2">
                                <div>
                                    <MultipleFile multipleImages={multipleImages} setMultipleImages={setMultipleImages} register={register} errors={errors} />
                                </div>
                                <div className="form-element-btn">
                                    <button type="submit" class="btn blue" id="">Upload</button>

                                </div>
                            </div>

                            {/* {dragActive && <div id="drag-file-element" onDragEnter={handleDrag} onDragLeave={handleDrag} onDragOver={handleDrag} onDrop={handleDrop}></div>} */}
                        </form>
                        {/* <ConfigUploadModal showModal={uploadStatus} setShowModal={setUploadStatus} /> */}
                    </div>
                </div>
            </div>
            {/* </div>
            </div> */}
        </Layout>
    )
}

export default UploadAutoAcord
