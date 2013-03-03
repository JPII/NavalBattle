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

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.roketgamer.data.DataLoadThread;
import com.roketgamer.exception.DataNotLoadedException;
import com.roketgamer.friend.Friend;
import com.roketgamer.rauth.Password;

public class Player {

	private String username, email;
	private Password password;
	private ArrayList<Friend> friends;
	private byte[] gravatar_16px, gravatar_32px, gravatar_64px, gravatar_128px, gravatar_256px;
	
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
	 * @throws Exception 
	 */
	public ArrayList<Friend> getFriends() throws DataNotLoadedException {
		if(hasLoadedData)
			return friends;
		else
			throw new DataNotLoadedException("Data not loaded!");
	}
	
	/**
	 * Returns player's current avatar (Gravatar) in the form of a <code>byte[]</code>.
	 * @param px Size of avatar. Available in 16px, 32px, 64px, 128px, and 256px.
	 * @return
	 */
	public byte[] getAvatarAsBytes(int px) {
		switch(px) {
			case 16: return gravatar_16px;
			case 32: return gravatar_32px;
			case 64: return gravatar_64px;
			case 128: return gravatar_128px;
			case 256: return gravatar_256px;
			default: return gravatar_256px;
		}
	}
	
	/**
	 * Returns player's current avatar (Gravatar) in the form of an <code>Image</code>.
	 * @param px Size of avatar. Available in 16px, 32px, 64px, 128px, and 256px.
	 * @return
	 */
	public Image getAvatarAsImage(int px) {
		switch(px) {
			case 16: return new ImageIcon(gravatar_16px).getImage();
			case 32: return new ImageIcon(gravatar_32px).getImage();
			case 64: return new ImageIcon(gravatar_64px).getImage();
			case 128: return new ImageIcon(gravatar_128px).getImage();
			case 256: return new ImageIcon(gravatar_256px).getImage();
			default: return new ImageIcon(gravatar_256px).getImage();
		}
	}
	
	public void setAvatar(byte[] img, int px) {
		switch(px) {
		case 16: gravatar_16px = img; break;
		case 32: gravatar_32px = img; break;
		case 64: gravatar_64px = img; break;
		case 128: gravatar_128px = img; break;
		case 256: gravatar_256px = img; break;
		default: gravatar_256px = img; break;
	}
	}
	
	/**
	 * Set <code>Player</code> email address.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Get <code>Player</code> email address.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Returns if player's data has been loaded.
	 * @return
	 */
	public boolean hasLoadedData() {
		return hasLoadedData;
	}
	
	/**
	 * Set data as loaded. <b>Use with caution!</b>
	 * @param status
	 */
	public void setDataLoaded(boolean status) {
		hasLoadedData = status;
	}
	
	/**
	 * Load data. <b>Use with caution!</b>
	 */
	public void loadData() {
		DataLoadThread dataLoaded = new DataLoadThread(this);
		dataLoaded.start();
	}
}
