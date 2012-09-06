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
	 * @param health
	 */
	public MortalEntity(Location location, Image image, int health) {
		super(location, image);
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
}
