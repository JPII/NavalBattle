/**
 * 
 */
package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.pavo.EntityManager;
import com.jpii.navalbattle.pavo.Game;

/**
 * @author maximusvladimir
 *
 */
public class BattleShip extends Entity {

	/**
	 * @param em
	 */
	public BattleShip(EntityManager em) {
		super(em);
	}

	/**
	 * @param em
	 * @param loc
	 * @param superId
	 */
	public BattleShip(EntityManager em, Location loc, int superId) {
		super(em, loc, superId);
	}
	
	public void init() {
		setWidth(4);
		setHeight(1);
	}
	
	public void onUpdate() {
		int r = Game.Settings.rand.nextInt(2,4);
		int c = Game.Settings.rand.nextInt(2,4);
		moveTo(r+getLocation().getRow(),c+getLocation().getCol());
	}

}
