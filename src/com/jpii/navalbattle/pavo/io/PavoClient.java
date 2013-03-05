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
import java.net.Socket;
import java.net.UnknownHostException;

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
	public PavoClient(String ipaddress) {
		ip = ipaddress;
	}
	
	public boolean start() {
		InetAddress address = null;
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
		while (doing) {
	        InputStream is = null;
			try {
				is = socket.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String tmp = "";
	        String build = "";
	        try {
				while ((tmp = br.readLine()) != null) {
					build += tmp + "\n";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
            if (build.length() > 1) {
            	build = build.substring(0, build.length()-1);
            }
            onMessageRecieved(build);
		}
	}
	
	public String getIP() {
		return ip;
	}
	
	public void onMessageRecieved(String msg) {
		System.out.println("Recieved from server (game host): " + msg);
	}
	
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
	
	public void send(String message) {
        OutputStream os = null;
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        try {
			bw.write(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
