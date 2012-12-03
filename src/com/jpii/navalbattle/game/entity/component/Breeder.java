package com.jpii.navalbattle.game.entity.component;

import com.jpii.navalbattle.game.entity.Entity;

public abstract class Breeder<E> {
	Entity parent1,parent2;
	public Breeder(Entity parent1, Entity parent2) {
		this.parent1 = parent1;
		this.parent2 = parent2;
	}
	public abstract E breed();
}
