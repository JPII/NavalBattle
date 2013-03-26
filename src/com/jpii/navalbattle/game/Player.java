package com.jpii.navalbattle.game;

import java.util.ArrayList;

import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.pavo.grid.Entity;

public class Player {
	
	ArrayList<Entity> entities;
	
	public Player(){
		entities = new ArrayList<Entity>();
	}
	
	public void nextTurn(){
		resetMovement();
	}
	
	public void resetMovement(){
		for (int index =0; index<entities.size(); index++){
			Entity e = entities.get(0);
			if(e.getHandle()==1){
				((MoveableEntity)e).resetMovement();
				System.out.println("reset: "+((MoveableEntity)e).getMoved());
			}
		}
	}
	
	public boolean myEntity(Entity e){
		System.out.println(entities.contains(e));
		return entities.contains(e);
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
}
