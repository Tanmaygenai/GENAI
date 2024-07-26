import React, { useEffect, useState } from 'react';
import $ from "jquery";
import "jquery-ui-dist/jquery-ui";
import classNames from 'classnames';

const DateInput = ({ fieldName = "", defaultValue = "", yearRange = "-60:+0", minDate, minAge , trigger = () => {}, register = {}, errors = {}, type = "text", className, required = false, label = "", setValue, id = 'Date', setState = () => {} }) => {
    var trimmedFieldName = fieldName.replace(/[ (/,.?'")&-]/gi, "")
    let dateToday = new Date();
    const [errorMessage, setErrorMessage] = useState(""); // State for the error message

    useEffect(() => {
        $("#"+id).datepicker({
            dateFormat: "yy-mm-dd",
            yearRange: yearRange,
            minDate: minDate,
            maxDate: "+100y",
            dayNamesMin: ["S", "M", "T", "W", "T", "F", "S"],
            changeMonth: true,
            changeYear: true,
            endDate: '-6570d',
            onSelect: function (value) {
                setValue(trimmedFieldName, value, { shouldValidate: true }); // Trigger validation
                $(this).datepicker('hide');
                $(this).parent().addClass('has-value');
                setState(value);
                trigger(fieldName);
            },
        });
    }, [])

    return (
        <>
            <div className="form-element datepicker">
                <label className="form-label">{label === "" ? fieldName : label} <b>*</b></label>
                <input
                    type="text"
                    name={trimmedFieldName}
                    id={id}
                    defaultValue={defaultValue}
                    autoComplete="off"
                    {...register(trimmedFieldName, {
                        required: {
                            value: required,
                            message: fieldName + ' is required',
                        },
                        validate: (value) => {
                            const selectedDate = new Date(value);
                            const currentDate = new Date();
                            const age = currentDate.getFullYear() - selectedDate.getFullYear();

                            if (age < minAge) {
                                return `${minAge} years or older is required.`;
                            }

                            return true;
                        }
                    })}
                    className={classNames("form-field dob " + id + className, {
                        "is-invalid": errors[trimmedFieldName],
                    })}
                />
                {errors[trimmedFieldName] && (
                    <div className="invalid-feedback">
                        {errors[trimmedFieldName].message}
                    </div>
                )}
            </div>
        </>
    )
}

export default DateInput;