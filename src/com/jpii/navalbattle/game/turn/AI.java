package com.jpii.navalbattle.game.turn;

import java.util.ArrayList;

import com.jpii.navalbattle.game.NavalGame;
import com.jpii.navalbattle.game.NavalManager;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.GridHelper;
import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.util.GrammarManager;
import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.game.entity.PortEntity;
import com.jpii.navalbattle.game.entity.Submarine;
import com.jpii.navalbattle.game.turn.DamageCalculator;

public class AI extends Player{
	
	NavalManager nm;
	ArrayList<Entity> primaryEnemies;
	ArrayList<Entity> secondaryEnemies;
	
	
	public AI(NavalManager nm,String name) {
		super(getNewName());
		primaryEnemies = new ArrayList<Entity>();
		secondaryEnemies = new ArrayList<Entity>();
		this.nm = nm;
	}
	
	private static String getNewName() {
		return GrammarManager.generateFullName(Game.Settings.rand.nextInt());
	}
	
	public void addEnemyEntityP(Entity e){
		primaryEnemies.add(e);
	}

	public void addEnemyEntityS(Entity e){
		secondaryEnemies.add(e);
	}
	
	
	public void takeTurn(){
		for(int k = 0; k < getTotalEntities(); k++)
		{
			Entity ent = getEntity(k);
			MoveableEntity currentEntity;
			if(ent.getHandle()%10 == 1){
				currentEntity = (MoveableEntity)ent;
				if(currentEntity.getHandle()==11){
					//Sub
					moveAIShip(currentEntity);
					if(!diplomacy){
					determineCurrentEnemiesP(currentEntity);
					if(currentEntity.getMissileCount()>0)
					determineCurrentEnemiesS(currentEntity);
					organizeMoveableEnemiesHP(primaryEnemies);
					organizeMoveableEnemiesHP(secondaryEnemies);
					attackEnemies(1, currentEntity);
					}
			
				}
				if(currentEntity.getHandle()==21){
					//AC
				moveAIShip(currentEntity);
				if(!diplomacy){
				determineCurrentEnemiesP(currentEntity);
				determineCurrentEnemiesS(currentEntity);
				organizeMoveableEnemiesHP(primaryEnemies);
				organizeMoveableEnemiesHP(secondaryEnemies);
				attackEnemies(2, currentEntity);
				}
				}
				if(currentEntity.getHandle()==31){
					//BS
					moveAIShip(currentEntity);
					if(!diplomacy){
					determineCurrentEnemiesP(currentEntity);
					if(currentEntity.getMissileCount()>0)
					determineCurrentEnemiesS(currentEntity);
					organizeMoveableEnemiesHP(primaryEnemies);
					organizeMoveableEnemiesHP(secondaryEnemies);
					attackEnemies(3, currentEntity);
					}
				}
			}
		}
		diplomacyCounter--;
		if(diplomacyCounter == 0)
			diplomacy = false;
		turnOver=true;

	}

	public void attackEnemies(int n, MoveableEntity currentEntity)
	{
		
		if(pickEnemyP(n)!=-1)
		primaryAttack(n, currentEntity);
		if(pickEnemyS(n)!=-1)
		secondaryAttack(n, currentEntity);
		
		secondaryEnemies.clear();
		primaryEnemies.clear();
	}
	public void primaryAttack(int n, MoveableEntity currentEntity )
	{
		
		Entity ene = primaryEnemies.get(pickEnemyP(n));
		if(ene.getHandle()==2){
			PortEntity enemyEntity;
			enemyEntity = (PortEntity)ene;
			DamageCalculator.doPrimaryDamage(currentEntity, enemyEntity);
		}
		else{
		MoveableEntity enemyEntity;
		enemyEntity = (MoveableEntity)ene;
		DamageCalculator.doPrimaryDamage(currentEntity, enemyEntity);
		}
	}
	
