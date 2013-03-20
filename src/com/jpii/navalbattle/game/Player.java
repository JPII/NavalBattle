package com.jpii.navalbattle.game;

import java.util.ArrayList;

import com.jpii.navalbattle.pavo.grid.Entity;

// base class for HumanPlayers and AIPlayers
public class Player {
	
	ArrayList<Entity> entities;
	
	public Player(){
		entities = new ArrayList<Entity>();
	}
	
	public boolean myEntity(Entity e){
		return entities.contains(e);
	}
	
}
