package dummy;

import java.util.AbstractMap.SimpleEntry;
import java.io.File;
import java.util.ArrayList;
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
	 * requires JSESSIONID token
	 */
	private static void usingGet(StringBuilder response) {
		final String labelUri = "http://itatlass-app02.faacspa.local:8090/pages/viewpage.action?spaceKey=HUBG&title=Table+JMS+labels+2";
		final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36";
		final String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
		final SimpleEntry<String, String> cookie = new SimpleEntry<String, String>("Cookie","mywork.tab.tasks=false; JSESSIONID=67F43BE851773E308CBC68860DE68B5C");
		
		/* print constants */
		System.out.println("uri: " + labelUri);
		System.out.println("userAgent: " + userAgent);
		System.out.println("accept: " + accept);
		System.out.println("cookie: " + cookie);
		
		HttpRequestUtils.get(labelUri).userAgent(userAgent).accept(accept).header(cookie).receive(response);
	}
	
	/* not working */
	@SuppressWarnings("unused")
	private static void usingPost(StringBuilder response) {
		final String labelUri = "http://itatlass-app02.faacspa.local:8090/pages/viewpage.action?spaceKey=HUBG&title=Table+JMS+labels+2";
		
		Map<String, String> headers = new HashMap<String, String>();
		//headers.put("Host", "itatlass-app02.faacspa.local:8090");
		headers.put("Connection", "keep-alive");
		headers.put("Content-Length", "160");
		//headers.put("Cache-Control", "max-age=0");
		//headers.put("Origin", "http://itatlass-app02.faacspa.local:8090");
		//headers.put("Upgrade-Insecure-Requests", "1");
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headers.put("Referer", "http://itatlass-app02.faacspa.local:8090/login.action?os_destination=%2Fpages%2Fviewpage.action%3FspaceKey%3DHUBG%26title%3DTable%2BJMS%2Blabels%2B2&permissionViolation=true");
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("os_username", "alberto.ielpo");
		data.put("os_password", "Titolavoro0!");
		data.put("login", "Log+in");
		data.put("os_destination", "/pages/viewpage.action?spaceKey=HUBG");
		data.put("title", "Table+JMS+labels+2");		
		
		System.out.println("headers " + headers);
		HttpRequestUtils.post(labelUri).headers(headers).form(data).receive(response);
	}
	
	
	public static void main(String[] args) {
		/* output file file properties */
		final String outFileRoot = "c:\\logs";
		final String outFilePrefixPg = "pg_";
		final String outFilePrefixMsSql = "mssql_";
		final String outFileExt = ".sql";
		
		System.out.println("START " + new Date());

		StringBuilder response = new StringBuilder("");
		boolean error = false;

		Map<String, StringBuilder> versionScriptPg = new HashMap<String, StringBuilder>();
		Map<String, StringBuilder> versionScriptMsSql = new HashMap<String, StringBuilder>();

		Set<String> labelVersion = new HashSet<String>();
		List<String> versions = new ArrayList<String>();

		LabelDownloader.usingGet(response);
		//LabelDownloader.usingPost(response);
		
		Document doc = Jsoup.parse(response.toString());
		Elements mainContentTable = doc.select("#main-content table");
		if (mainContentTable != null && mainContentTable.size() > 0) {
			/* select all versions */
			Elements rows = mainContentTable.get(0).select("tr");
			for (Element row : rows) {
				Elements cols = row.select("td");
				if (cols != null && cols.size() > 8) {
					String status = cols.get(8).text() != null ? cols.get(8).text().trim() : "";
					if ("validata".equalsIgnoreCase(status) || "validato".equalsIgnoreCase(status))
						labelVersion.add(cols.get(0).text());
				}
			}

			versions.addAll(labelVersion);

			/* do the job */
			rows = mainContentTable.get(0).select("tr");
			for (Element row : rows) {
				Elements cols = row.select("td");
				if (cols != null && cols.size() > 8) {
					String status = cols.get(8).text() != null ? cols.get(8).text().trim() : "";
					if ("validata".equalsIgnoreCase(status) || "validato".equalsIgnoreCase(status)) {
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

								pgBuff.append("select janus_ui_web.lpInsertOrUpdateLocalizedResource('en-US','"
										+ cols.get(1).text() + "','" + cols.get(2).text() + "');\r\n");
								msBuff.append("execute janus_ui_web.lpInsertOrUpdateLocalizedResource 'en-US','"
										+ cols.get(1).text() + "','" + cols.get(2).text() + "' ;\r\n");

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
					FileUtils.writeFile(fPath, versionScriptPg.get(k).toString());
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
