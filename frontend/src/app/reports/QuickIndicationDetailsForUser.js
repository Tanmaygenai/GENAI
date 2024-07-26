import { useState } from 'react';
import { useEffect } from 'react';
import jsPDF from "jspdf";
import "jspdf-autotable";
import { Pagination } from 'react-bootstrap';
import { useHistory, useLocation } from 'react-router-dom';
import { useParams } from 'react-router'
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import QuickIndicationReportService from '../services/QuickIndicationReportService';
import ExportCSV from "../../components/export/ExportCSV"
import ExportPDF from "../../components/export/ExportPDF"
import API_Headers from '../../API_Headers';
import useLoader from "../../context/loader"

const QuickIndicationDetails = (props) => {
  const { setLoader } = useLoader()
  const [reportDetails, setReportDetails] = useState([]);
  const reportData = useState([]);
  const { data } = useLocation();
  const [state, setState] = useState("")
  const [activePage, setActivePage] = useState(1)
  const startDate = props.startDate;
  const endDate = props.endDate;
  const productType = props.productType;
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
      const reportData = await QuickIndicationReportService.getQuickIndicationDetails(headers, startDate, endDate, productType, roleType);
      setReportDetails(reportData.data);

      setLoader(false)
    } catch (error) {
      setLoader(false)
      setErrorModal({ show: true, title: "Network Error", body: "Unable to load the details" })

    }
  };
  const pdfHeaders = ["Quote Number", "Insured Name", "State", "Product", "Premium", "Effective Date", "Class Code", "Liability Limit", "Submission Date", "Indication Date"];

  const pdfData = reportDetails.map(elt => [elt.quoteNumber, elt.insuredName, elt.stateCd, elt.product, elt.premium,
  elt.effectiveDate, elt.classCode, elt.liabilityLimit, elt.submissionDate, elt.indicationDate]);

  const reportHeaders = [
    { label: "Quote Number", key: "quoteNumber" },
    { label: "Insured Name", key: "insuredName" },
    { label: "State", key: "stateCd" },
    { label: "Product", key: "product" },
    { label: "Premium", key: "premium" },
    { label: "Effective Date", key: "effectiveDate" },
    { label: "Class Code", key: "classCode" },
    { label: "Liability Limit", key: "liabilityLimit" },
    { label: "Submission Date", key: "submissionDate" },
    { label: "Indication Date", key: "indicationDate" }
  ];
  var noOfRowsPerPage = 10;
  var noOfPagesToDisplay = 25;
  var ind = 0
  const itemRows = [];
  for (let item of reportDetails) {
    if (activePage === parseInt(ind / noOfRowsPerPage) + 1) {
      const row = (
        <tr key={item.id}>
          <td key={0}>{item.quoteNumber}</td>
          <td key={1}>{item.insuredName}</td>
          <td key={2}>{item.stateCd}</td>
          <td key={3}>{item.product}</td>
          <td key={4}>{item.premium}</td>
          <td key={5}>{item.effectiveDate}</td>
          <td key={6}>{item.classCode}</td>
          <td key={7}>{item.liabilityLimit}</td>
          <td key={8}>{item.submissionDate}</td>
          <td key={9}>{item.indicationDate}</td>
        </tr>
      );
      itemRows.push(row);
    }
    ind++;
  }

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
            <ExportPDF pdfHeaders={pdfHeaders} pdfTitle="Quote Indication And Submission Report" fileName="QuoteIndicationAndSubmission" pdfData={pdfData} />
          </div>
          <div>
            <ExportCSV csvData={reportDetails} fileName="QuoteIndicationAndSubmission.csv" headers={reportHeaders} />
          </div>
        </div>

        <table>
          <thead>
            <tr>
              <th>Quote Number</th>
              <th>Insured Name</th>
              <th>Insured State</th>
              <th>Product</th>
              <th>Premium</th>
              <th>Effective Date</th>
              <th>Class Code</th>
              <th>Liability Limit</th>
              <th>Submission Date</th>
              <th>Indication Date</th>
            </tr>
          </thead>

          <tbody>
            {itemRows}
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
export default QuickIndicationDetails;