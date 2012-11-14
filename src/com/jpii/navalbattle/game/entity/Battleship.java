package com.jpii.navalbattle.game.entity;

import java.awt.Image;

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.game.entity.component.IntelligenceModule;

public class Battleship extends IntelligentEntity {
	public Battleship(Location location, Image image, IntelligenceModule intelligenceModule, String tag, int health) {
		super(location,image,intelligenceModule,tag,health);
	}
	
	public void rotateTurrets() {
		
	}
	
	public void onMouseHover(int mx, int my) {
		rotateTurrets();
		updateImage();
	}
}
