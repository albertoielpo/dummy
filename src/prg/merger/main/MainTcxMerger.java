package prg.merger.main;

import java.util.Date;

import prg.merger.Merger;
import prg.merger.tcxmerger.TcxMerger;
import utils.FileUtils;

/**
 * @author Alberto Ielpo
 */
public class MainTcxMerger {

	private static String TCX_FILES_PATH = ".";
	private static String OUTPUT_FILE_NAME = "merge.tcx";

	public static void main(String[] args) {

		System.out.println("Start TcxMerger " + new Date());

		try {
			String tcxFilesPath = TCX_FILES_PATH;
			if (args.length > 0 && args[0] != null)
				tcxFilesPath = args[0];

			Merger tcxMerger = new TcxMerger();
			String mergedStr = tcxMerger.merge(tcxFilesPath);
			if (!"".equals(mergedStr))
				FileUtils.writeFile(tcxFilesPath + "\\" + OUTPUT_FILE_NAME, mergedStr);

		} catch (Exception e) {
			System.out.println("Something bad happened");
			e.printStackTrace();
		}

		System.out.println("End " + new Date());

	}

}
