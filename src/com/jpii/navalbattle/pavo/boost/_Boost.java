/**
 * 
 */
package com.jpii.navalbattle.pavo.boost;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.pavo.gui.controls.PWindow;

/**
 * @author maximusvladimir
 *
 */
public class _Boost {
	public BufferedImage buffer;
	PWindow wnd;
	int width, height;
	public _Boost() {
		resize(800,600-177);
	}
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		wnd = new PWindow(null);
		wnd.setLoc((width/2)-(wnd.getWidth()/2),(height/2)-(wnd.getHeight()/2));
		buffer = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
	}
	
	public void mouseDown(int x, int y,int button) {
		
	}
	public void mouseUp(int x, int y,int button) {
		
	}
	
	public void render() {
		Graphics g = buffer.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0,0,width,height);
		g.drawImage(wnd.getBuffer(),wnd.getLocX(),wnd.getLocY(),null);
	}
}
