package com.jpii.navalbattle.game;

import java.util.ArrayList;

import com.jpii.navalbattle.pavo.grid.Entity;

public class Player {
	
	ArrayList<Entity> entities;
	
	public Player(){
		entities = new ArrayList<Entity>();
	}
	
	public boolean myEntity(Entity e){
		return entities.contains(e);
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
}
