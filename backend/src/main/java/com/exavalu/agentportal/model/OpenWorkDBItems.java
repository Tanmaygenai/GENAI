package com.exavalu.agentportal.model;

import com.exavalu.agentportal.model.db.OpenWork;

import java.util.ArrayList;
import java.util.List;

public class OpenWorkDBItems {

    private List<OpenWork> openWorkList = new ArrayList<>();

    public List<OpenWork> getOpenWorkList() {
        return openWorkList;
    }

    public void setOpenWorkList(List<OpenWork> openWorkList) {
        this.openWorkList = openWorkList;
    }
}
