import React, { useEffect } from 'react'
import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import DataDownloadService from '../services/DataDownloadService';
import ExportCSV from "../../components/export/ExportCSV";
import JSONViewer from 'react-json-viewer';
import CryptoJS from "crypto-js";
import API_Headers from '../../API_Headers';
import useLoader from '../../context/loader'

const DataDownloadDetails = (props) => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    const { setLoader } = useLoader();
    const history = useHistory();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [response, setResponse] = useState([])
    const [tableRow, setTableRow] = useState([])
    const itemRows = []
    const systemId = props.systemId;

    const [headers, setHeaders] = useState([])

    useEffect(() => {
        setLoader(true)
        API_Headers().then((val) => setHeaders(val))
    }, [])


    useEffect(() => {
        if (headers.length !== 0) {
            loadReportDetails();
        }
    }, [headers]);

    const loadReportDetails = async () => {
        try {
            const data1 = CryptoJS.AES.encrypt(
                systemId,
                secretPass
            ).toString();
            const responseData = await DataDownloadService.dataDownloadSearch(data1, headers);
            setResponse([responseData.data])
            setLoader(false)
            if (responseData.data.length !== 0) {
                try {
                    const jsonData = JSON.parse(responseData.data);
                    console.log(jsonData)
                    setTableRow([jsonData])
                } catch (err) {
                    setErrorModal({ show: true, title: "Error", body: "Invalid Data Found!" })
                }
            } else {
                setErrorModal({ show: true, title: "Error", body: "No Data Found!" })
            }
        } catch (error) {
            ;
            setLoader(false)
            setErrorModal({ show: true, title: "Network Error", body: "Unable to load the details" })
        }
    };


    for (let item of tableRow) {
        const row = (
            <tr key={item}>
                <td key={0}>{item}</td>
            </tr>
        )
        itemRows.push(row);
    }



    return (
        <div>
            <div class="tabContent" style={{ display: "block" }}>
                <div>
                    {(tableRow.length > 0) ?

                        <div>
                            <ExportCSV csvData={response} class="btn blue" fileName="DataDownload.txt" headers={''} />
                            <div style={{ width: '100%', overflow: 'auto' }}>
                                <JSONViewer json={tableRow} />
                            </div>
                        </div> :
                        <div class="tabContent extraWidth" style={{ display: "block", color: '#CF0918' }}><p>No JSON to display</p></div>
                    }
                </div>
                <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
            </div>
        </div>
    );
}


export default DataDownloadDetails;
