/**
 * 
 */
package com.jpii.navalbattle.pavo.grid;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author maximusvladimir
 *
 */
public class IndexableImage {
	public static ImageStore Store = new ImageStore();
	int id;
	public IndexableImage(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static void populateStore(int mutexId, BufferedImage img) {
		Store.sellImage(img, mutexId);
	}
	public static BufferedImage getImage(int mutexId) {
		return Store.buyImage(mutexId);
	}
	public BufferedImage getActualImage() {
		return Store.buyImage(getId());
	}
	public static int getStoreSize() {
		return Store.$934uthfreIw9oihifreh90qajpr9otqh9o3q();
	}
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
		for (int c = 0; c < store.size(); c++) {
			$99u234rjie0230r9u330rj902rh308h209redh390hfr gs = store.get(c);
			if (asIndex == gs.iehgr890023jf90q) {
				throw new IllegalArgumentException("There is already an image in the store that has the same id.");
			}
			if (img.equals(gs.mjw9jJEWrih2)) {
				throw new IllegalArgumentException("There is already the same image in the store. It has the id " + gs.iehgr890023jf90q + ".");
			}
		}
		store.add(gnw);
	}
	public int $934uthfreIw9oihifreh90qajpr9otqh9o3q() {
		return store.size();
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