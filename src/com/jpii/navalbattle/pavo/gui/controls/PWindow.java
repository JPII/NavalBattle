/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.io.PavoImage;
import com.jpii.navalbattle.renderer.Helper;

/**
 * @author maximusvladimir
 *
 */
public class PWindow extends Control {
	private boolean showTitle = true;
	private String title = "";
	
	/**
	 * @param parent
	 */
	public PWindow() {
		super(null);
		createBuffer(false);
		setText("control #" + alo_livrezon_pa_pèmèt());
		setSize(100,100);
		repaint();
	}
	
	public PWindow(int x, int y) {
		super(null);
		createBuffer(false);
		setSize(100,100);
		setLoc(x,y);
		repaint();
	}
	
	public PWindow(int x, int y, int width, int height) {
		super(null);
		createBuffer(false);
		setSize(width,height);
		setLoc(x,y);
		repaint();
	}
	

	
	public void paint(Graphics2D g) {
		g.setColor(getBackgroundColor());
		g.fillRect(1,1,getWidth()-2,getHeight()-2);
	}

	
	public void paintAfter(Graphics2D g) {
		g.setColor(getForegroundColor());
		g.drawRect(0,0,getWidth()-1,getHeight()-1);
		if (isTitleShown()) {
			GradientPaint gradient = new GradientPaint(0,0,getBackgroundColor().darker().darker(),0,24
					,getBackgroundColor().darker().darker().darker());
			g.setPaint(gradient);
			//g.setColor(getBackgroundColor().darker().darker());
			g.fillRect(1,1,getWidth()-2,24);
			g.setPaint(null);
			g.setColor(getForegroundColor());
			g.drawRect(0,0,getWidth()-1,24);
			PavoImage adapter = new PavoImage(getWidth()-2,24,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = PavoHelper.createGraphics(adapter);
			g2.setColor(getBackgroundColor());
			g2.setFont(Helper.GUI_GAME_FONT);
			g2.drawString(title,3,20);
			g.drawImage(adapter, 1,1, null);
			g.setColor(new Color(126,105,65));
			g.fillRect(getWidth()-23,2,20,20);
			g.setColor(getForegroundColor());
			g.drawRect(getWidth()-23,2,20,20);
			g.setColor(Color.white);
			g.drawLine(getWidth()-20,5,getWidth()-6,19);
			g.drawLine(getWidth()-6,5,getWidth()-20,19);
		}
	}
	
	public void setText(String text) {
		if (!title.equals(text)) {
			title = text;
			paintUpdate();
		}
	}
	
	public String getText() {
		return title;
	}
	
	public boolean isTitleShown() {
		return showTitle;
	}
	
	public void setTitleVisiblity(boolean b) {
		if (b != showTitle) {
			showTitle = b;
			paintUpdate();
		}
	}
}
