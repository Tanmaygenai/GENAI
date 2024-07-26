package com.exavalu.agentportal.model;

import java.util.ArrayList;
import java.util.List;

public class DataDownloadDetails {

	public List<Object[]> getDataDownloadReport() {
		return dataDownloadDetail;
	}

	public void setDataDownloadDetails(List<Object[]> dataDownloadDetail) {
		this.dataDownloadDetail = dataDownloadDetail;
	}

	private List<Object[]> dataDownloadDetail = new ArrayList<Object[]>();


	private String indication_data;


	public String getIndication_data() {
		return indication_data;
	}

	public void setIndication_data(String indication_data) {
		this.indication_data = indication_data;
	}



}
