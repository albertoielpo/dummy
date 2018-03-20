package utils;

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
	
	/* ************************************************************************************** */
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
	
	/* ************************************************************************************** */
	
	/**
	 * Generate UUID type 3
	 * @param str
	 * @return
	 */
	public static String generateUUID3(String str) {
		if(str != null)
			return UUID.nameUUIDFromBytes(str.getBytes()).toString();
		return null;
	}
	
	/**
	 * Generate UUID type 4
	 * @return
	 */
	public static String generateUUID4() {
		return UUID.randomUUID().toString();
	}
	
}
