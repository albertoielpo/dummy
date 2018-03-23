package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
	
	public static byte[] fileToByteArray(String filePath) throws IOException {
		return Files.readAllBytes(Paths.get(filePath));
	}
	
	public static String fileToString(String filePath) throws IOException {
		return new String(fileToByteArray(filePath));
	}
	

	
}