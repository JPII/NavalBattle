/*
 * Copyright (C) 2012 JPII and contributors
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

package com.jpii.navalbattle.game.entity;

import java.awt.Image;

import com.jpii.navalbattle.game.Location;

public class Entity {
	
	private Location location;
	private Image image;
	
	/**
	 * Construct an <code>Entity</code> with a <code>Location</code>
	 * 
	 * @param location
	 */
	public Entity(Location location) {
		this.location = location;
	}
	
	/**
	 * Construct an <code>Entity</code> with an <code>Image</code>
	 * 
	 * @param image
	 */
	public Entity(Image image) {
		this.image = image;
	}
	
	/**
	 * Construct an <code>Entity</code> with a <code>Location</code> and <code>Image</code>
	 * 
	 * @param location, image
	 */
	public Entity(Location location, Image image) {
		this.location = location;
	}
	
	/**
	 * Set current <code>Location</code>
	 * 
	 * @param location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * Get current <code>Location</code>.
	 * 
	 * @return
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Set current <code>Image</code>
	 * 
	 * @param image
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	
	/**
	 * Get current <code>Image</code>
	 * 
	 * @return
	 */
	public Image getImage() {
		return image;
	}
}
