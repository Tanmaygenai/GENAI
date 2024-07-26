package com.exavalu.agentportal.integrations.vindecoder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.squareup.okhttp.Response;
import org.json.JSONException;
import org.json.JSONObject;

import com.exavalu.agentportal.model.Vin;
public class VinDecoderResponseMapper {
	public static List<String> vehicleYear = new ArrayList<>();
	public static List<String> vehiclePrice = new ArrayList<>();
	static {
		loadVehicleYear();
		loadVehicleCost();
	}
	
public Vin tranformFromApiResponse(Vin vin,Response response) throws JSONException, IOException{
		
		String jsonData = response.body().string();
	    JSONObject vehObject = new JSONObject(jsonData);
	    String responseStatus = null;
	    try{
	    	responseStatus = vehObject.get("status").toString();
	    }catch(Exception e){
	    	System.out.println("");
	    }
	    if(responseStatus!=null){
	    	if(responseStatus.equalsIgnoreCase("NOT_FOUND") || responseStatus.equalsIgnoreCase("BAD_REQUEST")){
		    	vin.setResponseStatus("FAILURE");
		    	vin.setResponseStatusMessage(responseStatus +" - "+ vehObject.get("message").toString());
		    }
	    }else{
	    	vin.setResponseStatus("SUCCESS");
		    vin.setMake(vehObject.getJSONObject("make").get("name").toString());
		    vin.setModel(vehObject.getJSONObject("model").get("name").toString());
		    vin.setYear(getRandomVehicleYear());
		    vin.setBodyType(vehObject.getJSONObject("categories").get("primaryBodyType").toString());
		    vin.setCostNew(getRandomVehicleCost());
		    vin.setResponseStatusMessage("API CALL SUCCESS");
	    }
	    
		return vin;
	}
	

	public static void loadVehicleYear(){
		vehicleYear.add("2011");
		vehicleYear.add("2012");
		vehicleYear.add("2013");
		vehicleYear.add("2014");
		vehicleYear.add("2016");
		vehicleYear.add("2018");
		vehicleYear.add("2019");
		vehicleYear.add("2020");
		vehicleYear.add("2021");
	}
	
	public static void loadVehicleCost(){
		vehiclePrice.add("25000");
		vehiclePrice.add("35000");
		vehiclePrice.add("45000");
		vehiclePrice.add("55000");
		vehiclePrice.add("22000");
		vehiclePrice.add("33000");
		vehiclePrice.add("19000");
		vehiclePrice.add("15000");
		vehiclePrice.add("24000");
	}
	
	public static String getRandomVehicleYear() {
	    int randomInt = new Random().nextInt(vehicleYear.size());
	    return vehicleYear.get(randomInt);
	}
	
	public static String getRandomVehicleCost() {
	    int randomInt = new Random().nextInt(vehiclePrice.size());
	    return vehiclePrice.get(randomInt);
	}
}
