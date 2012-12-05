/**
 * 
 */
package com.jpii.navalbattle.game;

import com.jpii.navalbattle.game.entity.Entity;
import com.jpii.navalbattle.game.entity.Whale;
import com.jpii.navalbattle.pavo.GameBeta;
import com.jpii.navalbattle.pavo.PavoHelper;

/**
 * @author MKirkby
 * A sample game file.
 */
public class NavalGame extends GameBeta {
	public NavalGame() {
		super();
	}
	/**
	 * Mulithreaded updator.
	 */
	public void update() {
		for (int r = 0; r < PavoHelper.getGameWidth(getWorld().getWorldSize()); r++) {
			for (int c = 0; c < PavoHelper.getGameHeight(getWorld().getWorldSize()); c++) {
				Entity ent = getWorld().getEntityManager().getEntity(r,c);
				if (ent != null && PavoHelper.isEntityVisibleOnScreen(getWorld(), ent)) {
					ent.update();
					// If there is a whale, and 2 seconds have gone by, then...
					if (ent instanceof Whale && getNumUpdates() % 2000 == 0) {
						Whale w = (Whale)ent;
						// Switch moods.
						w.setAngry(!w.isAngry());
					}
				}
			}
		}
	}
	public void becomingSunset() {
		
	}
	public void becomingSunrise() {
		
	}
	public void becomingNight() {
		
	}
	public void becomingDay() {
		for (int r = 0; r < PavoHelper.getGameWidth(getWorld().getWorldSize()); r++) {
			for (int c = 0; c < PavoHelper.getGameHeight(getWorld().getWorldSize()); c++) {
				Entity ent = getWorld().getEntityManager().getEntity(r,c);
				if (ent != null) {
					ent.setImage(null); // The daytime image would go here.
				}
			}
		}
	}
}
