import React, { useEffect, useState, useRef } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import { FormControl } from 'react-bootstrap';
import API_Headers from '../../API_Headers';
import { useHistory } from 'react-router-dom';
import useLoader from "../../context/loader"
import UploadLogoService from '../services/UploadLogo';
import SuccessMessageModal from '../../components/modals/popupmodal/SuccessMessageModal';
import GetLogoService from '../services/GetLogo';
import useMetaData from "../../context/metaData"

const UploadLogo = () => {
    const { setLoader } = useLoader();
    const {setLogoBase64} = useMetaData();
    const { register, handleSubmit, formState: { errors }, reset } = useForm();
    const [dragActive, setDragActive] = useState(false);
    const [modalData, setModalData] = useState({ show: false, title: "", body: "" });
    const [selectedFile, setSelectedFile] = useState({});
    const [photoPreview, setPhotoPreview] = useState([]);
    const inputRef = useRef(null);
    const [headers, setHeaders] = useState([])
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

    const handleChange = function (e) {
        e.preventDefault();
        if (e.target.files && e.target.files[0]) {
            const file = e.target.files[0]
            setSelectedFile(file);
            const previewFile = { ...file }
            setPhotoPreview([
                Object.assign(previewFile, {
                    preview: URL.createObjectURL(file)
                })
            ]);
        }
    };

    const thumbs = photoPreview.map((photo) => (
        <div key={photo.name}>
            <div>
                <img alt={photo.name} src={photo.preview} />
            </div>
        </div>
    ));

    const onSubmit = async () => {
        console.log(selectedFile)
        setLoader(true)
        const res = await UploadLogoService.uploadLogo(selectedFile, headers)
        setLoader(false)
        console.log(res)
        if (res.status == 200) {
            setModalData({
                title: 'Upload Logo',
                show: true,
                body: 'You have uplaoded the logo successfully.'
            })
            getLogo();
            removePhotos()
        }
    };

    const getLogo = async () => {
        const res = await GetLogoService.getLogo(headers)
        setLogoBase64(`data:image/jpeg;base64,${res.data.filecontent}`)
    }

    const removePhotos = () => {
        reset();
        inputRef.current.value = '';
        setSelectedFile({});
        setPhotoPreview([]);
    }

    return (
        <Layout TabName='Upload Logo' BreadCrum={['My Dashboard', 'Admin', 'Upload Logo']}>
            <div className="coverContainer">
                <div class="commonform_box">
                    <div class="inf">
                        <form class="allForm" onSubmit={handleSubmit(onSubmit)} onDragEnter={handleDrag}>
                            <div className="fullhead">Upload Logo</div><br />

                            <div className="grid2">
                                <div className="fullhead">
                                    <span style={{ color: "red" }}>File should be in image format</span><br />
                                    <span> Drag & Drop a .png/.jpg/.jpeg file here*</span>
                                </div>
                                <div className="form-element">
                                    <div className="d-flex gap-3 " >
                                        <FormControl name='logo' className='file-upload' labelClass="form-label" style={{ lineHeight: "40px" }} register={register} accept="image/png, image/jpeg, image/jpg" errors={errors} ref={inputRef} type="file" onChange={handleChange} />
                                        {!!Object.keys(selectedFile).length && !!photoPreview.length && <button onClick={removePhotos} className='btn blue'>Clear</button>}
                                    </div>
                                    <div className="mt-1" style={{ width: "200px" }}>
                                        {thumbs}
                                    </div>
                                </div>
                                <div class="form-element-btn">
                                    <button type="submit" className='btn blue' id="">Upload Logo</button>
                                </div>
                                <div className="form-element-btn-left">
                                    <button class="btn blue" id="" onClick={handleBack}>Back</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <SuccessMessageModal showModal={modalData.show} modalTitle={modalData.title} modalBody={modalData.body} setShowModal={setModalData} />
        </Layout>
    )
}

export default UploadLogo
