package com.jpii.navalbattle.turn;

import java.awt.Font;

import com.jpii.navalbattle.game.NavalGame;
import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

	public class ShipShop extends PWindow {

		public ShipShop(NewWindowManager parent){
			super(parent);
			setVisible(true);
			parent.add(this);
			setSize(500, 250);
			setLoc(390, 275);
			setText("Ship Shop");
			initItems();
		}
	 
		private void initItems(){
			PText shop = new PText(this);
			PText score = new PText(this);
			PButton hull = new PButton(this);
			PButton missile = new PButton(this);
			PButton range = new PButton(this);
			PButton antimissile = new PButton(this);
			PButton repair = new PButton(this);
		 
			PText stock = new PText(this);
			PText price = new PText(this);
			PText hprice = new PText(this);
			PText mprice = new PText(this);
			PText rprice = new PText(this);
			PText amprice = new PText(this);
			PText reprice = new PText(this);
		 
			shop.setFont(new Font("Verdana Bold", 1, 18));
			score.setFont(new Font("Verdana Bold", 1, 12));
			stock.setFont(new Font("Verdana Bold", 1, 12));
			price.setFont(new Font("Verdana Bold", 1, 12));
		 
			shop.setText("Ship Shop");
			score.setText("Your Current Score is "+NavalGame.getManager().getTurnManager().getTurn().getPlayer().getScore());
			hull.setText("Hull Upgrade");
			missile.setText("Missile X5");
			range.setText("Increase Range");
		 	antimissile.setText("Anti-Missile");
		 	repair.setText("Repair Ship");
		 
		 	stock.setText("Stock");
		 	price.setText("Price");
		 	hprice.setText("200");
		 	mprice.setText("250");
		 	rprice.setText("700");
		 	amprice.setText("350");
		 	reprice.setText("300");
		 
		 	shop.setLoc(200, 35);
		 	score.setLoc(5, 230);
			hull.setLoc(20, 85);
			missile.setLoc(20, 115);
			range.setLoc(20, 145);
			antimissile.setLoc(20, 175);
			repair.setLoc(20, 205);
		 
			stock.setLoc(60, 60);
			price.setLoc(390, 60);
			hprice.setLoc(390, 85);
			mprice.setLoc(390, 115);
		 	rprice.setLoc(390, 145);
		 	amprice.setLoc(390, 175);
		 	reprice.setLoc(390, 205);
		 
		 	addControl(shop);
			addControl(score);
			addControl(hull);
			addControl(missile);
			addControl(range);
			addControl(antimissile);
			addControl(repair);
			addControl(price);
			addControl(stock);		 
			addControl(hprice);
			addControl(mprice);
			addControl(rprice);
			addControl(amprice);
			addControl(reprice);
		}
	}