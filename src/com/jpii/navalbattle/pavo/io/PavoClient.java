/**
 * 
 */
package com.jpii.navalbattle.pavo.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author MKirkby
 *
 */
public class PavoClient {
	Socket socket;
	PrintWriter pw;
	String ip;
	public PavoClient(String ipaddress) {
		ip = ipaddress;
	}
	
	public boolean start() {
		try {
			socket = new Socket(ip,67);
		} catch (Throwable t) {
			return false;
		}
		try {
			pw = new PrintWriter(socket.getOutputStream(),true);
		} catch (Throwable t) {
			return false;
		}
		
		return true;
	}
	
	public void send(String message) {
		pw.print(message);
	}
}
