package com.jpii.navalbattle.pavo;

import com.jpii.navalbattle.game.entity.Entity;

public class EntityManager {
	boolean[][] tileAccessor;
	WorldSize ws;
	public EntityManager(WorldSize worlysize) {
		ws = worlysize;
		tileAccessor = new boolean[PavoHelper.getGameWidth(ws)][PavoHelper.getGameHeight(ws)];
	}
	public Entity getEntity(int r, int c) {
		return null;
	}
	public boolean isTileFilledWithWater(int r, int c) {
		return tileAccessor[r][c];
	}
}
