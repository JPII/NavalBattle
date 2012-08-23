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
