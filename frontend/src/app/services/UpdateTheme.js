import axios from 'axios';
import { Component } from 'react';

const updateTheme= process.env.REACT_APP_UPDATE_THEME
class UpdateThemeService extends Component{
    updateTheme(data, headers) {
        return axios.post(updateTheme, data, {
            headers: headers,
        });
    }
}
export default new UpdateThemeService()