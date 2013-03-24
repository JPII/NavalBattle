/**
 * 
 */
package com.jpii.navalbattle.game;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.io.PavoClient;
import com.jpii.navalbattle.pavo.io.PavoServer;

/**
 * @author MKirkby
 *
 */
public class NavalServer extends PavoServer {
	Game game;
	/**
	 * @param ipaddress
	 */
	public NavalServer(Game game) {
		super();
		this.game = game;
	}
	
	public void onClientConnect() {
		game.akamaideli3242very();
		send("SEED:"+Game.Settings.seed);
	}
	
	public void onMessageRecieved(String message) {
		if (message.startsWith("bounds:")) {
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
}
