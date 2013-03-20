package com.jpii.navalbattle.game;

import com.jpii.navalbattle.pavo.grid.Entity;

public class TurnManager {
	
	Turn currentTurn;
	public int turnnumber;
	PlayerManager players;
	
	public TurnManager(PlayerManager pm){
		players = pm;
		turnnumber=0;
		currentTurn = new Turn(players.getPlayer(turnnumber));
	}
	
	public void nextTurn(){
		turnnumber++;
		currentTurn = new Turn(players.getPlayer(turnnumber));
	}
	
	public Turn getTurn(){
		return currentTurn;
	}

	public int getTurnNumber(){
		return turnnumber;
	}
	
	public void addEntity(Entity e){
		currentTurn.getPlayer().addEntity(e);
	}
	
}
