package com.jpii.roketgamer.leaderboard;

public class Leaderboard {
	private String location;
	
	/**
	 * Create a new <code>Leaderboard</code>
	 * @param location
	 */
	public Leaderboard(String location) {
		this.location = location;
	}
	
	/**
	 * Get <code>Leaderboard</code> location
	 * @return
	 */
	public String getLocation() {
		return location;
	}
}
