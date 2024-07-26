import React, { useState, useEffect } from 'react';
import { useHistory } from "react-router-dom";
import API_Headers from '../../API_Headers';
import Layout from "../../layout/Layout"
import useLoader from "../../context/loader"
import { Pagination } from 'react-bootstrap';
import SuggestionService from '../services/SuggestionService';
const AgentSuggestion = () => {
  const history = useHistory();
  const [headers, setHeaders] = useState([])
  const { setLoader } = useLoader()
  const [activePage, setActivePage] = useState(1)
  const [suggestionData, setSuggestionData] = useState([])
  const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });

  useEffect(() => {
    setLoader(true)
    API_Headers().then((val) => setHeaders(val))
  }, [])
 
    useEffect(() => {
      if (headers.length !== 0) {
        suggestionDetails();
      }
    }, [headers]);

    const suggestionDetails = async () => {
      try {
        const responseData = await SuggestionService.getAgentSuggestion(headers);
        setSuggestionData(responseData.data);
        setLoader(false)
      } catch (error) {
        setLoader(false)
        setErrorModal({ show: true, title: "Network Error", body: "Unable to load the details" })

      }
    };

    const handleSuggestionDetails = (index, item) => {
    
      history.push('/suggestionDetails', item)
  }
  var noOfRowsPerPage = 10;
  var noOfPagesToDisplay = 25;
  var ind = 0
  const itemRows = [];

  suggestionData.map((item, index) => {
    if (activePage === parseInt(ind / noOfRowsPerPage) + 1) {
      const row = (
        <tr key={index}>
          <td key={0}>{item.userName}</td>
          <td key={1}>{item.suggestionDate}</td>
          <td key={2} style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }} onClick={() => handleSuggestionDetails(index, item)}>Click Here</td>
        </tr>
      );
      itemRows.push(row);
    }
    ind++;
  })

  let items = [];
    for (let number = 1; ((number - 1 - (1 / 10)) <= (suggestionData.length / 10) && number <= noOfPagesToDisplay); number++) {
      items.push(
        <Pagination.Item key={number} active={number === activePage} onClick={() => setActivePage(number)}>
          {number}
        </Pagination.Item>,
      );
    }
    
  return (
    <Layout TabName='Content Management' BreadCrum={['My Dashboard','Admin', 'Agent Suggestion']}>
      <div className="coverContainer">
        <div class="commonform_box">
          <div class="inf">
            <div className="fullhead">Agent Suggestion</div><br />
            <table>
              <thead>
                <tr>
                  <th>Name </th>
                  <th>Suggestion Date</th>
                  <th>Suggestion</th>
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
        </div>
      </div>
    </Layout>
  );
}

export default AgentSuggestion;
