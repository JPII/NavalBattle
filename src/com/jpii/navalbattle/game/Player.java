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
			MoveableEntity e = (MoveableEntity) entities.get(0);
			if(e.getHandle()==1){
				if(e.getMoved()>0){
					System.out.println("resetfrom: "+e.getMoved());
					e.resetMovement();
					System.out.println("reset: "+e.getMoved());
				}
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
