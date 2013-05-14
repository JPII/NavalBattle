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

import com.jpii.navalbattle.util.RoketUtils;

public class GameState {
	
	private boolean offline = true;
	private int pointsSpent = 0;
	
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
	
	/**
	 * Add points spent.
	 */
	public void addPointsSpent(int points) {
		pointsSpent += points;
		
		if(pointsSpent >= 10000)
			RoketUtils.submitAchievement(RoketGamerData.ACHIEVEMENT_BLANK_CHECK);
	}
	
	/**
	 * Get points spent in the current stage.
	 * @return
	 */
	public int getPointsSpent() {
		return pointsSpent;
	}
	
	/**
	 * Called at the end of a stage to reset all stage-related data.
	 */
	public void endStage() {
		pointsSpent = 0;
	}
}
