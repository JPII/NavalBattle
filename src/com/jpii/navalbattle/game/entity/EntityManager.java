package com.jpii.navalbattle.game.entity;

import java.util.ArrayList;

public class EntityManager {
	ArrayList<Entity> entities;
	public EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	public Entity getEntityByReference(EntityReference ref) {
		if (ref.referenceId == -1)
			return null; // There is no Entity at the specified reference location.
		for (int c = 0; c < entities.size(); c++) {
			Entity e = entities.get(c);
			if (e != null && e.getReference() == ref)
				return e;
		}
		return null;
	}
}
