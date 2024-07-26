import axios from "axios";
import { Component } from "react";
const logoUrl = `${process.env.REACT_APP_GET_LOGO}`;

class GetLogoService extends Component {
  getLogo(headers) {
    return axios.get(logoUrl, {
        headers: headers,
    });
  }
}
export default new GetLogoService();
