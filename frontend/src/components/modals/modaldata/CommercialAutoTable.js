import React from 'react'
import FormControl from '../../formcontrol/FormControl'
import { Table } from 'react-bootstrap'
import './CommercialAutoTable.css'

const CommercialAutoTable = ({ register, errors }) => {
    return (
        <Table>
            <tbody>
                <tr>
                    <th colSpan={4}>Radius of Operation</th>
                </tr>
                <tr>
                    <td></td>
                    <td className='tableHead'>Local (&lt;= 50 Miles)</td>
                    <td className='tableHead'>Intermediate (51 to 500 Miles)</td>
                    <td className='tableHead'>Long Haul (&gt;500 Miles)</td>
                </tr>
                <tr>
                    <td>Number of Medium (10,001 to 20,000 lbs.) Trucks</td>
                    <td><FormControl fieldName="Medium Trucks Local" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                    <td><FormControl fieldName="Medium Trucks Intermediate" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                    <td><FormControl fieldName="Medium Trucks Long Haul" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                </tr>
                <tr>
                    <td>Number of Heavy (20,001 to 45,000 lbs.) Trucks</td>
                    <td><FormControl fieldName="Heavy Trucks Local" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                    <td><FormControl fieldName="Heavy Trucks Intermediate" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                    <td><FormControl fieldName="Heavy Trucks Long Haul" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                </tr>
                <tr>
                    <td>Number of Extra Heavy (&gt;45,000 lbs.) Trucks</td>
                    <td><FormControl fieldName="Extra Heavy Trucks Local" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                    <td><FormControl fieldName="Extra Heavy Trucks Intermediate" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                    <td><FormControl fieldName="Extra Heavy Trucks Long Haul" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                </tr>
                <tr>
                    <td>Number of Heavy (20,001 to 45,000 lbs.) Truck Tractors</td>
                    <td><FormControl fieldName="Heavy Truck Tractors Local" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                    <td><FormControl fieldName="Heavy Truck Tractors Intermediate" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                    <td><FormControl fieldName="Heavy Truck Tractors Long Haul" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                </tr>
                <tr>
                    <td>Number of Extra Heavy (&gt;45,000 lbs.) Truck Tractors</td>
                    <td><FormControl fieldName="Extra Heavy Truck Tractors Local" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                    <td><FormControl fieldName="Extra Heavy Truck Tractors Intermediate" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                    <td><FormControl fieldName="Extra Heavy Truck Tractors Long Haul" register={register} errors={errors} showLabel={false} type='number' defaultValue={0} /> </td>
                </tr>

            </tbody>
        </Table>
    )
}

export default CommercialAutoTable
