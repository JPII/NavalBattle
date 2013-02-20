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
import com.jpii.navalbattle.pavo.grid.GridHelper;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Id;
import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.util.FileUtils;

/**
 * @author maximusvladimir
 * The entity manager specified for NavalThing.
 */
public class NavalManager extends EntityManager {
	public static GridedEntityTileOrientation w1, w2, w3;
	/**
	 * Creates a new instance of the NavalManager.
	 * @param w The world to create it from.
	 */
	public NavalManager(World w) {
		super(w);
		battleShipId = new GridedEntityTileOrientation();
		battleShipId.setLeftToRightImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/battleship/battleship.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT));
		battleShipId.setTopToBottomImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/battleship/battleship_S.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM));
		if (battleShipId != null) {
			BattleShip.BATTLESHIP_ID = battleShipId;
		}
		else {
			System.out.println("not a battleship");
		}
		gh = new GridHelper(this);
	}
	GridHelper gh;
	
	public void gameDoneGenerating() {
		int w1_ = registerEntity(PavoHelper.imgUtilOutline(FileUtils.getImage("drawable-game/other/whaleleft.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int w2_ = registerEntity(PavoHelper.imgUtilOutline(FileUtils.getImage("drawable-game/other/whalecenter.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int w3_ = registerEntity(PavoHelper.imgUtilOutline(FileUtils.getImage("drawable-game/other/whaleright.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		w1 = new GridedEntityTileOrientation();
		w1.setLeftToRightImage(w1_);
		w1.setTopToBottomImage(w1_);
		w2 = new GridedEntityTileOrientation();
		w1.setLeftToRightImage(w2_);
		w1.setTopToBottomImage(w2_);
		w3 = new GridedEntityTileOrientation();
		w1.setLeftToRightImage(w3_);
		w1.setTopToBottomImage(w3_);
		
		for (int c = 0; c < 20; c++) {
			Location poll = gh.pollNextWaterTile();
			new Whale(this, poll,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT,Game.Settings.rand.nextInt(0,3),w1,w2,w3);
		}
		System.out.println("Let me play you the song of my people.");
	}
	
	public void update(long ticksPassed) {
		for (int c = 0; c < this.getTotalEntities(); c++) {
			Entity e = getEntity(c);
			if (e != null){ //&& e instanceof BattleShip) {
				e.onUpdate(ticksPassed);
			}
		}
	}
}
