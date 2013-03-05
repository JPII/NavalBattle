/**
 * 
 */
package com.jpii.navalbattle.pavo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.sun.corba.se.spi.activation.Server;

/**
 * @author MKirkby
 *
 */
public class PavoServer implements Runnable {
	ServerSocket socket;
	Socket client;
	boolean doing = false;
	String ipaddress = null;
	Thread self;
	public PavoServer() {
		
	}
	
	public boolean start() {
		if (doing) {
			System.out.println("Server already runnning!");
			return false;
		}
		if (ipaddress == null) {
			Socket s;
			try {
				s = new Socket("google.com", 80);
				ipaddress = (s.getLocalAddress().getHostAddress());
				s.close();
			}
			catch (Throwable t) {
				
			}
		}
		try {
			socket = new ServerSocket(67);
		} catch (Throwable e) {
			return false;
		}
		doing = true;
		self = new Thread(this);
		self.start();
		return true;
	}

	public void run() {
		while (doing) {
			if (client == null)
				try {
					client = socket.accept();
				} catch (Throwable e) {
					
				}
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String s = "";
				String input;
				while((input = bufferedReader.readLine()) != null)
					s += input + "\n";
				
				onMessageRecieved(s);
			} catch (Throwable t) {
				
			}
			try {
				Thread.sleep(300);
			}
			catch (Throwable t) {
				
			}
		}
	}
	
	public void onMessageRecieved(String msg) {
		
	}
	
	public void halt() {
		doing = false;
		try {
			Thread.sleep(250);
		}
		catch (Throwable t) {
			
		}
		try {
			client.close();
		}
		catch (Throwable t) {
			
		}
		try {
			socket.close();
		}
		catch (Throwable t) {
			
		}
		client = null;
		socket = null;
		try{
			self.destroy();
		}
		catch (Throwable t) {
			
		}
		self = null;
	}
	
	
}
