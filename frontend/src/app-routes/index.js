import React, {useState, useEffect, useMemo} from 'react'
import { BrowserRouter as Router, Route, Switch, } from "react-router-dom";
import Home from '../app/policysearch/Home'
import ShowPolicy from '../app/policysearch/ShowPolicy';
import Login from '../app/login/Login';
import Upload from '../app/policyattachment/Upload';
import EligibilityCheck from '../app/eligibilitycheck/EligibilityCheck';
import ReportLoss from '../app/reportloss/ReportLoss';
import Dashboard from '../app/dashboard/Dashboard';
import ExcessLiability from '../app/excessliability/ExcessLiability'
import CommercialAuto from '../app/commercialauto/CommercialAuto'
import ReportLossSuccessMessage from '../app/reportloss/ReportLossSuccessMessage'
import TPCI from '../app/tpci/TPCI'
import LoginFailure from '../components/modals/popupmodal/LoginFailureModal'
import SystemMaintenance from '../components/modals/popupmodal/SystemMaintenanceModal'
import ErrorMessage from '../components/modals/popupmodal/ErrorMessageModal'
import PolicyNotFound from '../components/modals/popupmodal/PolicyNotFoundModal'
import DocumentsContainer from '../app/contentmanagement/DocumentsContainer'
import PremiumDataMessage from '../app/excessliability/PremiumDataMessage';
import Reports from '../app/reports/Reports';
import QuickIndicationReport from '../app/reports/QuickIndicationReport';
import QuoteSubmissionPremiumReport from '../app/reports/QuoteSubmissionPremiumReport';
// import FNOLReport from './app/reports/FNOLReport';
import ChangePassword from '../app/login/ChangePassword';
import Admin from '../app/admin/Admin'
import Content from '../app/admin/Content'
import CommercialProperty from '../app/commercialproperty/CommercialProperty'
import CommercialUmbrella from '../app/commercialumbrella/CommercialUmbrella';
import UserManagement from '../app/admin/UserManagement';

import UserRegistration from '../app/admin/UserCreation';
import UserUpdate from '../app/admin/UserUpdate';
import UserUpdateScreen from '../app/admin/UserUpdateScreen';

import ProducerManagement from '../app/producerManagement/ProducerManagement';
import CreateProducer from '../app/producerManagement/CreateProducer';
import DataDownload from '../app/admin/DataDownload';
import ApplicationProperties from '../app/admin/ApplicationProperties';
import SearchPolicy from '../app/dashboard/tabstable/SearchPolicy';
import LossNoticeReport from '../app/reports/LossNoticeReport';
import ApiDocumentation from '../app/admin/ApiDocumentation';
import ContactForm from '../app/contact/ContactForm';
import ContactSuccessMessage from '../app/contact/ContactSuccessMessage';
import ForgotPassword from '../app/login/ForgetPassword';
import LoginReportDetails from '../app/reports/LoginReport';
import Statistics from '../app/statistics/Statistics';
import ForceChangePassword from '../app/login/ForceChangePassword';
import ConfigDocument from '../app/admin/ConfigDocument';
import SessionTimeout from '../components/sessionTimeout/SessionTimeout';
import LogoutModal from '../components/modals/popupmodal/LogoutModal';
import UploadPropertyAcord from '../app/admin/UploadPropertyAcord';
import PropertyQuote from '../app/quote/propertyQuote';
import AutoQuote from '../app/quote/autoQuote';
import TaskList from '../app/tasks/taskList';
import { Support } from '../app/support/support';
import TicketInfo from '../app/support/ticketInfo/ticketInfo';
import UploadAutoAcord from '../app/admin/UploadAutoAcord';
import ApprovalConfigDocument from '../app/admin/ApprovalConfigDocument';
import MyWorkBench from '../app/dashboard/MyWorkBench';
import UploadLogo from '../app/admin/UploadLogo';
import LogoBranding from '../app/admin/UpdateTheme';
import GetThemeService from "../app/services/GetThemeService";
import API_Headers from '../API_Headers';
import useMetaData from "../context/metaData";
import GetLogoService from "../app/services/GetLogo";
import MapWithStreetView from "../app/commercialproperty/MapWithStreetView";
import AgentFeedback from '../app/admin/AgentFeedback';
import AgentSuggestion from '../app/admin/AgentSuggestion';
import FeedbackForm from '../app/feedback/FeedbackForm';
import SuggestionForm from '../app/feedback/SuggestionForm';
import FeedbackDetails from '../app/feedback/FeedbackDetails';
import SuggestionDetails from '../app/feedback/SuggestionDetails';

