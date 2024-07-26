import axios from 'axios';
import { Component } from 'react';
const cabinetURL = process.env.REACT_APP_CabinetDownload_URL;
const cabinetDocURL = process.env.REACT_APP_CabinetDocDownload_URL;
const cabinetFileDetailsURL = process.env.REACT_APP_cabinetFileDetails_URL;
const cabinateFileDownloadURL = process.env.REACT_APP_FileDownload_URL;
const allCabinetFilesURL = process.env.REACT_APP_AllCabinetFileDetails_URL;
class DocumentService extends Component {

    getCabinetFileDetails(data, headers) {
        return axios.get(cabinetFileDetailsURL+data, {
            headers: headers,
        });
    }

    getCabinetData(headers) {
        return axios.get(cabinetURL,{
            headers: headers,
        });
    }
    getCabinateFileDownload(data,headers) {
        return axios.get(cabinateFileDownloadURL+data,{
            headers: headers,responseType: 'blob'
        });
    }

    getAllCabinetFiles(headers) {
        return axios.get(allCabinetFilesURL,{
            headers: headers,
        });
    }
}
export default new DocumentService()