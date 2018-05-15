package dummy;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Alberto Ielpo
 */
public class PwdGen {

	/**
	 * 
	 * @param inputStr
	 * @return 20 character static password as String
	 */
	public static String generateStatic20CharPwd(String inputStr) {
		try {
			return sha1(inputStr).substring(0, 20);
		}catch(Exception e) {
			/* something bad happened */
			e.printStackTrace();			
		}
		return null;
	}
	
	/**
	 * 
	 * @param inputStr
	 * @return computed password as String
	 */
	public static String generateComputedPwd(String inputStr) {
		StringBuffer computedBuff = new StringBuffer("");
		try {
			String hashedInput = sha1(inputStr);
			for(int ii=0; ii<inputStr.length(); ii++) {
				int charSum = inputStr.charAt(ii) + hashedInput.charAt(ii);
				charSum = charSum % 127;
				/* use character from ! to ~ */
				if(!(charSum > 32 && charSum < 127))
					charSum+=33;				
				computedBuff.append(String.valueOf((char) charSum));
			}
			
		}catch(Exception e) {
			/* something bad happened */
			e.printStackTrace();			
		}
		return computedBuff.toString();
	}
	
	/**
	 * Calculate sha1 algorithm using DigestUtils from apache.commons.codec
	 * @param inputStr
	 * @return hashed inputStr as String
	 */
	private static String sha1(String inputStr) {
		 return new String(DigestUtils.sha1Hex(inputStr));
	}
	
	
}