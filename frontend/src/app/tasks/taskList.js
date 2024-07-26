import React, { useEffect,useState } from "react";
import { useHistory } from "react-router-dom";
import Layout from "../../layout/Layout";
import DataTable from "./taskTable";
import API_Headers from "../../API_Headers";
import TaskService from "../services/TaskService";
import useLoader from "../../context/loader"

const TaskList = () => {
  const {setLoader} = useLoader()
  const history = useHistory();

  const [headers, setHeaders] = useState([])
  const [rowData, setRowData] = useState([])
  // const [isLoaded, setIsLoaded] = useState(false);

  useEffect(() => {
    setLoader(true)
    const email = sessionStorage.getItem('email')
    API_Headers().then((val) => {
      const obj = {
        ...val,
        agentEmail: email
      }
      setHeaders(obj)
    })
  }, [])

  useEffect(() => {
    if(Object.keys(headers).length > 0) {
      const response = TaskService.getTaskData(headers)
      response.then((res) => {
        const taskData = res?.data
        setRowData(taskData)
        // setIsLoaded(true)
        setLoader(false)
      })
    }
    
  }, [headers])
  
  useEffect(() => {
    const loggedInUser = sessionStorage.getItem("userName");
    if (!loggedInUser) {
      history.push("/");
    }
  });
  return (
  
    <Layout TabName='Task' BreadCrum={['My Dashboard', 'Task']}>
      <div className="tasklistpage-bg">
        <DataTable rowData={rowData} />
      </div>
    </Layout>
  );
};

export default TaskList;
