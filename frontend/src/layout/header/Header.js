import React, { useEffect } from "react";
import { Link } from 'react-router-dom';
import CurrentTime from "./currenttime/CurrentTime";
// import Logo from "./logo/Logo";
import $ from 'jquery'
import user_icon from '../../assets/img/icons/username.svg';
import help from '../../assets/img/icons/help.png'
import { useHistory } from 'react-router-dom';


const Header = () => {
    const history = useHistory();
    const role = sessionStorage.getItem("roleType")
    useEffect(() => {
        $('.userinfoLink p').on('click', function () {
            $('.drpdwn').slideToggle();
        });
        $(document).on('click', function (e) {
            var parentarea = $('.userinfoLink');
            var elm = $('.drpdwn');
            if (parentarea.has(e.target).length === 0) {
                elm.slideUp();
            }
        });
    }, [])

    const logout = (e) => {
        e.preventDefault();
        sessionStorage.removeItem("userName");
        sessionStorage.removeItem("password");
        history.push("/");
    }

    return (
        (role === 'admin')?
        <div class="dateTimeheader">
            <CurrentTime />
            
            <div class="userInfoNotification">
                <div class="userInfo">              
                    <div class="userinfoLink">                                         
                        <p>
                            <span>
                                <img src={user_icon} />
                            </span>
                            {sessionStorage.getItem('userName')}
                            <i class="fa fa-angle-down" aria-hidden="true"></i>
                        </p>
                        <div class="drpdwn">
                            <ul>
                                
                             <li>  <Link to="/" onClick={e=>logout(e)}><span>Log Out</span></Link></li> 
                            </ul>
                        </div>
                    </div>                   
                </div>
                
            </div>
           
        </div>
        :
        <div class="dateTimeheader">
            <CurrentTime />
            
            <ul >
                <li >
                    <div class="dropdown" >
                        <button class="dropbtn" ><img src={help} alt="Help" title="Help" /></button>
                        <div class="dropdown-content">
                            <li><Link to="/agentFeedback"><span><p>Agent Feedback</p></span></Link></li>
                            <li><Link to="/agentSuggestion"><span><p>Agent Suggestions</p></span></Link></li>
                        </div>
                    </div>
                </li>
                <li >
                    <div class="dropdown" >
                        <button class="dropbtn"><img src={user_icon} alt="Exavalu" title="Exavalu" />{sessionStorage.getItem('userName')}</button>
                        <div class="dropdown-content">
                            <li><Link to="/" onClick={e=>logout(e)}>Logout</Link></li>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    )

}
export default Header
