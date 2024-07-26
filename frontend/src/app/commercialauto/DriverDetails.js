import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import MultipleFile from "../../components/multiplefile/MultipleFile";
import UploadService from "../services/UploadService";
import API_Headers from "../../API_Headers";
import DriverTable from "../../components/tables/DriverTable";
import csvIcon from "../../assets/img/icons/csv.png";
import ExcelDetailsService from "../services/ExcelDetailsService";
import useLoader from "../../context/loader";
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import driverDetailsExcel from '../../dummydata/DriverDetailsExcel.xlsx';

const DriverDetails= ({modalState, openModal, setValue, getValues, setModalState,downloadTriggered,setDownloadTriggered }) => {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [multipleImages, setMultipleImages] = useState([]);
    const [headers, setHeaders] = useState([]);
    const [responseData, setResponseData] = useState(null);
    const [uploadCompleted, setUploadCompleted] = useState(false);
    const { setLoader } = useLoader();
    const [modalData, setErrorModal] = useState({ show: false, title: "", body: "" });

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

    const handleUpload = async (data) => {
        let payload = new FormData();
        for (let key of Object.keys(multipleImages)) {
            if (key !== 'length') {
                payload.append("file", multipleImages[key]);
            }
        }

        try {
            const response = await UploadService.uploadExcel(payload, headers);
            setResponseData(response.data);
            console.log("in driver details",modalState.DriverData);
            setModalState((prevState) => ({
        ...prevState,
        DriverData: [...prevState.DriverData, ...response.data],
      }));
      console.log("response data", response.data);
    } catch (error) {
      console.error("Error uploading file:", error);
    }
  };

  useEffect(() => {
    if (downloadTriggered) {
        console.log("trigger",downloadTriggered);
      handleDownload();
    }
    setDownloadTriggered(false);
  }, [downloadTriggered]);

  const handleDownload = () => {
    setLoader(true);
  
    console.log("file",driverDetailsExcel);

    fetch(driverDetailsExcel)
    .then((response) => {
      console.log('File fetched successfully.', response);

      return response.blob({ type: "application/octet-stream" });
    })
    .then((blob) => {
      const url = URL.createObjectURL(blob);
        const link = document.createElement("a");
        link.href = url;
        link.download = "DriverDetails.xlsx";
        link.click();
        setLoader(false);
        setDownloadTriggered(false);
      })
      .catch((error) => {
        setLoader(false);
        setDownloadTriggered(false);
        setErrorModal({
          show: true,
          title: "Download Failure",
          body: error.message,
        });
      });
  };

return (
    <div className="grid2">
     <MultipleFile multipleImages={multipleImages} setMultipleImages={setMultipleImages} register={register} errors={errors}/> 
        <div className="form-element-left">
            <button type="submit" onClick={handleSubmit(handleUpload)} className="btn blue" >Upload Drivers</button>
        </div>
    </div>
  );
};
export default DriverDetails;
