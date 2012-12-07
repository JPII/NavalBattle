package com.roketgamer.analytics;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;

public class Analytics {
	
	public Analytics() {
		NavalBattle.getDebugWindow().printInfo("RoketGamer analytics initialized");
		submit();
	}
	
	
	@SuppressWarnings("unused")
	public void submit() {
		// System
		String os = System.getProperty("os.name");
		String osVersion = System.getProperty("os.version");
		String osArch = System.getProperty("os.arch");
		String javaVersion = System.getProperty("java.version");
		
		// NavalBattle
		String nbVersionCode = Constants.VERSION_CODE;
		String nbVersion = Constants.NAVALBATTLE_VERSION;
		String nbDebug = Boolean.toString(Constants.DEBUG_MODE);
		
		// RoketGamer
		String rgPlayer = NavalBattle.getRoketGamer().getPlayer().getName();
		String rgVersion = NavalBattle.getRoketGamer().getVersion();
	}
}
