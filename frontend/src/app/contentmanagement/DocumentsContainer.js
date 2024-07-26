import React, { useEffect, useState } from 'react'
import { useForm } from "react-hook-form"
import Layout from "../../layout/Layout"
import { CountryName } from '../../dummydata/CountryData';
import { useHistory } from 'react-router-dom';
// import loading from '../../assets/img/Loading.gif'
import DropDown from '../../components/dropdown/DropDown';
import { formFunctions } from '../../utility/jqueryFunctions';
import $ from "jquery"
import { saveAs } from 'file-saver';
import API_Headers from '../../API_Headers';
import DocumentService from '../services/DocumentService';
import FormControl from '../../components/formcontrol/FormControl';

const DocumentsContainer = () => {
    const CountryData = CountryName.map(val => val.name)
    const { register, handleSubmit, reset, formState: { errors }, setValue, control, getValues } = useForm();
    const history = useHistory();
    const cabinetURL = process.env.REACT_APP_CabinetDownload_URL;
    const cabinetDocURL = process.env.REACT_APP_CabinetDocDownload_URL;
    const cabinetFileDetailsURL = process.env.REACT_APP_cabinetFileDetails_URL;
    const cabinateFileDownloadURL = process.env.REACT_APP_FileDownload_URL;
    const [cabinetData, setCabinetData] = useState([]);
    const [fileData, setFileData] = useState([]);
    const [selectedData, setselectedData] = useState("");
    const [isLoaded, setIsLoaded] = useState(false);
    const [error, setError] = useState(null);
    const [headers, setHeaders] = useState([])
    const [files, setFiles] = useState([])
    const [searchInput, setSearchInput] = useState('');
    const [filteredResults, setFilteredResults] = useState([]);
    
    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

    useEffect(() => {
        if (headers.length !== 0) {
            formFunctions()
            const loggedInUser = sessionStorage.getItem("userName");
            if (!loggedInUser) {
                history.push("/");
            }

            loadCabinet();
            $('#classificationOfObjects').on('change', function () {
                setselectedData($(this).val());
                //start for calling to get file details as per classification name
                // alert("value is"+$(this).val())
                getFileDetails($(this).val());
                //end for calling to get file details as per classification name//
            });
            getFiles();
        }
    }, [headers])

    const getFiles = async () => {
        try {
            const responseData = await DocumentService.getAllCabinetFiles(headers);
            setFiles(responseData.data)
        } catch (error) {
            setError(error);
        }

    }

    const loadCabinet = async () => {
        try {
            
            const docsData = await DocumentService.getCabinetData(headers);
            console.log(docsData)
            setIsLoaded(true);
            if (docsData.data != "") {
                setCabinetData(docsData.data);
            }
        } catch (error) {
            setIsLoaded(true);
            setError(error);
        }
    };

    const getFileDetails = async (classification) => {
        try {
            const responseData = await DocumentService.getCabinetFileDetails(classification, headers)
            console.log(responseData)
            setIsLoaded(true);
            setFileData(responseData.data);
        } catch (error) {
            setIsLoaded(true);
            setError(error);
        }
    }

    let itemRows = [];

    if (fileData.length > 0) {
        fileData.map((item, ind) => {
            let row = (
                <tr key={Math.random()}>
                    <td>{ind + 1}</td>
                    <td class="text-break">{item.description}</td>
                    <td class="text-break">{item.name}</td>
                    <td key={Math.random()}><button type="button" onClick={() => callCabinetDocument(item.id, item.name)}>Download File</button></td>
                </tr>
            );
            itemRows.push(row);
        })
    }
    const callCabinetDocument = async (documentId, fileName) => {
        const docsData = await DocumentService.getCabinateFileDownload(documentId, headers);
        setTimeout(
            displayPDF(), 5000
        )
        function displayPDF() {
            var blob = new Blob([docsData.data], { type: "application/octet-stream" });
            saveAs(blob, fileName);
        }

    }

    const searchItems = (searchValue) => {
        
        setSearchInput(searchValue)
        if (searchInput !== ''){
            const filteredData = files.filter((item) => {
                // console.log(item.name)
                return item.name.toLowerCase().includes(searchInput.toLowerCase())
            })
            setFilteredResults(filteredData)
        }
        
}

let filteredRows = [];

        if (filteredResults.length > 0) {
            filteredResults.map((item, ind) => {
                let row = (
                    <tr key={Math.random()}>
                        <td>{ind + 1}</td>
                        <td class="text-break">{item.description}</td>
                        <td class="text-break">{item.name}</td>
                        <td key={Math.random()}><button type="button" onClick={() => callCabinetDocument(item.id, item.name)}>Download File</button></td>
                    </tr>
                );
                filteredRows.push(row);
            })
    }
    return (

        <Layout TabName='Content Management' BreadCrum={['My Dashboard', 'Content Management']}>
            <div class="coverContainer">
                <div class="commonform_box">
                    <div class="inf">
                        <form class="allForm" >
                            <div class="grid2">
                                <div class="form-element form-select">
                                    <DropDown id='classificationOfObjects' fieldName='classificationOfObjects' label='Documents Classification' register={register} errors={errors} lableClass='form-label' activeBold={true}

                                    >
                                        <option value=""></option>
                                        {cabinetData.map((value, ind) => (
                                            <option value={value}>{value}</option>
                                        ))}
                                    </DropDown>
                                </div>
                                <div class="grid2">
                                    <div class="form-element">
                                        <FormControl fieldName="producerCode" onChange={(e) => searchItems(e.target.value)} id='producerCode' label='Search filename' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={true} lableClass='form-label' activeBold={false} />
                                    </div>
                                    {/* <div class="form-element">
                                        <button type="submit" className='btn blue' id="">Search</button>
                                    </div> */}
                                </div>
                            </div>

                            {
                                selectedData !== "" &&

                                <div class="grid1">
                                    <table class="table table-hover">
                                        <thead class="thead-light">
                                            <tr class="table-primary">
                                                <th scope="col">#</th>
                                                <th scope="col">Document Description</th>
                                                <th scope="col">File Name</th>
                                                <th scope="col">Download URL</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {itemRows}
                                        </tbody>
                                    </table>
                                </div>
                            }
                            {
                                searchInput.length > 0 &&

                                <div class="grid1">
                                    <table class="table table-hover">
                                        <thead class="thead-light">
                                            <tr class="table-primary">
                                                <th scope="col">#</th>
                                                <th scope="col">Document Description</th>
                                                <th scope="col">File Name</th>
                                                <th scope="col">Download URL</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {filteredRows}
                                        </tbody>
                                    </table>
                                </div>
                            }

                        </form>

                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default DocumentsContainer