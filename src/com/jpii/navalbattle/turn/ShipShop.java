package com.jpii.navalbattle.turn;

import java.awt.Font;

import com.jpii.navalbattle.game.NavalGame;
import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

	public class ShipShop extends PWindow {

	 public ShipShop(NewWindowManager pare) {
		super(pare);
		setVisible(true);
		pare.add(this);
	  setLoc(78, 86);
	  setSize(500, 250);
	  setText("Ship Shop");
	  PText text0 = new PText(this);
	  text0.setText("Ship Shop");
	  text0.setLoc(199, 32);
	  addControl(text0);
	  text0.setFont(new Font("Verdana Bold", 1, 18));
	  PText text1 = new PText(this);
	  text1.setText("Your Current Score is "+NavalGame.getManager().getTurnManager().getTurn().getPlayer().getScore());
	  text1.setLoc(4, 224);
	  addControl(text1);
	  PButton button2 = new PButton(this);
	  button2.setText("Repair Ships");
	  button2.setLoc(16, 86);
	  addControl(button2);
	  PButton button3 = new PButton(this);
	  button3.setText("Missile X5");
	  button3.setLoc(16, 114);
	  addControl(button3);
	  PButton button4 = new PButton(this);
	  button4.setText("Increase Range: 0 of 3");
	  button4.setLoc(16, 143);
	  addControl(button4);
	  PButton button5 = new PButton(this);
	  button5.setText("Anti-Missile");
	  button5.setLoc(16, 171);
	  addControl(button5);
	  PButton button6 = new PButton(this);
	  button6.setText("Hull Upgrade");
	  button6.setLoc(16,199);
	  addControl(button6);
	  PText text5 = new PText(this);
	  text5.setText("Stock");
	  text5.setLoc(55, 60);
	  addControl(text5);
	  PText text6 = new PText(this);
	  text6.setText("Price");
	  text6.setLoc(388, 63);
	  addControl(text6);
	  PText text7 = new PText(this);
	  text7.setText("100");
	  text7.setLoc(390, 86);
	  addControl(text7);
	  PText text8 = new PText(this);
	  text8.setText("250");
	  text8.setLoc(390, 114);
	  addControl(text8);
	  PText text9 = new PText(this);
	  text9.setText("700");
	  text9.setLoc(390, 143);
	  addControl(text9);
	  PText text10 = new PText(this);
	  text10.setText("350");
	  text10.setLoc(390, 171);
	  addControl(text10);
	  PText text11 = new PText(this);
	  text11.setText("200");
	  text11.setLoc(390,199);
	  addControl(text11);
	 }

	}