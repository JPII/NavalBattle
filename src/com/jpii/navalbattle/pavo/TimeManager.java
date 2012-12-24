/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.navalbattle.pavo;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TimeManager extends Renderable {
	public static int DayNightTotalLengthSeconds = 120;
	public static int NightDarkness = 80;
	private int hour = 0;
	private String desc = "";
	private double currentTime = ((double)DayNightTotalLengthSeconds)/2;
	private Color cdr = new Color(0,0,0,0);
	private Color lcd = new Color(0,0,0,0);
	private int timeD = 0;
	private int minute = 0;
	private int lsw, lsh;
	public TimeManager() {
		lsw = Game.Settings.currentWidth;
		lsh = Game.Settings.currentHeight;
	}
	public void update() {
		int le = DayNightTotalLengthSeconds;
    	int alph = 0;
    	double tofd = currentTime;
    	if (currentTime < DayNightTotalLengthSeconds)
    		currentTime += (DayNightTotalLengthSeconds/1000.0);
    	else
    		currentTime = 0;
    	int nightl = 5;
    	if (tofd > 0 && tofd < le / nightl/2)
    	{
    		double t = tofd;
    		alph = (int)(t * NightDarkness / (le / nightl/2));
    		if (alph < 0)
    			alph = 0;
    		if (alph > 255)
    			alph = 255;
    		desc = "Sunset";
    		timeD = 0;
    	}
    	else if (tofd > le / nightl/2 && tofd < le / nightl * 2)
    	{
    		alph = NightDarkness;
    		desc = "Night";
    		timeD = 1;
    	}
    	else if (tofd > le / nightl * 2 && tofd < (le / nightl * 2) + (le / nightl / 2))
    	{
    		double t = ((le / nightl * 2) + (le / nightl / 2))- tofd;
    		alph = (int)(t * NightDarkness / (le / nightl /2));
    		if (alph < 0)
    			alph = 0;
    		if (alph > 255)
    			alph = 255;
    		desc = "Sunrise";
    		timeD = 2;
    	}
    	else {
    		desc = "Day";
    		timeD = 3;
    	}
    	cdr = new Color(27,31,58,alph);
    	if (!lcd.equals(cdr) || lsw != Game.Settings.currentWidth || lsh != Game.Settings.currentHeight) {
    		buffer = new BufferedImage(Game.Settings.currentWidth,Game.Settings.currentHeight,BufferedImage.TYPE_INT_ARGB);
    		lsw = Game.Settings.currentWidth;
    		lsh = Game.Settings.currentHeight;
    		Graphics g = buffer.getGraphics();
    		g.setColor(cdr);
    		g.fillRect(0,0,Game.Settings.currentWidth,Game.Settings.currentHeight);
    		lcd = cdr;
    	}
    	double thour = (((tofd) * 24) / DayNightTotalLengthSeconds)+18.0;
    	if (thour > 24) {
    		thour = 24 - thour;
    	}
    	if (thour < 0)
    		thour = thour * -1;
    	//minute = ((int)((tofd * 1440) / DayNightTotalLengthSeconds));// - (hour * 60);
    	int d = (int)(thour);
    	minute = (int)((thour - d) * 60);
    	hour = d;
	}
	public int getState() {
		return timeD;
	}
	public String getTimeDescription() {
		return desc;
	}
	public int getCurrentHour() {
		return hour;
	}
	public int getCurrentMinutes() {
		return minute;
	}
}
