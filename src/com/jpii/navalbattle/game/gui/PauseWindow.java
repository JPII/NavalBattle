/**
 * 
 */
package com.jpii.navalbattle.game.gui;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.WindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

/**
 * @author maximusvladimir
 *
 */
public class PauseWindow extends PWindow {
	PButton buttonSave, buttonReturnToGame, buttonQuitGame, buttonServer;
	public PauseWindow(NewWindowManager parent) {
		super(parent);
		//setSize(Game.Settings);
		setTitleAsCentered(true);
		setText("Game Paused");
		
		buttonSave = new PButton(this, "Save");
		buttonReturnToGame = new PButton(this, "Return to Game");
		buttonQuitGame = new PButton(this, "Quit");
		buttonServer = new PButton(this, "Server is running");
		addControl(buttonSave);
		addControl(buttonReturnToGame);
		addControl(buttonQuitGame);
		addControl(buttonServer);
		
		setWindowUp();
	}
	
	public void onMasterWindowResize() {
		setWindowUp();
	}
	
	private void setWindowUp() {
		if (Game.Settings.currentWidth > 1000 && Game.Settings.currentHeight > 600)
			setSize(Game.Settings.currentWidth/3,Game.Settings.currentHeight/3);
		else
			setSize(333,200);
		setLoc((Game.Settings.currentWidth/2)-(getWidth()/2),(Game.Settings.currentHeight/2)-(getHeight()/2));
		
		buttonSave.setLoc(10,33);
		buttonReturnToGame.setLoc((getWidth()/2) - (buttonReturnToGame.getWidth()/2), getHeight() - 33);
		buttonQuitGame.setLoc(10,60);
		buttonServer.setLoc(getWidth() - (10 + buttonServer.getWidth()), 33);
	}

}
