import React, { useState } from "react";
import Layout from "../../layout/Layout";
import Stepper from "../stepper/Stepper";
import { useEffect } from "react";
import { useHistory, useLocation } from "react-router-dom";
import ExcessQuickIndicationService from "../services/ExcessQuickIndicationService";
import { ExcessLiablityResopnseJson } from "../../utility/responseJsonExcessLiability";
import CryptoJS from "crypto-js";
import { StateName } from '../../dummydata/StateList'
import API_Headers from "../../API_Headers";
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import useLoader from '../../context/loader'

const ExcessLiability = () => {
  const {setLoader} = useLoader()
  const { state } = useLocation();
  const secretPass = process.env.REACT_APP_Secret_Key;
  const steps = ["Quote and Insured Information", "Effective Date & Location", "Risk Information"];
  const history = useHistory();
  var { quoteId } = useLocation();
  const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
  const [headers, setHeaders] = useState([])

  useEffect(() => {
    API_Headers().then((val) => setHeaders(val))
  }, [])
  function getStateCode(stateName) {
    const state = StateName.find(obj => obj.name == stateName);
    if (state) {
      return state.code;
    } else {
      return null;
    }
  }
  const onSubmit = async (data) => {
    setLoader(true)
    data = {
      ...data,
      "busState": getStateCode(data.busState),
      "State": getStateCode(data.State)
    }
    // quick indication DRC
    try {
      const responseJson = await ExcessLiablityResopnseJson(data, state, quoteId)
      const data1 = CryptoJS.AES.encrypt(
        JSON.stringify(responseJson),
        secretPass
      ).toString();
      const premiumData = await ExcessQuickIndicationService.getPremiumDetails(
        data1,
        headers
      );
      setLoader(false)
      if (premiumData.data.message === "" || premiumData.data.message === null || premiumData.data.message === undefined) {
        history.push({
          pathname: "/premiumDataMsg",
          state: { detail: premiumData.data, responseJson: responseJson }
        });
      } else {
        setErrorModal({ show: true, title: "Error", body: premiumData.data.message })
      }
    } catch (error) {
      setLoader(false)
      if (error == "Error: Network Error") {
        history.push("/SystemMaintenance");
      } else {
        alert(error);
      }
    }
    //edn for quick indication DRC
  };
  useEffect(() => {
    const loggedInUser = sessionStorage.getItem("userName");
    if (!loggedInUser) {
      history.push("/");
    }

  }, [])
  return (
    <Layout
      TabName="Commercial Excess Liability"
      BreadCrum={["My Dashboard", "Quick Quote", "Commercial Excess Liability"]}
      sidebarQQSopen={true}
    >
      <Stepper
        state={state}
        steps={steps}
        TabName="Commercial Excess Liability"
        onSubmit={onSubmit}
      />
      <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
    </Layout>
  );
};

export default ExcessLiability;
