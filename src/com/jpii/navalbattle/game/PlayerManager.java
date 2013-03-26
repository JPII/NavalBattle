package com.jpii.navalbattle.game;

import java.util.ArrayList;

public class PlayerManager {
	
	ArrayList<Player> players;
	
	public PlayerManager(){
		players = new ArrayList<Player>();
	}
	
	public PlayerManager(ArrayList<Player> players){
		this.players = players;
	}
	
	public PlayerManager(Player... players){
		this.players = new ArrayList<Player>();
		for(int index = 0; index<players.length; index++){
			this.players.add(players[index]);
		}
	}
	
	public Player nextTurn(int turnnumber){
		Player temp = getPlayer(turnnumber);
		temp.nextTurn();
		return temp;
	}
	
	public void addPlayer(Player p){
		players.add(p);
	}
	
	public Player getPlayer(int current){
		while(!(current<players.size()))
			current-=players.size();
		return players.get(current);
	}
	
}
