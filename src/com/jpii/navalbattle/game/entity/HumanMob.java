package com.jpii.navalbattle.game.entity;

import com.jpii.navalbattle.game.Location;
import com.jpii.navalbattle.pavo.EntityManager;
import com.jpii.navalbattle.pavo.GameBeta;

public class HumanMob extends Entity {
	public HumanMob(EntityManager em,Location l) {
		super(em,l);
		setId(1);
	}
	public void update() {
		super.update();
		/*int code = GameBeta.Settings.rand.nextInt(3);
		Entity next = getManager().getEntity(getLocation().getRow()+1,getLocation().getCol());
		if (next != null && code == 1) {
			getManager().setEntity(getLocation().getRow()+1, getLocation().getCol(),new HumanMob(getManager()));
			return;
		}
		
		next = getManager().getEntity(getLocation() .getRow(),getLocation().getCol()+1);
		if (next != null) {
			getManager().setEntity(getLocation().getRow(), getLocation().getCol()+1,new HumanMob(getManager()));
			return;
		}*/
	}
}
