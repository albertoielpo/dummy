package prg;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.FileUtils;
import utils.HttpRequestUtils;

/**
 * @author Alberto Ielpo
 */
public class JenkinsDownloader {

	/**
	 * basic authentication
	 */
	private static String usr = "janus";
	private static String pwd = "janus";
	private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36";
	private static String accept = "text/html,application/xhtml+xml,application/json,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
	private static String outFileRoot = "C:\\logs";
	
	private static boolean isGreaterThanZero(String str) {
		try {
			if(Integer.valueOf(str) > 0) 
				return true;
		}catch(Exception e) {
			//continue
		}
		return false;
	}
	
	/**
	 * 
	 * @param labelUri
	 * @param response
	 */
	private static void getUriContent(String labelUri, StringBuilder response) {
		/* print constants */
		System.out.println("uri: " + labelUri);
		HttpRequestUtils.get(labelUri).userAgent(userAgent).accept(accept).basic(usr, pwd).receive(response);
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("START " + new Date());
		System.out.println("userAgent: " + userAgent);
		System.out.println("accept: " + accept);
		
		/**
		 * args[0] => username
		 * args[1] => password
		 */
		if(args != null && args.length >= 2) {
			usr = args[0];
			pwd = args[1];
		}
		
		/* args[2] => outFileRoot */
		if(args.length >= 3 && !"".equals(args[2]))
			outFileRoot = args[2];	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");
		
		StringBuilder summaryResponse = new StringBuilder("");
		StringBuilder testFailed = new StringBuilder("");
		
		StringBuilder output = new StringBuilder("Jenkins tests" + FileUtils.CRLF);
		output.append("===========================" + FileUtils.CRLF);
		
		final String summaryUri = "http://172.29.1.36:8080/job/Janus%20Automatic%20Test/lastBuild/testReport/";
		JenkinsDownloader.getUriContent(summaryUri, summaryResponse);
		try {
			Document doc = Jsoup.parse(summaryResponse.toString());
			Elements mainContentTable = doc.select("#testresult");
			if (mainContentTable != null) {
				Elements rows = mainContentTable.get(0).select("tr");
				for (Element row : rows) {
					Elements cols = row.select("td");
					if (cols != null && cols.size() > 1) {
						String fail = cols.get(1).text() != null ? cols.get(1).text().trim().toLowerCase() : "0";
						if(isGreaterThanZero(fail)) 
							testFailed.append(cols.get(0).text() + " " + fail + FileUtils.CRLF);
					}
				}
			}
			
			if(!"".equalsIgnoreCase(testFailed.toString())){
				/** download full stack if any error occurred */
				final String fullStackUri = "http://172.29.1.36:8080/job/Janus%20Automatic%20Test/lastBuild/consoleText";
				StringBuilder fullStack = new StringBuilder("");
				JenkinsDownloader.getUriContent(fullStackUri, fullStack);
				try {
					String outputFilePath = outFileRoot + "\\jenkins_stack_" + sdf.format(new Date()) + ".log";
					FileUtils.writeFile(outputFilePath, fullStack.toString());
					System.out.println("created: " + outputFilePath);
				} catch(Exception e) {
					//something bad happened
					e.printStackTrace();
				}
			} else {
				output.append("0 test failed");
			}
			
			String outputFilePath = outFileRoot + "\\jenkins_summary_" + sdf.format(new Date()) + ".log";
			FileUtils.writeFile(outputFilePath, output.toString() + testFailed.toString());
			System.out.println("created: " + outputFilePath);
			
		} catch(Exception e) {
			//something bad happened
			e.printStackTrace();
		}
		
		System.out.println("END " + new Date());

	}

}
