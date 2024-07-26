import React,{useState} from 'react'
import { Button, Modal } from 'react-bootstrap'
import { useHistory } from 'react-router-dom';
const LogoutModal = ({showModal, modalTitle, modalBody, setShowModal}) => {

    var show=showModal;
    var title= modalTitle
    var body= modalBody
    const history = useHistory();
    const handleClose=()=>{
        sessionStorage.removeItem("userName");
        sessionStorage.removeItem("password");
        sessionStorage.removeItem("roleType");
        sessionStorage.removeItem("email");
        history.push("/");
        setShowModal({show:false, title:"", body:""})
    }

    return (
        <div>
            <Modal show={show} onHide={handleClose} backdrop="static" keyboard={false}>
                <Modal.Header closeButton>
                    <Modal.Title>{title}</Modal.Title>
                </Modal.Header>
                <Modal.Body>{body}</Modal.Body>
                <Modal.Footer> <Button className='btn blue' onClick={handleClose}> Okay </Button> </Modal.Footer>
            </Modal>
        </div>
    )
}

export default LogoutModal