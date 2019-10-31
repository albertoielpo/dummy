package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Alberto Ielpo
 */
public class FileUtils {
	
	/* New line */
	public static char CR = 0x0D;				//Mac
	public static char LF = 0x0A;				//Unix
	public static String CRLF = "" + CR + LF;	//Windows
	
	/**
	 * Read a file using filePath.
	 * @param filePath
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] fileToByteArray(String filePath) throws IOException {
		return Files.readAllBytes(Paths.get(filePath));
	}
	
	/**
	 * Read a file using filePath
	 * @param filePath
	 * @return String
	 * @throws IOException
	 */
	public static String fileToString(String filePath) throws IOException {
		return new String(fileToByteArray(filePath));
	}
	
	/**
	 * Write any String content to File
	 * @param path
	 * @param content
	 * @throws IOException
	 */
	public static void writeFile(String path, String content) throws IOException {
		Files.write(Paths.get(path), content.getBytes());
	}

	
	/**
	 * Get all files using extension as filter
	 * @param path
	 * @param extension
	 * @return
	 * @throws Exception
	 */
	public static List<File> getAllFiles(String path, String extension) throws Exception {
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