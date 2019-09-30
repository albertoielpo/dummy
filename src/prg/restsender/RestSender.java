package prg.restsender;

import java.util.Map;

import utils.HttpRequestUtils;

/**
 * @author Alberto Ielpo
 */
public class RestSender {

	public static String PROTOCOL = "http://";
	public static String HOST_PORT = "127.0.0.1:8080";
	
	/* URL */
	public static String OPERATION_PUSH_URL = PROTOCOL + HOST_PORT + "/janus/api/connector/operation/push";
	public static String CASHIER_PAYMENT_GET_SURCHARGE_URL = PROTOCOL + HOST_PORT + "/janus/api/cashier/payment/get/surcharge";
	public static String TRACKING_HISTORY_SEASONAL_GET_ALL_EVENTS_URL = PROTOCOL + HOST_PORT + "/janus/api/card/tracking/history/seasonal/getAllEvents";
	
	/* Content type */
	public static String CONTENT_TYPE = "Content-Type";
	public static String APPLICATION_JSON_UTF8 = "application/json; charset=utf8";
	
	/* Header for Janus UI */
	public static String JANUS_UI_AUTHENTICATION = "Janus-Authorization";
	public static String UI_AUTHENTICATION_TOKEN = "fc0e3d26-94ef-4017-9941-dfeb0b07e3a8"; 
	
	/* Header for Janus Connector */
	public static String JANUS_MS_AUTHENTICATION = "Janus-MS-Authentication";
	public static String MS_AUTHENTICATION_TOKEN = "e14c5924-e7dc-4df8-892f-c1bde3934403";
	
    /* Header for Third party */
    public static String JANUS_THIRD_PARTY_AUTHENTICATION = "Janus-TP-Authorization";
	
    /**
     * Print http response
     * @param httpStatusCode
     */
    private void printResponseResult(int httpStatusCode) {
		if(httpStatusCode >= 200 && httpStatusCode < 300) {
			System.out.println("Response Ok - HTTP Response Code: " + httpStatusCode);
		} else {
			System.out.println("Response Error - HTTP Response Code: " + httpStatusCode);
		}
    }
    
    /**
     * 
     * @param url
     * @param operations
     */
	public void postForJanusConnector(String url, Map<String,Object> operations) {

		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		String json = null;
		try{
			json = mapper.writeValueAsString(operations);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
			
		this.printResponseResult(HttpRequestUtils.post(url)
			.header(CONTENT_TYPE, APPLICATION_JSON_UTF8)
			.header(JANUS_MS_AUTHENTICATION, MS_AUTHENTICATION_TOKEN)
			.send(json)
			.code());
	}
	
	/**
	 * 
	 * @param url
	 * @param args
	 */
	public void getForJanusUi(String url, Object...args) {
		this.printResponseResult(HttpRequestUtils.get(url, true, args)
			.header(CONTENT_TYPE, APPLICATION_JSON_UTF8)
			.header(JANUS_UI_AUTHENTICATION, UI_AUTHENTICATION_TOKEN)
			.code());
	}
}
