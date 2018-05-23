package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
	
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
	
	public static void writeFile(String path, String content) throws IOException {
		Files.write(Paths.get(path), content.getBytes());
	}
	

	
}