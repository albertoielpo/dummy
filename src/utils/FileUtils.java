package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

	
}