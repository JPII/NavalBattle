package com.jpii.navalbattle.game.entity;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;

public class BattleShip extends MoveableEntity {
	private static final long serialVersionUID = 1L;
	public static GridedEntityTileOrientation BATTLESHIP_ID;

	/**
	 * @param em
	 * @param loc
	 * @param superId
	 */
	public BattleShip(EntityManager em, Location loc,byte orientation,int team) {
		super(em, loc, BATTLESHIP_ID,orientation,team);
		imgLocation="drawable-game/battleship/battleship.png";
		Game g = em.getWorld().getGame();
		if (!g.isAClient()) {
			g.getSelfServer().send("battleship:"+loc.getCol()+","+loc.getRow());
		}
		setTeamColor((byte) ((Game.Settings.rand.nextBoolean()) ? 5 : 9));
		handle = 31;
		maxHealth = 1200;
		currentHealth = maxHealth;
		
		moved=0;
		maxMovement = 5;
		primaryRange = 5;
		secondaryRange = 7;
		gunsAttackOption = true;
		missileAttackOption = true;
	}
	public void onTeamColorBeingDrawn(Area a) {
		if (getCurrentOrientation() == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT) {
			if (getLocation().getCol() % 2 == 0)
				a.add(new Area(new Rectangle2D.Float(95,0,50,0)));
			else
				a.add(new Area(new Rectangle2D.Float(145,0,50,0)));
		}
	}
	
	public void init() {
		setWidth(4);
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
}