	public void secondaryAttack(int n, MoveableEntity currentEntity)
	{
		Entity ene = secondaryEnemies.get(pickEnemyS(n));
		if(ene.getHandle()==2){
			PortEntity enemyEntity;
			enemyEntity = (PortEntity)ene;
			DamageCalculator.doSecondaryDamage(currentEntity, enemyEntity);
		}
		else{
		MoveableEntity enemyEntity;
		enemyEntity = (MoveableEntity)ene;
		DamageCalculator.doSecondaryDamage(currentEntity, enemyEntity);
		}
	}
	private void organizeMoveableEnemiesHP(ArrayList<Entity> Enemy){		
		Entity temp;
		for (int p = 1; p < Enemy.size(); p++){			
			for (int q = 0; q < Enemy.size()-1; q++){
				if(getHealth(Enemy.get(q))>getHealth(Enemy.get(q+1))){
					temp = Enemy.get(q);
					Enemy.set(q,Enemy.get(q+1));
					Enemy.set((q+1), temp);
				}
			}
		}
	}
	
	private void determineDiplomacy(){
			int diplomacyLevel = 0;
			for(int k = 0; k < getTotalEntities(); k++)
			{
				if(getHealth(getEntity(k))>70 && diplomacyLevel < 2){
					diplomacyLevel = 1;
				}
				else if(getHealth(getEntity(k))<70 && getHealth(getEntity(k)) >40 && diplomacyLevel < 3){
					diplomacyLevel = 2;
				}
				else{
					diplomacyLevel = 3;
				}
			}
			
			if(this.getScore() > 9000)
			{
				if(diplomacyLevel > 0)
					diplomacy = true;
				
				if(diplomacyLevel == 3){
					this.subtractscore(8000);
					diplomacyCounter = 5;
				}
				else if (diplomacyLevel == 2){
					this.subtractscore(2000);
					diplomacyCounter = 3;
				}
				else if (diplomacyLevel == 1){
					this.subtractscore(500);
					diplomacyCounter = 1;
				}
			}
			else if(this.getScore() > 3000){
				if(diplomacyLevel > 0)
					diplomacy = true;
				
				if (diplomacyLevel == 2 || diplomacyLevel == 3){
					this.subtractscore(2000);
					diplomacyCounter = 3;
				}
				else if (diplomacyLevel == 1){
					this.subtractscore(500);
					diplomacyCounter = 1;
				}
				
			}
			
			else if(this.getScore() > 1500){
				if(diplomacyLevel > 0){
					diplomacy = true;
					this.subtractscore(500);
					diplomacyCounter = 1;
				}
				
			}
			
			
			//long diplomacy cost: 8000 5 turns
			//medium diplomacy cost: 2000 3 turns
			//short diplomacy cost: 500 1 turn
		
			
	}
	
	private int getHealth(Entity e){
		if(e.getHandle()==2)
			return ((PortEntity)e).getPercentHealth();
		if(e.getHandle()%10==1)
			return ((MoveableEntity)e).getPercentHealth();
		else{
			return -1;
		}
	}
	
