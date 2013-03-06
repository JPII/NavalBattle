/**
 * 
 */
package com.jpii.navalbattle.game;

import com.jpii.navalbattle.pavo.io.PavoClient;

/**
 * @author MKirkby
 *
 */
public class TestClient extends PavoClient {
	long seed = Long.MIN_VALUE;
	/**
	 * @param ipaddress
	 */
	public TestClient(String ipaddress) {
		super(ipaddress);
	}
	public void onMessageRecieved(String message) {
		super.onMessageRecieved(message);
		if (message.startsWith("SEED:")) {
			String part = message.replace("SEED:","");
			seed = Long.parseLong(part);
		}
	}
	public long getSeed() {
		return seed;
	}
}
