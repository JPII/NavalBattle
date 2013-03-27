package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.pavo.grid.Tile;

public class MoveableEntity extends Entity {
	private static final long serialVersionUID = 1L;
	protected int maxMovement;
	protected int moved;
	private int health = 100;
	private boolean showMove = false;
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
		handle = 1;
	}
	
	public boolean isMovableTileBeingShown() {
		return showMove;
	}
	
	public void toggleMovable() {
		short good = (short)0x2f1d;
		short bad = (short)0x001;
		if(showMove){
			showMove = false;
			good = bad = 0;
		}
		else{
			showMove = true;
		}
			
			
		if (getCurrentOrientation() == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT) {
			for (int x = 0; x < (getMovementLeft() * 2) + 1; x++) {
				for (int y = 0; y < (getMovementLeft() * 2) + 1; y++) {
					int r = (y + getLocation().getRow()) - (((getMovementLeft() * 2) + 1)/2);
					int c = (x + getLocation().getCol()) - (getMovementLeft());
					if (r >= 0 && c >= 0) {
						Tile<?> temp = getManager().getTile(r,c);
						if (getManager().getTilePercentLand(r,c) <= Game.Settings.waterThresholdBarrier && (temp==null||temp.getEntity().equals(this))) {
							getManager().setTileOverlay(r,c,good);
						}
						else {
							getManager().setTileOverlay(r,c,bad);
						}
					}
				}
			}
		}
		else {
			for (int x = 0; x < (getMovementLeft() * 2) + 1; x++) {
				for (int y = 0; y < (getMovementLeft() * 2) + 1; y++) {
					int c = (x + getLocation().getCol()) - (((getMovementLeft() * 2) + 1)/2);
					int r = (y + getLocation().getRow()) - (getMovementLeft());
					if (r >= 0 && c >= 0) {
						Tile<?> temp = getManager().getTile(r,c);
						if (getManager().getTilePercentLand(r,c) <= Game.Settings.waterThresholdBarrier && (temp==null||temp.getEntity().equals(this))) {
							getManager().setTileOverlay(r,c,good);
						}
						else {
							getManager().setTileOverlay(r,c,bad);
						}
					}
				}
			}
		}
		getManager().getWorld().forceRender();
	}
	
	public boolean isInMoveRange(int chx, int chy){
		if(!showMove)
			return false;
		boolean flag = false;
		if (getCurrentOrientation() == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT) {
			int minr = getLocation().getRow() - getMovementLeft();
			int maxr = getLocation().getRow() + getMovementLeft();
			int minc = getLocation().getCol() - getMovementLeft();
			int maxc = getLocation().getCol() + getMovementLeft();
			if(chx<=maxc && chx>=minc && chy<=maxr && chy>=minr)
				flag = true;
		}
		else {
			int minr = getLocation().getRow() - getMovementLeft();
			int maxr = getLocation().getRow() + getMovementLeft();
			int minc = getLocation().getCol() - getMovementLeft();
			int maxc = getLocation().getCol() + getMovementLeft();
			if(chx<=maxc && chx>=minc && chy<=maxr && chy>=minr)
				flag = true;
		}
		return flag;
	}
	
	public int getMovementLeft() {
		return maxMovement-moved;
	}
	
	public void resetMovement(){
		moved = 0;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int getMaxMovement(){
		return maxMovement;
	}
	
	public int getMoved(){
		return moved;
	}
	
	public void addMovement(int num){
		moved += num;
	}
}
