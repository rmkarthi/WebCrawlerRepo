package com.crawler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CrawlerUnitTest {

	SimpleCrawler scTest;
	String URLToTest="http://wiprodigital.com";
	String searchWord="wiprodigital";
	@SuppressWarnings("null")
	
	public void test() throws Exception {
		
		final String URL = "https://wiprodigital.com";
		scTest = new SimpleCrawler();
		Set<String> setURLs = new HashSet<>();
		Set<String> actualGetListTest = scTest.searchCrawlURLProcess(URLToTest,searchWord,0);
		List<String> expectedGetList = null;
		setURLs.add("http://wiprodigital.com");
		setURLs.add("https://wiprodigital.com");
		setURLs.add("https://wiprodigital.com/who-we-are");
		setURLs.add("https://wiprodigital.com/what-we-do");
		setURLs.add("https://wiprodigital.com/who-we-are/#wdteam-vid");
		setURLs.add("https://wiprodigital.com/what-we-do#work-three-circles-row");
		setURLs.add("https://wiprodigital.com/who-we-are#wdteam_meetus");
		setURLs.add("https://wiprodigital.com/what-we-do#wdwork_cases");
		setURLs.add("https://wiprodigital.com/who-we-are#wdteam_leaders");
		setURLs.add("https://wiprodigital.com/what-we-do#wdwork_partners");		
	
		@Test
		public final testGetZeroLinks(){     
			Set<String> links = scTest.getAbsHrefLinks("http://www.google.com");
			Assert.assertEquals(links.size(),0);
		}

		@Test
	    public final testGetLinks(){    
			Set<String> links = scTest.getAbsHrefLinks("https://wiprodigital.com");
			Assert.assertEquals(links.size > 0);
		}
		
		@Test
	    public final testCompareLinkSize(){    
			Set<String> links = scTest.getAbsHrefLinks("https://wiprodigital.com");
			Assert.assertEquals(links.size(),setURLs.size());
		}
		
		@Test
		public void testMainClass() {
			Set<String> getList = sc.searchCrawlURLProcess(URL, search, 0);
			Assert.assertTrue("The files differ!", FileUtils.contentEquals(getList, "G://LinksBufferedWriter.txt"));
		}
		
		@Test
		public void checkInline() {
		    File actualFile = new File("actual.txt");
		    Assert.assertThat(linesOf(actualFile)).containsExactly(
		            "https://wiprodigital.com/who-we-are#wdteam_leaders",
		            "https://wiprodigital.com/who-we-are#wdteam_meetus",
		            "https://wiprodigital.com/what-we-do#wdwork_partners"
		    );
		}
	}
}
