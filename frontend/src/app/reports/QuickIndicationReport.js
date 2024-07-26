import React, { useEffect } from 'react'
import { useForm } from "react-hook-form";
import { useState } from 'react';
import $, { event } from "jquery"
import { useHistory, useLocation } from 'react-router-dom';
import DropDown from '../../components/dropdown/DropDown';
import DateInput from "../../components/dateinput/DateInput"
import Layout from "../../layout/Layout"
import classNames from 'classnames';
import { fileFunction, formFunctions } from '../../utility/jqueryFunctions';
import FormControl from "../../components/formcontrol/FormControl";
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import QuickIndicationDetails from "./QuickIndicationDetailsForUser";
import API_Headers from '../../API_Headers';
import Papa from 'papaparse';
import dropdown_val from '../../dummydata/lossNotice.csv';

const QuickIndicationReport = () => {
  const { register, handleSubmit, reset, formState: { errors }, setValue, control, getValues } = useForm();
  const [state, setState] = useState("")
  const history = useHistory();
  const { data } = useLocation();
  const [isLoaded, setIsLoaded] = useState(false);
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [productType, setProductType] = useState('');
  const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
  const [headers, setHeaders] = useState([])
  const [dropdownVal, setDropdownVal] = useState([]);
  var commonConfig = { delimiter: "," };

  useEffect(() => {
    Papa.parse(
      dropdown_val,
      {
        ...commonConfig,
        header: true,
        download: true,
        complete: (result) => {
          setDropdownVal(result.data)
        }
      }
    );
  }, [])
  useEffect(() => {
    API_Headers().then((val) => setHeaders(val))
  }, [])

  const clearIsLoaded = (data) => {
    setIsLoaded(false);
  }

  const onSubmit = async (data) => {
    clearIsLoaded();
    setIsLoaded(true);
  };



  useEffect(() => {

    $('#productSelect').on('change', function () {
      setProductType($(this).val());

    });
  }, []);



  useEffect(() => {
    const loggedInUser = sessionStorage.getItem("userName");
    if (!loggedInUser) {
      history.push("/");
    }
    fileFunction();
  }, [])

  return (
    <Layout TabName='Quick Indication/Submission Report' BreadCrum={['My Dashboard', 'Quick Indication/Submission Reports']}>
      <div className="coverContainer">
        <div class="commonform_box">
          <div class="inf">
            <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
              <div className="grid2">
                <div>
                  <DateInput
                    fieldName="Report Start Date" label='Start Date' register={register} errors={errors}
                    required={true} id='startDate' setValue={setValue}
                    yearRange="-0:+60" defaultValue={data ? data.state.startDate : ""} setState={setStartDate}
                  />
                </div>
                <div>
                  <DateInput
                    fieldName="Report End Date" label='End Date' register={register} errors={errors}
                    required={true} id='endDate' setValue={setValue}
                    yearRange="-0:+60" defaultValue={data ? data.state.endDate : ""} setState={setEndDate} />
                </div>

                <div className='form-element form-select'>
                  <DropDown fieldName='product' id='productSelect' label='Product' required={false} register={register} errors={errors} lableClass='form-label' activeBold={true}>
                    {dropdownVal.map((value, ind) => {
                      return value.ProductType !== "" && value.ProductType !== null && value.ProductType !== undefined ? (
                        <option value={value.ProductType === "-select-" ? "" : value.ProductType}>{value.ProductType}</option>
                      ) :
                        <></>
                    }
                    )}
                  </DropDown>
                </div>
                <br />
                <div className="form-element-btn">
                  <button type="submit" class="btn blue" id="" onClick={handleSubmit(onSubmit)}>Generate Report</button>
                </div>
              </div>
            </form>
          </div>
        </div>
        <br />
        <br />
        <div>
          {
            (isLoaded === false) ? <div class="coverContainer"> </div> :
              <QuickIndicationDetails startDate={startDate} endDate={endDate} productType={productType} />
          }
        </div>
        <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
      </div>
    </Layout>


  );
}

export default QuickIndicationReport
