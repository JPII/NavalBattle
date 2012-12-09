package com.roketgamer.leaderboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.roketgamer.Player;
import com.roketgamer.RoketGamer;

public class Leaderboard {
	private int id;
	
	/**
	 * Create a new <code>Leaderboard</code>.
	 * @param id
	 */
	public Leaderboard(int id) {
		this.id = id;
	}
	
	/**
	 * Get <code>Leaderboard</code> ID.
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
	
	/**
	 * Submit a leaderboard score. Returns if operation is successful.
	 * @param leaderboard
	 * @param score
	 * @return
	 */
	public boolean submit(int score) {
		try {
			URL url = new URL(RoketGamer.SERVER_LOCATION + "/api/" + RoketGamer.VERSION + "/leaderboard/submit.php?id=" + getID() + "?session=" + RoketGamer.getInstance().getSession().getSessionID().trim()  + "&score=" + score);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String result = in.readLine();
			if (result.contains("true")) {
				in.close();
				return true;
				
			} else {
				in.close();
				return false;
			}

		} catch (Exception e) {
		}
		
		return false;
	}
}
