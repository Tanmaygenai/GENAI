package com.exavalu.agentportal.model.GWINDownload;

public class PDFOutputList {

    private String pdfItemName;
    private String pdfItemRef;

    public PDFOutputList(String pdfName, String pdfRef){
        pdfItemName = pdfName;
        pdfItemRef = pdfRef;
    }
    public PDFOutputList(){}

    public String getPdfItemName() {
        return pdfItemName;
    }

    public void setPdfItemName(String pdfItemName) {
        this.pdfItemName = pdfItemName;
    }

    public String getPdfItemRef() {
        return pdfItemRef;
    }

    public void setPdfItemRef(String pdfItemRef) {
        this.pdfItemRef = pdfItemRef;
    }
}
