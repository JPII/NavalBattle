package com.jpii.navalbattle.gui.cotton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class NavButton extends JComponent implements MouseListener{
	
	boolean isHovering = false;
	
	public NavButton() {
		super();
		enableInputMethods(true);   
		addMouseListener(this);
	}
	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D)g2;
		g.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		int x = getLocation().x;
		int y = getLocation().y;
		int w = getWidth();
		int h = getHeight();
		//Paint p;
		/*Rectangle t = new Rectangle(0,0, getWidth(), getHeight());
        Point2D start = new Point2D.Float(t.x, t.y);
        Point2D end = new Point2D.Float(t.width, t.height);
        Color[] colors = {Color.magenta, Color.blue, Color.cyan,
            Color.green, Color.yellow, Color.red};
        float[] fracs = {0.0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f};
        p = new LinearGradientPaint(start, end, fracs, colors);
        g.setPaint(p);*/
		if (!isHovering) {
			int insw = w / 4;
			int insh = h / 4;
			int rgba = 120;
			for (int y2 = 0; y2 < insh; y2 ++) {
				if (y2 < insh / 2) {
					rgba -= (40 / insh);
				}
				else {
					rgba += (40/insh);
				}
				for (int x2 = 0; x2 < insw; x2++) {
					if (x2 == 0 || y2 == 0 || x2 == insw - 1 || y2 == insh - 1) {
						g.setColor(new Color(70,70,70,220));
					}
					else {
						g.setColor(new Color(rgba,rgba,rgba,220));
					}
					g.fillRect(x2*4, y2*4, 4,4);
				}
			}
		}
		else {
			int insw = w / 4;
			int insh = h / 4;
			int rgba = 120;
			for (int y2 = 0; y2 < insh; y2 ++) {
				if (y2 < insh / 2) {
					rgba -= (60 / insh);
				}
				else {
					rgba += (60/insh);
				}
				for (int x2 = 0; x2 < insw; x2++) {
					if (x2 == 0 || y2 == 0 || x2 == insw - 1 || y2 == insh - 1) {
						g.setColor(new Color(40,40,40));
					}
					else {
						g.setColor(new Color(rgba,rgba,rgba));
					}
					g.fillRect(x2*4, y2*4, 4,4);
				}
			}
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
		isHovering = true;
	}

	public void mouseExited(MouseEvent arg0) {
		isHovering = false;
	}

	public void mousePressed(MouseEvent arg0) {
	}
	
	public void mouseReleased(MouseEvent arg0) {
	}

}
