import React, { useEffect, useState } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import FormControl from "../../components/formcontrol/FormControl"
import DropDown from '../../components/dropdown/DropDown';
import { drop_data } from '../../dummydata/data';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { useHistory } from 'react-router-dom';
import CryptoJS from "crypto-js";
import Papa from 'papaparse';
import dropdown_val from '../../dummydata/admin.csv';
import API_Headers from '../../API_Headers.js';
import CognitoUserService from '../services/CognitoUserService';

const UserRegistration = () => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    const history = useHistory();
    const handleBack = () => {
        history.push({
            pathname: "/userManagement"
        });
    }
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });

    const [response, setResponse] = useState({})
    const [headers, setHeaders] = useState([])
    const [userRole, setUserRole] = useState("");
    const [groupCd, setGroupCd] = useState("");

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

    const onSubmit = (data) => {
        
        setResponse(data)
        try {
            const userEncrptedData = CryptoJS.AES.encrypt(
                JSON.stringify(data),
                secretPass
              ).toString();
            const responseData = CognitoUserService.cognitoUserSignup(userEncrptedData, headers);
            responseData.then((res)=>{
                if(res.data === "Success"){
                    setErrorModal({ show: true, title: "Success", body: "User created successfully!" })
                }else{
                    setErrorModal({ show: true, title: "Failure", body: res.data })
                }
            })
        }
        catch (error) {
            setErrorModal({show: true, title: "Some Error!", body: error })
        }
    };

    function formatPhoneNo(e) {
        var phone = e.target.value;
        var phoneClone = phone
        phoneClone = phoneClone.split("")
        phoneClone.length == 4 && phoneClone[3] !== "-" && phoneClone.splice(3, 0, "-")
        phoneClone.length == 8 && phoneClone[7] !== "-" && phoneClone.splice(7, 0, "-")
        phoneClone = phoneClone.join("")
        if (phoneClone.length > 11) {
            phoneClone = phoneClone.slice(0, 12)
        }
        e.target.value = phoneClone;
    }

    return (
        <Layout TabName='User Registration' BreadCrum={['My Dashboard', 'Admin', 'User Management', 'User Registration']}>
            <div class="commonform_box">
                <div class="inf">
                    <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                        <div class="fullhead">New Agent / Admin Registration</div>
                        <br />
                        <div className="grid2">
                            <div class="form-element">
                                <FormControl fieldName="userName" label='User Name' id='newusername' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                            <div class="form-element" style={{display:"none"}}>
                                <FormControl fieldName="status" defaultValue={"Active"} label='Status' id='newusername' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                            {/* <div class="form-element">
                                <FormControl fieldName="password" label='Password' id='newusername' register={register} errors={errors} type="password" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div> */}
                            <div class="form-element">
                                <FormControl fieldName="email" label='Email' id='newusername' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                            <div className="form-element">
                                    <FormControl fieldName="phone" label='Enter your phone (e.g. +141555575)' id='phone_number' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} onChange={(e) => { formatPhoneNo(e) }} />
                            </div>
                            <div className="form-element">
                                    <FormControl fieldName="organization" label='Organization name' id='organization' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                            </div>
                            <div class="form-element form-select">
                                <DropDown fieldName='role' label='Role Type' id='userRole' register={register} errors={errors} lableClass='form-label' activeBold={true} required={true}>
                                    {/* {drop_data.RoleType.map((value, ind) => (
                                        <option value={value}>{value}</option>
                                    ))} */}
                                     {dropdownVal.map((value, ind) => {
                                            return value.RoleType!== "" && value.RoleType !==null && value.RoleType !== undefined  ? (
                                                <option value={value.RoleType === "-select-" ? "" : value.RoleType}>{value.RoleType}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                </DropDown>
                            </div>
                            <div class="form-element form-select" onChange={(e)=>setGroupCd(e.target.value)}>
                                        <DropDown fieldName='groupCd' label='Group Code' id='groupCd' register={register} errors={errors} lableClass='form-label' activeBold={true} required={true}>
                                            {/* {drop_data.groupCd.map((value, ind) => (
                                                <option value={value}>{value}</option>
                                            ))} */}
                                            {dropdownVal.map((value, ind) => {
                                            return value.groupCd!== "" && value.groupCd !==null && value.groupCd !== undefined ? (
                                                <option value={value.groupCd === "-select-" ? "" : value.groupCd}>{value.groupCd}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                        </DropDown>
                            </div>
                                {
                                    groupCd !== ""?
                                    <div class="form-element form-select">
                                    <DropDown fieldName='agencyCd' label='Agency Code' id='agencyCd' register={register} errors={errors} lableClass='form-label' activeBold={true} required={true}>
                                        {/* {drop_data.agencyCd.map((value, ind) => {
                                           return value.includes(groupCd)?
                                            (
                                                <option value={value}>{value}</option>
                                            )
                                            :
                                            ''
                                        })} */}
                                        {dropdownVal.map((value, ind) => {
                                            return value.groupCd!== "" && groupCd==="0002" && value.agencyCd_1 !== "" && value.agencyCd_1 !== null && value.agencyCd_1 !== undefined? (
                                                <option value={value.agencyCd_1 === "-select-" ? "" : value.agencyCd_1}>{value.agencyCd_1}</option>
                                            ) :
                                            value.groupCd!== "" && groupCd==="0004" && value.agencyCd_2 !== "" && value.agencyCd_2 !== null && value.agencyCd_2 !== undefined? (
                                                <option value={value.agencyCd_2 === "-select-" ? "" : value.agencyCd_2}>{value.agencyCd_2}</option>
                                            ) :
                                            value.groupCd!== "" && groupCd==="0006" && value.agencyCd_3 !== "" && value.agencyCd_3 !== null && value.agencyCd_3 !== undefined? (
                                                <option value={value.agencyCd_3 === "-select-" ? "" : value.agencyCd_3}>{value.agencyCd_3}</option>
                                            ) :
                                                <></>
                                        }
                                        )}
                                    </DropDown>
                                    </div>
                                    :
                                    ''
                                }
                        </div>
                        <div class="form-element-btn">
                            <button type="submit" className='btn blue' id="">Submit</button>
                        </div>
                        <div className="form-element-btn-left">
                            <button type="submit" class="btn blue" id="" onClick={handleBack}>Back</button>
                        </div>
                        {/* <p style={{ color: 'red' }}><b>Password Requirements:</b><br></br>
                                                * Minimum length 8<br></br>
                                                * Require numbers<br></br>
                                                * Require special characters<br></br>
                                                * Require uppercase characters<br></br>
                                                * Require lowercase characters<br></br>
                                                </p> */}
                    </form>
                </div>
                <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
            </div>
        </Layout>
    )
}
export default UserRegistration
