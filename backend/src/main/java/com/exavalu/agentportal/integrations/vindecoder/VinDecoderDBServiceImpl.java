package com.exavalu.agentportal.integrations.vindecoder;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.json.JSONException;
import java.io.IOException;
import com.exavalu.agentportal.model.Vin;
import com.squareup.okhttp.Response;

@Service
@Qualifier("db")
public class VinDecoderDBServiceImpl implements VinDecoderService{

	@Override
	public Vin getVehicleInfo(Vin vinDetails, String apiUrl, String apiKey) {
		VinDecoderRequestMapper requestMapper = new VinDecoderRequestMapper();
		String reqBody = requestMapper.tranformToApiRequest(vinDetails);
		
		VinDecoderClient decoder = new VinDecoderClient();
		Response response = decoder.calVinDecoderService(reqBody,vinDetails,apiUrl,apiKey);
		VinDecoderResponseMapper mapper = new VinDecoderResponseMapper();
		try {
			mapper.tranformFromApiResponse(vinDetails, response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vinDetails;
	}

}
