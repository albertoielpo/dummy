package prg.restsender.mains;

import java.util.HashMap;

import prg.restsender.RestSender;

public class SchedulingTest {

	public static void main(String[] args) {
		var restSender = new RestSender();
		var m = new HashMap<String,Object>();
		m.put("type","CLOCK_SYNCHRONIZATION");
		m.put("triggerId", 1L);
		m.put("id", 1L);
		m.put("priority", "NORMAL");
		m.put("scriptName", "clock_sync");
		restSender.postForJanusUi(RestSender.SCHEDULING_ACTIONS_CREATE + "?triggerId=1", m);
	}
}
