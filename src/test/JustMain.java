package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JustMain {

	public static void main(String... args) throws Exception {
		
		List<String> a = new ArrayList<String>();
		a.add("pippo");
		String[] b = a.toArray(new String[a.size()]);
		System.out.println(Arrays.toString(b));
		
	}

}
