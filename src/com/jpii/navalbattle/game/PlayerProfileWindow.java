/**
 * 
 */
package com.jpii.navalbattle.game;

import java.awt.*;

import com.jpii.navalbattle.pavo.*;
import com.roketgamer.util.PlayerUtils;

/**
 * @author MKirkby
 *
 */
public class PlayerProfileWindow extends GameWindow {
	public PlayerProfileWindow() {
		super();
		setSize(300,200);
		setTitle("Dave's player profile.");
		render();
	}
	public void render() {
		super.render();
		Graphics2D g = PavoHelper.createGraphics(getBuffer());
		g.setColor(new Color(126,105,65));
		g.fillRect(10,34,100,100);
		g.setColor(new Color(104,77,60));
		g.drawRect(10,34,100,100);
		g.setColor(new Color(169,140,86));
		g.fillRect(20,44,80,80);
		g.setColor(new Color(104,77,60));
		g.drawRect(20,44,80,80);
		// Frame stuff
		g.setColor(new Color(65,54,33));
		for (int v = 0; v < 80; v+= 8) {
			g.drawLine(11,v+48,19,v+44);
		}
		for (int v = 0; v < 80; v+= 8) {
			g.drawLine(v+24,35,v+20,43);
		}
		for (int v = 0; v < 80; v+= 8) {
			g.drawLine(101,v+48,109,v+44);
		}
		for (int v = 0; v < 80; v+= 8) {
			g.drawLine(v+24,35+90,v+20,43+90);
		}
		g.setColor(Color.black);
		g.drawString("<no avatar>",27,87);
		g.drawString("Captain Dave", 21, 152);
		g.drawString("Rank: Captain", 135, 44);
		g.drawString("Money: £1 630 000", 135, 59);
		g.drawString("RG Id: shis-ka-bob1", 135, 74);
	}
}
