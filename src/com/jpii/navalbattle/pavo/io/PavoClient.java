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
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.jpii.navalbattle.pavo.gui.MessageBox;
import com.jpii.navalbattle.pavo.gui.MessageBoxIcon;

/**
 * @author MKirkby
 *
 */
public class PavoClient implements Runnable{
	Socket socket;
	PrintWriter pw;
	String ip;
	boolean doing = false;
	Thread thread;
	InetAddress address;
	public PavoClient(String ipaddress) {
		ip = ipaddress;
	}
	
	public boolean start() {
		address = null;
		try {
			address = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        try {
			socket = new Socket(address, 670);
		} catch (IOException e) {
			e.printStackTrace();
		}
        doing = true;
		thread = new Thread(this);
		thread.start();
		return true;
	}
	
	public void run() {
        InputStream is = null;
		try {
			is = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = null;
        PrintWriter pw = null;
		try {
			pw = new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
        //OutputStreamWriter osw = new OutputStreamWriter(os);
        //BufferedWriter bw = new BufferedWriter(osw);
		while (doing) {
            try {
            	String rl = br.readLine();
            	if (rl != null && !rl.equals(""))
            		onMessageRecieved(rl);
				writingToServer = true;
            	pw.println(tmpMsg);
            	tmpMsg = "";
            	writingToServer = false;
            	try {
            		Thread.sleep(50); // Let it breathe...
            	}
            	catch (Throwable t) {
            		
            	}
			} catch (IOException e) {
				if (e.getMessage().equals("Connection reset")) {
					doing = false;
					break;
				}
				e.printStackTrace();
			}
		}
		halt();
		System.out.println("Connection lost to the server!");
		try {
			MessageBox.show("Error", "Connection lost to the server!", MessageBoxIcon.Error, true);
		}
		catch (Throwable t) {
			
		}
	}
	
	public String getIP() {
		return ip;
	}
	
	public void onMessageRecieved(String msg) {
		System.out.println("Recieved from server (game host): " + msg);
	}
	boolean writingToServer = false;
	public void halt() {
		doing = false;
		try {
			Thread.sleep(250);
		}
		catch (Throwable t) {
			
		}
		try {
			socket.close();
		}
		catch (Throwable t) {
			
		}
		socket = null;
		try{
			thread.destroy();
		}
		catch (Throwable t) {
			
		}
		thread = null;
	}
	String tmpMsg = "";
	public void send(String message) {
		while (writingToServer) {
			
		}
		tmpMsg = message;
	}
}
