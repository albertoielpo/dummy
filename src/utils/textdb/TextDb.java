package utils.textdb;

import utils.FileUtils;

/**
 * @author Alberto Ielpo
 */
public class TextDb {

	/* text database location */
	public static String TEXTDB_PATH = "./resources/textdb/";
	
	/* sequences */
	public static String SEQ_OPERATION_IDENTIFIER = "seq_operation_identifier";
	
	/**
	 * Return the current value of the sequence using sequence name
	 * @param sequenceName
	 * @return
	 * @throws Exception
	 */
	public static long curSequence(String sequenceName) throws Exception {
		String value = FileUtils.fileToString(TEXTDB_PATH + sequenceName);
		long curVal = Long.valueOf(value);
		return curVal;
	}
	
	/**
	 * Set a new value of a sequence using sequence name
	 * @param sequenceName
	 * @param newValue
	 * @throws Exception
	 */
	public static void setSequence(String sequenceName, long newValue) throws Exception {
		FileUtils.writeFile(TEXTDB_PATH + sequenceName, String.valueOf(newValue));
	}
	
	/**
	 * Increment the sequence of "n" units
	 * @param sequenceName
	 * @param units
	 * @throws Exception
	 */
	public static void incrementSequence(String sequenceName, long units) throws Exception {
		long value = TextDb.curSequence(sequenceName);
		value = value + units;
		TextDb.setSequence(sequenceName, value);
	}
	
	/**
	 * Return the next sequence value and save it 
	 * @param sequenceName
	 * @return
	 * @throws Exception
	 */
	public static long nextSequence(String sequenceName) throws Exception {
		long value = TextDb.curSequence(sequenceName);
		value++;
		TextDb.setSequence(sequenceName, value);
		return value;
	}
	
}
