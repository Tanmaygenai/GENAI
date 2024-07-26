import React from 'react'
import { useState } from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
    const environment = process.env.REACT_APP_Environment;
    const [policyId, setpolicyId] = useState();
    const onInputChange = (event) => {
        setpolicyId(event.target.value);


    };
    return (
        <div>
            <h1>Home-{environment}</h1>
            <br />
            <input type="text" value="Enter Policy Id"  ></input>
            <input type="text" name="PolcyId" onChange={e => onInputChange(e)}></input>
            <br /> <br />
            <Link class="btn btn-primary mr-2" to={`/policy/${policyId}`}>
                Submit
            </Link>

        </div>
    )
}

export default Home
