package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;

public class Submarine extends MoveableEntity {
	private static final long serialVersionUID = 1L;
	public static GridedEntityTileOrientation SUBMARINE_ID;
	public static GridedEntityTileOrientation SUBMARINEU_ID;
	private boolean submerged=false;
	
	public Submarine(EntityManager em, Location loc,byte orientation,int team) {
		super(em, loc, SUBMARINE_ID,orientation,team);
		imgLocation="drawable-game/submarine/submarine.png";
		Game g = em.getWorld().getGame();
		if (!g.isAClient()) {
			g.getSelfServer().send("submarine:"+loc.getCol()+","+loc.getRow());
		}
		handle = 11;
		moved = 0;
		maxMovement=4;
		attackRange = 3;
		gunsAttackOption = true;
		missileAttackOption = true;
	}
	
	public void init() {
		setWidth(2);
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
	}
	public void toggleElevation(){
		if(submerged)
			setId(SUBMARINE_ID);
		else
			setId(SUBMARINEU_ID);
		submerged = !submerged;
	}
	
	public boolean isSumberged(){
		return submerged;
	}
	
	/**
	 * Gets the bow of the ship.
	 * @return The location. Could be "Unknown", if the Entity is not in the Grid.
	 */
	public Location getELocation(){
		Location temp = super.getLocation();
		if(getCurrentOrientation() == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT){
			return temp;
		}
		else if(getCurrentOrientation() == GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM){
			temp = new Location(temp.getRow()+(getWidth()-1),temp.getCol());
			if(startpos)
				temp = new Location(temp.getRow()+(getWidth()-1),temp.getCol());
		}
	
		return temp;
	}
	
	public void resetMovement(){
		super.resetMovement();
		if(isSumberged()){
			toggleElevation();
		}
	}
}
