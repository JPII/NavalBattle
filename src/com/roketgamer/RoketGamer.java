package com.roketgamer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.roketgamer.analytics.Analytics;
import com.roketgamer.rauth.*;

public class RoketGamer {
	
	/** RoketGamer components */
	private APIKey key;
	private Player player;
	private AuthStatus status;
	private Session session;
	private Analytics analytics;
	
	private static RoketGamer instance;
	
	/** Constants */
	public static final String VERSION = "1.0";
	public static final String SERVER_LOCATION = "http://www.roketgamer.com";
	
	/**
	 * Initialize RoketGamer. Returns <code>AuthStatus</code>.
	 * @param key
	 * @param player
	 * @param forceAuth
	 * @return
	 */
	public AuthStatus init(APIKey key, Player player) {
		instance = this;
		
		this.key = key;
		this.player = player;
		
		try {
			URL url = new URL(SERVER_LOCATION + "/api/" + VERSION + "/auth/login.php?key=" + key.getKey() + "&username=" + player.getName()
					+ "&password=" + player.getPassword().getPassword());
			
			URLConnection connection = url.openConnection();
		    connection.addRequestProperty("Protocol", "Http/1.1");
		    connection.addRequestProperty("Connection", "keep-alive");
		    connection.addRequestProperty("Keep-Alive", "1000");
		    connection.addRequestProperty("User-Agent", "Web-Agent");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String result = in.readLine();
			
			if (result.contains("true")) {
				session = new Session(in.readLine());
				status = AuthStatus.GOOD;
			} else if (result.contains("false")) {
				if(result.contains("Invalid API key")) {
					status = AuthStatus.INVALID_API_KEY;
				} else if (result.contains("Invalid user")) {
					status = AuthStatus.BAD;
				} else if(result.contains("API offline")) {
					status = AuthStatus.OFFLINE;
				}
			} else {
				status = AuthStatus.UNKNOWN;
			}

			in.close();
		} catch (Exception e) { }
		
		if(status == AuthStatus.GOOD) {
			analytics = new Analytics();
		}
		
		return status;
	}
	
	public void logout() {
		try {
			URL url = new URL(SERVER_LOCATION + "/api/" + VERSION + "/auth/logout.php?key=" + key.getKey());
			
			URLConnection connection = url.openConnection();
		    connection.addRequestProperty("Protocol", "Http/1.1");
		    connection.addRequestProperty("Connection", "keep-alive");
		    connection.addRequestProperty("Keep-Alive", "1000");
		    connection.addRequestProperty("User-Agent", "Web-Agent");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			in.close();
		} catch (Exception e) { }
	}
	
	/**
	 * Returns current <code>APIKey</code>
	 * @return
	 */
	public APIKey getKey() {
		return key;
	}
	
	/**
	 * Return current <code>Player</code>
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Return current <code>Session</code>
	 * @return
	 */
	public Session getSession() {
		return session;
	}
	
	/**
	 * Return current <code>AuthStatus</code>
	 * @return
	 */
	public AuthStatus getStatus() {
		return status;
	}
	
	/**
	 * Return RoketGamer version
	 * @return
	 */
	public String getVersion() {
		return VERSION;
	}
	
	/**
	 * Return RoketGamer server location
	 * @return
	 */
	public String getServerLocation() {
		return SERVER_LOCATION;
	}
	
	/**
	 * Get current <code>Analytics</code> instance.
	 * @return
	 */
	public Analytics getAnalytics() {
		return analytics;
	}
	
	/**
	 * Get current <code>RoketGamer</code> instance.
	 */
	public static RoketGamer getInstance() {
		return instance;
	}
}
