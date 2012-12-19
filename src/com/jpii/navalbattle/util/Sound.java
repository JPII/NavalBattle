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

package com.jpii.navalbattle.util;

import java.io.*;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound
{
	private Clip clip;
	boolean done;
	
	public Sound()
	{
		done = false;
		File f = null;
		URL url = Sound.class.getResource("/com/jpii/navalbattle/res/thunderstruck.wav");
		try 
		{
			f= new File(replace(url.getFile()));
			AudioInputStream audio = AudioSystem.getAudioInputStream(f);
			clip = AudioSystem.getClip();
			clip.open(audio);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch (Exception e) {e.printStackTrace(); System.out.println(f.getAbsolutePath());}
	}
	private String replace(String s){
			return s.replaceFirst("/", "").replaceAll("%20", " ");
	}
}