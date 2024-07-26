import { Component } from 'react';
import axios from 'axios';
const Document_Upload_URL=  process.env.REACT_APP_Document_Upload_URL;
const Excel_Upload_URL= process.env.REACT_APP_Excel_Upload_URL;
const acordUrl= process.env.REACT_APP_Acord_URL
class UploadService extends Component {
   
    attachDocument(data,headers){
     return axios.post(Document_Upload_URL,data,{
        headers: headers
      });
    }

    uploadPdf(data, headers) {
      return axios.post(acordUrl, data, {
          headers: headers,
      });
  }

  uploadExcel(data, headers) {
    return axios.post(Excel_Upload_URL, data, {
        headers: headers,
    });
  }
    }
    export default new UploadService()