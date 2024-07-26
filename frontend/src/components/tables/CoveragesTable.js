import React from 'react';

const CoveragesTable = ({ coverages }) => {
    if (coverages.length === 0) {
        return null
    }
    else {
        return (
            <table className="driverresults">
                <thead>
                    <tr>
                        <th>SL.</th>
                        <th>Coverage Type</th>
                        <th>Building Limit</th>
                        <th>Building Deductible</th>
                    </tr>
                </thead>

                <tbody>
                    {coverages.map((val, ind) => {
                        return (
                            <tr>
                                <td>{ind + 1}</td>
                                <td>{val.CoverageTypes}</td>
                                <td>{val.BuildingLimit}</td>
                                <td>{val.BuildingDeductible}</td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        )
    }

};

export default CoveragesTable;