package prg.merger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alberto Ielpo
 */
public abstract class AbstractMerger {

	/**
	 * Get all files in one path using extension as filter
	 * @param path
	 * @param extension
	 * @return
	 */
	protected List<File> getAllFiles(String path, String extension) {
		List<File> allFiles = new ArrayList<File>();
		File f = new File(path);
		if(f.isDirectory()) {
			File[] files = f.listFiles();
			for(File file: files) {
				if(file.isFile() && file.getAbsolutePath().toLowerCase().endsWith(extension)) {
					allFiles.add(file);
				}
			}
		}
		return allFiles;
	}
	
}
