import React, { useEffect, useState } from 'react'
import Layout from '../../layout/Layout'
import { useForm } from "react-hook-form"
import FormControl from "../../components/formcontrol/FormControl";
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import { Link, useHistory } from 'react-router-dom';
import CognitoUserService from '../services/CognitoUserService';
import DeleteUserModal from '../../components/modals/popupmodal/DeleteUserModal';
import API_Headers from '../../API_Headers.js';
import { Pagination } from 'react-bootstrap';
import useLoader from "../../context/loader"

const UserUpdate = () => {
    const { setLoader } = useLoader()
    const [rowData, setRowData] = useState([]);
    const { register, handleSubmit, formState: { errors } } = useForm();
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [headers, setHeaders] = useState([]);
    const [deleteUser, setDeleteUser] = useState({ show: false, userName: "" });
    const [userCognitoList, setUserCognitoList] = useState([]);
    const [activePage, setActivePage] = useState(1)
    const history = useHistory();
    const handleBack = () => {
        history.push({
            pathname: "/userManagement"
        });
    }

    useEffect(() => {
        setLoader(true)
        API_Headers().then((val) => setHeaders(val))
    }, [])

    useEffect(() => {
        if (headers.length !== 0) {
            if (rowData.length > 0) {
                onSearch();
            }
            const responseData1 = CognitoUserService.getCognitoUsers(headers);
            responseData1.then((res) => {
                setUserCognitoList(res.data)
            })
        }
    }, [headers])

    useEffect(() => {
        var userL = []
        if (userCognitoList.length !== 0) {
            userCognitoList.map((users) => {
                var ul = {
                    "userName": users.userName,
                    "email": users.email,
                    "phone": users.phone,
                }
                userL.push(ul)
            })
            setRowData(userL)
            setLoader(false)
        }
    }, [userCognitoList])

    const onSearch = async (data) => {
        var userL = []
        var c = 0;
        if (data.userName !== "") {
            if (userCognitoList.length > 0) {
                userCognitoList.map((users) => {
                    c = c + 1
                    if (users.userName === data.userName) {
                        c = c - 1
                        var ul = {
                            "userName": users.userName,
                            "email": users.email,
                            "phone": users.phone,
                        }
                        userL.push(ul)
                    }
                    if (userCognitoList.length === c) {
                        setErrorModal({ show: true, title: "Error", body: "User not found!" })
                    }
                })
                setRowData(userL)
                // setIsLoaded(true)
                setLoader(false)
            } else {
                setErrorModal({ show: true, title: "Error", body: "No user exist!" })
            }

        }
        else {
            userCognitoList.map((users) => {
                var ul = {
                    "userName": users.userName,
                    "email": users.email,
                    "phone": users.phone,
                }
                userL.push(ul)
            })
            setRowData(userL)
            setLoader(false)
        }

    };

    const onDelete = (data) => {
        var userL = []
        const responseData = CognitoUserService.deleteCognitoUser(data, headers);
        responseData.then((res) => {
            if (res.data === "Success") {
                setErrorModal({ show: true, title: "Success", body: "User deleted successfully!" })
                const responseData1 = CognitoUserService.getCognitoUsers(headers);
                responseData1.then((res) => {
                    res.data.map((users) => {
                        var ul = {
                            "userName": users.userName,
                            "email": users.email,
                            "phone": users.phone,
                        }
                        userL.push(ul)
                    })
                    setRowData(userL)
                })
            } else {
                setErrorModal({ show: true, title: "Error", body: res.data })
            }
        })
    }

    var noOfRowsPerPage = 10;
    var noOfPagesToDisplay = 25;
    let items = [];
    for (let number = 1; ((number - 1 - (1 / 10)) <= (rowData.length / 10) && number <= noOfPagesToDisplay); number++) {
        items.push(
            <Pagination.Item key={number} active={number === activePage} onClick={() => setActivePage(number)}>
                {number}
            </Pagination.Item>,
        );
    }

    return (
        <Layout TabName='User Update' BreadCrum={['My Dashboard', 'Admin', 'User Management', 'User Update']}>
            <div class="commonform_box">
                <div class="inf">
                    <form class="allForm" onSubmit={handleSubmit(onSearch)}>
                        <div class="fullhead">Update Agent / Admin Registration</div>
                        <br />
                        <div className="grid2">
                            <div class="form-element">
                                <FormControl fieldName="userName" label='User Name' id='username' register={register} errors={errors} type="text" showLabel={true} className="form-field" required={false} lableClass='form-label' activeBold={false} />
                            </div>
                            <div class="form-element">
                                <button type="submit" className='btn blue' id="">Search</button>
                            </div>
                        </div>
                        {rowData.length > 0 ?
                            <>
                                <table class='table table-hover' >
                                    <thead class="thead-light">
                                        <tr class="table-primary">
                                            <th scope="col">Id</th>
                                            <th scope="col">User Name</th>
                                            <th scope="col">User Email</th>
                                            <th scope="col">User Phone</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            rowData.map((item, ind) => {
                                                return activePage === parseInt(ind / noOfRowsPerPage) + 1 ?
                                                    <tr>
                                                        <td>{ind + 1}</td>
                                                        <td>{item.userName}</td>
                                                        <td>{item.email}</td>
                                                        <td>{item.phone}</td>
                                                        <td>
                                                            <button type="button" className='btn blue' id="" style={{ marginLeft: '10px' }} onClick={() => setDeleteUser({ show: true, userName: item.userName })}>
                                                                Delete
                                                            </button>
                                                        </td>
                                                    </tr>
                                                    :
                                                    ''
                                            })}
                                    </tbody>
                                </table>
                                <Pagination size="sm" style={{ justifyContent: "center" }}>
                                    {items}
                                </Pagination>
                            </>
                            :
                            <div class="tabContent extraWidth" style={{ display: "block", color: 'red' }}><p>No rows found!</p></div>}
                    </form>
                </div>
                <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
                <DeleteUserModal showModal={deleteUser} setShowModal={setDeleteUser} onDelete={onDelete} />
            </div>
        </Layout>
    )
}
export default UserUpdate
