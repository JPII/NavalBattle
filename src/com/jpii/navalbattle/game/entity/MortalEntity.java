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

public class MortalEntity extends Entity {
	
	private int health;
	private boolean dead;
	private int maxHealth;
	
	/**
	 * Construct <code>MortalEntity</code>.
	 * @param location
	 * @param image
	 * @param tag
	 * @param health
	 */
	public MortalEntity(Location location, Image image, String tag, int health) {
		super(location, 0x24B, tag);
		maxHealth = health;
		dead = false;
		setHealth(health);
	}
	
	/**
	 * Set health
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Add health
	 * @param health
	 */
	public void addHealth(int health) {
		this.health += health;
	}

	/**
	 * Subtract health
	 * @param damage
	 */
	public void damage(int damage) {
		this.health -= damage;
		
		if(getHealth() <= 0) {
			kill();
		}
	}

	/**
	 * Get health
	 * @return
	 */
	public int getHealth() {
		return this.health;
	}
	
	/**
	 * Determines if dead or not.
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * Kills the Entity.
	 */
	public void kill() {
		dead = true;
		this.health = 0;
		
		setActive(false);
	}

	/**
	 * Heals the Entity
	 */
	public void heal() {
		this.health = maxHealth;
		dead = false;
	}
	
	/**
	 * Set Entity as dead.
	 * @param dead
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
