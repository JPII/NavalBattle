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

package com.roketgamer.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.roketgamer.Player;
import com.roketgamer.RoketGamer;
import com.roketgamer.gravatar.Gravatar;
import com.roketgamer.gravatar.GravatarDefaultImage;
import com.roketgamer.gravatar.GravatarRating;

public class DataLoadThread extends Thread {
	
	private Player player;
	
	public DataLoadThread(Player player) {
		this.player = player;
		RoketGamer.getInstance().getLoggerHook().printInfo("DataLoadThread created");
	}

	@Override
	public void run() {
		loadUserData();
		loadFriends();
		loadGravatars();
		
		player.setDataLoaded(true);
		RoketGamer.getInstance().getLoggerHook().printInfo("DataLoadThread completed");
	}
	
	private void loadUserData() {
		try {
			URL url = new URL(RoketGamer.SERVER_LOCATION + "/api/" + RoketGamer.VERSION + "/user/getuserinfo.php?key=" + 
								RoketGamer.getInstance().getSession().getSessionKey());
			
			URLConnection connection = url.openConnection();
		    connection.addRequestProperty("Protocol", "Http/1.1");
		    connection.addRequestProperty("Connection", "keep-alive");
		    connection.addRequestProperty("Keep-Alive", "1000");
		    connection.addRequestProperty("User-Agent", "Web-Agent");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String result = in.readLine();
			
			if (result.contains("true")) {
				player.setEmail(in.readLine());
				RoketGamer.getInstance().getLoggerHook().printInfo("Loaded user email: " + player.getEmail());
			} else if (result.contains("false")) {
				if(result.contains("Invalid session key")) {
					RoketGamer.getInstance().getLoggerHook().printError("Invalid session key when loading user email");
				} else {
					RoketGamer.getInstance().getLoggerHook().printError("Unknown error when loading user email (returned false)");
				}
			} else {
				RoketGamer.getInstance().getLoggerHook().printError("Unknown error when loading user email (other)");
			}

			in.close();
		} catch (Exception e) { }
	}
	
	private void loadFriends() {
		// TODO: Implement
	}
	
	private void loadGravatars() {
		Gravatar gravatar = new Gravatar();
		gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
		gravatar.setDefaultImage(GravatarDefaultImage.WAVATAR);
		
		gravatar.setSize(16);
		player.setAvatar(gravatar.download(player.getEmail()), 16);
		
		gravatar.setSize(32);
		player.setAvatar(gravatar.download(player.getEmail()), 32);
		
		gravatar.setSize(64);
		player.setAvatar(gravatar.download(player.getEmail()), 64);
		
		gravatar.setSize(128);
		player.setAvatar(gravatar.download(player.getEmail()), 128);
		
		gravatar.setSize(256);
		player.setAvatar(gravatar.download(player.getEmail()), 256);
		
		RoketGamer.getInstance().getLoggerHook().printInfo("Loaded Gravatars");
	}
}