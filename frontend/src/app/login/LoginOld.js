import React from 'react'
import { useForm } from 'react-hook-form';
import classNames from 'classnames';
import { useHistory } from 'react-router-dom';
import LoginService from '../services/LoginService';
import Exavalu from '../Login/Exavalu.png';

const Login = () => {
  const { register, setError, formState: { errors }, handleSubmit, reset } = useForm();
  var status = ""
  const history = useHistory();
  const onSubmit = async (data) => {
    try {
      const responseData = await LoginService.verifyCredentials(data);
      status = responseData.data;
      alert("user status ::: " + status);
      if (status == "Active") {
        history.push("/home");
      } else {
        alert("Invalid credentials");
      }
    }
    catch (error) {
      alert("Error Occurred" + error.response.data.error);
    }
  }
  return (
    <section class="vh-100">
      <div className="container py-5 h-100">
        <div className="row d-flex align-items-center justify-content-center h-100">
          <div className="col-md-8 col-lg-7 col-xl-6">
            <img src={Exavalu} className="img-fluid" />
          </div>
          <div className="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
            <form onSubmit={handleSubmit(onSubmit)}>

              <div className="form-outline mb-4">
                {/*  <input type="email" id="form1Example13" className="form-control form-control-lg" /> */}
                <input
                  className={classNames("form-control form-control-lg", {
                    "is-invalid": errors.userName,
                  })}
                  {...register('userName', {
                    required: 'userName is a required',
                    maxLength: {
                      value: 15,
                      message: 'Max length is 15',
                    },
                  })}
                />
                {/*  <label className="form-label" for="form1Example13">Email address</label> */}
                <label htmlFor="userName" className="form-label">User Name</label>

                {errors.userName && (
                  <div className="invalid-feedback">
                    {errors.userName.message}
                  </div>
                )}

              </div>


              <div className="form-outline mb-4">
                {/*  <input type="password" id="form1Example23" className="form-control form-control-lg" /> */}
                <input
                  className={classNames("form-control form-control-lg", {
                    "is-invalid": errors.password,
                  })}
                  {...register('password', {
                    required: 'password is a required',
                    maxLength: {
                      value: 15,
                      message: 'Max length is 15',
                    },
                  })}
                  type="password"
                />
                <label htmlFor="password" className="form-label">Password</label>
                {errors.password && (
                  <div className="invalid-feedback">
                    {errors.password.message}
                  </div>
                )}
              </div>




              <button type="submit" className="btn btn-primary btn-lg btn-block">Sign in</button>


            </form>
          </div>
        </div>
      </div>
    </section>
  )
}

export default Login
