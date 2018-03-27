
/**
 * Angela English
 * CSC 130 MW 4-5:15
 * Working alone
 */

//import java.awt.print.Printable;

public class AVL<E extends Comparable<? super E>> extends BinarySearchTree<E>{
	
	public void incCount(E x) {
		super.incCount(x);
		overallRoot=rebalance(overallRoot);
		//remove later
		TreePrinter tp = new TreePrinter(this);
		tp.print("After inserting '"+x+"'");
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

	
	//case 1
	private BSTNode singleRightRotation(BSTNode k2)
	{
		//System.out.print("Doing a single right rotation on "+k2.getData()+"\n");
		BSTNode k1 = k2.getLeft();
		k2.setLeft(k1.getRight());
		k1.setRight(k2);
		//System.out.print("Returning: "+k1.print());	
		return k1;
	}
	//case 4
	private BSTNode singleLeftRotation(BSTNode k1)
	{
		//System.out.print("Doing a single left rotation on "+k1.getData()+"\n");
		BSTNode k2 = k1.getRight();
		k1.setRight(
				k2.getLeft());
		k2.setLeft(k1);
		return k2;
	}
	//case 2
	private BSTNode doubleLeftRightRotation(BSTNode k3)
	{
		//System.out.print("Doing a double left right rotation\n");
		k3.setLeft(singleLeftRotation(k3.getLeft()));
		return singleRightRotation(k3); 
	}
	//case 3
	private BSTNode doubleRightLeftRotation(BSTNode k1)
	{
		//System.out.print("Doing a double right left rotation\n");
		k1.setRight(singleRightRotation(k1.getRight()));
		return singleLeftRotation(k1);
	}
}
