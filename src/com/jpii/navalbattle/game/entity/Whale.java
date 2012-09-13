package com.jpii.navalbattle.game.entity;

import java.awt.Image;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.game.entity.component.IntelligenceModule;

public class Whale extends IntelligentEntity {
	
	private boolean angry = false;

	/**
	 * Construct <code>IntelligentEntity</code>.
	 * @param location
	 * @param image
	 * @param tag
	 * @param health
	 */
	public Whale(Location location, Image image, IntelligenceModule intelligenceModule, String tag, int health) {
		super(location, image, intelligenceModule, tag, health);
	}
	
	/**
	 * Called when attacked. Whale will turn visibly angry.
	 * @param damage
	 * @param attacker
	 */
	public void onAttacked(int damage, Entity attacker) {
		damage(damage);
		getIntelligenceModule().addTarget(attacker);
		
		// TODO: Make the whale do something if it is angry
		setAngry(true);
	}
	
	/**
	 * Kills the Entity.
	 */
	public void kill() {
		setDead(true);
		setHealth(0);
		
		setActive(false);
		NavalBattle.getGameState().addScore(Constants.WHALE_KILL_SCORE);
	}
	
	/**
	 * Make the whale appear angry.
	 * @param angry
	 */
	public void setAngry(boolean angry) {
		this.angry = angry;
	}
	
	/**
	 * Get if the whale is angry.
	 * @return
	 */
	public boolean isAngry() {
		return angry;
	}
}
