import React,{useEffect} from 'react'
import DropDown from '../../components/dropdown/DropDown';
import { formFunctions } from '../../utility/jqueryFunctions';
import Check from '../../components/check/Check';
const LossHistoryForm = ({ register, errors }) => {
    useEffect(() => {
        formFunctions()
    }, [])
    return (
        <div>
            <div className='form-element'>
                <p><b>Have there been any losses in the past 5 Years ?</b></p>
            </div>
            <div className="form-element form-inline-select">
                <DropDown fieldName='LossHistory' register={register} errors={errors} lableClass='form-label' activeBold={true} showLabel={false} >
                    <option value="Yes">Yes</option>
                    <option value="No">No</option>
                </DropDown>
            </div>
        </div>
    )
}
export default LossHistoryForm
