import React, { useEffect } from 'react'
import { useForm } from "react-hook-form";
import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import Layout from "../../layout/Layout"
import useLoader from "../../context/loader"
import FormControl from "../../components/formcontrol/FormControl";
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import SuccessMessageModal from '../../components/modals/popupmodal/SuccessMessageModal';
import API_Headers from '../../API_Headers';
import dropdown_val from '../../dummydata/admin.csv';
import ApplicationPropertiesService from '../services/ApplicationPropertiesService';
import Papa from 'papaparse';
import DropDown from '../../components/dropdown/DropDown';
import classNames from 'classnames';
const ApplicationProperties = () => {
    const { setLoader } = useLoader();
    const { register, handleSubmit, reset, formState: { errors }, setValue, control, getValues } = useForm();
    const history = useHistory();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [isLoaded, setIsLoaded] = useState(false)
    const [systemId, setSystemId] = useState('');
    const [headers, setHeaders] = useState([])
    const [dropdownVal, setDropdownVal] = useState([]);
    const [modalData, setModalData] = useState({ show: false, title: "", body: "" });
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



    const onSubmit = async (data) => {
        try{
        setLoader(true)
        const carrierMode = data.CarrierMode;
        const res = await ApplicationPropertiesService.updateCarrierMode(carrierMode, headers)
        setLoader(false)
        console.log(res)
        if (res.status === 200) {
            setModalData({
                title: 'Success',
                show: true,
                body: 'Carrier Mode has been updated successfully.'
            })
        }
        //clearIsLoaded(data);
        //setSystemId(data.systemId)
    }
    catch (error) {
        setLoader(false)
        setErrorModal({ show: true, title: "Error", body: error.toString() })
    }
    };

    const handleBack = () => {
        history.push({
            pathname: "/admin"
        });
    }


    return (
        <Layout TabName='Application Properties' BreadCrum={['My Dashboard', 'Admin', 'Application Properties']}>
            <div className="coverContainer">
                <div class="commonform_box">
                    <div class="inf">
                        <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                        <div className="fullhead">Application Properties</div><br />
                            <br />
                            <div className="grid3">
                                <div class="form-element">
                                <div className="form-element form-select">
              {dropdownVal.length > 0 ? (
                  <DropDown
                      //defaultValue={state.indicationData ? JSON.parse(state.indicationData).application.locations[0].addressType : ""}
                      fieldName="CarrierMode"
                      label="Carrier Mode"
                      register={register}
                      setValue={setValue}
                      errors={errors}
                      lableClass="form-label"
                      activeBold={false}
                      showLabel={true}
                  >
                      {dropdownVal.map((value, ind) => {
                          return value.CarrierMode !== "" && value.CarrierMode !== null && value.CarrierMode !== undefined ? (
                              <option value={value.CarrierMode === "-select-" ? "" : value.CarrierMode}>
                                  {value.CarrierMode}
                              </option>
                          ):
                          <></>
                          
                      })}
                  </DropDown>
              ) : null}
              {errors.CarrierMode && (
                <div className="invalid-feedback">
                  {errors.CarrierMode.message}
                </div>
              )}
            </div>
                                </div>
                                <br />
                                <div className="form-element-btn">
                                    <button type="submit" class="btn blue" id="">Save</button>
                                </div>
                                <div className="form-element-btn-left">
                                    <button type="submit" class="btn blue" id="" onClick={handleBack}>Back</button>
                                </div>
                            </div>
                            
                        </form>
                    </div>
                </div>
                <br />
                <br />
                <br />
                <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
                <SuccessMessageModal showModal={modalData.show} modalTitle={modalData.title} modalBody={modalData.body} setShowModal={setModalData} />
            </div>
        </Layout>

    );
}

export default ApplicationProperties
