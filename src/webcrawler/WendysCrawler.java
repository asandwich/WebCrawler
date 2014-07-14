package webcrawler;
import webcrawler.link.Link;
import webcrawler.link.WendysLink;

public class WendysCrawler extends Crawler{
	public WendysCrawler(String baseURL) {
		super(baseURL);
		this.startingLink = new WendysLink(this.baseURL,this.baseURL,this.baseURL);
	}

	@Override
	public void parseLink(Link l)
	{
		if(l.getHistory().contains("www.wendys.com"))
		{
			this.searchHead.remove(l);
			System.out.println(l.toString());
		}
		else
		{
			super.parseLink(l);
		}
	}
	
}
