import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form'
import FormControl from '../../components/formcontrol/FormControl'
import { useHistory } from 'react-router-dom';
import { Form } from 'react-bootstrap'
import Login from '../login/Login'
import white_logo from '../../assets/img/Exavalu_Logo.png';
import box from '../../assets/img/th.jpg';
import CryptoJS from "crypto-js";
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import LoginService from '../services/LoginService';
import { CognitoUser, AuthenticationDetails, AdminGetUserRequest } from "amazon-cognito-identity-js";
import UserPool from "../UserPool";

const ForgotPassword = () => {
    var status = ""
    const history = useHistory();
    var errorMsg = ""
    const handleBack = () => {
        history.push("/");
    }

    const headers = {
        'Authorization': 'Basic ' + btoa(`${process.env.REACT_APP_Security_ID}:${process.env.REACT_APP_Security_Password}`),
    }

    const secretPass = process.env.REACT_APP_Secret_Key;
    const [userName, setUserName] = useState("");
    const [passwordForm, setPasswordForm] = useState(false);
    const [verificationCode, setVerificationCode] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [oldPassword, setOldPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmNewPassword, setConfirmNewPassword] = useState("");

    // const [setErrorModal] = useState({show:false, title:"", body:""});
    const [modalDate, setModalDate] = useState({ show: false, title: "", body: "" });
    const handleVerifyCode = async (event) => {
        event.preventDefault();
        if (userName !== "") {
            const user = new CognitoUser({
                Username: userName,
                Pool: UserPool,
            });
            user.forgotPassword({
                onSuccess: data => {
                },
                onFailure: err => {
                    setModalDate({ show: true, title: "Error", body: err.message })
                    // if (data.code == "LimitExceededException") {
                    //     setModalDate({ show: true, title: "Error", body: "Try limit exceeded, Please try again after some time" })
                    // }
                    // else if (data.code == 'UserNotFoundException') {
                    //     setModalDate({ show: true, title: "Error", body: "User doesn't exist!" })
                    // } else if (data.code == 'ResourceNotFoundException') {
                    //     setModalDate({ show: true, title: "Error", body: "User not found!" })
                    // }
                },
                inputVerificationCode: (data) => {
                    setPasswordForm(true);
                }
            })
        } else {
            setModalDate({ show: true, title: "Error", body: "Please enter userName!" })
        }

    }

    const handleChangePassword = async (event) => {
        event.preventDefault();
        if (verificationCode !== "" && confirmNewPassword !== "" && newPassword !== "") {
            if (confirmNewPassword.match(newPassword)) {
                const user = new CognitoUser({
                    Username: userName,
                    Pool: UserPool,
                });
                user.confirmPassword(verificationCode, newPassword, {
                    onSuccess: data => {
                        setModalDate({ show: true, title: "Success Message", body: "Password successully changed." })
                    },
                    onFailure: data => {
                        if (data.code == "LimitExceededException") {
                            setModalDate({ show: true, title: "Error", body: "Try limit exceeded, Please try again after some time" })
                            setPasswordForm(false)
                            setVerificationCode("")
                            setPassword("")
                            setConfirmNewPassword("")
                        }
                        else if (data.code == "CodeMismatchException") {
                            setModalDate({ show: true, title: "Error", body: data.message })
                        } else if (data.code == "InvalidPasswordException") {
                            setModalDate({ show: true, title: "Error",  body: data.message })
                        }
                        else {
                            setModalDate({ show: true, title: "Error", body: data.message })
                        }
                    }
                })
            } else {
                setModalDate({ show: true, title: "Password Mismatch", body: "There is a mismatch in entered password." })
            }
        } else {
            setModalDate({ show: true, title: "Error", body: "Can't submit the blank data!" })
        }


    }
    return (
    <div>
            <main className="topa_login">
                {/* <section className="header">
                    <div className="main-container">
                        <div className="inner-container">
                            <div className="logowrap">
                                <div className="logo"><a href="#" title=""><img src={white_logo} alt="Topa" title="Topa" /></a></div>
                            </div>
                        </div>
                    </div>
                </section> */}
                <section className="signup">
                    <div className="main-container">
                        <div className="inner-container">
                            <div className="passwordbox">
                                {/* <h3><span>First Time User Login Detected...!</span></h3> */}
                                <br />
                                <h3><span>Forgot Password Required</span></h3>
                                {
                                    !passwordForm ?
                                        <form>
                                            <br />
                                            <div className="loginField">
                                                <input value={userName} onChange={(event) => setUserName(event.target.value)} type="text" showLabel={false} className="uname loginformfield" required={true} Placeholder='Username'></input>
                                            </div>
                                            <div className='forg_button'>
                                                <button class="btn blue" onClick={handleBack} type='button'>Cancel</button>
                                                &ensp; &ensp; &ensp; &ensp;&ensp;&ensp;&ensp;
                                                <button class="btn blue" onClick={handleVerifyCode}>Send Verfication Code</button>
                                            </div>
                                        </form>
                                        :
                                        <form>
                                            <br />
                                            <div className="loginField">
                                                <input value={verificationCode} onChange={(event) => setVerificationCode(event.target.value)} type="number" showLabel={false} className="code loginformfield" required={true} Placeholder='Verfication Code'></input>
                                            </div>
                                            <div className="loginField">
                                                <input value={newPassword} onChange={(event) => setNewPassword(event.target.value)} type="password" showLabel={false} className="pw loginformfield" required={true} Placeholder='New Password'></input>
                                            </div>
                                            <div className="loginField">
                                                <input value={confirmNewPassword} onChange={(event) => setConfirmNewPassword(event.target.value)} type="password" showLabel={false} className="pw loginformfield" required={true} Placeholder='Confirm New Password'></input>
                                            </div>
                                            <br />
                                            <p style={{ color: 'red' }}><b>Password Requirements:</b><br></br>
                                                * Minimum length 8<br></br>
                                                * Require numbers<br></br>
                                                * Require special characters<br></br>
                                                * Require uppercase characters<br></br>
                                                * Require lowercase characters<br></br>
                                                </p>
                                            <div className='forg_button'>
                                                <button class="btn blue" onClick={handleBack} type='button'>Cancel</button>
                                                &ensp; &ensp; &ensp; &ensp;&ensp;&ensp;&ensp;
                                                <button class="btn blue" onClick={handleChangePassword}>Change Password</button>
                                            </div>
                                        </form>
                                }
                            </div>
                        </div>
                    </div>
                </section>
                <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setModalDate} />
            </main>
     </div>
    )
}

export default ForgotPassword
