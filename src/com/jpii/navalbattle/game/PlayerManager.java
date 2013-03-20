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
	
	public void addPlayer(Player p){
		players.add(p);
	}
	
	public Player getPlayer(int current){
		while(current>=players.size())
			current-=players.size();
		return players.get(current);
	}
	
}
