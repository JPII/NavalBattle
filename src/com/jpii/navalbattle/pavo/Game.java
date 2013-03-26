/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.navalbattle.pavo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.game.NavalClient;
import com.jpii.navalbattle.game.NavalServer;
//import com.jpii.navalbattle.game.TestClient;
//import com.jpii.navalbattle.game.TestServer;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.grid.Location;
import com.jpii.navalbattle.pavo.grid.Tile;
import com.jpii.navalbattle.pavo.gui.GameWindow;
import com.jpii.navalbattle.pavo.gui.GridWindow;
import com.jpii.navalbattle.pavo.gui.MessageBox;
import com.jpii.navalbattle.pavo.gui.MessageBoxIcon;
import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.WindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;
import com.jpii.navalbattle.pavo.io.PavoClient;
import com.jpii.navalbattle.pavo.io.PavoImage;
import com.jpii.navalbattle.pavo.io.PavoServer;
import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.util.GameStatistics;

public class Game extends Renderable implements Runnable, Serializable {
	private Thread updator;
	private Thread chunkrender;
	private Thread generator;
	private Thread renderer;
	//private Thread sync;
	private boolean gameRunning = true;
	private long timeLastUpdate = System.currentTimeMillis();
	private int state = 0;
	private World world;
	//private WorldGen gen;
	private long numUpdates = 0;
	private boolean forceUpdate = false;
	private boolean forceRender = false;
	private int lastTime = -1;
	private int lastw = 0, lasth = 0;
	private WindowManager windows;
	private NewWindowManager windowsnt;
	private PavoImage shadow;
	public static Game Instance;
	private NavalClient client;
	private PavoServer server;
	private boolean isClient = false;
	private BufferedImage renderBuffer;
	//private BufferedImage chunkBuffer;
	public static PavoSettings Settings = new PavoSettings();
	
	/**
	 * Creates a new instance of the game.
	 */
	public Game() {
		server = new NavalServer(this);
		System.out.println("Server status: " + server.start());
		windows = new WindowManager(this);
		windowsnt = new NewWindowManager(this);
		world = new World(this);
		//gen = new WorldGen();
		threadInit();
		buffer = new PavoImage(Game.Settings.currentWidth,Game.Settings.currentHeight,BufferedImage.TYPE_3BYTE_BGR);
		//chunkBuffer = new BufferedImage(Game.Settings.currentWidth,Game.Settings.currentHeight,BufferedImage.TYPE_3BYTE_BGR);
		shadow = (PavoImage)PavoHelper.createInnerShadow(Game.Settings.currentWidth,Game.Settings.currentHeight);
		int yeart = Calendar.getInstance().get(Calendar.YEAR);
		String years = Integer.toString(yeart);
		yearf = Integer.parseInt(years.substring(0,2));
		yearl = Integer.parseInt(years.substring(2));
		Instance = this;
		//MessageBox.show("Server started", "Sucessfully started a server instance.\n\nYour IP address:" + server.getSelfIP(), 
			//	MessageBoxIcon.Information, true, true);
		//int prv = NavalBattle.getWindowHandler().getToasterManager().getDisplayTime();
		NavalBattle.getWindowHandler().getToasterManager().setDisplayTime(8000);
		NavalBattle.getWindowHandler().getToasterManager().showToaster(
				"Sucessfully started a server instance.\n\nYour IP address:" + server.getSelfIP());
		//NavalBattle.getWindowHandler().getToasterManager().setDisplayTime(prv);
	}
	
