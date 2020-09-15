package gameslist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import org.json.*;

public class Scraper implements ScraperInterface {

	private String sysPath = "\\json\\";
	private StringBuffer response;
	private Map<Integer, String> sysMap;
	private Map<Integer, String> genMap;
	private Map<Integer, String> devMap;
	private Map<Integer, String> pubMap;
	
	private Item game = new Item();
	
	Scraper() {
		
		try {
			//Gets the file location to edit
			File f = new File(System.getProperty("java.class.path"));
			File dir = f.getAbsoluteFile().getParentFile();
			String path = dir.toString();
			
			sysPath = path + sysPath;
			//Get responce for platforms
			StringBuffer response = getResponse("https://api.thegamesdb.net/Platforms?apikey=c55db28a573560d531b59593e0f1a9b8794462c3764424acc9661bf8672df4f8", sysPath + "Systemsjson.txt");
			parseJSONSystems(response);
			
			//Get responce for genres
			response = getResponse("https://api.thegamesdb.net/Genres?apikey=c55db28a573560d531b59593e0f1a9b8794462c3764424acc9661bf8672df4f8", sysPath + "Genresjson.txt");
			parseJSONGenres(response);
			
			//Get responce for developers
			response = getResponse("https://api.thegamesdb.net/Developers?apikey=c55db28a573560d531b59593e0f1a9b8794462c3764424acc9661bf8672df4f8", sysPath + "Devsjson.txt");
			parseJSONDevs(response);
			
			//Get response for publishers
			response = getResponse("https://api.thegamesdb.net/Publishers?apikey=c55db28a573560d531b59593e0f1a9b8794462c3764424acc9661bf8672df4f8", sysPath + "Pubsjson.txt");
			parseJSONPubs(response);
			
		} catch (IOException e) {
			PrintWriter test = null;
			try {
				//Write error to log file
				test = new PrintWriter("C:\\Users\\Matt\\Desktop\\LOG.txt", "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				
			}
			// TODO Auto-generated catch block
			test.print(e.toString());
		}
	}
	
	Map<Integer, String> getSys() {
		return sysMap;
	}
	
	void scrapeGame(Item pGame, String sys) {
		game = pGame;
		
		//Loops through each element in the map
		sysMap.forEach((k, v) -> {
			if (v.equals(sys)) {
				try {
					String name = game.getName();
					for (int i = 0; i < name.length(); i++) {
						//Removes the remaining string after a bracket is found
						if (name.charAt(i) == '(') {
							name = name.substring(0, i);
							break;
						}
					}
					//Gets responce for a specific game
					StringBuffer response = getResponse("https://api.thegamesdb.net/Games/ByGameName?apikey=c55db28a573560d531b59593e0f1a9b8794462c3764424acc9661bf8672df4f8&name=" + URLEncoder.encode(name, "UTF-8") + "&filter%5Bplatform%5D=" + k);
					parseJSONGame(response);
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	public StringBuffer getResponse(String pUrl) throws IOException {
		String url = pUrl;
		
		//Opens a connection to the required url
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("Content-Type", "application/json");

		@SuppressWarnings("unused")
		int responseCode = con.getResponseCode();

		//Creates a reader on the connection
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		response = new StringBuffer();
		
		//Reads the responce into a string buffer
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response;
	}
	
	public StringBuffer getResponse(String pUrl, String path) throws IOException {
		
		//Check that  the path leads to a file
		File f = new File (path);
		//If the file does NOT exist, then write to it
		//Otherwise, read from it
		if (!f.exists()) {

			String url = pUrl;

			//Opens a connection to the passed url
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("Content-Type", "application/json");

			@SuppressWarnings("unused")
			int responseCode = con.getResponseCode();

			//Create a reader on the connection
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuffer();
			
			//Read the responce into a string buffer
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//Write the responce to a file using a buffered writer
			BufferedWriter bw= new BufferedWriter(new FileWriter(path));

			bw.write(response.toString());
			bw.flush();
			bw.close();

			con.disconnect();

		} else {
			//Create a reader on the file
			BufferedReader in = new BufferedReader(new FileReader(path));
			String inputLine;
			response = new StringBuffer();

			//Write the contents of the file into a string buffer
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		}
		return response;

	}
	
	/**
	 * Handle the mapping of the systems
	 * @param json
	 */
	void parseJSONSystems(StringBuffer json) {
		
		//Create JSON Objects from the string buffer
		JSONObject obj = new JSONObject(json.toString());
		//Move to the data
		JSONObject data = obj.getJSONObject("data");
		
		//Move to the platforms
		JSONObject platforms = data.getJSONObject("platforms");
		
		//Initialize the map
		sysMap = new HashMap<>();
		
		//Create an iterator on the keys of the platforms
		Iterator<String> keys = platforms.keys();
		//Create a log writer
		PrintWriter test = null;
		try {
			test = new PrintWriter("C:\\Users\\Matt\\Desktop\\LOG.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Loops while there are remaining keys
		while (keys.hasNext()) {
			//Gets the next key
			String key = keys.next();
			//Checks it is a JSON Object
			if (platforms.get(key) instanceof JSONObject) {
				//Move to the itm
				JSONObject itm = platforms.getJSONObject(key);
				//Get the id and name from the item
				int id = itm.getInt("id");
				String name = itm.getString("name");
				test.print("System ID - " + id + " System Name - "+ name + "\n");
				//Add it to the map
				sysMap.put(id, name);
			}
		}
		test.close();
	}
	
	/**
	 * Handle the mapping of the genres
	 * @param json
	 */
	void parseJSONGenres(StringBuffer json) {
		
		//Create JSON Objects from the string buffer
		JSONObject obj = new JSONObject(json.toString());
		//Move to the data		
		JSONObject data = obj.getJSONObject("data");
		//Move to the genres
		JSONObject genres = data.getJSONObject("genres");
		
		//Initialize the map
		genMap = new HashMap<>();
		
		//Create an iterator on the keys
		Iterator<String> keys = genres.keys();
		
		//Loops while there is a remaining key
		while (keys.hasNext()) {
			//Get the next key
			String key = keys.next();
			//Checks it is an instance of JSON Object
			if (genres.get(key) instanceof JSONObject) {
				//Move to the item
				JSONObject itm = genres.getJSONObject(key);
				
				//Get the id and name from the item
				int id = itm.getInt("id");
				String name = itm.getString("name");
				
				//Add to  the map
				genMap.put(id, name);
			}
		}
	}
	
	/**
	 * Handle the mapping of the developers
	 * @param json
	 */
	void parseJSONDevs(StringBuffer json) {
		
		//Create JSON Objects from the string buffer
		JSONObject obj = new JSONObject(json.toString());
		//Move to the data
		JSONObject data = obj.getJSONObject("data");
		//Move to the developers
		JSONObject devs = data.getJSONObject("developers");
		
		//Create an iterator on the keys
		Iterator<String> keys = devs.keys();
		
		//Initialize the map
		Map<Integer, String> devMap = new HashMap<>();
		
		//Loops while there are remaining keys
		while (keys.hasNext()) {
			//Gets the next key
			String key = keys.next();
			//Checks it is an instance of the JSON Object
			if (devs.get(key) instanceof JSONObject) {
				//Move to the item
				JSONObject itm = devs.getJSONObject(key);
				
				//Get the id and name from the item
				int id = itm.getInt("id");
				String name = itm.getString("name");
				
				//Add to the map
				devMap.put(id, name);
			}
		}
	}
	
	//Handle the mapping of the publishers
	void parseJSONPubs(StringBuffer json) {
		
		//Create JSON Objects from the string buffer
		JSONObject obj = new JSONObject(json.toString());
		//Move to the data
		JSONObject data = obj.getJSONObject("data");
		//Move to the publishers
		JSONObject pubs = data.getJSONObject("publishers");
		
		//Initialize the map
		Map<Integer, String> pubMap = new HashMap<>();
		
		//Create an iterator on the keys
		Iterator<String> keys = pubs.keys();
		
		//Loops while there are remaining keys
		while (keys.hasNext()) {
			//Gets the next key
			String key = keys.next();
			//Checks it is an instance of JSON Object
			if (pubs.get(key) instanceof JSONObject) {
				//Move to the item
				JSONObject itm = pubs.getJSONObject(key);
				
				//Get the id and name from the item
				int id = itm.getInt("id");
				String name = itm.getString("name");
				
				//Add to the map
				pubMap.put(id, name);
			}
		}
	}
	
	//Handle the parsing for a game
	void parseJSONGame(StringBuffer json) {
		//Create JSON Objects from the string buffer
		JSONObject obj = new JSONObject(json.toString());
		//Move to the data
		JSONObject data = obj.getJSONObject("data");
		//Move to the games
		JSONArray games = data.getJSONArray("games");
		//Set a starting id
		int id = 0;
		
		//Check that games exists
		if (!games.isEmpty()) {
			//Move to an first game
			JSONObject g = games.getJSONObject(0);
			//Get all of the required details if they exist
			if (g.has("id"))
				id = g.getInt("id");
			if (g.has("game_title"))
				game.setName(g.getString("game_title"));
			if (g.has("release_date")) {
				game.setDate(g.getString("release_date"));
			}
			if (g.has("developers")) {
				int devIdx = g.getJSONArray("developers").getInt(0);
				game.setDeveloper(devMap.get(devIdx));
			}
			if (g.has("genres")) {
				int genIdx = g.getJSONArray("genres").getInt(0);
				game.setGenre(genMap.get(genIdx));
			}
			if (g.has("publishers")) {
				int pubIdx = g.getJSONArray("publishers").getInt(0);
				game.setPublisher(pubMap.get(pubIdx));
			}
			if (g.has("overview"))
				game.setDesc(g.getString("overview"));
		}	
		
		//Scrape the image using the id
		scrapeImage(id);
		
	}
	
	
	void scrapeImage(int id) {
		StringBuffer response;
		try {
			//Gets responce for an image
			response = getResponse("https://api.thegamesdb.net/Games/Images?apikey=c55db28a573560d531b59593e0f1a9b8794462c3764424acc9661bf8672df4f8&games_id=" + id);
			//Parse the responce
			parseJSONImage(response, id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Handles parsing for an image
	 * @param json
	 * @param id
	 */
	void parseJSONImage(StringBuffer json, int id) {
		String path = "";
		
		//Create JSON Objects from the string buffer
		JSONObject obj = new JSONObject(json.toString());
		//Move to the data
		JSONObject data = obj.getJSONObject("data");
		//Move to base url
		JSONObject baseURL = data.getJSONObject("base_url");
		
		//Get either the original, large or medium image path depending on what is available
		if (baseURL.has("original"))
			path += baseURL.getString("original");
		else if (baseURL.has("large"))
			path += baseURL.getString("large");
		else if (baseURL.has("medium"))
			path += baseURL.getString("medium");
		
		//Move to images
		JSONObject images = data.getJSONObject("images");
		//Create JSON Array using the passed in id
		JSONArray imageArr = images.getJSONArray(Integer.toString(id));
		
		//Loop through the array
		for (int i = 0; i < imageArr.length(); i++) {
			//Move to a single image
			JSONObject image = imageArr.getJSONObject(i);
			//Check if there is a screenshot,
			//If so, get it
			if (image.has("type") && image.getString("type").equals("screenshot"))
				path += image.getString("filename");
		}
		//If a path was found then set it as the image for the game
		if (!path.isEmpty())
			game.setImage(path);
	}
	
	/**
	 * Return the game
	 * @return
	 */
	Item returnGame() {
		return game;
	}
	
}
