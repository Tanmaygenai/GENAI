import { useState } from 'react';
import { useEffect } from 'react';
import { Pagination } from 'react-bootstrap';
import OpenWorkService from '../../services/OpenWorkService';
import ErrorMessageModal from '../../../components/modals/popupmodal/ErrorMessageModal';
import { Link, useHistory, useLocation } from 'react-router-dom';
import ComAutoService from '../../services/ComAutoService';
import ExcessService from '../../services/ExcessService';
import GenerateQuoteModal from '../../../components/modals/popupmodal/GenerateQuoteModal';
import DeleteQuoteModal from '../../../components/modals/popupmodal/DeleteQuoteModal';
import API_Headers from '../../../API_Headers';
import CryptoJS from "crypto-js";
import useLoader from "../../../context/loader"

const PerformenceStaticsTableOpenWork = ({ openWorkDBItem, setOpenWorkDBItem }) => {
  const secretPass = process.env.REACT_APP_Secret_Key;
  const {setLoader} = useLoader()
  // const { state } = useLocation();
  const [loaderData, setLoaderData] = useState({ show: false });
  const history = useHistory();
  // var responseJson = state.responseJson;
  const openWorkItemData = useState([]);
  // var policyType = responseJson.application.policyInfo.policyType
  const [generateQuote, setGenerateQuote] = useState({ show: false, appNumber: null });
  const [deleteQuote, setDeleteQuote] = useState({ show: false, appNumber: null });
  const [indicationStatus, setIndicationStatus] = useState()
  const [activePage, setActivePage] = useState(1)
  const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
  const [headers, setHeaders] = useState([])

  useEffect(() => {
    setLoader(true)
    API_Headers().then((val) => setHeaders(val))
  }, [])

  const submitOpenWork = async (appNumber) => {
    const data1 = CryptoJS.AES.encrypt(
      JSON.stringify(appNumber),
      secretPass
    ).toString();
    try {
      setLoaderData({ show: true })
      const responseData = await OpenWorkService.submitOpenWorkItem(data1, headers);
      if (responseData.data.includes('Error')) {
        setErrorModal({ show: true, title: "Error Message", body: responseData.data })
      } else {
        loadOpenWorkItems();
        setErrorModal({ show: true, title: "Success Message", body: "Quote Number Generated :: " + responseData.data })
      }

    } catch (error) {
      setLoaderData({ show: false })
    }
    setLoaderData({ show: false })
  }

  const deleteOpenWork = async (appNumber) => {
    const data1 = CryptoJS.AES.encrypt(
      JSON.stringify(appNumber),
      secretPass
    ).toString();
    try {
      setLoaderData({ show: true })
      const responseData = await OpenWorkService.deleteOpenWorkItem(data1, headers);
      if (responseData.data.includes('Error')) {
        setErrorModal({ show: true, title: "Error Message", body: responseData.data })
      } else {
        setErrorModal({ show: true, title: "Success Message", body: "Record deleted successfully! " + responseData.data })
        loadOpenWorkItems();
      }

    } catch (error) {
      setLoaderData({ show: false })
    }
    setLoaderData({ show: false })
  }

  useEffect(() => {
    if (headers.length !== 0) {
      loadOpenWorkItems();
    }
  }, [headers]);

  const loadOpenWorkItems = async () => {
    try {
      const role = sessionStorage.getItem("roleType");
      const openWorkItemDBData = await OpenWorkService.getOpenWorkDBItem(role, headers);
      // setIsLoaded(true);
      setLoader(false)
      openWorkItemDBData.data.sort(function(a,b){
        return new Date(b.indicationDate) - new Date(a.indicationDate);
      });
      setOpenWorkDBItem(openWorkItemDBData.data)
    } catch (error) {
      // setIsLoaded(true);
      setLoader(false)
      setErrorModal({ show: true, title: "Network Error", body: "Unable to load the details" })

    }
  };

  const itemRows = [];
  var noOfRowsPerPage = 10;
  var noOfPagesToDisplay = 25;
  var ind = 0
  for (let item of openWorkDBItem) {
    if (activePage === parseInt(ind / noOfRowsPerPage) + 1) {
      const id = item.id;
      var linkPath = "";
      if (item.product === "Commercial Auto") {
        linkPath = "/commercialauto"
      }
      else if (item.product === "Commercial Excess Liability") {
        linkPath = "/excessliability"
      }
      else if (item.product === "Commercial Property") {
        linkPath = "/commercialProperty"
      }
      var displayQuote = ''
      if (item.quoteNumber.includes('QT')) {
        displayQuote = 'Yes'
      } else {
        displayQuote = 'No'
      }
      const row = (
        <tr key={1}>
          {(displayQuote == 'Yes') ? <td>{item.quoteNumber}</td> : <td>
            {/* <Link to={{ pathname: linkPath, state: JSON.parse(item.indicationDataJson), quoteId: item.id }}> */}
            INDICATION-{id}
            {/* </Link>  */}
          </td>}
          <td key={2}>{item.effectiveDate}</td>
          <td key={2}>{item.stateCd}</td>
          <td key={4}>{item.insuredName}</td>
          <td key={5}>{item.indicationDate}</td>
          <td key={6}>{item.product}</td>
          {(displayQuote == 'Yes') ? <td key={Math.random()}><button onClick={() => setDeleteQuote({ show: true, appNumber: id })} class="btn blue" type='button'>Delete</button></td> :
            <td key={6}><button class="btn blue" onClick={() => setGenerateQuote({ show: true, appNumber: id })} type='button'>Submit</button></td>}
        </tr>
      );
      itemRows.push(row);
    }
    ind++;
  }

  let items = [];
  for (let number = 1; ((number - 1 - (1 / 10)) <= (openWorkDBItem.length / 10) && number <= noOfPagesToDisplay); number++) {
    items.push(
      <Pagination.Item key={number} active={number === activePage} onClick={() => setActivePage(number)}>
        {number}
      </Pagination.Item>,
    );
  }
  return (
    (openWorkDBItem.length > 0) ?
      <div class="tabContent extraWidth" style={{ display: "block" }}>
        <table>
          <thead>
            <tr>
              <th>App/Policy</th>
              <th>Eff Date</th>
              <th>State</th>
              <th>Insured Name</th>
              <th>Indication Date</th>
              <th>Product</th>
              <th>Action</th>
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
        <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
        <GenerateQuoteModal showModal={generateQuote} setShowModal={setGenerateQuote} submitOpenWork={submitOpenWork} />
        <DeleteQuoteModal showModal={deleteQuote} setShowModal={setDeleteQuote} deleteOpenWork={deleteOpenWork} />
      </div>
      : <div class="tabContent extraWidth" style={{ display: "block", color: '#CF0918' }}><p>No rows to display</p></div>
  )
}
export default PerformenceStaticsTableOpenWork;