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
	
	/**
	 * Default <code>Culture</code> constructor.
	 */
	public Culture() {
		
	}
	
	/**
	 * Get <code>Culture</code> name.
	 * @return
	 */
	public String getCultureName() {
		return "def";
	}
	
	/**
	 * Get <code>Culture</code>.
	 * @return
	 */
	public static Culture currentCulture() {
		return cult;
	}
	
	/**
	 * Get <code>NavalBattle</code> name.
	 * @return
	 */
	public String getNavalBattleName() {
		return "NavalBattle";
	}
	
	/**
	 * Get login string.
	 * @return
	 */
	public String getLoginString() {
		return "Login";
	}
	
	/**
	 * Get ok string.
	 * @return
	 */
	public String getOkString() {
		return "Ok";
	}
	
	/**
	 * Get cancel string.
	 * @return
	 */
	public String getCancelString() {
		return "Cancel";
	}
	
	/**
	 * Get offline string.
	 * @return
	 */
	public String getOfflineString() {
		return "Offline";
	}
	
	/**
	 * Get register string.
	 * @return
	 */
	public String getRegisterString() {
		return "Register";
	}
	
	/**
	 * Get options string.
	 * @return
	 */
	public String getOptionsString() {
		return "Options";
	}
	
	/**
	 * Get username string.
	 * @return
	 */
	public String getUsernameString() {
		return "Username";
	}
	
	/**
	 * Get password string.
	 * @return
	 */
	public String getPasswordString() {
		return "Password";
	}
	
	/**
	 * Get single player string.
	 * @return
	 */
	public String getSinglePlayerString() {
		return "Singleplayer";
	}
	
	/**
	 * Get help string.
	 * @return
	 */
	public String getHelpString() {
		return "Help";
	}
	
	/**
	 * Get quit string.
	 * @return
	 */
	public String getQuitString() {
		return "Quit";
	}
	
	/**
	 * Get credits string.
	 * @return
	 */
	public String getCreditsString() {
		return "Credits";
	}
	
	/**
	 * Get back string.
	 * @return
	 */
	public String getBackString() {
		return "Back";
	}
	
	/**
	 * Get open save string.
	 * @return
	 */
	public String getOpenSaveString() {
		return "Open Save";
	}
	
	/**
	 * Get advanced options string.
	 * @return
	 */
	public String getAdvancedOptionsString() {
		return "Advanced Options";
	}
	
	/**
	 * Get string string.
	 * @return
	 */
	public String getStartString() {
		return "Start";
	}
	
	/**
	 * Get close string.
	 * @return
	 */
	public String getCloseString() {
		return "Close";
	}
}
