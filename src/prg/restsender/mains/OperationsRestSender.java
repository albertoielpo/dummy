package prg.restsender.mains;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Start OperationsRestSender " + new Date());
		var restSender = new RestSender();
		Map<String, Object> operations = new HashMap<String, Object>();
		//var op = getPaymentOperation(TextDb.nextSequence(TextDb.SEQ_OPERATION_IDENTIFIER));
		var op = getJoinOperation(TextDb.nextSequence(TextDb.SEQ_OPERATION_IDENTIFIER));
		//var op = getValidationOperation(TextDb.nextSequence(TextDb.SEQ_OPERATION_IDENTIFIER));
		operations.put("operations", Arrays.asList(op));
		
		if(false) {
		/* Here to use JSON */
		operations.put("operations", 
			Arrays.asList(
				getOperationFromJSON("{\r\n"
						+ "    \"operationType\": \"PAYMENT\",\r\n"
						+ "    \"identifier\": 6007548517063471,\r\n"
						+ "    \"timestamp\": 1623442294000,\r\n"
						+ "    \"managementSystemGmt\": -300,\r\n"
						+ "    \"additionalInformation\": null,\r\n"
						+ "    \"sessionId\": 656522106111511,\r\n"
						+ "    \"isExternal\": false,\r\n"
						+ "    \"eventTimestamp\": null,\r\n"
						+ "    \"eventManagementSystemGmt\": null,\r\n"
						+ "    \"result\": \"VALID\",\r\n"
						+ "    \"card\": {\r\n"
						+ "      \"type\": \"TRANSIENT\",\r\n"
						+ "      \"identifier\": \"0\",\r\n"
						+ "      \"mediaType\": \"BARCODE\"\r\n"
						+ "    },\r\n"
						+ "    \"creditNoteIdentifier\": null,\r\n"
						+ "    \"paymentOperationType\": \"SINGLESTAY_PAYMENT_COMPLETED\",\r\n"
						+ "    \"paymentType\": \"CREDIT_CARD\",\r\n"
						+ "    \"creditCardData\": {\r\n"
						+ "      \"retCode\": 0,\r\n"
						+ "      \"presenceIdentifier\": null,\r\n"
						+ "      \"cardBrand\": \"VI\",\r\n"
						+ "      \"cardToken\": null,\r\n"
						+ "      \"maskedPan\": \"XXXXXXXXXXXX8366\",\r\n"
						+ "      \"expiryTimestamp\": null,\r\n"
						+ "      \"reference\": \"18040C\",\r\n"
						+ "      \"userReference\": \"A49-26318\",\r\n"
						+ "      \"userReferenceOriginalPayment\": null,\r\n"
						+ "      \"serviceCode\": null,\r\n"
						+ "      \"batchNumber\": null,\r\n"
						+ "      \"rejectionCode\": \"CHARGED\",\r\n"
						+ "      \"displayCardIdentifier\": null,\r\n"
						+ "      \"responseTimestamp\": null,\r\n"
						+ "      \"responseTimestampGmt\": null,\r\n"
						+ "      \"requestTimestamp\": null,\r\n"
						+ "      \"requestTimestampGmt\": null,\r\n"
						+ "      \"transactionToken\": null\r\n"
						+ "    },\r\n"
						+ "    \"facilityCode\": null,\r\n"
						+ "    \"entityId\": \"1.1.2.1.3.5.5.7.6.1\",\r\n"
						+ "    \"amount\": 7,\r\n"
						+ "    \"toPay\": 7,\r\n"
						+ "    \"payed\": 7,\r\n"
						+ "    \"managementSystemRejectionCode\": \"0 - no rejection\",\r\n"
						+ "    \"cashierId\": null,\r\n"
						+ "    \"shiftId\": 0,\r\n"
						+ "    \"transactionId\": null,\r\n"
						+ "    \"protocolNumber\": null,\r\n"
						+ "    \"vatAmount\": 0,\r\n"
						+ "    \"balance\": null,\r\n"
						+ "    \"paidToTimeTimestamp\": null,\r\n"
						+ "    \"paidToTimeTimestampGmt\": null,\r\n"
						+ "    \"userCategoryIdentifier\": null,\r\n"
						+ "    \"accountIdentifier\": null,\r\n"
						+ "    \"dpCardData\": null,\r\n"
						+ "    \"hotelCardData\": null,\r\n"
						+ "    \"rechargedValue\": null,\r\n"
						+ "    \"userId\": null,\r\n"
						+ "    \"ticketGroupId\": 1,\r\n"
						+ "    \"thirdPartyId\": null,\r\n"
						+ "    \"periodFrom\": null,\r\n"
						+ "    \"periodFromGmt\": null,\r\n"
						+ "    \"periodTo\": null,\r\n"
						+ "    \"periodToGmt\": null,\r\n"
						+ "    \"payerName\": null,\r\n"
						+ "    \"batchNumber\": null,\r\n"
						+ "    \"productProfileType\": null,\r\n"
						+ "    \"weeklyDistributionIdentifier\": null,\r\n"
						+ "    \"billingProfileIdentifier\": null,\r\n"
						+ "    \"invoiceNumber\": null,\r\n"
						+ "    \"paymentId\": null,\r\n"
						+ "    \"notes\": null,\r\n"
						+ "    \"surcharge\": null,\r\n"
						+ "    \"fiscalID\": null,\r\n"
						+ "    \"uniqueSN\": null,\r\n"
						+ "    \"paymentStartTime\": null,\r\n"
						+ "    \"paymentStartTimeGMT\": null,\r\n"
						+ "    \"offlineCleared\": true,\r\n"
						+ "    \"noneParkingProductCount\": null,\r\n"
						+ "    \"noneParkingProductName\": null,\r\n"
						+ "    \"isfInvoice\": null,\r\n"
						+ "    \"plate\": null,\r\n"
						+ "    \"plateReadConfidence\": null,\r\n"
						+ "    \"plateCountryCode\": null,\r\n"
						+ "    \"external\": false\r\n"
						+ "  }"),
				getOperationFromJSON("{\r\n"
						+ "    \"operationType\": \"EXIT\",\r\n"
						+ "    \"identifier\": 6007549017063471,\r\n"
						+ "    \"timestamp\": 1623442298000,\r\n"
						+ "    \"managementSystemGmt\": -300,\r\n"
						+ "    \"additionalInformation\": null,\r\n"
						+ "    \"sessionId\": 656522106111511,\r\n"
						+ "    \"isExternal\": false,\r\n"
						+ "    \"eventTimestamp\": null,\r\n"
						+ "    \"eventManagementSystemGmt\": null,\r\n"
						+ "    \"result\": \"VALID\",\r\n"
						+ "    \"card\": {\r\n"
						+ "      \"type\": \"TRANSIENT\",\r\n"
						+ "      \"identifier\": \"0\",\r\n"
						+ "      \"mediaType\": \"BARCODE\"\r\n"
						+ "    },\r\n"
						+ "    \"creditNoteIdentifier\": null,\r\n"
						+ "    \"joinType\": \"SUCCESSFUL_EXIT\",\r\n"
						+ "    \"creditCardData\": null,\r\n"
						+ "    \"facilityCode\": null,\r\n"
						+ "    \"entityId\": \"1.1.2.1.3.5.5.7.6.1\",\r\n"
						+ "    \"plate\": \"<no LPN>\",\r\n"
						+ "    \"managementSystemRejectionCode\": \"0 - no rejection\",\r\n"
						+ "    \"presenceEntityId\": null,\r\n"
						+ "    \"userCategoryIdentifier\": null,\r\n"
						+ "    \"dpCardData\": null,\r\n"
						+ "    \"hotelCardData\": null,\r\n"
						+ "    \"plateReadConfidence\": null,\r\n"
						+ "    \"plateCountryCode\": null,\r\n"
						+ "    \"joinAmount\": null,\r\n"
						+ "    \"ticketGroupId\": null,\r\n"
						+ "    \"plateLprClarification\": \"OK\",\r\n"
						+ "    \"plateLprStatusCode\": \"OK\",\r\n"
						+ "    \"fiscalID\": null,\r\n"
						+ "    \"uniqueSN\": null,\r\n"
						+ "    \"external\": false\r\n"
						+ "  }"),
				getOperationFromJSON("{\r\n"
						+ "    \"operationType\": \"PAYMENT\",\r\n"
						+ "    \"identifier\": 6007549317063471,\r\n"
						+ "    \"timestamp\": 1623442299000,\r\n"
						+ "    \"managementSystemGmt\": -300,\r\n"
						+ "    \"additionalInformation\": null,\r\n"
						+ "    \"sessionId\": 656522106111511,\r\n"
						+ "    \"isExternal\": false,\r\n"
						+ "    \"eventTimestamp\": null,\r\n"
						+ "    \"eventManagementSystemGmt\": null,\r\n"
						+ "    \"result\": \"VALID\",\r\n"
						+ "    \"card\": {\r\n"
						+ "      \"type\": \"TRANSIENT\",\r\n"
						+ "      \"identifier\": \"0\",\r\n"
						+ "      \"mediaType\": \"BARCODE\"\r\n"
						+ "    },\r\n"
						+ "    \"creditNoteIdentifier\": null,\r\n"
						+ "    \"paymentOperationType\": \"RECEIPT_ISSUED\",\r\n"
						+ "    \"paymentType\": \"CREDIT_CARD\",\r\n"
						+ "    \"creditCardData\": null,\r\n"
						+ "    \"facilityCode\": null,\r\n"
						+ "    \"entityId\": \"1.1.2.1.3.5.5.7.6.1\",\r\n"
						+ "    \"amount\": 7,\r\n"
						+ "    \"toPay\": null,\r\n"
						+ "    \"payed\": null,\r\n"
						+ "    \"managementSystemRejectionCode\": \"0\",\r\n"
						+ "    \"cashierId\": null,\r\n"
						+ "    \"shiftId\": null,\r\n"
						+ "    \"transactionId\": \"A0492100032582\",\r\n"
						+ "    \"protocolNumber\": null,\r\n"
						+ "    \"vatAmount\": 0,\r\n"
						+ "    \"balance\": null,\r\n"
						+ "    \"paidToTimeTimestamp\": null,\r\n"
						+ "    \"paidToTimeTimestampGmt\": null,\r\n"
						+ "    \"userCategoryIdentifier\": null,\r\n"
						+ "    \"accountIdentifier\": null,\r\n"
						+ "    \"dpCardData\": null,\r\n"
						+ "    \"hotelCardData\": null,\r\n"
						+ "    \"rechargedValue\": null,\r\n"
						+ "    \"userId\": null,\r\n"
						+ "    \"ticketGroupId\": null,\r\n"
						+ "    \"thirdPartyId\": null,\r\n"
						+ "    \"periodFrom\": null,\r\n"
						+ "    \"periodFromGmt\": null,\r\n"
						+ "    \"periodTo\": null,\r\n"
						+ "    \"periodToGmt\": null,\r\n"
						+ "    \"payerName\": null,\r\n"
						+ "    \"batchNumber\": null,\r\n"
						+ "    \"productProfileType\": null,\r\n"
						+ "    \"weeklyDistributionIdentifier\": null,\r\n"
						+ "    \"billingProfileIdentifier\": null,\r\n"
						+ "    \"invoiceNumber\": null,\r\n"
						+ "    \"paymentId\": null,\r\n"
						+ "    \"notes\": null,\r\n"
						+ "    \"surcharge\": null,\r\n"
						+ "    \"fiscalID\": null,\r\n"
						+ "    \"uniqueSN\": null,\r\n"
						+ "    \"paymentStartTime\": null,\r\n"
						+ "    \"paymentStartTimeGMT\": null,\r\n"
						+ "    \"offlineCleared\": null,\r\n"
						+ "    \"noneParkingProductCount\": null,\r\n"
						+ "    \"noneParkingProductName\": null,\r\n"
						+ "    \"isfInvoice\": null,\r\n"
						+ "    \"plate\": null,\r\n"
						+ "    \"plateReadConfidence\": null,\r\n"
						+ "    \"plateCountryCode\": null,\r\n"
						+ "    \"external\": false\r\n"
						+ "  }"),
				getOperationFromJSON("{\r\n"
						+ "    \"operationType\": \"ENTRANCE\",\r\n"
						+ "    \"identifier\": 6004876017063472,\r\n"
						+ "    \"timestamp\": 1623442320000,\r\n"
						+ "    \"managementSystemGmt\": -300,\r\n"
						+ "    \"additionalInformation\": null,\r\n"
						+ "    \"sessionId\": 323552106111512,\r\n"
						+ "    \"isExternal\": false,\r\n"
						+ "    \"eventTimestamp\": null,\r\n"
						+ "    \"eventManagementSystemGmt\": null,\r\n"
						+ "    \"result\": \"VALID\",\r\n"
						+ "    \"card\": {\r\n"
						+ "      \"type\": \"TRANSIENT\",\r\n"
						+ "      \"identifier\": \"450034022\",\r\n"
						+ "      \"mediaType\": \"BARCODE\"\r\n"
						+ "    },\r\n"
						+ "    \"creditNoteIdentifier\": null,\r\n"
						+ "    \"joinType\": \"SUCCESSFUL_ENTRY\",\r\n"
						+ "    \"creditCardData\": null,\r\n"
						+ "    \"facilityCode\": null,\r\n"
						+ "    \"entityId\": \"1.1.2.1.3.5.5.4.6.1\",\r\n"
						+ "    \"plate\": \"752VMG\",\r\n"
						+ "    \"managementSystemRejectionCode\": \"0 - no rejection\",\r\n"
						+ "    \"presenceEntityId\": \"1.1.2.1.3.5\",\r\n"
						+ "    \"userCategoryIdentifier\": null,\r\n"
						+ "    \"dpCardData\": null,\r\n"
						+ "    \"hotelCardData\": null,\r\n"
						+ "    \"plateReadConfidence\": null,\r\n"
						+ "    \"plateCountryCode\": null,\r\n"
						+ "    \"joinAmount\": null,\r\n"
						+ "    \"ticketGroupId\": null,\r\n"
						+ "    \"plateLprClarification\": \"OK\",\r\n"
						+ "    \"plateLprStatusCode\": \"OK\",\r\n"
						+ "    \"fiscalID\": null,\r\n"
						+ "    \"uniqueSN\": null,\r\n"
						+ "    \"external\": false\r\n"
						+ "  }")
				
		));
		}
		restSender.postForJanusConnector(RestSender.OPERATION_PUSH_URL, operations);
		//restSender.postForJanusConnector(RestSender.OPERATION_PUSH_PASSTHROUGHT_ETL_URL, operations);
		System.out.println("End OperationsRestSender " + new Date());
	}
	

	
	/**
	 * bulk
	 * @param args
	 * @throws Exception 
	 */
	public static void main2(String[] args) throws Exception {
		
		final int N_OPERATIONS = 10;
		final int PAUSE_BETWEEN_OPE = 500;	//ms
		
		for(int ii=0; ii<N_OPERATIONS; ii++) {
			System.out.println("Start OperationsRestSender " + new Date());
			var restSender = new RestSender();
			Map<String, Object> operations = new HashMap<String, Object>();

			try {
				Map<String, Object> op = null;
				if(ii%2==0) {
					op = getJoinOperation(TextDb.nextSequence(TextDb.SEQ_OPERATION_IDENTIFIER),"3","ENTRANCE","SUCCESSFUL_ENTRY","3",null,"EXTERNAL","BARCODE","merlin-6");
				} else {
					op = getJoinOperation(TextDb.nextSequence(TextDb.SEQ_OPERATION_IDENTIFIER),"3","EXIT","SUCCESSFUL_EXIT","3",null,"EXTERNAL","BARCODE","merlin-6");
				}

				operations.put("operations", Arrays.asList(op));
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			
			Thread.sleep(PAUSE_BETWEEN_OPE);
			
			restSender.postForJanusConnector(RestSender.OPERATION_PUSH_URL, operations);
			System.out.println("End OperationsRestSender " + new Date());
		}
		
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
		
		operation.put("paymentOperationType", "SUCCESSFUL_PAYMENT");
		operation.put("paymentType", "CREDIT_CARD");
		operation.put("rechargedValue", 13d);
		operation.put("sessionId",3L);
		operation.put("toPay", 10.0);
		operation.put("payed", 9.0);
		operation.put("amount", 1.0);
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
		
//		card.put("type","ANONYMOUS");
//		card.put("mediaType", "LICENSE_PLATE");	// MediaTypeIdentifier.LICENSE_PLATE;
//		card.put("identifier", "anony-000-" + identifier);
//		
//		card.put("type","EXTERNAL_SYSTEM");
//		card.put("mediaType", "BARCODE");
//		card.put("identifier", "barcode-00017");
//		card.put("externalSystemName", "external system name");
		
//		card.put("type","TRANSIENT");
//		card.put("mediaType", "BARCODE");	// MediaTypeIdentifier.LICENSE_PLATE;
//		card.put("identifier", "000111222333");
		
		card.put("type","SEASONAL");
		card.put("mediaType", "LICENSE_PLATE");	// MediaTypeIdentifier.LICENSE_PLATE;
		card.put("identifier", "69_identifier");
		
		operation.put("card", card);
		
//		Map<String,Object> isfInvoice = new HashMap<String,Object>();
//		isfInvoice.put("isfNumber", "isfNumber");
//		isfInvoice.put("licensePlate", "yy123qq");
//		isfInvoice.put("issueDate", 123456789L);
//		isfInvoice.put("issueDateGmt", Short.valueOf("120"));
//		isfInvoice.put("isfAmount", new BigDecimal("25.35"));
//		
//		Map<String,String> formData = new HashMap<String,String>();
//		formData.put("FIELD_1", "val1");
//		formData.put("FIELD_2", "val2");
//		isfInvoice.put("formData", formData);
//		
//		operation.put("isfInvoice", isfInvoice);
		
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
		
		operation.put("operationType", "EXIT");	//ENTRANCE EXIT
		operation.put("identifier", identifier);
		operation.put("entityId", "1.1.2.1.3.1");	//3 //1.1.2.0.66053
		operation.put("facilityCode", "123");
		operation.put("managementSystemGmt", Short.valueOf("60"));
		operation.put("result", "VALID");// = ValidationResult.VALID;
		operation.put("timestamp", Calendar.getInstance().getTime().getTime());
		operation.put("managementSystemRejectionCode", "rejcode");
		operation.put("additionalInformation", "additional information");
		operation.put("joinType", "SUCCESSFUL_EXIT");	// SUCCESSFUL_ENTRY;	//SUCCESSFUL_EXIT
		operation.put("presenceEntityId", null);
		//operation.put("plate", "AA123BC");
		//operation.put("joinAmount", new BigDecimal(3));

		
		Map<String, Object> card = new HashMap<String, Object>();
//		
//		card.put("type", "PREBOOKING");
//		card.put("mediaType", "BARCODE");
//		card.put("identifier", "881234560000013862750");
		
//		card.put("type","EXTERNAL_SYSTEM");
//		card.put("mediaType", "BARCODE");
//		card.put("identifier", "barcode-00017");
//		card.put("externalSystemName", "external system name");
		
//		card.put("type","EXTERNAL");
//		card.put("mediaType", "LICENSE_PLATE");	// MediaTypeIdentifier.LICENSE_PLATE;
//		card.put("identifier", "external-ticket-2");
//		
		card.put("type","TRANSIENT");
		card.put("mediaType", "LICENSE_PLATE");	// MediaTypeIdentifier.LICENSE_PLATE;
		card.put("identifier", "AA123BB");
//		
//		card.put("type","ANONYMOUS");
//		card.put("mediaType", "BARCODE");
//		card.put("identifier", "ano-0002");
		
//		card.put("type","THIRD_PARTY");
//		card.put("mediaType", "BARCODE");	// MediaTypeIdentifier.LICENSE_PLATE;
//		card.put("identifier", "third-party-ticket-1");
//		card.put("thirdPartyId", 1L);
		
//		card.put("type","SEASONAL");
//		card.put("mediaType", "LICENSE_PLATE");
//		card.put("identifier", "7912_identifier");
		
		operation.put("card", card);
		
		return operation;
	}
		
	private static Map<String, Object> getJoinOperation(long identifier, String entityId, String operationType, String joinType, 
			String presenceEntityId, String plate, String cardType, String cardMediaType, String cardIdentifier){
		Map<String, Object> operation = new HashMap<String, Object>(); 
		
		operation.put("operationType", operationType);
		operation.put("identifier", identifier);
		operation.put("entityId", entityId);	//3 //1.1.2.0.66053
		operation.put("facilityCode", "123");
		operation.put("managementSystemGmt", Short.valueOf("120"));
		operation.put("result", "VALID");// = ValidationResult.VALID;
		operation.put("timestamp", Calendar.getInstance().getTime().getTime());
		operation.put("managementSystemRejectionCode", "rejcode");
		operation.put("additionalInformation", "additional information");
		operation.put("joinType", joinType);
		operation.put("presenceEntityId", presenceEntityId);
		operation.put("plate", plate);
		operation.put("joinAmount", new BigDecimal(3));

		
		Map<String, Object> card = new HashMap<String, Object>();
		
		card.put("type", cardType);
		card.put("mediaType", cardMediaType);
		card.put("identifier", cardIdentifier);
		
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
		operation.put("result","POOL_GROUP_IS_FULL");
		
		/* validation fields */
		operation.put("validationOperationType", "VOUCHER_VALIDATION");
		Map<String, Object> card = new HashMap<String, Object>();
		
		card.put("type","TRANSIENT");
		card.put("mediaType", "LICENSE_PLATE");	// MediaTypeIdentifier.LICENSE_PLATE;
		card.put("identifier", "transient-000-" + identifier);
//		
//		card.put("type","EXTERNAL_SYSTEM");
//		card.put("mediaType", "BARCODE");
//		card.put("identifier", "barcode-00002");
//		card.put("externalSystemName", "external system name");
		
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
