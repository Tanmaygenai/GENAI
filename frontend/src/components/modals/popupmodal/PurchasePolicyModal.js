import React from 'react';
import { Button, Modal } from 'react-bootstrap';
import { json } from 'react-router';

// Recursive component to handle and format nested data
const RenderData = ({ data }) => {
    if (typeof data === 'object' && data !== null) {
      if (Array.isArray(data)) {
        return (
          <div style={{ marginLeft: '20px' }}>
            {data.map((item, index) => (
              <div key={index} style={{ marginBottom: '10px'}}>
                <h4>Item {index + 1}</h4>
                <RenderData data={item} />
              </div>
            ))}
          </div>
        );
      } else {
        return (
          <div style={{ marginLeft: '20px' }}>
            {Object.keys(data).map(key => (
              <div key={key} style={{ marginBottom: '10px'}}>
                {console.log(key+"key",data[key])}
                {typeof data[key] === 'object'?<h3 style={{fontWeight:"bold"}}>{formatKey(key)}: <RenderData data={data[key]} /></h3>:<h3 style={{fontWeight:"100"}}>{formatKey(key)}: <RenderData data={data[key]} /></h3>}
                
                
              </div>
            ))}
          </div>
        );
      }
    } else {
      return data.toString();
    }
  };
  
  const formatKey = (key) => {
    // Replace underscores with spaces and capitalize each word
    return key.replace(/_/g, ' ').replace(/\b\w/g, char => char.toUpperCase());
  };
  


const PurchasePolicyModal = ({ showModal, setShowModal, selectedItem }) => {
  const handleClose = () => setShowModal({ show: false });
  //selectedItem=JSON.parse(selectedItem)
  const data =
    typeof selectedItem === 'string' ? JSON.parse(selectedItem) : selectedItem;
  console.log("selectedItem", selectedItem);

  return (
    <Modal show={showModal.show} onHide={handleClose} backdrop="static" keyboard={false} size="md">
      <Modal.Header closeButton>
        <Modal.Title>Submission Data</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div>
          {selectedItem ? <RenderData data={data} /> : <p>Loading...</p>}
          {/* <li>{selectedItem}</li> */}
        </div>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>Ok</Button>
        <Button variant="secondary" onClick={handleClose}>Cancel</Button>
      </Modal.Footer>
    </Modal>
  );
};

export default PurchasePolicyModal;
