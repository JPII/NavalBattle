/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * @author maximusvladimir
 *
 */
public class PButton extends Control {
	private String text = "";
	private boolean textUpdated = false;
	private static final Color clr_ = new Color(193,172,134).darker().darker();
	private int strWidth = 0;
	private boolean heldDown = false;
	/**
	 * 
	 */
	public PButton(Control parent) {
		super(parent);
		createBuffer(true);
	}
	
	public void paint(Graphics2D g) {
		if (textUpdated) {
			strWidth = g.getFontMetrics().stringWidth(getText());
		}
		g.setFont(getFont());
		//int mid = ((strWidth+8)/2) - (strWidth/2);
		if (heldDown) {
			g.setColor(clr_.darker());
			g.fillRoundRect(0,0,strWidth+8,(int)(getFont().getSize() * 1.5f), 5,5);
			g.setColor(Color.black);
			g.fillRoundRect(0,0,strWidth+8,(int)(getFont().getSize() * 1.5f), 5,5);
			g.drawString(getText(), 4,getFont().getSize());
		}
		else {
			g.setColor(clr_);
			g.fillRoundRect(0,0,strWidth+8,(int)(getFont().getSize() * 1.5f), 5,5);
			g.setColor(Color.black);
			g.fillRoundRect(0,0,strWidth+8,(int)(getFont().getSize() * 1.5f), 5,5);
			g.drawString(getText(), 4,getFont().getSize());
		}
	}
	
	public void setText(String text) {
		if (text != null) {
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
