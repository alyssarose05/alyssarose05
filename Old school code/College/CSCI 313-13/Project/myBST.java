/**
 * This short class creates a binary tree node (btnode) that is used with the myBST (my binary search tree) class below, the main class for this project.
 */
class btnode {
	protected Comparable data; // Attributes: The Customer root and its left and right children.
	protected btnode left;
	protected btnode right;
	
	/**
	 * A constructor that takes a Customer as a root to create a btnode with a null left and right node.
	 * @param data The Customer to be the root of the btnode.
	 */
	public btnode(Comparable data) {
		this.data = data; //     data
		this.left = null; //    /    \
		this.right = null; // null   null
	}
}

/**
 * Project 1, Part 1: This class creates a binary search tree (BST) using a Comparable Object as the data type. 
 * Includes: A default constructor, isEmpty, isRoot, clear, PreOrder, InOrder, PostOrder, insert, FindMin, FindMax, delete, search, and getParent. 
 * Every method except the default constructor, isEmpty, isRoot, and clear has both a class method (user cannot access) and member method (user can access).
 * @author Alyssa Ayala, CSCI 313-13
 * Due Date: November 22nd, 2021 @ 9:00am.
 */
public class myBST {
	private static btnode root; // Attribute: The root of the BST.
	
	/**
	 * A default constructor that creates an empty BST: a null root.
	 */
	public myBST() {
		root = null;
	}
	
	public static boolean isEmpty() {
		return root == null;
	}
	
	public static boolean isRoot(Comparable x) {
		return x.compareTo(root) == 0;
	}
	
	public static void clear() {
		root = null;
	}
	
	/**
	 * Prints out the values of the BST from root -> left -> right.
	 * @param t The btnode that is currently being focused on from the BST.
	 */
	private static void PreOrder(btnode t) {
		if (t != null) {
			System.out.println(t.data); // root
			PreOrder(t.left); // -> left
			PreOrder(t.right); // -> right
		}
	}
	
	/**
	 * Runs the above PreOrder method by taking the root as a parameter.
	 */
	public void PreOrder() {
		if (isEmpty()) throw new IllegalArgumentException("Empty BST.");
		PreOrder(root);
	}
	
	/**
	 * Prints out the values of the BST from left -> root -> right.
	 * @param t The btnode that is currently being focused on from the BST.
	 */
	private static void InOrder(btnode t) {
		if (t != null) {
			InOrder(t.left); // left
			System.out.println(t.data); // -> root
			InOrder(t.right); // -> right
		}
	}
	
	/**
	 * Runs the above InOrder method by taking the root as a parameter.
	 */
	public void InOrder() {
		if (isEmpty()) throw new IllegalArgumentException("Empty BST.");
		InOrder(root);
	}
	
	/**
	 * Prints out the values of the BST from left -> right -> root.
	 * @param t The btnode that is currently being focused on from the BST.
	 */
	private static void PostOrder(btnode t) {
		if (t != null) {
			PostOrder(t.left); // left
			PostOrder(t.right); // -> right
			System.out.println(t.data); // -> root
		}
	}
	
	/**
	 * Runs the above PostOrder method by taking the root as a parameter.
	 */
	public void PostOrder() {
		if (isEmpty()) throw new IllegalArgumentException("Empty BST.");
		PostOrder(root);
	}
	
	/**
	 * Inserts a Comparable into the BST.
	 * @param t The btnode that is currently being focused on from the BST.
	 * @param x The Comparable to be inserted into the BST.
	 * @return The BST with the Comparable added to it.
	 */
	private static btnode insert(btnode t, Comparable x) {
		if (t == null) t = new btnode(x);
		else { // t != null
			if (t.data.compareTo(x) < 0) t.left = insert(t.left, x);
			else t.right = insert(t.right, x);
		}
		return t;
	}
	
	/**
	 * Updates the BST into the changes made by running the above insert method with the root and the Comparable the user wants to insert into it.
	 * @param x
	 */
	public void insert(Comparable x) {
		root = insert(root, x);
	}
	
	/**
	 * Finds the minimum Comparable of the BST.
	 * @param t The btnode that is currently being focused on from the BST.
	 * @return The minimum Comparable of the BST.
	 */
	private static Comparable FindMin(btnode t) {
		if (t.left == null) return t.data;
		return FindMin(t.left); // Minimum = the left node of the BST.
	}
	
