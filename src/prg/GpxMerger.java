package prg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import utils.FileUtils;
import utils.Utils;

/**
 * @author Alberto Ielpo
 */
public class GpxMerger {

	private static String GPX_FILES_PATH = ".";
	private static String OUTPUT_FILE_NAME = "merge.gpx";
	
	/**
	 * Get all the gpx files in one directory
	 * @param path
	 * @return
	 */
	private static List<File> getAllFiles(String path) {
		List<File> allGpx = new ArrayList<File>();
		File f = new File(path);
		if(f.isDirectory()) {
			File[] files = f.listFiles();
			for(File gpx: files) {
				if(gpx.isFile() && gpx.getAbsolutePath().toLowerCase().endsWith(".gpx")) {
					allGpx.add(gpx);
				}
			}
		}
		return allGpx;
	}
	
	/**
	 * Order a list of file using <time> tag. If not found into the gpx use current unix timestamp
	 * @param files
	 * @return
	 * @throws IOException
	 */
	private static List<File> orderFiles(List<File> files) throws IOException{
		List<File> res = new ArrayList<File>();
		Map<Long, File> mapFiles = new TreeMap<Long, File>();
		for(File f: files) {
			String strLower = FileUtils.fileToString(f.getAbsolutePath()).toLowerCase();
			long unixDate = new Date().getTime();
			if(strLower.indexOf("<time>") > -1) {
				String d = strLower.substring(strLower.indexOf("<time>") + "<time>".length(), strLower.indexOf("</time>"));
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
	private static Map<String, String> getHeadFoot(File f) throws IOException {
		Map<String, String> res = new HashMap<String, String>();
		String str = FileUtils.fileToString(f.getAbsolutePath());
		String strLower = str.toLowerCase();
		res.put("head", str.substring(0, strLower.indexOf("</trkseg>")));
		res.put("foot", str.substring(strLower.indexOf("</trkseg>"), str.length()));
		return res;
	}
	
	/**
	 * Get the content between trkseg tag
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private static String getTrkseg(File f) throws IOException {
		String str = FileUtils.fileToString(f.getAbsolutePath());
		String strLower = str.toLowerCase();
		return str.substring(strLower.indexOf("<trkseg>") + "<trkseg>".length(), strLower.indexOf("</trkseg>"));
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Start GpxMerger " + new Date());
			StringBuffer content = new StringBuffer();
			
			String gpxFilesPath = GPX_FILES_PATH;
			if(args.length > 0 && args[0] != null)
				gpxFilesPath = args[0];
			
			/* get all files */
			List<File> allGpxFiles = GpxMerger.getAllFiles(gpxFilesPath);
			if(allGpxFiles.size() > 1) {
				
				/* order files by date - using <time>2019-09-12T06:38:31Z</time> */
				List<File> orderedGpxFiles = GpxMerger.orderFiles(allGpxFiles);
					
				/* get head of first file and create the merged file called content */
				Map<String,String> headFoot = GpxMerger.getHeadFoot(orderedGpxFiles.get(0));
				content.append(headFoot.get("head"));
				
				/* put inside the content what is inside the trkseg segment of all the files */
				for(int ii=1; ii<orderedGpxFiles.size(); ii++)
					content.append(GpxMerger.getTrkseg(orderedGpxFiles.get(ii)));
				
				/* append the foot */
				content.append(headFoot.get("foot"));
				
				FileUtils.writeFile(gpxFilesPath + "\\" + OUTPUT_FILE_NAME, content.toString());
			} else {
				System.out.println("Found " + allGpxFiles.size() + " gpx files. Nothing to do");
			}

		} catch (IOException e) {
			System.out.println("Something bad happeend");
			e.printStackTrace();
		}
		
		System.out.println("End " + new Date());
	}
	
}
