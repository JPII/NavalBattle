/**
 * The primary package resposible for dagen.
 */
package com.jpii.dagen;

import java.util.Random;

/**
 * Primary generating Engine for dagen.
 * @author MKirkby
 *
 */
public class Engine {
	int width = 0;
	int height = 0;
	int seed_i = 0;
	int cycles = 0;
	
	int mtMag = 0;
	double magnitude = 1.0;
	int smoothamount = 10;
	boolean isgenerated = false;
	EngineStatistics stats;
	
	double waterLevel = 0.7;
	
	double[][] points;
	Random rand;
	
	/**
	 * Creates an instance of the Engine class.
	 * @param width The width of the generation matrix
	 * @param height The height of the generation matrix
	 */
	public Engine(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Gets the height of the generation matrix.
	 * @return The height of the matrix.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the width of the generation matrix.
	 * @return The width of the generation matrix.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the current engine seed.
	 * @return The seed of the generator.
	 */
	public int getSeed() {
		return seed_i;
	}
	
	/**
	 * Gets the number of iteration cycles complete.
	 * @return The int of cycles.
	 */
	public int getCycles() {
		return cycles;
	}
	
	/**
	 * Returns the random engine.
	 * @return The random object.
	 */
	public Random getRand() {
		return rand;
	}
	
	/**
	 * Generated the map, and places it in the generation matrix.
	 * @param type The type of map to produce. (No longer used).
	 * @param seed The seed (random value) of the map.
	 * @param magnitude The magnitude (roughness) of the map.
	 */
	public void generate(MapType type, int seed, double magnitude) {
		cycles = 0;
		points = new double[width][height];
		
		seed_i = seed;
		
		rand = new Random(seed);
		this.magnitude = magnitude;
		
		if (type == MapType.Hills)
			mtMag = 3;
		else if (type == MapType.Plains)
			mtMag = 1;
		else if (type == MapType.Dips)
			mtMag = -3;
		
		iterate(0,0,width,height,rand.nextDouble(),rand.nextDouble(),rand.nextDouble(),rand.nextDouble());
		iterate(smoothamount >> 2);
		reRunStats();
		isgenerated = true;
	}
	
	/**
	 * Returns whether data has been generated yet or not.
	 * @return
	 */
	public boolean isGenerated() {
		return isgenerated;
	}

	/**
	 * Cycles the main generation routine. Do not play with.
	 * @param x The rectangular x mulitple
	 * @param y The rectangular y mulitple
	 * @param width The rectangular width of the iteration stack.
	 * @param height The rectangular height of the iteration stack.
	 * @param c1 The positive x random square coordinate.
	 * @param c2 The positive y random square coordinate.
	 * @param c3 The middle x random square coordinate.
	 * @param c4 The middle y random square coordinate.
	 */
	private void iterate(double x, double y, double width, double height, double c1, double c2, double c3, double c4) {
		cycles += 1;
        double e1,e2,e3,e4,e5,e6,e7;
        e6 = Math.floor(width / 2);
        e7 = Math.floor(height / 2);
        if (width > 1 || height > 1)
        {
            e5 = snap(((c1 + c2 + c3 + c4) / 4) + ((rand.nextDouble() - 0.5) * ((e6 + e7) / (width+height) * magnitude)));
            e1 = snap((c1 + c2) / 2);
            e2 = snap((c2 + c3) / 2);
            e3 = snap((c3 + c4) / 2);
            e4 = snap((c4 + c1) / 2);		
            iterate(x, y, e6, e7, c1, e1, e5, e4);
            iterate(x + e6, y, width - e6, e7, e1, c2, e2, e5);
            iterate(x + e6, y + e7, width - e6, height - e7, e5, e2, c3, e3);
            iterate(x, y + e7, e6, height - e7, e4, e5, e3, c4);
        }
        else
        {
        	double c = (c1 + c2 + c3 + c4) / 4;
            points[(int)(x)][(int)(y)] = c;
            if (width == 2)
                points[(int)(x + 1)][(int)(y)] = c;
            if (height == 2)
                points[(int)(x)][(int)(y + 1)] = c;
            if ((width == 2) && (height == 2))
                points[(int)(x + 1)][(int)(y + 1)] = c;
        }
    }

	/**
	 * "Snaps" a value inbetween 0.0 and 1.0.
	 * @param num The number to snap.
	 * @return The snapped number.
	 */
    private double snap(double num) {
        if (num < 0)
            return 0;
        else if (num > 1.0)
            return 1.0;
        else
        	return num;
    }
	
    /**
     * Cycles the main generation routine. Do not play with.
     * @param cycli The number of recursions to complete.
     */
    private void iterate(int cycli) {
    	if (cycli > 0)
    		iterate(cycli - 1);
    	for (int x = 1; x < width-2; x++) {
    		for (int y = 1; y < height-2; y++) {
    			double a = points[x][y];
    			a += points[x+1][y];
    			a += points[x][y+1];
    			
    			a/=3;
    			
    			points[x][y] = a;
    			points[x+1][y] = a;
    			points[x][y+1] = a;
    		}
    	}
    }
    
    /**
     * Gets the generated points.
     * @return The generated value matrix.
     */
	public double[][] getPoints() {
		if (points == null)
			throw new NullPointerException("Map has not been generated yet.");
		
		return points;
	}
	
	/**
	 * Gets a point in the matrix. Better than "getPoints()".
	 * @param x The x location in the matrix.
	 * @param y The y location in the matrix.
	 * @return The value at the location in the matrix.
	 */
	public double getPoint(int x, int y) {
		if (points != null) {
			if (x < 0)
				x = 0;
			if (y < 0)
				y = 0;
			if (x > width - 1)
				x = width - 1;
			if (y > height - 1)
				y = height - 1;
			
			return points[x][y];
		}
		return -1;
	}
	
	/**
	 * Get the smoothing factor.
	 * @return The amount of smoothing to apply.
	 */
	public int getSmoothFactor() {
		return smoothamount;
	}
	
	/**
	 * Gets a random value between the two numbers, using the current seed.
	 * @param min The minimum, inclusive number.
	 * @param max The maximum, exclusive number.
	 * @return A number between the given values.
	 */
	public int r(int min, int max) {
		return rand.nextInt(max-min) + min;
	}

	/**
	 * Sets the smoothing factor.
	 * @param amount The amount to smooth by.
	 */
	public void setSmoothFactor(int amount) {
		smoothamount = amount;
	}
	
	/**
	 * Sets the water height.
	 * @param amount A value between (0.0-1.0), indicating where water should be.
	 */
	public void setWaterLevel(double amount) {
		waterLevel = amount;
	}
	
	/**
	 * Returns the water height.
	 * @return
	 */
	public double getWaterLevel() {
		return waterLevel;
	}
	
	/**
	 * Reruns the engine statistics.
	 */
	public void reRunStats() {
		int w = 0;
		for (int x = 0; x< width; x++) {
			for (int y = 0; y < height; y++) {
				if (points[x][y] < waterLevel)
					w += 1;
			}
		}
		stats = new EngineStatistics((w * 100) / (width*height));
	}

	/**
	 * Returns the statistics of the engine.
	 * @return The statistics object.
	 */
	public EngineStatistics getStats() {
		return stats;
	}
	
	/**
	 * Sets a value at the specified coordinates.
	 * @see The x and y values are snapped.
	 * @param x The x-location
	 * @param y The y-location
	 * @param amount The value to set.
	 */
	public void setPoint(int x, int y,double amount) {
		amount = snap(amount);
		if (points != null) {
			if (x < 0)
				x = 0;
			if (y < 0)
				y = 0;
			if (x > width - 1)
				x = width - 1;
			if (y > height - 1)
				y = height - 1;
			
			points[x][y] = amount;
		}
	}
}
