package com.jpii.navalbattle.game;

import com.jpii.navalbattle.game.entity.AircraftCarrier;
import com.jpii.navalbattle.game.entity.BattleShip;
import com.jpii.navalbattle.game.entity.PortEntity;
import com.jpii.navalbattle.game.entity.Submarine;
import com.jpii.navalbattle.game.entity.Whale;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.World;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridHelper;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.turn.AI;
import com.jpii.navalbattle.turn.Player;
import com.jpii.navalbattle.turn.PlayerManager;
import com.jpii.navalbattle.turn.TurnManager;
import com.jpii.navalbattle.util.FileUtils;

/**
 * The entity manager specified for NavalBattle.
 */
public class NavalManager extends EntityManager {
	private static final long serialVersionUID = 1L;
	public static GridedEntityTileOrientation w1, w2, w3;
	TurnManager tm;
	
	/**
	 * Creates a new instance of the NavalManager.
	 * @param w The world to create it from.
	 */
	public NavalManager(World w) {
		super(w);
		tm = new TurnManager(new PlayerManager(new Player("BattleshipPlayer"),new AI(this,"Sub&ACPlayer"),new AI(this,"AIofWhales&Ports")));
		battleShipId = new GridedEntityTileOrientation();
		battleShipId.setLeftToRightImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/battleship/battleship.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT));
		battleShipId.setTopToBottomImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/battleship/battleship_S.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM));
		
		acarrierId = new GridedEntityTileOrientation();
		acarrierId.setLeftToRightImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/aircraftcarrier/aircraftcarrier.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT));
		acarrierId.setTopToBottomImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/aircraftcarrier/aircraftcarrier_S.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM));
		
		submarineId = new GridedEntityTileOrientation();
		submarineId.setLeftToRightImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/submarine/submarine.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT));
		submarineId.setTopToBottomImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/submarine/submarine_S.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM));
		
		submarineUId = new GridedEntityTileOrientation();
		submarineUId.setLeftToRightImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/submarine/submarineU.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT));
		submarineUId.setTopToBottomImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/submarine/submarineU_S.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM));
		
		if (battleShipId != null) {
			BattleShip.BATTLESHIP_ID = battleShipId;
		}
		if (acarrierId != null) {
			AircraftCarrier.AIRCRAFTCARRIER_ID = acarrierId;
		}
		if (submarineId != null) {
			Submarine.SUBMARINE_ID = submarineId;
			Submarine.SUBMARINEU_ID = submarineUId;
		}
		else {
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
		w2.setLeftToRightImage(w2_);
		w2.setTopToBottomImage(w2_);
		w3 = new GridedEntityTileOrientation();
		w3.setLeftToRightImage(w3_);
		w3.setTopToBottomImage(w3_);
		
		for (int c = 0; c < 25; c++) {
			Location poll = gh.pollNextWaterTile();
			while((!(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, poll.getRow(), poll.getCol(), 4)) && !(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, poll.getRow(), poll.getCol(), 4)))){
				poll = gh.pollNextWaterTile(25);
			}
			if(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, poll.getRow(), poll.getCol(), 4))
				tm.addEntity(new BattleShip(this, poll, GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT,Game.Settings.rand.nextInt(0,3)),tm.getTurn().getPlayer());
			else if(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, poll.getRow(), poll.getCol(), 4))
				tm.addEntity(new BattleShip(this, poll,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM,Game.Settings.rand.nextInt(0,3)),tm.getTurn().getPlayer());
			else
				c--;
		}
		for (int c = 0; c < 25; c++) {
			Location poll = gh.pollNextWaterTile();
			while((!(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, poll.getRow(), poll.getCol(), 5)) && !(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, poll.getRow(), poll.getCol(), 5)))){
				poll = gh.pollNextWaterTile(25);
			}
			if(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, poll.getRow(), poll.getCol(), 5))
				tm.addEntity(new AircraftCarrier(this, poll,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT,Game.Settings.rand.nextInt(0,3)),tm.getPlayer(1));
			else if(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, poll.getRow(), poll.getCol(), 5))
				tm.addEntity(new AircraftCarrier(this, poll,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM,Game.Settings.rand.nextInt(0,3)),tm.getPlayer(1));
			else
				c--;
		}
		
		for (int c = 0; c < 25; c++) {
			Location poll = gh.pollNextWaterTile();
			while((!(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, poll.getRow(), poll.getCol(), 2)) && !(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, poll.getRow(), poll.getCol(), 2)))){
				poll = gh.pollNextWaterTile(25);
			}
			if(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, poll.getRow(), poll.getCol(), 2))
				tm.addEntity(new Submarine(this, poll,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT,Game.Settings.rand.nextInt(0,3)),tm.getPlayer(1));
			else if(GridHelper.canPlaceInGrid(this,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, poll.getRow(), poll.getCol(), 2))
				tm.addEntity(new Submarine(this, poll,GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM,Game.Settings.rand.nextInt(0,3)),tm.getPlayer(1));
			else
				c--;
		}
		for (int c = 0; c < 50; c++) {
			Location poll = gh.pollNextWaterTile();
			tm.addEntity(new Whale(this,poll,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT,-1,w1,w2,w3),tm.getPlayer(3));
		}
		for (int c = 0; c < 10; c++) {
			Location p3 = gh.pollNextShoreTile();
			tm.addEntity(new PortEntity(this,p3,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT,-1),tm.getPlayer(3));
			System.out.println("Port generated at " + p3);
		}
		System.out.println("Let me play you the song of my people.");
	}
	
	public void update(long ticksPassed) {
		for (int c = 0; c < this.getTotalEntities(); c++) {
			Entity e = getEntity(c);
			if (e != null){
				e.onUpdate(ticksPassed);
			}
		}
	}
	
	public TurnManager getTurnManager(){
		return tm;
	}
}
