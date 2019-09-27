package prg.restsender.mains;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import prg.restsender.RestSender;
@SuppressWarnings("unused")
public class OperationsRestSender {
	
	private static Map<String, Object> getPaymentOperation(){
		Map<String, Object> operation = new HashMap<String, Object>(); 
		
		operation.put("operationType", "PAYMENT");
		operation.put("identifier", Calendar.getInstance().getTime().getTime());
		operation.put("entityId", "3"); 
		operation.put("facilityCode", "123");
		operation.put("managementSystemGmt", Short.valueOf("120"));
		operation.put("result", "VALID");// = ValidationResult.VALID;
		operation.put("timestamp", Calendar.getInstance().getTime().getTime());
		operation.put("managementSystemRejectionCode", "rejcode");
		operation.put("additionalInformation", "additional information");
		operation.put("paymentOperationType", "COIN_INSERTED");
		operation.put("paymentType", "CASH");
		operation.put("rechargedValue", 13d);
		operation.put("sessionId",3L);
		operation.put("toPay", 10.0);
		operation.put("payed", 9.0);
		operation.put("amount", 8.0);
		operation.put("userCategoryIdentifier", 10L);
		operation.put("transactionId", "aaa123tran");
		operation.put("periodFrom", Calendar.getInstance().getTime().getTime());
		operation.put("periodFromGmt", 120);
		operation.put("periodTo", Calendar.getInstance().getTime().getTime());
		operation.put("periodToGmt" , 120);
		operation.put("payerName", "ciaociao");
		operation.put("batchNumber", 12345);
		operation.put("invoiceNumber", 123L);
		operation.put("paymentId", 555L);
		operation.put("notes", "notes");
		operation.put("surcharge", new BigDecimal("111.321"));
		operation.put("offlineCleared", true); 
		Map<String, Object> card = new HashMap<String, Object>();
		card.put("type","SEASONAL");
		card.put("mediaType", "PAR_QUBE_PROXIMITY");	
		card.put("identifier", "9093_identifier");
		
		operation.put("card", card);
		
		return operation;
	}

	private static Map<String, Object> getJoinOperation(){
		Map<String, Object> operation = new HashMap<String, Object>(); 
		
		operation.put("operationType", "ENTRANCE");		//ENTRANCE //EXIT
		operation.put("identifier", Calendar.getInstance().getTime().getTime());
		operation.put("entityId", "3"); 
		operation.put("facilityCode", "123");
		operation.put("managementSystemGmt", Short.valueOf("120"));
		operation.put("result", "VALID");// = ValidationResult.VALID;
		operation.put("timestamp", Calendar.getInstance().getTime().getTime());
		operation.put("managementSystemRejectionCode", "rejcode");
		operation.put("additionalInformation", "additional information");
		operation.put("joinType", "SUCCESSFUL_ENTRY");	// SUCCESSFUL_ENTRY;	//SUCCESSFUL_EXIT
		operation.put("presenceEntityId", "20");
		operation.put("plate", "AA123BB");
		operation.put("joinAmount", new BigDecimal(3));
		
		Map<String, Object> card = new HashMap<String, Object>();
		card.put("type","TRANSIENT");
		card.put("mediaType", "LICENSE_PLATE");	// MediaTypeIdentifier.LICENSE_PLATE;
		card.put("identifier", "tra_tra_mmm_001");
		
		operation.put("card", card);
		
		return operation;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Start OperationsRestSender " + new Date());
		var restSender = new RestSender();
		Map<String, Object> operations = new HashMap<String, Object>();
		Map<String, Object> operation = getJoinOperation(); //getPaymentOperation();	//getJoinOperation
		operations.put("operations", Arrays.asList(operation));
		
		restSender.postForJanusConnector(RestSender.OPERATION_PUSH_URL, operations);
		System.out.println("End OperationsRestSender " + new Date());
	}

}
