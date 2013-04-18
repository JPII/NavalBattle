package com.jpii.navalbattle.game;

import javax.swing.JFrame;

import com.jpii.navalbattle.pavo.PavoOpenState;

public class StageManager {
	
	private GameComponent game;
	
	public StageManager(){
		
	}
	/**
	 * @return the GameComponent, can be null
	 */
	public GameComponent getGameComponent(){
		return game;
	}
	
	public void setGameVars(JFrame jf,PavoOpenState pos, String args){
		game = new GameComponent(jf,pos,args);
	}
	
	public void toggleFullscreen(){
		game.toggleFullscreen();
	}
	
}
