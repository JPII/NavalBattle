/**
 * 
 */
package com.jpii.navalbattle.pavo;

import com.jpii.navalbattle.pavo.io.PavoClient;
import com.jpii.navalbattle.pavo.io.PavoServer;

/**
 * @author MKirkby
 *
 */
public class TestServer extends PavoServer {
	Game game;
	/**
	 * @param ipaddress
	 */
	public TestServer(Game game) {
		super();
		this.game = game;
	}
}
