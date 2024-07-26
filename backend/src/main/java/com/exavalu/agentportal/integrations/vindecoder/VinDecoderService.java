package com.exavalu.agentportal.integrations.vindecoder;

import com.exavalu.agentportal.model.Vin;

public interface VinDecoderService {
	Vin getVehicleInfo(Vin vinDetails, String vinApiUrl, String vinApiKey);
}
