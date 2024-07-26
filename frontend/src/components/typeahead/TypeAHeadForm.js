import { Controller } from 'react-hook-form';
import { Typeahead } from 'react-bootstrap-typeahead';

const TypeAHeadForm = ({typeAheadClass="", placeholder = "", register, errors, control, fieldName, Data, minLength, getValues, showLabel = true, lableClass, activeBold = true, label, onSelect = () => { }, disable = false, defaultInputValue }) => {

    var handleFocus = (e) => {
        var node = e.target.parentNode
        node.parentNode.parentNode.className = 'form-element has-value'
    }
    var handlBlur = (e) => {
        var node = e.target.parentNode
        if (e.target.value.length > 0 || typeAheadClass.length>0) {
            node.parentNode.parentNode.className = 'form-element has-value'
        }
        else {
            node.parentNode.parentNode.className = 'form-element'
        }
    }
    // To add class on the typeahead parent div
    typeAheadClass=`form-field unique ${typeAheadClass}`
    var defaultValue = defaultInputValue ? defaultInputValue:getValues(fieldName)
    // To set defaulte has-value class for typeahead
    var divClass = 'form-element'
    placeholder.length === 0 && !defaultValue ? divClass = 'form-element' : divClass = 'form-element has-value'

    return (
        <div className={divClass}>
            {showLabel ? <label className={lableClass}>{label ? label : fieldName} {activeBold ? <b>*</b> : null}</label> : null}
            <Controller
                control={control}
                name={fieldName}
                {...register(fieldName, { required: label + 'is required' })}
                render={({ field: { onChange } }) => (
                    <Typeahead
                        disabled={disable}
                        minLength={minLength}
                        className={typeAheadClass}
                        id="basic-typeahead-single"
                        onChange={([e]) => {
                            onChange(e)
                            onSelect(e)
                        }}
                        onFocus={(e) => handleFocus(e)}
                        onBlur={(e) => handlBlur(e)}
                        options={Data}
                        defaultInputValue={defaultValue}
                        placeholder={placeholder}
                    />
                )}
            />
            {errors[fieldName] && (
                <div className="form-text text-danger">
                    {errors[fieldName].message}
                </div>
            )}
        </div>

    )
}
export default TypeAHeadForm