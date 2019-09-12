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
	
	/**
	 * @param path
	 * @return
	 */
	private static List<File> getAllFiles(String path) {
		List<File> allGpx = new ArrayList<File>();
		File f = new File(path);
		if(f.isDirectory()) {
			File[] files = f.listFiles();
			for(File gpx: files) {
				if(gpx.isFile() && gpx.getAbsolutePath().endsWith(".gpx")) {
					allGpx.add(gpx);
				}
			}
		}
		return allGpx;
	}
	
	/**
	 * 
	 * @param files
	 * @return
	 * @throws IOException
	 */
	private static List<File> orderFiles(List<File> files) throws IOException{
		List<File> res = new ArrayList<File>();
		Map<Long, File> mapFiles = new TreeMap<Long, File>();
		for(File f: files) {
			String str = FileUtils.fileToString(f.getAbsolutePath());
			String d = str.substring(str.indexOf("<time>") + "<time>".length(), str.indexOf("</time>"));
			Long unixDate = Utils.mapAsUnixDate(d.replaceAll("T", " ").replaceAll("Z",""), "yyyy-MM-dd hh:mm:ss", "GMT");
			mapFiles.put(unixDate, f);
		}
		
		for(Map.Entry<Long, File> entry : mapFiles.entrySet())
			res.add(entry.getValue());
		
		return res;
	}
	
	/**
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private static Map<String, String> getHeadFoot(File f) throws IOException {
		Map<String, String> res = new HashMap<String, String>();
		String str = FileUtils.fileToString(f.getAbsolutePath());
		res.put("head", str.substring(0, str.indexOf("</trkseg>")));
		res.put("foot", str.substring(str.indexOf("</trkseg>"), str.length()));
		return res;
	}
	
	/**
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private static String getTrkseg(File f) throws IOException {
		String str = FileUtils.fileToString(f.getAbsolutePath());
		str.substring(str.indexOf("<trkseg>") + "<trkseg>".length(), str.indexOf("</trkseg>"));
		return str;
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
			List<File> allGpxFiles = getAllFiles(gpxFilesPath);
			if(allGpxFiles.size() > 1) {
				
				/* order files by date - using <time>2019-09-12T06:38:31Z</time> */
				List<File> orderedGpxFiles = orderFiles(allGpxFiles);
					
				/* get head of first file and create the merged file called content */
				Map<String,String> headFoot = getHeadFoot(orderedGpxFiles.get(0));
				content.append(headFoot.get("head"));
				
				/* put inside the content what is inside the trkseg segment of all the files */
				for(int ii=1; ii<orderedGpxFiles.size(); ii++)
					content.append(getTrkseg(orderedGpxFiles.get(ii)));
				
				/* append the foot */
				content.append(headFoot.get("foot"));
				
				FileUtils.writeFile(gpxFilesPath + "\\res.gpx", content.toString());
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
