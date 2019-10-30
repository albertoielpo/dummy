package prg.merger.main;

import java.util.Date;

import prg.merger.Merger;
import prg.merger.gpxmerger.GpxMerger;
import prg.merger.tcxmerger.TcxMerger;
import utils.FileUtils;

/**
 * @author Alberto Ielpo
 */
public class MainMerger {

	private static String OUTPUT_FILE_NAME = "merge.";

	public static void main(String[] args) {

		System.out.println("Start Merger " + new Date());

		try {
			if (args.length > 1 && args[1] != null) {
				String filesPath = args[0];
				String extention = args[1];
				if (!(extention.equalsIgnoreCase("gpx") || extention.equalsIgnoreCase("tcx"))) {
					System.out.println("Invalid extention: "+extention+ " - allowed only gpx and tcx");
				} else {
					String mergedStr = "";
					if (extention.equalsIgnoreCase("gpx")) {
						Merger gpxMerger = new GpxMerger();
						mergedStr = gpxMerger.merge(filesPath);
					} else if (extention.equalsIgnoreCase("tcx")) {
						Merger tcxMerger = new TcxMerger();
						mergedStr = tcxMerger.merge(filesPath);
					}

					if (!"".equals(mergedStr))
						FileUtils.writeFile(filesPath + "\\" + OUTPUT_FILE_NAME + extention, mergedStr);
				}

			} else {
				System.out.println("Invalid args - program should be launch with (folder) (extention) params");
				System.out.println("Example: x:\\> %JAVA_HOME%\\bin\\java -jar merger.jar . tcx");
			}

		} catch (Exception e) {
			System.out.println("Something bad happened");
			e.printStackTrace();
		}
		System.out.println("End " + new Date());

	}
}
