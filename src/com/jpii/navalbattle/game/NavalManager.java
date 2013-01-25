/**
 * 
 */
package com.jpii.navalbattle.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
		int battleshipId = 0;
		//try {
			battleshipId = registerEntity(FileUtils.getImage("drawable-game/battleship/battleship.png"));
		/*}
		catch (Throwable throwable) {
			System.out.println("mssage:" + throwable.getMessage());
		}*/
		if (battleshipId != 0) {
			new Entity(this,new Location(3,3),battleshipId).moveTo(new Location(3,3));
		}
		else {
			System.out.println("not a battleship");
		}
	}
	public BufferedImage getImage(Entity ent) {
		if (ent == null)
			return null;
		return null;
	}
}
