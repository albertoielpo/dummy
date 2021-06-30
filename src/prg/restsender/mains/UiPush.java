package prg.restsender.mains;

import java.util.HashMap;

import prg.restsender.RestSender;

@SuppressWarnings("unused")
public class UiPush {

	private static void lprAlarmFilter() {
		long oneYear = 1000*60*60*24*365;
		var restSender = new RestSender();
		var m = new HashMap<String, Object>();
		m.put("alarmTypology", "CLEARED");
		m.put("virtualParkingId", 110L);
		m.put("fromDate", 1606261591000L);
		m.put("toDate", 1606361791000L);
		m.put("licensePlate", "951920");
		//m.put("confidence", "HIGH");
		m.put("orderBy", "TIMESTAMP");
		m.put("orderAsc", true);
		m.put("page", 1);
		m.put("pageSize", 10);
		restSender.postForJanusUi(RestSender.LPR_ALARM_FILTER, m);
	}
	
	private static void eventStart() {
		new RestSender().putForJanusUi(RestSender.EVENT_PARKING_START, 
			"eventParkingId", 3,
			"duration", 10,
			"flatFee", 20,
			"displayDescription", "test");
	}
	
	private static void getParksolZones() {
		new RestSender().getForJanusUi(RestSender.PARKSOL_ZONE, "bayMonitoringId",5L);
	}

	public static void main(String[] args) throws InterruptedException {
		//UiPush.lprAlarmFilter();
//		for(int ii=0; ii<1; ii++) {
//			UiPush.eventStart();
//			Thread.sleep(500);
//		}
		
		UiPush.getParksolZones();
	}
}
