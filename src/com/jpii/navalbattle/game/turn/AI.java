package com.jpii.navalbattle.game.turn;

import java.util.ArrayList;

import com.jpii.navalbattle.game.NavalGame;
import com.jpii.navalbattle.game.NavalManager;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.GridHelper;
import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.game.turn.DamageCalculator;

public class AI extends Player{
	
	NavalManager nm;
	ArrayList<Entity> enemies;
	
	public AI(NavalManager nm,String name) {
		super(name);
		enemies = new ArrayList<Entity>();
		this.nm = nm;
	}
	
	public void addEnemyEntity(Entity e){
		enemies.add(e);
	}

	public void takeTurn(){
		for(int k = 0; k < getTotalEntities(); k++)
		{
			Entity ent = getEntity(k);
			MoveableEntity currentEntity;
			if(ent.getHandle()%10 == 1){
				currentEntity = (MoveableEntity)ent;
				System.out.println("my Location is... "+currentEntity.getLocation());
				if(currentEntity.getHandle()==11){
					//Sub
					System.out.println("I am a submarine");
					moveAIShip(currentEntity);
					determineCurrentEnemies(currentEntity);
					attackEnemies(1, currentEntity);
			
				}
				if(currentEntity.getHandle()==21){
					//AC
					System.out.println("I am a aircraft");
					moveAIShip(currentEntity);
				determineCurrentEnemies(currentEntity);
				attackEnemies(2, currentEntity);
				}
				if(currentEntity.getHandle()==31){
					//BS
					System.out.println("I am a battleship");
					moveAIShip(currentEntity);
					determineCurrentEnemies(currentEntity);
					attackEnemies(3, currentEntity);
				}
			}
		}
		
		turnOver=true;
		enemies.clear();
	}

	public void attackEnemies(int n, MoveableEntity currentEntity)
	{
		
		if(pickEnemy(n)!=-1){
		//primaryAttack(n, currentEntity); not done
		secondaryAttack(n, currentEntity);
		}
		enemies.clear();
	}
	public void primaryAttack(int n, MoveableEntity currentEntity )
	{
		int count = 0;
		int size = 0;
		boolean finish = false;
		MoveableEntity enemyEntity;
		MoveableEntity enemyEntity1;
		
		currentEntity.togglePrimaryRange();
		
		Entity enes = enemies.get(size);
		enemyEntity1 = (MoveableEntity)enes;
		Entity ene = enemies.get(pickEnemy(n));
		enemyEntity = (MoveableEntity)ene;
		
		if(GridHelper.canAttackPrimaryTo(currentEntity.getManager(), currentEntity, ene.getLocation().getRow(), ene.getLocation().getCol())){
			DamageCalculator.doPrimaryDamage(currentEntity, enemyEntity1);
			finish = true;
		}
			
		if(!finish){
		do{
			
			if(size < enemies.size())
			{
			enes = enemies.get(size);
			enemyEntity1 = (MoveableEntity)enes;
			}
			count++;
			size++;
			if(size == enemies.size())
				count = 123;
				
		}
		while(count != 123 || !GridHelper.canAttackPrimaryTo(currentEntity.getManager(), currentEntity, enemyEntity1.getLocation().getRow(), enemyEntity1.getLocation().getCol()));
		DamageCalculator.doPrimaryDamage(currentEntity, enemyEntity1);
		}
		currentEntity.togglePrimaryRange();
	}
	
	public void secondaryAttack(int n, MoveableEntity currentEntity)
	{
		Entity ene = enemies.get(pickEnemy(n));
		MoveableEntity enemyEntity;
		enemyEntity = (MoveableEntity)ene;
		DamageCalculator.doSecondaryDamage(currentEntity, enemyEntity);
	}
	
	public int pickEnemy(int currentShip)
	{
		//always prioritize to attack ports
		if(!enemies.isEmpty()){
		/*	
			for(int k = 0; k < enemies.size(); k++){
				if(enemies.get(k).getHandle()==2)
					return k;
			}*/
		switch (currentShip) {
	      case 1:	for(int k = 0; k < enemies.size(); k++){
						if(enemies.get(k).getHandle()==21)
							return k;
	      				}
	      			for(int k = 0; k < enemies.size(); k++){
	      				if(enemies.get(k).getHandle()==31)
	      					return k;
	      				}
	      			for(int k = 0; k < enemies.size(); k++){
	      				if(enemies.get(k).getHandle()==11)
	      					return k;
	      				}
	      			
	      case 2:	for(int k = 0; k < enemies.size(); k++){
						if(enemies.get(k).getHandle()==31)
							return k;
						}
					for(int k = 0; k < enemies.size(); k++){
						if(enemies.get(k).getHandle()==21)
							return k;
						}
					for(int k = 0; k < enemies.size(); k++){
						if(enemies.get(k).getHandle()==11)
							return k;
						}
						
	      case 3:	for(int k = 0; k < enemies.size(); k++){
						if(enemies.get(k).getHandle()==11)
							return k;
						}
					for(int k = 0; k < enemies.size(); k++){
						if(enemies.get(k).getHandle()==31)
							return k;
						}
					for(int k = 0; k < enemies.size(); k++){
						if(enemies.get(k).getHandle()==21)
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
		System.out.println("I am moving to ..."+new Location(currentY,currentX));
		e.toggleMoveable();
		e.moveTo(new Location(currentY,currentX));
		//delay
	}
	public void determineCurrentEnemies(MoveableEntity e){
		int topX = (e.getLocation().getCol()-e.getSecondaryRange())+1;	   
		int topY = (e.getLocation().getRow()-e.getSecondaryRange())+1;	 		
		for (int x = topX; x < (e.getLocation().getCol()+e.getSecondaryRange())+1; x++) {
			for (int y = topY; y < (e.getLocation().getRow()+e.getSecondaryRange())+1; y++) {
				Entity location = e.getManager().findEntity(y,x);
				if(location!=null){
					Player temp = NavalGame.getManager().getTurnManager().findPlayer(location); 
					if (temp!=null){
					if(!(temp.equals(this))&&!enemies.contains(location)){
						//entity at spot is not owned by this AI
						System.out.println("Enemies that are next to me with Location: "+ location.getLocation() );
						addEnemyEntity(location);
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
