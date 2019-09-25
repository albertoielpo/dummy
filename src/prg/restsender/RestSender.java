package prg.restsender;

import java.util.Map;

import utils.HttpRequestUtils;

/**
 * @author Alberto Ielpo
 */
public class RestSender {

	/* URL */
	public static String OPERATION_PUSH_URL = "http://127.0.0.1:8080/janus/api/connector/operation/push";
	public static String SURCHARGE_URL = "http://127.0.0.1:8080/janus/api/cashier/payment/get/surcharge";
	
	/* Content type */
	public static String CONTENT_TYPE = "Content-Type";
	public static String APPLICATION_JSON_UTF8 = "application/json; charset=utf8";
	
	/* Header for Janus UI */
	public static String JANUS_UI_AUTHENTICATION = "Janus-Authorization";
	public static String UI_AUTHENTICATION_TOKEN = "673963c8-6df1-45c9-a2d0-90f50533224b";
	
	/* Header for Janus Connector */
	public static String JANUS_MS_AUTHENTICATION = "Janus-MS-Authentication";
	public static String MS_AUTHENTICATION_TOKEN = "ebd315cd-6ba7-4773-b73f-91d36a0e2955";
	
    /* Header for Third party */
    public static String JANUS_THIRD_PARTY_AUTHENTICATION = "Janus-TP-Authorization";
	
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
			
		Integer response = HttpRequestUtils
				.post(url)
				.header(CONTENT_TYPE, APPLICATION_JSON_UTF8)
				.header(JANUS_MS_AUTHENTICATION, MS_AUTHENTICATION_TOKEN)
				.send(json)
				.code();
			
		if(response >= 200 && response < 300) {
			System.out.println("Response Ok - HTTP Response Code: " + response);
		} else {
			System.out.println("Response Error - HTTP Response Code: " + response);
		}
	}
	
	/**
	 * 
	 * @param url
	 * @param args
	 */
	public void getForJanusUi(String url, Object...args) {
					
		Integer response = HttpRequestUtils
				.get(url, true, args)
				.header(CONTENT_TYPE, APPLICATION_JSON_UTF8)
				.header(JANUS_UI_AUTHENTICATION, UI_AUTHENTICATION_TOKEN)
				.code();
			
		if(response >= 200 && response < 300) {
			System.out.println("Response Ok - HTTP Response Code: " + response);
		} else {
			System.out.println("Response Error - HTTP Response Code: " + response);
		}
	}
}
