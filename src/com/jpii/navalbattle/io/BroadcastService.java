package com.jpii.navalbattle.io;

import java.io.InputStream;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;

public class BroadcastService {
	
	/* Items from client */
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
	
	public String getVersionCode() {
		return versionCode;
	}
	
	public String getVersionReadable() {
		return versionReadable;
	}
	
	public String getUpdateUrl() {
		return updateUrl;
	}
	
	public String getAnnouncementCode() {
		return announcementCode;
	}
	
	public String getAnnouncementTitle() {
		return announcementTitle;
	}
	
	public String getAnnouncementText() {
		return announcementText;
	}
	
	public String getAnnouncementUrl() {
		return announcementUrl;
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
			try {
				InputStream is = new URL(Constants.NAVALBATTLE_UPDATE_URL).openStream();
				XMLInputFactory factory = XMLInputFactory.newInstance();
				XMLStreamReader reader = factory.createXMLStreamReader(is);
				
				while(reader.hasNext()) {
					if(reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
						if(reader.getLocalName().equals("string")) {
							if(reader.getAttributeValue(0).equals("versionCode"))
								versionCode = reader.getElementText();
							
							if(reader.getAttributeValue(0).equals("versionReadable"))
								versionReadable = reader.getElementText();
							
							if(reader.getAttributeValue(0).equals("updateUrl"))
								updateUrl = reader.getElementText();
							
							if(reader.getAttributeValue(0).equals("announcementCode"))
								announcementCode = reader.getElementText();
							
							if(reader.getAttributeValue(0).equals("announcementTitle"))
								announcementTitle = reader.getElementText();
							
							if(reader.getAttributeValue(0).equals("announcementText"))
								announcementText = reader.getElementText();
							
							if(reader.getAttributeValue(0).equals("announcementUrl"))
								announcementUrl = reader.getElementText();
							
							reader.next();
						} else {
							 reader.next();
						}
					} else {
						reader.next();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			NavalBattle.getDebugWindow().printInfo("Successfully loaded BroadcastService data");
		}
		
		private void checkForUpdates() {
			/*
			if(Integer.parseInt(Constants.VERSION_CODE) < Integer.parseInt(versionCode)) {
				NavalBattle.getDebugWindow().printWarning("Update found! " + versionReadable + " (" + versionCode + ")");
				NavalBattle.getDebugWindow().printWarning("Update url: " + updateUrl);
			} else {
				NavalBattle.getDebugWindow().printInfo("You are running the latest version!");
			}
			*/
		}
		
		private void checkForAnnouncement() {
			
		}
	}
}
