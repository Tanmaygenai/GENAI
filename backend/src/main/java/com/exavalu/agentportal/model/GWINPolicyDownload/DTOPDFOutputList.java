package com.exavalu.agentportal.model.GWINPolicyDownload;

public class DTOPDFOutputList {

    private String pdfItemName;
    private String pdfItemURL;

    public DTOPDFOutputList(String pdfName, String pdfURL){
        pdfItemName = pdfName;
        pdfItemURL = pdfURL;
    }
    public DTOPDFOutputList(){}

    public String getPdfItemName() {
        return pdfItemName;
    }

    public void setPdfItemName(String pdfItemName) {
        this.pdfItemName = pdfItemName;
    }

    public String getPdfItemURL() {
        return pdfItemURL;
    }

    public void setPdfItemURL(String pdfItemURL) {
        this.pdfItemURL = pdfItemURL;
    }
}