const AppRoutes = () => {
    const {setTheme, setLogoBase64, theme} = useMetaData()
    const [modalData, setModalData] = useState({ show: false, title: "", body: "" });
    const [headers, setHeaders] = useState({});
	useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

    useEffect(() => {
        updateTheme();
    }, [theme]);

    useEffect(() => {
      if(!!Object.keys(headers).length) {
        getLogo()
        getTheme()
      }}, [headers])

    const getLogo = async () => {
        const res = await GetLogoService.getLogo(headers)
        if(!!res?.data?.filecontent) {
            setLogoBase64(`data:image/jpeg;base64,${res.data.filecontent}`)
        }
    }

    const getTheme = async () => {
		const res = await GetThemeService.getTheme(headers)
        if('button' in res?.data && 'headerSection' in res?.data && 'navbar' in res?.data) {
            setTheme(res?.data)
        } else {
            setTheme({})
        }
	}

    const updateTheme = () => {
        const root = document.documentElement;
        //Navbar Property
        root?.style.setProperty(
            "--defaultBackgroundNavbarColor",
            !!Object.keys(theme).length ? theme.navbar.background : "#fff"
        );
        root?.style.setProperty("--defaultTextNavbarColor", !!Object.keys(theme).length ? theme.navbar.text : "#000");
        
        //Header Property
        root?.style.setProperty(
            "--defaultBackgroundHeaderColor",
            !!Object.keys(theme).length ? theme.headerSection.background : "#000"
        );
        root?.style.setProperty("--defaultTextHeaderColor", !!Object.keys(theme).length ? theme.headerSection.text : "#fff");
        
        //Sidebar Property
        root?.style.setProperty(
            "--defaultSidebarBackgroundColor",
            !!Object.keys(theme).length ? theme.sidebar.background : "#fff"
        );
        root?.style.setProperty("--defaultSidebarTextColor", !!Object.keys(theme).length ? theme.sidebar.text : "#000");

        //Button color
        root?.style.setProperty(
            "--defaultButtonBackground",
            !!Object.keys(theme).length ? theme.button.background : "#CF0918"
        );
        root?.style.setProperty("--defaultButtonColor", !!Object.keys(theme).length ? theme.button.text : "#ffffff");
    }
	
    function handleTimeout() {
        if (sessionStorage.getItem("userName") !== undefined && sessionStorage.getItem("userName") !== null) {
          setModalData({ show: true, title: "Session Expired", body: "Your session has expired due to inactivity. Please log in again to continue using the app. Click OK to return to the login page." })
        }
      }
    return (
        <Router>
            <div className="App">
                <SessionTimeout onTimeout={handleTimeout} />
                <Switch>
                    <Route exact path="/" component={Login} />
                    <Route exact path="/dashboard" component={Dashboard} />
                    <Route exact path="/policyDB/:id" component={ShowPolicy} />
                    <Route exact path="/home" component={Home} />
                    <Route exact path="/attachment" component={Upload} />
                    <Route exact path="/eligibilitycheck" component={EligibilityCheck} />
                    <Route exact path="/reportloss" component={ReportLoss} />
                    <Route exact path="/excessliability" component={ExcessLiability} />
                    <Route exact path="/commercialauto" component={CommercialAuto} />
                    <Route exact path="/reportLossSuccessMsg/:noticeNumber" component={ReportLossSuccessMessage} />
                    <Route exact path="/premiumDataMsg" component={PremiumDataMessage} />
                    <Route exact path="/tpci" component={TPCI} />
                    <Route exact path="/loginFailure" component={LoginFailure} />
                    <Route exact path="/SystemMaintenance" component={SystemMaintenance} />
                    <Route exact path="/errorMessage/:error" component={ErrorMessage} />
                    <Route exact path="/policyNotFound" component={PolicyNotFound} />
                    <Route exact path="/contentManagement" component={DocumentsContainer} />
                    <Route exact path="/forgotPassword" component={ForgotPassword} />
                    <Route exact path="/changePassword" component={ChangePassword} />
                    <Route exact path="/forceChangePassword" component={ForceChangePassword} />
                    <Route exact path="/admin" component={Admin} />
                    <Route exact path="/content" component={Content} />
                    <Route exact path="/commercialProperty" component={CommercialProperty} />
                    <Route exact path="/commercialUmbrella" component={CommercialUmbrella} />
                    <Route exact path="/userManagement" component={UserManagement} />
                    <Route exact path="/userManagement/userRegistration" component={UserRegistration} />
                    <Route exact path="/userManagement/userUpdate" component={UserUpdate} />
                    <Route exact path="/userManagement/userUpdate/userUpdateScreen" component={UserUpdateScreen} />
                    <Route exact path="/dataDownload" component={DataDownload} />
                    <Route exact path="/applicationProperties" component={ApplicationProperties} />

                    <Route exact path="/apiDocumentation" component={ApiDocumentation} />
                    <Route exact path="/reports" component={Reports} />
                    <Route exact path="/quickIndicationReport" component={QuickIndicationReport} />
                    <Route exact path="/quoteSubmissionPremiumReport" component={QuoteSubmissionPremiumReport} />
                    <Route exact path="/lossReport" component={LossNoticeReport} />
                    <Route exact path="/contactUs" component={ContactForm} />
                    <Route exact path="/tasks" component={TaskList} />
                    <Route exact path="/contactSuccessMessage" component={ContactSuccessMessage} />
                    <Route exact path="/openWork" component={MyWorkBench} />
                    <Route exact path="/statistics" component={Statistics} />
                    <Route exact path="/producerManagement" component={ProducerManagement} />
                    <Route exact path="/createProducer" component={CreateProducer} />
                    <Route exact path="/searchPolicy" component={SearchPolicy} />
                    <Route exact path="/loginReport" component={LoginReportDetails} />
                    <Route exact path="/configDocument" component={ConfigDocument} />
                    <Route exact path="/uploadPropertyPdf" component={UploadPropertyAcord} />
                    <Route exact path="/uploadAutoPdf" component={UploadAutoAcord} />
                    <Route exact path="/approvalConfigDocument" component={ApprovalConfigDocument} />
                    <Route exact path="/upload-logo" component={UploadLogo} />
                    <Route exact path="/logo-branding" component={LogoBranding} />
                    <Route exact path="/propertyQuote" component={PropertyQuote} />
                    <Route exact path="/autoQuote" component={AutoQuote} />
                    {/* <Route exact path="/fnolReport" component={FNOLReport} /> */}
                    <Route exact path="/support" component={Support} />
                    <Route exact path="/support/:id" component={TicketInfo} />
                    <Route path="/map" component={MapWithStreetView} />
                    <Route path="/feedback" component={AgentFeedback} />
                    <Route path="/suggestion" component={AgentSuggestion} />
                    <Route path="/agentFeedback" component={FeedbackForm} />
                    <Route path="/agentSuggestion" component={SuggestionForm} />
                    <Route path="/feedbackDetails" component={FeedbackDetails} />
                    <Route path="/suggestionDetails" component={SuggestionDetails} />

                </Switch>
            </div>
            <LogoutModal showModal={modalData.show} modalTitle={modalData.title} modalBody={modalData.body} setShowModal={setModalData} />
        </Router>
    )
}

export default AppRoutes