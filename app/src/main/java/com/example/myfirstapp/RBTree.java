package com.example.myfirstapp;

import com.example.myfirstapp.userCollection.User;

import java.util.ArrayList;

/**
 * @author Enze Peng
 * This class is about RBTree implementation.
 */
public class RBTree<T extends Comparable<T>> {
    private final static String BLACK = "BLACK";
    private final static String RED = "RED";
    private RBNode<T> root = null;

    /**
     * find the node that needs to inserted
     *
     * @param key
     */
    public void insert(T key) {
        RBNode<T> parent = null;
        // The new nodes are red
        RBNode<T> node = new RBNode<T>(RED, key);
        RBNode<T> root = this.root;
        while (root != null) {
            parent = root;
            int cmp = root.key.compareTo(key);
            if (cmp > 0) {
                root = root.leftChild;
            } else if (cmp < 0) {
                root = root.rightChild;
            } else {
                // if key value is same, update key value
                root.key = key;

            }
        }
        node.parent = parent;
        if (parent != null) {
            if (key.compareTo(parent.key) > 0) {
                parent.rightChild = node;
            } else {
                parent.leftChild = node;
            }
        } else {
            // if parent is null, update root node and update color
            this.root = node;
            node.setColor(BLACK);
        }
        // Check if it satisfies a red-black tree
        insertFix(node);
    }

    /**
     * Right rotation
     *
     * @param rbNode
     * @return
     */
    public void leftRotation(RBNode<T> rbNode) {
        // First get the right child and place the left child of the right child to the right of the rotation node
        RBNode<T> rChild = rbNode.rightChild;
        rbNode.rightChild = rChild.leftChild;

        if (rChild.leftChild != null) {
            rChild.leftChild.parent = rbNode;
        }
        // Update nodes
        setChildAndParent(rbNode, rChild);
        rbNode.parent = rChild;
        rChild.leftChild = rbNode;
    }

    /**
     * While parent is not null, update parent node and child node and update color of these
     *
     * @param rbNode
     * @param rp
     */
    public void setChildAndParent(RBNode<T> rbNode, RBNode<T> rp) {
        if (rbNode.parent != null) {
            rp.parent = rbNode.parent;
            if (rbNode == rbNode.parent.leftChild) {
                rbNode.parent.leftChild = rp;
            } else {
                rbNode.parent.rightChild = rp;
            }
        } else {
            this.root = rp;
            rp.parent = null;
            rp.setColor(BLACK);
        }
    }

    /**
     * Right rotation
     *
     * @param rbNode
     */
    public void rightRotation(RBNode<T> rbNode) {
        RBNode<T> rp = rbNode.leftChild;
        rbNode.leftChild = rp.rightChild;
        if (rp.rightChild != null) {
            rp.rightChild.parent = rbNode;
        }
        setChildAndParent(rbNode, rp);
        rbNode.parent = rp;
        rp.rightChild = rbNode;

    }

    /**
     * Update nodes in a red-black tree
     *
     * @param rbNode
     */
    public void insertFix(RBNode<T> rbNode) {
        this.root.setColor(BLACK);
        RBNode<T> parent = rbNode.parent;
        if (parent != null && parent.getColor().equals(RED)) {
            // get the node called grandfather
            RBNode<T> grandfather = rbNode.parent.parent;
            if (parent == grandfather.leftChild) {
                // uncle node is null or uncle node is black
                if (grandfather.rightChild == null || grandfather.rightChild.getColor().equals(BLACK)) {
                    if (rbNode == parent.leftChild) {
                        grandfather.setColor(RED);
                        parent.setColor(BLACK);
                        rightRotation(grandfather);
                    } else {
                        // take a left rotation
                        leftRotation(parent);
                        // Do recursive operations
                        insertFix(parent);
                    }
                } else {
                    //  uncle node is not null and uncle node is black
                    grandfather.setColor(RED);
                    grandfather.rightChild.setColor(BLACK);
                    parent.setColor(BLACK);
                    insertFix(grandfather);
                }
            } else {
                if (grandfather.leftChild == null || grandfather.leftChild.getColor().equals(BLACK)) {
                    if (rbNode == parent.rightChild) {
                        grandfather.setColor(RED);
                        parent.setColor(BLACK);
                        leftRotation(grandfather);
                    } else {
                        rightRotation(parent);
                        insertFix(parent);
                    }
                } else {
                    grandfather.setColor(RED);
                    grandfather.leftChild.setColor(BLACK);
                    parent.setColor(BLACK);
                    insertFix(grandfather);
                }
            }
        }
    }