	/**
	 * Creates a new instance of the game with given
	 * startup parameters.
	 * @param pos The type of state to open the game
	 * in.
	 * @param flags The flags/parameters for the
	 * <code>PavoOpenState</code>.
	 */
	public Game(PavoOpenState pos, String flags) {
		if (pos == PavoOpenState.OPEN_SERVER) {
			client = new NavalClient(this,flags);
			System.out.println("Client status: " + client.start());
			isClient = true;
			client.send("HELLO");
			client.send("HELLO");
			client.send("HELLO");
			while (client.getSeed() == Long.MIN_VALUE) {
				
			}
			akamaideli3242very();
			Game.Settings.seed = client.getSeed();
			//MessageBox.show("Connection sucessful", "Sucessfully connected to the server.", MessageBoxIcon.Information, true, true);
			//int prv = NavalBattle.getWindowHandler().getToasterManager().getDisplayTime();
			NavalBattle.getWindowHandler().getToasterManager().setDisplayTime(8000);
			NavalBattle.getWindowHandler().getToasterManager().showToaster("Sucessfully connected to the server.");
			//NavalBattle.getWindowHandler().getToasterManager().setDisplayTime(prv);
		}
		windows = new WindowManager(this);
		windowsnt = new NewWindowManager(this);
		world = new World(this);
		//gen = new WorldGen();
		threadInit();
		buffer = new PavoImage(Game.Settings.currentWidth,Game.Settings.currentHeight,BufferedImage.TYPE_3BYTE_BGR);
		//chunkBuffer = new BufferedImage(Game.Settings.currentWidth,Game.Settings.currentHeight,BufferedImage.TYPE_3BYTE_BGR);
		shadow = (PavoImage)PavoHelper.createInnerShadow(Game.Settings.currentWidth,Game.Settings.currentHeight);
		int yeart = Calendar.getInstance().get(Calendar.YEAR);
		String years = Integer.toString(yeart);
		yearf = Integer.parseInt(years.substring(0,2));
		yearl = Integer.parseInt(years.substring(2));
		Instance = this;
	}
	boolean isConnected = false;
	boolean ranCheckup = false;
	/**
	 * Determines if currently connected to a client, or
	 * to server.
	 * @return
	 */
	public boolean isConnectedToClientOrServer() {
		return isConnected;
	}
	
	/**
	 * No touching.
	 */
	public void akamaideli3242very() {
		isConnected = true;
	}
	
	/**
	 * Gets the client (if the current workstation
	 * is acting as a client).
	 * 
	 * Use <code></code>
	 * @return
	 */
	public PavoClient getSelfClient() {
		return client;
	}
	
	/**
	 * Gets the server (if the current workstation us
	 * acting as a server).
	 * 
	 * Use <code></code>
	 * @return
	 */
	public PavoServer getSelfServer() {
		return server;
	}
	
	/**
	 * Determines whether the current workstation is
	 * acting as a client or as a server.
	 * 
	 * @return true if the workstation is a client,
	 * false if the workstation is a server.
	 */
	public boolean isAClient() {
		return isClient;
	}
	
	/*
	 * Gets the window manager for the Game.
	 * @return
	 */
//	public WindowManager getWinMan() {
//		return windows;
//	}
	
	/**
	 * Gets the window manager for the Game.
	 * @return
	 */
	public NewWindowManager getWindows() {
		return windowsnt;
	}
	
