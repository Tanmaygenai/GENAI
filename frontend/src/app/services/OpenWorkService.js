import axios from 'axios';
import { Component } from 'react';
const openWorkDBURL=  process.env.REACT_APP_OPEN_WORK_ITEM_DB_URL;
const submitWorkURL=  process.env.REACT_APP_SUBMIT_WORK_ITEM_URL;
const deleteWorkURL= process.env.REACT_APP_DELETE_WORK_ITEM_URL;
const policyDBURL=  process.env.REACT_APP_POLICY_ITEM_DB_URL;
const lossNoticeDBURL= process.env.REACT_APP_LOSS_NOTICE_ITEM_DB_URL;
const getWorkURL="http://127.0.0.1:5000/api/submission_list";
const getsubmissionurl="http://127.0.0.1:5000/api/generate_data";

class OpenWorkService extends Component {
    getOpenWorkDBItem(role,headers){
        return axios.post(openWorkDBURL,role,{
          headers: headers
        } )
     }

     getPolicyDBItem(role,headers){
      return axios.post(policyDBURL,role,{
        headers: headers
      } )
   }

    submitOpenWorkItem(appNumber,headers){
      return axios.get(submitWorkURL,{
               headers: headers,
               params: {appNumber: appNumber}
      } )
    } 

    deleteOpenWorkItem(appNumber,headers){
      return axios.get(deleteWorkURL,{
               headers: headers,
               params: {appNumber: appNumber}
      } )
    } 

    getLossNoticeDBItem(role,body,headers){
      return axios.post(lossNoticeDBURL,role,{
        headers: headers
       
   })}
   getOpenWorkItemdata(){
    return axios.get(getWorkURL)
  } 
  
  getSumissionData(body){
    return axios.get(getsubmissionurl,{
      params: {submission_id: body.submission_id,file_name: body.file_name} ,
 
  

 })}
 
}export default new OpenWorkService()