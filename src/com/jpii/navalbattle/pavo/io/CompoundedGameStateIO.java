/**
 * 
 */
package com.jpii.navalbattle.pavo.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.jpii.navalbattle.pavo.Game;

/**
 * @author maximusvladimir
 *
 */
public class CompoundedGameStateIO {
	public static boolean save(Game game, String gameName, String folderPath) {
		if (gameName == null || game == null || folderPath == null || gameName.equals("") || folderPath.equals(""))
			return false;
		
		try {
			//java.io.
			File f = new File(folderPath);
			f.mkdirs();
			
			f = new File(folderPath+"\\"+gameName+".psf");
			if (!f.exists())
				f.createNewFile();
		}
		catch (Throwable t) {
			
		}
		
		System.out.println("Saving the game...");
		
		ObjectOutput out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(folderPath+"\\"+gameName+".psf"));
			out.writeObject(game);
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
		
		System.out.println("Game saved!");
		
		return true;
	}
	
	public static Game load(String gameName, String folderPath) {
		if (gameName == null || folderPath == null || gameName.equals("") || folderPath.equals(""))
			return null;
		Game g;
		try {
	         FileInputStream fileIn = new FileInputStream(folderPath+"\\"+gameName+".psf");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         g = (Game)in.readObject();
	         in.close();
	         fileIn.close();
	    } catch(Throwable t) {
	    	  t.printStackTrace();
	    	  return null;
	    }
		return g;
	}
}
