import React from "react";
import { useParams } from "react-router";
import Layout from "../../layout/Layout";
import Policy from "./Policy";
const ShowPolicy = () => {
    const { id } = useParams();
    return(
    <Layout TabName='Policy Search' BreadCrum={['My Dashboard', 'Policy Search']}>
        <Policy />
    </Layout>
    )
}
export default ShowPolicy;