/**
 * 
 */
package com.jpii.dagen.processing;

import com.jpii.dagen.Engine;

/**
 * A helper class for modifying Engine results.
 * @author MKirkby
 *
 */
public class FilterEngine {
	/**
	 * Applys a universe rise in variable values, by the specified amount.
	 * @param eng The engine to effect.
	 * @param amount The amount to rise by.
	 */
	public static void applyRise(Engine eng, double amount) {
		for (int x = 0; x < eng.getWidth(); x++) {
			for (int y = 0; y < eng.getHeight(); y++) {
				eng.setPoint(x, y, eng.getPoint(x, y) + amount);
			}
		}
	}
	
	/**
	 * Changes the value to fit that a certain percent of the map is in water.
	 * @param eng The engine to effect.
	 * @param percentWater The amount of the map that should be water.
	 */
	public static void applyPercent(Engine eng, int percentWater) {
		
		boolean sucessfull = true;
		
		eng.reRunStats();
		
		if (eng.getStats().getPercentWater() <= percentWater)
			sucessfull = false;
		
		while (!sucessfull) {
			applyRise(eng, -0.1);
			
			eng.reRunStats();
			
			if (eng.getStats().getPercentWater() > percentWater)
				sucessfull = true;
		}
	}
	
	/**
	 * Similar to applyPercent, but instead it lowers the amount of water.
	 * @param eng The engine to effect.
	 * @param percentWater The amount that should be less than water.
	 */
	public static void applyPercentUpper(Engine eng, int percentWater) {
		
		boolean sucessfull = true;
		
		eng.reRunStats();
		
		if (eng.getStats().getPercentWater() >= percentWater)
			sucessfull = false;
		
		while (!sucessfull) {
			applyRise(eng, 0.1);
			
			eng.reRunStats();
			
			if (eng.getStats().getPercentWater() < percentWater)
				sucessfull = true;
		}
	}

}
