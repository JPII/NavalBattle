package com.jpii.navalbattle.turn;

import java.awt.Font;

import com.jpii.navalbattle.game.NavalGame;
import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

	public class PortShop extends PWindow {

		public PortShop(NewWindowManager parent) {
			super(parent);
			setVisible(true);
			parent.add(this);
			setLoc(78, 86);
			setSize(500, 250);
			setText("Port Shop");
			initItems();
		}
		
		private void initItems(){
			
			PText shop = new PText(this);
			PText score = new PText(this);
			PButton bship = new PButton(this);
			PButton sub = new PButton(this);
			PButton ac = new PButton(this);
			PButton repair = new PButton(this);
			
			PText stock = new PText(this);
			PText price = new PText(this);
			PText bprice = new PText(this);
			PText sprice = new PText(this);
			PText acprice = new PText(this);
			PText rprice = new PText(this);
			
			shop.setFont(new Font("Verdana Bold", 1, 18));
			
			shop.setText("Port Shop");
			score.setText("Your Current Score is "+NavalGame.getManager().getTurnManager().getTurn().getPlayer().getScore());
			bship.setText("Purchase Battleship");
			sub.setText("Purchase Submarine");
			ac.setText("Purchase Aircraft Carrier");
			repair.setText("Repair Port");
			
			stock.setText("Stock");
			price.setText("Price");
			bprice.setText("1000");
			sprice.setText("1250");
			acprice.setText("1250");
			rprice.setText("400");
			
			shop.setLoc(199, 32);
			score.setLoc(4, 224);
			bship.setLoc(16, 86);
			sub.setLoc(16, 114);
			ac.setLoc(16, 143);
			repair.setLoc(16, 170);
			
			stock.setLoc(55, 60);
			price.setLoc(388, 63);
			bprice.setLoc(380, 81);
			sprice.setLoc(378, 105);
			acprice.setLoc(379, 136);
			rprice.setLoc(390, 170);
			
			addControl(shop);
			addControl(score);		
			addControl(bship);
			addControl(sub);
			addControl(ac);
			addControl(stock);
			addControl(price);
			addControl(bprice);
			addControl(sprice);
			addControl(acprice);
			addControl(repair);
			addControl(rprice);
		}

	}