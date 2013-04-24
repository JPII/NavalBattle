package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.game.NavalManager;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;

public class Whale extends AnimatedEntity {

	private static final long serialVersionUID = 1L;
	public static int[] animationFramesForDaWhale;
	
	/**
	 * @param em
	 * @param loc
	 * @param animationFrameIds
	 */
	public Whale(EntityManager em, Location loc,byte orientation, GridedEntityTileOrientation... animationFrameIds) {
		super(em, loc, orientation, animationFrameIds);
		nextIndex = Game.Settings.rand.nextInt(0,3);
		imgLocation="drawable-game/other/whaleright.png";
	}
	
	
	boolean speedy = false;
	public void onUpdate(long tickTime) {
		super.onUpdate(tickTime);
		if (speedy && tickTime % 2 == 0) {
			updateFrame();
		}
		else if (tickTime % 6 == 0) {
			updateFrame();
		}
		if (tickTime % 5 == 0)
			updateSurroundings();
	}
	
	public void onMouseDown(int x, int y, boolean leftClick) {
		//this.animatedMoveTo(/*new Location(3,3)*/((NavalManager)getManager()).gh.pollNearLocation(getLocation()), 0.25f);
		//getManager().getWorld().animatedSetLoc(Game.Settings.rand.nextInt(-2000, 2000),Game.Settings.rand.nextInt(1000, 2000));
		Location loc2 = Location.random(getManager().getWorld(),Game.Settings.rand);
		getManager().getWorld().animatedSetLoc(loc2,0.0556666662f);
	}
	
	
	private void updateSurroundings() {
		for (int r = -3; r < 3; r++) {
			for (int c = -3; c < c; c++) {
				int rr = getLocation().getRow();
				int cc = getLocation().getCol();
				int rrr = rr+r;
				int ccc = cc+c;
				if (rrr >= 0 && ccc >= 0 && ccc < 2*PavoHelper.getGameWidth(getManager().getWorld().getWorldSize()) 
						&& rrr < 2*PavoHelper.getGameHeight(getManager().getWorld().getWorldSize()) &&
						getManager().getTile(rrr, ccc) != null&& getManager().getTile(rrr, ccc).getEntity() instanceof BattleShip) {
					speedy = true;
					return;
				}
			}
		}
		speedy = false;
	}

}
