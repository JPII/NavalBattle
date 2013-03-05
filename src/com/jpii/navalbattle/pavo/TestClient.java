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
public class TestClient extends PavoClient {
	Game game;
	/**
	 * @param ipaddress
	 */
	public TestClient(String ip,Game game) {
		super(ip);
		this.game = game;
	}
	
	public void onRecieve(String message) {
		System.out.println("Recieved from server (" + getIP() + "): " + message);
	}
}
