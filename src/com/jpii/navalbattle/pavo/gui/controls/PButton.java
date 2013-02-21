/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * @author maximusvladimir
 *
 */
public class PButton extends Control {
	private String text = "";
	private boolean heldDown = false;
	/**
	 * 
	 */
	public PButton(Control parent) {
		super(parent);
	}
	
	public void paint(Graphics2D g) {
		if (heldDown) {
			
		}
	}
	
	public void setText(String text) {
		if (text != null) {
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
