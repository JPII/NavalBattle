/**
 * 
 */
package com.jpii.navalbattle.game.gui;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.gui.WindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

/**
 * @author maximusvladimir
 *
 */
public class PauseWindow extends PWindow {

	public PauseWindow(WindowManager parent) {
		super(parent);
		//setSize(Game.Settings);
		setWindowUp();
	}
	
	public void onMasterWindowResize() {
		setWindowUp();
	}
	
	private void setWindowUp() {
		setSize(Game.Settings.currentWidth/3,Game.Settings.currentHeight/3);
		setLoc((Game.Settings.currentWidth/2)-(getWidth()/2),(Game.Settings.currentHeight/2)-(getHeight()/2));
	}

}
