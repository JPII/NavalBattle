package com.jpii.navalbattle.pavo.grid;

import java.io.Serializable;

import maximusvladimir.dagen.Rand;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;

public class GridHelper implements Serializable {
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
			r = random.nextInt(PavoHelper.getGameHeight(man.getWorld().getWorldSize()));
			c = random.nextInt(PavoHelper.getGameHeight(man.getWorld().getWorldSize()));
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
		return pollNextWaterTile(8);
	}
}
