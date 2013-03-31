package com.jpii.navalbattle.game;

import java.util.ArrayList;

import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.pavo.grid.Entity;

public class Player {
	
	ArrayList<Entity> entities;
	public String name;
	
	public Player(String name){
		entities = new ArrayList<Entity>();
		this.name = name;
	}
	
	public void nextTurn(){
		
	}
	
	public void reset(){
		resetMovement();
	}
	
	public void resetMovement(){
		for (int index =0; index<entities.size(); index++){
			Entity e1 = entities.get(index);
			if(e1.getHandle()==1){
				MoveableEntity e = (MoveableEntity) e1;
				e.resetMovement();
			}
		}
	}
	
	public int myEntity(Entity e){
		return entities.indexOf(e);
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
}
