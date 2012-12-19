/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.navalbattle.pavo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.game.entity.Entity;
import com.jpii.navalbattle.util.FileUtils;

public class EntityManager {
	int[][] tileAccessor;
	Entity[][] ent;
	World w;
	int counter = 0;
	BufferedImage grid,humanoid;
	public EntityManager(World w) {
		this.w = w;
		ent = new Entity[PavoHelper.getGameWidth(w.getWorldSize())*2][PavoHelper.getGameHeight(w.getWorldSize())*2];
		tileAccessor = new int[PavoHelper.getGameWidth(w.getWorldSize())*2][PavoHelper.getGameHeight(w.getWorldSize())*2];
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
		return tileAccessor[c][r] == 0;
	}
	public int getTilePercentLand(int r, int c) {
		return tileAccessor[c][r] * 100 / 2500;
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
		BufferedImage s = ent.getCustomImage();
		if (s != null)
			ager = s;
		return ager;
	}
	public void AQms03KampOQ9103nmJMs(int snJMkqmd, int cKQK91nm38910JNFEWo, int traKQ91) {
		tileAccessor[cKQK91nm38910JNFEWo][snJMkqmd] = traKQ91;//mjMo1091(cKQK91nm38910JNFEWo, traKQ91);
	}
	/*public boolean mjMo1091(int Tj001, int Uim294) {
		if (Tj001 == Integer.MAX_VALUE || Uim294 == 0) return Boolean.TRUE;
		if (Tj001 == Integer.MIN_VALUE || Uim294 == 1) return Boolean.FALSE;
		return Boolean.FALSE;
	}*/
	public World getWorld() {
		return w;
	}
}
