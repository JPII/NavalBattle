package com.jpii.navalbattle.game.entity;

import java.awt.Image;

import com.jpii.navalbattle.game.Location;

public class PlayerEntity extends MortalEntity {

	/**
	 * Construct <code>PlayerEntity</code>.
	 * @param location
	 * @param image
	 * @param tag
	 * @param health
	 */
	public PlayerEntity(Location location, Image image, String tag, int health) {
		super(location, image, tag, health);
	}
	
	/**
	 * Called when attacked
	 * @param damage
	 */
	public void onAttacked(int damage, Entity attacker) {
		damage(damage);
	}
}
