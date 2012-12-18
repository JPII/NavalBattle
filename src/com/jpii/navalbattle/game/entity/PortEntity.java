package com.jpii.navalbattle.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.pavo.EntityManager;
import com.jpii.navalbattle.pavo.GameBeta;
import com.jpii.navalbattle.pavo.McRegion;
import com.jpii.navalbattle.pavo.PavoHelper;

public class PortEntity extends Entity {
	public PortEntity(EntityManager em,Location l) {
		super(em,l);
		setId(2);
		//System.out.println(getLocation());
		formulate();
	}
	public void update() {
		super.update();
	}
	public void formulate() {
		custom = new BufferedImage(50,50,2);
		Graphics2D g = PavoHelper.createGraphics(custom);
		g.setColor(Color.cyan);
		g.fillRect(0,0,50,50);
		for (int c = 0; c < GameBeta.Settings.rand.nextInt(5,10); c++) {
			int x = GameBeta.Settings.rand.nextInt(40);
			int y = GameBeta.Settings.rand.nextInt(40);
			if (McRegion.getPoint(x+(getLocation().getCol()*50), y+(getLocation().getRow()*50)) >= 0.4) {
				g.setColor(GameBeta.Settings.rand.nextColor());
				g.fillRect(x,y,5,5);
			}
		}
	}
}
