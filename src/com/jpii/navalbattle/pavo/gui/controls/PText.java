/**
 * 
 */
package com.jpii.navalbattle.pavo.gui.controls;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author maximusvladimir
 * Text display class.
 */
public class PText extends Control {

	private String text = "";
	private boolean textUpdated = false;
	private static final Color clr_ = new Color(193,172,134).darker().darker();
	private int maxStrWidth = 0;
	private boolean heldDown = false;
	private boolean autoResize = true;
	
	/**
	 * @param parent
	 */
	public PText(Control parent) {
		super(parent);
		createBuffer(true);
	}
	
	public PText(Control parent, String text) {
		super(parent);
		createBuffer(true);
		allowAutoResize(true);
		setText(text);
		repaint();
	}
	
	public PText(Control parent, int x, int y) {
		super(parent);
		createBuffer(true);
		allowAutoResize(true);
		setLoc(x,y);
		repaint();
	}
	
	public PText(Control parent, String text, int x, int y) {
		super(parent);
		createBuffer(true);
		allowAutoResize(true);
		setText(text);
		setLoc(x,y);
		repaint();
	}
	
	public PText(Control parent, int x, int y, int width, int height) {
		super(parent);
		createBuffer(true);
		setLoc(x,y);
		setSize(width,height);
		repaint();
	}
	
	public PText(Control parent, String text, int x, int y, int width, int height) {
		super(parent);
		createBuffer(true);
		setText(text);
		setLoc(x,y);
		repaint();
		setSize(width,height);
	}
	
	public void setAutoSize(boolean v) {
		autoResize = v;
		repaint();
	}
	
	public boolean getAutoSize() {
		return autoResize;
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
		String[] lines = null;
		if (getText().indexOf("\n") > -1)
			lines = getText().split("\n");
		else
			lines = new String[] {getText()};
		if (textUpdated) {
			maxStrWidth = 0;
			for (int v = 0; v < lines.length; v++) {
				int m = g.getFontMetrics().stringWidth(lines[v]);
				if (m > maxStrWidth)
					maxStrWidth = m;
			}
			if (autoResize) {
				this.width = 4 + (maxStrWidth);
				this.height = ((getFont().getSize()+2) * lines.length)+2;
				createBuffer(lastKnownTransMode);
			}
			
			textUpdated = false;
			bufferNeedsIntemediatePaint();
		}
		g.setColor(getForegroundColor());
		for (int v = 0; v < lines.length; v++) {
			g.drawString(lines[v], 2, ((v+1)*(getFont().getSize()+2)));
		}
	}
	
	
	public String getText() {
		return text;
	}
}
