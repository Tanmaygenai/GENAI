import React, { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import Layout from "../../layout/Layout";
import API_Headers from "../../API_Headers";
import GetTicketDetailsService from "../services/GetTicketDetails";
import { Pagination } from "react-bootstrap";
import CreateTicket from "../../components/modals/popupmodal/CreateTicketModal";
import useLoader from "../../context/loader"
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';

export const Support = () => {
  const {setLoader} = useLoader()
  const navigate = useHistory();
  const [headers, setHeaders] = useState([]);
  const [ticketData, setTicketData] = useState([]);
  // const [isLoaded, setIsLoaded] = useState(false);
  const [showModal, setShowModal] = useState(false)
  const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });

  useEffect(() => {
    API_Headers().then((val) => setHeaders(val));
  }, []);

  useEffect(async () => {
    if (Object.keys(headers).length > 0) {
      try {
      // setIsLoaded(false)
      setLoader(true)
      const payload = {
        role: sessionStorage.getItem("roleType")
      };
      const response = await GetTicketDetailsService.getTicketDetails(
        payload,
        headers
      );
      if(response.status === 200){
          setTicketData(response.data);
          setLoader(false)
      }
    }
  catch (error) {
    setLoader(false)
    if(error.response.status === 404){
      setErrorModal({ show: true, title: "Error", body: "Username not found in mappings list" })
    } else {
      setErrorModal({ show: true, title: "Error", body: error.toString() })
    }
}
    }
  }, [headers]);

  const truncateString = (str, maxLength) => {
    if (str.length > maxLength) {
      return str.split(' ').slice(0, maxLength).join(' ') + '...';
    } else {
      return str;
    }
  };

  const handleTicketInfo = (id) => {
    sessionStorage.setItem('ticketId', id)
    navigate.push(`/support/${id}`);
  };

  const [activePage, setActivePage] = useState(1);

  const itemRows = [];
  var noOfRowsPerPage = 10;
  var noOfPagesToDisplay = 25;
  var ind = 0;

  if(ticketData.length > 0) {
    var row;
    for (let item of ticketData) {
      if (activePage === parseInt(ind / noOfRowsPerPage) + 1) {
        var date = new Date(item.createdDate);
        const truncatedSubject = truncateString(item.message, 3);
        const fullSubject = item.message;
        if(sessionStorage.getItem("roleType") === "admin"){
          row= <tr key={1}>
            {/* </tr><tr key={item.caseId}> */}
              <td key={2}>{(date).getFullYear()+'-'+('0' + ((date).getMonth() + 1)).slice(-2)+'-'+ ('0' + (date).getDate()).slice(-2)}</td>
              <td key={2}>{item.agencyName}</td>
              <td style={{cursor: 'pointer', color:'blue', textDecoration:'underline'}} onClick={() => handleTicketInfo(item.caseId)}key={2}>{item.caseId}</td>
              <td key={2}>{item.email}</td>
              <td title={fullSubject} className="hoverableCell" key={4}>{truncatedSubject}</td>
              <td key={5}>{item.category}</td>
              <td key={6}>{item.severity}</td>
              <td key={6}>{item.status}</td>
              <td key={6}>Admin</td>
            </tr>
        }else{
          row= <tr key={1}>
              <td key={2}>{(date).getFullYear()+'-'+('0' + ((date).getMonth() + 1)).slice(-2)+'-'+ ('0' + (date).getDate()).slice(-2)}</td>
              <td style={{cursor: 'pointer', color:'blue', textDecoration:'underline'}} onClick={() => handleTicketInfo(item.caseId)}key={2}>{item.caseId}</td>
              <td title={fullSubject} className="hoverableCell" key={4}>{truncatedSubject}</td>
              <td key={5}>{item.category}</td>
              <td key={6}>{item.severity}</td>
              <td key={6}>{item.status}</td>
              <td key={6}>Admin</td>
            </tr>
        }
        itemRows.push(row);
      }
      ind++;
    }
  }

  let items = [];
  for (
    let number = 1;
    number - 1 - 1 / 10 <= ticketData.length / 10 && number <= noOfPagesToDisplay;
    number++
  ) {
    items.push(
      <Pagination.Item
        key={number}
        active={number === activePage}
        onClick={() => setActivePage(number)}
      >
        {number}
      </Pagination.Item>
    );
  }

  return (
    <Layout TabName='Support' BreadCrum={['My Dashboard', 'Support']}>
      <div className="tasklistpage-bg">
      <div className="tabContent extraWidth tasklist_table">
      {
          sessionStorage.getItem("roleType") === "admin"?
          []
          :
          <div className="textRight">
          <button onClick={() => navigate.push({ pathname: "/contactUs/" })} className="btn blue btn btn-primary" >Create Ticket</button>
        </div>
      }
        <table>
          <thead>
            {
            sessionStorage.getItem("roleType") === "admin" ? 
              <tr>
                <th>Created Date</th>
                <th>Agency Name</th>
                <th>Case Id</th>
                <th>Opened By</th>
                <th>Subject</th>
                <th>Category</th>
                <th>Severity</th>
                <th>Status</th>
                <th>Closed By</th>
              </tr>
            :
              <tr>
                <th>Created Date</th>
                <th>Case Id</th>
                <th>Subject</th>
                <th>Category</th>
                <th>Severity</th>
                <th>Status</th>
                <th>Closed By</th>
              </tr>
            }
          </thead>

          <tbody>
            {
            // !isLoaded ? (
            //   <div className="coverContainer">
            //     <img src={loading} alt="Loading..." />
            //   </div>
            // ) : 
            (
              itemRows
            )}
          </tbody>
        </table>
        <br />
        <Pagination className="paginationtabledata" size="sm">
          {items}
        </Pagination>
      </div>
      </div>
      <CreateTicket showModal={showModal} setShowModal={setShowModal} />
      <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
    </Layout>
  );
}
// export default Support;
