package gameslist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import org.apache.commons.text.StringEscapeUtils;

public class XMLWriter {

	private ArrayList<Item> gameList;
	private String originalPath = XMLReader.romPath;
	XMLWriter(ArrayList<Item> pList) {
			gameList = pList;
	}
	/**
	 * Creates a copy of the current gamelist file
	 * So if a problem occurs a previous working state can be restored
	 */
	void copyFile() {
		Path original = Paths.get(originalPath + "\\gamelist.xml");
		Path copied = Paths.get(originalPath + "\\gamelist-old.xml");
		
		try {
			Files.copy(original, copied, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Handles the writing to file
	 */
	void writeToFile() {
		
		
		try {
			//Creates an output stream to the file
			OutputStream os = new FileOutputStream(originalPath + "\\gamelist.xml");
			//Creates a writer on the stream
			Writer wos = new OutputStreamWriter(os, "UTF-8");
			
			//Appends the standard xml syntax and starting tag
			wos.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
			wos.append("<gameList>\n");
			
			//Loop through each item in theh list
			for (Item itm : gameList) {
				//Make sure the item is not set for deletion or complete deletion
				if (!itm.getDeleted() || !Item.getReallyDel()) {
					//Add the required tags with the required details and formatting
					wos.append("\t<game>\n");
					wos.append("\t\t<path>" + formatPath(StringEscapeUtils.escapeHtml4(itm.getPath())) + "</path>\n");
					wos.append("\t\t<name>" + StringEscapeUtils.escapeHtml4(itm.getName()) + "</name>\n");
					wos.append("\t\t<rating>" + itm.getRating() + "</rating>\n");
					wos.append("\t\t<desc>" + StringEscapeUtils.escapeHtml4(itm.getDesc()) + "</desc>\n");
					wos.append("\t\t<image>" + formatPath(itm.getImage()) + "</image>\n");
					wos.append("\t\t<releasedate>" + StringEscapeUtils.escapeHtml4(itm.getDate()) + "</releasedate>\n");
					wos.append("\t\t<developer>" + StringEscapeUtils.escapeHtml4(itm.getDeveloper()) + "</developer>\n");
					wos.append("\t\t<publisher>" + StringEscapeUtils.escapeHtml4(itm.getPublisher()) + "</publisher>\n");
					wos.append("\t\t<genre>" + StringEscapeUtils.escapeHtml4(itm.getGenre()) + "</genre>\n");
					wos.append("\t\t<players>" + itm.getPlayers() + "</players>\n");
					//Only append the ratio if there is one to append
					if (!itm.getRatio().equals(""))
						wos.append("\t\t<ratio>" + itm.getRatio() + "</ratio>\n");
					else 
						//Otherwise, if there is an image check the width and height
						//And append a relevant ratio
						if (!itm.getImage().equals("")) {
							ImageIcon ii = new ImageIcon(itm.getImage());
							if (ii.getIconWidth() < ii.getIconHeight())
								wos.append("\t\t<ratio>3/4</ratio>\n");
						}
					
					//The remaining are only appended if needed
					if (!itm.getEmulator().equals(""))
						wos.append("\t\t<emulator>" + itm.getEmulator() + "</emulator>\n");
					
					if (!itm.getCore().equals(""))
						wos.append("\t\t<core>" + itm.getCore() + "</core>\n");
					
					if (itm.getHidden())
						wos.append("\t\t<hidden>true</hidden>\n");
					
					if (itm.getDeleted() && !Item.getReallyDel())
						wos.append("\t\t<delete>true</delete>\n");
					
					//Append the closing tag for a single item
					wos.append("\t</game>\n");
					
				}
				//If the item is set for complete deletion do this
				if (itm.getDeleted() && Item.getReallyDel()) {
					//Directory for deleted roms
					String dir = originalPath + "\\Deleted";
					
					Path p = Paths.get(dir);
					//If it does not exist then create the directory
					if (Files.notExists(p)) {
						new File(p.toString()).mkdirs();
					}
					//Get item path but remove the "./"
					String tmpItmPath = itm.getPath().replace("./", "");
					//Remove the original path part from the item path
					tmpItmPath = tmpItmPath.replace(originalPath, "");
					//Checks to see if the rom exists
					if (!XMLReader.getRomExists().contains(tmpItmPath)) {
						@SuppressWarnings("unused")
						//Move the file from the original location to the new "Deleted" file
						Path temp = Files.move(Paths.get(originalPath + "\\" + tmpItmPath), Paths.get(dir + "\\" + tmpItmPath));
					}
					
				}
			}
			//Append the end tag
			wos.append("</gameList>");
			wos.close();
		} catch (IOException e) {
		}
	}
	
	/**
	 * Formats a url
	 * @param wPath
	 * @return
	 */
	String formatPath(String wPath) {
		
		if (!wPath.isBlank()) {
		
			String lPath = "./";
			
			if (wPath.contains(originalPath))
				lPath += wPath.replace(originalPath, "");
			else 
				return wPath;
			
			lPath = lPath.replace("\\", "/");
			
			return lPath;
		}
		
		return "";
	}
	
}
