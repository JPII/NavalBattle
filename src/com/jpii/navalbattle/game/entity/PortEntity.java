/**
 * 
 */
package com.jpii.navalbattle.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import maximusvladimir.dagen.Rand;

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
		super(em, loc, orientation, team, generatePort(em,loc));
	}
	
	
	private static GridedEntityTileOrientation[] generatePort(EntityManager man, Location loc) {
		BufferedImage flash1 = null, flash2 = null, flash3 = null, flash4 = null;
		
		flash1 = new BufferedImage(50,50,BufferedImage.TYPE_4BYTE_ABGR);
		flash2 = new BufferedImage(50,50,BufferedImage.TYPE_4BYTE_ABGR);
		flash3 = new BufferedImage(50,50,BufferedImage.TYPE_4BYTE_ABGR);
		flash4 = new BufferedImage(50,50,BufferedImage.TYPE_4BYTE_ABGR);
		
		Graphics2D g1 = PavoHelper.createGraphics(flash1);
		Graphics2D g2 = PavoHelper.createGraphics(flash2);
		Graphics2D g3 = PavoHelper.createGraphics(flash3);
		Graphics2D g4 = PavoHelper.createGraphics(flash4);
		
		Rand randy = new Rand(Game.Settings.seed + 50);
		int numStructures = randy.nextInt(6,10);
		int counter = 0;
		while (counter < numStructures) {
			counter++;
			int type = randy.nextInt(0,3);
			int lx = randy.nextInt(4,40);
			int ly = randy.nextInt(4,35);
			if (type == 2) {
				g1.setColor(Color.red);
				g1.drawRect(lx,ly,4,6);
				g2.setColor(Color.orange);
				g2.drawRect(lx,ly,4,6);
				g3.setColor(Color.yellow);
				g3.drawRect(lx,ly,4,6);
				g4.setColor(Color.pink);
				g4.drawRect(lx,ly,4,6);
			}
			else {
				g1.setColor(Color.orange);
				g1.fillRect(lx,ly,6,4);
				g2.setColor(Color.yellow);
				g2.fillRect(lx,ly,5,4);
				g3.setColor(Color.pink);
				g3.fillRect(lx,ly,4,4);
				g4.setColor(Color.red);
				g4.fillRect(lx,ly,5,4);
			}
		}
		
		int fl1 = 
				man.registerEntity(PavoHelper.imgUtilOutline(flash1,Game.Settings.GridColor),
						GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int fl2 = 
				man.registerEntity(PavoHelper.imgUtilOutline(flash2,Game.Settings.GridColor),
						GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int fl3 = 
				man.registerEntity(PavoHelper.imgUtilOutline(flash3,Game.Settings.GridColor),
						GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int fl4 = 
				man.registerEntity(PavoHelper.imgUtilOutline(flash4,Game.Settings.GridColor),
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
