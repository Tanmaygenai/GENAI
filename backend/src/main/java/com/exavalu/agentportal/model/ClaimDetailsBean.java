package com.exavalu.agentportal.model;

import java.util.ArrayList;
import java.util.List;

public class ClaimDetailsBean {

    private Integer totalNoOfClaims;
    private List<ClaimDetails> claimsListBean = new ArrayList<ClaimDetails>();
    public Integer getTotalNoOfClaims() {
        return totalNoOfClaims;
    }
    public void setTotalNoOfClaims(Integer totalNoOfClaims) {
        this.totalNoOfClaims = totalNoOfClaims;
    }
    public List<ClaimDetails> getClaimsListBean() {
        return claimsListBean;
    }
    public void setClaimsListBean(List<ClaimDetails> claimsListBean) {
        this.claimsListBean = claimsListBean;
    }
}
