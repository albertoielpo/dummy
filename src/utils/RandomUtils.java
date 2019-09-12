package utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
/**
 * @author Alberto Ielpo
 */

public class RandomUtils {

	/* Object */
	/* String */
	public static String randomString() {
		return UUID.randomUUID().toString();
	}
		
	/* Integer */	
	public static Integer randomInteger() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt();
	}
	
	public static Integer randomPositiveInteger() {
		return Math.abs(randomInteger());
	}
	
	public static Integer randomInteger(final List<Integer> exceptionValues) {
		Integer random;
		do {
			random = randomInteger();
	    } while (exceptionValues.contains(random));

		return random;
	}
	
	/**
	 * Generate a random Integer lower than maxValue param 
	 * @param maxValue
	 * @return
	 */
	public static Integer randomIntegerLowerThan(Integer maxValue) {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(maxValue);
	}
	
	/**
	 * Generate a list of random Integer lower than maxValue param. 
	 * The numbers of list element's are definited with qty param
	 * @param qty
	 * @param maxValue
	 * @return
	 */
	public static List<Integer> randomListIntegerLowerThan(Integer qty, Integer maxValue){
		List<Integer> res = new ArrayList<Integer>();
		for(int i=0; i<qty; i++)
			res.add(randomIntegerLowerThan(maxValue));
		return res;
	}
	
	/**
	 * Generate a list of random Integer
	 * The numbers of list element's are definited with qty param
	 * @param qty
	 * @return
	 */
	public static List<Integer> randomListInteger(Integer qty){
		return randomListIntegerLowerThan(qty, Integer.MAX_VALUE);
	}
	
	/* Long */
	public static Long randomLong() {
		Random randomGenerator = new Random();
		return randomGenerator.nextLong();
	}
	
	public static Long randomLong(final List<Long> exceptionValues) {
		Long random;
		do {
			random = randomLong();
	    } while (exceptionValues.contains(random));

		return random;
	}
	
	public static Long randomPositiveLong() {
		return Math.abs(randomLong());
	}
	
	/* Float */
	public static Float randomFloat() {
		Random randomGenerator = new Random();
		return randomGenerator.nextFloat();
	}
	
	public static Float randomFloat(final List<Float> exceptionValues) {
		Float random;
		do {
			random = randomFloat();
	    } while (exceptionValues.contains(random));

		return random;
	}
	
	public static Float randomPositiveFloat() {
		return Math.abs(randomFloat());
	}
	
	/* Double */
	public static Double randomDouble() {
		Random randomGenerator = new Random();
		return randomGenerator.nextDouble();
	}
	
	public static Double randomDouble(final List<Double> exceptionValues) {
		Double random;
		do {
			random = randomDouble();
	    } while (exceptionValues.contains(random));

		return random;
	}
	
	public static Double randomPositiveDouble() {
		return Math.abs(randomDouble());
	}
	
	/* Date */
	public static Date randomDate() {
		return new Date();
	}
	
	/* Boolean */
	public static Boolean randomBoolean() {
		Random randomGenerator = new Random();
		return randomGenerator.nextBoolean();
	}
	
	/* Short */
	public static Short randomShort() {
		Random randomGenerator = new Random();		
		return (short) randomGenerator.nextInt(Short.MAX_VALUE + 1);
	} 
	
	public static Short randomPositiveShort() {		
		Integer ii = Math.abs(randomShort().intValue());
		return (short) ii.intValue();
	}
	
	/* ***************************************************************************** */
	/* primitive */
	public static int randomint() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt();
	}
		
	public static long randomlong() {
		Random randomGenerator = new Random();
		return randomGenerator.nextLong();
	}
		
	public static float randomfloat() {
		Random randomGenerator = new Random();
		return randomGenerator.nextFloat();
	}
	
		
	public static double randomdouble() {
		Random randomGenerator = new Random();
		return randomGenerator.nextDouble();
	}
	
	
	public static boolean randomboolean() {
		Random randomGenerator = new Random();
		return randomGenerator.nextBoolean();
	}
	
	public static short randomshort() {
		Random randomGenerator = new Random();
		return (short) randomGenerator.nextInt(Short.MAX_VALUE + 1);
	}
	
}
