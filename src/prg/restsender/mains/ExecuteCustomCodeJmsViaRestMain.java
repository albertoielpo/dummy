package prg.restsender.mains;

import prg.restsender.RestSender;

public class ExecuteCustomCodeJmsViaRestMain {

	public static void main(String[] args) {
		try{
			RestSender r = new RestSender();
			r.getForJanusUi(RestSender.TEST_URL, "id", 1);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
	}
	
}
