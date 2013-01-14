package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.pavo.EntityManager;

public class Submarine extends Entity {

	public Submarine(EntityManager eman, Location l) {
		super(eman, l);
		setId(8);
		setIds(8,9,3000,40000,600000,2000000,9000);
		setTag("delta");
	}
}
