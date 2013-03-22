package com.jpii.navalbattle.game;

import com.jpii.navalbattle.game.entity.MoveableEntity;

public class Turn {
	private Player player;
	
	public Turn(Player player) {
		this.player = player;
	}
	
	public boolean canmoveEntity(MoveableEntity entity) {
		boolean flag=false;
		if(!flag)
			flag = player.myEntity(entity);
		if(!flag)
			flag = (entity.getMoved()>entity.getMaxMovement());
		return flag;
	}
	
	public Player getPlayer(){
		return player;
	}
}
