/**
 * 
 */
package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;

/**
 * @author maximusvladimir
 *
 */
public class AnimatedEntity extends Entity {
	private GridedEntityTileOrientation[] animationids;

	/**
	 * @param em The EnitityManager.
	 * @param loc The Location of the Entity.
	 * @param animationFrameIds The animation frame sequences.
	 */
	public AnimatedEntity(EntityManager em, Location loc,int team, GridedEntityTileOrientation... animationFrameIds) {
		super(em, loc, animationFrameIds[0],team);
		animationids = animationFrameIds;
	}
	
	public void setCurrentFrame(int index) {
		if (index >= getTotalFrames())
			throw new java.lang.ArrayIndexOutOfBoundsException("The indicated frame is not in the given range of frames.");
		setId(animationids[index]);
	}
	
	public int getTotalFrames() {
		return animationids.length;
	}
	
	
}
