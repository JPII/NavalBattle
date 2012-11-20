package com.jpii.navalbattle.renderer.weather;

public class WeatherManager {
	WeatherMode wm;
	public WeatherManager() {
		wm = WeatherMode.MostlySunny;
	}
	public void setWeather(WeatherMode wm) {
		this.wm = wm;
	}
	public WeatherMode getWeather() {
		return wm;
	}
}
