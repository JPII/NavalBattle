package com.jpii.navalbattle.turn;

import com.jpii.navalbattle.game.NavalGame;
import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.game.entity.PortEntity;

public class DamageCalculator {
	
	public static void doPrimaryDamage(MoveableEntity deal, MoveableEntity take){
		Player player = NavalGame.getManager().getTurnManager().getTurn().getPlayer();
		deal.usePrimary();
		System.out.println((int)(Math.random()*100));
		if((int)(Math.random()*100)==10){
			System.out.println("[chat] attack deflected");
			NavalGame.getManager().getTurnManager().findPlayer(take).addscore(50);
		}
		else if(take.takeDamage(100)){
			player.addscore(100);
			if(take.getPercentHealth()==0){
				player.addscore(400);
			}
		}
	}
	
	public static void doPrimaryDamage(MoveableEntity deal, PortEntity take){
		deal.usePrimary();
	}
	
	public static void doSecondaryDamage(MoveableEntity deal, MoveableEntity take){
		Player player = NavalGame.getManager().getTurnManager().findPlayer(deal);
		deal.useSecondary();
		System.out.println((int)(Math.random()*100));
		if((int)(Math.random()*100)==10){
			System.out.println("[chat] attack deflected");
			NavalGame.getManager().getTurnManager().findPlayer(take).addscore(50);
		}
		else if(take.takeDamage(300)){
			player.addscore(100);
			if(take.getPercentHealth()==0){
				player.addscore(400);
			}
		}
	}
	
	public static void doSecondaryDamage(MoveableEntity deal, PortEntity take){
		deal.useSecondary();
	}
	
}
