package com.jpii.navalbattle.game.gui;

import java.util.*;

public class History {

	private ArrayList<String> history;
	private int moves;

	public History(){
		history = new ArrayList<String>();
		moves = 0;
	}

	public boolean isEmpty(){
		return history.size() == 0;
	}

	public void add(String x){
		history.add(x);
	}
		
	public String remove(){
		return history.remove(moves);
	}
		
	public String peek(){
		return history.get(moves);
	}
}
