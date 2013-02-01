/**
 * 
 */
package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.Location;

/**
 * @author maximusvladimir
 *
 */
public class Whale extends AnimatedEntity {

	public static int[] animationFramesForDaWhale;
	
	/**
	 * @param em
	 * @param loc
	 * @param animationFrameIds
	 */
	public Whale(EntityManager em, Location loc, int... animationFrameIds) {
		super(em, loc, animationFrameIds);
	}
	
	long ticks = 0;
	int nextIndex = 0;
	public void onUpdate() {
		ticks++;
		if (ticks % 5 == 0) {
			setCurrentFrame(nextIndex++);
			if (nextIndex >= getTotalFrames())
				nextIndex = 0;
		}
	}

}
