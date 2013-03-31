package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Color;
import java.awt.Graphics2D;

public class PFrame extends Control {

	/**
	 * @param parent
	 */
	public PFrame(Control parent) {
		super(parent);
		createBuffer(false);
		setForegroundColor(new Color(65,54,33));
		repaint();
	}

	/**
	 * @param parent
	 * @param x
	 * @param y
	 */
	public PFrame(Control parent, int x, int y) {
		super(parent, x, y);
		createBuffer(false);
		setLoc(x,y);
		setForegroundColor(new Color(65,54,33));
		repaint();
	}

	/**
	 * @param parent
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public PFrame(Control parent, int x, int y, int width, int height) {
		super(parent, x, y, width, height);
		createBuffer(false);
		setLoc(x,y);
		setSize(width,height);
		setForegroundColor(new Color(65,54,33));
		repaint();
	}
	
	public void paint(Graphics2D g) {
		super.paint(g);
		drawFrame(g);
	}
	
	public void drawFrame(Graphics2D g) {
		g.setColor(new Color(126,105,65));
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(new Color(65,54,33));
		for (int x22 = 8; x22 < getWidth()-8; x22 += 8) {
			g.drawLine(x22,0,x22+4,8);
		}
		for (int x22 = 8; x22 < getWidth()-8; x22 += 8) {
			g.drawLine(x22+4,getHeight()-9,x22,getHeight());
		}
		for (int y22 = 8; y22 < getHeight()-8; y22 += 8) {
			g.drawLine(0,y22,8,y22+4);
		}
		for (int y22 = 8; y22 < getHeight()-8; y22 += 8) {
			g.drawLine(getWidth()-9,y22+4,getWidth(),y22);
		}
		g.setColor(new Color(169,140,86));
		g.fillRect(8,8,getWidth()-16,getHeight()-16);
		g.setColor(Color.black);
		g.drawRect(0,0,getWidth()-1,getHeight()-1);
		g.drawRect(8,8,getWidth()-16,getHeight()-16);
	}
}