    /**
     * The deletion for a node
     *
     * @param root root node
     * @param key  input node
     */
    public void delete(RBNode<T> root, T key) {
        RBNode<T> root1 = root;
        while (root1 != null) {
            int cmp = key.compareTo(root1.key);
            if (cmp > 0) {
                root1 = root1.rightChild;
            } else if (cmp < 0) {
                root1 = root1.leftChild;
            } else {
                if (root1.leftChild == null && root1.rightChild == null) {
                    // The leaf node is red
                    if (root1.getColor().equals(RED)) {
                        if (root1.parent != null) {
                            if (root1.parent.leftChild == root1) {
                                root1.parent.leftChild = null;
                            } else {
                                root1.parent.rightChild = null;
                            }
                        } else {
                            this.root = null;
                        }
                    } else {
                        deleteFix(root1, root1.key);
                    }
                } else if (root1.leftChild != null && root1.rightChild != null) {
                    RBNode<T> replaceNode = this.findMin(root1.rightChild);
                    root1.key = replaceNode.key;
                    delete(root1.rightChild, root1.key);
                } else {
                    if (root1.leftChild != null) {
                        root1.key = root1.leftChild.key;
                        delete(root1.leftChild, root1.key);
                    } else {
                        root1.key = root1.rightChild.key;
                        delete(root1.rightChild, root1.key);
                    }
                }
                return;
            }
            if (root1 == null) {
                System.out.println("没有该节点,删除失败");
                break;
            }
        }
    }

    /**
     * Restore balance
     * @param rbNode
     * @param Key
     */
    public void deleteFix(RBNode<T> rbNode, T Key) {
        if (rbNode.parent == null) {
            rbNode.setColor(BLACK);
            return;
        }
        String s = null;
        if (rbNode == rbNode.parent.leftChild) {
            s = "0";
        } else {
            s = "1";
        }
        if (s.equals("0")) {
            // Node that need to delete is on the left
            RBNode<T> brother = rbNode.parent.rightChild;
            // Brother node is red
            if (brother.getColor().equals(RED)) {
                rbNode.parent.setColor(RED);
                brother.setColor(BLACK);
                leftRotation(rbNode.parent);
                deleteFix(rbNode, Key);
            } else {
                // Brother node is black
                if ((brother.leftChild == null && brother.rightChild == null) || (brother.leftChild != null && brother.rightChild != null
                        && brother.leftChild.getColor().equals(BLACK
                ) && brother.rightChild.getColor().equals(BLACK))) {
                    brother.setColor(RED);
                    // The children of brother node is null or they are black
                    // Parent is red
                    if (brother.parent.getColor().equals(RED)) {
                        brother.parent.setColor(BLACK);
                        brother.setColor(RED);
                        if (rbNode.key == Key) {
                            rbNode.parent.leftChild = null;
                        }

                    } else {
                        // The children of brother node is null or they are black
                        // Parent is black
                        //兄弟的两个孩子为空或者都是黑色，父亲为黑色
                        if (rbNode.key == Key) {
                            rbNode.parent.leftChild = null;
                        }
                        brother.setColor(RED);
                        deleteFix(brother.parent, Key);
                    }
                } else if (brother.rightChild != null && brother.rightChild.getColor().equals(RED)) {
                    String color = brother.parent.getColor();
                    brother.parent.setColor(BLACK);
                    brother.setColor(color);
                    leftRotation(brother.parent);
                    brother.rightChild.setColor(BLACK);
                    if (rbNode.key == Key) {
                        rbNode.parent.leftChild = null;
                    }
                } else if (brother.leftChild != null && brother.leftChild.getColor().equals(RED)) {
                    String color = brother.leftChild.getColor();
                    brother.leftChild.setColor(brother.getColor());
                    brother.setColor(color);
                    rightRotation(brother);
                    deleteFix(rbNode, Key);
                }
            }
        } else {
            // The node that need to delete is on the right
            RBNode<T> brother = rbNode.parent.leftChild;
            // The brother node is red
            if (brother.getColor().equals(RED)) {
                rbNode.parent.setColor(RED);
                brother.setColor(BLACK);
                rightRotation(rbNode.parent);
                deleteFix(rbNode, Key);
            } else {
                // The brother node is black
                if ((brother.leftChild == null && brother.rightChild == null) || (brother.leftChild != null && brother.rightChild != null
                        && brother.leftChild.getColor().equals(BLACK
                ) && brother.rightChild.getColor().equals(BLACK))) {
                    // The brother node is black and parent is red
                    if (rbNode.parent.getColor().equals(RED)) {
                        rbNode.parent.setColor(BLACK);
                        brother.setColor(RED);
                        if (rbNode.key == Key) {
                            rbNode.parent.rightChild = null;
                        }
                    } else {
                        if (rbNode.key == Key) {
                            rbNode.parent.rightChild = null;
                        }
                        brother.setColor(RED);
                        deleteFix(brother.parent, Key);
                    }
                } else if (brother.leftChild != null && brother.leftChild.getColor().equals(RED)) {
                    String color = brother.parent.getColor();
                    brother.parent.setColor(BLACK);
                    brother.setColor(color);
                    rightRotation(brother.parent);
                    brother.leftChild.setColor(BLACK);
                    rbNode.parent.rightChild = null;
                } else if (brother.rightChild != null && brother.rightChild.getColor().equals(RED)) {
                    String color = brother.rightChild.getColor();
                    brother.rightChild.setColor(brother.getColor());
                    brother.setColor(color);
                    leftRotation(brother);
                    deleteFix(rbNode, Key);
                }
            }
        }
    }


