package prg.gpxmerger;

import java.util.Date;

import utils.FileUtils;

/**
 * @author Alberto Ielpo
 */
public class MainGpxMerger {

	private static String GPX_FILES_PATH = ".";
	private static String OUTPUT_FILE_NAME = "merge.gpx";
	
	public static void main(String[] args) {
		System.out.println("Start GpxMerger " + new Date());
		
		try {
			String gpxFilesPath = GPX_FILES_PATH;
			if(args.length > 0 && args[0] != null)
				gpxFilesPath = args[0];
			
			GpxMerger gpxMerger = new GpxMerger();
			String mergedStr = gpxMerger.merge(gpxFilesPath);
			if(!"".equals(mergedStr))
				FileUtils.writeFile(gpxFilesPath + "\\" + OUTPUT_FILE_NAME, mergedStr);
				
		} catch (Exception e) {
			System.out.println("Something bad happened");
			e.printStackTrace();
		}
		
		System.out.println("End " + new Date());
	
	}

}
