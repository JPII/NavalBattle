/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Graphics2D;

/**
 * @author maximusvladimir
 *
 */
public class PWindow extends Control {
	boolean showTitle = true;
	
	/**
	 * @param parent
	 */
	public PWindow(Control parent) {
		super(parent);
	}
	
	public void paint(Graphics2D g) {
		
	}
	
	public boolean isTitleShown() {
		return showTitle;
	}
	
	public void setTitleVisiblity(boolean b) {
		showTitle = b;
	}
}
