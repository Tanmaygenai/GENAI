import React, { useEffect } from "react";
import { Link } from 'react-router-dom';
import $ from 'jquery'
import logo from '../../assets/img/exavalu_logo_small.jpg';
import icon1 from '../../assets/img/icons/icon1.png';
import icon2 from '../../assets/img/icons/icon2.png';
import icon3 from '../../assets/img/icons/icon3.png';
import icon4 from '../../assets/img/icons/icon4.png';
import icon5 from '../../assets/img/icons/icon5.png';
import filetype from '../../assets/img/icons/filetype-doc.svg';
import user from '../../assets/img/icons/user.webp'
import dashboard from '../../assets/img/icons/dashboard.png'
import comproperty from '../../assets/img/icons/comproperty.png'
import comumbrella from '../../assets/img/icons/comumbrella.png'
import contactIcon from '../../assets/img/icons/mail.png'
import dash3 from '../../assets/img/icons/dash3.png';
import report from '../../assets/img/icons/report.png';
import analysis from '../../assets/img/icons/analysis.png';
import feedback from '../../assets/img/icons/feedback.png';
import suggestion from '../../assets/img/icons/suggestion.png';
import useMetaData from "../../context/metaData"

const Sidebar = () => {
    const { logoBase64 } = useMetaData();
    const role = sessionStorage.getItem("roleType")
    var current_url = window.location.pathname;
    useEffect(() => {
        $('a[href="' + current_url + '"]').addClass('active');
        $('section').on("click", '.accord .accord-btn', function (e) {
            e.preventDefault();
            $('.accord-target').not($(this).next('.accord-target')).slideUp();
            $(this).next('.accord-target').slideToggle();

            $('.accord-btn').not($(this)).removeClass('actv');
            $(this).toggleClass('actv');
            sessionStorage.removeItem("quoteNumber");
        });

        $('section').on("click", '.manual-quote .manual-quote-btn', function (e) {
            e.preventDefault();
            $('.manual-quote-target').not($(this).next('.manual-quote-target')).slideUp();
            $(this).next('.manual-quote-target').slideToggle();

            $('.manual-quote-btn').not($(this)).removeClass('actv');
            $(this).toggleClass('actv');
        });

        $('section').on("click", '.accord-quote .accord-quote-btn', function (e) {
            e.preventDefault();
            $('.accord-quote-target').not($(this).next('.accord-quote-target')).slideUp();
            $(this).next('.accord-quote-target').slideToggle();

            $('.accord-quote-btn').not($(this)).removeClass('actv');
            $(this).toggleClass('actv');
        });
    }, [])



    return (
        (role === 'admin') ?
            <section className="sidebar sidebarNewipad">
                {/* <img alt="Logo" src={base64 || ''} /> */}
                <div className="logo"><a href="https://www.Exavalu.com/" target="_blank" title=""><img src={!!logoBase64.length ? logoBase64 : logo} alt="Exavalu" title="Exavalu" /></a></div>
                <div class="ip">GEN AI Portal</div>
                <div class="sidebarNavigation sidebarNavigationNew">
                    <ul className="sidebar-content">
                        {/* <li><Link to="/dashboard"><span><img src={dashboard} alt="Exavalu" title="Exavalu" /></span> <p>My Dashboard</p></Link></li> */}
                        <li><Link to="/admin"><span><img src={icon2} alt="Exavalu" title="Exavalu" /></span> <p>Tasks</p></Link></li>
                        <li><Link to="/openWork"><span><img src={dash3} alt="Exavalu" title="Exavalu" /></span> <p>Submission</p></Link></li>
                        <li><Link to="/admin"><span><img src={icon1} alt="Exavalu" title="Exavalu" /></span> <p>Flows</p></Link></li>
                        <li><Link to="/admin"><span><img src={icon2} alt="Exavalu" title="Exavalu" /></span> <p>Library</p></Link></li>
                        {/* <li class="submenu accord" id="quickInd" >
                            <a href="javascript:void(0);" class="accord-btn"><span><img src={icon4} alt="Exavalu" title="Exavalu" /></span> <p>Quick Quote</p></a>
                            <ul class="accord-target">
                                <li class="submenu manual-quote">
                                    <a href="javascript:void(0);" class="manual-quote-btn">
                                        <span><img src={icon4} alt="Exavalu" title="Exavalu" /></span> <p>Enter Details</p>
                                    </a>
                                    <ul class="manual-quote-target">
                                        <li style={{ marginLeft: '10%' }}><Link to="/commercialAuto"><span><img src={icon5} alt="Exavalu" title="Exavalu" /></span> <p>Commercial Auto</p></Link></li>
                                        <li style={{ marginLeft: '10%' }}><Link to="/commercialProperty"><span><img src={comproperty} alt="Exavalu" title="Exavalu" /></span> <p>Commercial Property</p></Link></li>
                                        <li style={{ marginLeft: '10%' }}><Link to="/excessLiability"><span><img src={comumbrella} alt="Exavalu" title="Exavalu" /></span> <p>Commercial Excess Liability</p></Link></li>
                                    </ul>
                                </li>
                                <li class="submenu accord-quote">
                                    <a href="javascript:void(0);" class="accord-quote-btn">
                                        <span><img src={icon4} alt="Exavalu" title="Exavalu" /></span> <p>Upload Details</p>
                                    </a>
                                    <ul class="accord-quote-target">
                                        <li style={{ marginLeft: '10%' }}><Link to="/uploadPropertyPdf"><span><img src={comproperty} alt="Exavalu" title="Exavalu" /></span> <p>Commercial Property</p></Link></li>
                                        <li style={{ marginLeft: '10%' }}><Link to="/uploadAutoPdf"><span><img src={icon5} alt="Exavalu" title="Exavalu" /></span> <p>Commercial Auto</p></Link></li>
                                    </ul>
                                </li>
                            </ul>
                        </li> */}
                        {/* <li><Link to="/eligibilitycheck"><span><img src={icon3} alt="Exavalu" title="Exavalu" /></span> <p>Eligibility Check</p></Link></li>
                        <li><Link to="/reportloss"><span><img src={icon1} alt="Exavalu" title="Exavalu" /></span> <p>Report a Loss</p></Link></li>
                        <li><Link to="/attachment"><span><img src={icon2} alt="Exavalu" title="Exavalu" /></span> <p>Upload Documents</p></Link></li>
                        <li><Link to="/contentManagement"><span><img src={filetype} alt="Exavalu" title="Exavalu" /></span> <p>Content Repository</p></Link></li>
                        <li><Link to="/reports"><span><img src={report} alt="Exavalu" title="Exavalu" /></span> <p>Reports</p></Link></li>
                        <li><Link to="/statistics"><span><img src={analysis} alt="Exavalu" title="Exavalu" /></span> <p>Statistics</p></Link></li> */}
                        <li><Link to="/admin"><span><img src={user} alt="Exavalu" title="Exavalu" /></span> <p>Admin</p></Link></li>
                        <li><Link to="/support"><span><img src={contactIcon} alt="Exavalu" title="Exavalu" /></span> <p>Support</p></Link></li>
                    </ul>
                </div>
            </section>
            :
            <section className="sidebar sidebarNewipad">
                <div className="logo"><a href="https://www.Exavalu.com/" target="_blank" title=""><img src={!!logoBase64.length ? logoBase64 : logo} alt="Exavalu" title="Exavalu" /></a></div>
                <div class="ip">Insurance Portal</div>
                <div class="sidebarNavigation sidebarNavigationNew">
                    <ul className="sidebar-content">
                        <li><Link to="/dashboard"><span><img src={dashboard} alt="Exavalu" title="Exavalu" /></span> <p>My Dashboard</p></Link></li>
                        <li><Link to="/openWork"><span><img src={dash3} alt="Exavalu" title="Exavalu" /></span> <p>My Workbench</p></Link></li>
                        <li><Link to="/producerManagement"><span><img src={dashboard} alt="Exavalu" title="Exavalu" /></span> <p>Producer Management</p></Link></li>
                        <li class="submenu accord" id="quickInd">
                            <a href="javascript:void(0);" class="accord-btn">
                                <span><img src={icon4} alt="Exavalu" title="Exavalu" /></span> <p>Quick Quote</p>
                            </a>
                            <ul class="accord-target">
                                <li class="submenu manual-quote">
                                    <a href="javascript:void(0);" class="manual-quote-btn">
                                        <span><img src={icon4} alt="Exavalu" title="Exavalu" /></span> <p>Manual Quote</p>
                                    </a>
                                    <ul class="manual-quote-target">
                                        <li style={{ marginLeft: '10%' }}><Link to="/commercialAuto"><span><img src={icon5} alt="Exavalu" title="Exavalu" /></span> <p>Commercial Auto</p></Link></li>
                                        <li style={{ marginLeft: '10%' }}><Link to="/commercialProperty"><span><img src={comproperty} alt="Exavalu" title="Exavalu" /></span> <p>Commercial Property</p></Link></li>
                                        <li style={{ marginLeft: '10%' }}><Link to="/excessLiability"><span><img src={comumbrella} alt="Exavalu" title="Exavalu" /></span> <p>Commercial Excess Liability</p></Link></li>
                                    </ul>
                                </li>
                                <li class="submenu accord-quote">
                                    <a href="javascript:void(0);" class="accord-quote-btn">
                                        <span><img src={icon4} alt="Exavalu" title="Exavalu" /></span> <p>Automatic Quote</p>
                                    </a>
                                    <ul class="accord-quote-target">
                                        <li style={{ marginLeft: '10%' }}><Link to="/uploadPropertyPdf"><span><img src={comproperty} alt="Exavalu" title="Exavalu" /></span> <p>Commercial Property</p></Link></li>
                                        <li style={{ marginLeft: '10%' }}><Link to="/uploadAutoPdf"><span><img src={icon5} alt="Exavalu" title="Exavalu" /></span> <p>Commercial Auto</p></Link></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li><Link to="/eligibilitycheck"><span><img src={icon3} alt="Exavalu" title="Exavalu" /></span> <p>Eligibility Check</p></Link></li>
                        <li><Link to="/reportloss"><span><img src={icon1} alt="Exavalu" title="Exavalu" /></span> <p>Report a Loss</p></Link></li>
                        <li><Link to="/attachment"><span><img src={icon2} alt="Exavalu" title="Exavalu" /></span> <p>Upload Documents</p></Link></li>
                        <li><Link to="/contentManagement"><span><img src={filetype} alt="Exavalu" title="Exavalu" /></span> <p>Content Repository</p></Link></li>
                        <li><Link to="/reports"><span><img src={user} alt="Exavalu" title="Exavalu" /></span> <p>Reports</p></Link></li>
                        <li><Link to="/tasks"><span><img src={user} alt="Exavalu" title="Exavalu" /></span> <p>Tasks</p></Link></li>
                        <li><Link to="/support"><span><img src={contactIcon} alt="Exavalu" title="Exavalu" /></span> <p>Support</p></Link></li>
                        <li><Link to="/contactUs"><span><img src={contactIcon} alt="Exavalu" title="Exavalu" /></span> <p>Underwriter Connect</p></Link></li>
                    </ul>
                </div>
            </section>
    )
}
export default Sidebar