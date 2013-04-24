package com.roketgamer.leaderboard;

public class LeaderboardEntry {
	
	private String username;
	private int score;
	
	public LeaderboardEntry(String username, int score) {
		this.username = username;
		this.score = score;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getScore() {
		return score;
	}
}
