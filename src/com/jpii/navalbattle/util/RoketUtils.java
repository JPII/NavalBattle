package com.jpii.navalbattle.util;

import com.jpii.navalbattle.NavalBattle;
import com.roketgamer.achievement.Achievement;
import com.roketgamer.leaderboard.Leaderboard;

public class RoketUtils {
	
	/**
	 * Quickly submit a <code>Leaderboard</code> to the server. Checks if offline or if game is outdated.
	 * @param leaderboard
	 * @param score
	 */
	public static void submitLeaderboard(Leaderboard leaderboard, int score) {
		if(!NavalBattle.getGameState().isOffline()) {
			if(!NavalBattle.getBroadcastService().needsUpdate()) {
				leaderboard.submit(score);
			} else {
				NavalBattle.getDebugWindow().printWarning("NavalBattle is outdated. Score cannot be submitted.");
			}
		} else {
			NavalBattle.getDebugWindow().printWarning("NavalBattle is offline. Score cannot be submitted.");
		}
	}
	
	/**
	 * Quickly submit an <code>Achievement</code> to the server. Checks if offline or if game is outdated.
	 * @param achievement
	 * @param score
	 */
	public static void submitAchievement(Achievement achievement, int score) {
		if(!NavalBattle.getGameState().isOffline()) {
			if(!NavalBattle.getBroadcastService().needsUpdate()) {
				achievement.submit();
			} else {
				NavalBattle.getDebugWindow().printWarning("NavalBattle is outdated. Acheivement cannot be submitted.");
			}
		} else {
			NavalBattle.getDebugWindow().printWarning("NavalBattle is offline. Achievement cannot be submitted.");
		}
	}
}
