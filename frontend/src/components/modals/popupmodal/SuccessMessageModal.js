import React,{useState} from 'react'
import { Button, Modal } from 'react-bootstrap'
import { useHistory, useLocation } from 'react-router-dom';
const SuccessMessageModal = ({showModal, modalTitle, modalBody, setShowModal}) => {
    const location = useLocation();
    var show=showModal;
    var title= modalTitle
    var body= modalBody
    const history = useHistory();
    const handleClose=()=>{
        setShowModal({show:false, title:"", body:""})
        if(body !== "Error occured while craeting the case!"){
            if(location.pathname == '/contactUs/') {
                history.push("/support")
            }
        }
    }

    return (
        <div>
            <Modal show={show} onHide={handleClose} backdrop="static" keyboard={false}>
                <Modal.Header closeButton>
                    <Modal.Title>{title}</Modal.Title>
                </Modal.Header>
                <Modal.Body>{body}</Modal.Body>
                <Modal.Footer> <Button className='btn blue' onClick={handleClose}> Close </Button> </Modal.Footer>
            </Modal>
        </div>
    )
}

export default SuccessMessageModal
