package webcrawler.link;

import org.jsoup.nodes.Element;

public class WendysLink extends Link{

	public WendysLink(Element e, String history) {
		super(e, history);
		if(!e.attr("href").contains("www"))
			this._link = "http://www-dev.wendys.com"+ e.attr("href");
		this._history = history + "->" + this._link;

	}
	

	public WendysLink(String startingURL, String title,String history) {
		super(startingURL, title, history);
	}


	@Override
	protected boolean filterLink(String linkHref)
	{
		if(!linkHref.contains("#")
				&& !linkHref.contains("tel:(")
				&& !linkHref.contains("www.aboutwendys.com"))
			return false;
		return true;
	}
	@Override 
	protected Link getNewInstance(Element e, String newHistory)
	{
		return new WendysLink(e,newHistory);
	}

}
