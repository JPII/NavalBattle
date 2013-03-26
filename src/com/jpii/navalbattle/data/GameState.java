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
	private int score = 0;
	private int difficutly = 1;
	private int turns = 0;
	
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
	 * Get current score.
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Set score to specified amount.
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Add to current score.
	 * @param score
	 */
	public void addScore(int score) {
		this.score += score;
	}
	
	/**
	 * Subtract from current score.
	 * @param score
	 */
	public void subtractScore(int score) {
		this.score -= score;
	}
	
	/**
	 * Reset score.
	 */
	public void resetScore() {
		this.score = 0;
	}
	
	public int getTurnNum(){
		return turns;
	}
	
	/**
	 * Sets the difficulty of the game.
	 * @param difficulty
	 */
	public void setDifficulty(int difficulty) {
		this.difficutly = difficulty;
	}
	
	/**
	 * Gets the difficulty of the game.
	 * @return difficulty
	 */
	public int getDifficulty() {
		return difficutly;
	}
}
