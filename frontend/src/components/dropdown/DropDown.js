import React, { useEffect } from 'react'
import { Form } from 'react-bootstrap'
import classNames from 'classnames';
import $ from "jquery"

import { formFunctions } from '../../utility/jqueryFunctions';

const DropDown = ({disabled=false, defaultValue="", fieldName = "", register = {}, errors = {}, showLabel = true, children, required = true, lableClass, activeBold = true, label ,id='select'}) => {
    var trimmedFieldName = fieldName.replace(/[ (/,.?'")&-]/gi, "")
    useEffect(() => {
        formFunctions();
    }, [])

 
    return (
        <>
            {showLabel ? <label className={lableClass}>{label ? label : fieldName} {activeBold ? <b>*</b> : null}</label> : null}
            <select
                disabled={disabled}
                id={id}
                name={trimmedFieldName}
                size="sm"
                {...register(trimmedFieldName, {
                    required: {
                        value: required,
                        message: label + ' is required',
                }})}
                className={classNames(" form-field form-control", {
                    "is-invalid": errors[trimmedFieldName],
                })}
            >
                {children}
            </select>
            {errors[trimmedFieldName] && (
                <div className="invalid-feedback">
                    {errors[trimmedFieldName].message}
                </div>
            )}
        </>
    )
}

export default DropDown
