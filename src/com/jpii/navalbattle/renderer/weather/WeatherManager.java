package com.jpii.navalbattle.renderer.weather;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.pavo.GameBeta;
import com.jpii.navalbattle.pavo.PavoHelper;

public class WeatherManager {
	WeatherMode wm;
	BufferedImage buffer;
	RainDrop[] rain;
	public WeatherManager() {
		wm = WeatherMode.Sunny;
		repopulateRain();
	}
	public void setWeather(WeatherMode wm) {
		this.wm = wm;
		update();
	}
	public WeatherMode getWeather() {
		return wm;
	}
	private void repopulateRain() {
		rain = new RainDrop[GameBeta.Settings.rand.nextInt(150,200)];
		int dir = GameBeta.Settings.rand.nextInt(-6,6);
		for (int r = 0; r < rain.length; r++) {
			rain[r] = new RainDrop(dir,GameBeta.Settings.currentWidth,GameBeta.Settings.currentHeight);
		}
	}
	public void update() {
		if (getWeather() == WeatherMode.Raining) {
			buffer = new BufferedImage(GameBeta.Settings.currentWidth,
					GameBeta.Settings.currentHeight,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = PavoHelper.createGraphics(buffer);
			g.setStroke(new BasicStroke(2.5f));
			for (int r = 0; r < rain.length; r++) {
				RainDrop rd = rain[r];
				int inc = rd.length / 5;
				int sws = rd.dir;
				rd.y1 += inc;
				rd.y2 += inc;
				rd.x1 += sws;
				rd.x2 += sws;
				if (rd.y1 > GameBeta.Settings.currentHeight) {
					rd.y1 -= (GameBeta.Settings.currentHeight + 60);
					rd.y2 -= (GameBeta.Settings.currentHeight + 60);
				}
				rain[r] = rd;
				g.setColor(rd.colour);
				g.drawLine(rd.x1,rd.y1,rd.x2,rd.y2);
			}
		}
		else
			buffer = null;
	}
	public BufferedImage getBuffer() {
		if (getWeather() == WeatherMode.Sunny)
			return null;
		else
			return buffer;
	}
}
