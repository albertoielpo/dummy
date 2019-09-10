package test;

public class CompareVersionNumber {
	
	/**
	 * 
	 * @param a
	 * @param idx
	 * @return
	 */
	private static int defaultValueOf(String[] a, int idx) {
		int res = 0;
		try {
			res = Integer.valueOf(a[idx]);
		} catch(Exception e) {
			//don't worry
		}
		return res;
	}
	
	/**
	 * Compare version number
	 * <ul>
	 * <li>0.0.9 < 0.0.10 //-1</li>
	 * <li>1.1.0 > 1.0.15 //1</li>
	 * <li>2.0.0 == 2.0.0 //0</li>
	 * </ul>
	 * @param a
	 * @param b
	 * @return 1 if a > b, -1 if b > a, 0 if a == b
	 */
	private static int compareVersionNumber(String a, String b) {
		String[] as = a.split("\\.");
		String[] bs = b.split("\\.");
		int maxLength = as.length > bs.length ? as.length : bs.length;
		for(int ii=0; ii<maxLength; ii++) {
			int res = Integer.compare(CompareVersionNumber.defaultValueOf(as, ii), CompareVersionNumber.defaultValueOf(bs, ii));
			if(res != 0)
				return res;
		}
		return 0;
	}
	
	public static void main(String[] args) throws InterruptedException {

		System.out.println(CompareVersionNumber.compareVersionNumber("0.0.9", "0.0.10"));	//-1
		System.out.println(CompareVersionNumber.compareVersionNumber("1.1.0", "1.0.15"));	//1
		System.out.println(CompareVersionNumber.compareVersionNumber("2.0.0", "1.5.23"));	//1
		System.out.println(CompareVersionNumber.compareVersionNumber("1.0.1", "1.0.0.5"));	//1
		System.out.println(CompareVersionNumber.compareVersionNumber("2.2.2", "2.2.2.0"));	//0
		System.out.println(CompareVersionNumber.compareVersionNumber("2.2.2", "2.2.2.1"));	//-1
		System.out.println(CompareVersionNumber.compareVersionNumber("3.0", "3.0"));		//0
 
	}
}
