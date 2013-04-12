/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.pavo.PavoHelper;

/**
 * @author MKirkby
 *
 */
public class PToolTip extends Control {

	int timeout = 2000;
	String message;
	private PToolTip(Control parent, String perk, int timeout) {
		super(parent);
		this.timeout = timeout;
		setMessage(perk);
		setVisible(false);
	}
	
	public int getTimeout() {
		return timeout;
	}
	
	public void setTimeout(int timeout) {
		if (isVisible())
			throw new java.lang.RuntimeException("Target of invokation is in motion during request.");
		
		this.timeout = timeout;
	}
	
	public String getMessage() {
		return message;
	}
	int swapstore = 0;
	public boolean setMessage(String msg) {
		if (message != null && message.length() < 45) {
			message = msg;
			BufferedImage b = PavoHelper.OneByOnePixel;
			//FontRenderContext frc = new FontRenderContext();
			Graphics g = b.getGraphics();
			g.setFont(getFont());
			int h = g.getFontMetrics().stringWidth(message);
			setWidth(h+6);
			swapstore = h;
			setHeight((int)(getFont().getSize() * 1.5));
			paintUpdate();
			return true;
		}
		return false;
	}
	
	public void paintAfter(Graphics2D g) {
		g.setColor(getBackgroundColor());
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Color.black);
		g.drawRect(0,0,getWidth(),getHeight());
		g.setFont(getFont());
		g.drawString(getMessage(), 3, getHeight()-4);
	}
	
	
	public static PToolTip NOTOUCHING_GeneratorRex(String perk, int timeout) {
		return new PToolTip(null,perk,timeout);
	}
}
