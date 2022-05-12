package com.example.myfirstapp;

public class RBTree<T extends Comparable<T>> {

	public Node<T> root; // The root node of the tree
	public int sum=0;
	public int sum1=0;




	/**
	 * Initialize empty RBTree
	 */
	public RBTree() {
		this.root = null;
	}

	/**
	 * Add a new node into the tree with {@code root} node.
	 * 
	 * @param root Node<T> The root node of the tree where x is being inserted.
	 * @param x    Node<T> New node being inserted.
	 */
	private void insertRecurse(Node<T> root, Node<T> x) {
		if (root.value.compareTo(x.value) > 0) {
			if (root.left.value == null) {
				root.left = x;
				x.parent = root;
			} else {
				insertRecurse(root.left, x);
			}
		} else if (root.value.compareTo(x.value) < 0) {
			if (root.right.value == null) {
				root.right = x;
				x.parent = root;
			} else {
				insertRecurse(root.right, x);
			}
		}
		// Do nothing if the tree already has a node with the same value.
	}

	/**
	 * Insert node into RBTree. Note that for checking properties, we do not need to
	 * have a complete RB tree. Here the insertion is the same as the binary tree.
	 * 
	 * @param x Node<T> The new node being inserted into the tree.
	 */
	private void insert(Node<T> x) {
		if (this.root == null) {
			this.root = x;
		} else {
			insertRecurse(this.root, x);
		}
	}

	/**
	 * Demo functions (Safely) insert a value into the tree
	 * 
	 * @param value T The value of the new node being inserted.
	 */
	public void insert(T value) {
		Node<T> node = new Node<T>(value);
		if (node != null) {
			insert(node);
		}
	}

	/**
	 * Return the corresponding node of a value, if it exists in the tree
	 * 
	 * @param x Node<T> The root node of the tree we search for the value {@code v}
	 * @param v Node<T> The node that we are looking for
	 * @return
	 */
	private Node<T> find(Node<T> x, T v) {
		if (x.value == null) {
			return null;
		}

		int cmp = v.compareTo(x.value);
		if (cmp < 0)
			return find(x.left, v);
		else if (cmp > 0)
			return find(x.right, v);
		else
			return x;
	}

	public Node<T> find(T v) {
		Node<T> x=root;
		if (x.value == null) {
			return null;
		}

		int cmp = v.compareTo(x.value);
		if (cmp < 0)
			return find(x.left, v);
		else if (cmp > 0)
			return find(x.right, v);
		else
			return x;
	}


	/**
	 * Returns a node if the value of the node is {@code key}.
	 * 
	 * @param key T The value we are looking for
	 * @return
	 */
	public Node<T> search(T key) {
		return find(this.root, key);
	}
	
	public class Node<E extends Comparable<E>> {
		
		Colour colour; // Node colour
		E value; // Node value
		Node<E> parent; // Parent node
		Node<E> left, right; // Children nodes

		public Node(E value) {
			this.value = value;
			this.colour = Colour.BLACK;
			this.parent = null;

			// Initialise children leaf nodes
			this.left = new Node<E>();
			this.right = new Node<E>();
			this.left.parent = this;
			this.right.parent = this;
		}

		// Leaf node
		public Node() {
			this.value = null;
			this.colour = Colour.BLACK;
		}

		public void setColour(boolean red) {
			this.colour = red ? Colour.RED : Colour.BLACK;
		}
	}
	
	public enum Colour {
		RED, BLACK;
	}

	/**
	 * Please implement this method and feel free to add additional helper methods
	 * @return
	 */
	public boolean testProp1() {
		// START YOUR CODE
		if(root.colour== Colour.RED)
			return false;
		traverse(root);

		if(sum!=0)
			return false;


		return true; //you are allowed to change this return statement
		// END YOUR CODE
	}

	public Boolean traverse(Node t){
		if(t!=null){
			traverse(t.left);
			if(t.left==null && t.right==null && t.colour== Colour.RED)
				sum++;
			if(t.parent!=null)
			{
				if(t.colour== Colour.RED&& t.parent.colour== Colour.RED)
					sum1++;
			}

			traverse(t.right);
		}
		return null;
	}

	public Node traverse1(){
		Node t=root;
		if(t!=null){
			traverse(t.left);
			System.out.println(t);

			traverse(t.right);
		}
		return null;
	}

	/**
	 * Please implement this method and feel free to add additional helper methods
	 * @return
	 */
	public boolean testProp2() {
		// START YOUR CODE
		traverse(root);

		if(sum1!=0)
			return false;


		return true;

		// END YOUR CODE
	}
}