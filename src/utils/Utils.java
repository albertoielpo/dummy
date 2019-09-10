package utils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
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
	
	
	/**
	 * Map any date-string into unix date.
	 * This function has the aim to manually set a timezone, using the one in the parameter
	 * <br /><br />
	 * <b>Examples</b>: 
	 * <br/>
	 * <i> map the String date 10/12/2018 </i>
	 * <ol> Utils.mapAsUnixDate("10/12/2018", "dd/MM/yyyy", "GMT+1");	//1544396400000 </ol>
	 * <br/><i> Map the standard Javascript new Date().toString() </i>
	 * <ol> Utils.mapAsUnixDate("Fri Apr 05 2019 12:40:33 GMT+0200 (Central European Summer Time)".substring(0, 24), "EEE MMM d yyyy HH:mm:ss", "GMT+2");	//1554460833000 </ol>
	 * <br/>
	 * @param date (String)
	 * @param format (List<String> of allowed input type format)
	 * @param timezone (String in the format "GMT+/-offset"). If null is used GMT as default
	 * @return unix date or null if something goes wrong
	 */
	public static Long mapAsUnixDate(final String date, final String format, final String timezone) {
		String tz = Optional.ofNullable(timezone).orElse("GMT");
		Long unixDate = null;
		
		if(format != null && !"".equals(format)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				sdf.setTimeZone(TimeZone.getTimeZone(tz));
				unixDate = sdf.parse(date).getTime();
			} catch(Exception e) {
				//continue
			}
		}
		
		if(unixDate == null)
			System.out.println("An error occured during date mapping: " + date + " format: " + format);
		
		return unixDate;
	}
	

	/**
	 * Encrypt an input string using algorithm and charset as params
	 * @param inputStr
	 * @param algorithm
	 * @param charset
	 * @param isBaseEncoded
	 * @return
	 */
	public static String encrypt(final String inputStr, final String algorithm, final Charset charset, final boolean isBaseEncoded) {
		String res = "";
		if(!(inputStr == null || inputStr.length() == 0)) {
			try{
				byte[] input = inputStr.getBytes(charset);
				byte[] output = MessageDigest.getInstance(algorithm).digest(input);
				if(isBaseEncoded) {
					res = Base64.getEncoder().encodeToString(output);
				} else {
					res = String.format("%032X", new BigInteger(1, output));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		return res.toLowerCase();

	}
	
	/**
	 * Return sha1 hash of an input string 
	 * @param inputStr
	 * @return
	 */
	public static String sha1(final String inputStr) {
		return Utils.encrypt(inputStr, "SHA-1", StandardCharsets.UTF_8, false);
	}
	
	public static String md5(final String inputStr) {
		return Utils.encrypt(inputStr, "MD5", StandardCharsets.UTF_8, false);
	}
	
}
