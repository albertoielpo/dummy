package dummy;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomUtils {

	/* Object */
	public static String randomString() {
		return UUID.randomUUID().toString();
	}
		
	public static Integer randomInteger() {
		Random randomGenerator = new Random();
		return new Integer(randomGenerator.nextInt());
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
	
	public static Long randomLong() {
		Random randomGenerator = new Random();
		return new Long(randomGenerator.nextLong());
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
	
	public static Float randomFloat() {
		Random randomGenerator = new Random();
		return new Float(randomGenerator.nextFloat());
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
	
	public static Double randomDouble() {
		Random randomGenerator = new Random();
		return new Double(randomGenerator.nextDouble());
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
	
	public static Date randomDate() {
		return new Date();
	}
	
	public static Boolean randomBoolean() {
		Random randomGenerator = new Random();
		return new Boolean(randomGenerator.nextBoolean());
	}
	
	public static Short randomShort() {
		Random randomGenerator = new Random();		
		return new Short((short) randomGenerator.nextInt(Short.MAX_VALUE + 1));
	} 
	
	public static Short randomPositiveShort() {		
		Integer ii = Math.abs(new Integer(randomShort()));
		return new Short((short) ii.intValue());
	}
	
	/* primitive */
	public static int randomint() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt();
	}
	
	public static int randomint(final int[] exceptionValues) {
		int random;
		do {
			random = randomint();
	    } while (Arrays.asList(exceptionValues).contains(random));

		return random;
	}
	
	public static long randomlong() {
		Random randomGenerator = new Random();
		return randomGenerator.nextLong();
	}
	
	public static long randomlong(final long[] exceptionValues) {
		long random;
		do {
			random = randomlong();
	    } while (Arrays.asList(exceptionValues).contains(random));

		return random;
	}
	
	public static float randomfloat() {
		Random randomGenerator = new Random();
		return randomGenerator.nextFloat();
	}
	
	public static float randomfloat(final float[] exceptionValues) {
		float random;
		do {
			random = randomfloat();
	    } while (Arrays.asList(exceptionValues).contains(random));

		return random;
	}
	
	public static double randomdouble() {
		Random randomGenerator = new Random();
		return randomGenerator.nextDouble();
	}
	
	public static double randomdouble(final double[] exceptionValues) {
		double random;
		do {
			random = randomdouble();
	    } while (Arrays.asList(exceptionValues).contains(random));

		return random;
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
