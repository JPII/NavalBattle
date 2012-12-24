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

package com.jpii.navalbattle.debug;

import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.io.SettingsAttribute;
import com.jpii.navalbattle.io.SettingsReader;
import com.jpii.navalbattle.pavo.FastMath;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.util.FileUtils;
import com.jpii.navalbattle.util.GrammarManager;

import java.awt.event.*;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import maximusvladimir.dagen.Rand;

/**
 * Added to prevent conflicting diffs in NavalBattle.java. This is Max's file to play with.
 * @author MKirkby
 *
 */
public class MaxTests {
	
	/**
	 * Run <code>MaxTests</code>.
	 */
	public static void run() {
		Rand r = new Rand();
		for (int c = 0; c < 3; c++) {
			System.out.println(GrammarManager.generateFullName(r.nextInt()));
		}
		System.out.println("Fast Math calc: " + FastMath.sin(0.14235));
		System.out.println("Java Math calc: " + Math.sin(0.14235*(Math.PI/2)));
		System.out.println("Seed:" + Game.Settings.seed);
		
		if (isFirstRun()) {
			String settingsPath = getSettingsPath();
			File opt = new File(settingsPath);
			boolean res = false;
			try {
				res = opt.createNewFile();
				try{
				java.io.FileWriter writor = new FileWriter(opt);
				String c = System.getProperty("line.separator");
				writor.write("# Settings File for NavalBattle# NavalBattle is released under GNU.\n#\n# " +
						"Be sure to make a backup, "
						+"bring a cup of English Afternoon Tea, and\n# turn up the music, if you chose to " +
						"modify this file, understanding"
						+"\n# that modifying is at your own risk.\n# Login data:\nlastGoodUserName: notch" +
						"\nlastGoodPassword:" +
						"<just kidding>\n# Game window size:\ngameWindowWidth: 800\ngameWindowHeight: 600");
				writor.close();}
				catch(Throwable throwable) {
					System.out.println("A serious error has occured with NavalBattle, and it must close: " + throwable.getMessage());
				}
			}
			catch (Throwable threw) {
				System.out.println("A serious error has occured with NavalBattle, and it must close: " + threw.getMessage());
			}
		}
		else {
			ArrayList<SettingsAttribute> attrs = new ArrayList<SettingsAttribute>();
			SettingsAttribute a = new SettingsAttribute("lastGoodUserName");
			attrs.add(a);
			a = new SettingsAttribute("lastGoodPassword");
			attrs.add(a);
			a = new SettingsAttribute("gameWindowWidth");
			attrs.add(a);
			a = new SettingsAttribute("gameWindowHeight");
			attrs.add(a);
			java.net.URL url = null;
			File f = null;
			try {
				//url = new java.net.URL(getSettingsPath());
				f = new File(getSettingsPath());
			} catch (Exception e) { 
				//e.printStackTrace();
			}
			//if (url != null && f.exists()) {
				SettingsReader reader = new SettingsReader(f.getAbsolutePath(),attrs);
				reader.read();
			//}
			for (SettingsAttribute sa : attrs) {
				if (sa != null) {
					System.out.println(sa);
				}
			}
		}
	}
	
	/**
	 * Returns if program is running for the first time.
	 * @return
	 */
	public static boolean isFirstRun() {
		if (!new File(getSettingsPath()).exists())
			return true;
		else
			return false;
	}
	
	/**
	 * Returns settings file path.
	 * @return
	 */
	public static String getSettingsPath() {
		return (FileUtils.getSavingDirectory().getAbsolutePath()+"\\settings.inf");
	}
}