package com.jpii.navalbattle.renderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Console extends JFrame {
	private static Console instance = null;
	public static Console getInstance() {
		if (instance == null)
			instance = new Console();
		return instance;
	}
	
	ArrayList<cnsl_attr> attrs;
	
	public Console() {
		setSize(465,365);
		setLocation(0,380);
		setTitle("Rendering Console");
		setVisible(true);
		attrs = new ArrayList<cnsl_attr>();
		attrs.add(new cnsl_attr("Started Console!",0));
	}
	public void paint(Graphics g2) {
		Graphics2D g = (Graphics2D)g2;
		int h = 30;
		
		g.setColor(Color.black);
		g.fillRect(0,0,getWidth(),getHeight());
		g.setFont(Helper.GUI_GAME_FONT);
		g.setColor(new Color(70,70,70));
		g.drawLine(0,18+h,getWidth(),18+h);
		g.setColor(new Color(40,40,40));
		g.drawLine(0,19+h,getWidth(),19+h);
		
		g.setColor(Color.white);
		g.drawString("Seed: " + seed + ". FPS: " + fps,2,18);
		
		int buffer = 40;
		for (int c = (getHeight() - 21) / 18; c > 0; c--) {
			if (c < attrs.size()) {
				cnsl_attr a = attrs.get(c);
				int s = getWidthCnsl(g,a);
				int f = s / getWidth();
				f++;
				if (a.type == 0) {
					g.setColor(Color.yellow);
				}
				else if (a.type == 1) {
					g.setColor(Color.red);
				}
				else
					g.setColor(Color.green);
				g.drawString(a.v, 2, buffer);
				buffer += (f*20);	
			}
		}
	}
	int seed = 0;
	int fps = 0;
	public void setSeed(int seed) {
		this.seed = seed;
	}
	public void setFPS(int fps) {
		this.fps = fps;
	}
	public void printWarn(String v) {
		attrs.add(new cnsl_attr(v,0));
	}
	public void printError(String v) {
		attrs.add(new cnsl_attr(v,1));
	}
	public void printInfo(String v) {
		attrs.add(new cnsl_attr(v,2));
	}
	private int getWidthCnsl(Graphics2D g,cnsl_attr ams) {
		FontMetrics metrics = g.getFontMetrics(Helper.GUI_GAME_FONT);
		return metrics.stringWidth(ams.v);
	}
	public void update(Graphics g) {
		paint(g);
	}
}
class cnsl_attr {
	public String v;
	public int type;
	public cnsl_attr(String v, int type) {
		this.v = v;
		this.type = type;
	}

}
