package gameslist;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class XMLReader implements XMLReaderInterface{
	
	static String romPath;
	private String fileName;
	private static String filePath;
	private int idx = 0;
	private ArrayList<Item> gameList = new ArrayList<Item>();
	private static ArrayList<String> nonExistentRoms = new ArrayList<String>();
	
	XMLReader(String pFileName, String pFilePath) {
		//Set name and paths
		fileName = pFileName;
		filePath = pFilePath;
		romPath = filePath;
		//Get the xml file
		File xmlFile = new File(fileName);
		
		//Create a document builder and factory
		DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			//Get new doc builder
			dBuilder = dbFac.newDocumentBuilder();
			//Parse the xml file
			Document doc = dBuilder.parse(xmlFile);
			//Normalize
			doc.getDocumentElement().normalize();
			//Create a node list from the doc
			NodeList nodelist = doc.getElementsByTagName("game");
			/*
			 * XML is loaded into memory
			 * 
			 */
			for (int i = 0; i < nodelist.getLength(); i++) {
				//Adds the item to the list
				gameList.add(getItem(nodelist.item(i)));
				//Check to see if the rom exists
				boolean romExists = checkRomExists(nodelist.item(i));
				
				//If it does not
				//Set it as deleted
				if (!romExists) {
					gameList.get(idx).setDeleted("true");
				}
				//Increase index
				next();
			}
			
			checkNodeExists(gameList);
			//Reset the index
			idx = 0;
			//Sort the list
			Collections.sort(gameList, Item.nameComparator);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
	}
	
	private void checkNodeExists(ArrayList<Item> list) {
		File romFolder = new File(romPath);
		File[] romList = romFolder.listFiles();
		String[] ext = new String[] {"xml", "txt"};
		boolean found = false;
		
		for (int i = 0; i < romList.length; i++) {
			for (Item itm : list) {
					if (romList[i].getName().contains(FilenameUtils.removeExtension(itm.getPath().replace("./", "")))) {
						found = true;
						break;
					}
			}
			if (romList[i].isFile() && !Arrays.asList(ext).contains(FilenameUtils.getExtension(romList[i].getAbsolutePath()))) {
				if (!found) {
					Item itm = new Item();
					itm.setPath(romList[i].getPath());
					itm.setName(romList[i].getName());
					
					gameList.add(itm);
				}
				found = false;
			}
		}
	}
	
	/**
	 * Get the list
	 * @return
	 */
	ArrayList<Item> getArray() {
		return gameList;
	}
	
	private boolean checkRomExists(Node node) {
		File romFolder = new File(romPath);
		File[] romList = romFolder.listFiles();
		
		NodeList nList = node.getChildNodes();
		String path = "";
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node n = nList.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				switch (n.getNodeName()) {
				case "path":
					path = n.getTextContent();
					break;
				}
			}
		}
		
		if (path.substring(0, 2).equals("./"))
			path = path.replace("./", "");
			
		
		for (int i = 0; i < romList.length; i++)
				if (path.equals(romList[i].getName()))
					return true;
		
		nonExistentRoms.add(path);
		return false;
		
	}
	
	private static Item getItem(Node node) {
		Item itm = new Item();
		//Get child nodes
		NodeList nList = node.getChildNodes();
		//Loop through child nodes
		for (int i = 0; i < nList.getLength(); i++) {
			//Get a single child node
	        Node n = nList.item(i);
	        //Check it is an element node
	        if (n.getNodeType() == Node.ELEMENT_NODE) {
	        	//Switch on the node name
	        	//Set the relevant attribute of the item
	            switch (n.getNodeName()) {
	            case "path":
	            	itm.setPath(n.getTextContent());
	            	break;
	            case "name":
	            	
	            	itm.setName(n.getTextContent());
	            	break;
	            case "image":
	            	String temp = n.getTextContent();
	            	//Format the path if there is one
	            	if (!temp.isBlank()) {
	            		//Remove the first char if it is a "."
		            	if (temp.substring(0, 1).equals(".")) {
		            		temp = temp.substring(1);
		            	}
		            	//If the path does not contain the file path
		            	//Then, add it to  the start of the path
		            	if (!temp.contains(filePath)) {
			            	String imgFileName = Paths.get(filePath + temp).toString();
			            	itm.setImage(imgFileName);
			            //Otherwise, just set to the parsed path
		            	} else {
		            		itm.setImage(temp);
		            	}
	            	} else {
	            		itm.setImage(null);
	            	}
		            	
	            	
	            	break;
	            case "rating":
	            	if (n.getTextContent().isBlank()) {
	            		itm.setRating(0.0); 
	            	} else 
	            		itm.setRating(Double.parseDouble(n.getTextContent()));
	            	break;
	            case "releasedate":
	            	itm.setDate(n.getTextContent());
	            	break;
	            case "developer":
	            	itm.setDeveloper(n.getTextContent());
	            	break;
	            case "publisher":
	            	itm.setPublisher(n.getTextContent());
	            	break;
	            case "genre":
	            	itm.setGenre(n.getTextContent());
	            	break;
	            case "players":
	            		itm.setPlayers(n.getTextContent());
	            	break;
	            case "delete":
	            	itm.setDeleted(n.getTextContent());
	            	break;
	            case "hidden":
	            	itm.setHidden(n.getTextContent());
	            	break;
	            case "playcount":
	            	if (n.getTextContent().isBlank())
	            		itm.setPlayCount(0);
	            	else
	            		itm.setPlayCount(Integer.parseInt(n.getTextContent()));
	            	break;
	            case "emulator":
	            	itm.setEmulator(n.getTextContent());
	            	break;
	            case "core":
	            	itm.setCore(n.getTextContent());
	            	break;
	            case "ratio":
	            	itm.setRatio(n.getTextContent());
	            case "desc":
	            	itm.setDesc(n.getTextContent());
	            }
	        }
		}
        return itm;
    }
	
	public Item getItem() {
		
		return gameList.get(idx);
	}
	
	public Item getItem(int pIdx) {
		idx = pIdx;
		return gameList.get(idx);
	}
	
	void setItem(Item val) {
		gameList.set(idx, val);
	}
	
	void next() {
		if (idx != gameList.size() - 1)
			idx++;
	}
	
	void prev() {
		if (idx != 0)
			idx--;
	}
	
	
	void setIdx(int val) {
		idx = val;
	}
	int getIdx() {
		return idx;
	}
	static ArrayList<String> getRomExists() {
		return nonExistentRoms;
	}
	
}
