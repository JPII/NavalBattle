package com.jpii.navalbattle.io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class P2PService {
	int status = 0;
	Timer ticker;
	boolean isByteStreamComplete = false;
	byte[] currentBytes;
	public P2PService(String client) {
		ActionListener l = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		};
		ticker = new Timer(100,l);
	}
	private void update() {
		
	}
	public void connect() throws java.net.SocketException {
		status = 1;
	}
	public void disconnect() {
		if (status != 1)
			return;
		status = 0;
	}
	public boolean sendBytes(byte packetId, byte[] data) {
		return false;
	}
	public byte checkForNewBytes(byte[] data) {
		if (!isByteStreamComplete) {
			data = null;
			return 0;
		}
		else {
			return 0;
		}
	}
}
