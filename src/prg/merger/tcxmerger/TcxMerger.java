package prg.merger.tcxmerger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import prg.merger.AbstractMerger;
import prg.merger.Merger;
import utils.FileUtils;
import utils.Utils;

/**
 * TcxMerger merge multiple tcx files into one without data loss
 * @author Alberto Ielpo
 */
public class TcxMerger extends AbstractMerger implements Merger {

	private static String TCX_EXTENSION = ".tcx";
	private static String LAP_TAG = "<lap starttime=\"";
	private static String TRACK_START_TAG = "<track>";
	private static String TRACK_END_TAG = "</track>";
	
	/**
	 * Sum stats 
	 * @see {prg.merger.tcxmerger.TcxMerger#sumFilesHeaderStats} 
	 * @see {prg.merger.tcxmerger.TcxMerger#updateHeadWithStats}
	 */
	private static String TOTAL_TIME_SECONDS_TAG = "<totaltimeseconds>";
	private static String TOTAL_TIME_SECONDS_END_TAG = "</totaltimeseconds>";
	private static String DISTANCE_METERS_TAG = "<distancemeters>";
	private static String DISTANCE_METERS_END_TAG = "</distancemeters>";
	private static String CALORIES_TAG = "<calories>";
	private static String CALORIES_END_TAG = "</calories>";
	private static String CAMEL_TOTAL_TIME_SECONDS_TAG = "<TotalTimeSeconds>";
	private static String CAMEL_TOTAL_TIME_SECONDS_END_TAG = "</TotalTimeSeconds>";
	private static String CAMEL_DISTANCE_METERS_TAG = "<DistanceMeters>";
	private static String CAMEL_DISTANCE_METERS_END_TAG = "</DistanceMeters>";
	private static String CAMEL_CALORIES_TAG = "<Calories>";
	private static String CAMEL_CALORIES_END_TAG = "</Calories>";
	
	/**
	 * Order a list of file using <Lap StartTime> tag. If not found into the tcx use current unix timestamp
	 * @param files
	 * @return
	 * @throws IOException
	 */
	private List<File> orderFiles(List<File> files) throws IOException{
		String lap = LAP_TAG;
		List<File> res = new ArrayList<File>();
		Map<Long, File> mapFiles = new TreeMap<Long, File>();
		for(File f: files) {
			String strLower = FileUtils.fileToString(f.getAbsolutePath()).toLowerCase();
			long unixDate = new Date().getTime();
			if(strLower.indexOf(lap) > -1) {
				String d = strLower.substring(strLower.indexOf(lap) + lap.length(), strLower.indexOf(lap) + lap.length() + 19);
				unixDate = Utils.mapAsUnixDate(d.replaceAll("t", " ").replaceAll("z",""), "yyyy-MM-dd hh:mm:ss", "GMT");
			}
			
			mapFiles.put(unixDate, f);
		}
		
		for(Map.Entry<Long, File> entry : mapFiles.entrySet())
			res.add(entry.getValue());
		
		return res;
	}
	
