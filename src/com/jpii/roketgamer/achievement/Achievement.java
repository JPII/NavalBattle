package com.jpii.roketgamer.achievement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.jpii.roketgamer.Player;
import com.jpii.roketgamer.RoketGamer;

public class Achievement {
	private int id;
	
	/**
	 * Create a new <code>Achievement</code>
	 * @param id
	 */
	public Achievement(int id) {
		this.id = id;
	}
	
	/**
	 * Get <code>Achievement</code> ID
	 * @return id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Returns if current user has achieved achievement.
	 * @param player
	 */
	public boolean hasAchieved(Player player) {
		// TODO: Implement
		return false;
	}
	
	/**
	 * Submit an achievement. Returns if operation is successful.
	 * @param achievement
	 * @param id
	 * @return
	 */
	public boolean submit(Achievement achievement, int id) {
		try {
			URL url = new URL(RoketGamer.SERVER_LOCATION + "/api/" + RoketGamer.VERSION + "/achievement/submit.php?id=" + achievement.getID() + "?session=" + RoketGamer.getInstance().getSession().getSessionID().trim() + "&id=" + id);

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
			e.printStackTrace();
		}
		
		return false;
	}
}
