package com.exavalu.agentportal.carrier.socotra;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.exavalu.agentportal.model.DRCExcessQuickIndication.Driver;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.InsuranceLine;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.InsuredInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.Risk;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SocotraRequestMapper {
	
	public String buildAuthenticateRequestBody(String socotraUsername, String socotraPassword, String socotraHostName) {
	    JsonObject requestBodyJson = new JsonObject();
	    if (socotraUsername == null || socotraUsername.isEmpty()) {
	        return "Username is null or empty.";
	    }
	    if (socotraPassword == null || socotraPassword.isEmpty()) {
	        return "Password is null or empty.";
	    }
	    if (socotraHostName == null || socotraHostName.isEmpty()) {
	        return "HostName is null or empty.";
	    }
	    requestBodyJson.addProperty("username", socotraUsername);
	    requestBodyJson.addProperty("password", socotraPassword);
	    requestBodyJson.addProperty("hostName", socotraHostName);

	    return requestBodyJson.toString();
	}
	
	
	public String buildPolicyHolderRequestBody(QuickIndication quickIndicationDetails) {
	    if (quickIndicationDetails == null) {
	        return "QuickIndication object is null.";
	    }
	    InsuredInfo insuredInfo = quickIndicationDetails.getApplication() != null ? quickIndicationDetails.getApplication().getInsuredInfo() : null;

	    if (insuredInfo == null) {
	        return "InsuredInfo in Application is null.";
	    }

	    String givenName = insuredInfo.getGivenName();
	    String surName = insuredInfo.getSurName();
	    String dob = insuredInfo.getDob();
	    String gender = insuredInfo.getGender();
	    String occupation = insuredInfo.getOccupation();
	    String maritalStatus = insuredInfo.getMaritalStatus();
	    String entityType = insuredInfo.getEntityType();

	    if (givenName == null || givenName.isEmpty()) {
	        return "givenName in InsuredInfo is null or empty.";
	    }
	    
	    if (surName == null || surName.isEmpty()) {
	        return "SurName in InsuredInfo is null or empty.";
	    }

	    if (dob == null || dob.isEmpty()) {
	        return "Date of Birth in InsuredInfo is null or empty.";
	    }

	    if (gender == null || gender.isEmpty()) {
	        return "SurName in InsuredInfo is null or empty.";
	    }

	    if (occupation == null || occupation.isEmpty()) {
	        return "Date of Birth in InsuredInfo is null or empty.";
	    }
	    
	    if (maritalStatus == null || maritalStatus.isEmpty()) {
	        return "SurName in InsuredInfo is null or empty.";
	    }
	    
		/*
		 * if (entityType == null || entityType.isEmpty()) { return
		 * "entityType in InsuredInfo is null or empty."; }
		 */

	    // Create a JSON object to represent the policy request body
	    JsonObject requestBodyJson = new JsonObject();

	    // Extract values from the QuickIndication object and populate the JSON
	    JsonObject valuesJson = new JsonObject();
	    valuesJson.addProperty("first_name", givenName);
	    valuesJson.addProperty("last_name", surName);
	    valuesJson.addProperty("date_of_birth", dob);
	    valuesJson.addProperty("gender", gender);
	    valuesJson.addProperty("occupation", occupation);
	    valuesJson.addProperty("marital_status", maritalStatus);
	    valuesJson.addProperty("marital_status", maritalStatus);
	    //valuesJson.addProperty("entity_type", entityType);

	    // Add the values JSON to the main request body JSON
	    requestBodyJson.add("values", valuesJson);

	    // Convert the JSON object to a string
	    return requestBodyJson.toString();
	}

	
	public String convertAndFormatDate(String inputDate) {
        try {
            // Create a SimpleDateFormat for the input date format
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            
            // Parse the input date string to a Date object
            Date date = inputDateFormat.parse(inputDate);
            
            // Create a SimpleDateFormat for the desired output format
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            
            // Format the Date object to the desired output format
            String formattedDate = outputDateFormat.format(date);
            
            // Replace hyphens with empty spaces
            formattedDate = formattedDate.replaceAll("-", "");
           
            return formattedDate;
        } catch (ParseException e) {
            // Handle parsing exceptions here
            e.printStackTrace();
            return "Invalid Date Format";
        }
    }
	
	public String buildPolicyRequestBody(String locator, QuickIndication quickIndicationDetails) {
	    // Create a JSON object to represent the policy request body
		if (quickIndicationDetails == null) {
	        return "QuickIndication object is null.";
	    }
		if (locator == null || locator.isEmpty()) {
	        return "Locator is null or empty.";
	    }
		List<InsuranceLine> insuranceLines = quickIndicationDetails.getApplication().getInsuranceLine();
	    if (insuranceLines == null || insuranceLines.isEmpty()) {
	        return "InsuranceLine list is null or empty.";
	    }

	    InsuranceLine insuranceLine = insuranceLines.get(0);
	    if (insuranceLine == null) {
	        return "First InsuranceLine object is null.";
	    }

	    String lineCd = insuranceLine.getLineCd();
	    if (lineCd == null || lineCd.isEmpty()) {
	        return "LineCd in InsuranceLine is null or empty.";
	    }
	    
	    if (!lineCd.equalsIgnoreCase("Commercial Auto")) {
	        return "This product type is not available!";
	    }
	    
	    List<Risk> risks = insuranceLine.getRisks();
	    if (risks == null || risks.isEmpty()) {
	        return "Risk list is null or empty.";
	    }

	    List<Driver> drivers = quickIndicationDetails.getApplication().getDrivers();
	    if (drivers == null || drivers.isEmpty()) {
	        return "Driver list is null or empty.";
	    }
	    
	    InsuredInfo insuredInfo = quickIndicationDetails.getApplication().getInsuredInfo();
	    if (insuredInfo == null) {
	        return "InsuredInfo object is null.";
	    }

		/*
		 * String entityType = insuredInfo.getEntityType(); if (entityType == null ||
		 * entityType.isEmpty()) { return "EntityType in InsuredInfo is null or empty.";
		 * }
		 */
	    JsonObject requestBodyJson = new JsonObject();

	    // Set policyholderLocator, productName, and fieldValues
	    requestBodyJson.addProperty("policyholderLocator", locator);
	    requestBodyJson.addProperty("productName", quickIndicationDetails.getApplication().getInsuranceLine().get(0).getLineCd());
	    //requestBodyJson.addProperty("entity_type", quickIndicationDetails.getApplication().getInsuredInfo().getEntityType());


	    // Create a JSON object for fieldValues
	    JsonObject fieldValuesJson = new JsonObject();
	    fieldValuesJson.addProperty("liability_limit", quickIndicationDetails.getApplication().getInsuranceLine().get(0).getLiabilityLimit());
	    fieldValuesJson.addProperty("medical_payments", quickIndicationDetails.getApplication().getInsuranceLine().get(0).getMedicalPayments());
	    fieldValuesJson.addProperty("towing_and_labor", quickIndicationDetails.getApplication().getInsuranceLine().get(0).getTowingAndLaborInd());
	    fieldValuesJson.addProperty("rental_reimbursement", quickIndicationDetails.getApplication().getInsuranceLine().get(0).getRentalReimbursementInd());
	    fieldValuesJson.addProperty("primary_business_type", quickIndicationDetails.getApplication().getInsuredInfo().getPrimaryBusinessInfo().getBusinessType());
	    fieldValuesJson.addProperty("primary_business_zip", quickIndicationDetails.getApplication().getInsuredInfo().getPrimaryBusinessInfo().getPrimaryOperationsZip());
	    //fieldValuesJson.addProperty("entity_type", quickIndicationDetails.getApplication().getInsuredInfo().getEntityType());

	    // Add fieldValues to the main request body JSON
	    requestBodyJson.add("fieldValues", fieldValuesJson);

	    // Create an array for exposures
	    JsonArray exposuresArray = new JsonArray();

	    // Loop through vehicles and create exposures
	    for (Risk risk : quickIndicationDetails.getApplication().getInsuranceLine().get(0).getRisks()) {
	        JsonObject vehiclesExposureJson = new JsonObject();
	        vehiclesExposureJson.addProperty("exposureName", "Vehicles");
	        String vehicleType = risk.getVehicleInfo().getVehicleType();
	        String modelYear = risk.getVehicleInfo().getModelYear();
	        String vin = risk.getVehicleInfo().getVin();
	        String manufacturer = risk.getVehicleInfo().getManufacturer();
	        String model = risk.getVehicleInfo().getModel();
	        String garageAddress = risk.getVehicleInfo().getGarageAddress().getAddr1();
	        String registeredState = risk.getVehicleInfo().getRegisteredState();
	        String vehicleUse = risk.getVehicleInfo().getVehicleUse();
	        String vehicleOwnership = risk.getVehicleInfo().getVehicleOwnership();
	        String mainDriver = risk.getVehicleInfo().getMainDriverofVehicle();
	        String comprehensiveDed = risk.getVehicleInfo().getComprehensiveDeductible();
	        String collisionDed = risk.getVehicleInfo().getCollisionDeductible();
	        String costNew = risk.getVehicleInfo().getCostNew();
	        
	        if (vehicleType == null || vehicleType.isEmpty()) {
		        return "Vehicle Type in Risk is null or empty.";
		    }
	        
	        if (modelYear == null || modelYear.isEmpty()) {
		        return "Model Year in Risk is null or empty.";
		    }
	        
	        if (vin == null || vin.isEmpty()) {
		        return "Vin in Risk is null or empty.";
		    }
	        
	        if (manufacturer == null || manufacturer.isEmpty()) {
		        return "Manufacturer in Risk is null or empty.";
		    }
	        
	        if (model == null || model.isEmpty()) {
		        return "Model in Risk is null or empty.";
		    }
	        
	        if (garageAddress == null || garageAddress.isEmpty()) {
		        return "Garage Address in Risk is null or empty.";
		    }
	        
	        if (registeredState == null || registeredState.isEmpty()) {
	        	return "Registered State in Risk is null or empty.";
	        }
	        
	        if (vehicleUse == null || vehicleUse.isEmpty()) {
	        	return "Vehicle Use in Risk is null or empty.";
	        }
	        
	        if (vehicleOwnership == null || vehicleOwnership.isEmpty()) {
	        	return "vehicle Ownership in Risk is null or empty.";
	        }
	        
	        if(mainDriver == null || mainDriver.isEmpty()) {
	        	return "Main Driver in Risk is null or empty.";
	        }
	        
	        if(comprehensiveDed == null || comprehensiveDed.isEmpty()) {
	        	return "Comprehensive Deductible in Risk is null or empty.";
	        }
	        
	        if(collisionDed == null || collisionDed.isEmpty()) {
	        	return "collision Deductible in Risk is null or empty.";
	        }
	        
	        if(costNew == null || costNew.isEmpty()) {
	        	return "Cost New in Risk is null or empty.";
	        }
	        
	        // Create a JSON object for fieldValues within Vehicles
	        JsonObject vehiclesFieldValuesJson = new JsonObject();
	        vehiclesFieldValuesJson.addProperty("vehicle_type", vehicleType);
	        vehiclesFieldValuesJson.addProperty("year", modelYear);
	        vehiclesFieldValuesJson.addProperty("vin", vin);
	        vehiclesFieldValuesJson.addProperty("make", manufacturer);
	        vehiclesFieldValuesJson.addProperty("model", model);
	        vehiclesFieldValuesJson.addProperty("garage_address", garageAddress);
	        vehiclesFieldValuesJson.addProperty("registered_state", registeredState);
	        vehiclesFieldValuesJson.addProperty("vehicle_use", vehicleUse);
	        vehiclesFieldValuesJson.addProperty("vehicle_ownership", vehicleOwnership);
	        vehiclesFieldValuesJson.addProperty("main_driver_of_vehicle", mainDriver);
	        vehiclesFieldValuesJson.addProperty("comprehensive_deductible", "$"+comprehensiveDed);
	        vehiclesFieldValuesJson.addProperty("collision_deductible", collisionDed);
	        vehiclesFieldValuesJson.addProperty("cost_new", costNew);

	        // Add fieldValues to the Vehicles exposure
	        vehiclesExposureJson.add("fieldValues", vehiclesFieldValuesJson);

	        // Add Vehicles exposure to the exposures array
	        exposuresArray.add(vehiclesExposureJson);
	    }

	    // Loop through drivers and create exposures
	    for (Driver driver : quickIndicationDetails.getApplication().getDrivers()) {
	        JsonObject driversExposureJson = new JsonObject();
	        driversExposureJson.addProperty("exposureName", "Drivers");
	        String driverGivenName = driver.getGivenName();
	        String driverSurName = driver.getSurname();
	        String driverDob = convertAndFormatDate(driver.getBirthDt());
	        int driverAge =  driver.getAge();
	        String licenseNumber = driver.getLicenseNumber();
	        String licenseState = driver.getLicenseState();
	        
	        if (driverGivenName == null || driverGivenName.isEmpty()) {
	            return "Driver GivenName is null or empty.";
	        }

	        if (driverSurName == null || driverSurName.isEmpty()) {
	            return "Driver SurName is null or empty.";
	        }

	        if (driverDob == null || driverDob.isEmpty()) {
	            return "Driver Date of Birth is null or empty.";
	        }

	        if (driverAge <= 0) {
	            return "Driver Age is not valid.";
	        }

	        if (licenseNumber == null || licenseNumber.isEmpty()) {
	            return "Driver License Number is null or empty.";
	        }

	        if (licenseState == null || licenseState.isEmpty()) {
	            return "Driver License State is null or empty.";
	        }
	        // Create a JSON object for fieldValues within Drivers
	        JsonObject driversFieldValuesJson = new JsonObject();
	        driversFieldValuesJson.addProperty("first_name", driverGivenName);
	        driversFieldValuesJson.addProperty("last_name", driverSurName);
	        driversFieldValuesJson.addProperty("date_of_birth", driverDob);
	        driversFieldValuesJson.addProperty("age", driverAge);
	        driversFieldValuesJson.addProperty("license_number", licenseNumber);
	        driversFieldValuesJson.addProperty("license_state", licenseState);

	        // Add fieldValues to the Drivers exposure
	        driversExposureJson.add("fieldValues", driversFieldValuesJson);

	        // Add Drivers exposure to the exposures array
	        exposuresArray.add(driversExposureJson);
	    }

	    // Add the exposures array to the main request body JSON
	    requestBodyJson.add("exposures", exposuresArray);

	    // Convert the JSON object to a string
	    return requestBodyJson.toString();
	}
	
	public String buildEndorsementRequestBody(QuickIndication quickIndicationDetails, String policyLocator, JsonNode jsongetPolicyNode) {
		 // Create a JSON object to represent the endorsement request body
		 		if (quickIndicationDetails == null) {
		 	        return "QuickIndication object is null.";
		 	    }
		 		if (policyLocator == null || policyLocator.isEmpty()) {
		 	        return "PolicyLocator is null or empty.";
		 	    }
		 		List<InsuranceLine> insuranceLines = quickIndicationDetails.getApplication().getInsuranceLine();
		 	    if (insuranceLines == null || insuranceLines.isEmpty()) {
		 	        return "InsuranceLine list is null or empty.";
		 	    }

		 	    InsuranceLine insuranceLine = insuranceLines.get(0);
		 	    if (insuranceLine == null) {
		 	        return "First InsuranceLine object is null.";
		 	    }

		 	    String lineCd = insuranceLine.getLineCd();
		 	    if (lineCd == null || lineCd.isEmpty()) {
		 	        return "LineCd in InsuranceLine is null or empty.";
		 	    }
		 	    
		 	    if (!lineCd.equalsIgnoreCase("Commercial Auto")) {
		 	        return "This product type is not available!";
		 	    }
		 	    
		 	    List<Risk> risks = insuranceLine.getRisks();
		 	    if (risks == null || risks.isEmpty()) {
		 	        return "Risk list is null or empty.";
		 	    }

		 	    List<Driver> drivers = quickIndicationDetails.getApplication().getDrivers();
		 	    if (drivers == null || drivers.isEmpty()) {
		 	        return "Driver list is null or empty.";
		 	    }
		 	    JsonObject endorsementRequestBodyJson = new JsonObject();

		 	    // Set policyholderLocator, productName, and fieldValues
		 	    endorsementRequestBodyJson.addProperty("endorsementName", "generic");
		 	   	endorsementRequestBodyJson.addProperty("state", "accepted");

		 	    // Create a JSON object for fieldValues
		 	    JsonObject fieldValuesJson = new JsonObject();
		 	    fieldValuesJson.addProperty("liability_limit", quickIndicationDetails.getApplication().getInsuranceLine().get(0).getLiabilityLimit());
		 	    fieldValuesJson.addProperty("medical_payments", quickIndicationDetails.getApplication().getInsuranceLine().get(0).getMedicalPayments());
		 	    fieldValuesJson.addProperty("towing_and_labor", quickIndicationDetails.getApplication().getInsuranceLine().get(0).getTowingAndLaborInd());
		 	    fieldValuesJson.addProperty("rental_reimbursement", quickIndicationDetails.getApplication().getInsuranceLine().get(0).getRentalReimbursementInd());
		 	    fieldValuesJson.addProperty("primary_business_type", quickIndicationDetails.getApplication().getInsuredInfo().getPrimaryBusinessInfo().getBusinessType());
		 	    fieldValuesJson.addProperty("primary_business_zip", quickIndicationDetails.getApplication().getInsuredInfo().getPrimaryBusinessInfo().getPrimaryOperationsZip());

		 	    // Add fieldValues to the main request body JSON
		 	    endorsementRequestBodyJson.add("fieldValues", fieldValuesJson);

				JsonNode policyDetails1 = jsongetPolicyNode.get("exposures");
				JsonNode policyExposuresArray = policyDetails1;
				List<JsonNode> filteredDriverNodes = new ArrayList<>();
				List<String> policyLicenseNumbers = new ArrayList<>();
				List<JsonNode> filteredVehicleNodes = new ArrayList<>();
				List<String> policyVinNumbers = new ArrayList<>();
				String policyLicenseNumber = "";
				String policyVinNumber = "";

				for (JsonNode exposureNode : policyExposuresArray) {
					JsonNode nameNode = exposureNode.get("name");
					if (nameNode != null && "Drivers".equals(nameNode.asText())) {
						filteredDriverNodes.add(exposureNode);
					}else if(nameNode != null && "Vehicles".equals(nameNode.asText())) {
						filteredVehicleNodes.add(exposureNode);
					}
				}
				
				if(filteredDriverNodes.size() >0 && filteredDriverNodes!= null) {
				for (JsonNode driverNode : filteredDriverNodes) {
					policyLicenseNumber = driverNode.get("characteristics").get(0).get("fieldValues").get("license_number").get(0).asText();
		 	        policyLicenseNumbers.add(policyLicenseNumber);
				}
				}
				if(filteredVehicleNodes!= null && filteredVehicleNodes.size() >0) {
				for (JsonNode vehicleNode : filteredVehicleNodes) {
					policyVinNumber = vehicleNode.get("characteristics").get(0).get("fieldValues").get("vin").get(0).asText();
					policyVinNumbers.add(policyVinNumber);
				}
				}
				
				
		 	    // Create an array for add exposures
		 	    JsonArray addExposuresArray = new JsonArray();

		 	    // Loop through vehicles and create exposures
		 	    for (Risk risk : quickIndicationDetails.getApplication().getInsuranceLine().get(0).getRisks()) {
		 	        JsonObject vehiclesExposureJson = new JsonObject();
		 	        vehiclesExposureJson.addProperty("exposureName", "Vehicles");
		 	        String vin = risk.getVehicleInfo().getVin();
		 	        if(!policyVinNumbers.contains(vin)) {
		 	        String vehicleType = risk.getVehicleInfo().getVehicleType();
		 	        String modelYear = risk.getVehicleInfo().getModelYear();
		 	        String manufacturer = risk.getVehicleInfo().getManufacturer();
		 	        String model = risk.getVehicleInfo().getModel();
		 	        String garageAddress = risk.getVehicleInfo().getGarageAddress().getAddr1();
		 	        String registeredState = risk.getVehicleInfo().getRegisteredState();
		 	        String vehicleUse = risk.getVehicleInfo().getVehicleUse();
		 	        String vehicleOwnership = risk.getVehicleInfo().getVehicleOwnership();
		 	        String mainDriver = risk.getVehicleInfo().getMainDriverofVehicle();
		 	        String comprehensiveDed = risk.getVehicleInfo().getComprehensiveDeductible();
		 	        String collisionDed = risk.getVehicleInfo().getCollisionDeductible();
		 	        String costNew = risk.getVehicleInfo().getCostNew();
		 	        
		 	        if (vehicleType == null || vehicleType.isEmpty()) {
		 		        return "Vehicle Type in Risk is null or empty.";
		 		    }
		 	        
		 	        if (modelYear == null || modelYear.isEmpty()) {
		 		        return "Model Year in Risk is null or empty.";
		 		    }
		 	        
		 	        if (vin == null || vin.isEmpty()) {
		 		        return "Vin in Risk is null or empty.";
		 		    }
		 	        
		 	        if (manufacturer == null || manufacturer.isEmpty()) {
		 		        return "Manufacturer in Risk is null or empty.";
		 		    }
		 	        
		 	        if (model == null || model.isEmpty()) {
		 		        return "Model in Risk is null or empty.";
		 		    }
		 	        
		 	        if (garageAddress == null || garageAddress.isEmpty()) {
		 		        return "Garage Address in Risk is null or empty.";
		 		    }
		 	        
		 	        if (registeredState == null || registeredState.isEmpty()) {
		 	        	return "Registered State in Risk is null or empty.";
		 	        }
		 	        
		 	        if (vehicleUse == null || vehicleUse.isEmpty()) {
		 	        	return "Vehicle Use in Risk is null or empty.";
		 	        }
		 	        
		 	        if (vehicleOwnership == null || vehicleOwnership.isEmpty()) {
		 	        	return "vehicle Ownership in Risk is null or empty.";
		 	        }
		 	        
		 	        if(mainDriver == null || mainDriver.isEmpty()) {
		 	        	return "Main Driver in Risk is null or empty.";
		 	        }
		 	        
		 	        if(comprehensiveDed == null || comprehensiveDed.isEmpty()) {
		 	        	return "Comprehensive Deductible in Risk is null or empty.";
		 	        }
		 	        
		 	        if(collisionDed == null || collisionDed.isEmpty()) {
		 	        	return "collision Deductible in Risk is null or empty.";
		 	        }
		 	        
		 	        if(costNew == null || costNew.isEmpty()) {
		 	        	return "Cost New in Risk is null or empty.";
		 	        }
		 	        
		 	        // Create a JSON object for fieldValues within Vehicles
		 	        JsonObject vehiclesFieldValuesJson = new JsonObject();
		 	        vehiclesFieldValuesJson.addProperty("vehicle_type", vehicleType);
		 	        vehiclesFieldValuesJson.addProperty("year", modelYear);
		 	        vehiclesFieldValuesJson.addProperty("vin", vin);
		 	        vehiclesFieldValuesJson.addProperty("make", manufacturer);
		 	        vehiclesFieldValuesJson.addProperty("model", model);
		 	        vehiclesFieldValuesJson.addProperty("garage_address", garageAddress);
		 	        vehiclesFieldValuesJson.addProperty("registered_state", registeredState);
		 	        vehiclesFieldValuesJson.addProperty("vehicle_use", vehicleUse);
		 	        vehiclesFieldValuesJson.addProperty("vehicle_ownership", vehicleOwnership);
		 	        vehiclesFieldValuesJson.addProperty("main_driver_of_vehicle", mainDriver);
		 	        vehiclesFieldValuesJson.addProperty("comprehensive_deductible", "$"+comprehensiveDed);
		 	        vehiclesFieldValuesJson.addProperty("collision_deductible", collisionDed);
		 	        vehiclesFieldValuesJson.addProperty("cost_new", costNew);

		 	        // Add fieldValues to the Vehicles exposure
		 	        vehiclesExposureJson.add("fieldValues", vehiclesFieldValuesJson);

		 	        // Add Vehicles exposure to the exposures array
		 	       addExposuresArray.add(vehiclesExposureJson);
		 	        }
		 	    }
		 	    
				// Loop through drivers and create exposures
				for (Driver driver : quickIndicationDetails.getApplication().getDrivers()) {
		 	        JsonObject driversExposureJson = new JsonObject();
		 	        driversExposureJson.addProperty("exposureName", "Drivers");
		 	        String licenseNumber = driver.getLicenseNumber();
		 	        if(!policyLicenseNumbers.contains(licenseNumber)) {
	 	        		String driverGivenName = driver.getGivenName();
			 	        String driverSurName = driver.getSurname();
			 	        String driverDob = convertAndFormatDate(driver.getBirthDt());
			 	        int driverAge =  driver.getAge();
			 	        String licenseState = driver.getLicenseState();
			 	        
			 	        if (driverGivenName == null || driverGivenName.isEmpty()) {
			 	            return "Driver GivenName is null or empty.";
			 	        }

			 	        if (driverSurName == null || driverSurName.isEmpty()) {
			 	            return "Driver SurName is null or empty.";
			 	        }

			 	        if (driverDob == null || driverDob.isEmpty()) {
			 	            return "Driver Date of Birth is null or empty.";
			 	        }

			 	        if (driverAge <= 0) {
			 	            return "Driver Age is not valid.";
			 	        }

			 	        if (licenseNumber == null || licenseNumber.isEmpty()) {
			 	            return "Driver License Number is null or empty.";
			 	        }

			 	        if (licenseState == null || licenseState.isEmpty()) {
			 	            return "Driver License State is null or empty.";
			 	        }
			 	        // Create a JSON object for fieldValues within Drivers
			 	        JsonObject driversFieldValuesJson = new JsonObject();
			 	        driversFieldValuesJson.addProperty("first_name", driverGivenName);
			 	        driversFieldValuesJson.addProperty("last_name", driverSurName);
			 	        driversFieldValuesJson.addProperty("date_of_birth", driverDob);
			 	        driversFieldValuesJson.addProperty("age", driverAge);
			 	        driversFieldValuesJson.addProperty("license_number", licenseNumber);
			 	        driversFieldValuesJson.addProperty("license_state", licenseState);

			 	        // Add fieldValues to the Drivers exposure
			 	        driversExposureJson.add("fieldValues", driversFieldValuesJson);

			 	        // Add Drivers exposure to the exposures array
			 	       addExposuresArray.add(driversExposureJson);
			 	    }
			}

		 	    // Add the exposures array to the main request body JSON
		 	    endorsementRequestBodyJson.add("addExposures", addExposuresArray);
		 	    
				
/*				 // Create an array for update exposures 
		 	    JsonArray updateExposuresArray = new JsonArray();
				 
				 // Loop through drivers and create exposures 
		 	    for (Driver driver : quickIndicationDetails.getApplication().getDrivers()){ 
				JsonObject driversExposureJson = new JsonObject();
				driversExposureJson.addProperty("exposureName", "Drivers"); 
				String licenseNumber = driver.getLicenseNumber();
				if(policyLicenseNumbers.contains(licenseNumber)) { 
					String driverGivenName = driver.getGivenName(); 
					String driverSurName = driver.getSurname(); 
					String driverDob = convertAndFormatDate(driver.getBirthDt()); 
					int driverAge =	driver.getAge(); 
					String licenseState = driver.getLicenseState();
					String policyLicenseNumr = "";
					String policyDriverGivenName = ""; 
					String policyDriverSurName = ""; 
					String policyDriverDob = ""; 
					int policyDriverAge = 0; 
					String policyLicenseState = "";
					String exposureLocator = "";
					
					for(JsonNode driverNode : filteredDriverNodes) {
						policyLicenseNumr = driverNode.get("characteristics").get(0).get("fieldValues").get("license_number").get(0).asText();
						if(licenseNumber.equals(policyLicenseNumr)) {
							policyDriverGivenName = driverNode.get("characteristics").get(0).get("fieldValues").get("first_name").get(0).asText();
							policyDriverSurName = driverNode.get("characteristics").get(0).get("fieldValues").get("last_name").get(0).asText();
							policyDriverDob = driverNode.get("characteristics").get(0).get("fieldValues").get("date_of_birth").get(0).asText();
							policyDriverAge = driverNode.get("characteristics").get(0).get("fieldValues").get("age").get(0).asInt();
							policyLicenseState = driverNode.get("characteristics").get(0).get("fieldValues").get("license_state").get(0).asText();
							policyLicenseNumr = driverNode.get("characteristics").get(0).get("fieldValues").get("license_number").get(0).asText();
							exposureLocator = driverNode.get("characteristics").get(0).get("exposureLocator").asText();
							break;
						}
					}
					 
					 if (driverGivenName == null || driverGivenName.isEmpty()) 
					 { 
						 return "Driver GivenName is null or empty."; 
					 }
					 
					 if (driverSurName == null || driverSurName.isEmpty()) 
					 { 
						 return "Driver SurName is null or empty."; 
					 }
					 
					 if (driverDob == null || driverDob.isEmpty()) 
					 { 
						 return "Driver Date of Birth is null or empty."; 
					 }
					 
					 if (driverAge <= 0) 
					 { 
						 return "Driver Age is not valid."; 
					 }
					 
					 if (licenseNumber == null || licenseNumber.isEmpty()) 
					 { 
						 return "Driver License Number is null or empty."; 
					 }
					 
					 if (licenseState == null || licenseState.isEmpty()) 
					 { 
						 return "Driver License State is null or empty."; 
					 } 
					 // Create a JSON object forfieldValues within Drivers 
					 JsonObject driversFieldValuesJson = new JsonObject(); 
					 if(!policyLicenseNumr.equals(licenseNumber)) {
						 driversFieldValuesJson.addProperty("license_number", licenseNumber);
					 }
					 if(!policyDriverGivenName.equals(driverGivenName)) {
						 driversFieldValuesJson.addProperty("first_name", driverGivenName);
					 }
					 if(!policyDriverSurName.equals(driverSurName)) {
						 driversFieldValuesJson.addProperty("last_name", driverSurName);
					 }
					 if(!policyDriverDob.equals(driverDob)) {
						 driversFieldValuesJson.addProperty("date_of_birth", driverDob);
					 }
					 if(policyDriverAge != driverAge) {
						 driversFieldValuesJson.addProperty("age", driverAge);
					 }
					 if(!policyLicenseState.equals(licenseState)) {
						 driversFieldValuesJson.addProperty("license_state", licenseState);
					 }
					 driversExposureJson.addProperty("exposureLocator", exposureLocator);
					 //driversFieldValuesJson.addProperty("first_name", driverGivenName); 
					 //driversFieldValuesJson.addProperty("last_name", driverSurName); 
					 //driversFieldValuesJson.addProperty("date_of_birth", driverDob); 
					 //driversFieldValuesJson.addProperty("age", driverAge);
					 //driversFieldValuesJson.addProperty("license_number", licenseNumber);
					 //driversFieldValuesJson.addProperty("license_state", licenseState);
					 
					 // Add fieldValues to the Drivers exposure
					 driversExposureJson.add("fieldValues", driversFieldValuesJson);
					  
					 // Add Drivers exposure to the exposures array
					 updateExposuresArray.add(driversExposureJson); 
					 } 
				}
		 	    
		 	   // Loop through vehicles and create exposures 
		 	    for (Risk risk : quickIndicationDetails.getApplication().getInsuranceLine().get(0).getRisks()) {
		 	        JsonObject vehiclesExposureJson = new JsonObject();
		 	        vehiclesExposureJson.addProperty("exposureName", "Vehicles");
		 	        String vin = risk.getVehicleInfo().getVin();
		 	        if(policyVinNumbers.contains(vin)) {
		 	        String vehicleType = risk.getVehicleInfo().getVehicleType();
		 	        String modelYear = risk.getVehicleInfo().getModelYear();
		 	        String manufacturer = risk.getVehicleInfo().getManufacturer();
		 	        String model = risk.getVehicleInfo().getModel();
		 	        String garageAddress = risk.getVehicleInfo().getGarageAddress().getAddr1();
		 	        String registeredState = risk.getVehicleInfo().getRegisteredState();
		 	        String vehicleUse = risk.getVehicleInfo().getVehicleUse();
		 	        String vehicleOwnership = risk.getVehicleInfo().getVehicleOwnership();
		 	        String mainDriver = risk.getVehicleInfo().getMainDriverofVehicle();
		 	        String comprehensiveDed = risk.getVehicleInfo().getComprehensiveDeductible();
		 	        String collisionDed = risk.getVehicleInfo().getCollisionDeductible();
		 	        String costNew = risk.getVehicleInfo().getCostNew();
		 	        
		 	        String policyVin = "";
		 	        String policyVehicleType = "";
		 	        String policyModelYear = "";
		 	        String policyManufacturer = "";
		 	        String policyPolicyModel = "";
		 	        String policyGarageAddress = "";
		 	        String policyRegisteredState = "";
		 	        String policyVehicleUse = "";
		 	        String policyVehicleOwnership = "";
		 	        String policyMainDriver = "";
		 	        String policyComprehensiveDed = "";
		 	        String policyComprehensiveDedStr = "";
		 	        String policyCollisionDed = "";
		 	        String policyCostNew = "";
		 	        String exposureLocator = "";
		 	        
		 	       for(JsonNode vehicleNode : filteredVehicleNodes) {
	 	    	   		 policyVin = vehicleNode.get("characteristics").get(0).get("fieldValues").get("vin").get(0).asText();
	 	    	   		 if(policyVin.equals(vin)) {
			 	    	   	 policyVehicleType = vehicleNode.get("characteristics").get(0).get("fieldValues").get("vehicle_type").get(0).asText();
				 	         policyModelYear = vehicleNode.get("characteristics").get(0).get("fieldValues").get("year").get(0).asText();
				 	         policyManufacturer = vehicleNode.get("characteristics").get(0).get("fieldValues").get("make").get(0).asText();
				 	         policyPolicyModel = vehicleNode.get("characteristics").get(0).get("fieldValues").get("model").get(0).asText();
				 	         policyGarageAddress = vehicleNode.get("characteristics").get(0).get("fieldValues").get("garage_address").get(0).asText();
				 	         policyRegisteredState = vehicleNode.get("characteristics").get(0).get("fieldValues").get("registered_state").get(0).asText();
				 	         policyVehicleUse = vehicleNode.get("characteristics").get(0).get("fieldValues").get("vehicle_use").get(0).asText();
				 	         policyVehicleOwnership = vehicleNode.get("characteristics").get(0).get("fieldValues").get("vehicle_ownership").get(0).asText();
				 	         policyMainDriver = vehicleNode.get("characteristics").get(0).get("fieldValues").get("main_driver_of_vehicle").get(0).asText();
				 	         policyComprehensiveDed = vehicleNode.get("characteristics").get(0).get("fieldValues").get("comprehensive_deductible").get(0).asText();
				 	         policyComprehensiveDedStr = policyComprehensiveDed.replace("$", "");
				 	         policyCollisionDed = vehicleNode.get("characteristics").get(0).get("fieldValues").get("collision_deductible").get(0).asText();
				 	         policyCostNew = vehicleNode.get("characteristics").get(0).get("fieldValues").get("cost_new").get(0).asText();
				 	         policyVin = vehicleNode.get("characteristics").get(0).get("fieldValues").get("vin").get(0).asText();
				 	         exposureLocator = vehicleNode.get("characteristics").get(0).get("exposureLocator").asText();
				 	         break;
	 	    	   		 }
		 	       }
		 	        
		 	        if (vehicleType == null || vehicleType.isEmpty()) {
		 		        return "Vehicle Type in Risk is null or empty.";
		 		    }
		 	        
		 	        if (modelYear == null || modelYear.isEmpty()) {
		 		        return "Model Year in Risk is null or empty.";
		 		    }
		 	        
		 	        if (vin == null || vin.isEmpty()) {
		 		        return "Vin in Risk is null or empty.";
		 		    }
		 	        
		 	        if (manufacturer == null || manufacturer.isEmpty()) {
		 		        return "Manufacturer in Risk is null or empty.";
		 		    }
		 	        
		 	        if (model == null || model.isEmpty()) {
		 		        return "Model in Risk is null or empty.";
		 		    }
		 	        
		 	        if (garageAddress == null || garageAddress.isEmpty()) {
		 		        return "Garage Address in Risk is null or empty.";
		 		    }
		 	        
		 	        if (registeredState == null || registeredState.isEmpty()) {
		 	        	return "Registered State in Risk is null or empty.";
		 	        }
		 	        
		 	        if (vehicleUse == null || vehicleUse.isEmpty()) {
		 	        	return "Vehicle Use in Risk is null or empty.";
		 	        }
		 	        
		 	        if (vehicleOwnership == null || vehicleOwnership.isEmpty()) {
		 	        	return "vehicle Ownership in Risk is null or empty.";
		 	        }
		 	        
		 	        if(mainDriver == null || mainDriver.isEmpty()) {
		 	        	return "Main Driver in Risk is null or empty.";
		 	        }
		 	        
		 	        if(comprehensiveDed == null || comprehensiveDed.isEmpty()) {
		 	        	return "Comprehensive Deductible in Risk is null or empty.";
		 	        }
		 	        
		 	        if(collisionDed == null || collisionDed.isEmpty()) {
		 	        	return "collision Deductible in Risk is null or empty.";
		 	        }
		 	        
		 	        if(costNew == null || costNew.isEmpty()) {
		 	        	return "Cost New in Risk is null or empty.";
		 	        }
		 	        
		 	        // Create a JSON object for fieldValues within Vehicles
		 	        JsonObject vehiclesFieldValuesJson = new JsonObject();
	 	        	 if(!policyVehicleType.equals(vehicleType)) {
	 	        		vehiclesFieldValuesJson.addProperty("vehicle_type", vehicleType);
					 }
					 if(!policyModelYear.equals(modelYear)) {
						 vehiclesFieldValuesJson.addProperty("year", modelYear);
					 }
					 if(!policyVin.equals(vin)) {
						 vehiclesFieldValuesJson.addProperty("vin", vin);
					 }
					 if(!policyManufacturer.equals(manufacturer)) {
						 vehiclesFieldValuesJson.addProperty("make", manufacturer);
					 }
					 if(!policyPolicyModel.equals(model)) {
						 vehiclesFieldValuesJson.addProperty("model", model);
					 }
					 if(!policyGarageAddress.equals(garageAddress)) {
						 vehiclesFieldValuesJson.addProperty("garage_address", garageAddress);
					 }
					 if(!policyRegisteredState.equals(registeredState)) {
						 vehiclesFieldValuesJson.addProperty("registered_state", registeredState);
					 }
					 if(!policyVehicleUse.equals(vehicleUse)) {
						 vehiclesFieldValuesJson.addProperty("vehicle_use", vehicleUse);
					 }
					 if(!policyVehicleOwnership.equals(vehicleOwnership)) {
						 vehiclesFieldValuesJson.addProperty("vehicle_ownership", vehicleOwnership);
					 }
					 if(!policyMainDriver.equals(mainDriver)) {
						 vehiclesFieldValuesJson.addProperty("main_driver_of_vehicle", mainDriver);
					 }
					 if(!policyComprehensiveDedStr.equals(comprehensiveDed)) {
						 vehiclesFieldValuesJson.addProperty("comprehensive_deductible", "$"+comprehensiveDed);
					 }
					 if(!policyCollisionDed.equals(collisionDed)) {
						 vehiclesFieldValuesJson.addProperty("collision_deductible", collisionDed);
					 }
					 if(!policyCostNew.equals(costNew)) {
						 vehiclesFieldValuesJson.addProperty("cost_new", costNew);
					 }
					 vehiclesExposureJson.addProperty("exposureLocator", exposureLocator);
		 	        
//		 	        // Create a JSON object for fieldValues within Vehicles
//		 	        JsonObject vehiclesFieldValuesJson = new JsonObject();
//		 	        vehiclesFieldValuesJson.addProperty("vehicle_type", vehicleType);
//		 	        vehiclesFieldValuesJson.addProperty("year", modelYear);
//		 	        vehiclesFieldValuesJson.addProperty("vin", vin);
//		 	        vehiclesFieldValuesJson.addProperty("make", manufacturer);
//		 	        vehiclesFieldValuesJson.addProperty("model", model);
//		 	        vehiclesFieldValuesJson.addProperty("garage_address", garageAddress);
//		 	        vehiclesFieldValuesJson.addProperty("registered_state", registeredState);
//		 	        vehiclesFieldValuesJson.addProperty("vehicle_use", vehicleUse);
//		 	        vehiclesFieldValuesJson.addProperty("vehicle_ownership", vehicleOwnership);
//		 	        vehiclesFieldValuesJson.addProperty("main_driver_of_vehicle", mainDriver);
//		 	        vehiclesFieldValuesJson.addProperty("comprehensive_deductible", "$"+comprehensiveDed);
//		 	        vehiclesFieldValuesJson.addProperty("collision_deductible", collisionDed);
//		 	        vehiclesFieldValuesJson.addProperty("cost_new", costNew);
					 
					 // Add fieldValues to the Drivers exposure
		 	       vehiclesExposureJson.add("fieldValues", vehiclesFieldValuesJson);
					  
					 // Add Drivers exposure to the exposures array
					 updateExposuresArray.add(vehiclesExposureJson); 
					 } 
				}
					 
					 // Add the exposures array to the main request body JSON
					 endorsementRequestBodyJson.add("updateExposures", updateExposuresArray);*/
					 
	
			 	    // Convert the JSON object to a string

		    return endorsementRequestBodyJson.toString();
		}


}
