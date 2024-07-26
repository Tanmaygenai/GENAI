import React from 'react'

async function API_Headers() {
    const securityID = process.env.REACT_APP_Security_ID
    const securityPassword = process.env.REACT_APP_Security_Password

    const headers = {
        'Content-Type': 'application/text',
        'username': sessionStorage.getItem("userName"),
        'Authorization': 'Basic ' + btoa(`${securityID}:${securityPassword}`),
    }
    return headers
}

export default API_Headers