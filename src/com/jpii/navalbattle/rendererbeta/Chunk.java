package com.jpii.navalbattle.rendererbeta;

import com.jpii.navalbattle.game.entity.EntityManager;
import com.jpii.navalbattle.game.entity.EntityReference;

public class Chunk extends StaticRenderable {
	public int x;
	public int z;
	public EntityReference EntityReference00;
	public EntityReference EntityReference01;
	public EntityReference EntityReference10;
	public EntityReference EntityReference11;
	private World w;
	byte[][] data;
	public Chunk(World w) {
		this.w = w;
	}
	public int getX() {
		return x;
	}
	public int getZ() {
		return z;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public void render() {
		buffer = ChunkRenderer.genChunk(w, getX()*100, getZ()*100, 100,100);
	}
	public void update() {
		EntityManager.getInstance().getEntityByReference(EntityReference00).update();
		EntityManager.getInstance().getEntityByReference(EntityReference01).update();
		EntityManager.getInstance().getEntityByReference(EntityReference10).update();
		EntityManager.getInstance().getEntityByReference(EntityReference11).update();
	}
}
