package com.jpii.navalbattle.game;

import com.jpii.navalbattle.pavo.grid.Entity;

public class TurnManager {
	
	Turn currentTurn;
	public int turnnumber;
	public byte playernumber;
	PlayerManager players;
	
	public TurnManager(PlayerManager pm){
		players = pm;
		turnnumber=1;
		currentTurn = new Turn(players.getPlayer(turnnumber));
	}
	
	public void nextTurn(){
		players.reset(turnnumber);
		turnnumber++;
		currentTurn = new Turn(players.nextTurn(turnnumber));
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
