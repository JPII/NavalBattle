package com.jpii.navalbattle.gui;

import java.awt.event.*;

import com.jpii.navalbattle.NavalBattle;

public class KeyBoard implements KeyListener {
	
	Object o;
	
	public KeyBoard(Object l) {
		o = l;
	}
	
	public void keyPressed(KeyEvent k) {	
		if(k.getKeyCode() == KeyEvent.VK_ESCAPE) {
			NavalBattle.close();
		}
		if(o instanceof LoginWindow) {
			LoginWindow l = (LoginWindow) o;
			if(k.getKeyCode() == KeyEvent.VK_ENTER) {
				l.login();
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
