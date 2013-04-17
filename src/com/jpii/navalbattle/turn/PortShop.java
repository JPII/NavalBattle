package com.jpii.navalbattle.turn;

import java.awt.Font;

import com.jpii.navalbattle.game.NavalGame;
import com.jpii.navalbattle.game.entity.PortEntity;
import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;
import com.jpii.navalbattle.pavo.gui.events.PMouseEvent;

	public class PortShop extends PWindow {
		
		PortEntity port;
		Player current;
		
		public PortShop(NewWindowManager parent) {
			super(parent);
			setVisible(true);
			parent.add(this);
			setSize(500, 250);
			setLoc(390, 275);
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
			score.setFont(new Font("Verdana Bold", 1, 12));
			stock.setFont(new Font("Verdana Bold", 1, 12));
			price.setFont(new Font("Verdana Bold", 1, 12));
			
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
			rprice.setText("500");
			
			shop.setLoc(200,35);
			score.setLoc(5, 230);
			bship.setLoc(20, 90);
			sub.setLoc(20, 120);
			ac.setLoc(20, 150);
			repair.setLoc(20, 180);
			
			stock.setLoc(60, 60);
			price.setLoc(390, 60);
			bprice.setLoc(390, 90);
			sprice.setLoc(390, 120);
			acprice.setLoc(390, 150);
			rprice.setLoc(390, 180);
			
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
			
			bship.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					buyBattleShip();
				}
			});
			
			sub.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					buySubmarine();
				}
			});
			
			ac.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					buyCarrier();
				}
			});
			
			repair.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					repairAction();
				}
			});
		}
		
		private void buyBattleShip(){
			if(current!=null && port!=null){
				if(current.getScore()>=1000){
					current.subtractscore(1000);
					port.spawnBattleship();
				}
			}
		}
		
		private void buySubmarine(){
			if(current!=null && port!=null){
				if(current.getScore()>=1250){
					current.subtractscore(1250);
					port.spawnSubmarine();
				}
			}
		}
		
		private void buyCarrier(){
			if(current!=null && port!=null){
				if(current.getScore()>=1250){
					current.subtractscore(1250);
					port.spawnAC();
				}
			}
		}
		
		private void repairAction(){
			if(current!=null && port!=null){
				if(current.getScore()>=500){
					current.subtractscore(500);
					port.repair();
				}
			}
		}
		
		/**
		 * @deprecated
		 */
		public void setVisible(boolean sight){
			super.setVisible(false);
		}
		
		public void setVisilbe(PortEntity pe){
			port = pe;
			current = null;
			if(port == null){
				super.setVisible(false);
			}
			else{
				super.setVisible(true);
				current = NavalGame.getManager().getTurnManager().findPlayer(pe);
			}
		}
	}