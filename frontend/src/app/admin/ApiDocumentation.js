import React, { useEffect, useState } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import FormControl from "../../components/formcontrol/FormControl"
import DropDown from '../../components/dropdown/DropDown';
// import { drop_data } from '../../dummydata/data';
import LoginService from '../services/LoginService';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { useHistory } from 'react-router-dom';
import { Link } from 'react-router-dom';
import API_Headers from '../../API_Headers';
import Papa from 'papaparse';
import dropdown_val from '../../dummydata/admin.csv';
// import { useNavigate } from 'react-router-dom';
const ApiDocumentation = () => {
    const history = useHistory();
    const handleBack = () => {
        history.push({
            pathname: "/admin"
        });
    }
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [response, setResponse] = useState({})
    const [headers, setHeaders] = useState([])
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

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])
    // let navigate = useNavigate();
    const onSubmit = async (data) => {
        setResponse(data.version)
        try {
            if (data.version === 'Exavalu_v1') {
                window.open(process.env.REACT_APP_Exavalu_v1_URL, '_blank')
            }
        }
        catch (error) {
            setErrorModal({shoe:true, title: "Some Error!", body: error})
        }
    };
    return (
        <Layout TabName='API Documentation' BreadCrum={['My Dashboard', 'Admin', 'REST API Documentation']}>
            <div class="commonform_box">
                <div class="inf">
                    <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                        {/* <div class="fullhead">New Agent / Admin Registration</div> */}
                        <br />
                        <div className="grid2">
                            <div class="form-element form-select">
                                <DropDown fieldName='version' label='Version' register={register} errors={errors} lableClass='form-label' activeBold={true} required={true}>
                                    {/* {drop_data.ApiVersion.map((value, ind) => (
                                        <option value={value}>{value}</option>
                                    ))} */}
                                    {dropdownVal.map((value, ind) => {
                                            return value.ApiVersion!== "" && value.ApiVersion!== null && value.ApiVersion!== undefined ? (
                                                <option value={value.ApiVersion === "-select-" ? "" : value.ApiVersion}>{value.ApiVersion}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                </DropDown>
                            </div>
                        </div>
                        <div class="form-element-btn">
                            <button type="submit" className='btn blue' id="">View Doc</button>
                        </div>
                        <div className="form-element-btn-left">
                            <button type="submit" class="btn blue" id="" onClick={handleBack}>Back</button>
                        </div>
                    </form>
                </div>
                <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
            </div>
        </Layout>
    );
};
export default ApiDocumentation;