package com.crawler;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class SimpleCrawler {

	private HashSet<String> linksVisited;
	static String URL_LINK;
	private static final int DEPTH_SEARCH = 1;
	PrintWriter pwriter;

	public SimpleCrawler() {
		linksVisited = new HashSet<>();
	}

	public Set<String> searchCrawlURLProcess(String URL, String search, int depth) throws IOException {
		Set<String> links = new HashSet<String>();

		if (!linksVisited.contains(URL) && (depth < DEPTH_SEARCH)) {
			try {
				linksVisited.add(URL);
				Connection conn = Jsoup.connect(URL);
				Response response = conn.response();
				Document document = conn.get();
				System.out.println(response.statusCode() + " : " + response.url());
				// Elements linksOnPage = document.select("a[href]");
				Elements linksOnPage = getHrefLinks(document);
				depth++;

				links = getAbsHrefLinks(linksOnPage);
				int size = links.size();
				System.out.println("Size => "+size);
				// searchCrawlURLProcess(page.attr("abs:href"),search,depth);
				for (String link : links) {
					System.out.println(link);
					searchCrawlURLProcess(link, search, depth);
				}

			}
			catch (IOException e) {
				System.err.println(URL + "': " + e.getMessage());
			}
		}
		return links;
	}

	public Elements getHrefLinks(Document doc) {
		return doc.select("a[href]");
	}

	public Set<String> getAbsHrefLinks(Elements elements) {
		Set<String> list = new HashSet<>();
		elements.forEach(element -> list.add(element.attr("abs:href")));
		calltoUpdateFile(list);
		return list;
	}

	private void calltoUpdateFile(Set<String> list) {
		try {
			pwriter = new PrintWriter(new FileWriter("G://LinksBufferedWriter.txt", true), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pwriter.println(list);
		pwriter.close();
	}

	public static void main(String[] args) throws IOException {
		URL_LINK = "http://wiprodigital.com";
		String search = "wiprodigital.com";
		SimpleCrawler sc = new SimpleCrawler();
		Set<String> getList = sc.searchCrawlURLProcess(URL_LINK, search, 0);
		System.out.println("From GetList call => " + getList);
		// sc.writeToFile("G:\\CrawlerLinks.txt");
	}
}