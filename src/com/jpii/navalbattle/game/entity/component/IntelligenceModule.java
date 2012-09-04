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

import java.util.ArrayList;

import com.jpii.navalbattle.game.entity.Entity;

public class IntelligenceModule {
	
	private Entity entity;
	private int team, level;
	private ArrayList<Entity> targets = new ArrayList<Entity>();
	
	/**
	 * Construct a basic IntelligenceModule
	 * @param team
	 * @param level
	 */
	public IntelligenceModule(int team, int level) {
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
	
	/**
	 * Get targets
	 * @return
	 */
	public ArrayList<Entity> getTargets() {
		return targets;
	}
	
	/**
	 * Add target to bottom of priority
	 * @param entity
	 */
	public void addTarget(Entity entity) {
		targets.add(entity);
	}
	
	/**
	 * Add target with specific priority
	 * @param entity
	 * @param pos
	 */
	public void addTarget(Entity entity, int pos) {
		targets.add(pos, entity);
	}
}
