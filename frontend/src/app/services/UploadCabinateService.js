import { Component } from 'react';

import axios from 'axios';
const Document_Upload_URL = process.env.REACT_APP_Cabinate_Document_Upload_URL;
class UploadCabinateService extends Component {

    attachDocument1(data, headers) {
        
        return axios.post(Document_Upload_URL, data, {
            headers: headers
        });
    }
}
export default new UploadCabinateService()