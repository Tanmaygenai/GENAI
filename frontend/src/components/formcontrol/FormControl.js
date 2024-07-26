import React, { useEffect } from 'react';
import { Form } from 'react-bootstrap';
import classNames from 'classnames';
import { formFunctions } from '../../utility/jqueryFunctions';

const FormControl = ({ fieldName = "", register = {}, errors = {}, type = "text", showLabel = true, defaultValue = "", min, minLength, maxLength, className, required = false, lableClass, activeBold = true, Placeholder = '', label, onChange, pattern, id, validateFullName }) => {
    var trimmedFieldName = fieldName.replace(/[ (/,.?'")&-]/gi, "");

    useEffect(() => {
        formFunctions();
    }, []);

    const validateFullNameValue = (value) => {
        if (validateFullName && value.indexOf(" ") !== -1 && value.split(' ')[1] === null || value.split(" ")[0] === "" || value.split(" ")[0] === undefined || value.split(" ")[1] === "" || value.split(" ")[1] === undefined) {
            return label + ' must contain first and last name';
        }
        return true;
    };

    return (
        <>
            {showLabel ? <Form.Label className={lableClass}>{label ? label : fieldName} {activeBold ? <b>*</b> : null}</Form.Label> : null}
            <Form.Control
                name={trimmedFieldName}
                type={type}
                onKeyUp={onChange}
                placeholder={Placeholder}
                size="md"
                id={id}
                defaultValue={defaultValue}
                {...register(trimmedFieldName, {
                    required: {
                        value: required,
                        message: label + ' is required',
                    },
                    min: {
                        value: min,
                        message: 'Value should be greater than 0.'
                    },
                    maxLength: {
                        value: maxLength,
                        message: label + ` Maximum characters allowed is ${maxLength}`
                    },
                    minLength: {
                        value: minLength,
                        message: label + ` Minimum characters allowed is ${minLength}`
                    },
                    pattern: {
                        value: pattern,
                        message: 'Value should be greater than 0.',
                    },
                    validate: validateFullName ? validateFullNameValue: false,
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
    );
}

export default FormControl;
