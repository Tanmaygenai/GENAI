import React, { useState } from 'react'
import { Button, Modal } from 'react-bootstrap'
import loading from '../../../assets/img/Loading.gif'

const LoaderModal = ({ showModal, setShowModal }) => {

    var show = showModal;
    const handleClose = () => {
        setShowModal({ show: false})
    }
    return (
        <div>
            <Modal show={show} onHide={handleClose} backdrop="static" keyboard={false} centered={true}>
                <Modal.Body>
                    <div class="coverContainer"> 
                        <img src={loading} alt='Loading...' width={'50%'}/>
                    </div> 
                </Modal.Body>
            </Modal>
        </div>
    )
}

export default LoaderModal