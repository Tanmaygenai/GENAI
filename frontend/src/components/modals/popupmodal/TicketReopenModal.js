import React, {useEffect, useState} from 'react'
import { Button, Modal } from 'react-bootstrap'
import CryptoJS from "crypto-js";
import { useHistory } from "react-router-dom";
import API_Headers from "../../../API_Headers";
import ReopenTicketService from "../../../app/services/ReopenTicketService"

const TicketCloseModal = ({ showModal, setShowModal, fetchUserDetails }) => {
    const [headers, setHeaders] = useState([]);
    const secretPass = process.env.REACT_APP_Secret_Key;
    const history = useHistory();

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val));
      }, []);

    const handleClose = () => {
        setShowModal({...showModal, show: false})
    }

    const reopenTicket = async () => {
        const payload = {
            caseId: showModal.caseId,
            status: 'Open',
            closedAt: null
        }
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(payload),
            secretPass
          ).toString();
          try {
            const responseData = await ReopenTicketService.reopenTicket(data1, headers);
            if (responseData?.status == 200) {
              if(responseData.data=== "Ticket reopened successfully!"){
                setShowModal({show: false})
                fetchUserDetails();
              }
              else{
                setShowModal({...showModal, body: "Some error!"})
              }
            }
          } catch (error) {
            if (error == "Error: Network Error") {
              history.push("/SystemMaintenance");
            } else {
              alert(error.response.data.error);
            }
          }
    }
    return (
        <div>
            <Modal show={showModal.show} onHide={handleClose} backdrop="static" keyboard={false} size="md">
                <Modal.Header closeButton>
                    <Modal.Title>Reopen Issue</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        <p><b>{
                          showModal.body
                        }</b></p>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="white" onClick={reopenTicket}> Yes </Button>
                    <Button variant="white" onClick={handleClose}> Cancel </Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
}

export default TicketCloseModal