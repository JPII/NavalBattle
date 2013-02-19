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

package com.roketgamer;

import java.util.ArrayList;

import com.roketgamer.data.DataLoadThread;
import com.roketgamer.friend.Friend;
import com.roketgamer.rauth.Password;

public class Player {

	private String username;
	private Password password;
	private ArrayList<Friend> friends;
	
	private boolean hasLoadedData;

	/**
	 * Create a new <code>Player</code>.
	 * @param username
	 * @param password
	 */
	public Player(String username, Password password) {
		this.username = username;
		this.password = password;
		friends = new ArrayList<Friend>();
		
		hasLoadedData = false;
	}

	/**
	 * Get player name.
	 * @return
	 */
	public String getName() {
		return username;
	}

	/**
	 * Get player <code>Password</code>.
	 * @return
	 */
	public Password getPassword() {
		return password;
	}
	
	/**
	 * Get player's friends list. Consists of <code>Friend</code> objects.
	 * @return
	 */
	public ArrayList<Friend> getFriends() {
		return friends;
	}
	
	/**
	 * Returns if player's data has been loaded.
	 * @return
	 */
	public boolean hasLoadedData() {
		return hasLoadedData;
	}
	
	public void setDataLoaded(boolean status) {
		hasLoadedData = status;
	}
	
	public void loadData() {
		DataLoadThread dataLoaded = new DataLoadThread(this);
		dataLoaded.start();
	}
}
