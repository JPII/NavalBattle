package com.jpii.navalbattle;

import com.jpii.roketgamer.*;
import com.jpii.navalbattle.gui.*;

public class NavalBattle {

	private static RoketGamer roketGamer = new RoketGamer();
	private static DebugWindow debugWindow;
	
	public static void main(String[] args) {
		debugWindow = new DebugWindow();
		
		debugWindow.printInfo("NavalBattle initialized.");
	}
	
	public DebugWindow getDebugWindow() {
		return debugWindow;
	}
	
	public RoketGamer getRoketGamer() {
		return roketGamer;
	}
}