import React, {
    useCallback,
    useMemo,
    useRef,
    useState,
    useEffect
} from 'react';

import { AgGridReact } from 'ag-grid-react';
import 'ag-grid-community/styles/ag-grid.css';
import 'ag-grid-community/styles/ag-theme-alpine.css';
import CryptoJS from "crypto-js";
// import './styles.css';
import DeleteQuoteModal from '../../components/modals/popupmodal/DeleteQuoteModal';
import ErrorMessageModal from '../../components/modals/popupmodal/ErrorMessageModal';
import GenerateQuoteModal from '../../components/modals/popupmodal/GenerateQuoteModal';
import EndorsePolicyModal from '../../components/modals/popupmodal/EndorsePolicyModal';
import OpenWorkService from '../services/OpenWorkService';
import API_Headers from '../../API_Headers';
import Layout from '../../layout/Layout'
import useLoader from "../../context/loader"
import open_work_icon from '../../assets/img/icons/open_work.svg';
import recent_view_icon from '../../assets/img/icons/recent_view.svg';
import { CommercialAutoModalStateResopnseJson } from "../../utility/resopnseModalStateComAuto";
import { useHistory } from 'react-router-dom';
import PurchasePolicyModal from '../../components/modals/popupmodal/PurchasePolicyModal';
import PolicyService from '../services/PolicyService';
import { styles } from 'react-json-viewer';
const MyWorkBench = () => {
    const gridRef = useRef();
    const secretPass = process.env.REACT_APP_Secret_Key;
    const { setLoader } = useLoader()
    const containerStyle = useMemo(() => ({ width: '100%', height: 'auto' }), []);
    const gridStyle = useMemo(() => ({ height: '100%', width: '100%' }), []);
    const history = useHistory();

    const [headers, setHeaders] = useState([])
    const [openWorkDBItem, setOpenWorkDBItem] = useState([])
    const [selectedItem, setSelectedItem] = useState({});
    const [policyDBItem, setPolicyDBItem] = useState([]);
    const [lossNoticeDBItem,setLossNoticeDBItem]=useState([]);
    const[submissionData,setSubmissionData]=useState([]);
    const [policyDetail, setPolicyDetail] = useState({ show: false});
    const [gridApi, setGridApi] = useState(null);
    const [activeTab, setActiveTab] = useState('submission')
    const [modalDate, setErrorModal] = useState({ show: false, title: "", body: "" });
    const [generateQuote, setGenerateQuote] = useState({ show: false, appNumber: null });
    const [deleteQuote, setDeleteQuote] = useState({ show: false, appNumber: null });
    const [purchasePolicy, setPurchasePolicy] = useState({ show: false});
    const [endorsePolicy, setEndorsePolicy] = useState({ show: false, appNumber: null, state: null, modalState: null });
    const [rowData, setRowData] = useState([]);
    let [modalState, setModalState] = useState([]);
    const [columnDefs, setColumnDefs] = useState([]);

    const editQuote = (quote) => {
        if(quote.indicationData != "") {
            const data = {
                id: quote.id,
                quoteData: quote.indicationData
            }
            history.push('/commercialProperty', data)
        }
    }
    function processText(text) {
        if (typeof text === 'string') {
          return text.replace(/\\n/g, '').replace(/\\/g, '');
        }
        return text;
      }
      const transformKeys = (obj) => {
        if (Array.isArray(obj)) {
          return obj.map(item => transformKeys(item));
        } else if (typeof obj === 'object' && obj !== null) {
          return Object.keys(obj).reduce((acc, key) => {
            const newKey = key.replace(/\s+/g, '-');
            acc[newKey] = transformKeys(obj[key]);
            return acc;
          }, {});
        }
        return obj;
      };
    const setSubmissionID=async (item,fileName)=>{
        setLoader(true)
        console.log("item",item)
        const body={submission_id:item.submission_id,file_name:fileName}
        console.log("body",body)
        
        try {
            const role = sessionStorage.getItem("roleType");
            const response = await OpenWorkService.getSumissionData(body);
            setLoader(false)
             console.log("finalresponse",response.data.response)
            //const data=processText(response.data.response)
            const finaldata=transformKeys(response.data.response)
            const test=JSON.stringify(finaldata, null, 2)
            console.log("response",test)
            setSelectedItem(JSON.parse(test));
            setPolicyDetail({show:true});        
        } catch (error) {
            setLoader(false)
            setErrorModal({ show: true, title: "Network Error", body: "Unable to load the details" })

        }
    }
    console.log("selectedItem",selectedItem)

    const actionButton = (item) => {
        let displayQuote = ''
        if (!!item?.quoteNumber && item?.quoteNumber.includes('QT') && item?.status != 0) {
            displayQuote = 'Yes'
            return <button onClick={() => setDeleteQuote({ show: true, appNumber: item.id })} class="btn blue" style={{width:'100%',overFlow: "hidden"}} type='button'>Delete</button>
        } else {
            displayQuote = 'No'
            return (<><div style={{ display: 'flex', flexWrap: 'wrap', gap: '8px',justifyContent: 'flex-start' }}>
           {/* {item.map((input, index) => (
                    <button key={index} className="btn blue" onClick={() => setSubmissionID(item, input.file_name)} style={{ width: '30%',overflow: 'hidden', margin: '5px 0'  }} type="button">
                        View{index + 1}
                    </button>
                ))} */}
                <button className="btn blue" onClick={() => setSubmissionID(item, item.file_name)} type="button">Extract Data</button>
                </div>
        </>)
        }
    }

    useEffect(() => {
        API_Headers().then((val) => setHeaders(val))
    }, [])

    useEffect(() => {
        if (headers.length !== 0) {
            
            //loadOpenWorkItems();
        }
    }, [headers]);

    const loadOpenWorkItems = async () => {
        setLoader(true)
        try {
            const role = sessionStorage.getItem("roleType");
            const openWorkItemDBData = await OpenWorkService.getOpenWorkDBItem(role, headers);
            setLoader(false)
            openWorkItemDBData.data.sort(function (a, b) {
                return new Date(b.indicationDate) - new Date(a.indicationDate);
            });
            setOpenWorkDBItem(openWorkItemDBData.data)          
        } catch (error) {
            setLoader(false)
            setErrorModal({ show: true, title: "Network Error", body: "Unable to load the details" })

        }
    };
   
    useEffect(() => {
    
        loadPolicyItems()
        
    }, [])

    const openWorkRows = () => {
        const column = [
            { field: 'appPolicy', headerName: 'Submissionid' },
            // { field: 'effDate', filter: 'agDateColumnFilter', comparator: (valueA, valueB, nodeA, nodeB, isDescending) => valueB - valueA },
            { field: 'SubmissioDate', filter: 'agDateColumnFilter'  },
            { field: 'Documents' },
            { field: 'Flow', },
            { field: 'Status', filter: 'agDateColumnFilter' },
            { field: 'CompletionDate'},
            { field: 'Task', cellRenderer: (item) => actionButton(item.data.action) },
        ]
        setColumnDefs(column)
        const updatedRowData = openWorkDBItem.map((item) => {

                let displayQuote = ''
                if (item.quoteNumber.includes('QT')) {
                    displayQuote = 'Yes'
                } else if(item.quoteNumber.length>0){
                    displayQuote = 'Yes'
                }
                return {
                    Submissionid: displayQuote === 'Yes' ? item.quoteNumber : item.id,
                   // appPolicy: displayQuote == 'Yes' ? item.quoteNumber : `INDICATION-${item.id}`,
                    SubmissioDate: item.effectiveDate,
                    Documents: item.stateCd,
                    Flow: item.insuredName,
                    Status: item.indicationDate,
                    CompletionDate: item.product,
                    Task: item,
                }
            
        })
        setRowData(updatedRowData)
    }

    const loadPolicyItems = async () => {
        setLoader(true)
        try {
            const role = sessionStorage.getItem("roleType");
            
            const openPolicyDBData = await OpenWorkService.getOpenWorkItemdata();
            setLoader(false)
            setPolicyDBItem(openPolicyDBData.data)
            console.log("openPolicyDBData.data",openPolicyDBData.data)
        } catch (error) {
            setLoader(false)
            setErrorModal({ show: true, title: "Network Error", body: "Unable to load the details" })

        }
    };

    useEffect(() => {
        if (!!policyDBItem.length) {
            openPolicyRows();
        }
    }, [policyDBItem])
    // const LinkCellRenderer = (params) => {
    //     return (
    //       <a href={params.value.url} target="_blank" rel="noopener noreferrer">
    //         {params.value.file_name}
    //       </a>
    //     );
    //   };
    const openPolicyRows = () => {
        const inputdata=policyDBItem.map((item)=>item.input)
        
        console.log("inputdata",inputdata)
        const Viewdoc=(googleDocsUrl)=>{
<iframe
                      src={googleDocsUrl}
                      title="Document Viewer"
                      style={{ height: '100%', width: '100%' }}
                      frameBorder="0"
                      sandbox="allow-same-origin allow-scripts allow-top-navigation allow-popups allow-forms"
                    />
        }
        const column = [
            { field: 'policyNumber', headerName: 'Sumission Id' },
            // { field: 'insuredName' },
            // { field: 'state' },
             { field: 'createdDate', filter: 'agDateColumnFilter' },
             { field: 'URL',
                headerName: 'URL',
                cellRenderer: (params) => {
                  const url = params.value;
                  const onClick = () => {
                    const googleDocsUrl = `https://docs.google.com/gview?url=${url}&embedded=true`;
                    const viewerWindow = window.open('', 'Document Viewer', 'width=800,height=600');
                    viewerWindow.document.write(
                        `<iframe src="${googleDocsUrl}" title="Document Viewer" style="height: 100%; width: 100%;" frameBorder="0" sandbox="allow-same-origin allow-scripts allow-top-navigation allow-popups allow-forms"></iframe>`
                    );
                };
                return (
                    <button className="btn blue" onClick={onClick}>View Document {params.value.file_name}</button>
                );
            },
              },
            
            // { field: 'premium' },
            { field: 'action', cellRenderer: (item) => actionButton(item.data.action) },
            

        ]
        setColumnDefs(column)
        const updatedRowData = policyDBItem.map((item,index) => {
            console.log("policyDBItem",policyDBItem)
            
            // const urldata=item.input.map(data=><a style={{ color: 'blue' }} href={data.url} target="_blank" rel="noopener noreferrer">
            //     {data.file_name}
            // </a>)
            const url=item.url;
            return {
                id: index,
                policyNumber: item.submission_id.slice(-5),
                // insuredName: item.name,
                // state: item.email,
                createdDate: item.last_modified,
                URL:url
      
                  ,
                  // premium: item.company.name,
                  action: item
                  
                
            }
        })
        setRowData(updatedRowData)
    }

    const policyExpReportRows = () => {
        const column = [
            { field: 'quoteNumber', headerName: 'Policy Number' },
            { field: 'effDate', filter: 'agDateColumnFilter' },
            { field: 'expiDate', filter: 'agDateColumnFilter' },
            { field: 'insuredName' },
            { field: 'state' },
            { field: 'product' },
        ]
        setColumnDefs(column)
        const updatedRowData = openWorkDBItem.map((item) => {
            let year = new Date(item.effectiveDate).getFullYear();
            let month = new Date(item.effectiveDate).getMonth();
            let day = new Date(item.effectiveDate).getDate();
            let expDate = new Date(year + 1, month, day);
            let expirationDate = expDate.toISOString().slice(0, 10)
            if (item.quoteNumber !== "") {
                return {
                    id: item.quoteNumber,
                    quoteNumber: item.quoteNumber,
                    effDate: item.effectiveDate,
                    expiDate: expirationDate,
                    insuredName: item.insuredName,
                    state: item.stateCd,
                    product: item.product,
                }
            } else {
                return null
            }
        })
        const filteredRowData = updatedRowData.filter((item) => item != null)
        setRowData(filteredRowData)
    }


    const loadLossNoticeItems = async () => {
        setLoader(true)
        try {
            const role = sessionStorage.getItem("roleType");
            const lossNoticeItemDBData = await OpenWorkService.getLossNoticeDBItem(role, headers);
            setLoader(false)
            setLossNoticeDBItem(lossNoticeItemDBData.data)
        } catch (error) {
            setLoader(false)
            setErrorModal({ show: true, title: "Network Error", body: "Unable to load the details" })

        }
    };

    useEffect(() => {
        if (!!lossNoticeDBItem.length) {
            openlossNoticeRows();
        }
    }, [lossNoticeDBItem])

    const openlossNoticeRows = () => {
        const column = [
            { field: 'id', headerName: 'Loss Notice Number' },
            {field:'policyNumber',headerName: 'Policy Number'},
            { field:'insureName', headerName: 'Insured Name'},
            { field:'state', headerName: 'State'},
            { field: 'reportDt', filter: 'agDateColumnFilter'},
            { field: 'ptype', headerName: 'Product Type'},
            
        ]
        setColumnDefs(column)
        const updatedRowData = lossNoticeDBItem.map((item) => {
             const lossNoticeDataJson = JSON.parse(item.lossNoticeDataJson);
            return {
                id: item.lossNoticeNumber,
                policyNumber:lossNoticeDataJson.policyNumber,
                insureName:lossNoticeDataJson.insuredName,
                state:lossNoticeDataJson.insuredState,
                reportDt: item.reportedDt,
                ptype:lossNoticeDataJson.productType
            }
        })
        setRowData(updatedRowData)
    }


    const defaultColDef = useMemo(() => {
        return {
            flex: 1,
            filter: true,
            floatingFilter: true,
            sortable: false,
            resizable: true,
            suppressResize: true,
            editable: false,
        };
    }, []);

    const getRowId = useMemo(() => {
        return (params) => {
            return params.data.id;
        };
    }, []);

    const onGridReady = useCallback((params) => {
        setGridApi(params.api);
    }, []);

    const updateFilter = () => {
        if (gridApi) {
            gridApi.setFilterModel(null);
        }
    };

    const handleCreatePolicy = async (item) => {
        
        const premium=item.premium.split(" ")
        
        let indicationData=JSON.parse(item.indicationData)
        indicationData.application.policyPremiumInfo["fullTermAmount"] = premium[0] 
        indicationData.application.policyPremiumInfo["locator"] = item.quoteNumber
        const data = indicationData 
        var policyType = indicationData.application.policyInfo.policyType
        var responseData = "";
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(data),
            secretPass
        ).toString();
        try {
            if (policyType === 'Commercial Auto') {
                setLoader(true)
                responseData = await PolicyService.createPolicy(data1, headers);
                
                if (responseData.data !== null && responseData.data !== undefined && responseData.data !== "") {
                    
                    setErrorModal({ show: true, title: "Policy Success Message", body: responseData.data })
                    loadOpenWorkItems();
                    setLoader(false)
                } else {
                    setLoader(false)
                    setErrorModal({ show: true, title: "Couldn't create policy", body: "Some Error!" })
                }
            } else if (policyType === 'Commercial Property') {
                setLoader(true)
                responseData = await PolicyService.createPolicy(data1, headers);
                if (responseData.data !== null && responseData.data !== undefined && responseData.data !== "") {
                
                    setErrorModal({ show: true, title: "Policy Success Message", body: responseData.data })
                    loadOpenWorkItems();
                    setLoader(false)
                } else {
                    setLoader(false)
                    setErrorModal({ show: true, title: "Couldn't create policy", body: "Some Error!" })
                }
            }
            else {
                setLoader(true)
                
                responseData = await PolicyService.createPolicy(data1, headers);
                if (responseData.data !== null && responseData.data !== undefined && responseData.data !== "") {
                   
                    setErrorModal({ show: true, title: "Policy Success Message", body: responseData.data })
                    setLoader(false)
                    loadOpenWorkItems();
                } else {
                    setLoader(false)
                    setErrorModal({ show: true, title: "Couldn't create policy", body: "Some Error!" })
                }
            }
        }
        catch (error) {
            setLoader(false)
            if (error == 'Error: Network Error') {
                useHistory.push("/SystemMaintenance");
            }
            else {
                
                setErrorModal({ show: true, title: "Policy Error Message", body: error.response.data.error })
               
            }
        }
    }

    const endorseOpenWork = async (policyNumber) => {
        setLoader(true)
        const data1 = CryptoJS.AES.encrypt(
          JSON.stringify(policyNumber),
          secretPass
        ).toString();
        try {
          const responseData = await OpenWorkService.submitOpenWorkItem(data1, headers);
          setLoader(false)
          if (responseData.data.includes('Error')) {
            setErrorModal({ show: true, title: "Error Message", body: responseData.data })
          } else {
            loadOpenWorkItems();
            setErrorModal({ show: true, title: "Success Message", body: "Quote Number Generated :: " + responseData.data })
          }
    
        } catch (error) {
          setLoader(false)
        }
      }

    const submitOpenWork = async (appNumber) => {
        setLoader(true)
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(appNumber),
            secretPass
        ).toString();
        try {
            const responseData = await OpenWorkService.submitOpenWorkItem(data1, headers);
            setLoader(false)
            if (responseData.data.includes('Error')) {
                setErrorModal({ show: true, title: "Error Message", body: responseData.data })
            } else {
                loadOpenWorkItems();
                setErrorModal({ show: true, title: "Success Message", body: "Quote Number Generated :: " + responseData.data })
            }

        } catch (error) {
            setLoader(false)
        }
    }

    const deleteOpenWork = async (appNumber) => {
        setLoader(true)
        const data1 = CryptoJS.AES.encrypt(
            JSON.stringify(appNumber),
            secretPass
        ).toString();
        try {
            const responseData = await OpenWorkService.deleteOpenWorkItem(data1, headers);
            setLoader(false)
            if (responseData.data.includes('Error')) {
                setErrorModal({ show: true, title: "Error Message", body: responseData.data })
            } else {
                setErrorModal({ show: true, title: "Success Message", body: "Record deleted successfully! " + responseData.data })
                loadOpenWorkItems();
            }

        } catch (error) {
            setLoader(false)
        }
    }

    return (
        <Layout TabName='MY TRANSACTIONS' BreadCrum={['My Dashboard']} sidebarQQSopen='true'>
            <div style={containerStyle}>
                <div className="example-wrapper">
                    <div className="demtable">
                        <ul className="tabMenu">
                            {/* <li onClick={() => setTimeout(() => {
                                openWorkRows()
                                setActiveTab('submission')
                            }, 0)} className={`item ${activeTab == 'submission' ? 'active' : ''}`}><a href="javascript:void(0);" className="actv"><span><img src={open_work_icon} /></span>Submission</a></li> */}
                            <li onClick={() => {
                                setTimeout(() => {
                                    loadPolicyItems();
                                    openPolicyRows()
                                    setActiveTab('policyList')
                                }, 0)
                            }} className={`item ${activeTab == 'policyList' ? 'active' : ''}`}><a href="javascript:void(0);" ><span><img src={open_work_icon} /></span> Submission</a></li>
                            <li onClick={() => {
                                setTimeout(() => {
                                    policyExpReportRows()
                                    setActiveTab('policyReport')
                                }, 0)
                            }} className={`item ${activeTab == 'policyReport' ? 'active' : ''}`}><a href="javascript:void(0);" ><span><img src={recent_view_icon} /></span>Cases</a></li>
                           {/* <li onClick={() => {
                                loadLossNoticeItems();
                                openlossNoticeRows();
                                setActiveTab('lossNoticeReport');
                            }} className={`item ${activeTab == 'lossNoticeReport' ? 'active' : ''}`}><a href="javascript:void(0);"><span><img src={recent_view_icon} /></span> Loss Notice Report</a></li> */}
                        </ul>
                        <div className='filterbtn'>
                            <button onClick={updateFilter}>Clear Filter</button>
                        </div>
                    </div>
                    <div style={gridStyle} className="ag-theme-alpine">
                        <AgGridReact
                            ref={gridRef}
                            rowData={rowData}
                            columnDefs={columnDefs}
                            defaultColDef={defaultColDef}
                            animateRows={true}
                            getRowId={getRowId}
                            pagination={true}
                            paginationPageSize={10}
                            onGridReady={onGridReady}
                            // frameworkComponents={{ linkCellRenderer: LinkCellRenderer }}
                            // noRowsOverlay={noRowsOverlay}
                            gridOptions={{
                                customNoRowsOverlay: true,
                                overlayNoRowsTemplate: '<span style="position:absolute;top:50%;padding:2%;font-weight:600;" class="ag-overlay-loading-center">No Rows to display</span>',
                                suppressAutoSize: true,
                                suppressAggFilteredOnly: true,
                                suppressBrowserResizeObserver: true,
                                minBodyHeight: 10,
                                domLayout: 'autoHeight'
                            }}
                        />
                    </div>
                </div>
            </div>
            <ErrorMessageModal showModal={modalDate.show} modalTitle={modalDate.title} modalBody={modalDate.body} setShowModal={setErrorModal} />
            <GenerateQuoteModal showModal={generateQuote} setShowModal={setGenerateQuote} submitOpenWork={submitOpenWork} />
            <DeleteQuoteModal showModal={deleteQuote} setShowModal={setDeleteQuote} deleteOpenWork={deleteOpenWork} />
            <EndorsePolicyModal showModal={endorsePolicy} setShowModal={setEndorsePolicy} endorseOpenWork={endorseOpenWork} />
            <PurchasePolicyModal showModal={policyDetail} setShowModal={setPolicyDetail} selectedItem={selectedItem} />
        </Layout>
    );
};

export default MyWorkBench;
