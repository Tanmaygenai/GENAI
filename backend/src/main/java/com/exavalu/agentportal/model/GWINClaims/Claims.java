package com.exavalu.agentportal.model.GWINClaims;

import java.util.ArrayList;
import java.util.List;


public class Claims {


    private Integer totalNoOfClaims;

    private List<ClaimsListBean> claimsListBean = new ArrayList<ClaimsListBean>();


    public Integer getTotalNoOfClaims() {
        return totalNoOfClaims;
    }


    public void setTotalNoOfClaims(Integer totalNoOfClaims) {
        this.totalNoOfClaims = totalNoOfClaims;
    }


    public List<ClaimsListBean> getClaimsListBean() {
        return claimsListBean;
    }


    public void setClaimsListBean(List<ClaimsListBean> claimsListBean) {
        this.claimsListBean = claimsListBean;
    }


}
