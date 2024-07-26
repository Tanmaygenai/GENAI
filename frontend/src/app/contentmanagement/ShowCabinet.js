import React from "react";
import Layout from "../../layout/Layout";
import DocumentsContainer from "./DocumentsContainer";
const ShowCabinet = () => {
    return(
        <Layout TabName='Content Management' BreadCrum={['My Dashboard', 'Content Management']}>
            <DocumentsContainer />
         </Layout>
    )
}
export default ShowCabinet;