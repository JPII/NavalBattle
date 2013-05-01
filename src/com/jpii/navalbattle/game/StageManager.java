package com.jpii.navalbattle.game;

import com.jpii.navalbattle.game.entity.AircraftCarrier;
import com.jpii.navalbattle.game.entity.BattleShip;
import com.jpii.navalbattle.game.entity.PortEntity;
import com.jpii.navalbattle.game.entity.Submarine;
import com.jpii.navalbattle.game.entity.Whale;
import com.jpii.navalbattle.game.turn.AI;
import com.jpii.navalbattle.game.turn.Player;
import com.jpii.navalbattle.game.turn.PlayerManager;
import com.jpii.navalbattle.game.turn.TurnManager;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.WorldSize;
import com.jpii.navalbattle.pavo.grid.GridHelper;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;

public class StageManager {
	
	private GameComponent game;
	int stageNumber;
	Player persists;
	AI ai;
	
	public StageManager(String playerName){
		stageNumber = 0;
		if(playerName.equals(""))
			playerName = "Player 1";
		ai = new AI(NavalGame.getManager());
		persists = new Player(playerName);
	}
	/**
	 * @return the GameComponent
	 */
	public GameComponent getGameComponent(){
		stageNumber++;
		GameComponent temp = newGameComponent(stageNumber);
		setStage(stageNumber);
		return temp;
	}
	
	private GameComponent newGameComponent(int num){
		if(game!=null) {
			System.out.println("Disposing.");
			game.dispose();
		}
		switch(num){
			case 1: Game.Settings.resetSeed(0); 
					NavalManager.setTurnManager(new TurnManager(new PlayerManager(persists,ai)));
					game=new GameComponent(new NavalGame(WorldSize.WORLD_TINY));
					break;
			case 2: Game.Settings.resetSeed(10); game=new GameComponent(new NavalGame(WorldSize.WORLD_SMALL));  break;
			case 3: Game.Settings.resetSeed(15); game=new GameComponent(new NavalGame(WorldSize.WORLD_SMALL));  break;
			case 4: Game.Settings.resetSeed(20); game=new GameComponent(new NavalGame(WorldSize.WORLD_SMALL));  break;
			case 5: Game.Settings.resetSeed(25); game=new GameComponent(new NavalGame(WorldSize.WORLD_SMALL));  break;
			default: Game.Settings.resetSeed(1000); game=new GameComponent(new NavalGame(WorldSize.WORLD_MEDIUM));  break;
		}
		return game;
	}
	
	private void setStage(int num){
		testWait();
		switch(num){
			case 1: 
				addEntities(persists, 1, 0, 0,1);
				addEntities(ai, 1, 0, 0,1);
				addWhales(5);
				System.out.println("Let me play you the sounds of my people people");
				break;
			case 2:  break;
			case 3:  break;
			case 4:  break;
			case 5:  break;
		   default:  break;
		}
	}
	
	private void testWait(){
		while(NavalManager.isGenerating())
			;
	}
	
	private void addEntities(Player p, int bss, int subs, int acs,int ports){		
		NavalManager nm = NavalGame.getManager();
		GridHelper gh = new GridHelper(0,nm);
		boolean placed = false;
		Location poll;
		TurnManager tm = nm.getTurnManager();
		
		for(int index = 0; index<bss; index++){
			placed = false;
			while (!placed){
				poll = gh.pollNextWaterTile();
				placed = true;
				if(GridHelper.canPlaceInGrid(nm,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, poll.getRow(), poll.getCol(), 4))
					tm.addEntity(new BattleShip(nm, poll, GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT),p);
				else if(GridHelper.canPlaceInGrid(nm,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, poll.getRow(), poll.getCol(), 4))
					tm.addEntity(new BattleShip(nm, poll,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM),p);
				else
					placed = false;
			}
		}
		
		for(int index = 0; index<subs; index++){
			placed = false;
			while (!placed){
				poll = gh.pollNextWaterTile(25);
				placed = true;
				if(GridHelper.canPlaceInGrid(nm,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, poll.getRow(), poll.getCol(), 2))
					tm.addEntity(new Submarine(nm, poll,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT),p);
				else if(GridHelper.canPlaceInGrid(nm,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, poll.getRow(), poll.getCol(), 2))
					tm.addEntity(new Submarine(nm, poll,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM),p);
				else
					placed = false;
			}
		}
		
		for(int index = 0; index<acs; index++){
			placed = false;
			while (!placed){
				poll = gh.pollNextWaterTile(25);
				placed = true;
				if(GridHelper.canPlaceInGrid(nm,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, poll.getRow(), poll.getCol(), 5))
					tm.addEntity(new AircraftCarrier(nm, poll,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT),p);
				else if(GridHelper.canPlaceInGrid(nm,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, poll.getRow(), poll.getCol(), 5))
					tm.addEntity(new AircraftCarrier(nm, poll,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM),p);
				else
					placed = false;
			}
		}
				
		for(int index = 0; index<ports; index++){
			placed = false;
			while (!placed){
				poll = gh.pollNextShoreTile();
				placed = true;
				tm.addEntity(new PortEntity(nm,poll,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT),p);
				System.out.println("Port generated at " + poll);
			}
		}
	}
	
	private void addWhales(int whales){
		NavalManager nm = NavalGame.getManager();
		GridHelper gh = new GridHelper(0,nm);
		boolean placed = false;
		Location poll;			
		for(int index = 0; index<whales; index++){
			placed = false;
			while (!placed){
				poll = gh.pollNextWaterTile();
				while(!GridHelper.canPlaceInGrid(nm, GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, poll.getRow(), poll.getCol(), 1)){
					poll = gh.pollNextWaterTile();
				}
				placed = true;
				new Whale(nm,poll,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT,NavalManager.w1,NavalManager.w2,NavalManager.w3);
			}
		}
	}
}
