package com.jpii.navalbattle.renderer.particles;

import java.awt.*;

public class Particle implements Runnable{
	float x, y, radius;
	float health = 100;
	float maxHealth = 100;
	public Particle() {
		
	}
	public void setMaxHealth(float max) {
		if (health > max)
			health = max;
		maxHealth = max;
	}
	public float getMaxHealth() {
		return maxHealth;
	}
	public void run() {
		update();
	}
	public void update() {
		if (health <= 0)
			return;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setRadius(float rad) {
		radius = rad;
	}
	public float getRadius() {
		return radius;
	}
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}
	public void draw(Graphics2D g) {
		
	}
}
