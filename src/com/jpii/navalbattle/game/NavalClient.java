/**
 * 
 */
package com.jpii.navalbattle.game;

import com.jpii.navalbattle.game.entity.BattleShip;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.EntityManager;
import com.jpii.navalbattle.pavo.grid.GridedEntityTileOrientation;
import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.pavo.io.PavoClient;

/**
 * @author MKirkby
 *
 */
public class NavalClient extends PavoClient {
	long seed = Long.MIN_VALUE;
	Game game;
	/**
	 * @param ipaddress
	 */
	public NavalClient(Game game,String ipaddress) {
		super(ipaddress);
		this.game = game;
	}
	public void onMessageRecieved(String message) {
		if (message.startsWith("SEED:")) {
			String part = message.replace("SEED:","");
			seed = Long.parseLong(part);
		}
		else if (message.startsWith("battleship:")) {
			String part = message.replace("battleship:","");
			String col = part.substring(0, part.indexOf(","));
			String row = part.substring(part.indexOf(",")+1);
			
			int c = Integer.parseInt(col);
			int r = Integer.parseInt(row);
			new BattleShip(game.getWorld().getEntityManager(),
					new Location(r,c), BattleShip.BATTLESHIP_ID,GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT,0);
			//System.out.println("battleship was placed at: "+ col + ", "+row);
		}
		else if (message.startsWith("bounds:")) {
			String part = message.replace("bounds:","");
			String col = part.substring(0, part.indexOf(","));
			String row = part.substring(part.indexOf(",")+1);
			
			int x = Integer.parseInt(col);
			int y = Integer.parseInt(row);
			NavalGame gn = (NavalGame)game;
			gn.getMap().setMultiplayer(x, y);
			//System.out.println("battleship was placed at: "+ col + ", "+row);
		}
		else
			super.onMessageRecieved(message);
	}
	public long getSeed() {
		return seed;
	}
}
