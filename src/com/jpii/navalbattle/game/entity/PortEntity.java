/**
 * 
 */
package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;

/**
 * @author maximusvladimir
 *
 */
public class PortEntity extends AnimatedEntity {

	/**
	 * @param em
	 * @param loc
	 * @param orientation
	 * @param team
	 * @param animationFrameIds
	 */
	public PortEntity(EntityManager em, Location loc, byte orientation,
			int team, GridedEntityTileOrientation... animationFrameIds) {
		super(em, loc, orientation, team, animationFrameIds);
		// TODO Auto-generated constructor stub
	}

}
