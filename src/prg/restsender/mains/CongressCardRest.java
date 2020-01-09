package prg.restsender.mains;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import prg.restsender.RestSender;
import utils.RandomUtils;
import utils.textdb.TextDb;

public class CongressCardRest {

	public static void main(String[] args) throws Exception {
		System.out.println("Start CongressCardRest " + new Date());
		for (int ii = 0; ii < 50; ii++) {
			var restSender = new RestSender();
			var mapPar = new HashMap<String, Object>();
			var map = new HashMap<String, Object>();
			map.put("identifier", TextDb.nextSequence(TextDb.SEQ_OPERATION_IDENTIFIER));
			map.put("mediaType", "LICENSE_PLATE");
			map.put("timestamp", new Date().getTime());
			map.put("managementSystemGmt", Short.valueOf("120"));
			map.put("entityId", "3");
			map.put("additionalInformation", "addinfo");
			map.put("amount", new BigDecimal("123.231"));
			map.put("from", RandomUtils.randomPositiveLong());
			map.put("fromGmtOffset", Short.valueOf("0"));
			map.put("to", RandomUtils.randomPositiveLong());
			map.put("toGmtOffset", Short.valueOf("0"));
			map.put("productProfileIdentifer", 1L);
			map.put("operationIdentifier", RandomUtils.randomPositiveLong());
			map.put("status", "ISSUED");
			map.put("joinType", "SUCCESSFUL_ENTRY");
			map.put("paymentOperationType", null);
			map.put("validationOperationType", null);
			map.put("congressSequence", RandomUtils.randomPositiveLong());
			map.put("accountIdentifier", null);
			map.put("notes", "notes");
			map.put("printed", false);
			map.put("centralOperationType", null);
			map.put("congressRawData", RandomUtils.randomString());
			mapPar.put("congressCards", Arrays.asList(map));
			restSender.postForJanusConnector(RestSender.CONGRESS_PUSH, mapPar);
		}

		System.out.println("End CongressCardRest " + new Date());
	}
}
