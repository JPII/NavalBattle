package com.jpii.eightbitsound.test;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

import com.jpii.eightbitsound.SoundMan;
import com.jpii.eightbitsound.StepList;

@SuppressWarnings("serial")
public class SoundTest extends Applet{
	public void init() {
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Playing title menu sound.", 0, 50);
		
		//playTitleMenu();
		//sleep(8000);
		
		g.setColor(Color.white);
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Color.black);
		g.drawString("Playing game over sound.", 0, 50);
		
		//playGameOver();
		//sleep(5000);
		
		g.setColor(Color.white);
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Color.black);
		g.drawString("Playing new turn sound.", 0, 50);
		
		playTitleMenu();
		sleep(1000);
		
		//lets load up the produced file.
		//Notice the quality difference.
		SoundMan.playSound(SoundMan.loadStepSound("titlescreen.b8s"), 5.0);
		sleep(10000);
		
		System.exit(0);
	}
	
	public void sleep(long milliseconds) {
		long stamp = System.currentTimeMillis();
		
		while (System.currentTimeMillis() <= stamp + milliseconds) {
			
		}
	}
	
	public void playNewTurn() {
		StepList lists = new StepList();	
		for (int loops = 0; loops < 1; loops++) {
			for (int c = 0; c < 70000; c++) {
				double dub = (Math.log(c) ) * c;
				lists.addStep(dub);
			}
		}
		
		SoundMan.playSound(lists,5.0);
	}
	
	public void playError() {
		StepList lists = new StepList();	
		for (int loops = 0; loops < 1; loops++) {
			for (int c = 0; c < 70000; c++) {
				double dub = Math.atan2(c,c % 5) * c;
				lists.addStep(dub);
			}
		}
		SoundMan.playSound(lists,5.0);
	}
	
	public void playTitleMenu() {
		StepList lists = new StepList();	
		for (int loops = 0; loops < 4; loops++) {
			for (int c = 0; c < 70000; c++) {
				double dub = Math.sqrt(c);
				lists.addStep(dub % 5);
			}
			for (int c = 0; c < 50000; c++) {
				double dub = Math.sqrt(50000 - c);
				lists.addStep(dub % 5);
			}
		}
		SoundMan.saveStepSound(lists, 0, "titlescreen.b8s");
		SoundMan.playSound(lists,30.0);
	}
	public void playGameOver() {
		StepList lists = new StepList();
		
			for (int c = 0; c < 70000; c++) {
				double dub = Math.cbrt(c);
				lists.addStep(dub * 57);
			}
		
		SoundMan.playSound(lists,6.0);
	}
}
