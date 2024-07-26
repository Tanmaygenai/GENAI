package com.exavalu.agentportal.model;

public class Vin {
	 String vin;
	    String year;
	    String make;
	    String model;
	    String bodyType;
	    String costNew;
	    String responseStatus;
	    String responseStatusMessage;
	    
	    
	    @Override
		public String toString() {
			return "Vin [vin=" + vin + ", responseStatus=" + responseStatus + ", year=" + year + ", make=" + make
					+ ", model=" + model + "]";
		}
		
	    public String getResponseStatus() {
			return responseStatus;
		}
		public void setResponseStatus(String responseStatus) {
			this.responseStatus = responseStatus;
		}
		
		public String getVin() {
			return vin;
		}
		public void setVin(String vin) {
			this.vin = vin;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getMake() {
			return make;
		}
		public void setMake(String make) {
			this.make = make;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public String getBodyType() {
			return bodyType;
		}
		public void setBodyType(String bodyType) {
			this.bodyType = bodyType;
		}
		public String getCostNew() {
			return costNew;
		}
		public void setCostNew(String costNew) {
			this.costNew = costNew;
		}

		public String getResponseStatusMessage() {
			return responseStatusMessage;
		}

		public void setResponseStatusMessage(String responseStatusMessage) {
			this.responseStatusMessage = responseStatusMessage;
		}

	   

}
