package com.exavalu.agentportal.integrations.vindecoder;
import java.io.IOException;

import com.exavalu.agentportal.model.Vin;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
public class VinDecoderClient {
	public Response calVinDecoderService(String reqBody, Vin vinDetails, String apiUrl, String apiKey) {
		OkHttpClient client = new OkHttpClient();
		Response response = null;
		Request request = new Request.Builder()
		  .url(apiUrl+vinDetails.getVin()+"?"+apiKey)
		  .get()
		  .addHeader("content-type", "text/xml")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("postman-token", "68bf2d88-4357-1428-4a08-1f81c4da3da2")
		  .build();

		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
