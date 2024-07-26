import React from 'react'
import { Button, Modal } from 'react-bootstrap'
import { useHistory } from 'react-router-dom';

const ApprovalConfigUploadModal = ({ showModal, setShowModal }) => {
    const history = useHistory();
    const handleClose = (e) => {
        history.push({ pathname: '/approvalConfigDocument' })
        setShowModal({ show: false })
    }
    return (
        <div>
            <Modal show={showModal.show} onHide={handleClose} backdrop="static" keyboard={false} size="md">
                <Modal.Header closeButton>
                    <Modal.Title>Approval Config Document</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        <p><b>Approval Config Document has been uploaded successfully</b></p>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="white" onClick={(e) => {
                        handleClose(e)
                    }}> Ok </Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
}

export default ApprovalConfigUploadModal