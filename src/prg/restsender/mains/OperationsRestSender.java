package prg.restsender.mains;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import prg.restsender.RestSender;
import utils.RandomUtils;
import utils.textdb.TextDb;

@SuppressWarnings({"rawtypes", "unused"})
public class OperationsRestSender {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start OperationsRestSender " + new Date());
		var restSender = new RestSender();
		Map<String, Object> operations = new HashMap<String, Object>();

		try {
			operations.put("operations", Arrays.asList(
				getPaymentOperation(TextDb.nextSequence(TextDb.SEQ_OPERATION_IDENTIFIER))
					//getValidationOperation(TextDb.nextSequence(TextDb.SEQ_OPERATION_IDENTIFIER))
			));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

/* Here to use JSON */
//		operations.put("operations", 
//			Arrays.asList(
//				getOperationFromJSON("{\"operationType\":\"EXIT\",\"identifier\":500000000000,\"timestamp\":1569857456000,\"managementSystemGmt\":120,\"additionalInformation\":null,\"sessionId\":null,\"isExternal\":false,\"eventTimestamp\":null,\"eventManagementSystemGmt\":null,\"result\":\"VALID\",\"card\":{\"type\":\"SEASONAL\",\"identifier\":\"05400014102404\",\"mediaType\":\"PAR_QUBE_PROXIMITY\"},\"joinType\":\"CARD_VALIDATION\",\"creditCardData\":null,\"facilityCode\":\"1\",\"entityId\":\"1.1.2.0.66053\",\"plate\":\"NOTREAD\",\"managementSystemRejectionCode\":null,\"presenceEntityId\":\"1.1.2\",\"userCategoryIdentifier\":7,\"dpCardData\":null,\"hotelCardData\":null,\"plateReadConfidence\":null,\"plateCountryCode\":null,\"joinAmount\":null,\"ticketGroupId\":null,\"plateLprClarification\":\"UNDEFINED\",\"plateLprStatusCode\":\"ERROR\",\"external\":false}"), 
//				getOperationFromJSON("{\"operationType\":\"EXIT\",\"identifier\":500000000009,\"timestamp\":1569857456001,\"managementSystemGmt\":120,\"additionalInformation\":null,\"sessionId\":null,\"isExternal\":false,\"eventTimestamp\":null,\"eventManagementSystemGmt\":null,\"result\":\"MOVING_BACK\",\"card\":{\"type\":\"SEASONAL\",\"identifier\":\"05400014102404\",\"mediaType\":\"PAR_QUBE_PROXIMITY\"},\"joinType\":\"UNSUCCESSFUL_EXIT\",\"creditCardData\":null,\"facilityCode\":\"1\",\"entityId\":\"1.1.2.0.66053\",\"plate\":\"NOTREAD\",\"managementSystemRejectionCode\":null,\"presenceEntityId\":\"1.1.2\",\"userCategoryIdentifier\":7,\"dpCardData\":null,\"hotelCardData\":null,\"plateReadConfidence\":null,\"plateCountryCode\":null,\"joinAmount\":null,\"ticketGroupId\":null,\"plateLprClarification\":\"UNDEFINED\",\"plateLprStatusCode\":\"ERROR\",\"external\":false}")
//		));
		
		restSender.postForJanusConnector(RestSender.OPERATION_PUSH_URL, operations);
		System.out.println("End OperationsRestSender " + new Date());
	}
	
	/**
	 * 
	 * @param identifier
	 * @return
	 */
	private static Map<String, Object> getPaymentOperation(long identifier){
		Map<String, Object> operation = new HashMap<String, Object>(); 
		
		operation.put("operationType", "PAYMENT");
		operation.put("identifier", identifier);
		operation.put("entityId", "3"); 
		operation.put("facilityCode", "123");
		operation.put("managementSystemGmt", Short.valueOf("120"));
		operation.put("result", "VALID");// = ValidationResult.VALID;
		operation.put("timestamp", Calendar.getInstance().getTime().getTime());
		operation.put("managementSystemRejectionCode", "rejcode");
		operation.put("additionalInformation", "additional information");
		operation.put("paymentOperationType", "NONE_PARKING_PRODUCT_SOLD");
		operation.put("paymentType", "CASH");
		operation.put("rechargedValue", 13d);
		operation.put("sessionId",3L);
		operation.put("toPay", 10.0);
		operation.put("payed", 9.0);
		operation.put("amount", 8.0);
		operation.put("userCategoryIdentifier", 10L);
		operation.put("transactionId", "transaction id");
		operation.put("noneParkingProductCount", 23);
		operation.put("noneParkingProductName", "noneParkingProductName");
		
		operation.put("periodFrom", Calendar.getInstance().getTime().getTime());
		operation.put("periodFromGmt", 120);
		operation.put("periodTo", Calendar.getInstance().getTime().getTime());
		operation.put("periodToGmt" , 120);
		operation.put("payerName", "payer name");
		operation.put("batchNumber", 12345);
		operation.put("invoiceNumber", 123L);
		operation.put("paymentId", 555L);
		operation.put("notes", "notes");
		operation.put("surcharge", new BigDecimal("111.321"));
		operation.put("offlineCleared", true); 
		operation.put("productProfileType", "BASE_CONTRACT");
		Map<String, Object> card = new HashMap<String, Object>();
		card.put("type","ANONYMOUS");
		card.put("mediaType", "LICENSE_PLATE");	// MediaTypeIdentifier.LICENSE_PLATE;
		card.put("identifier", "anony-000-" + identifier);
		
		operation.put("card", card);
		
		return operation;
	}
	
