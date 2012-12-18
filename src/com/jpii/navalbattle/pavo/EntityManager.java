package com.jpii.navalbattle.pavo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.game.entity.Entity;
import com.jpii.navalbattle.util.FileUtils;

public class EntityManager {
	boolean[][] tileAccessor;
	Entity[][] ent;
	World w;
	int counter = 0;
	BufferedImage grid,humanoid;
	public EntityManager(World w) {
		this.w = w;
		ent = new Entity[PavoHelper.getGameWidth(w.getWorldSize())*2][PavoHelper.getGameHeight(w.getWorldSize())*2];
		tileAccessor = new boolean[PavoHelper.getGameWidth(w.getWorldSize())*2][PavoHelper.getGameHeight(w.getWorldSize())*2];
		grid = new BufferedImage(50,50,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = PavoHelper.createGraphics(grid);
		g.setColor(new Color(120,120,120,100));
		g.drawRect(1,1,49,49);
		humanoid = FileUtils.getImage("drawable-game/Other/humanmob.png");
	}
	public Entity getEntity(int r, int c) {
		if (c >= PavoHelper.getGameWidth(w.getWorldSize())*2 ||
				r >= PavoHelper.getGameHeight(w.getWorldSize())*2 || c < 0 || r < 0)
			return null;
		return ent[c][r];
	}
	public Chunk getAssociatedChunk(int r, int c) {
		if (c >= PavoHelper.getGameWidth(w.getWorldSize())*2 ||
				r >= PavoHelper.getGameHeight(w.getWorldSize())*2 || c < 0 || r < 0)
			return null;
		
		return w.getChunk(c/2,r/2);
	}
	public void setEntity(int r, int c, Entity e) {
		if (c >= PavoHelper.getGameWidth(w.getWorldSize())*2 ||
				r >= PavoHelper.getGameHeight(w.getWorldSize())*2 || c < 0 || r < 0)
			return;
		e.setLocation(new Location(r,c));
		ent[c][r] = e;
		int x = c/2;
		int z = r/2;
		Chunk chunk = w.getChunk(x, z);
		int rx = c % 2;
		int rz = r % 2;
		if (rx == 0 && rz == 0)
			chunk.Tile00 = e;
		else if (rx != 0 && rz == 0)
			chunk.Tile10 = e;
		else if (rx == 0 && rz != 0)
			chunk.Tile01 = e;
		else if (rx != 0 && rz != 0)
			chunk.Tile11 = e;
		chunk.needsBufferWrite();
	}
	public boolean isTileFilledWithWater(int r, int c) {
		return tileAccessor[c][r];
	}
	public EntityReference getTypeById(int id) {
		return new EntityReference(counter++,1);
	}
	public BufferedImage getImage(Entity ent) {
		if (ent == null)
			return null;
		BufferedImage ager = null;
		switch (ent.getId()) {
		case 0:
			ager = grid;
			break;
		case 1:
			ager = humanoid;
			break;
		}
		return ager;
	}
	public void AQms03KampOQ9103nmJMs(int snJMkqmd, int cKQK91nm38910JNFEWo, int traKQ91) {
		tileAccessor[cKQK91nm38910JNFEWo][snJMkqmd] = mjMo1091(cKQK91nm38910JNFEWo, traKQ91);
	}
	public boolean mjMo1091(int Tj001, int Uim294) {
		if (Tj001 == Integer.MAX_VALUE || Uim294 == 0) return Boolean.TRUE;
		if (Tj001 == Integer.MIN_VALUE || Uim294 == 1) return Boolean.FALSE;
		return Boolean.FALSE;
	}
	public World getWorld() {
		return w;
	}
}
