import { useEffect, useState } from "react";
import API_Headers from "../../../API_Headers";

const PerformenceStaticsTableRecentList = ({openWorkDBItem}) => {
  
  const [headers, setHeaders] = useState([])

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

  const itemRowsRecent = [];
  for (let item of openWorkDBItem) {
    var year  = new Date(item.effectiveDate).getFullYear();
    var month  = new Date(item.effectiveDate).getMonth();
    var day  = new Date(item.effectiveDate).getDate();
    var expDate  = new Date(year + 1, month, day);
    var expirationDate = expDate.toISOString().slice(0, 10)
    var ninetyDays = new Date(item.effectiveDate)
    ninetyDays.setDate(ninetyDays.getDate() + 90)
    var exactDate = new Date(ninetyDays)
    exactDate.toISOString().slice(0, 10)
    if(item.quoteNumber !== ""){
      const row = (
        <tr>
          <td >{item.quoteNumber}</td>
          <td >{item.effectiveDate}</td>
          <td >{expirationDate}</td>
          <td >{item.insuredName}</td>
          <td >{item.stateCd}</td> 
          <td >{item.product}</td> 
        </tr>
      );
      itemRowsRecent.push(row);
    }
  }

  return (
    <div class="tabContent extraWidth">
      <table>
        <thead>
          <tr>
            <th>Quote Number</th>
            <th>Effective Date</th>
            <th>Expiration Date</th>
            <th>Insured Name</th>
            <th>State</th>
            <th>Product</th>
          </tr>
        </thead>
      <tbody>
          {itemRowsRecent} 
        </tbody> 
        
      </table>
    </div>
  )
}

export default PerformenceStaticsTableRecentList;
