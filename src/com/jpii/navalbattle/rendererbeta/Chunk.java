package com.jpii.navalbattle.rendererbeta;

import com.jpii.navalbattle.game.entity.EntityManager;
import com.jpii.navalbattle.game.entity.EntityReference;

public class Chunk extends StaticRenderable {
	public char x;
	public char z;
	public EntityReference EntityReference00;
	public EntityReference EntityReference01;
	public EntityReference EntityReference10;
	public EntityReference EntityReference11;
	public Chunk() {
		
	}
	public char getX() {
		return x;
	}
	public char getZ() {
		return z;
	}
	public void setX(char x) {
		this.x = x;
	}
	public void setZ(char z) {
		this.z = z;
	}
	public void render() {
		
	}
	public void update() {
		EntityManager.getInstance().getEntityByReference(EntityReference00).update();
		EntityManager.getInstance().getEntityByReference(EntityReference01).update();
		EntityManager.getInstance().getEntityByReference(EntityReference10).update();
		EntityManager.getInstance().getEntityByReference(EntityReference11).update();
	}
}
