import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form'
import FormControl from '../../components/formcontrol/FormControl'
import { useHistory } from 'react-router-dom';
import { Form } from 'react-bootstrap'
import Login from '../login/Login'
import white_logo from '../../assets/img/Exavalu_Logo.png';
import box from '../../assets/img/th.jpg';

import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { CognitoUser, AuthenticationDetails } from "amazon-cognito-identity-js";
import UserPool from "../UserPool";

const ChangePassword = () => {
    var status = ""
    const history = useHistory();
    var errorMsg = ""
    const handleBack = () => {
        history.push("/");
    }

    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [oldPassword, setOldPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmNewPassword, setConfirmNewPassword] = useState("");
    
   // const [setErrorModal] = useState({show:false, title:"", body:""});
    const [modalDate, setModalDate] = useState({show:false, title:"", body:""});
    const handleChangePassword = (event) => {
        event.preventDefault();
        if (confirmNewPassword !== "" && newPassword !== "" && password !== "") {
            if (confirmNewPassword === newPassword) {
                const user = new CognitoUser({
                    Username: userName,
                    Pool: UserPool,
                });
                const authDetails = new AuthenticationDetails({
                    Username: userName,
                    Password: password,
                });

                user.authenticateUser(authDetails, {
                    onSuccess: (data2) => {
                        user.changePassword(password, newPassword, (err, result) => {
                            if (err) {
                                setModalDate({ show: true, title: "Error Message", body: err.message })
                            } else if(result) {
                                setModalDate({ show: true, title: "Success Message", body: "Password successully changed." })
                            }
                        })
                    },
                    onFailure: (err) => {
                        if (err.code) {
                            setModalDate({ show: true, title: "Error Message", body: err.message })
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
            <section className="header">
                <div className="main-container">
                    <div className="inner-container">
                        <div className="logowrap">
                            <div className="logo"><a href="#" title=""><img src={white_logo} alt="Topa" title="Topa" /></a></div>
                        </div>
                    </div>
                </div>
            </section>
            <section className="signup">
                <div className="main-container">
                    <div className="inner-container">
                        <div className="passwordbox">
                        {/* <h3><span>First Time User Login Detected...!</span></h3> */}
                            	<br />
                                <h3><span>Change Password Required</span></h3>
                                <form>
                                    <br />
                                    <div className="loginField">
                                		<input value={userName} onChange={(event) => setUserName(event.target.value)} type="text" showLabel={false} className="uname loginformfield" required={true} Placeholder='Username'></input>
                                	</div>
                                    <div className="loginField">
                                    <input value={password} onChange={(event) => setPassword(event.target.value)} type="password" showLabel={false} className="pw loginformfield" required={true} Placeholder='Password'></input>
                                    </div>
                                    <div className="loginField">
                                    	<input value={newPassword} onChange={(event) => setNewPassword(event.target.value)} type="password" showLabel={false} className="pw loginformfield" required={true} Placeholder='New Password'></input>
                                    </div>
                                    <div className="loginField">
                                        <input value={confirmNewPassword} onChange={(event) => setConfirmNewPassword(event.target.value)} type="password" showLabel={false} className="pw loginformfield" required={true} Placeholder='Confirm New Password'></input>
                                    </div>
                                    <br />

                                    <div>
                                        <button class="btn blue" onClick={handleBack} type='button'>Cancel</button>
                                        &ensp; &ensp; &ensp; &ensp;&ensp;&ensp;&ensp;
                                        <button class="btn blue" onClick={handleChangePassword}>Change Password</button>
                                    </div>
                                </form>
                                <p style={{ color: 'red' }}><b>Password Requirements:</b><br></br>
                                                * Minimum length 8<br></br>
                                                * Require numbers<br></br>
                                                * Require special characters<br></br>
                                                * Require uppercase characters<br></br>
                                                * Require lowercase characters<br></br>
                                                </p>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setModalDate}/>
    </div>



    )
}

export default ChangePassword
