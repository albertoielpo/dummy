package dummy;

import java.util.AbstractMap.SimpleEntry;
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
 * 
 */
public class LabelDownloader {

	public static void main(String[] args) {
		final String labelUri = "http://itatlass-app02.faacspa.local:8090/pages/viewpage.action?spaceKey=HUBG&title=Table+JMS+labels+2";
		final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36";
		final String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
		final SimpleEntry<String, String> cookie = 
			new SimpleEntry<String, String>("Cookie","mywork.tab.tasks=false; JSESSIONID=6DB8F069A7DA00B4561662F3DB8BE771");

		System.out.println("START " + new Date());
		System.out.println("uri: " + labelUri);
		System.out.println("userAgent: " + userAgent);
		System.out.println("accept: " + accept);
		System.out.println("cookie: " + cookie);

		StringBuilder sb = new StringBuilder("");
		boolean error = false;

		Map<String, StringBuilder> versionScriptPg = new HashMap<String, StringBuilder>();
		Map<String, StringBuilder> versionScriptMsSql = new HashMap<String, StringBuilder>();

		Set<String> labelVersion = new HashSet<String>();
		List<String> versions = new ArrayList<String>();

		HttpRequestUtils.get(labelUri).userAgent(userAgent).accept(accept).header(cookie).receive(sb);
		Document doc = Jsoup.parse(sb.toString());
		Elements mainContentTable = doc.select("#main-content table");
		if (mainContentTable != null && mainContentTable.size() > 0) {
			/* select all versions */
			Elements rows = mainContentTable.get(0).select("tr");
			for (Element row : rows) {
				Elements cols = row.select("td");
				if (cols != null && cols.size() > 8) {
					if ("validata".equalsIgnoreCase(cols.get(8).text())
							|| "validato".equalsIgnoreCase(cols.get(8).text()))
						labelVersion.add(cols.get(0).text());
				}
			}

			versions.addAll(labelVersion);

			/* do the job */
			rows = mainContentTable.get(0).select("tr");
			for (Element row : rows) {
				Elements cols = row.select("td");
				if (cols != null && cols.size() > 8) {
					if ("validata".equalsIgnoreCase(cols.get(8).text())
							|| "validato".equalsIgnoreCase(cols.get(8).text())) {
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
					String path = "c:\\logs\\pg_" + k + ".sql";
					FileUtils.writeFile(path, versionScriptPg.get(k).toString());
					System.out.println("writed: " + path);
				}
				/* writing files for mssql */
				for (String k : msSqlKeys) {
					String path = "c:\\logs\\mssql_" + k + ".sql";
					FileUtils.writeFile(path, versionScriptPg.get(k).toString());
					System.out.println("writed: " + path);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("END " + new Date());

	}

}
