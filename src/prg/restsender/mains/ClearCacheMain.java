package prg.restsender.mains;

import java.util.Date;
import java.util.HashMap;

import prg.restsender.RestSender;

/**
 * @author Alberto Ielpo
 */
public class ClearCacheMain {

	/**
	 * Evict all caches
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start ClearCacheMain " + new Date());
		System.out.println("Evict all caches " + RestSender.CACHE_EVICT_ALL);
		
		try {
			RestSender r = new RestSender();
			r.putForJanusAdminUi(RestSender.CACHE_EVICT_ALL, new HashMap<>());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("End ClearCacheMain " + new Date());
	}
}