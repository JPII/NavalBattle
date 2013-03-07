package com.jpii.navalbattle.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;

public class BroadcastService {
	
	private String announcementId;
	private BroadcastThread broadcastThread;
	
	/* Items from update.xml */
	private String versionCode, versionReadable, updateUrl;
	private String announcementCode, announcementTitle, announcementText, announcementUrl;
	
	public BroadcastService() {
		announcementId = NavalBattleIO.getAttribute("announcementId");
		broadcastThread = new BroadcastThread();
		
		NavalBattle.getDebugWindow().printInfo("Checking for announcements...");
		broadcastThread.run();
	}
	
	public String getAnnouncementId() {
		return announcementId;
	}
	
	public String getVersionCode() {
		return Constants.VERSION_CODE;
	}
	
	class BroadcastThread extends Thread {
		
		public BroadcastThread() {

		}

		@Override
		public void run() {
			parseXml();
			
			checkForUpdates();
			checkForAnnouncement();
		}
		
		private void parseXml() {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = null;
			
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			
			
			Document doc = null;
			try {
				doc = db.parse(new URL(Constants.NAVALBATTLE_UPDATE_URL).openStream());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Element rootElement = doc.getDocumentElement();
			NodeList stringNodes = rootElement.getChildNodes();

			for(int i = 0; i < stringNodes.getLength(); i++){
				Node defaultStringNode = stringNodes.item(i);

				if(defaultStringNode instanceof Element) {
					Element stringChild = (Element) stringNodes;
					System.out.println(stringChild.getAttribute("version_code"));
					System.out.println(stringChild.getChildNodes().item(0).getNodeValue());
					System.out.println("DOMNE");
					//stringNodes.put(defaultStringChild.getAttribute("name"), defaultStringChild.getChildNodes().item(0).getNodeValue());
				}
			}
		}
		
		private void checkForUpdates() {
			
		}
		
		private void checkForAnnouncement() {
			
		}
	}
}
