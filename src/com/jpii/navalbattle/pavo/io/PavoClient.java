package com.jpii.navalbattle.pavo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.gui.MessageBox;
import com.jpii.navalbattle.pavo.gui.MessageBoxIcon;

public class PavoClient implements Runnable{
	Socket socket;
	PrintWriter pw;
	String ip;
	boolean doing = false;
	Thread thread;
	InetAddress address;
	String selfIP;
	boolean internetConnected = true;
	ArrayList<NetAttribute> nas;
	public PavoClient(String ipaddress) {
		ip = ipaddress;
		nas = new ArrayList<NetAttribute>();
	}
	
	public boolean start() {
		address = null;
		try {
			address = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		Socket s;
		try {
			s = new Socket("google.com", 80);
			selfIP = (s.getLocalAddress().getHostAddress());
			if (selfIP == null) {
				internetConnected = false;
				selfIP = "None";
			}
			s.close();
		}
		catch (Throwable t) {	
		}
		if (!internetConnected)
			Game.Settings.currentNetworkState = NetworkState.CONNECTED_TO_NETWORK_NO_INTERNET;
        try {
			//socket = new Socket(address, 670);
        	socket = new Socket();
        	socket.connect(new InetSocketAddress(address, 670), 1000);
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
			if (internetConnected)
				Game.Settings.currentNetworkState = NetworkState.CONNECTED_TO_INTERNET_NO_NETWORK;
			else
				Game.Settings.currentNetworkState = NetworkState.NO_CONNECTION;
			e.printStackTrace();
		}
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        PrintWriter pw = null;
		try {
			pw = new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean entry = false;
		while (doing) {
            try {
            	if (!entry) {
            		entry = true;
            		tmpMsg = "HELLO";
            	}
				writingToServer = true;
            	pw.println(tmpMsg);
            	tmpMsg = null;
            	writingToServer = false;
            	
            	String rl = br.readLine();
            	
            	if (rl.equals("HELLO")) 
            		onConnection();
            	else if (rl != null && !rl.equals("")) {
            		if (rl.startsWith("NA")) {
            			rl = rl.replace("NA", "");
            			String name = rl.substring(0,rl.indexOf(":")+1);
            			String val = rl.replace(name, "");
            			name = name.replace(":", "");
            			nas.add(new NetAttribute(name,val));
            		}
            		else if (rl.startsWith("MSG")) {
            			rl = rl.replace("MSG", "");
            			onMessageRecieved(rl);
            		}
            		else
            			System.out.println(rl);
            	}
            	//if (rl != null && !rl.equals(""))
            		//onMessageRecieved(rl);
            	
//            	try {
//            		Thread.sleep(50);
//            	}
//            	catch (Throwable t) {
//            	}
			} catch (IOException e) {
				if (e.getMessage().equals("Connection reset")) {
					doing = false;
					break;
				}
				if (e.getMessage().equals("Software caused connection abort: recv failed")) {
					doing = false;
					break;
				}
				e.printStackTrace();
			}
		}
		pw.close();
		try {
			br.close();
		} catch (Throwable t) {
			
		}
		try {
			isr.close();
		} catch (Throwable t) {
			
		}
		try {
			is.close();
		} catch (Throwable t) {
		}
		halt();
		System.out.println("Connection lost to the server!");
		try {
			MessageBox.show("Error", "Connection lost to the server!", MessageBoxIcon.Error, true);
		}
		catch (Throwable t) {
		}
	}
	
	
	public void onConnection() {
		
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
			thread.interrupt();
		}
		catch (Throwable t) {
		}
		thread = null;
	}
	String tmpMsg = "";
	/*public void send(String message) {
		while (writingToServer) {
		}
		tmpMsg = message;
	}*/
	
	

	public void sendAttribute(NetAttribute attr) {
		while (writingToServer || tmpMsg != null) {
			
		}
		tmpMsg = attr.getComposite();
	}
	
	public void sendMessage(String msg) {
		while (writingToServer || tmpMsg != null) {
			
		}
		tmpMsg = "MSG"+msg;
	}
	
	public NetAttribute getNetAttribute(String name) {
		NetAttribute attr = null;
		for (int c = 0; c < nas.size(); c++) {
			NetAttribute b = nas.get(c);
			if (b.getName().toLowerCase().equals(name.toLowerCase())) {
				attr = b;
				c = nas.size() + 10;
				break;
			}
		}
		return attr;
	}
}
