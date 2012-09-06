package com.jpii.navalbattle.game.entity;

import java.awt.Image;

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.game.entity.component.IntelligenceModule;

public class IntelligentEntity extends MortalEntity {
	
	private IntelligenceModule intelligenceModule;

	/**
	 * Construct <code>IntelligentEntity</code>.
	 * @param location
	 * @param image
	 * @param health
	 */
	public IntelligentEntity(Location location, Image image, IntelligenceModule intelligenceModule, int health) {
		super(location, image, health);
		this.intelligenceModule = intelligenceModule;
	}
	
	/**
	 * Called when attacked
	 * @param damage
	 */
	public void onAttacked(int damage, Entity attacker) {
		damage(damage);
		intelligenceModule.addTarget(attacker);
	}
	
	/**
	 * Get current instance's <code>IntelligenceModule</code>
	 * @return
	 */
	public IntelligenceModule getIntelligenceModule() {
		return intelligenceModule;
	}
}
