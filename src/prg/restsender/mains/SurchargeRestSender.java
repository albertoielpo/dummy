package prg.restsender.mains;

import java.util.Date;

import prg.restsender.RestSender;

public class SurchargeRestSender {

	public static void main(String[] args) {
		System.out.println("Start OperationsRestSender " + new Date());
		var restSender = new RestSender();
		restSender.getForJanusUi(
			RestSender.CASHIER_PAYMENT_GET_SURCHARGE_URL,
			"ticketCardId", 1L, 
			"deviceId", 1L, 
			"cardType", "SEASONAL_PARKING"
		);
		
		System.out.println("End OperationsRestSender " + new Date());
	}
}
