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
							System.out.print(reader.getAttributeValue(0) + " : ");
							
							if(reader.getAttributeValue(0).equals("version_code"))  {
								versionCode = reader.getElementText();
								System.out.println(versionCode);
								break;
							}
							
							if(reader.getAttributeValue(0).equals("version_readable")) {
								versionReadable = reader.getElementText();
								System.out.println(versionReadable);
								break;
							}
							
							if(reader.getAttributeValue(0).equals("update_url")) {
								updateUrl = reader.getElementText();
								System.out.println(updateUrl);
								break;
							}
							
							if(reader.getAttributeValue(0).equals("announcement_code")) {
								announcementCode = reader.getElementText();
								System.out.println(announcementCode);
								break;
							}
							
							if(reader.getAttributeValue(0).equals("announcement_title")) {
								announcementTitle = reader.getElementText();
								System.out.println(announcementTitle);
								break;
							}
							
							if(reader.getAttributeValue(0).equals("announcement_text")) {
								announcementText = reader.getElementText();
								System.out.println(announcementText);
								break;
							}
							
							if(reader.getAttributeValue(0).equals("announcement_url")) {
								announcementUrl = reader.getElementText();
								System.out.println(announcementUrl);
								break;
							}
							
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
			int clientVersion = Integer.parseInt(Constants.VERSION_CODE);
			int latestVersion = Integer.parseInt(versionCode);
			
			if(clientVersion < latestVersion) {
				NavalBattle.getDebugWindow().printWarning("Update found! " + versionReadable + " (" + versionCode + ")");
				NavalBattle.getDebugWindow().printWarning("Update url: " + updateUrl);
			} else {
				NavalBattle.getDebugWindow().printInfo("You are running the latest version!");
			}
		}
		
		private void checkForAnnouncement() {
			
		}
	}
}
