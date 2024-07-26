import React from 'react'
import jsPDF from "jspdf";
import "jspdf-autotable";
import { CSVLink } from 'react-csv'
import Button from 'react-bootstrap/Button';
 const ExportPDF = ({pdfHeaders,pdfData, fileName,pdfTitle}) => {
   const exportPDF = () => {
       const unit = "pt";
       const size = "A4"; // Use A1, A2, A3 or A4
       const orientation = "portrait"; // portrait or landscape

       const marginLeft = 40;
       const doc = new jsPDF(orientation, unit, size);

       doc.setFontSize(15);

       const title = pdfTitle;

        const headers = [pdfHeaders]


       let content = {
         startY: 50,
         head: headers,
         body: pdfData
       };

       doc.text(title, marginLeft, 40);
       doc.autoTable(content);
       doc.save(fileName.pdf)
     };

    return (
        <div>
                    <button type="button" className="btn blue" onClick={() => exportPDF()}>
                        Generate PDF
                    </button>
                </div>
    )
}
export default ExportPDF