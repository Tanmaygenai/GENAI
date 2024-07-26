import axios from 'axios';
import { Component } from 'react';

const uploadUrl= process.env.REACT_APP_POST_LOGO
class UploadLogoService extends Component{
    uploadLogo(data, headers) {
        const formdata= new FormData()
        formdata.append("file", data)
        return axios.post(uploadUrl,formdata, {
            headers: headers,
        });
    }
}
export default new UploadLogoService()