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
	
	int nextIndex = 0;
	boolean direction = true;
	public void onUpdate(long tickTime) {
		super.onUpdate(tickTime);
		if (tickTime % 5 == 0) {
			setCurrentFrame(nextIndex);
			if (direction)
				nextIndex++;
			else
				nextIndex--;
			if (nextIndex >= getTotalFrames()) {
				direction = false;
				nextIndex--;
			}
			if (nextIndex == -1) {
				direction = true;
				nextIndex = 0;
			}
		}
	}

}
