package test;

import java.util.HashMap;
import java.util.Map;

import utils.Utils;

public class JustMain {


	public static void main(String... args) throws Exception {
		Map<Long, Boolean> m = new HashMap<Long, Boolean>();
		m.put(1L, true);
		System.out.println(Utils.serializeObject(m));
		
	}

}
