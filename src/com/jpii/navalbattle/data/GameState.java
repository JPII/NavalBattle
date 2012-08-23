package com.jpii.navalbattle.data;

public class GameState {
	private boolean offline = true;
	private int score = 0;
	
	/**
	 * Get if game is in offline mode.
	 * 
	 * @return offline
	 */
	public boolean isOffline() {
		return offline;
	}
	
	/**
	 * Set game into offline mode.
	 * 
	 * @param offline
	 */
	public void setOffline(boolean offline) {
		this.offline = offline;
	}
	
	/**
	 * Get current score.
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Set score to specified amount.
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Add to current score.
	 * 
	 * @param score
	 */
	public void addScore(int score) {
		this.score += score;
	}
	
	/**
	 * Subtract from current score.
	 * 
	 * @param score
	 */
	public void subtractScore(int score) {
		this.score -= score;
	}
	
	/**
	 * Reset score.
	 * 
	 */
	public void resetScore() {
		this.score = 0;
	}
}