	/**
	 * Get the head (including activity track) and the foot of the tcx
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private Map<String, String> getHeadFoot(File f) throws IOException {
		Map<String, String> res = new HashMap<String, String>();
		String str = FileUtils.fileToString(f.getAbsolutePath());
		String strLower = str.toLowerCase();
		res.put("head", str.substring(0, strLower.lastIndexOf(TRACK_END_TAG) + TRACK_END_TAG.length()));
		res.put("foot", str.substring(strLower.lastIndexOf(TRACK_END_TAG) + TRACK_END_TAG.length(), str.length()));
		return res;
	}
	
	/**
	 * Get the content between activity tag
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private String getActivitySeg(File f) throws IOException {
		String str = FileUtils.fileToString(f.getAbsolutePath());
		String strLower = str.toLowerCase();
		return str.substring(strLower.indexOf(TRACK_START_TAG), strLower.lastIndexOf(TRACK_END_TAG) + TRACK_END_TAG.length());
	}
	
	/**
	 * Not working with Strava
	 * @param fs
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private Map<String, String> sumFilesHeaderStats(List<File> fs) throws IOException {
		
		float totalTimeSeconds = 0;
		float distanceMeters = 0;
		int calories = 0;
		
		Map<String, String> res = new HashMap<String, String>();
		for(File f : fs) {
			String str = FileUtils.fileToString(f.getAbsolutePath());
			String strLower = str.toLowerCase();
			String head = str.substring(0, strLower.indexOf(TRACK_START_TAG));;
			String headLower = head.toLowerCase();
			
			String tts = head.substring(headLower.indexOf(TOTAL_TIME_SECONDS_TAG) + TOTAL_TIME_SECONDS_TAG.length() , headLower.indexOf(TOTAL_TIME_SECONDS_END_TAG));
			String dm = head.substring(headLower.indexOf(DISTANCE_METERS_TAG) + DISTANCE_METERS_TAG.length() , headLower.indexOf(DISTANCE_METERS_END_TAG));
			String cal = head.substring(headLower.indexOf(CALORIES_TAG) + CALORIES_TAG.length() , headLower.indexOf(CALORIES_END_TAG));
			
			try {
				float ttsF = Float.valueOf(tts);
				totalTimeSeconds = totalTimeSeconds + ttsF;
			} catch (Exception e) {
				System.out.println("Invalid Total Time Second");
			}
			
			try {
				float dmF = Float.valueOf(dm);
				distanceMeters = distanceMeters + dmF;
			} catch (Exception e) {
				System.out.println("Invalid Distance meters");
			}
			
			try {
				int calF = Integer.valueOf(cal);
				calories = calories + calF;
			} catch (Exception e) {
				System.out.println("Invalid Calories");
			}
		}
		
		res.put("totalTimeSeconds", CAMEL_TOTAL_TIME_SECONDS_TAG + totalTimeSeconds + CAMEL_TOTAL_TIME_SECONDS_END_TAG);
		res.put("distanceMeters", CAMEL_DISTANCE_METERS_TAG + distanceMeters + CAMEL_DISTANCE_METERS_END_TAG);
		res.put("calories", CAMEL_CALORIES_TAG + calories + CAMEL_CALORIES_END_TAG);
		
		return res;
	}
	
	/**
	 * Not working with Strava
	 * @param head
	 * @param stats
	 * @return
	 */
	@SuppressWarnings("unused")
	private String updateHeadWithStats(String head, Map<String, String> stats) {
		head = head.replaceAll("(?i)"+TOTAL_TIME_SECONDS_TAG + ".+" + "(?i)"+TOTAL_TIME_SECONDS_END_TAG, stats.get("totalTimeSeconds"));
		head = head.replaceAll("(?i)"+DISTANCE_METERS_TAG + ".+" +"(?i)"+ DISTANCE_METERS_END_TAG, stats.get("distanceMeters"));
		head = head.replaceAll("(?i)"+CALORIES_TAG + ".+" + "(?i)"+CALORIES_END_TAG, stats.get("calories"));
		return head;
	}
	
	/**
	 * Merge all tcx files contained into tcxFilesPath
	 * @param tcxFilesPath
	 * @return
	 * @throws Exception
	 */
	public String merge(String tcxFilesPath) throws Exception {
		StringBuffer content = new StringBuffer("");
		/* get all files */
		List<File> allTcxFiles = this.getAllFiles(tcxFilesPath, TCX_EXTENSION);
		if(allTcxFiles.size() > 1) {
			System.out.println("Found " + allTcxFiles.size() + " tcx files");
			/* order files by date - using <time>2019-09-12T06:38:31Z</time> */
			List<File> orderedTcxFiles = this.orderFiles(allTcxFiles);
				
			/* get head of first file and create the merged file called content */
			Map<String,String> headFoot = this.getHeadFoot(orderedTcxFiles.get(0));
			content.append(headFoot.get("head"));
			
			/* put inside the content what is inside the trkseg segment of all the files */
			for(int ii=1; ii<orderedTcxFiles.size(); ii++)
				content.append(this.getActivitySeg(orderedTcxFiles.get(ii)));
			
			/* append the foot */
			content.append(headFoot.get("foot"));
			
		} else {
			System.out.println("Found " + allTcxFiles.size() + " tcx files. Nothing to do");
		}

	
		return content.toString();
	}

}
