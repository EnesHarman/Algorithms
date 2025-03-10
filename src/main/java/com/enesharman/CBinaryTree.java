package com.enesharman;

import java.util.ArrayDeque;
import java.util.Queue;

public class CBinaryTree {
    public static void main(String[] args) {
        Tree root = generateDummy();
        insertion(root, 98);
        search(root, 98);
        delete(root, 7);
        countNodes(root);
        findDeepestNode(root);
    }

    public static Tree lowestCommonAncestor(Tree root, Tree node1, Tree node2) {
        /* The idea is iteration over branches of the root until find desired nodes. When both leftLCA and rightLCA is not null, that means parent is found.*/
        if (root == null) {
            return null;
        }
        if (root == node1 || root == node2) {
            return root;
        }
        Tree leftLCA = lowestCommonAncestor(root.left, node1, node2);
        Tree rightLCA = lowestCommonAncestor(root.right, node1, node2);

        if(leftLCA != null && rightLCA != null) { //It's only called when the parent is found.
            return root;
        }

        return leftLCA != null ? leftLCA : rightLCA;
    }

    public static Tree findDeepestNode(Tree tree) {
        /* We run the iteration code here. The last node taken from the queue is the deepest node.*/
        Queue<Tree> queue = new ArrayDeque<>();
        queue.add(tree);

        Tree result = null;
        while (!queue.isEmpty()) {
            result = queue.poll();
            if (result.left != null) {
                queue.add(result.left);
            }
            if (result.right != null) {
                queue.add(result.right);
            }
        }
        return result;
    }

    public static void delete(Tree tree, int key) {
        /*Delete is similar with BST, we iterate and find the desired value. Then we find the deepest node and transfer it's value to desired node.
        At last, deepest node is deleted from the tree.*/
        if (tree == null) {
            return;
        }

        Queue<Tree> queue = new ArrayDeque<>();
        queue.add(tree);

        Tree nodeToDelete = null;
        Tree lastNode = null;
        Tree lastNodeParent = null;


        while (!queue.isEmpty()) {
            lastNode = queue.poll();

            if (lastNode.val == key) {
                nodeToDelete = lastNode;
            }

            if (lastNode.left != null) {
                lastNodeParent = lastNode;
                queue.add(lastNode.left);
            }

            if (lastNode.right != null) {
                lastNodeParent = lastNode;
                queue.add(lastNode.right);
            }
        }

        if (nodeToDelete != null) {
            nodeToDelete.val = lastNode.val;
            if (lastNodeParent.left == lastNode) {
                lastNodeParent.left = null;
            } else {
                lastNodeParent.right = null;
            }
        }
    }

    public static void insertion(Tree tree, int key) {
        /* Insertion is done to the deepest place. We iterate the nodes and when we find a node with absent child, we add new node there.*/
        if (tree == null) {
            return;
        }
        Queue<Tree> queue = new ArrayDeque<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            Tree node = queue.poll();

            if (node.left == null) {
                Tree newNode = new Tree(key);
                node.left = newNode;
                return;
            } else {
                queue.add(node.left);
            }

            if (node.right == null) {
                Tree newNode = new Tree(key);
                node.right = newNode;
                return;
            } else {
                queue.add(node.right);
            }
        }
    }

    public static Tree search(Tree tree, int key) {
        /* Simple iteration algorithm with value check.*/
        if (tree == null) {
            return tree;
        }
        Queue<Tree> queue = new ArrayDeque<>();
        queue.add(tree);

        while (!queue.isEmpty()) {
            Tree node = queue.poll();
            if (node.val == key) {
                return node;
            }

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return null;
    }

    public static int countNodes(Tree tree) {
        /*Count of nodes equals to sum of left sub tree and right sub tree + 1*/
        if (tree == null) {
            return 0;
        }
        return countNodes(tree.left) + countNodes(tree.right) + 1;
    }


    public static Tree generateDummy() {
        Tree root = new Tree(10);
        root.left = new Tree(25);
        root.right = new Tree(7);
        root.left.left = new Tree(3);
        root.left.right = new Tree(18);
        root.right.left = new Tree(30);
        root.right.right = new Tree(12);

        // Adding leaves
        root.left.left.left = new Tree(5);
        root.left.left.right = new Tree(8);
        root.left.right.left = new Tree(20);
        root.left.right.right = new Tree(22);
        root.right.left.left = new Tree(35);
        root.right.left.right = new Tree(40);
        root.right.right.left = new Tree(15);
        root.right.right.right = new Tree(17);
        lowestCommonAncestor(root, root.left.left.right,  root.right.left.right); //8,40
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

