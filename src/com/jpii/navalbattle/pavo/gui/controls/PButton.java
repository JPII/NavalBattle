/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * @author maximusvladimir
 * Button class.
 */
public class PButton extends Control {
	private String text = "";
	private boolean textUpdated = false;
	private int strWidth = 0;
	private boolean heldDown = false;
	private boolean autoResize = true;
	/**
	 * 
	 */
	public PButton(Control parent) {
		super(parent);
		createBuffer(true);
		setBackgroundColor(new Color(193,172,134).darker().darker());
	}
	
	public PButton(Control parent, String text) {
		super(parent);
		createBuffer(true);
		setBackgroundColor(new Color(193,172,134).darker().darker());
		setText(text);
	}
	
	public PButton(Control parent, String text, int x, int y) {
		super(parent);
		createBuffer(true);
		setBackgroundColor(new Color(193,172,134).darker().darker());
		setText(text);
		setLoc(x,y);
	}
	
	public PButton(Control parent, int x, int y) {
		super(parent);
		createBuffer(true);
		setBackgroundColor(new Color(193,172,134).darker().darker());
		setLoc(x,y);
	}
	
	public PButton(Control parent, String text, int x, int y, int width, int height) {
		super(parent);
		createBuffer(true);
		setBackgroundColor(new Color(193,172,134).darker().darker());
		setText(text);
		setLoc(x,y);
		autoResize = false;
		setSize(width,height);
	}
	
	public PButton(Control parent, int x, int y, int width, int height) {
		super(parent);
		createBuffer(true);
		setBackgroundColor(new Color(193,172,134).darker().darker());
		setLoc(x,y);
		autoResize = false;
		setSize(width,height);
	}
	
	public void setAutoSize(boolean v) {
		autoResize = v;
		repaint();
	}
	
	public boolean getAutoSize() {
		return autoResize;
	}
	
	public void setFont(Font f) {
		if (!f.equals(getFont())) {
			textUpdated = true;
			super.setFont(f);
		}
	}
	
	public void paint(Graphics2D g) {
		g.setFont(getFont());
		if (textUpdated) {
			strWidth = g.getFontMetrics().stringWidth(getText());
			textUpdated = false;
			if (autoResize) {
				this.width = strWidth+9;
				this.height = (int)(getFont().getSize() * 1.5f)+1;
				createBuffer(true);
				bufferNeedsIntemediatePaint();
			}
		}
		//int mid = ((strWidth+8)/2) - (strWidth/2);
		int vwidth = strWidth+8;
		if (vwidth < width)
			vwidth = width-1;
		int vheight = (int)(getFont().getSize() * 1.5f);
		if (vheight < height)
			vheight = height-1;
		if (heldDown) {
			GradientPaint gp = new GradientPaint(0,0,new Color(134,111,68),0,(getFont().getSize() * 1.5f),new Color(87,72,45));
			g.setPaint(gp);
			g.fillRoundRect(0,0,vwidth,vheight, 5,5);
			g.setPaint(null);
			g.setColor(Color.black);
			g.drawRoundRect(0,0,vwidth,vheight, 5,5);
			g.setColor(Color.lightGray);
			g.drawString(getText(), ((getWidth()/2) - (strWidth/2)),(int)(getFont().getSize() * 1.5f)- (getFont().getSize()/2)+2);
		}
		else {
			GradientPaint gp = new GradientPaint(0,0,new Color(169,140,86),0,(getFont().getSize() * 1.5f),new Color(126,105,65));
			g.setPaint(gp);
			g.fillRoundRect(0,0,vwidth,vheight, 5,5);
			g.setPaint(null);
			g.setColor(Color.black);
			g.drawRoundRect(0,0,vwidth,vheight, 5,5);
			g.drawString(getText(), ((getWidth()/2) - (strWidth/2)),(int)(getFont().getSize() * 1.5f)- (getFont().getSize()/2)+2);
		}
	}
	
	public void setText(String text) {
		if (text != null) {
			createBuffer(true);
			if (!this.text.equals(text))
				textUpdated = true;
			this.text = text;
			paintUpdate();
		}
	}
	
	public String getText() {
		return text;
	}
	
	public void onMouseDown(int x, int y, int buttonid) {
		super.onMouseDown(x, y, buttonid);
		
		if (buttonid == MouseEvent.BUTTON1) {
			heldDown = true;
			paintUpdate();
		}
	}
	
	public void onMouseUp(int x,int y, int buttonid) {
		super.onMouseUp(x, y, buttonid);
		if (buttonid == MouseEvent.BUTTON1) {
			heldDown = false;
			paintUpdate();
			onButtonPressed();
		}
	}
	
	public void onButtonPressed() {
		
	}
}
