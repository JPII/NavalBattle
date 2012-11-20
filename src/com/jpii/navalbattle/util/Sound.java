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