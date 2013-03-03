/*
 * Copyright (C) 2013 RoketGamer <http://roketgamer.com> and contributors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.roketgamer.rauth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.roketgamer.RoketGamer;

public class Session {
	
	private String sessionKey;
	
	/**
	 * Create a new <code>Session</code>.
	 * @param sessionID
	 */
	public Session(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	/**
	 * Get session ID.
	 * @return
	 */
	public String getSessionKey() {
		return sessionKey;
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
