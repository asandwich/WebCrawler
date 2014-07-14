package webcrawler.link;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Link {
	protected String _link = "";
	private String _title = "";
	protected String _history = "";
	private ArrayList<Link> _filteredLinks;
	
	public Link (String link, String title, String history)
	{
		this._filteredLinks = new ArrayList<Link>();
		this._link = link;
		this._title = title;
		this._history = history;
	}
	
	public Link(Element e, String history) {
		this._filteredLinks = new ArrayList<Link>();
		this._link = e.attr("href");
		this._title = e.text();
		this._history = history;
	}
	public String getTitle() 
	{
		return this._title;
	}
	public String getLink() 
	{
		return this._link;
	}
	public String toString()
	{
		String retVal = "\n";
		retVal += "\tLink:\t" + this._link;
		retVal += "\n\tTitle:\t" + this._title;
		retVal += "\n\tHistory:" + this._history;
		return retVal;
	}

	public String getHistory() {
		return this._history;
	}
	
	public ArrayList<Link> getFilteredLinks()
	{
		return this._filteredLinks;
	}
	
	
	protected boolean filterLink(String linkHref)
	{
		return true;
	}

	
	public ArrayList<Link> getAllLinks() 
	{
		ArrayList<Link> retVal = new ArrayList<Link>();
		Document doc = new Document("");
		try {
			doc = Jsoup.connect(this._link).get();
			Elements allLinks = doc.select("a[href]");
//			Element content = doc.getElementById("container");
//			Element content = doc.getElementById("content");
//			Elements allLinks = content.getElementsByTag("a");
					
			for (Element e : allLinks) {
				String linkHref = e.attr("href");
				Link l = this.getNewInstance(e,this._history);
				if(filterLink(linkHref))
				{
					this._filteredLinks.add(l);
				}
				else
				{
					retVal.add(l);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(doc.toString());
		}
		return retVal;
	}

	protected Link getNewInstance(Element e, String newHistory)
	{
		return new Link(e,newHistory);
	}
	
	@Override 
	public boolean equals(Object obj)
	{
		if(obj instanceof Link)
	        if(((Link) obj).getLink().equals(this._link))
	        	return true;
		return false;
		
	}
}
