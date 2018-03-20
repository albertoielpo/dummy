package dummy;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;

public class Utils {
    
	/**
	 * 
	 * @param collection
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Collection<T> emptyIfNull(final Collection<T> collection) {        
		return collection == null ? new ArrayList() : collection;
    }
	
	/**
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String serializeArrayAsList(Object[] input) throws Exception {
		if(input != null && input.length > 0) {
			List<Object> list = new ArrayList<Object>(Arrays.asList(input));
			ObjectMapper mapper = new ObjectMapper();		
			return mapper.writeValueAsString(list);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String serializeObject(Object input) throws Exception {
		if(input != null) {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(input);	
		}
			       
		return null;
	}
	
	/**
	 * 
	 * @param input
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Object deserialize(String input, Class<?> clazz) throws Exception {
		if(input != null && clazz != null) {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(input, clazz);
		}

		return null;
	}
	
	public static String generateUUID3(String str) {
		if(str != null)
			return UUID.nameUUIDFromBytes(str.getBytes()).toString();
		return null;
	}
	
	public static InetAddress getInetAddress() throws Exception {
		return InetAddress.getLocalHost();
	}
	
	public static String getIp() throws Exception {
		return getInetAddress().getHostAddress();
	}
	
	public static String getHostname() throws Exception {
		return getInetAddress().getHostName();
	}
	
	public static NetworkInterface getNetworkInterface() throws Exception {
		return NetworkInterface.getByInetAddress(getInetAddress());
	}
	
	public static byte[] getHardwareAddress() throws Exception {
		return getNetworkInterface().getHardwareAddress();
	}
	
	public static String getMacAddress() {
		try {
			return new String(getHardwareAddress(), StandardCharsets.UTF_8);
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
