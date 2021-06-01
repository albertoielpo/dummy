package prg.restsender.mains;

import java.util.HashMap;

import prg.restsender.RestSender;

public class UiPush {

	public static void main(String[] args) {
		long oneYear = 1000*60*60*24*365;
		
		var restSender = new RestSender();
		var m = new HashMap<String, Object>();
		//m.put("alarmTypology", "NOT_CLEARED");
		//m.put("virtualParkingId", 3L);
		//m.put("fromDate", new Date().getTime()-oneYear);
	//	m.put("toDate", new Date().getTime());
		//m.put("licensePlate", "AA123BB");
		m.put("userCultureId", 2L);
		//m.put("confidence", "HIGH");
		m.put("orderBy", "TIMESTAMP");
		m.put("orderAsc", true);
		m.put("page", 1);
		m.put("pageSize", 10);
		restSender.postForJanusUi(RestSender.LPR_ALARM_FILTER, m);
	}
}
