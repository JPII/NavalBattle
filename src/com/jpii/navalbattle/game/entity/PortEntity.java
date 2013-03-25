/**
 * 
 */
package com.jpii.navalbattle.game.entity;

import java.awt.image.BufferedImage;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.util.FileUtils;

/**
 * @author maximusvladimir
 *
 */
public class PortEntity extends AnimatedEntity {

	/**
	 * @param em
	 * @param loc
	 * @param orientation
	 * @param team
	 * @param animationFrameIds
	 */
	public PortEntity(EntityManager em, Location loc, byte orientation,
			int team) {
		super(em, loc, orientation, team, generatePort());
	}
	
	
	private GridedEntityTileOrientation[] generatePort() {
		BufferedImage flash1 = null, flash2 = null, flash3 = null, flash4 = null;
		
		int fl1 = 
				getManager().registerEntity(PavoHelper.imgUtilOutline(flash1,Game.Settings.GridColor),
						GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int fl2 = 
				getManager().registerEntity(PavoHelper.imgUtilOutline(flash2,Game.Settings.GridColor),
						GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int fl3 = 
				getManager().registerEntity(PavoHelper.imgUtilOutline(flash3,Game.Settings.GridColor),
						GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int fl4 = 
				getManager().registerEntity(PavoHelper.imgUtilOutline(flash4,Game.Settings.GridColor),
						GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		GridedEntityTileOrientation w1 = new GridedEntityTileOrientation();
		w1.setLeftToRightImage(fl1);
		w1.setTopToBottomImage(fl1);
		GridedEntityTileOrientation w2 = new GridedEntityTileOrientation();
		w2.setLeftToRightImage(fl2);
		w2.setTopToBottomImage(fl2);
		GridedEntityTileOrientation w3 = new GridedEntityTileOrientation();
		w3.setLeftToRightImage(fl3);
		w3.setTopToBottomImage(fl3);
		GridedEntityTileOrientation w4 = new GridedEntityTileOrientation();
		w4.setLeftToRightImage(fl4);
		w4.setTopToBottomImage(fl4);
		return new GridedEntityTileOrientation[] {w1,w2,w3,w4};
	}
}
