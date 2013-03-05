/**
 * 
 */
package com.jpii.navalbattle.pavo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
	BufferedReader reader;
	PrintWriter output;
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
			System.out.println("Your ip address is: " + ipaddress);
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
					try {
						socket = new ServerSocket(67);
					} catch (Throwable e) {
					}
					client = socket.accept();
					System.out.println("Connected to: " + client.getLocalAddress().getHostAddress());
				} catch (Throwable e) {
					System.out.println("Failed to connect to client." + e.getMessage());
				}
			try {
				if (reader != null)
					reader.close();
				if (output != null)
					output.close();
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				output = new PrintWriter(client.getOutputStream(),true);
				String s = "";
				String input;
				while((input = reader.readLine()) != null)
					s += input + "\n";
				
				onMessageRecieved(s);
			} catch (Throwable t) {
				System.out.println("Something occured on the server:" +t.getMessage());
			}
			try {
				Thread.sleep(300);
			}
			catch (Throwable t) {
				
			}
			loop();
		}
	}
	
	public void loop() {
		
	}
	
	public void send(String msg) {
		if (output != null)
			output.println(msg);
	}
	
	public void onMessageRecieved(String msg) {
		System.out.println("Recieved from client: " + msg);
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
