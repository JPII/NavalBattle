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
	}
	public void onMouseMove(int x, int y) {
		getManager().moveEntity(getLocation().getRow(),getLocation().getCol(),getLocation().getRow()+1,getLocation().getCol());
	}
}
