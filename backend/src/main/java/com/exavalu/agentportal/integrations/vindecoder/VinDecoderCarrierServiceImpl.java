package com.exavalu.agentportal.integrations.vindecoder;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.model.Vin;
@Service
@Qualifier("carrier")
public class VinDecoderCarrierServiceImpl implements VinDecoderService {

	@Override
	public Vin getVehicleInfo(Vin vinDetails,String apiUrl, String apiKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
