package gameslist;

import java.io.IOException;

public interface ScraperInterface {
	
	StringBuffer getResponse(String pUrl, String path) throws IOException;
	StringBuffer getResponse(String pUrl) throws IOException;
	
}
