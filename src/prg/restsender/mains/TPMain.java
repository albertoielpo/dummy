package prg.restsender.mains;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import prg.restsender.RestSender;

public class TPMain {

	public static void main(String[] args) {
		System.out.println("Start Verification " + new Date());
		var restSender = new RestSender();
		Map<String, Object> payload = new HashMap<String,Object>();
		payload.put("username", "janus");
		payload.put("password", "janus");	//??
		restSender.postForJanusConnectorWithResponse(RestSender.TP_LOGIN, payload);
		System.out.println("End Verification " + new Date());
	}
}
