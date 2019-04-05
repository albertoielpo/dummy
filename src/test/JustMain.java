package test;

import utils.Utils;
import utils.HttpRequestUtils.Base64;

public class JustMain {

	public static void main(String[] args) {
		/* enjoy */
		System.out.println("Basic " + Base64.encode("username:password"));

		System.out.println(
			Utils.mapAsUnixDate(
				"Fri Apr 05 2019 12:40:33 GMT+0200 (Central European Summer Time)".substring(0, 24),
				"EEE MMM d yyyy HH:mm:ss", 
					"GMT-1"
		));  //1554468033000 1554460833000
		
	}
}
