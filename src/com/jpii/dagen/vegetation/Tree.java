package com.jpii.dagen.vegetation;

/**
 * Basic tree structure class.
 * @author MKirkby
 *
 */
public class Tree {
	
	boolean dead = false;
	TreeType type = TreeType.Pine;
	
	/**
	 * Initalises a new instance of Tree class.
	 * @param type The type of tree to create.
	 * @param isdead Is the tree dead?
	 */
	public Tree(TreeType type, boolean isdead) {
		this.type = type;
		dead = isdead;
	}
	
	/**
	 * Sets the type of tree.
	 * @param treetype The type of tree.
	 */
	public void setTreeType(TreeType treetype) {
		type = treetype;
	}
	
	/**
	 * Sets whether the tree should be dead or not.
	 * @param dead True means the tree is dead, otherwise it is not.
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	/**
	 * Gets the type of tree.
	 * @return The type of tree the tree is.
	 */
	public TreeType getTreeType() {
		return this.type;
	}
	
	/**
	 * Gets whether the tree is dead or not.
	 * @return True if the tree is dead, otherwise it is not.
	 */
	public boolean isDead() {
		return dead;
	}
}
