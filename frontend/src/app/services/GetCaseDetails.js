import axios from "axios";
import { Component } from "react";
const ticketURL = process.env.REACT_APP_FETCH_TICKET_DETAILS;

class GetCaseDetailsService extends Component {
  getCaseDetails(data, headers) {
    return axios.get(ticketURL, {
        headers: headers,
        params: data
    });
  }
}
export default new GetCaseDetailsService();
