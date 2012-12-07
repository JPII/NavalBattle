package com.roketgamer.analytics;

import com.jpii.navalbattle.NavalBattle;

public class Analytics {
	
	public Analytics() {
		NavalBattle.getDebugWindow().printInfo("RoketGamer analytics initialized");
		submitCore();
		submitCustom();
	}
	
	@SuppressWarnings("unused")
	public void submitCore() {
		// System
		String os = System.getProperty("os.name");
		String osVersion = System.getProperty("os.version");
		String osArch = System.getProperty("os.arch");
		String javaVersion = System.getProperty("java.version");
		
		// RoketGamer
		String rgPlayer = NavalBattle.getRoketGamer().getPlayer().getName();
		String rgVersion = NavalBattle.getRoketGamer().getVersion();
	}
	
	public void submitCustom() {
		
	}
}
