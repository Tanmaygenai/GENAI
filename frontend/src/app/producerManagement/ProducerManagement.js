import React, { useEffect, useState } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import FormControl from "../../components/formcontrol/FormControl"
import DropDown from '../../components/dropdown/DropDown';
import { drop_data } from '../../dummydata/data';
import LoginService from '../services/LoginService';
import classNames from 'classnames';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { Link, useHistory } from 'react-router-dom';
import ProducerManagementService from '../services/ProducerManagementService';
import API_Headers from '../../API_Headers';
import Papa from 'papaparse';
import dropdown_val from '../../dummydata/admin.csv';
const ProducerManagement = () => {

    const [rowData, setRowData] = useState([]);
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [response, setResponse] = useState({})
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
    useEffect(() => {
        if(headers.length !== 0){
        if (rowData.length > 0) {
            onSearch();
        }
    }
    }, [headers])

    const onSearch = async (data) => {
        setResponse(data)
        try {
            const responseData = await ProducerManagementService.getProducerInfo(data, headers);
            setRowData(responseData.data);
        }
        catch (error) {
            alert(error);
        }
    };

    return (
        <Layout TabName='Producer Management' BreadCrum={['My Dashboard', 'Admin', 'Producer Management',]}>
            <div class="commonform_box mt-3">
                <div class="inf">
                    <form class="allForm" onSubmit={handleSubmit(onSearch)}>
                        <div class="fullhead">Producer Management</div>
                        <br />
                        <div>Search For:</div>
                        <div class="grid4">
                            <div className='form-element form-select'>
                                <label className='form-label'> Where </label>
                                <select id='autoProducerTypeSelect'
                                    name="autoProducerTypeSelect"
                                    size="sm"
                                    {...register('autoProducerTypeSelect', {
                                        required: 'Producer Type is required',
                                    })}
                                    className={classNames(" form-field form-control", {
                                        "is-invalid": errors.autoProducerTypeSelect,
                                    })}
                                >
                                    {dropdownVal.map((value, ind) => {
                                        return value.producerSearch !== "" && value.producerSearch !== null && value.producerSearch !== undefined ? (
                                            <option value={value.producerSearch === "-select-" ? "" : value.producerSearch}>{value.producerSearch}</option>
                                        ) :
                                            <></>
                                        }
                                        )}
                                </select>
                                {errors.IsAutoClaim && (
                                    <div className="invalid-feedback">
                                        {errors.IsAutoClaim.message}
                                    </div>
                                )}
                            </div>
                            <div className="grid1">
                                <div class="form-element">
                                    <FormControl fieldName="producerCode" label='Contains' id='producerCode' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={true} />
                                </div>
                            </div>
                            <div class="form-element">
                                <button type="submit" className='btn blue' id="">Search</button>
                            </div>
                            <div class="form-element" style={{ display: 'flex', alignContent: 'end' }}>
                                <button type="button" className='btn blue' id="" >
                                    <li><Link to={{ pathname: '/createProducer' }}><u>New Producer</u></Link></li></button>
                            </div>
                        </div>
                        <br />


                        <table class='table table-hover' >
                            <thead class="thead-light">
                                <tr class="table-primary">
                                    <th scope="col">Producer Code</th>
                                    <th scope="col">Agency Code</th>
                                    <th scope="col">Group Code</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Email</th>
                                </tr>
                            </thead>
                            {rowData.length > 0 ?
                                <tbody>
                                    {rowData.map((item, index) => (
                                        <tr>
                                            <td>{item.producer_code}</td>
                                            <td>{item.agency_code}</td>
                                            <td>{item.group_code}</td>
                                            <td>{item.name}</td>
                                            <td>{item.email}</td>
                                        </tr>
                                    ))}
                                </tbody> :
                                <tbody>
                                    <tr>Empty List</tr>
                                </tbody>}
                        </table>
                    </form>
                </div>
                <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
            </div>
        </Layout>
    );
};

export default ProducerManagement;
