package prg.restsender.mains;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import prg.restsender.RestSender;

public class PayWithJpMain {

	public static void main(String[] args) {
		System.out.println("Start PayWithJpMain " + new Date());
		
		String identifier ="4477898116590411750001";
		String mediaTypeIdentifier = "PAR_QUBE_BARCODE";
		String entityId = "3";
		BigDecimal amount = new BigDecimal("10.50");
		long timestamp = System.currentTimeMillis();
		short managementSystemGmt = Short.valueOf("120");
		
		var restSender = new RestSender();
		Map<String, Object> map = new HashMap<String,Object>();
		
		StringBuilder url = new StringBuilder(RestSender.PAYWITHJP_PUSH);
		
		url.append("?identifier=");
		url.append(identifier);
		
		url.append("&mediaTypeIdentifier=");
		url.append(mediaTypeIdentifier);
		
		url.append("&entityId=");
		url.append(entityId);

		url.append("&amount=");
		url.append(amount);
		
		url.append("&timestamp=");
		url.append(timestamp);
		
		url.append("&managementSystemGmt=");
		url.append(managementSystemGmt);
		
		restSender.postForJanusConnectorWithResponse(url.toString(), map);
		System.out.println("End PayWithJpMain " + new Date());
	}
}
