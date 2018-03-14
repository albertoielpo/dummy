package dummy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PlayWithFiles {

	public static void main(String[] args) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\Alberto Ielpo\\Google Drive\\automatic-test\\excludeTest.txt")));
		List<String> c = Arrays.asList(content.split("\n"));
		Utils.emptyIfNull(c).forEach(line -> {
			/* class name */
			int beginIndex = line.lastIndexOf("\\");
			int endIndex = line.lastIndexOf(".java");
			String className = line.substring(beginIndex + 1, endIndex + 5);
			
			/* hit times */
			beginIndex = line.lastIndexOf("(");
			endIndex = line.lastIndexOf(")");
			String hitTimes = line.substring(beginIndex + 1, endIndex);
			
			/* package name */
			beginIndex = line.indexOf("\\");
			endIndex = line.indexOf("\\", beginIndex +1);
			String packageName = line.substring(beginIndex + 1, endIndex);
			
			System.out.println(
					packageName + "->" + 
					className   + "->" + 
					hitTimes.replaceAll("hits", "tests excluded").replaceAll("hit", "test excluded"));
		});
	}
}
