/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Graphics2D;

/**
 * @author maximusvladimir
 *
 */
public class PButton extends Control {
	private String text = "";
	/**
	 * 
	 */
	public PButton(Control parent) {
		super(parent);
	}
	
	public void paint(Graphics2D g) {
		
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
}
