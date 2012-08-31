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
