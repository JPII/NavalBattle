package com.jpii.navalbattle.turn;

import java.awt.Font;

import com.jpii.navalbattle.game.NavalGame;
import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

	public class ShipShop extends PWindow {

	 public ShipShop(NewWindowManager pare){
		 super(pare);
		 setVisible(true);
		 pare.add(this);
		 setLoc(78, 86);
		 setSize(500, 250);
		 setText("Ship Shop");
		 
		 PText text0 = new PText(this);
		 text0.setText("Ship Shop");
		 text0.setLoc(199, 32);
		 text0.setFont(new Font("Verdana Bold", 1, 18));
		 addControl(text0);
		 
		 PText text1 = new PText(this);
		 text1.setText("Your Current Score is "+NavalGame.getManager().getTurnManager().getTurn().getPlayer().getScore());
		 text1.setLoc(4, 224);
		 addControl(text1);
		 
		 PText text2 = new PText(this);
		 text2.setText("Stock");
		 text2.setLoc(55, 60);
		 addControl(text2);
		 PText text3 = new PText(this);
		 text3.setText("Price");
		 text3.setLoc(388, 63);
		 addControl(text3);
		 
		 PButton button1 = new PButton(this);
		 button1.setText("Repair Ships");
		 button1.setLoc(16, 86);
		 addControl(button1);
		 PText text4 = new PText(this);
		 text4.setText("100");
		 text4.setLoc(390, 86);
		 addControl(text4);
		 
		 PButton button2 = new PButton(this);
		 button2.setText("Missile X5");
		 button2.setLoc(16, 114);
		 addControl(button2);
		 PText text5 = new PText(this);
		 text5.setText("250");
		 text5.setLoc(390, 114);
		 addControl(text5);
		 
		 PButton button3 = new PButton(this);
	  	 button3.setText("Increase Range: 0 of 3");
	  	 button3.setLoc(16, 143);
	  	 addControl(button3);
	  	 PText text6 = new PText(this);
		 text6.setText("700");
		 text6.setLoc(390, 143);
		 addControl(text6);
		 
	  	 PButton button4 = new PButton(this);
	  	 button4.setText("Anti-Missile");
	  	 button4.setLoc(16, 171);
	  	 addControl(button4);
	  	 PText text7 = new PText(this);
		 text7.setText("350");
		 text7.setLoc(390, 171);
		 addControl(text7);
	  	 
	  	 PButton button5 = new PButton(this);
	  	 button5.setText("Hull Upgrade");
	  	 button5.setLoc(16,199);
	  	 addControl(button5);
	  	 PText text8 = new PText(this);
		 text8.setText("200");
		 text8.setLoc(390,199);
		 addControl(text8);
	 }
	}