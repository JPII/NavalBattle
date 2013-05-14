package com.jpii.navalbattle.game.turn;

import com.jpii.navalbattle.data.RoketGamerData;
import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.util.RoketUtils;

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
		if(player.name.equals("Player 1"))
			if(player.score >= 10000)
				RoketUtils.submitAchievement(RoketGamerData.ACHIEVEMENT_WAR_BONDS);
		
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
