package gameslist;

import java.util.Comparator;

public class Item {
	
	private String path;
	private String name;
	private String image;
	private double rating;
	private String releaseDate;
	private String developer;
	private String publisher;
	private String genre;
	private String players;
	private int playCount;
	private boolean deleted;
	private boolean hidden;
	private String desc;
	private String emulator;
	private String core;
	private String ratio;
	private static boolean reallyDel;
	
	Item() {
		path = "";
		name = "";
		image = "";
		rating = 0.0;
		releaseDate = "19950101T000000";
		developer  = "";
		publisher = "";
		genre = "";
		players = "1";
		playCount = 0;
		deleted = false;
		hidden = false;
		desc = "";
		emulator = "";
		core = "";
		ratio = "";
		
	}
	
	String getPath() {
		return path;
	}
	void setPath(String val) {
		if (val != null)
			path = val;
		else
			path = "";
		
	}
	
	String getName() {
		return name;
	}
	void setName(String val) {
		if (val != null)
			name = val;
		else
			name = "";
	}
	
	String getImage() {
		return image;
	}
	void setImage(String val) {
		if (val != null)
			image = val;
		else
			image = "";
	}
	
	double getRating() {
		return rating;
	}
	void setRating(double val) {
		if ((Double) val != null)
			rating = val;
		else
			rating = 0.0;
	}
	
	String getDate() {
		return releaseDate;
	}
	void setDate(String val) {
		if (val != null)
			releaseDate = val;
		else
			releaseDate = "19950101T000000";
	}
	
	String getDeveloper() {
		return developer;
	}
	void setDeveloper(String val) {
		if (val != null)
			developer = val;
		else
			developer  = "";
	}
	
	String getPublisher() {
		return publisher;
	}
	void setPublisher(String val) {
		if (val != null)
			publisher = val;
		else
			publisher = "";
	}
	
	String getGenre() {
		return genre;
	}
	void setGenre(String val) {
		if (val != null)
			genre = val;
		else
			genre = "";
	}
	
	String getPlayers() {
		return players;
	}
	void setPlayers(String val) {
		if (val != null)
			players = val;
		else
			players = "0";
	}
	
	boolean getDeleted() {
		return deleted;
	}
	void setDeleted(String val) {
		if (val != null) {
			if (val.equals("true"))
				deleted = true;
			else
				deleted = false;
		} else
			deleted = false;
	}
	
	boolean getHidden() {
		return hidden;
	}
	void setHidden(String val) {
		if (val != null) {
			if (val.equals("true"))
				hidden = true;
			else
				hidden = false;
		} else
			hidden = false;
	}
	
	int getPlayCount() {
		return playCount;
	}
	void setPlayCount(int val) {
		if ((Integer) val != null)
			playCount = val;
		else
			playCount = 0;
	}
	
	String getDesc() {
		return desc;
	}
	void setDesc(String val) {
		if (val != null)
			desc = val;
		else
			desc = "";
	}
	
	String getEmulator() {
		return emulator;
	}
	void setEmulator(String val) {
		if (val != null)
			emulator = val;
		else
			emulator = "";
	}
	
	String getCore() {
		return core;
	}
	void setCore(String val) {
		if (val != null)
			core = val;
		else
			core = "";
	}
	
	String getRatio() {
		return ratio;
	}
	void setRatio(String val) {
		if (val != null)
			ratio = val;
		else
			ratio = "";
	}
	
	static boolean getReallyDel() {
		return reallyDel;
	}
	static void setReallyDel(String val) {
		if (val != null) {
			if (val.equals("true"))
				reallyDel = true;
			else
				reallyDel = false;
		} else
			reallyDel = false;
	}
	
	public static Comparator<Item> nameComparator = new Comparator<Item>() {

		@Override
		public int compare(Item o1, Item o2) {
			
			String name1 = o1.getName().toUpperCase();
			String name2 = o2.getName().toUpperCase();
			
			// TODO Auto-generated method stub
			return name1.compareTo(name2);
		}
		
	};
}