    /**
     * Find the smallest node
     *
     * @param root
     * @return
     */
    public RBNode<T> findMin(RBNode<T> root) {
        while (root.leftChild != null) {
            root = root.leftChild;
        }
        return root;
    }

    /**
     * In-order traversal
     * @param root
     */
    public void Middle(RBNode<T> root) {
        if (root != null) {
            Middle(root.leftChild);
            System.out.println(root.key + root.color);
            Middle(root.rightChild);
        }
    }

    ArrayList<T> x = new ArrayList<>();

    public ArrayList traverse(RBNode<T> root) {
        if (root != null) {
            traverse(root.leftChild);
            System.out.println(root.key + root.color);
            x.add(root.key);
            traverse(root.rightChild);
        }
        return x;
    }

    /**
     * Get root node
     */
    public RBNode<T> getRoot() {
        return this.root;
    }

    /**
     * Initialize empty RBTree
     */
    public RBTree() {
        this.root = null;
    }

    /**
     * @author Enze Peng
     * This class is to find a node for a certain node.
     * @return node
     */
    private RBNode<T> find(RBNode<T> x, T v) {
        if (x == null) {
            return null;
        }

        int cmp = v.compareTo(x.key);
        if (cmp < 0)
            return find(x.leftChild, v);
        else if (cmp > 0)
            return find(x.rightChild, v);
        else
            return x;
    }

    /**
     * @author Enze Peng
     * This class is to find a node.
     * @return node
     */
    public RBNode<T> find(T v) {
        RBNode<T> x = root;
        if (x == null) {
            return null;
        }

        int cmp = v.compareTo(x.key);
        if (cmp < 0)
            return find(x.leftChild, v);
        else if (cmp > 0)
            return find(x.rightChild, v);
        else
            return x;
    }

    /**
     * @author Enze Peng
     * This class is defining a red-black tree.
     */
    public class RBNode<T extends Comparable<T>> {
        String color;
        T key;
        RBNode<T> leftChild;
        RBNode<T> rightChild;
        RBNode<T> parent;

        public RBNode(String color, T key) {
            this(color, key, null, null, null);
        }

        // define a constructor
        public RBNode(String color, T key, RBNode<T> leftChild, RBNode<T> rightChild, RBNode<T> parent) {
            this.color = color;
            this.key = key;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.parent = parent;
        }

        // Set color method
        public void setColor(String color) {
            this.color = color;
        }

        // Get color method
        public String getColor() {
            return color;
        }
    }


}
