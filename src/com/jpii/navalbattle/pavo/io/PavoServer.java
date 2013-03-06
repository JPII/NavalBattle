/**
 * 
 */
package com.jpii.navalbattle.pavo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
	String clientAddress = null;
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
		clientAddress = null;
		doing = true;
		self = new Thread(this);
		self.start();
		return true;
	}

	public void run() {
		try {
			socket = new ServerSocket(670);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		while (doing) {
			try {
				client = socket.accept();
				if (clientAddress == null) {
					clientAddress = client.getInetAddress().toString();
					System.out.println("The server connected to: " + clientAddress);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//if (sendTmp != null && !sendTmp.equals("")) {
			try {
				PrintWriter out = new PrintWriter(client.getOutputStream(),true);
				out.print(sendTmp);
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
	            /*OutputStream os = null;
				try {
					os = client.getOutputStream();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	            OutputStreamWriter osw = new OutputStreamWriter(os);
	            BufferedWriter bw = new BufferedWriter(osw);
	            try {
					bw.write(sendTmp);
				} catch (IOException e) {
					e.printStackTrace();
				}
	            sendTmp = "";
	            try {
					bw.flush();
				} catch (Throwable t) {
					
				}*/
			//}
			
            InputStream is = null;
			try {
				is = client.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            /*String tmp = "";
            String build = "";
            try {
				while ((tmp = br.readLine()) != null && !tmp.equals("")) {
					if (tmp.equals("Is the server listening?")) {
						send("Yes sir. I am listening. Are you listening?");
						System.out.println("Congrads! The connection test to the client was sucessful!");
					}
					build += tmp + "abc\n";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
            if (build.length() > 1) {
            	build = build.substring(0, build.length()-1);
            }
            
            if (!build.equals("\n")) {
            	onMessageRecieved(build);
            }*/
            try {
				onMessageRecieved(br.readLine());
			
			} catch (IOException e) {
				e.printStackTrace();
			}
            
			loop();
		}
	}
	
	public void loop() {
		
	}
	
	String sendTmp = "";
	public void send(String msg) {
		sendTmp += msg;
	}
	
	public void onMessageRecieved(String msg) {
		System.out.println("Recieved from client (the requestor): " + msg);
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
