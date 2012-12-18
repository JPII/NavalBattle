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

import com.jpii.navalbattle.game.*;
import com.jpii.navalbattle.pavo.EntityManager;
import com.jpii.navalbattle.pavo.EntityReference;

public class Entity implements Runnable {
	
	private Location location;
	private boolean active;
	private String tag;
	private EntityReference ref;
	private int id;
	private EntityManager man;
	public long lastUpdate = 0;
	
	/**
	 * Default constructor. Sets instance to inactive.
	 */
	public Entity(EntityManager eman) {
		setActive(false);
		man = eman;
	}
	public EntityManager getManager() {
		return man;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Construct an <code>Entity</code>
	 * @param location
	 * @param image
	 * @param tag
	 */
	public Entity(Location location, int id, String tag) {
		setLocation(location);
		setTag(tag);
		setActive(true);
		setReference(new EntityReference(id,-1));
	}
	
	public void setReference(EntityReference r) {
		ref = r;
	}
	public EntityReference getReference() {
		return ref;
	}
	
	public void update() {
		
	}
	
	/**
	 * Gets the tag for the entity.
	 * @return
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * Sets the tag for the Entity.
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
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
	public boolean isActive() {
		return active;
	}
	
	public void onMouseDown(int localMX, int localMY) {
		
	}
	
	public void onMouseMove(int localMX, int localMY) {
		if (getManager().isTileFilledWithWater(getLocation().getRow(),getLocation().getCol()))
			return;
		//System.out.println(getLocation() + "mouse: " + localMX + "," + localMY);
		id = 1;
		getManager().getAssociatedChunk(getLocation().getRow(),getLocation().getCol()).writeBuffer();
		getManager().getWorld().forceRender();
	}

	@Override
	public void run() {
		update();		
	}
}