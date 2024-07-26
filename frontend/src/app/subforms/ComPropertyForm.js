import React from 'react'
import FormControl from '../../components/formcontrol/FormControl';
import DropDown from '../../components/dropdown/DropDown';
import { drop_data } from '../../dummydata/data';
import CoveragesTable from '../../components/tables/CoveragesTable';
const ComPropertyForm = ({ openModal,modalState }) => {

    return (
        <div className='grid1'>
            <div className="form-element-btn-border">
                <a href="# " onClick={() => { openModal('mainModal', "Add Coverage") }} className="btn outline">Add Coverage</a>
            </div>
            <CoveragesTable coverages={modalState.CoverageData}/>
        </div>
    )
}

export default ComPropertyForm