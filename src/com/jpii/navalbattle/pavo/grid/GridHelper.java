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
	public GridHelper(EntityManager eman) {
		random = Game.Settings.rand;
		man = eman;
	}
	public Location pollNextLiquidSpace(int amountOfWater, int tolerance) {
		boolean found = false;
		int r = 0, c = 0;
		while (!found) {
			r = random.nextInt(PavoHelper.getGameHeight(man.getWorld().getWorldSize())*2);
			c = random.nextInt(PavoHelper.getGameWidth(man.getWorld().getWorldSize())*2);
			int b = man.getTilePercentLand(r, c);
			if (b > amountOfWater - tolerance && b < amountOfWater + tolerance)
				found = true;
		}
		return new Location(r,c);
	}
	public Location pollNextWaterTile(int tolerance) {
		return pollNextLiquidSpace(0, tolerance);
	}
	public Location pollNextWaterTile() {
		return pollNextWaterTile(Game.Settings.waterThresholdBarrier);
	}
	/**
	 * 
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
				if (p > Game.Settings.waterThresholdBarrier) {
					flag = false;
					break;
				}
				Tile temp = em.getTile(row,col+c);
				if(temp!=null) {
					flag=false;
					break;
				}
			}
		}
		if (rotateto == GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM) {
			for (int c = 0; c < width; c++) {
				int p = em.getTilePercentLand(row-c,col);
				if (p > Game.Settings.waterThresholdBarrier) {
					flag = false;
					break;
				}
				Tile temp = em.getTile(row-c,col);
				if(temp!=null) {
					flag=false;
					break;
				}
			}
		}
		return flag;
	}
	
	public static boolean canRotate(EntityManager em,Entity e,byte rotateto, int row, int col, int width) {
		boolean flag = true;
		if (rotateto == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT) {
			for (int c = 0; c < width; c++) {
				int p = em.getTilePercentLand(row-(width-1),col+c);
				if (p > Game.Settings.waterThresholdBarrier) {
					flag = false;
					break;
				}
				Tile temp = em.getTile(row-(width-1),col+c);
				if(temp!=null&&!temp.getEntity().equals(e)){
					flag=false;
					break;
				}
			}
		}
		if (rotateto == GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM) {
			for (int c = 0; c < width; c++) {
				int p = em.getTilePercentLand(row-c,col);
				if (p > Game.Settings.waterThresholdBarrier) {
					flag = false;
					break;
				}
				Tile temp = em.getTile(row-c,col);
				if(temp!=null&&!temp.getEntity().equals(e)){
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
				if(!e.isInMoveRange(col+c,row))
					return false;
				if (p > Game.Settings.waterThresholdBarrier)
					return false;
				Tile temp = em.getTile(row,col+c);
				if(temp!=null&&!temp.getEntity().equals(e))
					return false;
			}
		}
		if (position == GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM) {
			for (int c = 0; c < width; c++) {
				int p = em.getTilePercentLand(row-c,col);
				if(!e.isInMoveRange(col,row-c))
					return false;
				if (p > Game.Settings.waterThresholdBarrier)
					return false;
				Tile temp = em.getTile(row-c,col);
				if(temp!=null&&!temp.getEntity().equals(e))
					return false;
			}
		}
		return true;
	}
}
