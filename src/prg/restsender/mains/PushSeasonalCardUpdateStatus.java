package prg.restsender.mains;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
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
public class PushSeasonalCardUpdateStatus {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start PushSeasonalCardUpdateStatus " + new Date());
		var restSender = new RestSender();
		var seasonalCardManagementSystemIdentifier = "114_identifier";
		var timestamp = Calendar.getInstance().getTime().getTime();
		var gmt = Short.valueOf("120");

		//var params = "?timestamp="+ timestamp +"&managementSystemGmt="+ gmt +"&identifier="+ seasonalCardManagementSystemIdentifier;
		var params = "?timestamp="+ timestamp +"&managementSystemGmt="+ gmt ;
		
		Map<String, Object> pushStatus = new HashMap<String, Object>();
		pushStatus.put("enabled",true);
		pushStatus.put("apbStatus","ENABLED");
		var flags = new ArrayList<Object>();
		var enums = new ArrayList<Object>();
		
		Map<String, Object> pushMultiStatusMap = new HashMap<String, Object>();
		pushMultiStatusMap.put(seasonalCardManagementSystemIdentifier,pushStatus);		
		
		var flag = new HashMap<String, Object>();
		flag.put("value",true);
		flag.put("type","BOOLEAN");
		flag.put("readonly",false);
		flag.put("name","IS_PAID");

		flags.add(flag);		

		var enumPush = new HashMap<String, Object>();
		enumPush.put("name","enumName");
		enumPush.put("values", new ArrayList<String>());

		enums.add(enumPush);
		
		pushStatus.put("flags",flags );
		pushStatus.put("enums",enums );
		
		//restSender.postForJanusConnector(RestSender.PUSH_SEASONALCARD_MANAGEMENTSYSTEM_STATUS+params, pushStatus);
		restSender.postForJanusConnector(RestSender.PUSH_SEASONALCARD_MANAGEMENTSYSTEM_STATUS_MULTI+params, pushMultiStatusMap);
		System.out.println("End PushSeasonalCardUpdateStatus " + new Date());
	}

}
