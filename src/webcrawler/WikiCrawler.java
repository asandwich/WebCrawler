package webcrawler;

import java.util.Date;

import webcrawler.link.Link;
import webcrawler.link.WikiLink;

public class WikiCrawler extends Crawler {
	private String endingTitle = "";
	private Link lastLink = null;
	private boolean found;
	
	public WikiCrawler(String baseURL,String endingTitle) {
		super(baseURL);
		this.endingTitle = endingTitle;
		this.startingLink = new WikiLink(this.baseURL,this.baseURL,this.baseURL);
	}
	
	@Override
	public void parseLink(Link l)
	{
		if(l.getTitle().contains(this.endingTitle))
		{
			this.found = true;
			this.lastLink = l;
		}
		else
		{
			super.parseLink(l);
		}
	}
	
	@Override 
	protected boolean stillSearching(int clickCount, int linkCount,Date start) 
	{
		return super.stillSearching(clickCount, linkCount, start) && !this.found;
	}

	public String reportResults() {
		String retVal = super.reportResults();
		retVal+="\n" + this.lastLink.toString();
		return retVal;
	}
	 
}
