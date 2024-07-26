import axios from "axios";
import { Component } from "react";
const ticketURL = process.env.REACT_APP_FETCH_TICKET_URL;

class GetTicketDetailsService extends Component {
  getTicketDetails(data, headers) {
    return axios.get(ticketURL, {
        headers: headers,
        params: data
    });
  }
}
export default new GetTicketDetailsService();
