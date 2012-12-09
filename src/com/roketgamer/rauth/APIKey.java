/*
 * Copyright (C) 2012 RoketGamer <http://roketgamer.com> and contributors.
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

public class APIKey {
	
	private String key;
	
	/**
	 * Create a new <code>APIKey</code>.
	 * @param key
	 */
	public APIKey(String key) {
		this.key = key;
	}
	
	/**
	 * Get API key.
	 * @return
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Set API key.
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
