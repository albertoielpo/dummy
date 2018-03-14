package dummy;

import java.math.BigDecimal;

public class JustMain {

	public static void main(String[] args) {
		BigDecimal bd  = new BigDecimal("12.54");
		BigDecimal dd = new BigDecimal("1.32");
		System.out.println(bd.add(dd.negate()));
	}
}
