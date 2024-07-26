import React, {useState} from "react";
import Button from 'react-bootstrap/Button';
import FormControl from '../../formcontrol/FormControl';
import { useForm } from 'react-hook-form'
import { useHistory } from 'react-router-dom';
import Modal from 'react-bootstrap/Modal';
import Layout from '../../../layout/Layout'

function PolicyNotFoundModal() {
const { register, formState: { errors }, handleSubmit } = useForm();
  const [show, setShow] = useState(true);
  const history = useHistory();
  const handleClose = () => history.push("/");
return (
    <>
        <Modal show={show} onHide={handleClose} backdrop="static" keyboard={false}>
            <Modal.Header closeButton>
              <Modal.Title>Error</Modal.Title>
            </Modal.Header>
            <Modal.Body> Policy Number Not Found </Modal.Body>
            <Modal.Footer> <Button variant="primary" onClick={handleClose}> Close </Button> </Modal.Footer>
          </Modal>
    </>
);
}
export default PolicyNotFoundModal;
