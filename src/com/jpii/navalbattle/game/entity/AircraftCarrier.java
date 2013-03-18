package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridHelper;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;

public class AircraftCarrier extends MoveableEntity {
	private static final long serialVersionUID = 1L;
	public static GridedEntityTileOrientation AIRCRAFTCARRIER_ID;

	public AircraftCarrier(EntityManager em) {
		super(em);
		imgLocation="drawable-game/aircraftcarrier/aircraftcarrier.png";
	}

	public AircraftCarrier(EntityManager em, Location loc, GridedEntityTileOrientation superId,byte orientation,int team) {
		super(em, loc, superId,orientation,team);
		imgLocation="drawable-game/aircraftcarrier/aircraftcarrier.png";
		Game g = em.getWorld().getGame();
		if (!g.isAClient()) {
			g.getSelfServer().send("aircraftcarrier:"+loc.getCol()+","+loc.getRow());
		}
	}
	
	public void init() {
		setWidth(5);
		setHeight(1);
	}
	
	public boolean moveTo(Location loc, boolean override) {
		return super.moveTo(loc, override);
	}
	
	public void onUpdate(long timePassed) {
	}
	
	public void onMouseMove(int x, int y) {
	}
	
	public void onMouseDown(int x, int y, boolean leftbutton) {
		super.onMouseDown(x, y, leftbutton);
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
