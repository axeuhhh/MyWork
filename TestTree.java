/*********************************************************
 * filename: TestTree.java
 *
 * A file that builds a binary tree, fills it with
 * integers read from a file, does some operations
 * on the tree, and printis the results.
 *********************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/** class Tree
 *
 * A binary tree data structure that stores TreeNodes.
 * Can:
 *	add new TreeNodes to the tree,
 *	sum all the values in the tree,
 *	find minimum value in the tree,
 *	find total number of entries in the tree,
 *	find maximum depth of the tree.
 */
class Tree {
	/** class TreeNode - a private member class of Tree
	 *
	 * A node in class Tree.
	 * Stores a value, and references to zero, one, or two children.
	 */
	private class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		TreeNode(int v) {
			value = v;
			left = null;
			right = null;
		}
		int value() { return value; }
	}

	//////
	////// Class members of Tree
	//////
	TreeNode root;

	//////
	////// Class methods
	//////
	public Tree() {
		root = null;
	}

	/** Add an int value to the tree
	 * @param input the int value to be added to the tree
	 */
	public void add(int input) {
		TreeNode n = new TreeNode(input);

		if (root == null) {
			root = n;
			return;
		}
		add(root, input);
	}
//this function 
	private void add(TreeNode top, int v) {
		// Put the new node into the tree, in "sorted order".
		// Nodes are added "to the left" of nodes with greater values
		// and "to the right" of nodes with smaller values.
		//
		// 'top' should not be null
		
		if (v > top.value()) {
			if (top.right == null) {
				top.right = new TreeNode(v);
			} else {
				add(top.right, v);
			}
		} else {//this functions sets all the number that are less than or equal top the left of the top node.
			// STUDENTS FILL IN CODE HERE
			if(top.left == null) {
				top.left = new TreeNode(v);
			}else {
				add(top.left,v);
			}
				
		}
	}

	
//	public int sum() {
//		return sum(root)
//	}
//	public int sum(TreeNode n) {
//		if n == null return 0
//		return n.value + sum(n.left)+ sum(n.right) 
//	}
	
	public int sum() {
		
		return root.value+sum(root);
	}
	private static int sum(TreeNode n) {// this fucntion computes the sum of all the nodes in the tree using a recursivly defined function 
		// STUDENT FILL IN CODE HERE
		if (n.left == null && n.right == null) {
			return 0;
		}else if (n.left == null&& n.right !=null) {
			return n.right.value()+sum(n.right);
		}else if(n.left != null&& n.right ==null) {
			return n.left.value()+ sum(n.left);
		}
		return (n.left.value()+n.right.value()+sum(n.left)+sum(n.right));
	}
	
	public int size() {
		return 1+size(root);
	}
	private static int size(TreeNode n) {// this function computes the size, which is equivalent to the number of leaves/root ort elements in the trees using a recursively defined function.
		// STUDENT FILL IN CODE HERE
		if (n.left == null && n.right == null) {
			return 0;
		}else if (n.left == null&& n.right !=null) {
			return 1+size(n.right);
		}else if(n.left != null&& n.right ==null) {
			return 1+ size(n.left);
		}
		return (2+size(n.left)+size(n.right));
		
		
	}

	public int depth() {
		return 1+depth(root);
	}
	private static int depth(TreeNode n) {// this function computes the depth if the tree using a recursively defined fucntion.
		// STUDENT FILL IN CODE HERE
		if (n.left == null && n.right == null) {
			return 0;
		}else if (n.left == null&& n.right !=null) {
			return 1+depth(n.right);
		}else if(n.left != null&& n.right ==null) {
			return 1+(depth(n.left));
		}
		return 1+Math.max(depth(n.left),depth(n.right));
	}

	/** @return minimum value stored in the tree.
	 *
	 * If the tree is empty, throw an Exception.
	 */
	public int min() throws Exception {// this is a function call that calls the overlaoded min fucntion with the root as teh first argument.
		if (root == null) { 
			throw new Exception("TreeMin() called with null arg"); 
		}
		// STUDENT FILL IN CODE HERE
		return Math.min(root.value,min(root));
	}
	private int min(TreeNode n) {// this function calculates the minimu values of the tree using a recusrively defined function.
		if (n.left == null && n.right == null) {
			return n.value;
		}else if (n.left == null&& n.right !=null) {
			return Math.min(n.value,min(n.right));
		}else if(n.left != null&& n.right ==null) {
			return Math.min(n.value, min(n.left));
		}
		return Math.min(min(n.left),min(n.right));
	}
}

public class TestTree {
	
	public static void main(String[] args) throws Exception, FileNotFoundException, IOException {
		if (args.length < 1) {
			System.err.println("ERROR: need a filename");
			System.exit(0);
		}
		Tree myTree = new Tree();
		File file = new File(args[0]);
		Scanner scan = new Scanner(file);
		while (scan.hasNextInt()) {
			myTree.add(scan.nextInt());
		}
		scan.close();
		// STUDENTS FILL IN CODE TO READ INTS FROM A FILE WHOSE
		// FILENAME IS IN args[0] AND ADD THEM TO THE TREE.

		System.out.println("Sum is " + myTree.sum());
		System.out.println("Min is " + myTree.min());
		System.out.println("Size is " + myTree.size());
		System.out.println("Depth is " + myTree.depth());
	}
}
