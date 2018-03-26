
/**
 * Angela English
 * CSC 130 MW 4-5:15
 * Working alone
 */

//import java.awt.print.Printable;

public class AVL<T extends Comparable<? super T>> extends BinarySearchTree<T>{
	
	public void incCount(T x) {
		super.incCount(x);
		rebalance(overallRoot);
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
	
	public BSTNode deleteRoot(){
		root=remove(root.value, root);
		return root;
	}
	
	private BSTNode remove( T x, BSTNode leaf )
    {
        if( leaf == null )
            return leaf;
            
        int compareResult = x.compareTo( leaf.value );
            
        if( compareResult < 0 )
            leaf.setLeft(remove( x, leaf.left ));
        else if( compareResult > 0 )
            leaf.setRight(remove( x, leaf.right ));
        else if( leaf.left != null && leaf.right != null ) // Two children
        {
            leaf.value = super.min( leaf.right ).value;
            leaf.setRight(remove( leaf.value, leaf.right ));
        }
        else if(leaf.left!=null)
        	leaf=leaf.left;
        else leaf=leaf.right;
        return rebalance(leaf);
    }
	
	
	
	
	//case 1
	private BSTNode singleRightRotation(BSTNode k2)
	{
		//System.out.print("Doing a single right rotation on "+k2.print()+"\n");
		BSTNode k1 = k2.left;
		//try {
			k2.left=k1.right;
			k1.right=k2;
		/*}catch(NullPointerException e)	//k2.left is null
		{
			System.out.print("Aborting\n");
			return k2;
		}
		*/
		//k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight()))+1);
		//k1.setHeight(Math.max(height(k1.getLeft()), k2.getHeight()) + 1);
		//System.out.print("Returning: "+k1.print());	
		return k1;
	}
	//case 4
	private BSTNode singleLeftRotation(BSTNode k1)
	{
		//System.out.print("Doing a single left rotation on "+k1.print()+"\n");
		BSTNode k2 = k1.right;
		//try {
			k1.right=k2.left;
			k2.left=k1;
		/*}catch(NullPointerException e)	//k1.right is null
		{
			System.out.print("Aborting\n");
			return k1;
		}
		System.out.print("Returning: "+k2.print());*/
		return k2;
	}
	//case 2
	private BSTNode doubleLeftRightRotation(BSTNode k3)
	{
		//System.out.print("Doing a double left right rotation\n");
		k3.left=singleLeftRotation(k3.left);
		return singleRightRotation(k3); 
	}
	//case 3
	private BSTNode doubleRightLeftRotation(BSTNode k1)
	{
		//System.out.print("Doing a double right left rotation\n");
		k1.right=singleRightRotation(k1.right);
		return singleLeftRotation(k1);
	}
}
