package com.jpii.navalbattle.pavo;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class WindowManager extends Renderable{
	ArrayList<GameWindow> wins;
	public WindowManager() {
		wins = new ArrayList<GameWindow>();
	}
	public void add(GameWindow wnd) {
		wins.add(wnd);
	}
	public void remove(GameWindow wnd) {
		wins.remove(wnd);
	}
	public GameWindow get(int index) {
		return wins.get(index);
	}
	public int size() {
		return wins.size();
	}
	public void mouseMove(MouseEvent me) {
		
	}
	public boolean mouseDown(MouseEvent me) {
		int mx = me.getX();
		int my = me.getY();
		boolean flag = false;
		for (int c = 0; c < wins.size(); c++) {
			GameWindow gw = wins.get(c);
			if (gw!=null) {
				if (gw.isTitleShown()) {
					if (mx >= gw.getWidth()-23+gw.getX() && mx <= gw.getWidth()-3+gw.getX() && my >= gw.getY() + 2 && my <= gw.getY() + 20) {
						gw.onCloseCalled();
						flag = true;
					}
				}
			}
		}
		return flag;
	}
	public void mouseUp(MouseEvent me) {
		
	}
	public boolean mouseDragged(MouseEvent me) {
		int mx = me.getX();
		int my = me.getY();
		boolean flag = false;
		for (int c = 0; c < wins.size(); c++) {
			GameWindow gw = wins.get(c);
			if (gw!=null) {
				if (gw.isTitleShown()) {
					if (mx >= gw.getX() - 10 && mx <= gw.getX()+gw.getWidth()+10 && my >= gw.getY()-10 && my <= gw.getY()+34) {
						gw.setLoc(mx - (gw.getWidth()/2), my - 12);
						flag = true;
					}
				}
			}
		}
		return flag;
	}
	public void render() {
		buffer = new BufferedImage(DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = PavoHelper.createGraphics(getBuffer());
		for (int c = 0; c < wins.size(); c++) {
			GameWindow gw = wins.get(c);
			if (gw!=null) {
				if (gw.isVisible()) {
					int gwx = gw.getX();
					int gwy = gw.getY();
					BufferedImage gwb = gw.getBuffer();
					g2.drawImage(gwb, gwx,gwy, null);
				}
			}
		}
	}
}
