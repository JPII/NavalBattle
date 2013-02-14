package com.jpii.navalbattle.pavo.grid;

public class GridedEntityTileOrientation {
	public static final byte ORIENTATION_LEFTTORIGHT = 0x0A;
	public static final byte ORIENTATION_TOPTOBOTTOM = 0x1A;
	private static Id NULLPOINTER = new Id(0,0);
	/*public static final byte ORIENTATION_RIGHTTOLEFT = 0x2A;
	public static final byte ORIENTATION_BOTTOMTOTOP = 0x3A;*/
	
	private Id[] INDICES = new Id[2];
	
	public GridedEntityTileOrientation() {
	}
	
	public void setLeftToRightImage(Id storeId) {
		INDICES[0] = storeId;
	}
	
	public void setTopToBottomImage(Id storeId) {
		INDICES[1] = storeId;
	}
	
	public Id memCall(byte offset) {
		if (offset == ORIENTATION_LEFTTORIGHT)
			return INDICES[0];
		if (offset == ORIENTATION_TOPTOBOTTOM)
			return INDICES[1];
		
		return NULLPOINTER;
	}
}