package com.roketgamer.rauth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.roketgamer.RoketGamer;

public class Session {
	
	private String sessionID;
	
	/**
	 * Create a new <code>Session</code>.
	 * @param sessionID
	 */
	public Session(String sessionID) {
		this.sessionID = sessionID;
	}
	
	/**
	 * Get session ID.
	 * @return
	 */
	public String getSessionID() {
		return sessionID;
	}
	
	/**
	 * Logout of the current <code>Session</code>. Note that future API calls will fail.
	 */
	public void logout() {
		try {
			URL url = new URL(RoketGamer.SERVER_LOCATION + "/api/" + RoketGamer.VERSION + "/auth/logout.php?key=" + RoketGamer.getInstance().getKey());
			
			URLConnection connection = url.openConnection();
		    connection.addRequestProperty("Protocol", "Http/1.1");
		    connection.addRequestProperty("Connection", "keep-alive");
		    connection.addRequestProperty("Keep-Alive", "1000");
		    connection.addRequestProperty("User-Agent", "Web-Agent");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			in.close();
		} catch (Exception e) { }
	}
}
