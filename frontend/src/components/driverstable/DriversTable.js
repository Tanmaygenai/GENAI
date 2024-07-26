import React from 'react'
import { Table } from 'react-bootstrap'

const DriversTable = ({ drivers }) => {

    if (drivers.length === 0) {
        return null
    }
    else {
        return (<Table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Age</th>
                    <th>Total Driver Points</th>
                </tr>
            </thead>
            <tbody>
                {drivers.map((val, ind) => {
                    return (
                        <tr>
                            <td>{ind + 1}</td>
                            <td>{val.Age}</td>
                            <td>{val.TotalDriverPoints}</td>
                        </tr>
                    )
                })}

            </tbody>
        </Table>
        )
    }
}

export default DriversTable
