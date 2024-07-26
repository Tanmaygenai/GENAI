import axios from 'axios';
import { Component } from 'react';
const closeTicketServiceURL = process.env.REACT_APP_CLOSE_TICKET_URL;
class CloseTicketService extends Component {

  closeTicket(data, headers) {
    return axios.post(closeTicketServiceURL, data, {
      headers: headers
    });
  }
}
export default new CloseTicketService()