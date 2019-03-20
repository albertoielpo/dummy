package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.Utils;
import utils.HttpRequestUtils.Base64;

public class JustMain {

	public static void main(String[] args) {
		/* enjoy */
		System.out.println("Basic " + Base64.encode("username:password"));
		System.out.println(Utils.mapAsUnixDate("10/12/2018", Arrays.asList("dd/MM/yyyy"), "GMT+1"));
	}
}
