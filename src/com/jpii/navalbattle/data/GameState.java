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

package com.jpii.navalbattle.data;

public class GameState {
	
	private boolean offline = true;
	
	/**
	 * Get if game is in off-line mode.
	 * @return off-line
	 */
	public boolean isOffline() {
		return offline;
	}
	
	/**
	 * Set game into Off-line mode.
	 * @param offline
	 */
	public void setOffline(boolean offline) {
		this.offline = offline;
	}
}
