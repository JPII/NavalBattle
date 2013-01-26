/**
 * 
 */
package com.jpii.navalbattle.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.game.entity.BattleShip;
import com.jpii.navalbattle.game.entity.Entity;
import com.jpii.navalbattle.pavo.EntityManager;
import com.jpii.navalbattle.pavo.IndexableImage;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.Tile;
import com.jpii.navalbattle.pavo.World;
import com.jpii.navalbattle.util.FileUtils;

/**
 * @author maximusvladimir
 * The entity manager specified for NavalThing.
 */
public class NavalManager extends EntityManager {
	/**
	 * Creates a new instance of the NavalManager.
	 * @param w The world to create it from.
	 */
	public NavalManager(World w) {
		super(w);
		int battleshipId = registerEntity(FileUtils.getImage("drawable-game/battleship/battleship.png"));
		if (battleshipId != 0) {
			BattleShip e = new BattleShip(this,new Location(3,3),battleshipId);
			e.moveTo(new Location(3,3));
		}
		else {
			System.out.println("not a battleship");
		}
	}
	
	public void update(long ticksPassed) {
		for (int c = 0; c < this.getTotalEntities(); c++) {
			Entity e = getEntity(c);
			if (e != null && e instanceof BattleShip) {
				e.onUpdate();
			}
		}
	}
}
