/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author maximusvladimir
 *
 */
public class PText extends Control {

	private String text = "";
	private boolean textUpdated = false;
	private static final Color clr_ = new Color(193,172,134).darker().darker();
	private int maxStrWidth = 0;
	private boolean heldDown = false;
	private boolean autoResize = false;
	
	/**
	 * @param parent
	 */
	public PText(Control parent) {
		super(parent);
		createBuffer(true);
	}
	
	public void setText(String text) {
		if (text != null) {
			if (!this.text.equals(text))
				textUpdated = true;
			this.text = text;
			paintUpdate();
		}
	}
	
	public void allowAutoResize(boolean v) {
		if (autoResize != v)
			paintUpdate();
	}
	
	/**
	 * Gets the minimum required width to comfortably fit the
	 * string on the control.
	 * @return The width
	 */
	public int getMinFitWidth() {
		return maxStrWidth+4;
	}
	
	public void paint(Graphics2D g) {
		g.setFont(getFont());
		String[] lines = getText().split("\n");
		if (textUpdated) {
			for (int v = 0; v < lines.length; v++) {
				int m = g.getFontMetrics().stringWidth(lines[v]);
				if (m > maxStrWidth)
					maxStrWidth = m;
			}
			if (autoResize)
				setSize(4 + (maxStrWidth), ((getFont().getSize()+2) * lines.length)+2);
			
			textUpdated = false;
		}
		g.setColor(getForegroundColor());
		for (int v = 0; v < lines.length; v++) {
			g.drawString(lines[v], 2, (v*(getFont().getSize()+2)));
		}
	}
	
	
	public String getText() {
		return text;
	}
}
