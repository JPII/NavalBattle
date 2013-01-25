package com.jpii.navalbattle.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;
import java.util.ArrayList;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.game.entity.*;

/**
 * The grid class. Contains details about entities.
 * @author MKirkby
 *
 */
public class Grid {
    private Entity[][] entities;
    int width, height;
    /**
     * Creates a new instance of the Grid.
     */
    @SuppressWarnings("unused")
	public Grid() {
        width = (Constants.WINDOW_WIDTH * 4) / (Constants.CHUNK_SIZE / 2);
        height = (Constants.WINDOW_HEIGHT * 4) / (Constants.CHUNK_SIZE / 2);
        entities = new Entity[width + 1][height + 1];
        BufferedImage bu = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bu.getGraphics();
        g.setColor(Color.black);
        g.drawRect(0, 0, 50, 50);
        int c = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //entities[x][y] = new Entity(new Location(x, y), bu, "gridRect" + c);
                c++;
            }
        }
    }
    /**
     * Returns the entity at the specified location.
     * @param x The column of the entity.
     * @param y The row of the entity.
     * @return Could be null.
     */
    public Entity getEntity(int x, int y) {
        boolean failFlag = false;
        if (x > width) {
            x = width;
            failFlag = true;
        }
        if (x < 0) {
            x = 0;
            failFlag = true;
        }
        if (y > height) {
            y = height;
            failFlag = true;
        }
        if (y < 0) {
            y = 0;
            failFlag = true;
        }
        if (failFlag) {
            String stack = "";
            for (int c = 0; c < Thread.currentThread().getStackTrace().length; c++) {
                stack += "\n" + Thread.currentThread().getStackTrace()[c].getMethodName() + ": " + Thread.currentThread().getStackTrace()[c].getLineNumber();
            }
            NavalBattle.getDebugWindow().printError("Error accessing power grid. Fallback within bounds sucess.\n" + stack);
        }
        return entities[x][y];
    }
    /**
     * Sets the entity at the entity's location in the grid.
     * @param e The entity.
     */
    public void setEntity(Entity e) {
    	int x = e.getLocation().getCol();
    	int y = e.getLocation().getRow();
        boolean failFlag = false;
        if (x > width) {
            x = width;
            failFlag = true;
        }
        if (x < 0) {
            x = 0;
            failFlag = true;
        }
        if (y > height) {
            y = height;
            failFlag = true;
        }
        if (y < 0) {
            y = 0;
            failFlag = true;
        }
        if (failFlag) {
            String stack = "";
            for (int c = 0; c < Thread.currentThread().getStackTrace().length; c++) {
                stack += "\n" + Thread.currentThread().getStackTrace()[c].getMethodName() + ": " + Thread.currentThread().getStackTrace()[c].getLineNumber();
            }
            NavalBattle.getDebugWindow().printError("Error accessing power grid. Fallback within bounds sucess.\n" + stack);
        }
        entities[x][y] = e;
    }
    /**
     * The width of the Grid.
     * @return An int that match the grid width.
     */
    public int getWidth() {
        return width;
    }
    /**
     * Finds the first entity matching the specified tag.
     * @param tag The tag to search for.
     * @return The found entity, may be null.
     */
    public Entity findEntity(String tag) {
    	return findEntity(tag,true);
    }
    /**
     * Finds the first entity matching the specified tag.
     * @param tag The tag to search for.
     * @param caseMatters Should it matter if the tag is in uppercase or lowercase???
     * @return The found entity, may be null.
     */
    public Entity findEntity(String tag, boolean caseMatters) {
    	for (int x = 0; x < width; x++) {
    		for (int y = 0; y < height; y++) {
    			Entity a = entities[x][y];
    			if (caseMatters) {
    				//if (a.getTag().equals(tag))
    					//return a;
    			}
    			else {
    				//if (a.getTag().toLowerCase().equals(tag.toLowerCase()))
    					//return a;
    			}
    		}
    	}
    	return null;
    }
    /**
     * Finds all entities of a certain type. TALK TO MAX TO FIND OUT HOW TO USE THIS.
     * @param typeOf The type of entity to search for.
     * @return An array of entities. Will never be null.
     */
    public Entity[] findAllEntities(Entity typeOf) {
    	ArrayList<Entity> ecache = new ArrayList<Entity>();
    	for (int x = 0; x < width; x++) {
    		for (int y = 0; y < height; y++) {
    			Entity a = entities[x][y];
    			if (a.getClass().isInstance(typeOf))
    				ecache.add(a);
    		}
    	}
    	return (Entity[]) ecache.toArray();
    }
    /**
     * The height of the Grid.
     * @return An int responding to the height of the Grid.
     */
    public int getHeight() {
        return height;
    }
}