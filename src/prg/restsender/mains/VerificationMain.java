package prg.restsender.mains;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import prg.restsender.RestSender;

public class VerificationMain {

	public static void main(String[] args) {
		System.out.println("Start Verification " + new Date());
		var restSender = new RestSender();
		Map<String, Object> verificationMap = new HashMap<String,Object>();
		verificationMap.put("terminal", "terminal");
		verificationMap.put("ticketId", "ticketId");	//??
		verificationMap.put("entryDateTime", 1578697200001L);
		//verificationMap.put("exitDateTime", 1578646894002L);
		verificationMap.put("externalCardId", "100002");		//	//external-ticket-4
		double amount = 15.50;
		verificationMap.put("amount", amount);
		verificationMap.put("verificationType", "ENTRY");
		verificationMap.put("externalTicketMediaType", "BARCODE");
		verificationMap.put("presenceAreaIdentifier", "1");
		restSender.postForJanusConnectorWithResponse(RestSender.VERIFICATION_PUSH, verificationMap);
		System.out.println("End Verification " + new Date());
	}
}
