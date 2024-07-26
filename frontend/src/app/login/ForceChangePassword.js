import React, { useState } from 'react'
import { Form } from 'react-bootstrap'
import { CognitoUser, AuthenticationDetails } from "amazon-cognito-identity-js";
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { useForm } from 'react-hook-form';
import FormControl from '../../components/formcontrol/FormControl';
import UserPool from '../UserPool';
import white_logo from '../../assets/img/Exavalu_Logo.png';
import { useHistory } from 'react-router-dom';

const ForceChangePassword = () => {
    const { register, formState: { errors }, handleSubmit } = useForm();
    const [modalData, setErrorModal] = useState({show:false, title:"", body:""});
    const history = useHistory();
    const onSubmit = async (data) => {
        if(data.newPassword !== "" && data.confPassword !=="" && data.temPassword !== "" ){
            if(data.newPassword === data.confPassword){
                const cognitoUser = new CognitoUser({
                    Username: data.userName,
                    Pool: UserPool,
                });
        
                const authDetails = new AuthenticationDetails({
                    Username: data.userName,
                    Password: data.tempPassword,
                });
                try {
                    cognitoUser.authenticateUser(authDetails, {
                        newPasswordRequired: () =>{
                            cognitoUser.completeNewPasswordChallenge(data.newPassword, {}, {
                                onSuccess: () => {
                                  //console.log("Password changed:", result);
                                  setErrorModal({ show: true, title: "Success", body: "Password Changed" })
                                },
                                onFailure: (error) => {
                                  setErrorModal({ show: true, title: "Error", body: error.message })
                                }
                              });
                        },
                        onFailure:(err)=>{
                            setErrorModal({ show: true, title: "Error", body: err.message })
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
            }else{
                setErrorModal({ show: true, title: "Password Mismatch", body: "There is a mismatch in entered password." })
            }
        }
        else{
            setErrorModal({ show: true, title: "Error", body: "One or more field is empty!" })
        }

    }

    const handleBack = () => {
        history.push("/");
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
                                <h3><span>Force Change Password Required</span></h3>
                                <Form name="login" id="login" onSubmit={handleSubmit(onSubmit)}>
                                <p style={{color:"red"}}>Please enter a new password here!</p>
                            <div className="loginField">
                            <FormControl fieldName="userName" register={register} errors={errors} type="text" showLabel={false} className="pw loginformfield" required={true} Placeholder='User Name' /><br />
                            <FormControl fieldName="tempPassword" register={register} errors={errors} type="password" showLabel={false} className="pw loginformfield" required={true} Placeholder='temporary Password' /><br />
                            <FormControl fieldName="newPassword" register={register} errors={errors} type="password" showLabel={false} className="pw loginformfield" required={true} Placeholder='New Password' /><br />
                            <FormControl fieldName="confPassword" register={register} errors={errors} type="password" showLabel={false} className="pw loginformfield" required={true} Placeholder='Confirm New Password' />
                            </div>  
                            <div className="chkbox">
                                {/* <input type="submit" name="" id="submit_button" className="loginformbtn" /><br></br>
                                <input type="submit" name="Cancel" id="submit_button" onClick={()=>setVerifyCodeForm(false)} className="loginformbtn" /> */}
                                <button className='btn blue' type="submit">Submit</button>
                                &ensp; &ensp; &ensp; &ensp;&ensp;&ensp;&ensp;
                                <button className='btn blue' onClick={handleBack} type="submit">Cancel</button>
                            </div>
                        </Form>
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
        <ErrorMessageModal showModal={modalData.show} modalTitle={modalData.title} modalBody={modalData.body} setShowModal={setErrorModal}/>
        </div>
    )
}

export default ForceChangePassword;
