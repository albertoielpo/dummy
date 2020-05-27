package test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JustMain {

	
	public static void main(String... args) {
		
		List<String> a = new ArrayList<String>();
		var b = a.stream().map(c -> c.toUpperCase()).collect(Collectors.toList());
		System.out.println(b);
		
	}

}
