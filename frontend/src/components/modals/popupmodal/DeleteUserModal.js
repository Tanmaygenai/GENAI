import React, { useState } from 'react'
import { Button, Modal } from 'react-bootstrap'

const DeleteUserModal = ({ showModal, setShowModal, onDelete }) => {

    var show = showModal;
    const handleClose = () => {
        setShowModal({ show: false, userName:null })
    }
    return (
        <div>
            <Modal show={showModal.show} onHide={handleClose} backdrop="static" keyboard={false} size="md">
                <Modal.Header closeButton>
                    <Modal.Title>Delete User</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        <p><b>Delete user {showModal.userName}?</b></p>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="white" onClick={() => {
                        onDelete(showModal.userName)
                        handleClose()
                    }}> Ok </Button>
                    <Button variant="white" onClick={handleClose}> Cancel </Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
}

export default DeleteUserModal