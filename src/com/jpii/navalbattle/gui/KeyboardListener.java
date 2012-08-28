package com.jpii.navalbattle.gui;

import java.awt.event.*;

import javax.swing.SwingUtilities;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.debug.DebugWindow;

public class KeyboardListener implements Runnable, KeyListener {
	
	Object o;
	KeyEvent k;
	
	public KeyboardListener(Object l) {
		o = l;
	}
	
	public void keyPressed(KeyEvent keyevent) {	
		this.k = keyevent;
		
	}
	
	public void run()
	{
		if(k.getKeyCode() == KeyEvent.VK_ESCAPE) {
			try {
				SwingUtilities.invokeAndWait(new Runnable(){ public void run() {
					NavalBattle.close(); }});
			}catch (Exception e) {}
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
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
