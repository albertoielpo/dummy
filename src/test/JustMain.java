package test;

import utils.Utils;

public class JustMain {
	
	public static void main(String[] args) {
		
		double a = 15.55d;
		float b = 15.36f;
		
		System.out.println(Utils.convertToBigDecimal(a));
		System.out.println(Utils.convertToBigDecimal(b));
		
		System.out.println(Utils.convertToBigDecimal(a,5));
		System.out.println(Utils.convertToBigDecimal(b,1));
		
	}
}
