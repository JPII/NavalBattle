/**
 * 
 */
package com.jpii.navalbattle.game.entity;


import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridHelper;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;

/**
 * @author maximusvladimir
 *
 */
public class BattleShip extends Entity {
	public static GridedEntityTileOrientation BATTLESHIP_ID;
	/**
	 * @param em
	 */
	public BattleShip(EntityManager em) {
		super(em);
	}

	/**
	 * @param em
	 * @param loc
	 * @param superId
	 */
	public BattleShip(EntityManager em, Location loc, GridedEntityTileOrientation superId,byte orientation,int team) {
		super(em, loc, superId,orientation,team);
	}
	
	public void init() {
		setWidth(4);
		setHeight(1);
	}
	public boolean moveTo(Location loc, boolean override) {
		return super.moveTo(loc, override);
	}
	public void onUpdate(long timePassed) {
		int r = 1;
		int c = 1;
	}
	public void onMouseMove(int x, int y) {
	}
	public void onMouseDown(int x, int y, boolean leftbutton) {
		if(!leftbutton){
			byte t = getCurrentOrientation();
			if (t == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT)
				rotateTo(GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM);
			else
				rotateTo(GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		}
	}
	
	public void rotateTo(byte code) {
		boolean flag = GridHelper.canRotate(getManager(), this, code, getLocation().getRow(), getLocation().getCol(), getWidth());
		if (flag)
			super.rotateTo(code);
	}
}
