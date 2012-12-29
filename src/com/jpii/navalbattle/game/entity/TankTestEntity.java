/**
 * 
 */
package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.pavo.EntityManager;

public class TankTestEntity extends Entity {
	public TankTestEntity(EntityManager eman, Location l) {
		super(eman, l);
		setId(0x93AF9B);
		setTag("tank-test-1");
	}
	public void onMouseMove(int x, int y) {
		// If the mouse is in the upper left quadrant, then move is back towards that way....etc.
		int addx = 0;
		int addy = 0;
		if (x < 25)
			addx = -1;
		else
			addx = 1;
		if (y < 25)
			addy = -1;
		else
			addy = 1;
		getManager().moveEntity(getLocation().getRow(),getLocation().getCol(),getLocation().getRow()+addy,getLocation().getCol()+addx);
	}
}