	/**
	 * Sets the window manager for the Game.
	 * @param wm
	 */
	public void setWinMan(WindowManager wm) {
		windows = wm;
	}
	/**
	 * Gets the total number of updates that the updator has performed.
	 * @return
	 */
	public long getNumUpdates() {
		return numUpdates;
	}
	/**
	 * Don't play with.
	 */
	private void threadInit() {
		int js = 0;
		if (Settings.OverClock)
			js = Thread.MAX_PRIORITY;
		else
			js = Thread.NORM_PRIORITY;
		updator = new Thread(this);
		state = 1;
		updator.setPriority(js);
		updator.setName("updatorThread");
		updator.setDaemon(true);
		updator.start();
		updator.setPriority(js);
		long lastStart = System.currentTimeMillis();
		while (lastStart + 500 > System.currentTimeMillis()) {
			
		}
		chunkrender = new Thread(this);
		state = 2;
		chunkrender.setPriority(js);
		chunkrender.setName("chunkGenThread");
		chunkrender.setDaemon(true);
		chunkrender.start();
		chunkrender.setPriority(js);
		lastStart = System.currentTimeMillis();
		while (lastStart + 500 > System.currentTimeMillis()) {
			
		}
		//sync = new Thread(this);
		state = 4;
		//sync.setPriority(js);
		//sync.setName("syncThread");
		//sync.setDaemon(true);
		//sync.start();
		//sync.setPriority(js);
		//while (lastStart + 500 > System.currentTimeMillis()) {
		//	
		//}
		renderer = new Thread(this);
		state = 5;
		renderer.setPriority(js);
		renderer.setName("worldRenderingThread");
		renderer.setDaemon(true);
		renderer.start();
		renderer.setPriority(js);
		//state = 3;
		//generator = new Thread(this);
		//generator.setPriority(Thread.MAX_PRIORITY);
		//generator.setName("generatorThread");
		//generator.start();
	}
	private boolean worldReRenderNotNeeded = true;
	private static GameStatistics stats = new GameStatistics();
	/**
	 * The graphics statistics for the game.
	 * @return
	 */
	public static GameStatistics getStats() {
		return stats;
	}
	/**
	 * Immortal caller.
	 */
	public void run() {
		// Game updator
		if (state == 1) {
			while (gameRunning) {
				//System.out.println("Game updator firing..." + Thread.currentThread().getName());
				///while (timeLastUpdate + 100 > System.currentTimeMillis()) {
					//if (forceUpdate)
					//	break;
				//}
				while (!forceUpdate) {
					;;;
				}
				if (lastw != Settings.currentWidth || lasth != Settings.currentHeight) {
					lastw = Settings.currentWidth;
					lasth = Settings.currentHeight;
					getWindows().$akafre();
					//shadow = PavoHelper.createInnerShadow(Game.Settings.currentWidth,Game.Settings.currentHeight);
				}
				//System.out.println("winupdate");
				numUpdates += 100;
				forceUpdate = false;
				long updateStart = System.currentTimeMillis();
				while (getWorld().isLocked()) {}
				getWorld().lock();
				//getWorld().render();
				getWorld().update();
				getWorld().unlock();
				TimeManager tim = getWorld().getTimeManager();
				if (tim.getState() != lastTime) {
					lastTime = tim.getState();
					if (lastTime == 3) {
						becomingDay();
					}
					else if (lastTime == 2) {
						becomingSunrise();
					}
					else if (lastTime == 1) {
						becomingNight();
					}
					else if (lastTime == 0) {
						becomingSunset();
					}
				}
				update();
				long updateFinish = System.currentTimeMillis() - updateStart;
				getStats().SmSK280K99(updateFinish);
				timeLastUpdate = System.currentTimeMillis();
				if (!getStats().isGenerating() && !inStatement) {
					inStatement = true;
					try {
						String name = "";
						if (chunkrender != null)
							name = chunkrender.getName();
						chunkrender.stop();
						chunkrender = null;
						System.gc();
						System.out.println("Thread " + name + " is probably dead.");
					}
					catch (Throwable t) {
						
					}
					for (int c = 0; c < 5; c++) {
						System.gc();
					}
					getWorld().getEntityManager().gameDoneGenerating();
					if (isAClient()) {
						this.getSelfClient().send("Chunk gen has been complete!");
					}
					else {
						this.getSelfServer().send("Chunk gen has been complete on the server!");
					}
				}
			}
		}
		// Chunk renderer
		else if (state == 2) {
			while (gameRunning && getWorld().hasMoreChunks()) {
				//while (!getWorld().isReadyForGen()) {
					
				//}
				//System. out.println("Chunk gen firing..." + Thread.currentThread().getName());
				if (getWorld().hasMoreChunks()) {
					getWorld().genNextChunk();
					// Make a small break between each generation.
					long start = System.currentTimeMillis();
					while (start + 150 > System.currentTimeMillis()) {
						;;;
					}
				}
				else {
					//Chunk fcs = getWorld().getChunk(0);
					//System.out.println(fcs.water00+"wateramount");
						for (int c = 0; c < getWorld().getTotalChunks(); c++) {
							Chunk chunk = getWorld().getChunk(c);
							//getWorld().getEntityManager().AQms03KampOQ9103nmJMs((chunk.getZ()*2), (chunk.getX()*2), chunk.water00);
							//getWorld().getEntityManager().AQms03KampOQ9103nmJMs((chunk.getZ()*2)+1, (chunk.getX()*2), chunk.water01);
							//getWorld().getEntityManager().AQms03KampOQ9103nmJMs((chunk.getZ()*2), (chunk.getX()*2)+1, chunk.water10);
							//getWorld().getEntityManager().AQms03KampOQ9103nmJMs((chunk.getZ()*2)+1, (chunk.getX()*2)+1, chunk.water11);
					}
					//Game.getStats().SmKdn02nOaP(1);
					break;
				}
			}
			//Chunk fcs = getWorld().getChunk(0);
			//System.out.println(fcs.water00+"wateramount");
			for (int c = 0; c < getWorld().getTotalChunks(); c++) {
				Chunk chunk = getWorld().getChunk(c);
				getWorld().getEntityManager().AQms03KampOQ9103nmJMs((chunk.getZ()*2), (chunk.getX()*2), chunk.water00);
				getWorld().getEntityManager().AQms03KampOQ9103nmJMs((chunk.getZ()*2)+1, (chunk.getX()*2), chunk.water01);
			getWorld().getEntityManager().AQms03KampOQ9103nmJMs((chunk.getZ()*2), (chunk.getX()*2)+1, chunk.water10);
				getWorld().getEntityManager().AQms03KampOQ9103nmJMs((chunk.getZ()*2)+1, (chunk.getX()*2)+1, chunk.water11);
		}
			getWorld().getWorldStatus().NOTOUCH_930202894(0);
			Game.getStats().SmKdn02nOaP(1);
		}
		else if (state == 4) {
			while (gameRunning) {
				long start = System.currentTimeMillis();
				while (start + 250 > System.currentTimeMillis()) {
					//;;;//System.gc();
					PavoHelper.threadSleep();
				}
			}
		}
		else if (state == 5) {
			while (gameRunning && Settings.isUsingMultithreadedRenderer) {
				worldReRenderNotNeeded = true;
				while (getWorld().isLocked()) {
					//PavoHelper.threadSleep();
				}
				getWorld().lock();
				getWorld().render();
				getWorld().unlock();
				/*long start = System.currentTimeMillis();
				while (start + 250 > System.currentTimeMillis() && worldReRenderNotNeeded) {
					;;;
				}*/
				//PavoHelper.threadSleep();
			}
		}
		System.out.println("Thread " + Thread.currentThread().getName() + " is prepairing to exit context.");
		System.gc();
		// World generator
		//else if (state == 3) {
			//System.out.println("World gen firing..." + Thread.currentThread().getName());
			//gen.generateChunk();
			//getWorld().setWorldGen(gen);
		//}
	}
	boolean inStatement = false;
	public String getGenStatus() {
		return "";
	}
	/**
	 * Unknown
	 * @return
	 * @deprecated
	 */
	public int getGenAmount() {
		return 1;
	}
	int lkw = 0, lkh = 0;
	/**
	 * Renders the Game.
	 */
	public void render() {
		long sjan = System.currentTimeMillis();
		//for (int c = 0; c < 5; c++)
			//System.gc();
		
		if (lkw != Game.Settings.currentWidth || lkh != Game.Settings.currentHeight) {
			buffer = new PavoImage(Game.Settings.currentWidth,Game.Settings.currentHeight,BufferedImage.TYPE_3BYTE_BGR);
			//chunkBuffer = new BufferedImage(Game.Settings.currentWidth,Game.Settings.currentHeight,BufferedImage.TYPE_3BYTE_BGR);
			lkw = Game.Settings.currentWidth;
			lkh = Game.Settings.currentHeight;
		}
		
		Graphics2D g = PavoHelper.createGraphics(buffer);
		
		while (getWorld().isLocked()) {
			
		}
		
		getWorld().lock();
		if (!Settings.isUsingMultithreadedRenderer)
			getWorld().render();
		g.drawImage(getWorld().getBuffer(),0,0,null);
		g.drawImage(getWorld().getTimeManager().getBuffer(),0,0,null);
		
		GameStatistics gs = getStats();
		g.setColor(Color.red);
		//g.setFont(Helper.GUI_GAME_FONT);
		int ma = 38;
		g.drawString("Idling (should be low):" + gs.getDrawIdling() + ". Draw time:" + gs.getDrawTime() + " Live chunks:" + gs.getLiveChunks(),12,660);
		g.drawString("Is generating? " + gs.isGenerating() + ". Total update time:" + gs.getUpdateTime()
				+ ". Last render length:" + gs.getTotalUpdate() + ". Current network state: " + Game.Settings.currentNetworkState, 12,690);
		getWorld().unlock();
		
		while (/*getWinMan().isLocked() && */
				getWindows().isLocked()) {
			
		}
		//getWinMan().lock();
		getWindows().lock();
		if (gJsiw)
			g.setXORMode(Color.yellow);
		
		//g.setXORMode(Color.blue);
		for (int c = 0; c < getWindows().size(); c++) {
			PWindow gw = getWindows().get(c);
			if (gw instanceof GridWindow && gw.isVisible()) {
				GridWindow gr = (GridWindow)gw;
				Location l = gr.getGridLocation();
				if (l != null) {
					Chunk chunk = PavoHelper.convertGridLocationToChunk(getWorld(), l);
					if (chunk != null && PavoHelper.isChunkVisibleOnScreen(getWorld(), chunk)) {
						g.setColor(Color.red);
						int ssx = (getWorld().getScreenX())+(l.getCol()*50)+25;
						int ssy = (getWorld().getScreenY())+(l.getRow()*50)+25;
						int midx = gr.getLocX()+(gr.getWidth()/2);
						int midy = gr.getLocY()+(gr.getHeight()/2);
						if (Math.sqrt(Math.pow(ssx-midx,2)+Math.pow(ssy-midy,2)) <= gr.getDistanceConstraint()) {
							Polygon p = new Polygon();
							p.addPoint(ssx,ssy);
							p.addPoint(midx-10,midy-10);
							p.addPoint(midx+10,midy+10);
							p.addPoint(ssx,ssy);
							g.fillPolygon(p);
							g.setColor(Color.black);
							g.drawPolygon(p);
						}
					}
				}
			}
		}
		//g.setXORMode(Color.blue);
		if (PavoHelper.getCalculatedSystemSpeed() != SystemSpeed.CREEPER && 
				PavoHelper.getCalculatedSystemSpeed() != SystemSpeed.TURTLE) {
			g.drawImage(shadow,0,0,null);
		}
		//getWinMan().render();
		//getWindows().render();
		//g.drawImage(getWinMan().getBuffer(), 0, 0, null);
		g.drawImage(getWindows().getBuffer(),0,0,null);
		g.dispose();
		//getWinMan().unlock();
		getWindows().unlock();
		Game.getStats().sBm3ns02AKa99mqp392(System.currentTimeMillis() - sjan);
	}
	/**
	 * Gets the active world for the Game.
	 * @return
	 */
	public World getWorld() {
		return world;
	}
	/**
	 * Occurs when the game is going into sunset stage.
	 */
	public void becomingSunset() {
		
	}
	/**
	 * Occurs when the game is going into sunrise stage.
	 */
	public void becomingSunrise() {
		
	}
	/**
	 * Occurs when the game is going into night stage.
	 */
	public void becomingNight() {
		
	}
	/**
	 * Occurs when the game is going into day time stage.
	 */
	public void becomingDay() {
		
	}
	/**
	 * This method should be called sparsingly (which means DO NOT OVER USE). This method is multithreaded, so it puts no stress on the calling thread.
	 * This method is not actually deprecated, but it is called so to ensure that the above message is read.
	 * @deprecated
	 */
	public void forceUpdate() {
		forceUpdate = true;
	}
	
