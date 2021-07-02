package prg;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.FileUtils;
import utils.Utils;

/**
 * @author Alberto Ielpo
 */
public class FileShaCalculator {

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String... args) throws Exception {
		String sourceDir = "C:\\dev\\git\\janus-dev-a\\janus\\janus-dbmigration\\src\\main\\resources\\db\\migration_sqlserver";
		String destinationFile = "C:\\logs\\out.sqlserver.log";
		
		if(args != null && args.length > 1) {
			sourceDir = args[0];
			destinationFile = args[1];
		}
				
		System.out.println("start: " + new Date());
		System.out.println("source dir: " + sourceDir);
		System.out.println("destination file: " + destinationFile);
		// key is the sha
		StringBuffer output = new StringBuffer();
		
		Map<String, List<String>> shaFiles = new HashMap<String, List<String>>();
		File f = new File(sourceDir);
		
		output.append("=======================================" + FileUtils.CRLF);
		output.append("FILES LIST" + FileUtils.CRLF);
		output.append("=======================================" + FileUtils.CRLF);
		for (File file : f.listFiles()) {
			if (file.isDirectory())
				continue;
			String content = FileUtils.fileToString(file.getAbsolutePath());
			String sha = Utils.sha1(content);
			output.append(file.getName() + " " + sha + FileUtils.CRLF);
			if (shaFiles.get(sha) == null) {
				shaFiles.put(sha, new ArrayList<String>());
			}
			shaFiles.get(sha).add(file.getName());
		}

		output.append("=======================================" + FileUtils.CRLF);
		output.append("DUPLICATE CONTENT FILES" + FileUtils.CRLF);
		output.append("=======================================" + FileUtils.CRLF);
		StringBuffer deleteBranchScripts = new StringBuffer();
		for(var a : shaFiles.values()) {
			if(a.size() > 1) {
				boolean atLeastOneBranchScript = false;
				boolean atLeastOneMasterScript = false;
				for(String b : a) {
					String c = b.substring(0, b.indexOf("__"));
					String[] d = c.split("_");
					atLeastOneBranchScript = atLeastOneBranchScript || (d.length > 3);
					atLeastOneMasterScript = atLeastOneMasterScript || (d.length < 4);
										
				}
				/* true if atLeastOneBranchScript and atLeastOneMasterScript so I can delete it the branch one */
				if(atLeastOneBranchScript && atLeastOneMasterScript) {
					deleteBranchScripts.append(a + FileUtils.CRLF);
				}
				output.append(a + FileUtils.CRLF);
			}
		}
		
		output.append("=======================================" + FileUtils.CRLF);
		output.append("DELETE BRANCH SCRIPTS" + FileUtils.CRLF);
		output.append("=======================================" + FileUtils.CRLF);
		if(deleteBranchScripts.length() == 0) {
			output.append("Nothing to do...." + FileUtils.CRLF);
		} else {
			output.append(deleteBranchScripts);			
		}
		
		output.append("=======================================" + FileUtils.CRLF);		
		FileUtils.writeFile(destinationFile, output.toString());
		System.out.println("end: " +new Date());
	}

}
