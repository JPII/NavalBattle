/**
 * 
 */
package com.jpii.navalbattle.pavo;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.renderer.Helper;

/**
 * @author MKirkby
 *
 */
public class GameWindow extends Renderable {
	Color bck_clr;
	boolean showTitle;
	String title;
	public GameWindow() {
		bck_clr = new Color(193,172,134);
		showTitle = true;
		title = "GameWindow";
	}
	public void render() {
		buffer = new BufferedImage(getWidth()+1,getHeight()+1,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = PavoHelper.createGraphics(buffer);
		g.setColor(Color.black);
		g.drawRect(0,0,getWidth(),getHeight());
		g.setColor(getBackgroundColor());
		g.fillRect(1,1,getWidth()-2,getHeight()-2);
		if (isTitleShown()) {
			g.setColor(getBackgroundColor().darker().darker());
			g.fillRect(1,1,getWidth()-2,24);
			g.setColor(Color.black);
			g.drawRect(0,0,getWidth(),24);
			BufferedImage adapter = new BufferedImage(getWidth()-2,24,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = PavoHelper.createGraphics(adapter);
			g2.setColor(Color.black);
			g2.setFont(Helper.GUI_GAME_FONT);
			g2.drawString(title,3,20);
			g.drawImage(adapter, 1,1, null);
			g.setColor(new Color(126,105,65));
			g.fillRect(getWidth()-23,2,20,20);
			g.setColor(Color.black);
			g.drawRect(getWidth()-23,2,20,20);
			g.setColor(Color.white);
			g.drawLine(getWidth()-20,5,getWidth()-6,19);
			g.drawLine(getWidth()-6,5,getWidth()-20,19);
		}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String titl) {
		title = titl;
	}
	public boolean isTitleShown() {
		return showTitle;
	}
	public void setTitleVisiblity(boolean b) {
		showTitle = b;
	}
	public Color getBackgroundColor() {
		return bck_clr;
	}
	public void setBackgroundColor(Color colour) {
		bck_clr = colour;
	}
	public void setWidth(int w) {
		width = w;
		render();
	}
	public void setHeight(int h) {
		height = h;
		render();
	}
	public void setSize(int w, int h) {
		width = w;
		height = h;
		render();
	}

}
