/**
 * 
 */
package com.jpii.navalbattle.pavo.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import sun.reflect.Reflection;

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.game.entity.Entity;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;

/**
 * @author maximusvladimir
 *
 */
public class GridWindow extends GameWindow {
	Location local;
	private static ArrayList<String> classes;
	double dconstraint = 900;
	/**
	 * 
	 */
	public GridWindow() {
		super();
		
		try {
			classes = getClassNamesFromPackage("com.jpii.navalbattle.game.entity");
		} catch (Throwable e) {

		}
		
		setSize(200,200);
		setGridLocation(0,0);
	}
	public void setGridLocation(int r, int c) {
		local = new Location(r,c);
	}
	public void setGridLocation(Location loc) {
		local = loc;
	}
	public Location getGridLocation() {
		return local;
	}
	public void setDistanceConstraint(double d) {
		dconstraint = d;
	}
	public double getDistanceConstraint() {
		return dconstraint;
	}
	public void render() {
		super.render();
		Game game = getWinMan().getGame();
		if (game == null)
			return;
		Graphics2D g = PavoHelper.createGraphics(getBuffer());
		g.setColor(Color.black);
		g.drawString("Info:", 10, 40);
		if (local == null)
			return;
		//System.out.println("local not null");
		if (game.getWorld() == null)
			return;
		//System.out.println("world not null");
		if (game.getWorld().getEntityManager() == null)
			return;
		if (game.getWorld().getEntityManager().isTileFilledWithWater(local.getRow(),local.getCol())) {
			g.drawString("Tile has water.", 10, 60);
		}
		else {
			g.drawString("Tile doesn't have water.", 10, 60);
		}
		Entity ef = game.getWorld().getEntityManager().getEntity(local.getRow(),local.getCol());
		String nma = "Unknown";
		for (int c = 0; c < classes.size(); c++) {
			try {
				if (!classes.get(c).replace(".class", "").equals("Entity")) {
					String ghs = "com.jpii.navalbattle.game.entity." + classes.get(c).replace(".class", "");
					Class<?> clocal = ef.getClass().getClassLoader().loadClass(ghs);
					int id = (Integer) Entity.class.
							getMethod("getId", null).invoke(clocal.newInstance(), null);
					//System.out.println("id"+id);
					if (id == ef.getId()) {
						nma = classes.get(c).replace(".class", "");
						break;
					}
				}
			}
			catch (Throwable th) {
				//th.printStackTrace();
			}
			//System.out.println("c"+classes.get(c));
		}
		g.drawString("Occupied by: " + nma, 10, 70);
	}
	private ArrayList<String> getClassNamesFromPackage(String packageName) throws IOException{
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    URL packageURL;
	    ArrayList<String> names = new ArrayList<String>();;

	    packageName = packageName.replace(".", "/");
	    packageURL = classLoader.getResource(packageName);

	    if(packageURL.getProtocol().equals("jar")){
	        String jarFileName;
	        JarFile jf ;
	        Enumeration<JarEntry> jarEntries;
	        String entryName;

	        // build jar file name, then loop through zipped entries
	        jarFileName = URLDecoder.decode(packageURL.getFile(), "UTF-8");
	        jarFileName = jarFileName.substring(5,jarFileName.indexOf("!"));
	        System.out.println(">"+jarFileName);
	        jf = new JarFile(jarFileName);
	        jarEntries = jf.entries();
	        while(jarEntries.hasMoreElements()){
	            entryName = jarEntries.nextElement().getName();
	            if(entryName.startsWith(packageName) && entryName.length()>packageName.length()+5){
	                entryName = entryName.substring(packageName.length(),entryName.lastIndexOf('.'));
	                names.add(entryName);
	            }
	        }

	    // loop through files in classpath
	    }else{
	        File folder = new File(packageURL.getFile());
	        File[] contenuti = folder.listFiles();
	        String entryName;
	        for(File actual: contenuti){
	            entryName = actual.getName();
	            //entryName = entryName.substring(0, entryName.lastIndexOf('.'));
	            names.add(entryName);
	        }
	    }
	    return names;
	}
}
