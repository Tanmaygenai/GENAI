import React, { useState, useEffect } from 'react'
import { Button, Modal } from 'react-bootstrap'
import {Link} from 'react-router-dom'
import { useHistory, useLocation } from "react-router-dom";
import { CommercialAutoModalStateResopnseJson } from "../../../utility/resopnseModalStateComAuto";

const EndorsePolicyModal = ({ showModal, setShowModal, endorseOpenWork }) => {

    const { state } = useLocation();
    var { policyNumber } = useLocation();
    var show = showModal;
    const handleClose = () => {
        setShowModal({ show: false, policyNumber:null, state: null, modalState: null})
    }

    return (
        <div>
            <Modal show={showModal.show} onHide={handleClose} backdrop="static" keyboard={false} size="md">
                <Modal.Header closeButton>
                    <Modal.Title>Endorsement</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        <p><b>Endorse Policy?</b></p>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    
                        <Button><Link to={{ pathname: '/commercialAuto', policyNumber : showModal.policyNumber, state: showModal.state, modalState:showModal.state}} >Endorse Policy</Link></Button>
                        
                        
                    <Button variant="white" onClick={handleClose}> Cancel </Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
}

export default EndorsePolicyModal