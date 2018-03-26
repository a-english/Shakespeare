
/**
 * Angela English
 * CSC 130 MW 4-5:15
 * Working alone
 */

//import java.awt.print.Printable;

public class AVL<E extends Comparable<? super E>> extends BinarySearchTree<E>{
	
	public void incCount(E x) {
		super.incCount(x);
		int before=this.size;
		rebalance(overallRoot);
		if(this.size!=before)
			System.out.print("MAGIC!");
	}
	
	private BSTNode rebalance(BSTNode leaf){
		if(leaf==null)
			return null;
		if(height(leaf.left)-height(leaf.right)==2) {
			  if (height(leaf.left.left)-height(leaf.left.right)>0)
				  leaf=singleRightRotation(leaf);
			  else
				  leaf=doubleLeftRightRotation(leaf);
		  }
		else if(height(leaf.right)-height(leaf.left)==2) {
			  if (height(leaf.right.right)-height(leaf.right.left)>0)
				  leaf=singleLeftRotation(leaf);
			  else
				  leaf=doubleRightLeftRotation(leaf);
			}
		return leaf;
	}
	
	int height(BSTNode leaf){
		 if(leaf==null)
			 return -1;
		 return Math.max(height(leaf.left), height(leaf.right))+1;
	 }
	
	
	//case 1
	private BSTNode singleRightRotation(BSTNode k2)
	{
		BSTNode k1 = k2.left;
		k2.left=k1.right;
		k1.right=k2;
		return k1;
	}
	//case 4
	private BSTNode singleLeftRotation(BSTNode k1)
	{
		BSTNode k2 = k1.right;
		k1.right=k2.left;
		k2.left=k1;
		return k2;
	}
	//case 2
	private BSTNode doubleLeftRightRotation(BSTNode k3)
	{
		k3.left=singleLeftRotation(k3.left);
		return singleRightRotation(k3); 
	}
	//case 3
	private BSTNode doubleRightLeftRotation(BSTNode k1)
	{
		k1.right=singleRightRotation(k1.right);
		return singleLeftRotation(k1);
	}
}
