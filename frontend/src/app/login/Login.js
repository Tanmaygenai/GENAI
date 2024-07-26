import React, { useContext, useEffect, useState } from 'react'
import { useForm } from 'react-hook-form'
import FormControl from '../../components/formcontrol/FormControl'
import { useHistory } from 'react-router-dom';
import { Form } from 'react-bootstrap'
import LoginService from '../services/LoginService';
import white_logo from '../../assets/img/Exavalu_Logo.png';
import about from '../../assets/img/about.png';
import industry from '../../assets/img/industry.png';
import client from '../../assets/img/client.png';
import LoginFailure from '../../components/modals/popupmodal/LoginFailureModal'
import SystemMaintenance from '../../components/modals/popupmodal/SystemMaintenanceModal'
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { CognitoUser, AuthenticationDetails } from "amazon-cognito-identity-js";
import { Link } from 'react-router-dom';
import API_Headers from '../../API_Headers';
import UserPool from '../UserPool';
import CryptoJS from "crypto-js";
import ConfigDocumentService from '../services/ConfigDocumentService';
import ApprovalConfigDocumentService from '../services/ApprovalConfigDocumentService';
const Login = () => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    const { register, formState: { errors }, handleSubmit } = useForm();
    const [headers, setHeaders] = useState([])
    const [verifyCodeForm, setVerifyCodeForm] = useState(false);
    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

    const loadGlia = (callback) => {
        const existingScript = document.getElementById('glia');
        if (!existingScript) {
            const script = document.createElement('script');
            script.src = 'https://api.glia.com/salemove_integration.js';
            script.id = 'glia';
            document.body.appendChild(script);
            script.onload = () => {
                if (callback) callback();
            };
        }
        if (existingScript && callback) callback();
    };

    var roleType = ""
    const history = useHistory();
    const [user, setUser] = useState("");
    const [password, setPassword] = useState("");
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    var errorMsg = ""

    loadGlia();

    useEffect(() => {
        if (headers.length !== 0) {
            const loggedInUser = sessionStorage.getItem("userName");
            if (loggedInUser) {
                setUser(loggedInUser);
            }
            const responseData = ConfigDocumentService.getDocument(headers);
            responseData.then((res) => {
            })
            const responseData1 = ApprovalConfigDocumentService.getDocument(headers);
            responseData1.then((res) => {
            })
        }
    }, [headers]);
    const onSubmit = async (data) => {
        const cognitoUser = new CognitoUser({
            Username: data.userName,
            Pool: UserPool,
        });

        const authDetails = new AuthenticationDetails({
            Username: data.userName,
            Password: data.password,
        });

        setUser(data.userName)
        setPassword(data.password)
        try {
            cognitoUser.authenticateUser(authDetails, {
                onSuccess: () => {                  
                    cognitoUser.getAttributeVerificationCode("email", {
                        onSuccess: () => {
                            setVerifyCodeForm(true)
                        },
                        onFailure: (err) => {
                            setErrorModal({ show: true, title: "Failure", body: err.message })
                        }
                    });
                },
                newPasswordRequired: () => {
                    history.push('/forceChangePassword')
                },
                onFailure: (err) => {
                    if (err.code == "UserNotConfirmedException") {
                        setErrorModal({ show: true, title: "Login Failure", body: "User account is not confirmed yet, please follow a link sent to your gmail to confirm you account!" })
                    }
                    else {
                        setErrorModal({ show: true, title: "Login Failure", body: err.message })
                    }
                }
            })
        }
        catch (error) {
            if (error == 'Error: Network Error') {
                setErrorModal({ show: true, title: "System Maintenance", body: "System is under maintenance.Please try again." })
            } else {
                setErrorModal({ show: true, title: "Login Failure", body: "Invalid user or password." })
            }
        }

    }

    const onSubmit1 = async (data) => {
        var role = "";
        var email = "";
        var organization = "";
        var producerCode = "";
        var status = "";
        const cognitoUser = new CognitoUser({
            Username: user,
            Pool: UserPool,
        });

        const authDetails = new AuthenticationDetails({
            Username: user,
            Password: password,
        });

        try {
            cognitoUser.authenticateUser(authDetails, {
                onSuccess: () => {
                    cognitoUser.verifyAttribute("email", data.verifyCode.trim(), {
                        onSuccess: () => {
                            cognitoUser.getUserAttributes(function (err, result) {
                                if (err) {
                                    alert(err.message || JSON.stringify(err));
                                    return;
                                }
                                else {
                                    sessionStorage.setItem("userName", user);
                                    for (var i = 0; i < result.length; i++) {
                                        
                                        if (result[i].getName() == "custom:role") {
                                            role = result[i].getValue();
                                            sessionStorage.setItem("roleType", role);
                                        }
                                        if (result[i].getName() === "email") {
                                            email = result[i].getValue();
                                            sessionStorage.setItem("email", result[i].getValue());
                                        }
                                        if (result[i].getName() === "custom:organization") {
                                            organization = result[i].getValue();
                                            sessionStorage.setItem("organization", result[i].getValue());
                                        }
                                        if (result[i].getName() === "custom:agencyCd") {
                                            producerCode = result[i].getValue();
                                            sessionStorage.setItem("producerCode", result[i].getValue());
                                        }
                                        if (result[i].getName() === "custom:status") {

                                            status = result[i].getValue();
                                            sessionStorage.setItem("status", result[i].getValue());
                                        }
                                    }
                                    
                                    if (role === "agent" || role === "admin") {
                                        const loginDet = {
                                            "userName": user,
                                            "email": email,
                                            "lastUsed": new Date()
                                        }
                                        const loginEncrptedData = CryptoJS.AES.encrypt(
                                            JSON.stringify(loginDet),
                                            secretPass
                                        ).toString();

                                        if(status==='Active'){
                                            const responseData = LoginService.loginUserInsert(loginEncrptedData, headers);
                                            responseData.then((res) => {
                                            })
                                            history.push("/openWork");
                                        }
                                        else{
                                            setErrorModal({ show: true, title: "Login Failure", body: "Producer status is Cancelled." })
                                            setVerifyCodeForm(false)
                                        }
                                        
                                    } else {
                                        setErrorModal({ show: true, title: "Login Failure", body: "Invalid user or password." })
                                    }
                                }
                            })
                        },
                        onFailure: (err) => {
                            setErrorModal({ show: true, title: "Login Failure", body: err.message })
                        }
                    })
                },
                onFailure: (err) => {
                    setErrorModal({ show: true, title: "Login Failure", body: err.message })
                }
            })
        }
        catch (error) {
            if (error == 'Error: Network Error') {
                setErrorModal({ show: true, title: "System Maintenance", body: "System is under maintenance.Please try again." })
            } else {
                setErrorModal({ show: true, title: "Login Failure", body: "Invalid user or password." })
            }
        }
    }

    return (
        <div>
            <main className="topa_login">
                <section className="header">
                    <div className="main-container">
                        <div className="inner-container">
                            <div className="logowrap">
                                <div className="logo"><a href="https://www.exavalu.com/" target="_blank" title=""><img src={white_logo} alt="Exavalu" title="Exavalu" /></a></div>
                                {/* <div className="howto"><a href="#" title="How It Works">How It Works</a></div> */}
                            </div>
                            <div className="headerpara">

                            </div>
                        </div>
                    </div>
                </section>
                <section className="signup">
                    <div className="main-container">
                        <div className="inner-container">
                            <div className="signupleft">
                                <figure></figure>
                                <figure></figure>
                                <figure></figure>
                            </div>
                            <div className="signupbox">
                                {
                                    !verifyCodeForm ?
                                        <>
                                            <h3>Hi, <span>Welcome!</span></h3>
                                            <p>Login to your Agent Portal.</p>
                                            <Form name="login" id="login" onSubmit={handleSubmit(onSubmit)}>
                                                <div className="loginField">
                                                    <FormControl fieldName="userName" register={register} errors={errors} type="text" showLabel={false} className="uname loginformfield" required={true} Placeholder='Username' />
                                                </div>
                                                <div className="loginField">
                                                    <FormControl fieldName="password" register={register} errors={errors} type="password" showLabel={false} className="pw loginformfield" required={true} Placeholder='Password' />
                                                </div>
                                                <div class="chkbox">
                                                    <div class="forg"><Link to="/forgotPassword"> Forgot Password </Link></div>
                                                    <div class="forg"><Link to="/changePassword"> Change Password </Link></div>
                                                </div>
                                                <div className="loginField">
                                                    <input type="submit" name="" id="submit_button" className="loginformbtn" />
                                                </div>
                                                <div className="newuser">

                                                </div>
                                            </Form>
                                        </>
                                        :
                                        <Form name="login" id="login" onSubmit={handleSubmit(onSubmit1)}>
                                            <p style={{ color: "red" }}>Please check your email for the verfication code!</p>
                                            <div className="loginField">
                                                <FormControl fieldName="verifyCode" register={register} errors={errors} type="text" showLabel={false} className="pw loginformfield" required={true} Placeholder='Verify Code' />
                                            </div>
                                            <div className="chkbox">
                                                {/* <input type="submit" name="" id="submit_button" className="loginformbtn" /><br></br>
                            <input type="submit" name="Cancel" id="submit_button" onClick={()=>setVerifyCodeForm(false)} className="loginformbtn" /> */}
                                                <div class="forg"><button className='btn blue' type="submit">Submit</button></div>
                                                <div class="forg"><button className='btn blue' onClick={() => setVerifyCodeForm(false)} type="submit">Cancel</button></div>
                                            </div>
                                        </Form>
                                }
                            </div>
                        </div>
                    </div>
                </section>
                <section className="footer">
                    <div className="main-container">
                        <div className="inner-container">
                            <p>©2023 Exavalu Company. All Rights Reserved.</p>
                        </div>
                    </div>
                </section>
            </main>
            <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
        </div>
    )
}

export default Login
