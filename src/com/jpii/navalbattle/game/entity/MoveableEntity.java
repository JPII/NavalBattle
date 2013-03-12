/**
 * 
 */
package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;

/**
 * @author MKirkby
 *
 */
public class MoveableEntity extends Entity {
	private int maxStep = 2;
	
	/**
	 * @param em
	 */
	public MoveableEntity(EntityManager em) {
		super(em);
	}

	/**
	 * @param em
	 * @param loc
	 * @param id
	 * @param orientation
	 * @param teams
	 */
	public MoveableEntity(EntityManager em, Location loc,
			GridedEntityTileOrientation id, byte orientation, int teams) {
		super(em, loc, id, orientation, teams);
	}
	
	public void toggleMovable() {
		if (getCurrentOrientation() == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT) {
			for (int x = 0; x < (maxStep * 2) + getWidth(); x++) {
				for (int y = 0; y < (maxStep * 2) + 1; y++) {
					int r = (y + getLocation().getRow()) - (((maxStep * 2) + 1)/2);
					int c = (x + getLocation().getCol()) - (maxStep);
					if (r >= 0 && c >= 0) {
						if (getManager().getTilePercentLand(r,c) <= 8) {
							getManager().setTileOverlay(r,c,(short)0x2f1d);
						}
						else {
							getManager().setTileOverlay(r,c,(short)0x001);
						}
					}
				}
			}
		}
		else {
			for (int x = 0; x < (maxStep * 2) + getWidth(); x++) {
				for (int y = 0; y < (maxStep * 2) + 1; y++) {
					int r = (y + getLocation().getRow()) - (((maxStep * 2) + 1)/2);
					int c = (x + getLocation().getCol()) - (((maxStep * 2) + getWidth())/2);
					if (r >= 0 && c >= 0) {
						if (getManager().getTilePercentLand(c,r) <= 8) {
							getManager().setTileOverlay(c,r,(short)0x2f1d);
						}
						else {
							getManager().setTileOverlay(c,r,(short)0x0001);
						}
					}
				}
			}
		}
	}
	
	public int getMaxStep() {
		return maxStep;
	}
	
	public void setMaxStep(int v) {
		maxStep = v;
	}
}
