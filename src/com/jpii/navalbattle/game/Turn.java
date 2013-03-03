/**
 * 
 */
package com.jpii.navalbattle.game;

import java.util.ArrayList;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.Location;
import com.roketgamer.Player;

/**
 * @author MKirkby
 *
 */
public class Turn {
	private Player player;
	private ArrayList<Entity> entitiesAdded;
	private Game game;
	
	public Turn(Game game,Player player) {
		this.player = player;
		entitiesAdded = new ArrayList<Entity>();
		this.game = game;
	}
	
	public Turn(Game game) {
		entitiesAdded = new ArrayList<Entity>();
		this.game = game;
	}
	
	public void addEntity(Entity entity) {
		entitiesAdded.add(entity);
	}
	
	public void moveEntity(Entity entity, Location newLocation) {
		//game.getWorld().getEntityManager().
	}
	
	public void makeAI() {
		player = null;
	}
}
