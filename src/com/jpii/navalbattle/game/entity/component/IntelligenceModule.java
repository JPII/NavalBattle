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

package com.jpii.navalbattle.game.entity.component;

import com.jpii.navalbattle.game.entity.Entity;

public class IntelligenceModule {
	
	private Entity entity;
	private int team, level;
	
	/**
	 * Construct a basic IntelligenceModule
	 * @param entity
	 * @param team
	 * @param level
	 */
	public IntelligenceModule(Entity entity, int team, int level) {
		this.entity = entity;
		this.team = team;
		this.level = level;
	}
	
	/**
	 * Get Entity to which the current instance belongs
	 * @return
	 */
	public Entity getEntity() {
		return entity;
	}
	
	/**
	 * Set Entity to which the current instance belongs
	 * @param entity
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	/**
	 * Get team to which the current instance belongs
	 * @return
	 */
	public int getTeam() {
		return team;
	}
	
	/**
	 * Set team to which the current instance belongs
	 * @param team
	 */
	public void setTeam(int team) {
		this.team = team;
	}
	
	/**
	 * Get difficulty level
	 * @return
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Set difficulty level
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
}
