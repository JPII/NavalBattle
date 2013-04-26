package com.jpii.navalbattle.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import maximusvladimir.dagen.Rand;

import com.jpii.navalbattle.game.NavalGame;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.ProceduralLayeredMapGenerator;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridHelper;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;

public class PortEntity extends AnimatedEntity {
	private static final long serialVersionUID = 1L;
	BufferedImage icon;
	private int maxHealth;
	private int currentHealth;
	
	/**
	 * @param em
	 * @param loc
	 * @param orientation
	 * @param team
	 * @param animationFrameIds
	 */
	public PortEntity(EntityManager em, Location loc, byte orientation) {
		super(em, loc, orientation, generatePort(em,loc));
		icon = em.getImage(em.getTile(loc));
		this.setAlternatingDirection(false);
		handle = 2;
		maxHealth = 2500;
		currentHealth = maxHealth;
	}
	
	public BufferedImage getIcon() {
		return icon;
	}
	
	public void onUpdate(long tickTime) {
		super.onUpdate(tickTime);
		if (tickTime % 6 == 0) {
			updateFrame();
		}
	}
	
	private static GridedEntityTileOrientation[] generatePort(EntityManager man, Location loc) {
		BufferedImage flash1 = null, flash2 = null, flash3 = null, flash4 = null;
		
		flash1 = new BufferedImage(50,50,BufferedImage.TYPE_4BYTE_ABGR);
		flash2 = new BufferedImage(50,50,BufferedImage.TYPE_4BYTE_ABGR);
		flash3 = new BufferedImage(50,50,BufferedImage.TYPE_4BYTE_ABGR);
		flash4 = new BufferedImage(50,50,BufferedImage.TYPE_4BYTE_ABGR);
		
		Graphics2D g1 = PavoHelper.createGraphics(flash1);
		Graphics2D g2 = PavoHelper.createGraphics(flash2);
		Graphics2D g3 = PavoHelper.createGraphics(flash3);
		Graphics2D g4 = PavoHelper.createGraphics(flash4);
		
		Rand randy = new Rand(Game.Settings.seed + 50);
		int numStructures = randy.nextInt(6,10);
		int counter = 0;
		float wlx = loc.getCol() * 16;//((loc.getCol() / 2)/32.666666666666666666f);
		float wlz = loc.getRow() * 16;//((loc.getRow() / 2)/32.666666666666666666f);
		while (counter < numStructures) {
			counter++;
			int type = randy.nextInt(0,3);
			int lx = randy.nextInt(4,40);
			int ly = randy.nextInt(4,35);
			if (type == 2) {
				float h = ProceduralLayeredMapGenerator.getPoint(wlx+((lx*50)/16),wlz+((ly*50)/16));
				float h1 = 0;
				float h2 = 0;
				int tries = 0;
				while (h > 0.56 && h2 > 0.56 && h1 > 0.56 && tries < 20) {
					lx = randy.nextInt(10,39);
					ly = randy.nextInt(19,40);
					tries++;
					h = ProceduralLayeredMapGenerator.getPoint(wlx+((lx*50)/16),wlz+((ly*50)/16));
					h1 = ProceduralLayeredMapGenerator.getPoint(wlx+(((lx-8)*50)/16),wlz+((ly*50)/16));
					h2 = ProceduralLayeredMapGenerator.getPoint(wlx+(((lx-8)*50)/16),wlz+((ly*50)/16));
				}
				g1.setColor(new Color(126,105,65));
				g2.setColor(new Color(126,105,65));
				g3.setColor(new Color(126,105,65));
				g4.setColor(new Color(126,105,65));
				g1.fillRect(lx - 10, ly - 4, 20, 8);
				g2.fillRect(lx - 10, ly - 4, 20, 8);
				g3.fillRect(lx - 10, ly - 4, 20, 8);
				g4.fillRect(lx - 10, ly - 4, 20, 8);
				g1.setColor(new Color(53,45,28));
				g2.setColor(new Color(53,45,28));
				g3.setColor(new Color(53,45,28));
				g4.setColor(new Color(53,45,28));
				g1.drawRect(lx - 10, ly - 4, 20, 8);
				g2.drawRect(lx - 10, ly - 4, 20, 8);
				g3.drawRect(lx - 10, ly - 4, 20, 8);
				g4.drawRect(lx - 10, ly - 4, 20, 8);
				g2.setColor(new Color(140,140,140,175));
				g3.setColor(new Color(200,200,200,100));
				g4.setColor(new Color(255,255,255,75));
				g2.fillOval(lx, ly - 7, 3,3);
				g3.fillOval(lx-1, ly - 9, 4,4);
				g4.fillOval(lx-2, ly - 13, 5,5);
			}
			else {
				g1.setColor(Color.orange);
				g1.fillRect(lx,ly,6,4);
				g2.setColor(Color.yellow);
				g2.fillRect(lx,ly,5,4);
				g3.setColor(Color.pink);
				g3.fillRect(lx,ly,4,4);
				g4.setColor(Color.red);
				g4.fillRect(lx,ly,5,4);
			}
		}
		
		int fl1 = 
				man.registerEntity(PavoHelper.imgUtilOutline(flash1,Game.Settings.GridColor),
						GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int fl2 = 
				man.registerEntity(PavoHelper.imgUtilOutline(flash2,Game.Settings.GridColor),
						GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int fl3 = 
				man.registerEntity(PavoHelper.imgUtilOutline(flash3,Game.Settings.GridColor),
						GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int fl4 = 
				man.registerEntity(PavoHelper.imgUtilOutline(flash4,Game.Settings.GridColor),
						GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		GridedEntityTileOrientation w1 = new GridedEntityTileOrientation();
		w1.setLeftToRightImage(fl1);
		w1.setTopToBottomImage(fl1);
		GridedEntityTileOrientation w2 = new GridedEntityTileOrientation();
		w2.setLeftToRightImage(fl2);
		w2.setTopToBottomImage(fl2);
		GridedEntityTileOrientation w3 = new GridedEntityTileOrientation();
		w3.setLeftToRightImage(fl3);
		w3.setTopToBottomImage(fl3);
		GridedEntityTileOrientation w4 = new GridedEntityTileOrientation();
		w4.setLeftToRightImage(fl4);
		w4.setTopToBottomImage(fl4);
		return new GridedEntityTileOrientation[] {w1,w2,w3,w4};
	}
	
	public void spawnBattleship(){
		Location to = spawnAt(4); //static way of accessing width?
		if(GridHelper.canPlaceInGrid(getManager(),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, to.getRow(), to.getCol(), 4))
			NavalGame.getManager().getTurnManager().addEntity(new BattleShip(getManager(), to, GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT),NavalGame.getManager().getTurnManager().findPlayer(this));
		else if(GridHelper.canPlaceInGrid(getManager(),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, to.getRow(), to.getCol(), 4))
			NavalGame.getManager().getTurnManager().addEntity(new BattleShip(getManager(), to,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM),NavalGame.getManager().getTurnManager().findPlayer(this));
	}
	
	public void spawnSubmarine(){
		Location to = spawnAt(2); //static way of accessing width?
		if(GridHelper.canPlaceInGrid(getManager(),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, to.getRow(), to.getCol(), 2))
			NavalGame.getManager().getTurnManager().addEntity(new Submarine(getManager(), to, GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT),NavalGame.getManager().getTurnManager().findPlayer(this));
		else if(GridHelper.canPlaceInGrid(getManager(),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, to.getRow(), to.getCol(), 2))
			NavalGame.getManager().getTurnManager().addEntity(new Submarine(getManager(), to,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM),NavalGame.getManager().getTurnManager().findPlayer(this));
	}
	
	public void spawnAC(){
		Location to = spawnAt(5); //static way of accessing width?
		if(GridHelper.canPlaceInGrid(getManager(),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, to.getRow(), to.getCol(), 5))
			NavalGame.getManager().getTurnManager().addEntity(new AircraftCarrier(getManager(), to, GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT),NavalGame.getManager().getTurnManager().findPlayer(this));
		else if(GridHelper.canPlaceInGrid(getManager(),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, to.getRow(), to.getCol(), 5))
			NavalGame.getManager().getTurnManager().addEntity(new AircraftCarrier(getManager(), to,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM),NavalGame.getManager().getTurnManager().findPlayer(this));
	}	
	
	public void repair(){
		currentHealth = maxHealth;
	}
	
	public boolean takeDamage(int damage){
		boolean flag = false; // used when returning if changed teams
		currentHealth-=damage;
		if(currentHealth <= 0){
			flag = true; // changed teams;
			repair();
		}
		return flag;
	}
	
	private Location spawnAt(int width){
		int currentx, currenty, tempx, tempy;
		int length = 0;
		currentx = tempx = getLocation().getCol();
		currenty = tempy = getLocation().getRow();
		
		while(!meetsSpawningCondition(currentx, currenty, width)){
			length++;
			for(int k = 1; k<=length; k++){
				tempx=currentx-k;
				if(meetsSpawningCondition(tempx, tempy, width))
					return new Location(tempy,tempx);
			}
			currentx = tempx;
			for(int k = 1; k<=length; k++){
				tempy=currenty-k;
				if(meetsSpawningCondition(tempx, tempy, width))
					return new Location(tempy,tempx);
			}
			currenty = tempy;
			
			length++;
			for(int k = 1; k<=length; k++){
				tempx=currentx+k;
				if(meetsSpawningCondition(tempx, tempy, width))
					return new Location(tempy,tempx);
			}
			currentx = tempx;
			for(int k = 1; k<=length; k++){
				tempy=currenty+k;
				if(meetsSpawningCondition(tempx, tempy, width))
					return new Location(tempy,tempx);
			}
			currenty = tempy;
		}
		
		return new Location(currenty,currentx);
	}
	
	private boolean meetsSpawningCondition(int x, int y, int width){
		boolean flag = (GridHelper.canPlaceInGrid(getManager(), GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, y, x, width) ||
				GridHelper.canPlaceInGrid(getManager(), GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, y, x, width) );
		System.out.println("placed? "+flag+" "+new Location(y,x));
		return flag;
	}
	
	public int getPercentHealth(){
		return (int)((double)currentHealth/(double)maxHealth*100.0);
	}
}
