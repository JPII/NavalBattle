package com.jpii.navalbattle.pavo.grid;

import java.io.Serializable;

import maximusvladimir.dagen.Rand;

import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;

public class GridHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	EntityManager man;
	Rand random;
	public GridHelper(long randomSeed,EntityManager eman) {
		random = new Rand(randomSeed);
		man = eman;
	}
	public Location pollNextLiquidSpace(int amountOfWater, int tolerance) {
		boolean found = false;
		int r = 0, c = 0, count = 0;
		while (!found&&count<20) {
			count++;
			r = random.nextInt(PavoHelper.getGameHeight(man.getWorld().getWorldSize())*2);
			c = random.nextInt(PavoHelper.getGameWidth(man.getWorld().getWorldSize())*2);
			int b = man.getTilePercentLand(r, c);
			if (b > amountOfWater - tolerance && b < amountOfWater + tolerance)
				found = true;
		}
		if(count>=20){
			System.out.println("There was an error finding a LiquidSpace");
			return Location.Unknown;
		}
		return new Location(r,c);
	}
	public Location pollNearLocation(Location l) {
		Location ln = new Location(random.nextInt(-4, 4)+l.getRow(), random.nextInt(-4,4)+l.getCol());
		boolean flag = Location.isValid(ln,man);
		if (flag)
			System.out.println("Invalid location selected.");
		return ln;
	}
	public Location pollNextWaterTile(int tolerance) {
		return pollNextLiquidSpace(0, tolerance);
	}
	public Location pollNextWaterTile() {
		return pollNextWaterTile(Game.Settings.waterThresholdBarrier);
	}
	
	public Location pollNextShoreTile() {
		boolean found = false;
		int r = 0, c = 0, count=0;
		while (!found&&count<20) {
			count++;
			r = random.nextInt(PavoHelper.getGameHeight(man.getWorld().getWorldSize())*2);
			c = random.nextInt(PavoHelper.getGameWidth(man.getWorld().getWorldSize())*2);
			int b = man.getTilePercentLand(r, c);
			if (b > 10 && b < 70)
				found = true;
		}
		if(count>=20){
			System.out.println("There was an error finding a ShoreSpace");
			return getRandomCorner();
		}
		return new Location(r,c);
	}
	
	public Location getRandomCorner(){
		int maxr = PavoHelper.getGameHeight(man.getWorld().getWorldSize())*2-1;
		int maxc = PavoHelper.getGameWidth(man.getWorld().getWorldSize())*2-1;
		Location NW=new Location(0,0),NE=new Location(0,maxr),SW=new Location(maxr,maxc),SE=new Location(maxr,0);
		NW = getClosestLocation(NW,0);
		NE = getClosestLocation(NE,0);
		SW = getClosestLocation(SW,0);
		SE = getClosestLocation(SE,0);
		switch(random.nextInt(1,4)){
			case 1: return NW;
			case 2: return NE;
			case 3: return SE;
			default: return SW;
		}
	}
	
	public Location getClosestLocation(Location l,int distance){
		if(!Location.isValid(l, man) || distance>5)
			return Location.Unknown;
		Location flag = Location.Unknown;
		Location[] list = new Location[9];
		if(!testShoreTile(l)){			
			list[0] = getClosestLocation(l.getAdjacentLocation(Location.NORTHWEST),distance+1);
			list[1] = getClosestLocation(l.getAdjacentLocation(Location.NORTH),distance+1);
			list[2] = getClosestLocation(l.getAdjacentLocation(Location.NORTHEAST),distance+1);
			list[3] = getClosestLocation(l.getAdjacentLocation(Location.WEST),distance+1);
			list[4] = getClosestLocation(l.getAdjacentLocation(Location.EAST),distance+1);
			list[5] = getClosestLocation(l.getAdjacentLocation(Location.SOUTHWEST),distance+1);
			list[6] = getClosestLocation(l.getAdjacentLocation(Location.SOUTH),distance+1);
			list[7] = getClosestLocation(l.getAdjacentLocation(Location.SOUTHEAST),distance+1);
		}
		else{
			flag = l;
		}
		double leastDistance = 1000.0;
		
		for(int index = 0; index<list.length; index++){
			if(list[index]!=null){
				if(Location.isValid(list[index],man)){
					if(list[index].getDistanceFrom(l)<leastDistance){
						leastDistance = list[index].getDistanceFrom(l);
						flag = list[index];
					}	
				}
			}
		}		
		return flag;
	}
	private boolean testShoreTile(Location l){
		boolean flag = false;
		if(man.getTile(l)!=null)
			;
		else
			flag = true;
		return flag;
		
	}
	
	
	/**
	 * !USE WHEN ENTITY DOES NOT EXIST PREVIOUSLY!
	 * @param em - needed to get Tile Percent Land to check for if land is in the way
	 * @param rotate - needed to check which direction your checking for
	 * @param row - needed to find the starting row
	 * @param col - needed to find the starting col
	 * @param width - needed to know how many spaces to check
	 * @return - returns true if the space(s) allow for this entity
	 */
	public static boolean canPlaceInGrid(EntityManager em,byte rotateto, int row, int col, int width) {
		boolean flag = true;
		if (rotateto == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT) {
			for (int c = 0; c < width; c++) {
				int p = em.getTilePercentLand(row,col+c);
				if(row<0||col+c<0)
					return false;
				if(col+c>=PavoHelper.getGameWidth(em.getWorld().getWorldSize())*2)
					return false;
				if (p > Game.Settings.waterThresholdBarrier) {
					flag = false;
					break;
				}
				Tile<Entity> temp = em.getTile(row,col+c);
				if(temp!=null) {
					flag=false;
					break;
				}
			}
		}
		if (rotateto == GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM) {
			for (int c = 0; c < width; c++) {
				int p = em.getTilePercentLand(row-c,col);
				if(row-c<0||col<0)
					return false;
				if (p > Game.Settings.waterThresholdBarrier) {
					flag = false;
					break;
				}
				Tile<Entity> temp = em.getTile(row-c,col);
				if(temp!=null) {
					flag=false;
					break;
				}
			}
		}
		return flag;
	}
	
	public static boolean canMoveTo(EntityManager em,MoveableEntity e,byte position, int row, int col, int width) {
		if (position == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT) {
			for (int c = 0; c < width; c++) {
				int p = em.getTilePercentLand(row,col+c);
				if(col+c>=PavoHelper.getGameWidth(em.getWorld().getWorldSize())*2)
					return false;
				if(!e.isInMoveRange(col,row)){
					return false;
				}
				if (p > Game.Settings.waterThresholdBarrier){
					return false;
				}
				Tile<Entity> temp = em.getTile(row,col+c);
				if(!(temp==null||temp.getEntity().equals(e))){
					return false;
				}
			}
		}
		if (position == GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM) {
			for (int c = 0; c < width; c++) {
				int p = em.getTilePercentLand(row-c,col);
				if(row-c<0)
					return false;
				if(!e.isInMoveRange(col,row))
					return false;
				if (p > Game.Settings.waterThresholdBarrier)
					return false;
				Tile<Entity> temp = em.getTile(row-c,col);
				if(temp!=null&&!temp.getEntity().equals(e))
					return false;
			}
		}
		return true;
	}
	
	public static boolean canAttackPrimaryTo(EntityManager em,MoveableEntity e, int row, int col) {
		if(!e.isInPrimaryRange(col,row)){
			return false;
		}
		Tile<Entity> temp = em.getTile(row,col);
		if((temp==null||temp.getEntity().equals(e))){
			return false;
		}
		return true;
	}
	
	public static boolean canAttackSecondaryTo(EntityManager em,MoveableEntity e, int row, int col) {
		if(!e.isInSecondaryRange(col,row)){
			return false;
		}
		Tile<Entity> temp = em.getTile(row,col);
		if((temp==null||temp.getEntity().equals(e))){
			return false;
		}
		return true;
	}
}
