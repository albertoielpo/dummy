package prg.restsender.mains;

import java.util.Date;

import prg.restsender.RestSender;

public class SeasonalCardEventRestSender {

	public static void main(String[] args) {
		System.out.println("Start SeasonalCardEventRestSender " + new Date());
		var restSender = new RestSender();
		restSender.getForJanusUi(
			RestSender.TRACKING_HISTORY_SEASONAL_GET_ALL_EVENTS_URL,
			"virtualCardId", 1L
		);
		
		//seasonalCardId, 1L
		//accountId, 1L
		//customerId, 1L
		
		System.out.println("End SeasonalCardEventRestSender " + new Date());
	}
}
