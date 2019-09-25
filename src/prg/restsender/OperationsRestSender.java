package prg.restsender;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OperationsRestSender {

	public static void main(String[] args) {
		System.out.println("Start OperationsRestSender " + new Date());
		var restSender = new RestSender();
		Map<String, Object> operations = new HashMap<String, Object>();
		Map<String, Object> operation = new HashMap<String, Object>(); 
		
		operation.put("operationType", "ENTRANCE");
		operation.put("identifier", Calendar.getInstance().getTime().getTime());
		operation.put("entityId", "3"); 
		operation.put("facilityCode", "123");
		operation.put("managementSystemGmt", Short.valueOf("120"));
		operation.put("result", "VALID");// = ValidationResult.VALID;
		operation.put("timestamp", Calendar.getInstance().getTime().getTime());
		operation.put("managementSystemRejectionCode", "rejcode");
		operation.put("additionalInformation", "additional information");
		operation.put("joinType", "SUCCESSFUL_ENTRY");	// = JoinType.SUCCESSFUL_ENTRY;
		operation.put("presenceEntityId", "20");
		operation.put("plate", "AA123BB");
		operation.put("joinAmount", new BigDecimal(3));
		
		Map<String, Object> card = new HashMap<String, Object>();
		card.put("type","SEASONAL");
		card.put("mediaType", "LICENSE_PLATE");	// MediaTypeIdentifier.LICENSE_PLATE;
		card.put("identifier", "542_identifier");
		
		operation.put("card", card);
		operations.put("operations", Arrays.asList(operation));
		
		restSender.postForJanusConnector(RestSender.OPERATION_PUSH_URL, operations);
		System.out.println("End OperationsRestSender " + new Date());

	}

}