	/**
	 * Occurs when the mouse is moved.
	 * @param me The mouse event for the motion.
	 */
	public void mouseMove(MouseEvent me) {
		if (getWindows().mouseMove(me)) {
			
			return;
		}
		int chx = (-getWorld().getScreenX()) + me.getX();
		int chy = (-getWorld().getScreenY()) + me.getY(); 
		chx /= 50;
		chy /= 50;
		if (chx < PavoHelper.getGameWidth(getWorld().getWorldSize()) * 2 && chy < PavoHelper.getGameHeight(getWorld().getWorldSize()) * 2 &&
		chx >= 0 && chy >= 0) {
			Tile<Entity> e = getWorld().getEntityManager().getTile(chy,chx);
			if (e != null) {
				int acuratex = (-getWorld().getScreenX()) + me.getX() - (chx*50);
				int acuratey = (-getWorld().getScreenY()) + me.getY() - (chy*50);
				Location l = e.getEntity().getLocation();
				acuratex += (chx - l.getCol())*50;
				acuratey += (chy - l.getRow())*50;
				e.getEntity().onMouseMove(acuratex,acuratey);
			}
		}
		lastmx = me.getX();
		lastmy = me.getY();
	}
	
	/**
	 * Occurs when the mouse wheel is changed.
	 * @param mwe The mouse event for the
	 * method.
	 */
	public void mouseWheelChange(MouseWheelEvent mwe) {
		
	}
	
