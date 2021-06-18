package prg.restsender.mains;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import prg.restsender.RestSender;
import utils.RandomUtils;
import utils.textdb.TextDb;

@SuppressWarnings({"rawtypes", "unused"})
public class AlarmSender {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start AlarmSender " + new Date());
		var restSender = new RestSender();
		var identifier = "3";
		var gmt = Short.valueOf("120");
		var status = "FULL";
		

		///janus/api/virtualparking/updateStatus
		var params = "?virtualParkingIdentifier="+ identifier +"&managementSystemGmt="+ gmt +"&status="+ status;
		
//	    
		Map<String, Object> pushStatus = new HashMap<String, Object>();
		pushStatus.put("identifier",Calendar.getInstance().getTime().getTime());
		pushStatus.put("messageId",1l);
		pushStatus.put("entityId","d2");
		pushStatus.put("timestamp", Calendar.getInstance().getTime().getTime());
		pushStatus.put("managementSystemGmt", Short.valueOf("120"));
		pushStatus.put("priority","HIGH");
		pushStatus.put("type","LOG");
		pushStatus.put("alarmType","ALARM");
		pushStatus.put("status", "ALARM");	
		pushStatus.put("additionalInformation", "additionalInformation");
		pushStatus.put("operationIdentifier", "11416");
		pushStatus.put("lprAlarm", true);

		Map<String, Object> message = new HashMap<String, Object>();
		message.put("message", null);
		pushStatus.put("message", message);

		Map<String, Object> pushList = new HashMap<String, Object>();
		pushList.put("messages", Collections.singletonList(pushStatus));


		restSender.postForJanusConnector(RestSender.ALARM_SENDER, pushList);
		System.out.println("End AlarmSender " + new Date());
	}

}
