package webcrawler;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import webcrawler.link.Link;



public class Crawler {
	
	private final long MAXTIME = -1;
	private final int MAXCLICKS = -1;
	private final int MAXLINKS = -1;
	private int clickCount = 1;
	private int linkCount = 1;
	private Date start = new Date();
	protected final String baseURL;
	protected HashSet<Link> searchHead = new HashSet<Link>();
	protected HashSet<Link> newSearchHead = new HashSet<Link>();
	protected HashSet<Link> history = new HashSet<Link>();
	protected Link startingLink;

	
	public Crawler(String baseURL)
	{
		this.baseURL = baseURL;
	}
	
	public void crawl()
	{
		this.clickCount = 1;
		this.linkCount = 1;
		this.start = new Date();
		
		searchHead.add(startingLink);

		
		while(true)
		{
			for(Link l : searchHead)
			{
				history.add(l);
				ArrayList<Link> temp = l.getAllLinks();
				for(Link t : temp )
				{
					this.parseLink(t);
					if(!this.stillSearching(clickCount,linkCount,start))
						break;
				}
				if(!this.stillSearching(clickCount,linkCount,start))
					break;
				linkCount++;
			}
			if(!this.stillSearching(clickCount,linkCount,start))
				break;
			clickCount++;
			searchHead.clear();
			searchHead.addAll(newSearchHead);
			newSearchHead.clear();
			System.out.println("NEXT CLICK: " + clickCount);
			System.out.println("SEARCH HEAD SIZE: " + searchHead.size());
			System.out.println("EXPLORED PAGES COUNT: " + linkCount);
		}
	}
	
	protected boolean stillSearching(int clickCount, int linkCount,Date start) {
		return !(timedOut(start) || clickedOut(clickCount) || linkedOut(linkCount));
	}

	private boolean clickedOut(int clickCount) {
		if(this.MAXCLICKS > 0 && clickCount >= this.MAXCLICKS) 
			return true;
		return false;
	}
	private boolean linkedOut(int linkCount) {
		if(this.MAXLINKS > 0 && linkCount >= this.MAXLINKS) 
			return true;
		return false;
	}

	private boolean timedOut(Date start) {
		if (this.MAXTIME > 0 && System.currentTimeMillis() - start.getTime() >= this.MAXTIME)
			return true;
		return false;
	}

	public void parseLink(Link l) {
		if(!searchHead.contains(l) && !newSearchHead.contains(l) && !history.contains(l))
		{
			System.out.println(l.toString());
			this.newSearchHead.add(l);
		}
	}

	public String reportResults() {
		String retVal = "";
		long difference = System.currentTimeMillis() - start.getTime();
		retVal+="TOOK " + difference + " MILLISECONDS!!";
		retVal+="\nMADE " + clickCount + " CLICKS!!";
		retVal+="\nDOWNLOADED " + linkCount + " DIFFERENT PAGES!!";
		return retVal;
	}
	
	
}