	public int pickEnemyS(int currentShip)
	{

		if(!secondaryEnemies.isEmpty()){
			
			for(int k = 0; k < secondaryEnemies.size(); k++){
				if(secondaryEnemies.get(k).getHandle()==2)
					return k;
			}
		switch (currentShip) {
	      case 1:	for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==21)
							return k;
	      				}
	      			for(int k = 0; k < secondaryEnemies.size(); k++){
	      				if(secondaryEnemies.get(k).getHandle()==31)
	      					return k;
	      				}
	      			for(int k = 0; k < secondaryEnemies.size(); k++){
	      				if(secondaryEnemies.get(k).getHandle()==11)
	      					return k;
	      				}
	      			
	      case 2:	for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==31)
							return k;
						}
					for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==21)
							return k;
						}
					for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==11)
							return k;
						}
						
	      case 3:	for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==11)
							return k;
						}
					for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==31)
							return k;
						}
					for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==21)
							return k;
						}
     			
		}
	}
		return -1;
	}
	
	public int pickEnemyP(int currentShip)
	{

		if(!primaryEnemies.isEmpty()){
			for(int k = 0; k < primaryEnemies.size(); k++){
				if(primaryEnemies.get(k).getHandle()==2)
					return k;
			}
		switch (currentShip) {
	      case 1:	for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==21)
							return k;
	      				}
	      			for(int k = 0; k < primaryEnemies.size(); k++){
	      				if(primaryEnemies.get(k).getHandle()==31)
	      					return k;
	      				}
	      			for(int k = 0; k < primaryEnemies.size(); k++){
	      				if(primaryEnemies.get(k).getHandle()==11)
	      					return k;
	      				}
	      			
	      case 2:	for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==31)
							return k;
						}
					for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==21)
							return k;
						}
					for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==11)
							return k;
						}
						
	      case 3:	for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==11)
							return k;
						}
					for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==31)
							return k;
						}
					for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==21)
							return k;
						}
     			
		}
	}
		return -1;
	}
	
	public void moveAIShip(MoveableEntity e){
		int topX = e.getLocation().getCol()-e.getMovementLeft()+1;	   
		int topY = e.getLocation().getRow()-e.getMovementLeft()+1;
		int currentX=topX;
		int currentY=topY;
		e.toggleMoveable();
		//delay!
	do
		{	
			currentX=topX;
			currentY=topY;
			currentX += (int) (Math.random()*((e.getMovementLeft() * 2) + 1));
			currentY += (int) (Math.random()*((e.getMovementLeft() * 2) + 1));
		}
		while(!GridHelper.canMoveTo(e.getManager(), e, e.getCurrentOrientation(), currentY, currentX,e.getWidth()));
		e.toggleMoveable();
		e.moveTo(new Location(currentY,currentX));
		//delay
	}
	public void determineCurrentEnemiesS(MoveableEntity e){
		int topX = (e.getLocation().getCol()-e.getSecondaryRange())+1;	   
		int topY = (e.getLocation().getRow()-e.getSecondaryRange())+1;	 		
		for (int x = topX; x < (e.getLocation().getCol()+e.getSecondaryRange())+1; x++) {
			for (int y = topY; y < (e.getLocation().getRow()+e.getSecondaryRange())+1; y++) {
				Entity location = e.getManager().findEntity(y,x);
				if(location!=null){
					Player temp = NavalGame.getManager().getTurnManager().findPlayer(location); 
					if (temp!=null){
					if(!(temp.equals(this))&&!secondaryEnemies.contains(location)){
						//entity at spot is not owned by this AI
						if(location.getHandle()==11) {
							if(!((Submarine)location).isSumberged()){
								addEnemyEntityS(location);
							}
						}
						else {
							addEnemyEntityS(location);
						}

					}
					}
				}
			}
		}
	}
	
	public void determineCurrentEnemiesP(MoveableEntity e){
		int topX = (e.getLocation().getCol()-e.getPrimaryRange())+1;	   
		int topY = (e.getLocation().getRow()-e.getPrimaryRange())+1;	 		
		for (int x = topX; x < (e.getLocation().getCol()+e.getPrimaryRange())+1; x++) {
			for (int y = topY; y < (e.getLocation().getRow()+e.getPrimaryRange())+1; y++) {
				Entity location = e.getManager().findEntity(y,x);
				if(location!=null){
					Player temp = NavalGame.getManager().getTurnManager().findPlayer(location); 
					if (temp!=null){
					if(!(temp.equals(this))&&!primaryEnemies.contains(location)){
						//entity at spot is not owned by this AI
						if(location.getHandle()==11) {
							if(!((Submarine)location).isSumberged()){
								addEnemyEntityS(location);
							}
						}
						else{
							addEnemyEntityS(location);
						}
					}
					}
				}
			}
		}
	}
	
	
	public void endTurn(){
		super.endTurn();
	}
}
