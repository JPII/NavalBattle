package com.jpii.navalbattle.pavo.grid;

public class GridedEntityTileOrientation {
	public static final byte ORIENTATION_LEFTTORIGHT = 0x0A;
	public static final byte ORIENTATION_TOPTOBOTTOM = 0x1A;
	private static int[] NULLPOINTER = new int[]{0};
	/*public static final byte ORIENTATION_RIGHTTOLEFT = 0x2A;
	public static final byte ORIENTATION_BOTTOMTOTOP = 0x3A;*/
	
	private int[][] INDICES = new int[2][];
	
	public GridedEntityTileOrientation() {
	}
	
	public void setLeftToRightImage(int... storeId) {
		INDICES[0] = storeId;
	}
	
	public void setTopToBottomImage(int... storeId) {
		INDICES[1] = storeId;
	}
	
	
	public int[] memCall(byte offset) {
		if (offset == ORIENTATION_LEFTTORIGHT)
			return INDICES[0];
		if (offset == ORIENTATION_TOPTOBOTTOM)
			return INDICES[1];
		
		return NULLPOINTER;
	}
}