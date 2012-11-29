package com.jpii.navalbattle.rendererbeta;

import com.jpii.navalbattle.game.entity.Entity;

public class GameThing extends Renderable {
	Entity ent;
	public GameThing() {
		
	}
	public void render() {
		ent.updateImage();
	}
	public void setEntity(Entity en) {
		ent = en;
	}
	public Entity getEntity() {
		return ent;
	}
}
