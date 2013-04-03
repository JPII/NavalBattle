package com.jpii.navalbattle.game;

import com.jpii.navalbattle.game.entity.MoveableEntity;

public class Turn {
	private Player player;
	
	public Turn(Player player) {
		this.player = player;
		startTurn();
	}
	
	public boolean canmoveEntity(MoveableEntity entity) {
		boolean flag=true;
		if(flag){
			System.out.println("ship is mine?" + player.myEntity(entity));
			flag = (player.myEntity(entity));
		}
		if(flag)
			flag = !(entity.getMoved()>=entity.getMaxMovement());
		return flag;
	}
	
	public boolean canFireGuns(MoveableEntity entity) {
		boolean flag=true;
		if(flag){
			System.out.println("ship is mine?" + player.myEntity(entity));
			flag = (player.myEntity(entity));
		}
		if(flag)
			flag = entity.getUsedGuns();
		return flag;
	}
	
	public boolean canFireMissiles(MoveableEntity entity) {
		boolean flag=true;
		if(flag){
			System.out.println("ship is mine?" + player.myEntity(entity));
			flag = (player.myEntity(entity));
		}
		if(flag)
			flag = entity.getUsedMissiles();
		return flag;
	}
	
	public void startTurn(){
		player.startTurn();
	}
	
	public void endTurn(){
		player.endTurn();
	}
	
	public Player getPlayer(){
		return player;
	}
}
