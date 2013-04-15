package com.jpii.navalbattle.turn;

import java.util.ArrayList;

import com.jpii.navalbattle.game.NavalManager;
import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.Location;

public class AI extends Player{
	
	NavalManager nm;
	ArrayList<Entity> enemies;
	
	public AI(NavalManager nm,String name) {
		super(name);
		enemies = new ArrayList<Entity>();
		this.nm = nm;
	}
	
	public void addEntity(Entity e){
		enemies.add(e);
	}
	
	
	public void takeTurn(){
		for(int k = 0; k < getTotalEntities(); k++)
		{
			Entity ent = getEntity(k);
			MoveableEntity currentEntity = (MoveableEntity)ent;
		}
		turnOver=true;
	}
	
	public void endTurn(){
		super.endTurn();
	}
}
