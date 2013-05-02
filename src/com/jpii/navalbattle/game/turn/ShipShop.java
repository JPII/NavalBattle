package com.jpii.navalbattle.game.turn;

import java.awt.Font;

import com.jpii.navalbattle.game.NavalManager;
import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.pavo.gui.controls.PText;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;
import com.jpii.navalbattle.pavo.gui.events.PMouseEvent;

	public class ShipShop extends PWindow {
		
		MoveableEntity move;
		Player current;
		
		PButton missile = null;
		PText score;
		
		public ShipShop(NewWindowManager parent,MoveableEntity e){
			super(parent);
			parent.add(this);
			setSize(500, 250);
			setLoc(390, 275);
			setText("Ship Shop");
			initItems();
			
			move = e;
			current = null;
			missile.setVisible(true);
			if(move == null)
				setVisible(false);
			else{
				setVisible(true);
				current = ((NavalManager)e.getManager()).getTurnManager().findPlayer(e);
				if(move.getHandle()==21)
					missile.setVisible(false);
			}
		}
	 
		private void initItems(){
			PText shop = new PText(this);
			score = new PText(this);
			PButton hull = new PButton(this);
			missile = new PButton(this);
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
			score.setText("Your Current Score is "+((NavalManager)move.getManager()).getTurnManager().getTurn().getPlayer().getScore());
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
			
			hull.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					hardenHullAction();
				}
			});
			
			missile.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					increaseMissileAction();
				}
			});
			
			range.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					increaseRangeAction();
				}
			});
			
			antimissile.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					deflectMisileAction();
				}
			});
			
			repair.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					repairAction();
				}
			});
		}
		
		public void update(){
			score.setText("Your Current Score is "+((NavalManager)move.getManager()).getTurnManager().getTurn().getPlayer().getScore());
		}
		
		private void hardenHullAction(){
			if(current!=null && move!=null){
				if(current.getScore()>=200){
					current.subtractscore(200);
					move.hardenHull();
				}
				update();
			}
		}
		
		private void increaseMissileAction(){
			if(current!=null && move!=null){
				if(current.getScore()>=250){
					current.subtractscore(250);
					move.increaseMissile();
				}
				update();
			}
		}
		
		private void deflectMisileAction(){
			if(current!=null && move!=null){
				if(current.getScore()>=350){
					current.subtractscore(350);
					move.deflectMissile();
				}
				update();
			}
		}
		
		private void increaseRangeAction(){
			if(current!=null && move!=null){
				if(current.getScore()>=700 && move.rangeLimit>0){
					current.subtractscore(700);
					move.increaseRange();
				}
				update();
			}
		}
		
		private void repairAction(){
			if(current!=null && move!=null){
				if(current.getScore()>=300){
					current.subtractscore(300);
					move.repair();
				}
				update();
			}
		}
	}