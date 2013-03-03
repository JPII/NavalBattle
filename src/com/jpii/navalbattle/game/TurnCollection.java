/**
 * 
 */
package com.jpii.navalbattle.game;

import java.util.ArrayList;

/**
 * @author MKirkby
 *
 */
public class TurnCollection {
	ArrayList<Turn> turns;
	public TurnCollection() {
		turns = new ArrayList<Turn>();
	}
	
	public Turn getTurn() {
		return turns.get(turns.size()-1);
	}
	
	public Turn getTurn(int index) {
		return turns.get(index);
	}
	
	public Turn newTurn() {
		return null;//Turn turn = new Turn();
	}
}
