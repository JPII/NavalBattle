package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.GridHelper;
import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.pavo.grid.Tile;
import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;
import com.jpii.navalbattle.turn.TurnManager;

public class HUD extends PWindow{
	
	TurnManager tm;
	GradientPaint gp;
	Entity display;
	boolean pinned = true;
	RightHud right;
	MidHud mid;
	LeftHud left;

	 public HUD(NewWindowManager parent,TurnManager tm,int x, int y, int width, int height){
		super(parent, x, y, width, height);
		this.tm = tm;
		System.out.println(tm);
		right = new RightHud(width,height);
		mid = new MidHud(this,tm);
		left = new LeftHud(height);
		gp = new GradientPaint(0,0,new Color(96,116,190),0,height,new Color(0,0,54));
		setTitleVisiblity(false);
		setVisible(false);
		update();
	 }
	 
	 
	
	public void paint(Graphics2D g) {
		super.paint(g);
		g.setPaint(gp);
		g.fillRect(0,0,getWidth(),getHeight());
		if(right!=null&&mid!=null&&left!=null){
			right.draw(g);
			mid.draw(g);
			left.draw(g);
		}
		
		if(tm!=null)
			if(tm.getTurn()!=null)
				if(tm.getTurn().getPlayer()!=null)
					g.drawString(""+tm.getTurn().getPlayer().name,(width/3)+25,25);
	}
	
	public void setEntity(Entity e){
		if(display!=null){
			if(e==null || !display.equals(e)){
				if(display.getHandle()==1){
					MoveableEntity display = (MoveableEntity)this.display;
					if(display.isMovableTileBeingShown()){
						display.toggleMovable();
					}
					if(display.isAttackTileBeingShown()){
						display.toggleAttackRange();
					}
				}
			}
		}
		display = e;
		right.setEntity(e);
		mid.setEntity(e);
		update();
	}
	
	public void update(){
		right.update();
		mid.update();
		if(display != null){
			setVisible(true);
			if(display.getHandle()==1){
				MoveableEntity display = (MoveableEntity)this.display;
				if(display.isMovableTileBeingShown()){
					display.toggleMovable();
					display.toggleMovable();
				}
				if(display.isAttackTileBeingShown()){
					display.toggleAttackRange();
					display.toggleAttackRange();
				}					
			}
		}
		else if(pinned)
			setVisible(true);
		else
			setVisible(false);
		repaint();
	}
	
	public boolean isShowingMove(){
		boolean flag = false;
		if(display!=null)
			if(display.getHandle() == 1)
				flag = ((MoveableEntity) display).isMovableTileBeingShown();
		return flag;
	}
	
	public boolean isShowingAttack(){
		boolean flag = false;
		if(display!=null)
			if(display.getHandle() == 1)
				flag = ((MoveableEntity) display).isAttackTileBeingShown();
		return flag;
	}
	
	public boolean hudClick(int x, int y, boolean leftclick){
		if(moveShip(x,y,leftclick))
			return true;
		if(attackGuns(x,y,leftclick))
			return true;
		if(attackMissile(x,y,leftclick))
			return true;
		return false;
	}
	
	private boolean moveShip(int x, int y, boolean leftclick){	
		if(!isShowingMove())
			return false;

		MoveableEntity display = (MoveableEntity)this.display;			
		
		if(!tm.getTurn().canmoveEntity(display))
			return false;

		int startr = display.getLocation().getRow();
		int startc = display.getLocation().getCol();
		if(leftclick && GridHelper.canMoveTo(display.getManager(), display, display.getCurrentOrientation(), y, x,display.getWidth())){
			if(display.isMovableTileBeingShown()){
				display.toggleMovable();
			}
			display.moveTo(new Location(y,x));
			addEvent("Moving ship from ("+startr+","+startc+") to ("+display.getLocation().getRow()+","+display.getLocation().getCol()+")");
			int rowchange = Math.abs(startr - (display.getLocation().getRow())); 
			int colchange = Math.abs(startc - (display.getLocation().getCol()));
			if(rowchange>=colchange)
				display.addMovement(rowchange);
			else
				display.addMovement(colchange);
			update();
			return true;
		}
		else if(GridHelper.canMoveTo(display.getManager(), display, display.getOppositeOrientation(), y, x,display.getWidth())){
			if(display.isMovableTileBeingShown()){
				display.toggleMovable();
			}
			display.moveTo(new Location(y,x),display.getOppositeOrientation());
			addEvent("Moving ship from ("+startr+","+startc+") to ("+display.getLocation().getRow()+","+display.getLocation().getCol()+")");
			int rowchange = Math.abs(startr - (display.getLocation().getRow())); 
			int colchange = Math.abs(startc - (display.getLocation().getCol()));
			if(rowchange>=colchange)
				display.addMovement(rowchange);
			else
				display.addMovement(colchange);
			update();
			return true;
		}
		return false;
	}
	
	private boolean attackGuns(int x, int y, boolean leftclick){
		if(!mid.attackGuns)
			return false;
		if(!isShowingAttack())
			return false;
		MoveableEntity display = (MoveableEntity)this.display;			
		
		if(!tm.getTurn().canFireGuns(display))
			return false;
		int startr = display.getLocation().getRow();
		int startc = display.getLocation().getCol();
		Tile<Entity> temp = display.getManager().getTile(y,x);
		if(temp!=null){
			Entity e = temp.getEntity();
			if(e.getHandle()==1){
			MoveableEntity there = (MoveableEntity)e;
				if(tm.getTurn().getPlayer().myEntity(there)){
					System.out.println("You can;t attack your own team");
					return false;
				}
			}
		}
		if(leftclick && GridHelper.canAttackTo(display.getManager(), display, y, x)){
			if(display.isAttackTileBeingShown()){
				display.toggleAttackRange();
			}
			addEvent("Gunning ship from ("+startr+","+startc+") to ("+y+","+x+")");
			display.useGuns();
			update();
			return true;
		}
		return false;
	}
	
	private boolean attackMissile(int x, int y, boolean leftclick){
		if(!mid.attackMissiles)
			return false;
		
		if(!isShowingAttack())
			return false;

		MoveableEntity display = (MoveableEntity)this.display;			
		
		if(!tm.getTurn().canFireMissiles(display))
			return false;

		int startr = display.getLocation().getRow();
		int startc = display.getLocation().getCol();
		Tile<Entity> temp = display.getManager().getTile(y,x);
		if(temp!=null){
			Entity e = temp.getEntity();
			if(e.getHandle()==1){
			MoveableEntity there = (MoveableEntity)e;
				if(tm.getTurn().getPlayer().myEntity(there)){
					System.out.println("You can;t attack your own team");
					return false;
				}
			}
		}
		if(leftclick && GridHelper.canAttackTo(display.getManager(), display, y, x)){
			if(display.isAttackTileBeingShown()){
				display.toggleAttackRange();
			}
			addEvent("Tomahawk ship from ("+startr+","+startc+") to ("+y+","+x+")");
			display.useMissiles();
			update();
			return true;
		}
		return false;
	}
	
	public void addEvent(String s){
		
	}
	
	public void togglePinable(){
		pinned = !pinned;
		update();
	}
	
}