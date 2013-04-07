/**
 * 
 */
package com.jpii.navalbattle.pavo.boost;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JEditorPane;

import com.jpii.navalbattle.pavo.gui.controls.Control;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

/**
 * @author maximusvladimir
 *
 */
public class _Boost {
	public BufferedImage buffer;
	PWindow wnd;
	int width, height;
	Control selected;
	JEditorPane jep;
	public _Boost(JEditorPane jep) {
		this.jep = jep;
		resize(800-144,600-177);
	}
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		wnd = new PWindow(null);
		wnd.setLoc((width/2)-(wnd.getWidth()/2),(height/2)-(wnd.getHeight()/2));
		buffer = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
	}
	int lwmx = 0;
	int lwmy = 0;
	boolean md = false;
	public void mouseDown(int x, int y,int button) {
		int wmx = x - wnd.getLocX();
		int wmy = y - wnd.getLocY();
		md = false;
		if (wmx > 0 && wmx < wnd.getWidth() && wmy > 0 && wmy < 23) {
			lwmx = wmx;
			lwmy = wmy;
			md = true;
		}
	}
	
	public void mouseDrag(int x, int y,int button) {
		if (md) {
			wnd.setLoc((x-wnd.getLocX())-lwmx+wnd.getLocX(),(y-wnd.getLocY())-lwmy+wnd.getLocY());
			compile();
		}
	}
	
	public void mouseUp(int x, int y,int button) {
		
	}
	
	public void compile() {
		String t = "import com.jpii.navalbattle.pavo.gui.control.*;";
		String line = "\r\n\t\t";
		t += "\r\n";
		t += "public class MyWindow extends PWindow {";
		t += "\r\n\r\n\tpublic MyWindow() {";
		t += line;
		t += "setLoc(" + wnd.getLocX() + ", " + wnd.getLocY() + ");";
		t += line;
		t += "setSize(" + wnd.getWidth() + ", " + wnd.getHeight() + ");";
		t += line;
		t += "setText(\"" + wnd.getText() + "\");";
		
		t += "\r\n\t}";
		t += "\r\n}";
		
		jep.setText(t);
	}
	
	public Control getActiveControl() {
		if (selected == null)
			return wnd;
		else
			return selected;
	}
	
	public void render() {
		Graphics g = buffer.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0,0,width,height);
		g.drawImage(wnd.getBuffer(),wnd.getLocX(),wnd.getLocY(),null);
	}
}
