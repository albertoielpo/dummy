package prg.restsender.mains;

import java.util.Date;
import java.util.HashMap;

import prg.restsender.RestSender;
import utils.FileUtils;

public class PushPlateImageRestSender {
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("PushPlateImageRestSender: start " + new Date());
		var restSender = new RestSender();
		
		var content = new HashMap<String,Object>();
		content.put("identifier", 11329);		//operation identifier
		content.put("managementSystemGmt", Short.valueOf("120"));
	    System.out.println("Working Directory = " + System.getProperty("user.dir"));

		byte[] value = FileUtils.getBytesFromImage(RestSender.RESOURCES_CONTENT_PATH + "\\plate.jpg", true);
		System.out.println(value);
		content.put("value", value);
		content.put("imageType", "LICENSE_PLATE");
		restSender.postForJanusConnector(RestSender.OPERATION_PLATEIMAGE_PUSH, content);
		System.out.println("PushPlateImageRestSender: end " + new Date());
	}
	

}
