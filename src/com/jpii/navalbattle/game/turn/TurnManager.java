package com.jpii.navalbattle.game.turn;

import com.jpii.navalbattle.game.StageManager;
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
		StageManager sm = MainMenuWindow.spg.getStageManager();
		for(int index = 0; index<players.players.size(); index++){
			if(!flag){
				flag = (!doesPlayerHaveEntities(players.getPlayer(index)));
				sm.checkForCompletion(flag,index);
			}
			if(!flag){
				flag = (!doesPlayerHavePort(players.getPlayer(index)));
				sm.checkForCompletion(flag,index);
			}
		}
	}
	
	private boolean doesPlayerHaveEntities(Player p){
		System.out.println(p.ownsEntity());
		return p.ownsEntity();
	}
	
	private boolean doesPlayerHavePort(Player p){
		System.out.println(p.ownsPort());
		return p.ownsPort();
	}
}
