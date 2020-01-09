package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import utils.Utils;

public class JustMain {
	
	public static void main(String[] args) {

		List<MyObj> vps = new ArrayList<>(
			Arrays.asList(
				new MyObj("par",1),
				new MyObj("par",5),
				new MyObj("par",654),
				new MyObj("par",-1)));
		
		List<MyObj> vps2 = new ArrayList<>(
				Arrays.asList(
					new MyObj("par",-1),
					new MyObj("par",1),
					new MyObj("par",654),
					new MyObj("par",5)));
		
		var res = Utils.emptyIfNull(vps).stream().map(MyObj::getMyInt).sorted().collect(Collectors.toList());
		var res2 = Utils.emptyIfNull(vps2).stream().map(MyObj::getMyInt).sorted().collect(Collectors.toList());
		if(!res.containsAll(res2)) 
			System.out.println("error: " + res + " " + res2);
		else
			System.out.println("good: " + res + " " + res2);
		
	}
}
