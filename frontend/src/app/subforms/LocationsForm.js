import React, {useEffect} from 'react';
import LocationsTable from '../../components/tables/LocationsTable';

const LocationsForm= ({ modalState, state, openModal, setModalState }) => {
  useEffect(() => {
    if(!!state.quoteData) {
        const data = JSON.parse(state.quoteData);
        if(data?.application && data?.application?.locations && data?.application?.locations.length > 0) {
            const oldLocations = data?.application?.locations.map((loc, ind) => {
                return {
                        "LocationDesc" : loc?.locationDesc,
                        "StreetName": loc?.addr?.addr1,
                        "City": loc?.addr?.city,
                        "PostalCode": loc?.addr?.postalCode,
                        "State": loc?.addr?.state,
                        "Country": loc?.addr?.country,
                        }
                    })
                    const updatedLocations = modalState.LocationData.concat(oldLocations)
                    setModalState({
                      ...modalState,
                      LocationData: updatedLocations
                    })
            }
    }
  }, [state])
  return (
    <div className='grid1'>
    <div className="form-element-btn-border">
        <a href="# " onClick={() => { openModal('mainModal', "Add New Location") }} className="btn outline">Add Location</a>
    </div>
    <LocationsTable state={state} locations={modalState.LocationData} modalState={modalState} setModalState={setModalState} openModal={openModal}  />
</div>
  );
}

export default LocationsForm;
