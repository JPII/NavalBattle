package com.jpii.dagen;

/**
 * A statistics class manager for Engine class.
 * @author MKirkby
 */
public class EngineStatistics {
	Engine eng;
	int percentwater;
	/**
	 * Constructs a new instance of EngineStatistics.
	 * @param eng
	 */
	public EngineStatistics(int ski) {
		percentwater = ski;
	}
	
	/**
	 * Gets the amount of land in the water. See Engine.getWaterLevel().
	 * @return Returns a number out of 100 that pertains to the amount that is water.
	 */
	public int getPercentWater() {
		return percentwater;
	}
	
	/**
	 * Gets the amount of land in the map. See Engine.getWaterLevel().
	 * @return Returns a number out of 100 that pertains to the amount that is land.
	 */
	public int getPercentLand() {
		return 100 - getPercentWater();
	}
}
