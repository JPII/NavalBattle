package com.jpii.navalbattle.pavo;

import java.awt.image.BufferedImage;

import com.jpii.navalbattle.game.entity.Entity;

public class EntityManager {
	boolean[][] tileAccessor;
	Entity[][] ent;
	World w;
	int counter = 0;
	public EntityManager(World w) {
		this.w = w;
		ent = new Entity[PavoHelper.getGameWidth(w.getWorldSize())*2][PavoHelper.getGameHeight(w.getWorldSize())*2];
		tileAccessor = new boolean[PavoHelper.getGameWidth(w.getWorldSize())*2][PavoHelper.getGameHeight(w.getWorldSize())*2];
	}
	public Entity getEntity(int r, int c) {
		return ent[c][r];
	}
	public void setEntity(int r, int c, Entity e) {
		ent[c][r] = e;
		int x = c/2;
		int z = c/2;
		
	}
	public boolean isTileFilledWithWater(int r, int c) {
		return tileAccessor[c][r];
	}
	public EntityReference getTypeById(int id) {
		return new EntityReference(counter++,1);
	}
	public BufferedImage getImage(EntityReference ref) {
		return null;
	}
	public void AQms03KampOQ9103nmJMs(int snJMkqmd, int cKQK91nm38910JNFEWo, int traKQ91) {
		tileAccessor[cKQK91nm38910JNFEWo][snJMkqmd] = mjMo1091(cKQK91nm38910JNFEWo, traKQ91);
	}
	public boolean mjMo1091(int Tj001, int Uim294) {
		if (Tj001 == Integer.MAX_VALUE || Uim294 == 0) return Boolean.TRUE;
		if (Tj001 == Integer.MIN_VALUE || Uim294 == 1) return Boolean.FALSE;
		return Boolean.FALSE;
	}
}
