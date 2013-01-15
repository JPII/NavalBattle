/**
 * 
 */
package com.jpii.navalbattle.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.game.entity.Entity;
import com.jpii.navalbattle.pavo.EntityManager;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.World;
import com.jpii.navalbattle.util.FileUtils;

/**
 * @author maximusvladimir
 * The entity manager specified for NavalThing.
 */
public class NavalManager extends EntityManager {
	BufferedImage grid,humanoid,staticTank;
	BufferedImage sub01,sub02;
	/**
	 * Creates a new instance of the NavalManager.
	 * @param w The world to create it from.
	 */
	public NavalManager(World w) {
		super(w);
		grid = new BufferedImage(50,50,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = PavoHelper.createGraphics(grid);
		g.setColor(new Color(120,120,120,100));
		g.drawRect(1,1,49,49);
		humanoid = FileUtils.getImage("drawable-game/other/humanmob.png");
		staticTank = FileUtils.getImage("drawable-game/other/TankBase.png");
		BufferedImage submarine = FileUtils.getImage("drawable-game/submarine/submarine.png");
		sub01 = PavoHelper.imgUtilFastCrop(submarine, 0, 0, 50,50);
		sub02 = PavoHelper.imgUtilFastCrop(submarine, 50, 0, 50,50);
	}
	public BufferedImage getImage(Entity ent) {
		if (ent == null)
			return null;
		BufferedImage ager = null;
		switch (ent.getId()) {
		case 0:
			ager = grid;
			break;
		case 1:
			ager = humanoid;
			break;
		case 0x93AF9B:
			ager = staticTank;
			break;
		case 8:
			ager = sub01;
		case 9:
			ager = sub02;
		}
		BufferedImage s = ent.getCustomImage();
		if (s != null)
			ager = s;
		return ager;
	}
}
