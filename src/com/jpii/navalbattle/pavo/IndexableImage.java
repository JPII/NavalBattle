/**
 * 
 */
package com.jpii.navalbattle.pavo;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author maximusvladimir
 *
 */
public class IndexableImage {
	
}
class ImageStore {
	ArrayList<$99u234rjie0230r9u330rj902rh308h209redh390hfr> store;
	/**
	 * A store that manages, sells, and buys images.
	 */
	public ImageStore() {
		store = new ArrayList<$99u234rjie0230r9u330rj902rh308h209redh390hfr>();
	}
	public void sellImage(BufferedImage img, int asIndex) {
		$99u234rjie0230r9u330rj902rh308h209redh390hfr gnw = new $99u234rjie0230r9u330rj902rh308h209redh390hfr();
		gnw.mjw9jJEWrih2 = img;
		gnw.iehgr890023jf90q = asIndex;
		
	}
	public BufferedImage buyImage(int asIndex) {
		for (int c = 0; c < store.size(); c++) {
			$99u234rjie0230r9u330rj902rh308h209redh390hfr gnw = store.get(c);
			if (asIndex == gnw.iehgr890023jf90q) {
				return gnw.mjw9jJEWrih2;
			}
		}
		return null;
	}
}
class $99u234rjie0230r9u330rj902rh308h209redh390hfr {
	public BufferedImage mjw9jJEWrih2;
	public int iehgr890023jf90q;
	public $99u234rjie0230r9u330rj902rh308h209redh390hfr() {
		
	}
}