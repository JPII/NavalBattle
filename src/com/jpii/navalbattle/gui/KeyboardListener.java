package com.jpii.navalbattle.gui;

import java.awt.event.*;

import javax.swing.SwingUtilities;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.debug.DebugWindow;

public class KeyboardListener implements KeyListener {
	
	Object o;
	
	public KeyboardListener(Object l) {
		o = l;
	}
	
	public void keyPressed(KeyEvent key) {	
		try {
			final KeyEvent k = key;
			SwingUtilities.invokeAndWait(new Runnable(){ public void run() {
		if(k.getKeyCode() == KeyEvent.VK_ESCAPE) {
			NavalBattle.close();	
		}
		
		if(o instanceof LoginWindow) {
			LoginWindow l = (LoginWindow) o;
			if(k.getKeyCode() == KeyEvent.VK_ENTER) {
				l.login();
			}
		}
		
		if(o instanceof DebugWindow) {
			DebugWindow d = (DebugWindow) o;
			if(k.getKeyCode() == KeyEvent.VK_ENTER) {
				d.submitCommandRemote();
			}
		}
		
		}});
		}catch (Exception e) {}
		
	}
	
	public void run()
	{
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
