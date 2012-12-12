package com.jpii.navalbattle.lang;

public class Culture {
	private static Culture cult = new EnglishCulture();
	public Culture() {
		
	}
	public String getCultureName() {
		return "def";
	}
	public static Culture currentCulture() {
		return cult;
	}
	public String getNavalBattleName() {
		return "NavalBattle";
	}
	public String getLoginString() {
		return "Login";
	}
	public String getOkString() {
		return "Ok";
	}
	public String getCancelString() {
		return "Cancel";
	}
	public String getOfflineString() {
		return "Offline";
	}
	public String getRegisterString() {
		return "Register";
	}
	public String getOptionsString() {
		return "Options";
	}
	public String getUsernameString() {
		return "Username";
	}
	public String getPasswordString() {
		return "Password";
	}
	public String getSinglePlayerString() {
		return "Singleplayer";
	}
	public String getHelpString() {
		return "Help";
	}
	public String getQuitString() {
		return "Quit";
	}
	public String getCreditsString() {
		return "Credits";
	}
	public String getBackString() {
		return "Back";
	}
	public String getOpenSaveString() {
		return "Open Save";
	}
	public String getAdvancedOptionsString() {
		return "Advanced Options";
	}
	public String getStartString() {
		return "Start";
	}
	public String getCloseString() {
		return "Close";
	}
}
