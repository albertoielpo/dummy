package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {


	/**
	 * modifica per riferimento
	 * @param par
	 * @return
	 */
	private static MyObj myFunc(MyObj par) {
		par.setMyVar(par.getMyVar() + " myFunc");
		return par;
	}
	
	/**
	 * senza modifica
	 * @param par
	 * @return
	 */
	private static MyObj myFunc2(MyObj par) {
		MyObj res = new MyObj(par.getMyVar() + " myFunc2");
		return res;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
        Random randomGenerator = new Random();
        final int RANDOM_NUMBERS  = 1000;
        final int MAX_RANDOM  = 91;
        System.out.println(RANDOM_NUMBERS+ " numeri casuali interi >= 0 e < " + MAX_RANDOM);
        List<Integer> listNum = new ArrayList<Integer>();
        
        for(int i=0; i<RANDOM_NUMBERS;i++) {        	 
        	listNum.add(randomGenerator.nextInt(MAX_RANDOM));
        }
        
        StringBuilder strB = new StringBuilder();
        for(Integer i : listNum) {
        	strB.append(i + " ");
        }
        
        System.out.println(strB.toString());        
        System.out.println("=================");
		
		MyObj ers = new MyObj("ers");
		MyObj ser = new MyObj("ser");
		
		MyObj res  = 10 > 3 ? myFunc(ers) : myFunc(ser);
		System.out.println("res: " + res.getMyVar());
		System.out.println("ers: " + ers.getMyVar());	//il passaggio di parametri di oggetti 
		System.out.println("ser: " + ser.getMyVar());
		
		res  = 10 > 3 ? myFunc2(ers) : myFunc2(ser);
		System.out.println("res: " + res.getMyVar());
		System.out.println("ers: " + ers.getMyVar());	//il passaggio di parametri di oggetti 
		System.out.println("ser: " + ser.getMyVar());
		
		
		List<Long> ll = new ArrayList<Long>();
		ll.add(1L);
		ll.add(2L);
		System.out.println(ll.contains(3L) ? "vero" : "falso");
		
		
	}

}
