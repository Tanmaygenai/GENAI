import axios from 'axios';
import { Component } from 'react';

const acordUrl= process.env.REACT_APP_Acord_URL
class TextractService extends Component{
    uploadPdf(data, headers) {
        return axios.post(acordUrl, data, {
            headers: headers,
        });
    }
}
export default new TextractService()