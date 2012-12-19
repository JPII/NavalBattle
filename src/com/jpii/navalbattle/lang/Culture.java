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
