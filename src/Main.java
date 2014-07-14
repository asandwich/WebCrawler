
import webcrawler.Crawler;
import webcrawler.WikiCrawler;



public class Main {
	
	public static void main(String[] args)
	{
		String wikiURL = "http://en.wikipedia.org/wiki/Rubeus_Hagrid";
		String endingTitle = "Twinkie";
		if(args.length == 1)
			wikiURL = args[0];
		else if(args.length == 2)
		{
			wikiURL = args[0];
			endingTitle = args[1];
		}
		wikiCrawl(wikiURL,endingTitle);
		//String wendyURL = "http://www-dev.wendys.com";
/*
		Crawler c;
		c = new WikiCrawler(wikiURL,endingTitle);		
		c = new WendysCrawler(wendyURL);
		
		
		c.crawl();
		*/
	}
	
	
	public static void wikiCrawl(String wikiURL, String endingTitle) {
		Crawler c;
		c = new WikiCrawler(wikiURL,endingTitle);
		c.crawl();
		System.out.println(c.reportResults());	
	}
}