	//Timer mouseLogicTimer = new Timer();
	//TimerTask mouseLogicTask = new $$$MouseLogicTimer();
	/*MouseEvent mouseEventSchedule;
	private class $$$MouseLogicTimer extends TimerTask implements Serializable {
	    public void run() {
	        mouseHeldDown(mouseEventSchedule);
	    }
	}*/
	int lastmx = 0,lastmy = 0;
	int yearf = 0;
	int yearl = 0;
	boolean gJsiw = false;
	public boolean guiUsedMouseDown = false, guiUsedMouseUp = false, guiUsedMouseDrag = false;
	
	/**
	 * Occurs when a mouse button is pushed.
	 * @param me The mouse event for the
	 * indicator.
	 */
	public void mouseDown(MouseEvent me) {
		guiUsedMouseDown = false;
		if (getWindows().mouseDown(me)) {
			guiUsedMouseDown = true;
			return;
		}
		//mouseEventSchedule = me;
		//mouseLogicTask = new $$$MouseLogicTimer();
		//mouseLogicTimer.scheduleAtFixedRate(mouseLogicTask, 0, 10);
		int chx = (-getWorld().getScreenX()) + lastmx;
		int chy = (-getWorld().getScreenY()) + lastmy; 
		chx /= 50;
		chy /= 50;
		if (chx < PavoHelper.getGameWidth(getWorld().getWorldSize()) * 2 && chy < PavoHelper.getGameHeight(getWorld().getWorldSize()) * 2 &&
		chx >= 0 && chy >= 0) {
			Tile<Entity> e = getWorld().getEntityManager().getTile(chy,chx);
			if (e != null) {
				int acuratex = (-getWorld().getScreenX()) + lastmx - (chx*50);
				int acuratey = (-getWorld().getScreenY()) + lastmy - (chy*50);
				Location l = e.getEntity().getLocation();
				acuratex += (chx - l.getCol())*50;
				acuratey += (chy - l.getRow())*50;
				e.getEntity().onMouseDown(acuratex,acuratey,me.getButton() == MouseEvent.BUTTON1);
			}
		}
		if (chx == yearf && chy == yearl) {
			gJsiw = !gJsiw;
		}
	}
	
	/**
	 * Occurs when a mouse button is released.
	 * @param me The mouse event for the
	 * indicator.
	 */
	public void mouseUp(MouseEvent me) {
		guiUsedMouseUp = false;
		if (getWindows().mouseUp(me)) {
			guiUsedMouseUp = true;
			return;
		}
		//mouseLogicTask.cancel();
	}
	
	/**
	 * Occurs when the mouse is dragged.
	 * @param me The mouse event for the motion.
	 */
	public void mouseDragged(MouseEvent me) {
		guiUsedMouseDrag = false;
		if (getWindows().mouseDragged(me)) {
			guiUsedMouseDrag = true;
			return;
		}
	}
	
	/**
	 * Occurs when the mouse is held down.
	 * @param me The mouse event for the
	 * indicator.
	 * 
	 * @deprecated No longer in use.
	 */
	public void mouseHeldDown(MouseEvent me) {
		
	}
	
	/**
	 * Occurs when the Game is shuting down.
	 */
	public void onShutdown() {
		
	}
}