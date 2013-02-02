/**
 * 
 */
package com.jpii.navalbattle.game;

import java.awt.Color;

import com.jpii.navalbattle.game.entity.BattleShip;
import com.jpii.navalbattle.game.entity.Whale;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.World;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.Location;
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
		battleShipId = registerEntity(PavoHelper.imgUtilOutline(FileUtils.getImage("drawable-game/battleship/battleship.png"),Game.Settings.GridColor));
		int w1 = registerEntity(PavoHelper.imgUtilOutline(FileUtils.getImage("drawable-game/other/whaleleft.png"),Game.Settings.GridColor));
		int w2 = registerEntity(PavoHelper.imgUtilOutline(FileUtils.getImage("drawable-game/other/whalecenter.png"),Game.Settings.GridColor));
		int w3 = registerEntity(PavoHelper.imgUtilOutline(FileUtils.getImage("drawable-game/other/whaleright.png"),Game.Settings.GridColor));
		if (battleShipId != 0) {
			BattleShip.BATTLESHIP_ID = battleShipId;
			BattleShip e = new BattleShip(this,new Location(3,3),battleShipId);
			//e.moveTo(new Location(3,3));
		}
		else {
			System.out.println("not a battleship");
		}
		Whale whale1 = new Whale(this, new Location(5,5),w1,w2,w3);
	}
	
	public void gameDoneGenerating() {
		BattleShip e = new BattleShip(this,new Location(7,3),battleShipId);
		e.moveTo(7, 3);
		//System.out.println("let me play you the song of my people.");
	}
	
	public void update(long ticksPassed) {
		for (int c = 0; c < this.getTotalEntities(); c++) {
			Entity e = getEntity(c);
			if (e != null){ //&& e instanceof BattleShip) {
				e.onUpdate();
			}
		}
	}
}
