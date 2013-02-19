/**
 * 
 */
package com.jpii.navalbattle.game.entity;

import java.awt.event.MouseEvent;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridHelper;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.pavo.grid.Tile;

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
	public BattleShip(EntityManager em, Location loc, GridedEntityTileOrientation superId,int team) {
		super(em, loc, superId,team);
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
		//if(Game.Settings.isFinishedGenerating && Game.Settings.rand.nextInt(0,20) == 2)
			//moveTo(r+getLocation().getRow(),c+getLocation().getCol());
	}
	public void onMouseMove(int x, int y) {
		System.out.println("The battleship was hovered over. (" + x + "," + y + ")");
	}
	public void onMouseDown(int x, int y, boolean leftbutton) {
		System.out.println("The battleship was clicked on. Left mouse button? " + leftbutton + " (" + x + "," + y + ")");
		if(!leftbutton){
			byte t = getCurrentOrientation();
			if (t == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT)
				rotateTo(GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM);
			else
				rotateTo(GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		}
	}
	
	public void rotateTo(byte code) {
		boolean flag = GridHelper.canPlaceInGrid(getManager(), code, getLocation().getRow(), getLocation().getCol(), getWidth());
		if (flag)
			super.rotateTo(code);
	}
}
