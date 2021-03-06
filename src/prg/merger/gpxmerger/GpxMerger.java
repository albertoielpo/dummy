package prg.merger.gpxmerger;

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
 * GpxMerger merge multiple gpx files into one without data loss
 * @author Alberto Ielpo
 */
public class GpxMerger extends AbstractMerger implements Merger {

	private static String GPX_EXTENSION = ".gpx";
	private static String TIME_TAG = "<time>";
	private static String TIME_END_TAG = "</time>";
	private static String TRACK_TAG = "<trkseg>";
	private static String TRACK_END_TAG = "</trkseg>";
	
	/**
	 * Order a list of file using <time> tag. If not found into the gpx use current unix timestamp
	 * @param files
	 * @return
	 * @throws IOException
	 */
	private List<File> orderFiles(List<File> files) throws IOException{
		List<File> res = new ArrayList<File>();
		Map<Long, File> mapFiles = new TreeMap<Long, File>();
		for(File f: files) {
			String strLower = FileUtils.fileToString(f.getAbsolutePath()).toLowerCase();
			long unixDate = new Date().getTime();
			if(strLower.indexOf(TIME_TAG) > -1) {
				String d = strLower.substring(strLower.indexOf(TIME_TAG) + TIME_TAG.length(), strLower.indexOf(TIME_END_TAG));
				unixDate = Utils.mapAsUnixDate(d.replaceAll("t", " ").replaceAll("z",""), "yyyy-MM-dd hh:mm:ss", "GMT");
			}
			
			mapFiles.put(unixDate, f);
		}
		
		for(Map.Entry<Long, File> entry : mapFiles.entrySet())
			res.add(entry.getValue());
		
		return res;
	}
	
	/**
	 * Get the head (including trkseg track) and the foot of the gpx
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private Map<String, String> getHeadFoot(File f) throws IOException {
		Map<String, String> res = new HashMap<String, String>();
		String str = FileUtils.fileToString(f.getAbsolutePath());
		String strLower = str.toLowerCase();
		res.put("head", str.substring(0, strLower.indexOf(TRACK_END_TAG)));
		res.put("foot", str.substring(strLower.indexOf(TRACK_END_TAG), str.length()));
		return res;
	}
	
	/**
	 * Get the content between trkseg tag
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private String getTrkseg(File f) throws IOException {
		String str = FileUtils.fileToString(f.getAbsolutePath());
		String strLower = str.toLowerCase();
		return str.substring(strLower.indexOf(TRACK_TAG) + TRACK_TAG.length(), strLower.indexOf(TRACK_END_TAG));
	}
	
	/**
	 * Merge all gpx files contained into gpxFilesPath
	 * @param gpxFilesPath
	 * @return
	 * @throws Exception
	 */
	@Override
	public String merge(String gpxFilesPath) throws Exception {
		StringBuffer content = new StringBuffer("");
		/* get all files */
		List<File> allGpxFiles = FileUtils.getAllFiles(gpxFilesPath, GPX_EXTENSION);
		if(allGpxFiles.size() > 1) {
			System.out.println("Found " + allGpxFiles.size() + " gpx files");
			/* order files by date - using <time>2019-09-12T06:38:31Z</time> */
			List<File> orderedGpxFiles = this.orderFiles(allGpxFiles);
				
			/* get head of first file and create the merged file called content */
			Map<String,String> headFoot = this.getHeadFoot(orderedGpxFiles.get(0));
			content.append(headFoot.get("head"));
			
			/* put inside the content what is inside the trkseg segment of all the files */
			for(int ii=1; ii<orderedGpxFiles.size(); ii++)
				content.append(this.getTrkseg(orderedGpxFiles.get(ii)));
			
			/* append the foot */
			content.append(headFoot.get("foot"));
			
		} else {
			System.out.println("Found " + allGpxFiles.size() + " gpx files. Nothing to do");
		}

	
		return content.toString();
	}
}
