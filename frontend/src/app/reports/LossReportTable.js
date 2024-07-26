import React, { useEffect, useState } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import FormControl from "../../components/formcontrol/FormControl"
import DropDown from '../../components/dropdown/DropDown';
import { drop_data } from '../../dummydata/data';
import LoginService from '../services/LoginService';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { Link } from 'react-router-dom';
import TabsTable1 from './tabstable1/TabsTable1';
import ReportLossService from '../services/ReportLossService';
import API_Headers from '../../API_Headers';
const LossReportTable = () => {
    const [lossReportDBItem, setLossReportDBItem] = useState([]);
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [response, setResponse] = useState({})
    const [headers, setHeaders] = useState([])

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

    useEffect(() => {
       if(headers.length !== 0){
        getLossNoticeReports();
        async function getLossNoticeReports() {
            try {
                const responseData = await ReportLossService.lossNoticeReports(headers);
                setLossReportDBItem(responseData.data)
            }
            catch (error) {
                alert(error);
            }
        }
       }
    }, [headers])
    return (
        <Layout TabName='Loss Report' BreadCrum={['My Dashboard', 'Admin', 'Loss Report' ]} sidebarQQSopen='true'>
            <div className="coverContainer">
                <div className="dashboard">
                    <div className="left">
                        <TabsTable1 lossReportDBItem={lossReportDBItem} setLossReportDBItem={setLossReportDBItem} />
                    </div>
                </div>
            </div>
        </Layout>
    )
};

export default LossReportTable;
