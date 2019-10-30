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

	private static String OUTPUT_FILE_NAME = "merge" + new Date().getTime() + ".";

	public static void main(String[] args) {

		System.out.println("Start Merger " + new Date());

		try {
			if (args.length > 1 && args[1] != null) {
				String filesPath = args[0];
				String extension = args[1];
				if (!(extension.equalsIgnoreCase("gpx") || extension.equalsIgnoreCase("tcx"))) {
					System.out.println("Invalid extension: "+extension+ " - allowed only gpx and tcx");
				} else {
					String mergedStr = "";
					if (extension.equalsIgnoreCase("gpx")) {
						Merger gpxMerger = new GpxMerger();
						mergedStr = gpxMerger.merge(filesPath);
					} else if (extension.equalsIgnoreCase("tcx")) {
						Merger tcxMerger = new TcxMerger();
						mergedStr = tcxMerger.merge(filesPath);
					}

					if (!"".equals(mergedStr))
						FileUtils.writeFile(filesPath + "\\" + OUTPUT_FILE_NAME + extension, mergedStr);
				}

			} else {
				System.out.println("Invalid args - program should be launch with (folder) (extension) params");
				System.out.println("Example: x:\\> %JAVA_HOME%\\bin\\java -jar merger.jar . tcx");
			}

		} catch (Exception e) {
			System.out.println("Something bad happened");
			e.printStackTrace();
		}
		System.out.println("End " + new Date());

	}
}
