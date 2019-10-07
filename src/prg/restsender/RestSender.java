package prg.restsender;

import java.util.Map;

import utils.HttpRequestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public static String SCHEDULING_ACTIONS_CREATE = PROTOCOL + HOST_PORT + "/janus/api/scheduling/actions/create";
	
	/* Content type */
	public static String CONTENT_TYPE = "Content-Type";
	public static String APPLICATION_JSON_UTF8 = "application/json; charset=utf8";
	
	/* Header for Janus UI */
	public static String JANUS_UI_AUTHENTICATION = "Janus-Authorization";
	public static String UI_AUTHENTICATION_TOKEN = "06947649-afc9-4396-bfa3-876f22742b93"; 
	
	/* Header for Janus Connector */
	public static String JANUS_MS_AUTHENTICATION = "Janus-MS-Authentication";
	public static String MS_AUTHENTICATION_TOKEN = "f62b982a-99cf-4815-a376-1d7d305405e1";
	
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
     * @param mapPar
     */
	public void postForJanusConnector(String url, Map<String,Object> mapPar) {

		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try{
			json = mapper.writeValueAsString(mapPar);
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
	
	/**
	 * 
	 * @param url
	 * @param mapPar
	 */
	public void postForJanusUi(String url, Map<String,Object> mapPar) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try{
			json = mapper.writeValueAsString(mapPar);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		this.printResponseResult(HttpRequestUtils.post(url)
				.header(CONTENT_TYPE, APPLICATION_JSON_UTF8)
				.header(JANUS_UI_AUTHENTICATION, UI_AUTHENTICATION_TOKEN)
				.send(json)
				.code());
	}
}
