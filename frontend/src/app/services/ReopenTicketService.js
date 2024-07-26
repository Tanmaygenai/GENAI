import axios from 'axios';
import { Component } from 'react';
const reopenTicketServiceURL = process.env.REACT_APP_REOPEN_TICKET_URL;
class ReopenTicketService extends Component {

  reopenTicket(data, headers) {
    return axios.post(reopenTicketServiceURL, data, {
      headers: headers
    });
  }
}
export default new ReopenTicketService()