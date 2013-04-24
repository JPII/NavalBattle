package com.jpii.navalbattle.game.turn;

import java.util.ArrayList;

public class PlayerManager {
	
	ArrayList<Player> players;
	int count = 0;
	
	public PlayerManager(Player... players){
		this.players = new ArrayList<Player>();
		for(int index = 0; index<players.length; index++){
			this.players.add(players[index]);
			count++;
			players[index].setPlayerNumber(count);
			players[index].setTeamColor((byte)(PlayerManager.getPlayerColor(count)-56));
		}
	}
	
	public void reset(int turnnumber){
		Player temp = getPlayer(turnnumber);
		temp.reset();
	}
	
	public void addPlayer(Player p){
		players.add(p);
	}
	
	public Player getPlayer(int current){
		while(!(current<players.size()))
			current-=players.size();
		return players.get(current);
	}
	
	public static byte getPlayerColor(int num){
		return (byte)num;
	}
	
	
}
