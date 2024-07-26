import React, { useState } from "react";
import { Pagination } from "react-bootstrap";

const DataTable = ({ rowData }) => {
  const [activePage, setActivePage] = useState(1);

  const itemRows = [];
  var noOfRowsPerPage = 10;
  var noOfPagesToDisplay = 25;
  var ind = 0;
  for (let item of rowData) {
    if (activePage === parseInt(ind / noOfRowsPerPage) + 1) {
      const row = (
        <tr key={1}>
          <td key={2}>{item.id}</td>
          <td key={2}>{item.agentId}</td>
          <td key={2}>{item.agentEmail}</td>
          <td key={2}>{item.quoteNumber}</td>
          <td key={2}>{item.creationDate}</td>
          <td key={4}>{item.reminderDate}</td>
          <td key={5}>{item.taskId}</td>
          <td key={6}>{item.taskDescription}</td>
          <td key={6}>
            <button class="btn blue" type="button">
              Delete
            </button>
          </td>
        </tr>
      );
      itemRows.push(row);
    }
    ind++;
  }

  let items = [];
  for (
    let number = 1;
    number - 1 - 1 / 10 <= rowData.length / 10 && number <= noOfPagesToDisplay;
    number++
  ) {
    items.push(
      <Pagination.Item
        key={number}
        active={number === activePage}
        onClick={() => setActivePage(number)}
      >
        {number}
      </Pagination.Item>
    );
  }

  return rowData.length > 0 ? (
    <div
      class="tabContent extraWidth tasklist_table"
      style={{ display: "block" }}
    >
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Agent Id</th>
            <th>Agent Email</th>
            <th>Quote Number</th>
            <th>Creation Date</th>
            <th>Reminder Date</th>
            <th>Task Id</th>
            <th>Task Description</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {itemRows}
        </tbody>
      </table>
      <br />
      <Pagination size="sm" style={{ justifyContent: "center" }}>
        {items}
      </Pagination>
    </div>
  ) : (
    <div
      class="tabContent extraWidth"
      style={{ display: "block", color: "#CF0918" }}
    >
      <p>No rows to display</p>
    </div>
  );
};
export default DataTable;
