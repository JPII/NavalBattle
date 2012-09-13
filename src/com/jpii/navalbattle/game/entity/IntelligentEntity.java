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

public class IntelligentEntity extends MortalEntity {
	
	private IntelligenceModule intelligenceModule;

	/**
	 * Construct <code>IntelligentEntity</code>.
	 * @param location
	 * @param image
	 * @param tag
	 * @param health
	 */
	public IntelligentEntity(Location location, Image image, IntelligenceModule intelligenceModule, String tag, int health) {
		super(location, image, tag, health);
		this.intelligenceModule = intelligenceModule;
	}
	
	/**
	 * Called when attacked
	 * @param damage
	 * @param attacker
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
