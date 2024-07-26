import React, {useState} from "react";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { useForm } from 'react-hook-form'
import FormControl from '../../../components/formcontrol/FormControl'
import { useHistory } from 'react-router-dom';
import white_logo from '../../../assets/img/logo_White.png';
import box from '../../../assets/img/th.jpg';
import { Form } from 'react-bootstrap'

function LoginFailure() {
 const { register, formState: { errors }, handleSubmit } = useForm();
  const [show, setShow] = useState(true);
  const history = useHistory();
  const handleClose = () => history.push("/");
const onSubmit = async (data) => {}
  return (
    <>
    <div>
    <main className="topa_login">
        <section className="header">
            <div className="main-container">
                <div className="inner-container">
                    <div className="logowrap">
                        <div className="logo"><a href="#" title=""><img src={white_logo} alt="Topa" title="Topa" /></a></div>
                        <div className="howto"><a href="#" title="How It Works">How It Works</a></div>
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
                    <figure><a href="#" title=""><img src={box} alt="Topa" title="Topa" /></a></figure>
                    <figure><a href="#" title=""><img src={box} alt="Topa" title="Topa" /></a></figure>
                    <figure><a href="#" title=""><img src={box} alt="Topa" title="Topa" /></a></figure>
                </div>
                <div className="signupbox">
                    <h3>Hi, <span>Welcome!</span></h3>
                    <p>Login to your Portal.</p>
                    <Form name="login" id="login" onSubmit={handleSubmit(onSubmit)}>
                        <div className="loginField">
                            <FormControl fieldName="userName" register={register} errors={errors} type="text" showLabel={false} className="uname loginformfield" required={true} Placeholder='Username'/>
                        </div>
                        <div className="loginField">
                            <FormControl fieldName="password" register={register} errors={errors} type="password" showLabel={false} className="pw loginformfield" required={true} Placeholder='Password'/>
                        </div>

                        <div className="loginField">
                            <input type="submit" name="" id="submit_button" className="loginformbtn" />
                        </div>
                        <div className="newuser">

                        </div>
                    </Form>
                </div>
            </div>
        </div>
    </section>
           <Modal show={show} onHide={handleClose} backdrop="static" keyboard={false}>
            <Modal.Header closeButton>
              <Modal.Title>Login Failure</Modal.Title>
            </Modal.Header>
            <Modal.Body> Invalid user or password </Modal.Body>
            <Modal.Footer> <Button variant="primary" onClick={handleClose}> Close </Button> </Modal.Footer>
          </Modal>
        <section className="footer">
                <div className="main-container">
                    <div className="inner-container">
                        <p>©2021 Exavalu Insurance Company. All Rights Reserved.</p>
                    </div>
                </div>
            </section>
        </main>
    </div>
    </>
  );
}
export default LoginFailure;