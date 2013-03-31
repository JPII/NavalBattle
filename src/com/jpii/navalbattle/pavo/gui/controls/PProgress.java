package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

/**
 * Progress bar class.
 */
public class PProgress extends Control {

	float progress = 0.5f;
	
	/**
	 * @param parent
	 */
	public PProgress(Control parent) {
		super(parent);
		createBuffer(false);
		repaint();
	}
	
	public PProgress(Control parent, int x, int y) {
		super(parent);
		createBuffer(false);
		setLoc(x,y);
		repaint();
	}
	
	public PProgress(Control parent, int x, int y, int width, int height) {
		super(parent);
		createBuffer(false);
		setLoc(x,y);
		setSize(width,height);
		repaint();
	}
	
	public void setProgress(int value) {
		setProgress(value, 100);
	}
	
	public void setProgress(float value, float outOf) {
		float progress1 = value / outOf;
		if (progress1 != progress) {
			progress = progress1;
			paintUpdate();
		}
	}
	
	public float getProgress() {
		return progress;
	}
	
	public float getProgress(float outOf) {
		return progress * outOf;
	}
	
	public void paint(Graphics2D g) {
		if (getHeight() > 5) {
			g.setColor(getBackgroundColor().darker());
			g.fillRect(0,0,getWidth(),getHeight());
			GradientPaint gradient = new GradientPaint(0,0,new Color(95,166,74),0,getHeight(),new Color(63,107,68));
			g.setPaint(gradient);
			g.fillRect(2,2,(int)(progress*(getWidth()-4)),getHeight()-4);
		}
		else {
			GradientPaint gradient = new GradientPaint(0,0,new Color(95,166,74),0,getHeight(),new Color(63,107,68));
			g.setPaint(gradient);
			g.fillRect(0,0,(int)(progress*(getWidth()-4)),getHeight());
		}
	}
}
