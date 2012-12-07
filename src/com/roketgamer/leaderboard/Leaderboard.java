package com.roketgamer.leaderboard;

import com.roketgamer.Player;

public class Leaderboard {
	private int id;
	
	/**
	 * Create a new <code>Leaderboard</code>
	 * @param id
	 */
	public Leaderboard(int id) {
		this.id = id;
	}
	
	/**
	 * Get <code>Leaderboard</code> ID
	 * @return
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Get score at a position on the leaderboard.
	 * @param position
	 * @return
	 */
	public int getScoreAt(int position) {
		// TODO: Implement
		return 0;
	}
	
	/**
	 * Get username at a position on the leaderboard.
	 * @param position
	 * @return
	 */
	public String getUserAt(int position) {
		// TODO: Implement
		return "";
	}
	
	/**
	 * Get current user's top position.
	 * @param player
	 * @return
	 */
	public int getUserPos(Player player) {
		// TODO: Implement
		return 0;
	}
	
	/**
	 * Get current user's top score.
	 * @param player
	 * @return
	 */
	public int getUserTopScore(Player player) {
		// TODO: Implement
		return 0;
	}
}
