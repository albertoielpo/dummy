package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author Alberto Ielpo
 */
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
	 * @param arrayIntoString
	 * @return
	 */
	public static Object deserialize(String input, Class<?> clazz, boolean arrayIntoString) {
		if(input != null && clazz != null) {
			try{
				ObjectMapper mapper = new ObjectMapper();
				if(arrayIntoString)
					mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			
				return mapper.readValue(input, clazz);
			
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
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
