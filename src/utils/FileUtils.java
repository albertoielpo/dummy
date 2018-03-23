package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

	public static String fileToString(String filePath) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		return content;
	}
	
}