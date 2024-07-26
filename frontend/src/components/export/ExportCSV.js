import React from 'react'
import { CSVLink } from 'react-csv'
import Button from 'react-bootstrap/Button';
 const ExportCSV = ({csvData, fileName,headers}) => {
    return (
        <div>
            <button type="button" className="btn blue">
                <CSVLink data={csvData} filename={fileName} headers={headers}>Export</CSVLink>
            </button>
        </div>
    )
}
export default ExportCSV