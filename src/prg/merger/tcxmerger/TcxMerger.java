package prg.merger.tcxmerger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import prg.merger.Merger;
import utils.FileUtils;
import utils.Utils;

/**
 * @author Alberto Ielpo
 */
public class TcxMerger implements Merger {


	/**
	 * Get all the tcx files in one directory
	 * @param path
	 * @return
	 */
	private List<File> getAllFiles(String path) {
		List<File> allTcx = new ArrayList<File>();
		File f = new File(path);
		if(f.isDirectory()) {
			File[] files = f.listFiles();
			for(File tcx: files) {
				if(tcx.isFile() && tcx.getAbsolutePath().toLowerCase().endsWith(".tcx")) {
					allTcx.add(tcx);
				}
			}
		}
		return allTcx;
	}
	
	/**
	 * Order a list of file using <Lap StartTime> tag. If not found into the gpx use current unix timestamp
	 * @param files
	 * @return
	 * @throws IOException
	 */
	private List<File> orderFiles(List<File> files) throws IOException{
		String lap = "<lap starttime=\"";
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
		String activity = "</activity>";
		String str = FileUtils.fileToString(f.getAbsolutePath());
		String strLower = str.toLowerCase();
		res.put("head", str.substring(0, strLower.lastIndexOf(activity) + activity.length()));
		res.put("foot", str.substring(strLower.lastIndexOf(activity) + activity.length(), str.length()));
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
		String startActivity = "<activity";
		String endActivity = "</activity>";
		String strLower = str.toLowerCase();
		return str.substring(strLower.indexOf(startActivity), strLower.indexOf(endActivity) + endActivity.length());
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
		List<File> allTcxFiles = this.getAllFiles(tcxFilesPath);
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
