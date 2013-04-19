package com.jpii.navalbattle.game.turn;

import com.jpii.navalbattle.game.entity.MoveableEntity;

public class Turn {
	private Player player;
	
	public Turn(Player player) {
		this.player = player;
		startTurn();
	}
	
	public boolean canmoveEntity(MoveableEntity entity) {
		boolean flag=true;
		if(flag)
			flag = (player.myEntity(entity));
		if(flag)
			flag = !(entity.getMoved()>=entity.getMaxMovement());
		return flag;
	}
	
	public boolean canFireGuns(MoveableEntity entity) {
		boolean flag=true;
		if(flag)
			flag = (player.myEntity(entity));
		if(flag)
			flag = !entity.getUsedGuns();
		return flag;
	}
	
	public boolean canFireMissiles(MoveableEntity entity) {
		boolean flag=true;
		if(flag)
			flag = (player.myEntity(entity));
		if(flag)
			flag = !entity.getUsedMissiles();
		return flag;
	}
	
	public void startTurn(){
		player.startTurn();
	}
	
	public void takeTurn(){
		player.takeTurn();
	}
	
	public void endTurn(){
		player.endTurn();
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public boolean isDone(){
		return player.isTurnOver();
	}
}
