/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.navalbattle;

import javax.swing.UIManager.*;
import javax.swing.*;

import com.jpii.roketgamer.*;
import com.jpii.navalbattle.data.*;
import com.jpii.navalbattle.debug.*;
import com.jpii.navalbattle.game.SinglePlayerGame;
import com.jpii.navalbattle.renderer.*;

public class NavalBattle {

	private static RoketGamer roketGamer;
	private static DebugWindow debugWindow;
	private static GameState gameState;
	private static CommandHandler commandHandler;
	private static WindowHandler windowHandler;
	
	public static void main(String[] args) {
		
		
		/*ArrayList<SettingsAttribute> attrs = new ArrayList<SettingsAttribute>();
		SettingsAttribute a = new SettingsAttribute("lastGoodUserName");
		attrs.add(a);
		a = new SettingsAttribute("lastGoodPassword");
		attrs.add(a);
		a = new SettingsAttribute("gameWindowWidth");
		attrs.add(a);
		a = new SettingsAttribute("gameWindowHeight");
		attrs.add(a);
		java.net.URL url = null;
		try {
			url = new java.net.URL("C:\\navalbattle\\settings.ini");
		} catch (Exception e) { 
			e.printStackTrace();
		}
		if (url != null && new File(url.getPath()).exists()) {
		SettingsReader reader = new SettingsReader(url.getPath(),attrs);
		reader.read();
		}*/
		
		Helper.LoadStaticResources();
		setDefaultLookAndFeel();
		debugWindow = new DebugWindow();
		debugWindow.setVisible(true);
		gameState = new GameState();
		roketGamer = new RoketGamer();
		commandHandler = new CommandHandler(Commands.COMMANDS);	
		debugWindow.printInfo("NavalBattle " + Constants.NAVALBATTLE_VERSION + " initialized");
		windowHandler = new WindowHandler();
		
		
		new SinglePlayerGame();
	}
	
	/**
	 * Returns current instance of RoketGamer.
	 * 
	 * @return roketGamer
	 */
	public static RoketGamer getRoketGamer() {
		return roketGamer;
	}
	
	/**
	 * Returns current instance of DebugWindow.
	 * 
	 * @return debugWindow
	 */
	public static DebugWindow getDebugWindow() {
		return debugWindow;
	}
	
	/**
	 * Returns current instance of GameState.
	 * 
	 * @return gameState
	 */
	public static GameState getGameState() {
		return gameState;
	}
	
	/**
	 * Returns current instance of CommandHandler.
	 * 
	 * @return commandHandler
	 */
	public static CommandHandler getCommandHandler() {
		return commandHandler;
	}
	
	/**
	 * Returns current instance of WindowHandler. Used to switch Windows.
	 * 
	 * @return windowHandler
	 */
	public static WindowHandler getWindowHandler() {
		return windowHandler;
	}
	
	private static void setDefaultLookAndFeel(){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
			NavalBattle.getDebugWindow().printError("NimbusLookAndFeel was unable to be loaded, unsuported");	
		} catch (ClassNotFoundException e) {
			NavalBattle.getDebugWindow().printError("NimbusLookAndFeel was unable to be loaded, class not found");	
		} catch (InstantiationException e) {
			NavalBattle.getDebugWindow().printError("NimbusLookAndFeel was unable to be loaded, instantiation");	
		} catch (IllegalAccessException e) {
			NavalBattle.getDebugWindow().printError("NimbusLookAndFeel was unable to be loaded, illegalaccess");	
		}
	}
}