package com.jpii.navalbattle.game.turn;

import com.jpii.navalbattle.gui.MainMenuWindow;
import com.jpii.navalbattle.pavo.grid.Entity;

public class TurnManager {
	
	Turn currentTurn;
	public int turnnumber;
	public byte playernumber;
	PlayerManager players;
	
	public TurnManager(PlayerManager pm){
		players = pm;
		turnnumber=0;
		currentTurn = new Turn(players.getPlayer(turnnumber));
	}
	
	public void nextTurn(){
		currentTurn.endTurn();
		turnnumber++;
		currentTurn = new Turn(players.getPlayer(turnnumber));
		currentTurn.takeTurn();
		if(currentTurn.isDone()){
			nextTurn();
		}
	}
	
	public Turn getTurn(){
		return currentTurn;
	}

	public int getTurnNumber(){
		return turnnumber;
	}
	
	public void addEntity(Entity e,Player p){
		p.addEntity(e);
	}
	
	public Player getPlayer(int pos){
		return players.getPlayer(pos-1);
	}
	
	public Player findPlayer(Entity b){
		for(int index = 0; index<players.players.size(); index++){
			if(players.players.get(index).myEntity(b))
				return players.players.get(index);
		}
		return null;
	}
	
	public void removeEntity(Entity e){
		for(int index = 0; index<players.players.size(); index++){
			players.players.get(index).removeEntity(e);				
		}
	}
	
	public void checkDone(){
		boolean flag = false;
		
		for(int index = 0; index<players.players.size(); index++){
			if(!flag)
				flag = doesPlayerHaveEntities(players.getPlayer(index));
			if(!flag)
				flag = doesPlayerHavePort(players.getPlayer(index));		
		}
		
		MainMenuWindow.spg.getStageManager().checkForCompletion(flag);
	}
	
	private boolean doesPlayerHaveEntities(Player p){
		return p.ownsEntity();
	}
	
	private boolean doesPlayerHavePort(Player p){
		return p.ownsPort();
	}
}
