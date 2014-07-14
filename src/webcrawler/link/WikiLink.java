package webcrawler.link;

import org.jsoup.nodes.Element;

public class WikiLink extends Link {

	public WikiLink(Element e, String history) {
		super(e, history);
		if(!e.attr("href").contains("www"))
			this._link = "http://en.wikipedia.org"+ e.attr("href");
		this._history = history + "->" + e.attr("href");

	}

	public WikiLink(String startingURL, String title, String history) {
		super(startingURL, title, history);
	}

	@Override
	protected boolean filterLink(String linkHref)
	{
		if(!linkHref.contains("#")
				&& linkHref.contains("/wiki/")
				&& !linkHref.contains("wikipedia")
				&& !linkHref.contains(".org")
				&& !linkHref.contains("File:")
				&& !linkHref.contains("Special pages")
				&& !linkHref.contains("Disclaimers")
				&& !linkHref.contains("About Wikipedia"))
			return false;
		return true;
	}
	
	@Override 
	protected Link getNewInstance(Element e, String newHistory)
	{
		return new WikiLink(e,newHistory);
	}
}
