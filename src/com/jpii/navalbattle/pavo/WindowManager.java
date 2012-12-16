package com.jpii.navalbattle.pavo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class WindowManager extends Renderable{
	ArrayList<GameWindow> wins;
	MessageBox context = null;
	public WindowManager() {
		wins = new ArrayList<GameWindow>();
		Inst = this;
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
	public void ianOwjej10nJAnin345soaKOEe9201LIQUICK(MessageBox Ijsn9j20OKan01nJFNAnia) {
		context = Ijsn9j20OKan01nJFNAnia;
	}
	public static WindowManager Inst;
	public boolean mouseDown(MouseEvent me) {
		int mx = me.getX();
		int my = me.getY();
		boolean flag = false;
		if (context != null) {
			GameWindow gw = context;
			if (mx >= gw.getWidth()-23+gw.getX() && mx <= gw.getWidth()-3+gw.getX() && my >= gw.getY() + 2 && my <= gw.getY() + 20) {
				gw.onCloseCalled();
				context = null;
			}
			if (gw.checkOtherDown(me)) {
				context = null;
			}
			return true;
		}
		for (int c = 0; c < wins.size(); c++) {
			GameWindow gw = wins.get(c);
			if (gw!=null) {
				gw.mouseDown(me);
				if (gw.needsShutdown())
					flag = true;
			}
		}
		return flag;
	}
	public boolean mouseDragged(MouseEvent me) {
		int mx = me.getX();
		int my = me.getY();
		boolean flag = false;
		if (context != null) {
			GameWindow gw = context;
			if (gw.isTitleShown() && gw.isVisible()) {
				if (mx >= gw.getX() - 10 && mx <= gw.getX()+gw.getWidth()+10 && my >= gw.getY()-10 && my <= gw.getY()+34) {
					gw.setLoc(mx - (gw.getWidth()/2), my - 12);
				}
			}
			return true;
		}
		for (int c = 0; c < wins.size(); c++) {
			GameWindow gw = wins.get(c);
			if (gw!=null) {
				if (gw.isTitleShown() && gw.isVisible()) {
					if (mx >= gw.getX() - 10 && mx <= gw.getX()+gw.getWidth()+10 && my >= gw.getY()-10 && my <= gw.getY()+34) {
						gw.setLoc(mx - (gw.getWidth()/2), my - 12);
						return true;
					}
				}
			}
		}
		return flag;
	}
	BufferedImage grided = null;
	int lwsw = 0;
	int lwsh = 0;
	public void render() {
		buffer = new BufferedImage(DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT,BufferedImage.TYPE_INT_ARGB);
		if (grided == null || lwsw != DynamicConstants.WND_WDTH || lwsh != DynamicConstants.WND_HGHT) {
			grided = new BufferedImage(DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g4 = PavoHelper.createGraphics(grided);
			g4.setColor(new Color(200,200,180,90));
			g4.fillRect(0,0,DynamicConstants.WND_WDTH,DynamicConstants.WND_HGHT);
			g4.setColor(new Color(20,20,45));
			for (int x = 0; x < DynamicConstants.WND_WDTH*2; x += 8) {
				g4.drawLine(x,0,0,x);
			}
			lwsw = DynamicConstants.WND_WDTH;
			lwsh = DynamicConstants.WND_HGHT;
		}
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
		
		if (context != null) {
			g2.drawImage(grided, 0, 0, null);
			GameWindow gw = context;
			int gwx = gw.getX();
			int gwy = gw.getY();
			BufferedImage gwb = gw.getBuffer();
			g2.drawImage(gwb, gwx,gwy, null);
		}
	}
}