	/**
	 * Runs the above FindMin method by taking the root as a parameter.
	 * @return The minimum Comparable of the BST.
	 */
	public Comparable FindMin() {
		if (isEmpty()) throw new IllegalArgumentException("Empty BST.");
		return FindMin(root);
	}
	
	/**
	 * Finds the maximum Comparable of the BST.
	 * @param t The btnode that is currently being focused on from the BST.
	 * @return The maximum Comparable of the BST.
	 */
	private static Comparable FindMax(btnode t) {
		if (t.right == null) return t.data;
		return FindMax(t.right); // Maximum = the right node of the BST.
	}
	
	/**
	 * Runs the above FindMax method by taking the root as a parameter.
	 * @return The maximum Comparable of the BST.
	 */
	public Comparable FindMax() {
		if (isEmpty()) throw new IllegalArgumentException("Empty BST.");
		return FindMax(root);
	}
	
	/**
	 * Deletes a Comparable from the BST.
	 * @param t The btnode that is currently being focused on from the BST.
	 * @param x The Comparable to be deleted from the BST.
	 * @return The BST with the Comparable no longer there.
	 */
	private static btnode delete(btnode t, Comparable x) {
		if (t == null) throw new IllegalArgumentException("Not found.");
		if (t.data.compareTo(x) < 0) t.left = delete(t.left, x);
		else if (t.data.compareTo(x) > 0) t.right = delete(t.right, x);
		else { // The Comparable Object has been found.
			// 1 child or less
			if (t.left == null) return t.right;
			if (t.right == null) return t.left;
			// 2 children
			Comparable newRoot = FindMin(t.right);
			t.data = newRoot;
			t.right = delete(t.right, newRoot);
		}
		return t;
	}
	
	/**
	 * Updates the BST into the changes made by the above delete method when run with the root and the Comparable the user wants to delete from the BST.
	 * @param x The Comparable the user wants to delete from the BST.
	 */
	public void delete(Comparable x) {
		if (isEmpty()) throw new IllegalArgumentException("Empty BST.");
		root = delete(root, x);
	}
	
	/**
	 * Searches for a Comparable in the BST.
	 * @param t The btnode that is currently being focused on from the BST.
	 * @param x The Comparable to be searched for in the BST.
	 * @return true if the Comparable is in the BST, and false otherwise.
	 */
	private static Comparable search(btnode t, Comparable x) {
		if (t == null) throw new IllegalArgumentException("Not found.");
		if (x.compareTo(t.data) == 0) return t.data;
		else if (t.data.compareTo(x) < 0) return search(t.left, x);
		return search(t.right, x);
	}
	
	/**
	 * Returns the output of the above search method when taking the root and the Comparable the user wants to search for in the BST as parameters.
	 * @param x The Comparable the user wants to search for in the BST.
	 * @return true if the Comparable is in the BST, and false otherwise.
	 */
	public Comparable search(Comparable x) {
		if (isEmpty()) throw new IllegalArgumentException("Empty BST.");
		return search(root, x);
	}
	
	/**
	 * Gets the parent of a btnode.
	 * @param t The btnode that is currently being focused on from the BST.
	 * @param x The Comparable to get the parent of in the BST.
	 * @return The parent of the Comparable.
	 */
	private static Comparable getParent(btnode t, Comparable x) {
		if (t == null) throw new IllegalArgumentException("Not found.");
		if (t.left != null && t.data.compareTo(x) < 0 || t.right != null && t.data.compareTo(x) > 0) return t.data;
		if (t.data.compareTo(x) > 0) return getParent(t.left, x);
		return getParent(t.right, x);
	}
	
	/**
	 * Returns the output of the above getParent method when taking the root and the Comparable the user wants to get the parent of in the BST as parameters.
	 * @param x The Comparable the user wants to get the parent of in the BST.
	 * @return The parent of the Comparable. 
	 */
	public Comparable getParent(Comparable x) {
		if (isEmpty() || isRoot(x)) throw new IllegalArgumentException("Not found / No parent.");
		return getParent(root, x);
	}
}