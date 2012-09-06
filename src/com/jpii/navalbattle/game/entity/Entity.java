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
	private boolean active;
	
	/**
	 * Default constructor. Sets instance to inactive.
	 */
	public Entity() {
		this.active = false;
	}
	
	/**
	 * Construct an <code>Entity</code>
	 * @param location, image
	 */
	public Entity(Location location, Image image) {
		this.location = location;
		this.image = image;
		this.active = true;
	}
	
	/**
	 * Called when moving
	 * @param location
	 */
	public void onMove(Location location) {
		
	}
	
	/**
	 * Called when attacked
	 * @param attacker
	 */
	public void onAttacked(Entity attacker) {
		
	}
	
	/**
	 * Called when attacking
	 * @param target
	 */
	public void onAttack(Entity target) {
		
	}
	
	/**
	 * Set current <code>Location</code>
	 * @param location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * Get current <code>Location</code>.
	 * @return
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Set current <code>Image</code>
	 * @param image
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	
	/**
	 * Get current <code>Image</code>
	 * @return
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * Set active
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Get if active
	 * @return
	 */
	public boolean getActive() {
		return active;
	}
}