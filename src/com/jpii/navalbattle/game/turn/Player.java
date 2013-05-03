package com.jpii.navalbattle.game.turn;

import java.util.ArrayList;

import com.jpii.navalbattle.game.NavalGame;
import com.jpii.navalbattle.game.NavalManager;
import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.pavo.grid.Entity;

public class Player {
	
	ArrayList<Entity> entities;
	public String name;
	protected boolean turnOver;
	int score;
	byte color;
	int playernumber;
	int diplomacyCounter = 0;
	boolean diplomacy = false;
	
	public Player(String name){
		entities = new ArrayList<Entity>();
		this.name = name;
		turnOver = false;
		score = 0;
	}
	
	public void setPlayerNumber(int pnum){
		playernumber = pnum;
	}
	
	public void setTeamColor(byte b){
		color = b;
	}
	
	public void startTurn(){
		
	}
	
	public void takeTurn(){
		reset();
	}
	
	public void endTurn(){
		
	}
	
	public void reset(){
		resetMovement();
		resetAttack();
		if(entities.size()>0&&entities.get(0)!=null)
		((NavalManager)entities.get(0).getManager()).getGame().getHud().update();
	}
	
	public void resetMovement(){
		for (int index =0; index<entities.size(); index++){
			Entity e1 = entities.get(index);
			if(e1.getHandle()%10 == 1){
				MoveableEntity e = (MoveableEntity) e1;
				e.resetMovement();
			}
		}
	}
	
	public void resetAttack(){
		for (int index =0; index<entities.size(); index++){
			Entity e1 = entities.get(index);
			if(e1.getHandle()%10 == 1){
				MoveableEntity e = (MoveableEntity) e1;
				e.resetAttack();
			}
		}
	}
	
	public boolean myEntity(Entity e){
		return entities.contains(e);
	}
	
	public void addEntity(Entity e){
		entities.add(e);
		e.setTeamColor(color);
	}
	
	public Entity getEntity(int index) {
		return entities.get(index);
	}
	
	public boolean isTurnOver(){
		return turnOver;
	}
	
	public int getTotalEntities() {
		return entities.size();
	}
	
	public void addscore(int add){
		score +=add;
	}
	
	public void setscore(int set){
		score = set;
	}
	
	public void subtractscore(int sub){
		score -=sub;
	}
	
	public int getScore(){
		return score;
	}
	
	public void removeEntity(Entity e){
		while(entities.remove(e))
			;
	}
	
	public void nextEntity(Entity e){
		Entity temp = null;
		if(e==null){
			temp = entities.get(0);
		}
		else{
			if(entities.contains(e)){
				int index = entities.indexOf(e)+1;
				if(index>=entities.size())
					index -= entities.size();
				temp = entities.get(index);
			}
			else{
				temp = entities.get(0);
			}
		}
		if(temp == null)
			return;
		temp.getManager().getWorld().animatedSetLoc(temp.getLocation(),0.054392019f);
		((NavalGame)temp.getManager().getWorld().getGame()).getHud().setEntity(temp);
	}

	public boolean ownsEntity() {
		for(int index = 0; index<entities.size();index++){
			if(entities.get(index).getHandle()%10 == 1)
				return true;
		}
		return false;
	}

	public boolean ownsPort() {		
		for(int index = 0; index<entities.size();index++){
			if(entities.get(index).getHandle() == 2)
				return true;
		}		
		return false;
	}
}
