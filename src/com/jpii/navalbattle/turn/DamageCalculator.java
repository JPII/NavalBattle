package com.jpii.navalbattle.turn;

import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.game.entity.PortEntity;

public class DamageCalculator {
	
	public static void doPrimaryDamage(MoveableEntity deal, MoveableEntity take){
		deal.usePrimary();
		take.takeDamage(100);
	}
	
	public static void doPrimaryDamage(MoveableEntity deal, PortEntity take){
		deal.usePrimary();
	}
	
	public static void doSecondaryDamage(MoveableEntity deal, MoveableEntity take){
		deal.useSecondary();
		take.takeDamage(300);
	}
	
	public static void doSecondaryDamage(MoveableEntity deal, PortEntity take){
		deal.useSecondary();
	}
	
}
