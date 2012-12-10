/**
 * 
 */
package com.jpii.navalbattle.pavo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.jpii.navalbattle.renderer.Helper;

/**
 * @author MKirkby
 *
 */
public class MessageBox extends com.jpii.navalbattle.pavo.GameWindow {
	String message = "no msg";
	private MessageBox(String title, String message) {
		super();
		setTitle(title);
		this.message = message;
		String[] lines = message.split("\n");
		int lg = 0;
		for (int v = 0; v < lines.length; v++) {
			if (lines[v].length() > lg) {
				lg = lines[v].length();
			}
		}
		int w = lg * 10;
		int h = 125 + (16 * lines.length);
		setSize(w,h);
		setLoc((DynamicConstants.WND_WDTH/2)-(w/2),(DynamicConstants.WND_HGHT/2)-(h/2));
		render();
	}
	public static void show(String title, String message) {
		WindowManager.Inst.add(new MessageBox(title,message));
	}
	public void render() {
		super.render();
		Graphics2D g = PavoHelper.createGraphics(getBuffer());
		g.setColor(Color.black);
		g.setFont(Helper.GUI_GAME_FONT);
		String[] lines = message.split("\n");
		for (int v = 0; v < lines.length; v++) {
			g.drawString(lines[v], 5, 40 + (v*14));
		}
		g.setColor(getBackgroundColor().darker().darker());
		g.fillRoundRect((getWidth()/2)-30, (getHeight()-36), 60, 24, 5,5);
		g.setColor(Color.black);
		g.drawRoundRect((getWidth()/2)-30, (getHeight()-36), 60, 24, 5,5);
		g.drawString("OK",(getWidth()/2)-8, (getHeight()-18));
	}
	public void checkOtherDown(MouseEvent me) {
		int mx = me.getX();
		int my = me.getY();
		if (mx >= (getWidth()/2)-30+getX() && mx <= (getWidth()/2)+30+getX() && my >= (getHeight()-36)+getY() && my <= (getHeight()-18)+getY()) {
			onCloseCalled();
		}
	}
	public void onCloseCalled() {
		getWinMan().remove(this);
	}
}
