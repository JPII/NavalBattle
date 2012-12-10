package com.jpii.navalbattle.pavo;

public class EntityReference {
	public int referenceId = 0;
	public int imageId = 0;
	public boolean ghost = false;
	public EntityReference(int refid,int imgid) {
		referenceId = refid;
		imageId = imgid;
	}
	public void makeGhost() {
		ghost = true;
	}
	public void destroyGhost() {
		ghost = false;
	}
	public boolean isGhost() {
		return ghost;
	}
}
