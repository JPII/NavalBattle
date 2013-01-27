/**
 * 
 */
package com.jpii.navalbattle.pavo.grid;



/**
 * @author maximusvladimir
 * Shouldn't be used according to the user's 
 */
public class Tile<T> {
	T parent;
	Entity ent;
	Id id;
	public Tile(T parent, int r, int c) {
		this.parent = parent;
		if (!(parent instanceof Entity))
			throw new RuntimeException("The provided object is not an entity.");
	}
	public void setId(Id id) {
		this.id = id;
	}
	public Id getId() {
		return id;
	}
	public short getSuperId() {
		return id.getSuperId();
	}
	public short getSubId() {
		return id.getSubId();
	}
	public T getEntity() {
		return this.parent;
	}
}
