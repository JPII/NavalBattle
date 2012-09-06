package com.jpii.navalbattle.game.entity;

import java.awt.Image;

import com.jpii.navalbattle.game.Location;

public class MortalEntity extends Entity {
	
	private int health;
	
	public MortalEntity(Location location, Image image, int health) {
		super(location, image);
		this.health = health;
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
	}

	/**
	 * Get health
	 * @return
	 */
	public int getHealth() {
		return this.health;
	}
}
