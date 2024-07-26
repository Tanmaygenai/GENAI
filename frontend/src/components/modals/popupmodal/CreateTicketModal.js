import React, { useState, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import { useForm } from "react-hook-form";
import CryptoJS from "crypto-js";
import API_Headers from "../../../API_Headers";
import FormControl from "../../formcontrol/FormControl";
import DropDown from "../../dropdown/DropDown";
import TextArea from "../../textarea/TextArea";
import ContactUsService from "../../../app/services/ContactUsService";

const CreateTicket = ({ showModal, setShowModal, deleteOpenWork }) => {
    const secretPass = process.env.REACT_APP_Secret_Key;
    const [headers, setHeaders] = useState([]);
    const {
        register,
        handleSubmit,
        reset,
        formState: { errors },
        setValue,
    } = useForm();

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val));
    }, []);

    const handleClose = () => {
        setShowModal(false);
    };

    const onSubmit = async (data) => {
        const updatedData = {
            ...data,
            createdDate: new Date(),
            severity: 'GQ',
            status: 'open'
        };
        reset();
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(updatedData),
            secretPass
        ).toString();
        try {
            await ContactUsService.contactUs(data1, headers);
            handleClose();
        } catch (error) {
            if (error == "Error: Network Error") {
                handleClose();
            } else {
                alert(error.response.data.error);
            }
        }
    };

    function formatPhoneNo(e) {
        var phone = e.target.value;
        var phoneClone = phone;
        phoneClone = phoneClone.split("");
        phoneClone.length == 4 &&
            phoneClone[3] !== "-" &&
            phoneClone.splice(3, 0, "-");
        phoneClone.length == 8 &&
            phoneClone[7] !== "-" &&
            phoneClone.splice(7, 0, "-");
        phoneClone = phoneClone.join("");
        if (phoneClone.length > 11) {
            phoneClone = phoneClone.slice(0, 12);
        }
        e.target.value = phoneClone;
    }
    return (
        <div>
            <Modal
                show={showModal}
                onHide={handleClose}
                backdrop="static"
                keyboard={false}
                size="md"
            >
                <Modal.Header closeButton>
                    <Modal.Title>Create Ticket</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div class="inf">
                        <form class="allForm" onSubmit={handleSubmit(onSubmit)}>
                            <h1 style={{ fontWeight: "bolder", fontSize: "15px" }}>
                                Send us a message
                            </h1>

                            <div className="form-element">
                                <FormControl
                                    fieldName="name"
                                    label="Name"
                                    id="name"
                                    register={register}
                                    errors={errors}
                                    type="text"
                                    showLabel={true}
                                    className="form-field"
                                    required={true}
                                    lableClass="form-label"
                                    activeBold={true}
                                />
                            </div>
                            <div className="form-element">
                                <FormControl
                                    fieldName="agencyName"
                                    label="Agency's Name"
                                    id="agency_name"
                                    register={register}
                                    errors={errors}
                                    type="text"
                                    showLabel={true}
                                    className="form-field"
                                    required={true}
                                    lableClass="form-label"
                                    activeBold={true}
                                />
                            </div>
                            <div className="form-element">
                                <FormControl
                                    fieldName="email"
                                    label="Email"
                                    id="email"
                                    register={register}
                                    errors={errors}
                                    type="text"
                                    showLabel={true}
                                    className="form-field"
                                    required={true}
                                    lableClass="form-label"
                                    activeBold={true}
                                />
                            </div>
                            <div className="form-element">
                                <FormControl
                                    fieldName="phoneNumber"
                                    label="Enter your phone (e.g. +141555575)"
                                    id="phone_number"
                                    register={register}
                                    errors={errors}
                                    type="text"
                                    showLabel={true}
                                    className="form-field"
                                    required={true}
                                    lableClass="form-label"
                                    activeBold={true}
                                    onChange={(e) => {
                                        formatPhoneNo(e);
                                    }}
                                />
                            </div>
                            <div className="form-element form-select">
                                <DropDown
                                    fieldName="category"
                                    label="Category"
                                    id="category"
                                    defaultValue=""
                                    register={register}
                                    errors={errors}
                                    lableClass="form-label"
                                    activeBold={true}
                                    showLabel={true}
                                >
                                    <option value="">Select</option>
                                    <option value="policy">Policy</option>
                                    <option value="claim">Claim</option>
                                </DropDown>
                            </div>

                            <div className="form-element">
                                <FormControl
                                    fieldName="message"
                                    label="Subject"
                                    id="message"
                                    register={register}
                                    errors={errors}
                                    type="text"
                                    showLabel={true}
                                    className="form-field"
                                    required={true}
                                    lableClass="form-label"
                                    activeBold={true}
                                />
                            </div>

                            <TextArea
                                fieldName="description"
                                Placeholder="Enter your description"
                                label="Enter your description"
                                id="description"
                                register={register}
                                errors={errors}
                                type="text"
                                showLabel={false}
                                className="form-field"
                                lableClass="form-label"
                                activeBold={true}
                            />

                            <div class="form-element-btn">
                                <button type="submit" className="btn blue" id="">
                                    Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button
                        variant="white"
                        onClick={() => {
                            handleClose();
                        }}
                    >
                        {" "}
                        Ok{" "}
                    </Button>
                    <Button variant="white" onClick={handleClose}>
                        {" "}
                        Cancel{" "}
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default CreateTicket;
