package com.jpii.navalbattle.rendererbeta;

import com.jpii.navalbattle.game.entity.EntityManager;
import com.jpii.navalbattle.game.entity.EntityReference;

public class Chunk extends Renderable {
	public int x;
	public int z;
	public EntityReference EntityReference00;
	public EntityReference EntityReference01;
	public EntityReference EntityReference10;
	public EntityReference EntityReference11;
	private boolean generated = false;
	byte[][] data;
	public Chunk() {
		data = new byte[200][200];
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

	}
	public boolean isRendered() {
		return generated;
	}
	public void setData(byte[][] data) {
		this.data = data;
	}
	public byte[][] getData() {
		return data;
	}
	public void setDataPoint(int x, int z, byte val) {
		if (x < 200 && z < 200 && x > 0 && z > 0)
			data[x][z] = val;
	}
	public void setDataPoint(int x, int z, double d) {
		if (d > 1)
			d = 1;
		if (d < 0)
			d = 0;
		setDataPoint(x,z,(byte)(d*255));
	}
	public byte getDataPoint(int x, int z) {
		if (x < 200 && z < 200 && x > 0 && z > 0)
			return data[x][z];
		else
			return 0;
	}
	public void update() {
		EntityManager.getInstance().getEntityByReference(EntityReference00).update();
		EntityManager.getInstance().getEntityByReference(EntityReference01).update();
		EntityManager.getInstance().getEntityByReference(EntityReference10).update();
		EntityManager.getInstance().getEntityByReference(EntityReference11).update();
	}
}
