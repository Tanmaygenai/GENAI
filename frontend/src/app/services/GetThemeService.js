import axios from "axios";
import { Component } from "react";
const themeUrl = `${process.env.REACT_APP_GET_THEME}`;

class GetThemeService extends Component {
  getTheme(headers) {
    return axios.get(themeUrl, {
        headers: headers,
    });
  }
}
export default new GetThemeService();
