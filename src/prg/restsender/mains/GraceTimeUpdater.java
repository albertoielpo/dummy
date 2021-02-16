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
public class GraceTimeUpdater {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start GraceTimeUpdater " + new Date());
		var restSender = new RestSender();
		var identifier = "3";
		var gmt = Short.valueOf("120");
		var status = "FULL";
		

		///janus/api/virtualparking/updateStatus
		var params = "?virtualParkingIdentifier="+ identifier +"&managementSystemGmt="+ gmt +"&status="+ status;
		
//	     * 
//	     * @param virtualParkingIdentifier (required) The virtual parking identifier
//	     * @param managementSystemGmt (required) The management system GMT offset
//	     * @param status (required) The device status
//	     * @param detail (required) The detailed virtual parking status
//	     * </br>
//	     * </br>
//	     * <b>Payload example (JSON)</b>
//	     * </br>
//	     * <pre>
//	     * <code>
//	     *  {
//	     *      "isOpened" : true, 
//	     *      "isClosedBecauseOfFire" : false, 
//	     *      "isBarrierPermanentlyUp" : false, 
//	     *      "isStationDisabled" : false, 
//	     *      "isStationOffline" : false, 
//	     *      "isTicketsBlocked" : null, 
//	     *      "isSeasonalCardsBlocked" : null, 
//	     *      "isParkingFull" : false, 
//	     *      "isParkingForcedFull" : null, 
//	     *      "isOverCapacity" : false, 
//	     *      "isGraceTimeChanged" : null, 
//	     *      "isAntiPassbackChanged" : null,
//	     *      "isDoorOpened" : null
//	     *  }

		Map<String, Object> pushCompleteStatus = new HashMap<String, Object>();
		Map<String, Object> pushIdentifier = new HashMap<String, Object>();
		Map<String, Object> pushDetail= new HashMap<String, Object>();
		
		

		Map<String, Object> pushStatus = new HashMap<String, Object>();
		pushStatus.put("isOpened",true);
		pushStatus.put("isGraceTimeChanged",true);
		pushStatus.put("isClosedBecauseOfFire", false);
		pushStatus.put("isBarrierPermanentlyUp", false);
		pushStatus.put("isStationOffline", false);
		pushStatus.put("isTicketsBlocked", false);
		pushStatus.put("isSeasonalCardsBlocked", false);
		pushStatus.put("isParkingFull", false);
		pushStatus.put("isParkingForcedFull", true);
		pushStatus.put("isOverCapacity", false);
		pushStatus.put("isAntiPassbackChanged", true);
		pushStatus.put("isDoorOpened", true);
		pushStatus.put("isAntiPassbackChanged", true);
		pushStatus.put("ticketLevel", "LOW");
		pushDetail.put("detail", pushStatus);
		pushDetail.put("status", "FULL");
		pushIdentifier.put("6", pushDetail);
		pushIdentifier.put("5", pushDetail);
		pushIdentifier.put("1", pushDetail);
		pushIdentifier.put("2", pushDetail);
		pushIdentifier.put("3", pushDetail);
		pushIdentifier.put("4", pushDetail);

//		restSender.postForJanusConnector(RestSender.GRACE_TIME_UPDATER_MASSIVE+params, pushIdentifier);
		restSender.postForJanusConnector(RestSender.GRACE_TIME_UPDATER+params, pushStatus);
		System.out.println("End GraceTimeUpdater " + new Date());
	}

}
