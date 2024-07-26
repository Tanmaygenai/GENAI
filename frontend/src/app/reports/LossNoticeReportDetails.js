import { useState } from 'react';
import { useEffect } from 'react';
import jsPDF from "jspdf";
import "jspdf-autotable";
import { Pagination } from 'react-bootstrap';
import { useHistory, useLocation } from 'react-router-dom';
import { useParams } from 'react-router'
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import ReportLossService from '../services/ReportLossService';
import ExportCSV from "../../components/export/ExportCSV"
import ExportPDF from "../../components/export/ExportPDF"
import API_Headers from '../../API_Headers';
import useLoader from "../../context/loader"

const LossNoticeReportDetails = (props) => {
  const { setLoader } = useLoader()
  const [reportDetails, setReportDetails] = useState([]);
  const reportData = useState([]);
  const { data } = useLocation();
  const [state, setState] = useState("")
  const [activePage, setActivePage] = useState(1)
  const startDate = props.startDate;
  const endDate = props.endDate;
  const [rows, setRows] = useState([]);
  const [lossNoticeData, setLossNoticeData] = useState([]);
  const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
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
      const roleType = sessionStorage.getItem("roleType");
      const reportData = await ReportLossService.lossNoticeReports(headers, startDate, endDate, roleType);
      setReportDetails(reportData.data);

      setLoader(false)
    } catch (error) {
      // setIsLoaded(false);
      setLoader(true)
      setErrorModal({ show: true, title: "Network Error", body: "Unable to load the details" })

    }
  };

  useEffect(() => {
    if (reportDetails !== undefined) {
      var arr = [];
      reportDetails.map((lr) => {
        var st = lr.lossnoticeData;
        var stob = st.replace(/[=]/g, ':');
        var strob = stob.replaceAll("'", '"');
        var jsonob = JSON.parse(strob);
        arr.push(jsonob)
        try {
          var jsonob = JSON.parse(strob);
        } catch (error) {
          setLoader(true)
          setErrorModal({ show: true, title: "Error", body: "Invalid Data Found!" })
        }
      })
      setLossNoticeData(arr)
    }
  }, [reportDetails])

  useEffect(() => {
    if (lossNoticeData.length > 0) {
      var arr = [];
      for (var i = 0; i < reportDetails.length; i++) {
        const items = (<>
          <td >{lossNoticeData[i].policyNumber}</td>
          <td >{lossNoticeData[i].insuredName}</td>
          <td >{reportDetails[i].lossNoticeNumber}</td>
          <td >{reportDetails[i].reportedDate}</td>
          <td >{reportDetails[i].userName}</td>
        </>
        )
        arr.push(items)
      }
      setRows(arr)
    }
  }, [lossNoticeData])

  const pdfHeaders = ["Policy Number", "Insured Name", "Loss Notice Number", "Reported Date", "User Name"];

  var arr = []
  var arr2 = []
  for (var i = 0; i < lossNoticeData.length; i++) {
    arr.push(lossNoticeData[i].policyNumber, lossNoticeData[i].insuredName, reportDetails[i].lossNoticeNumber, reportDetails[i].reportedDate, reportDetails[i].userName)
    arr2.push(arr)
  }
  const pdfData = arr2

  const reportHeaders = [
    { label: "Policy Number", key: "policyNumber" },
    { label: "Insured Name", key: "insuredName" },
    { label: "Loss Notice Number", key: "lossNoticeNumber" },
    { label: "Reported Date", key: "reportedDate" },
    { label: "User Name", key: "userName" },
  ];
  var noOfRowsPerPage = 10;
  var noOfPagesToDisplay = 25;
  var ind = 0

  useEffect(() => {
    if (lossNoticeData.length > 0) {
      var arr = [];
      for (var i = 0; i < reportDetails.length; i++) {
        const items = (<>
          <td >{lossNoticeData[i].policyNumber}</td>
          <td >{lossNoticeData[i].insuredName}</td>
          <td >{reportDetails[i].lossNoticeNumber}</td>
          <td >{reportDetails[i].reportedDate}</td>
          <td >{reportDetails[i].userName}</td>
        </>
        )
        arr.push(items)
      }
      setRows(arr)
    }
  }, [lossNoticeData])

  let items = [];
  for (let number = 1; ((number - 1 - (1 / 10)) <= (reportDetails.length / 10) && number <= noOfPagesToDisplay); number++) {
    items.push(
      <Pagination.Item key={number} active={number === activePage} onClick={() => setActivePage(number)}>
        {number}
      </Pagination.Item>,
    );
  }

  return (
    <div class="tabContent" style={{ display: "block" }}>
      <div>
        <div class="grid4">
          <div style={{ paddingBottom: "5%" }}>
            <ExportPDF pdfHeaders={pdfHeaders} pdfTitle="Loss Notice Report" fileName="LossNoticeReport" pdfData={pdfData} />
          </div>
          <div>
            <ExportCSV csvData={reportDetails} fileName="LossNoticeReport.csv" headers={reportHeaders} />
          </div>
        </div>

        <table>
          <thead>
            <tr>
              <th>Policy Number</th>
              <th>Insured Name</th>
              <th>Loss Notice Number</th>
              <th>Reported Date</th>
              <th>User Name</th>
            </tr>
          </thead>

          <tbody>
            {rows.length > 0 ?
              rows.map((i, r) => {
                return (
                  <tr>{i}</tr>
                )
              }) : ''
            }
          </tbody>
        </table>
        <br />
        <Pagination size="sm" style={{ justifyContent: "center" }}>
          {items}
        </Pagination>
      </div>
      <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
    </div>

  )
}
export default LossNoticeReportDetails;