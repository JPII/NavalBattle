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
import com.jpii.navalbattle.game.entity.component.IntelligenceModule;

public class Entity {
	
	private Location location;
	private Image image;
	private IntelligenceModule intelligenceModule;
	private int health = -1;
	
	/**
	 * Construct an <code>Entity</code> with a <code>Location</code> and <code>Image</code>
	 * 
	 * @param location, image
	 */
	public Entity(Location location, Image image, IntelligenceModule intelligenceModule, int health) {
		this.location = location;
		this.image = image;
		this.intelligenceModule = intelligenceModule;
		this.health = health;
		
		intelligenceModule.setEntity(this);
	}
	
	/**
	 * Called when moving
	 */
	public void onMove() {
		
	}
	
	/**
	 * Called when attacked
	 * @param damage
	 */
	public void onAttacked(int damage, Entity attacker) {
		health -= damage;
		intelligenceModule.addTarget(attacker);
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
	
	/**
	 * Get current <code>IntelligenceModule</code>
	 * @param intelligenceModule
	 */
	public void setIntelligenceModule(IntelligenceModule intelligenceModule) {
		this.intelligenceModule = intelligenceModule;
	}
	
	/**
	 * Set current <code>IntelligenceModule</code>
	 * @return
	 */
	public IntelligenceModule getIntelligenceModule() {
		return intelligenceModule;
	}
}
