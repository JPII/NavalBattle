/**
 * 
 */
package com.jpii.navalbattle.game;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.jpii.navalbattle.game.entity.*;
import com.jpii.navalbattle.pavo.*;

/**
 * @author MKirkby
 * The game file.
 */
public class NavalGame extends GameBeta {
	PlayerProfileWindow ppw;
	public NavalGame() {
		super();
		ppw = new PlayerProfileWindow();
	}
	/**
	 * Mulithreaded updator.
	 */
	public void update() {
		//Console.getInstance().printWarn(getWorld().getTimeManager().getTimeDescription() + " " + getWorld().getTimeManager().getCurrentHour() + ":00");
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
	/**
	 * Called right when sunset starts.
	 */
	public void becomingSunset() {
		
	}
	/**
	 * Called right when sunrise starts.
	 */
	public void becomingSunrise() {
		
	}
	/**
	 * Called right when nighttime starts.
	 */
	public void becomingNight() {
		
	}
	/**
	 * Called right when daytime starts.
	 */
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
	/**
	 * Called... all the time.
	 */
	public void becomingDave() {
		// Just kidding.
	}
	public void render() {
		super.render();
		//Graphics2D g = PavoHelper.createGraphics(getBuffer());
		//g.drawImage(ppw.getBuffer(),200,50,null);
	}
	@SuppressWarnings("deprecation")
	public void mouseDragged(MouseEvent me) {
		getWorld().setLoc(getWorld().getScreenX()-1,getWorld().getScreenY()-1); // Add one to the screen position in the world.
		forceUpdate(); // SEE WARNING IN DESCRIPTION!!! THIS METHOD IS NOT ACTUALLY DECREPATED!!!
		// Там будет орать в России, если вы не соблюдаете!!!
	}
}
