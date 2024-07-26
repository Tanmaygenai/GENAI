import React, { useEffect, useState } from 'react'
import { Link, useHistory } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import $ from 'jquery'
const SubHeader = ({ BreadCrum }) => {
    var newWidth = $(window).width();
    React.useEffect(() => {
        if (window.readyState === "complete") {
            window.addEventListener("resize", setTimeout(() => handleResize(), 100), false);
            return () => window.removeEventListener("resize", setTimeout(() => handleResize(), 100), false);
        } else {
            window.addEventListener('load', handleResize(), false);
            return () => window.removeEventListener('load', handleResize, false);
        }
    });
    const handleResize = () => {

        if (newWidth <= 767) {

            $('.sidebarNavigation').addClass('sidebarNavigationNew');
            // $('.submenu').removeClass("menu-open");
            $('.sidebar').addClass('sidebarNew');
            $('.sidebar').addClass('sidebarNewipad');
            $('.mainContent').addClass('mainContentNew');
            $('.dateTimeheader').addClass('dateTimeheaderNew');
            $('.commonheader').addClass('commonheaderNew');
            $('.userheader').addClass('userheaderNew');
            $('.breadcum').addClass('breadcumNew');
            $('.submenu > a').removeClass('submenuactive');
            $('.submenu > ul').slideUp();
        } else {
            $('.sidebarNavigation').removeClass('sidebarNavigationNew');
            // $('.submenu').removeClass("menu-open");
            $('.sidebar').removeClass('sidebarNew');
            $('.sidebar').removeClass('sidebarNewipad');
            $('.mainContent').removeClass('mainContentNew');
            $('.dateTimeheader').removeClass('dateTimeheaderNew');
            $('.commonheader').removeClass('commonheaderNew');
            $('.userheader').removeClass('userheaderNew');
            $('.breadcum').removeClass('breadcumNew');
            $('.submenu > a').removeClass('submenuactive');
            $('.submenu > ul').slideUp();
        }
    }
    useEffect(() => {
        $('.sidebarNavigation').removeClass('sidebarNavigationNew');
        $('.toggleBtn').on('click', function () {
            $('.sidebarNavigation').toggleClass('sidebarNavigationNew');
            $('.submenu').removeClass("menu-open");
            $('.sidebar').toggleClass('sidebarNew');
            $('.sidebar').toggleClass('sidebarNewipad');
            $('.mainContent').toggleClass('mainContentNew');
            $('.dateTimeheader').toggleClass('dateTimeheaderNew');
            $('.commonheader').toggleClass('commonheaderNew');
            $('.userheader').toggleClass('userheaderNew');
            $('.breadcum').toggleClass('breadcumNew');
            $('.submenu > a').removeClass('submenuactive');
            $('.submenu > ul').slideUp();
        });
    }, []);
    const [policyId, setPolicyId] = useState();
    const onInputChange = (event) => {
        setPolicyId(event.target.value);
    }
    const history = useHistory();
    const handleOnClick = () => {
        history.push({
            pathname: "/policy/" + policyId,
            state: policyId
        });
    }
    const finalClassName = (ind) => {
        if (BreadCrum.length - 1 === ind) {
            return ("brd-active")
        }
        else {
            return ("")
        }
    }
    const supportTicket = `/support/${sessionStorage.getItem('ticketId')}`
    return (
        <div className="userheader">
            <div className="toggwrp">
                <a href="#" className="toggleBtn">&nbsp;</a>
                <div className="breadcum">
                    <ul className='m-0'>
                        {BreadCrum.map((val, ind) => {
                            return (
                                <li key={ind} className={finalClassName(ind)}>
                                    {val === 'My Dashboard' && <Link to="/dashboard">My Dashboard</Link>}
                                    {val === 'Producer Management' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Producer Management</p>}
                                    {val === 'New Producer' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>New Producer</p>}
                                    {val === 'Report A Loss' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Report A Loss</p>}
                                    {val === 'Loss Notice Number' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Loss Notice Number</p>}
                                    {val === 'Eligibility Check' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Eligibility Check</p>}
                                    {val === 'Quick Quote' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Quick Quote</p>}
                                    {val === 'Commercial Auto' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Commercial Auto</p>}
                                    {val === 'Commercial Excess Liability' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Commercial Excess Liability</p>}
                                    {val === 'Add Attachment' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Add Attachment</p>}
                                    {val === 'TPCI' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Commercial Package</p>}
                                    {val === 'Content Management' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Content Management</p>}
                                    {val === 'Admin' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Admin</p>}
                                    {val === 'Commercial Umbrella' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Commercial Umbrella</p>}
                                    {val === 'Commercial Property' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Commercial Property</p>}
                                    {val === 'User Management' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>User Management</p>}

                                    {val === 'User Registration' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>User Registration</p>}
                                    {val === 'User Update' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>User Update</p>}

                                    {val === 'Data Download' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Data Download</p>}
                                    {val === 'Search Policy' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Search Policy</p>}
                                    {val === 'REST API Documentation' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>REST API Documentation</p>}

                                    {val === 'Loss Report' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Loss Report</p>}
                                    {val === 'Login Report' &&<Link to='/loginReport'>Login Report</Link>}
                                    {val === 'Contact Us' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Contact Us</p>}
                                    {val === 'Contact Us Message' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Contact Us Message</p>}
                                    {val === 'Reports' && <p style={{ paddingTop: '2px', fontSize: "11.5px" }}>Reports</p>}
                                    {val === 'Quick Indication/Submission Report' && <Link to='/quickIndicationReport'>Quick Indication/Submission Report</Link>}
                                    {val === 'Quote Submission Premium Report' && <Link to='/quoteSubmissionPremiumReport'>Quote Submission Premium Report</Link>}
                                    {val === 'Statistics' && <Link to='/statistics'>Statistics</Link>}
                                    {val === 'Task' && <Link to='/tasks'>Task</Link>}
                                    {val === 'Support' && <Link to='/support'>Support</Link>}
                                    {val === 'Ticket' && <Link to={supportTicket}>Ticket</Link>}
                                    {val === 'FNOL Report' && <Link to='/fnolReport'>FNOL Report</Link>}
                                    {val === 'Config Dropdown Data Management' && <Link to='/configDocument'>Config Dropdown Data Management</Link>}
                                    {val === 'Approval Dropdown Data Management' && <Link to='/approvalDocument'>Approval Dropdown Data Management</Link>}
                                    {val === 'Upload Acord Form' && <Link to='/uploadPdf'>Upload Acord Form</Link>}
                                    {val === 'Agent Feedback' && <Link to='/agentFeedback'>Agent Feedback</Link>}
                                    {val === 'Agent Suggestion' && <Link to='/agentSuggestion'>Agent Suggestion</Link>}
                                </li>
                            )
                        })}
                    </ul>
                </div>
            </div>
        </div>
    )
}
export default SubHeader;
