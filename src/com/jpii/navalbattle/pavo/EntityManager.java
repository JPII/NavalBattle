package com.jpii.navalbattle.pavo;

import java.awt.image.BufferedImage;

import com.jpii.navalbattle.game.entity.Entity;

public class EntityManager {
	boolean[][] tileAccessor;
	World w;
	int counter = 0;
	public EntityManager(World w) {
		this.w = w;
		tileAccessor = new boolean[PavoHelper.getGameWidth(w.getWorldSize())][PavoHelper.getGameHeight(w.getWorldSize())];
	}
	public Entity getEntity(int r, int c) {
		return null;
	}
	public boolean isTileFilledWithWater(int r, int c) {
		return tileAccessor[r][c];
	}
	public EntityReference getTypeById(int id) {
		return new EntityReference(counter++,1);
	}
	public BufferedImage getImage(EntityReference ref) {
		return null;
	}
}