	private static Map<String, Object> getBillingOperation(long identifier){
		Map<String, Object> operation = new HashMap<String, Object>(); 
		
		operation.put("operationType", "BILLING");
		operation.put("identifier", identifier);
		operation.put("entityId", "3"); 
		operation.put("facilityCode", "123");
		operation.put("managementSystemGmt", Short.valueOf("120"));
		operation.put("result", "VALID");// = ValidationResult.VALID;
		operation.put("timestamp", Calendar.getInstance().getTime().getTime());
		operation.put("additionalInformation", "additional information");
		operation.put("billOriginatorType", "MDCC");
		operation.put("billingOperationType", "ACCOUNT_BILL");
		operation.put("accountIdentifier", "5967");
		operation.put("sessionId",3L);
		operation.put("amount", 10L);
		operation.put("vatAmount",40L);
		operation.put("amountDue", 30L);
		operation.put("productProfileType","BASE_CONTRACT");

		Map<String, Object> card = new HashMap<String, Object>();
		card.put("type","SEASONAL");
		card.put("mediaType", "LICENSE_PLATE");	// MediaTypeIdentifier.LICENSE_PLATE;
		card.put("identifier", "3654_identifier");
		
		operation.put("card", card);
		
		return operation;
	}

	/**
	 * 
	 * @param identifier
	 * @return
	 */
	private static Map<String, Object> getJoinOperation(long identifier){
		Map<String, Object> operation = new HashMap<String, Object>(); 
		
		operation.put("operationType", "ENTRANCE");
		operation.put("identifier", identifier);
		operation.put("entityId", "3");	//3 //1.1.2.0.66053
		operation.put("facilityCode", "123");
		operation.put("managementSystemGmt", Short.valueOf("120"));
		operation.put("result", "VALID");// = ValidationResult.VALID;
		operation.put("timestamp", Calendar.getInstance().getTime().getTime());
		operation.put("managementSystemRejectionCode", "rejcode");
		operation.put("additionalInformation", "additional information");
		operation.put("joinType", "SUCCESSFUL_ENTRY");	// SUCCESSFUL_ENTRY;	//SUCCESSFUL_EXIT
		operation.put("presenceEntityId", "20");
		operation.put("plate", "qq123bb");
		operation.put("joinAmount", new BigDecimal(3));
		
		Map<String, Object> card = new HashMap<String, Object>();
		card.put("type","HOTEL");
		card.put("mediaType", "LICENSE_PLATE");	// MediaTypeIdentifier.LICENSE_PLATE;
		card.put("identifier", "hotel-000-" + identifier);
		
		operation.put("card", card);
		
		return operation;
	}
	
	/**
	 * 
	 * @param identifier
	 * @return
	 */
	private static Map<String,Object> getValidationOperation(long identifier){
		Instant instant = Instant.now();
		//String identifier = String.valueOf(instant.getEpochSecond()) + String.valueOf(instant.getNano());
		
		/** common fields */
		Map<String, Object> operation = new HashMap<String, Object>(); 
		operation.put("operationType", "VALIDATION");
		operation.put("identifier", identifier);
		operation.put("timestamp", Calendar.getInstance().getTime().getTime());
		operation.put("managementSystemGmt", Short.valueOf("120"));
		operation.put("additionalInformation", "addinfo");
		operation.put("sessionId", RandomUtils.randomlong());
		operation.put("isExternal", false);
		operation.put("eventTimestamp", Calendar.getInstance().getTime().getTime());
		operation.put("eventManagementSystemGmt", Short.valueOf("120"));
		operation.put("result","VALID");
		
		/* validation fields */
		operation.put("validationOperationType", "VOUCHER_VALIDATION");
		Map<String, Object> card = new HashMap<String, Object>();
		
		card.put("type","HOTEL");
		card.put("mediaType", "LICENSE_PLATE");	// MediaTypeIdentifier.LICENSE_PLATE;
		card.put("identifier", "hotel-000-" + identifier);
		operation.put("card", card);
		
		operation.put("entityId", "d3");	//3 vp
		operation.put("discountId",null);
		operation.put("shopId", null);
		operation.put("voucherRangeId", null);
	    operation.put("amount", RandomUtils.randomPositiveDouble());
	    operation.put("percentageCharge", RandomUtils.randomPositiveDouble());
	    operation.put("cashierId", null);
	    operation.put("voucherNumber", null);
	    operation.put("generatorId", RandomUtils.randomPositiveLong());
	    operation.put("webValidationId", null);
	    operation.put("merchantAmount", new BigDecimal(RandomUtils.randomPositiveFloat().toString()));
	    operation.put("appliedDiscount", new BigDecimal(RandomUtils.randomPositiveFloat().toString()));
	    operation.put("vatAmount", RandomUtils.randomPositiveDouble());
	    operation.put("transactionId", RandomUtils.randomPositiveInteger().toString());
	    operation.put("paymentType", null);
	    operation.put("externalValidationIdentifier", null);
	    operation.put("hotelCardData", null);
	    return operation;
	}
	
	
	
	/**
	 * 
	 * @param json
	 * @return
	 */
	private static Map getOperationFromJSON(String json) {		
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
