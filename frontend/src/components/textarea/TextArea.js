import React from 'react'
import { Form } from 'react-bootstrap'
import classNames from 'classnames';

const TextArea = ({ fieldName = "", register = {}, errors = {}, type = "text", showLabel = true, defaultValue = "", min, minLength, maxLength, className, required = false, lableClass, activeBold = true, Placeholder = '', label }) => {
    var trimmedFieldName = fieldName.replace(/[ (/,.?'")&-]/gi, "")

    return (
        <>
            {showLabel ? <Form.Label className={lableClass}>{label ? label : fieldName} {activeBold ? <b>*</b> : null}</Form.Label> : null}
            <Form.Control
                as="textarea"
                rows={3}
                name={trimmedFieldName}
                type={type}
                placeholder={Placeholder}
                size="sm"
                defaultValue={defaultValue}
                {...register(trimmedFieldName, {
                    required: {
                        value: required,
                        message: fieldName + ' is required',
                    },
                    min: {
                        value: min,
                        message: 'Should Not be Negative'
                    },
                    maxLength: {
                        value: maxLength,
                        message: fieldName + ' Should carry 5 digits only'
                    },
                    minLength: {
                        value: minLength,
                        message: fieldName + ' Should carry 5 digits only'
                    },
                })}
                className={classNames("form-control pointsModal " + className, {
                    "is-invalid": errors[trimmedFieldName],
                })}
            />
            {errors[trimmedFieldName] && (
                <div className="invalid-feedback">
                    {errors[trimmedFieldName].message}
                </div>
            )}
        </>
    )
}
export default TextArea