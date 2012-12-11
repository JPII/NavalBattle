package com.jpii.navalbattle.pavo;

import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;

import maximusvladimir.dagen.Rand;

import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.renderer.RenderConstants;

public class OmniMap extends Renderable {
	int mx,my;
	World w;
	public OmniMap(World w) {
		super();
		this.w = w;
		setSize(100,100);
		buffer =   (new BufferedImage(getWidth(), getHeight(),BufferedImage.TYPE_INT_RGB));
	}
	public void mouseMoved(MouseEvent me) {
		mx = me.getX();
		my = me.getY();
		
		render();
	}
	public void render() {
		Graphics2D g = PavoHelper.createGraphics(getBuffer());
		Rand rand = new Rand(Constants.MAIN_SEED);
		for (int x = 0; x < 100/3; x++) {
			for (int y = 0; y < 100/3; y++) {
				int strx = x * PavoHelper.getGameWidth(w.getWorldSize());
				int stry = y * PavoHelper.getGameHeight(w.getWorldSize());
				float frsh = McRegion.getPoint(strx,stry);
				int opcode = (int)(frsh*255.0f);
				if (opcode > 255)
					opcode = 255;
				if (opcode < 0)
					opcode = 0;
				g.setColor(new Color(opcode,opcode,opcode));
				if (opcode < 130) {
					int nawo = rand.nextInt(-5, 8);
					g.setColor(Helper.adjust(Helper.randomise(new Color(83+nawo,83+nawo,132+nawo),
	                        5, rand, false), 1 - ((frsh)/2 / RenderConstants.GEN_WATER_HEIGHT), 30));
					
				}
				else if (opcode < 135) {
					g.setColor(Helper.adjust(Helper.randomise(RenderConstants.GEN_SAND_COLOR,
	                        RenderConstants.GEN_COLOR_DIFF, rand, false), (1.0-frsh)/2, 50));
				}
				else{
					g.setColor(Helper.adjust(Helper.randomise(RenderConstants.GEN_GRASS_COLOR,
	                        RenderConstants.GEN_COLOR_DIFF, rand, false), (1.0-frsh)/2, 50));
				}
				g.fillRect(x*3,y*3,4,4);
				//g.drawLine(x,y,x,y);
			}
		}
		int rwx = (int) (Math.abs(w.getScreenX()) * 33.333333 / (PavoHelper.getGameWidth(w.getWorldSize()) * 100))*3;
		int rwy = (int) (Math.abs(w.getScreenY()) * 33.333333 / (PavoHelper.getGameHeight(w.getWorldSize()) * 100))*3;
		g.setColor(Color.red);
		g.fillRect(rwx-1,rwy-1,2,2);
	}
}
