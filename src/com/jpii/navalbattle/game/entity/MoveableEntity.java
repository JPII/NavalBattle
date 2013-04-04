package com.jpii.navalbattle.game.entity;

import java.awt.Color;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;
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
	protected int attackRange;
	private boolean showMove = false;
	private boolean showAttack = false;
	private boolean usedGuns = false;
	private boolean usedMissiles = false;
	public boolean gunsAttackOption = false;
	public boolean missileAttackOption = false;
	public boolean planeAttackOption = false;
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
	
	public boolean isAttackTileBeingShown() {
		return showAttack;
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
					int r = getRLR(y);
					int c = getCLR(x);
					if (r >= 0 && c >= 0) {
						if(isPossibleMoveChoiceLR(x,y)){
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
						if (isPossibleMoveChoiceTB(x,y)) {
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
	
	public void toggleAttackRange(){
		short good = PavoHelper.getByteFromColor(new Color(165,42,42));
		short bad = (short)0x2f1d;
		if(showAttack){
			showAttack = false;
			good = bad = 0;
		}
		else{
			showAttack = true;
		}
		
		if (getCurrentOrientation() == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT) {
			for (int x = 0; x < (getAttackRange() * 2) + 1; x++) {
				for (int y = 0; y < (getAttackRange() * 2) + 1; y++) {
					int r = getAttackR(y);
					int c = getAttackC(x);
					if (r >= 0 && c >= 0) {
						if(isPossibleAttackChoice(c,r)){
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
			for (int x = 0; x < (getAttackRange() * 2) + 1; x++) {
				for (int y = 0; y < (getAttackRange() * 2) + 1; y++) {
					int c = (x + getLocation().getCol()) - (((getAttackRange() * 2) + 1)/2);
					int r = (y + getLocation().getRow()) - (getAttackRange());
					if (r >= 0 && c >= 0) {
						if (isPossibleAttackChoice(x,y)) {
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
	
	public int getRLR(int y)
	{
		return (y + getLocation().getRow()) - (((getMovementLeft() * 2) + 1)/2);
	}
	
	public int getCLR(int x)
	{
		return (x + getLocation().getCol()) - (getMovementLeft());
	}
	
	public int getAttackR(int y)
	{
		return (y + getLocation().getRow()) - (((getAttackRange() * 2) + 1)/2);
	}
	
	public int getAttackC(int x)
	{
		return (x + getLocation().getCol()) - (getAttackRange());
	}
	
	public Tile<?> getTileLR(int x, int y)
	{
		Tile<?> temps = getManager().getTile(getRLR(y),getCLR(x));
		return temps;
	}
	
	public boolean isPossibleMoveChoiceLR(int x, int y)
	{
		boolean horizontal = true;
		boolean vertical = true;
		for(int p = 0 ; p < getWidth(); p++){
			if( ((getTileLR(x+p,y)!=null) && !getTileLR(x+p,y).getEntity().equals(this)) || (getManager().getTilePercentLand(getRLR(y),getCLR(x+p)) > Game.Settings.waterThresholdBarrier)){
				horizontal = false;
			}
		}
		
		for(int q = 0 ; q < getWidth(); q++){
			if( ((getTileLR(x,y-q)!=null) && !getTileLR(x,y-q).getEntity().equals(this)) || (getManager().getTilePercentLand(getRLR(y-q),getCLR(x)) > Game.Settings.waterThresholdBarrier)){
				vertical = false;
			}
		}
			
		return (horizontal == true || vertical == true);
	}
	
	public int getRTB(int y)
	{
		return (y + getLocation().getRow()) - (getMovementLeft());
	}
	
	public int getCTB(int x)
	{
		return (x + getLocation().getCol()) - (((getMovementLeft() * 2) + 1)/2);
	}
	public Tile<?> getTileTB(int x, int y)
	{
		Tile<?> temps = getManager().getTile(getRTB(y),getCTB(x));
		return temps;
	}
	
	public boolean isPossibleMoveChoiceTB(int x, int y)
	{		
		boolean horizontal = true;
		boolean vertical = true;
		for(int p = 0 ; p < getWidth(); p++){
			if((getTileTB(x+p,y)!=null)  && !getTileTB(x+p,y).getEntity().equals(this) || (getManager().getTilePercentLand(getRTB(y),getCTB(x+p)) > Game.Settings.waterThresholdBarrier)){
			horizontal = false;
			}
		}
		
		for(int q = 0 ; q < getWidth(); q++){
			if((getTileTB(x,y-q)!=null)  && !getTileTB(x,y-q).getEntity().equals(this) || (getManager().getTilePercentLand(getRTB(y-q),getCLR(x)) > Game.Settings.waterThresholdBarrier)){
				vertical = false;
			}
		}
			
		return (horizontal == true || vertical == true);
	}
	
	public boolean isPossibleAttackChoice(int x, int y)
	{
		boolean good = false;
		if(getManager().getTile(y,x)!=null )
			good = true;
		return good;
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
	
	public boolean isInAttackRange(int chx, int chy){
		if(!showAttack)
			return false;
		boolean flag = false;
		if (getCurrentOrientation() == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT) {
			int minr = getLocation().getRow() - getAttackRange();
			int maxr = getLocation().getRow() + getAttackRange();
			int minc = getLocation().getCol() - getAttackRange();
			int maxc = getLocation().getCol() + getAttackRange();
			if(chx<=maxc && chx>=minc && chy<=maxr && chy>=minr)
				flag = true;
		}
		else {
			int minr = getLocation().getRow() - getAttackRange();
			int maxr = getLocation().getRow() + getAttackRange();
			int minc = getLocation().getCol() - getAttackRange();
			int maxc = getLocation().getCol() + getAttackRange();
			if(chx<=maxc && chx>=minc && chy<=maxr && chy>=minr)
				flag = true;
		}
		return flag;
	}
	
	public int getMovementLeft() {
		return maxMovement-moved;
	}
	
	public int getAttackRange(){
		return attackRange;
	}
	
	public void resetMovement(){
		moved = 0;
	}
	
	public void resetAttack(){
		usedGuns=false;
		usedMissiles=false;
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
	
	public boolean getUsedGuns(){
		return usedGuns;
	}
	
	public boolean getUsedMissiles(){
		return usedMissiles;
	}
	
	public void addMovement(int num){
		moved += num;
	}
	
	public void useGuns(){
		usedGuns=true;
	}
	
	public void useMissiles(){
		usedMissiles=true;
	}
}
