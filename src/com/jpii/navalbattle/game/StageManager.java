package com.jpii.navalbattle.game;

import javax.swing.JFrame;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoOpenState;
import com.jpii.navalbattle.pavo.WorldSize;

public class StageManager {
	
	private GameComponent game;
	int stageNumber;
	
	public StageManager(){
		stageNumber = 0;
	}
	/**
	 * @return the GameComponent, can be null
	 */
	public GameComponent getGameComponent(){
		stageNumber++;
		return newGameComponent(stageNumber);
	}
	
	private GameComponent newGameComponent(int num){
//		game.getGame().getSelfServer().halt();
//		switch(num){
//			case 1: Game.Settings.resetSeed(0); game.setGame(new NavalGame(WorldSize.WORLD_TINY)); break;
//			case 2: break;
//			case 3: break;
//			case 4: break;
//			case 5: break;
//			case 6: break;
//			case 7: break;
//			case 8: break;
//			case 9: break;
//			case 10: break;
//		}
		return game;
	}
	
	public void setGameVars(WorldSize ws,JFrame jf,PavoOpenState pos, String args){
		game = new GameComponent(ws,jf,pos,args);
	}
	
	public void toggleFullscreen(){
		game.toggleFullscreen();
	}
	
}
