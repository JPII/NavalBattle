package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.pavo.grid.Tile;

public class MoveableEntity extends Entity {
	private static final long serialVersionUID = 1L;
	protected int maxStep = 4;
	protected static int maxMovement;
	protected static int moved;
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
			for (int x = 0; x < (maxStep * 2) + getWidth(); x++) {
				for (int y = 0; y < (maxStep * 2) + 1; y++) {
					int r = (y + getLocation().getRow()) - (((maxStep * 2) + 1)/2);
					int c = (x + getLocation().getCol()) - (maxStep);
					if (r >= 0 && c >= 0) {
						Tile temp = getManager().getTile(r,c);
						if (getManager().getTilePercentLand(r,c) <= Game.Settings.waterThresholdBarrier && temp==null) {
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
			for (int x = 0; x < (maxStep * 2) + 1; x++) {
				for (int y = 0; y < (maxStep * 2) + getWidth(); y++) {
					int c = (x + getLocation().getCol()) - (((maxStep * 2) + 1)/2);
					int r = (y + getLocation().getRow()) - (((maxStep*2)-1));
					if (r >= 0 && c >= 0) {
						Tile temp = getManager().getTile(r,c);
						if (getManager().getTilePercentLand(r,c) <= Game.Settings.waterThresholdBarrier && temp==null) {
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
			int minr = getLocation().getRow() - maxStep;
			int maxr = getLocation().getRow() + maxStep;
			int minc = getLocation().getCol() - maxStep;
			int maxc = getLocation().getCol() + maxStep+getWidth()-1;
			if(chx<=maxc && chx>=minc && chy<=maxr && chy>=minr)
				flag = true;
		}
		else {
			int minr = getLocation().getRow() - maxStep-getWidth()+1;
			int maxr = getLocation().getRow() + maxStep;
			int minc = getLocation().getCol() - maxStep;
			int maxc = getLocation().getCol() + maxStep;
			if(chx<=maxc && chx>=minc && chy<=maxr && chy>=minr)
				flag = true;
		}
		return flag;
	}
	
	public int getMaxStep() {
		return maxStep;
	}
	
	public void setMaxStep(int v) {
		maxStep = v;
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
}
