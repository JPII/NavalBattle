package com.jpii.navalbattle.io;

import java.io.InputStream;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

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
								versionCode = reader.getElementText();
							
							if(reader.getAttributeValue(0).equals("announcementCode"))
								versionReadable = reader.getElementText();
							
							if(reader.getAttributeValue(0).equals("announcementTitle"))
								versionCode = reader.getElementText();
							
							if(reader.getAttributeValue(0).equals("announcementText"))
								versionCode = reader.getElementText();
							
							if(reader.getAttributeValue(0).equals("announcementUrl"))
								versionReadable = reader.getElementText();
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
			
		}
		
		private void checkForAnnouncement() {
			
		}
	}
}
