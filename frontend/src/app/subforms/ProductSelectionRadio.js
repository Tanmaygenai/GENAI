import React from 'react'
import Check from '../../components/check/Check';
import { Form } from 'react-bootstrap'

const ProductSelectionRadio = ({ register, errors }) => {
    return (
        <Form.Group>
            <Check register={register} fieldName="Product Selection" value="Commercial Auto" type="radio" />
            <Check register={register} fieldName="Product Selection" value="TPCI" type="radio" showLabel={false} />
            <Check register={register} errors={errors} fieldName="Product Selection" value="Excess" type="radio" showLabel={false} />
        </Form.Group>
    )
}

export default ProductSelectionRadio
