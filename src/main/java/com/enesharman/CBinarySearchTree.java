package com.enesharman;

import java.util.ArrayDeque;
import java.util.Queue;

public class CBinarySearchTree {
    public static void main(String[] args) {
        Tree root = generateDummy();
        insertion(root, 21);
        search(root, 45);
        traverse(root);
        levelOrder(root);
        delete(root, 18);
        isBalanced(root);
    }


    public static Tree findLowestAncestor(Tree root, Tree node1, Tree node2) {
        /*The idea is moving the root pointer until find a node which is greater than one node and less than other*/
        if (root == null) {
            return null;
        }

        if (root.val > node1.val && root.val > node2.val) {
            findLowestAncestor(root.left, node1, node2);
        } else if (root.val < node1.val && root.val < node2.val) {
            findLowestAncestor(root.right, node1, node2);
        }
        return root;
    }


    public static boolean isBalanced(Tree tree) {
        /*This algorithm uses height finder method. The idea is checking heights of root's branches recursively.*/
        if (tree == null) {
            return true;
        }

        int leftH = findHeight(tree.left);
        int rightH = findHeight(tree.right);

        if (Math.abs(leftH - rightH) > 1) {
            return false;
        }

        return isBalanced(tree.left) && isBalanced(tree.right);
    }

    public static int findHeight(Tree tree) {
        /*To find the height of given node, we should iterate all branches of it and keep a count.*/
        if (tree == null) {
            return -1;
        }
        return Math.max(findHeight(tree.left), findHeight(tree.right)) + 1;
    }

    public static Tree delete(Tree tree, int key) {
        /* To delete a value from a tree, first the desired node with the key should be found. After that there are 3 options:
                1. The node does not have any branch (it's a leaf).
                2. The node has one branch.
                3. The node has two branches.

            In the first scenario, nothing is done and parent node's child is set null.
            In the second, parent node's child is set to the only branch of the target node.
            And the last, successor of the target not is found by 'findMin' method. Target node's value is set as successor's value. This operation is done until reach one of the leaf.

        *
        * */
        if (tree == null) {
            return null;
        }

        //find node
        if (tree.val < key) {
            tree.right = delete(tree.right, key);
        } else if (tree.val > key) {
            tree.left = delete(tree.left, key);
        } else {
            if (tree.left == null) {
                return tree.right; //Scenario 1 or 2, depending on the right branch.
            } else if (tree.right == null) {
                return tree.left; //Scenario 2. only left branch exists.
            }
            //Scenario 3. Both branches are exist. Successor transfer operation is done.
            Tree successor = findMin(tree.right);
            tree.val = successor.val;
            tree.right = delete(tree.right, successor.val);
        }
        return tree;
    }

    public static Tree findMin(Tree root) {
        /*Find the leftest node in the tree with a loop.*/
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void levelOrder(Tree tree) {
        /*A queue is used here. Nodes in a level is inserted into the queue and 'printed' after reading */
        if (tree == null) {
            return;
        }
        Queue<Tree> queue = new ArrayDeque<>();
        queue.add(tree);

        while (!queue.isEmpty()) {
            Tree node = queue.poll();
            System.out.print(" " + node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

    }

    public static Tree insertion(Tree tree, int value) {
        /*To insert a value to binary tree. First the correct place should be found. It's done by traversing the tree from the root.*/
        if (tree == null) {
            return new Tree(value);
        }
        if (value < tree.val) {
            tree.left = insertion(tree.left, value);
        } else {
            tree.right = insertion(tree.right, value);
        }
        return tree;
    }

    public static Tree search(Tree tree, int value) {
        /*Searching is very similar to insertion. Data is compared with the node that the pointer shows.*/
        if (tree == null || tree.val == value) {
            return tree;
        }
        if (tree.val > value) {
            return search(tree.left, value);
        } else {
            return search(tree.right, value);
        }
    }

    public static void traverse(Tree root) {
        /*There are different traverse algorithm for different purposes. This one is called Inorder traversing.*/
        if (root == null) {
            return;
        }
        traverse(root.left);
        System.out.print(" " + root.val);
        traverse(root.right);
    }

    public static Tree generateDummy() {
        Tree root = new Tree(10);

        root.left = new Tree(5);
        root.right = new Tree(15);

        root.left.left = new Tree(3);
        root.left.right = new Tree(7);
        root.right.left = new Tree(12);
        root.right.right = new Tree(18);

        root.left.left.left = new Tree(1);
        root.left.left.right = new Tree(4);
        root.left.right.left = new Tree(6);
        root.left.right.right = new Tree(8);
        root.right.left.left = new Tree(11);
        root.right.left.right = new Tree(13);
        root.right.right.left = new Tree(17);
        root.right.right.right = new Tree(20);

        return root;
    }
}


class Tree {
    public int val;
    public Tree left;
    public Tree right;

    public Tree(int val) {
        this.val = val;
    }
}
