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
	
	/**
	 * URL to test code with JMS context
	 * The following url does not exist in JMS
	 * To use @see {test.TestJmsBackendCode}
	 */
	public static String TEST_URL = PROTOCOL + HOST_PORT + "/janus/api/tax/test/donotcommit";
	
	/* URL */
	public static String OPERATION_PUSH_URL = PROTOCOL + HOST_PORT + "/janus/api/connector/operation/push";
	public static String OPERATION_PUSH_PASSTHROUGHT_ETL_URL = PROTOCOL + HOST_PORT + "/janus/api/connector/operation/pushpassthroughetl";
	
	public static String CASHIER_PAYMENT_GET_SURCHARGE_URL = PROTOCOL + HOST_PORT + "/janus/api/cashier/payment/get/surcharge";
	public static String TRACKING_HISTORY_SEASONAL_GET_ALL_EVENTS_URL = PROTOCOL + HOST_PORT + "/janus/api/card/tracking/history/seasonal/getAllEvents";
	public static String SCHEDULING_ACTIONS_CREATE = PROTOCOL + HOST_PORT + "/janus/api/scheduling/actions/create";
	public static String LPR_ALARM_FILTER = PROTOCOL + HOST_PORT + "/janus/api/alarm/lpr/filter";
	public static String EVENT_PARKING_START = PROTOCOL + HOST_PORT + "/janus/api/eventparking/event/start";
	public static String PARKSOL_ZONE = PROTOCOL + HOST_PORT + "/janus/api/baymonitoringconfiguration/parksol/zone";
	public static String CACHE_EVICT_ALL = PROTOCOL + HOST_PORT + "/janus/api/cache/evict/all";
	public static String PUSH_SEASONALCARD_MANAGEMENTSYSTEM_STATUS = PROTOCOL + HOST_PORT + "/janus/api/connector/card/seasonal/managementsystems/status/push";
	public static String PUSH_SEASONALCARD_MANAGEMENTSYSTEM_STATUS_MULTI = PROTOCOL + HOST_PORT + "/janus/api/connector/card/seasonal/managementsystems/status/multi/push";
	public static String CONGRESS_PUSH = PROTOCOL + HOST_PORT + "/janus/api/connector/card/congress/push";
	public static String VERIFICATION_PUSH = PROTOCOL + HOST_PORT + "/janus/api/connector/externalticketing/verification";
	public static String PAYWITHJP_PUSH = PROTOCOL + HOST_PORT + "/janus/api/connector/card/paywithjp";
	public static String OPERATION_PLATEIMAGE_PUSH = PROTOCOL + HOST_PORT + "/janus/api/connector/operation/plateImage/push";
	public static String GRACE_TIME_UPDATER = PROTOCOL + HOST_PORT + "/janus/api/connector/parking/wrapped/updateStatus";
    public static String ALARM_SENDER = PROTOCOL + HOST_PORT + "/janus/api/connector/message/push";
    public static String APB_OVERRIDE_INSERTER = PROTOCOL + HOST_PORT + "/janus/api/connector/ccblacklist/push";
    public static String GRACE_TIME_UPDATER_MASSIVE = PROTOCOL + HOST_PORT + "/janus/api/connector/parking/wrapped/multi/updateStatus";;
	
	/* THIRD PARTY */
	public static String TP_LOGIN = PROTOCOL + HOST_PORT + "/janus-integration/api/ext/login";
	
	/* Content type */
	public static String CONTENT_TYPE = "Content-Type";
	public static String APPLICATION_JSON_UTF8 = "application/json; charset=utf8";
	
	public static String ACCEPT = "Accept";
	public static String APPLICATION_JSON = "application/json";
	public static String APPLICATION_XML = "application/xml";
	
	/* Header for Janus UI */
	public static String JANUS_UI_AUTHENTICATION = "Janus-Authorization";
	/* Header for Janus Admin UI */
	public static String JANUS_ADMIN_UI_AUTHENTICATION = "Janus-Admin-Authorization";
	public static String ADMIN_UI_AUTHENTICATION_TOKEN = "e0b0f482-19b3-4801-840e-37b3025ffaa5";
	public static String UI_AUTHENTICATION_TOKEN = "346411db-7288-4d7a-b171-c6546da6ea4f"; 
	
	/* Header for Janus Connector */
	public static String JANUS_MS_AUTHENTICATION = "Janus-MS-Authentication";
	//MS_AUTHENTICATION_TOKEN = select * from janus.management_system where active = true
	public static String MS_AUTHENTICATION_TOKEN = "1cdbad67-a803-44da-8f69-09ab9f8fe628";
	
    /* Header for Third party */
    public static String JANUS_THIRD_PARTY_AUTHENTICATION = "Janus-TP-Authorization";
    
    public static String RESOURCES_CONTENT_PATH = System.getProperty("user.dir") + "\\resources\\contents";
	
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
     * Print response body
     * @param body
     */
    private void printResponseResult(String body) {
		System.out.println("Response body: " + body);
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
		System.out.println("Request url: " + url);
		System.out.println("Request body: " + json);
			
		this.printResponseResult(HttpRequestUtils.post(url)
			.header(CONTENT_TYPE, APPLICATION_JSON_UTF8)
			.header(JANUS_MS_AUTHENTICATION, MS_AUTHENTICATION_TOKEN)
			.send(json)
			.code());
	}
	
	public void postForJanusConnectorWithResponse(String url, Map<String,Object> mapPar) {

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
			.body());
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
	
	public void putForJanusUi(String url, Object...args) {
		this.printResponseResult(HttpRequestUtils.put(url, true, args)
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
				.header(ACCEPT, APPLICATION_JSON)
				//.header(ACCEPT, APPLICATION_XML)
				.send(json)
				.body());
	}
	
	/**
	 * 
	 * @param url
	 * @param mapPar
	 */
	public void putForJanusAdminUi(String url, Map<String,Object> mapPar) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try{
			json = mapper.writeValueAsString(mapPar);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		this.printResponseResult(HttpRequestUtils.put(url)
				.header(CONTENT_TYPE, APPLICATION_JSON_UTF8)
				.header(JANUS_ADMIN_UI_AUTHENTICATION, ADMIN_UI_AUTHENTICATION_TOKEN)
				.send(json)
				.code());
	}
	
}
