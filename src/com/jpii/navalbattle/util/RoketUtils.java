package com.jpii.navalbattle.util;

import javax.swing.ImageIcon;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.RoketGamerData;
import com.roketgamer.achievement.Achievement;
import com.roketgamer.leaderboard.Leaderboard;

public class RoketUtils {
	
	/**
	 * Quickly submit a <code>Leader-board</code> to the server. Checks if off-line or if game is out-dated.
	 * @param leaderboard
	 * @param score
	 */
	public static void submitLeaderboard(Leaderboard leaderboard, int score) {
		if(!NavalBattle.getGameState().isOffline()) {
			if(!NavalBattle.getBroadcastService().needsUpdate()) {
				leaderboard.submit(score);
				NavalBattle.getWindowHandler().getToasterManager().showToaster(
						new ImageIcon(RoketUtils.class.getResource("/com/roketgamer/res/logo_100px.png")),
						"Submitted score of " + score + " to server");
				
				submitAchievement(RoketGamerData.ACHIEVEMENT_SYNCING_SHIP);
			} else {
				NavalBattle.getDebugWindow().printWarning("NavalBattle is outdated. Score cannot be submitted.");
			}
		} else {
			NavalBattle.getDebugWindow().printWarning("NavalBattle is offline. Score cannot be submitted.");
		}
	}
	
	/**
	 * Quickly submit an <code>Achievement</code> to the server. Checks if off-line or if game is out-dated.
	 * Also handles duplicate achievements and toast notifications.
	 * @param achievement
	 * @param score
	 */
	public static void submitAchievement(Achievement achievement) {
		if(!NavalBattle.getGameState().isOffline()) {
			if(!NavalBattle.getBroadcastService().needsUpdate()) {
				if(!achievement.hasAchieved()) {
					achievement.submit();
					
					NavalBattle.getWindowHandler().getToasterManager().showToaster(
							new ImageIcon(RoketUtils.class.getResource("/com/roketgamer/res/logo_100px.png")), 
							"Achievement Unlocked!\n" + achievement.getName());

					SoundUtils.playSound(RoketUtils.class.getResourceAsStream("/com/jpii/navalbattle/res/sfx/achievement.wav"));
					
					NavalBattle.getDebugWindow().printInfo("Achievement \"" + achievement.getName() + "\" submitted.");
				} else {
					NavalBattle.getDebugWindow().printWarning("Achievement \"" + achievement.getName() + "\" has already been submitted.");
				}
			} else {
				NavalBattle.getDebugWindow().printWarning("NavalBattle is outdated. Acheivement cannot be submitted.");
			}
		} else {
			NavalBattle.getDebugWindow().printWarning("NavalBattle is offline. Achievement cannot be submitted.");
		}
	}
}
