package prg;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.FileUtils;
import utils.HttpRequestUtils;

/**
 * @author Alberto Ielpo
 */
public class LabelDownloader {

	/**
	 * filter by label status
	 */
	private static List<String> labelStatusFilter = new ArrayList<String>(Arrays.asList("nuovo", "nuova"));
	
	/**
	 * basic authentication
	 */
	private static String usr = "";
	private static String pwd = "";
	
	
	/* 
	 * filter by developer name -> if null no filter will apply 
	 */
	private static List<String> devNamesFilter = null;
//	private static List<String> devNamesFilter = new ArrayList<String>(Arrays.asList("marco gasbarro"));	//lowercase
	
	
	/* 
	 * output file file properties 
	 */
	private static String outFileRoot = "c:\\logs";
	private static String outFilePrefixPg = "pg_";
	private static String outFilePrefixMsSql = "mssql_";
	private static String outFileExt = ".sql";
	
	
	/**
	 * @param response
	 */
	private static void getData(StringBuilder response) {
		final String labelUri = "http://itatlass-app02.faacspa.local:8090/pages/viewpage.action?spaceKey=HUBG&title=Table+JMS+labels+3";
		final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36";
		final String accept = "text/html,application/xhtml+xml,application/json,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
		
		/* print constants */
		System.out.println("uri: " + labelUri);
		System.out.println("userAgent: " + userAgent);
		System.out.println("accept: " + accept);		
		
		HttpRequestUtils.get(labelUri).userAgent(userAgent).accept(accept).basic(usr, pwd).receive(response);
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("START " + new Date());
		
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
		
		/* args[3] => labelStatusFilter */
		if(args.length >= 4 && !"".equals(args[3]))
			labelStatusFilter = new ArrayList<String>(Arrays.asList(args[3].split(";")));
		
		/* args[4] => devNamesFilter */
		if(args.length >= 5 && !"".equals(args[4])) {
			String[] dnf = args[4].split(";");		
			//trimmed and lowercased
			for(int ii=0; ii<dnf.length; ii++)
				dnf[ii] = dnf[ii].trim().toLowerCase();
			
			devNamesFilter = new ArrayList<String>(Arrays.asList(dnf));
		}

		StringBuilder response = new StringBuilder("");
		boolean error = false;

		Map<String, StringBuilder> versionScriptPg = new HashMap<String, StringBuilder>();
		Map<String, StringBuilder> versionScriptMsSql = new HashMap<String, StringBuilder>();

		Set<String> labelVersion = new HashSet<String>();
		List<String> versions = new ArrayList<String>();

		LabelDownloader.getData(response);
		
		Document doc = Jsoup.parse(response.toString());
		Elements mainContentTable = doc.select("#main-content table");
		if (mainContentTable != null && mainContentTable.size() > 0) {
			/* select all versions */
			Elements rows = mainContentTable.get(0).select("tr");
			for (Element row : rows) {
				Elements cols = row.select("td");
				if (cols != null && cols.size() > 8) {
					String status = cols.get(8).text() != null ? cols.get(8).text().trim().toLowerCase() : "";
					String devName = cols.get(6).text() != null ? cols.get(6).text().trim().toLowerCase() : "";
					
					if(labelStatusFilter.contains(status) && (devNamesFilter == null || devNamesFilter.contains(devName)))
						labelVersion.add(cols.get(0).text());
				}
			}

			versions.addAll(labelVersion);

			/* do the job */
			rows = mainContentTable.get(0).select("tr");
			for (Element row : rows) {
				Elements cols = row.select("td");
				if (cols != null && cols.size() > 8) {
					String status = cols.get(8).text() != null ? cols.get(8).text().trim().toLowerCase() : "";
					String devName = cols.get(6).text() != null ? cols.get(6).text().trim().toLowerCase() : "";
					
					if(labelStatusFilter.contains(status) && (devNamesFilter == null || devNamesFilter.contains(devName))) {
						// cols.get(0).text() => VERSION
						// cols.get(1).text() => CODE
						// cols.get(2).text() => VALUE(en)
						for (int vIndex = 0; vIndex < versions.size(); vIndex++) {
							String curVer = versions.get(vIndex);
							if (curVer.equalsIgnoreCase(cols.get(0).text())) {
								StringBuilder pgBuff = versionScriptPg.get(curVer);
								StringBuilder msBuff = versionScriptMsSql.get(curVer);

								if (pgBuff == null)
									pgBuff = new StringBuilder("");

								if (msBuff == null)
									msBuff = new StringBuilder("");

								String labelType = cols.get(4).text() != null ? cols.get(4).text().trim().toLowerCase() : "";
								if(labelType.contains("admin")) {
									pgBuff.append(
										"select janus_ui_web.lpinsertorupdatelocalizedadminresource("
										+ "'en-US','"
										+ cols.get(1).text() + "','" 
										+ cols.get(2).text() + "');" 
										+ FileUtils.CRLF);
									
									msBuff.append(
										"execute janus_ui_web.lpinsertorupdatelocalizedadminresource "
										+ "'en-US','"
										+ cols.get(1).text() + "','" 
										+ cols.get(2).text() + "' ;" 
										+ FileUtils.CRLF);
								} else {
									pgBuff.append(
										"select janus_ui_web.lpInsertOrUpdateLocalizedResource("
										+ "'en-US','"
										+ cols.get(1).text() + "','"
										+ cols.get(2).text() + "');"
										+ FileUtils.CRLF);
									msBuff.append(
										"execute janus_ui_web.lpInsertOrUpdateLocalizedResource "
										+ "'en-US','"
										+ cols.get(1).text() + "','" 
										+ cols.get(2).text() + "' ;" 
										+ FileUtils.CRLF);
								}

								versionScriptPg.put(curVer, pgBuff);
								versionScriptMsSql.put(curVer, msBuff);
							}
						}
					}
				}
			}
		} else {
			error = true;
			System.out.println("mainContentTable NOT VALID");
		}

		if (!error) {
			Set<String> pgKeys = versionScriptPg.keySet();
			Set<String> msSqlKeys = versionScriptMsSql.keySet();
			try {
				/* writing files for postgres */
				for (String k : pgKeys) {
					String fPath = outFileRoot + File.separator + outFilePrefixPg + k + outFileExt;
					FileUtils.writeFile(fPath, versionScriptPg.get(k).toString());
					System.out.println("writed: " + fPath);
				}
				/* writing files for mssql */
				for (String k : msSqlKeys) {
					String fPath = outFileRoot + File.separator + outFilePrefixMsSql + k +  outFileExt;
					FileUtils.writeFile(fPath, versionScriptMsSql.get(k).toString());
					System.out.println("writed: " + fPath);
				}

			} catch (Exception e) {
				/* something bad happened */
				e.printStackTrace();
			}
		}

		System.out.println("END " + new Date());

	}

}
