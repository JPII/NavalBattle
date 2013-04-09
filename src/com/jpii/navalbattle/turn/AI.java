package com.jpii.navalbattle.turn;

import com.jpii.navalbattle.game.NavalManager;

public class AI extends Player{
	
	NavalManager nm;
	
	public AI(NavalManager nm,String name) {
		super(name);
		this.nm = nm;
	}
	
	public void takeTurn(){
		turnOver=true;
	}
	
	public void endTurn(){
		super.endTurn();
	}
	
}
