import React, { useState } from 'react'
import { Button, Modal } from 'react-bootstrap'

const GenerateQuoteModal = ({ showModal, setShowModal, submitOpenWork }) => {

    var show = showModal;
    const handleClose = () => {
        setShowModal({ show: false, appNumber:null })
    }
    return (
        <div>
            <Modal show={showModal.show} onHide={handleClose} backdrop="static" keyboard={false} size="md">
                <Modal.Header closeButton>
                    <Modal.Title>Quotation</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        <p><b>Submit for a Quotation?</b></p>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="white" onClick={() => {
                        submitOpenWork(showModal.appNumber)
                        handleClose()
                    }}> Ok </Button>
                    <Button variant="white" onClick={handleClose}> Cancel </Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
}

export default GenerateQuoteModal