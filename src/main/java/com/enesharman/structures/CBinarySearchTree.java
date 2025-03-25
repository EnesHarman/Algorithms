package com.enesharman.structures;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class CBinarySearchTree {
    public static void main(String[] args) {
        BSTree root = generateDummy();
        insertion(root, 21);
        search(root, 45);
        traverse(root);
        levelOrder(root);
        delete(root, 18);
        isBalanced(root);
        traverseIterative(root);
    }


    public static BSTree findLowestAncestor(BSTree root, BSTree node1, BSTree node2) {
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


    public static boolean isBalanced(BSTree BSTree) {
        /*This algorithm uses height finder method. The idea is checking heights of root's branches recursively.*/
        if (BSTree == null) {
            return true;
        }

        int leftH = findHeight(BSTree.left);
        int rightH = findHeight(BSTree.right);

        if (Math.abs(leftH - rightH) > 1) {
            return false;
        }

        return isBalanced(BSTree.left) && isBalanced(BSTree.right);
    }

    public static int findHeight(BSTree BSTree) {
        /*To find the height of given node, we should iterate all branches of it and keep a count.*/
        if (BSTree == null) {
            return -1;
        }
        return Math.max(findHeight(BSTree.left), findHeight(BSTree.right)) + 1;
    }

    public static BSTree delete(BSTree BSTree, int key) {
        /* To delete a value from a tree, first the desired node with the key should be found. After that there are 3 options:
                1. The node does not have any branch (it's a leaf).
                2. The node has one branch.
                3. The node has two branches.

            In the first scenario, nothing is done and parent node's child is set null.
            In the second, parent node's child is set to the only branch of the target node.
            And the last, successor of the target not is found by 'findMin' method. Target node's value is set as successor's value. This operation is done until reach one of the leaf.

        *
        * */
        if (BSTree == null) {
            return null;
        }

        //find node
        if (BSTree.val < key) {
            BSTree.right = delete(BSTree.right, key);
        } else if (BSTree.val > key) {
            BSTree.left = delete(BSTree.left, key);
        } else {
            if (BSTree.left == null) {
                return BSTree.right; //Scenario 1 or 2, depending on the right branch.
            } else if (BSTree.right == null) {
                return BSTree.left; //Scenario 2. only left branch exists.
            }
            //Scenario 3. Both branches are exist. Successor transfer operation is done.
            BSTree successor = findMin(BSTree.right);
            BSTree.val = successor.val;
            BSTree.right = delete(BSTree.right, successor.val);
        }
        return BSTree;
    }

    public static BSTree findMin(BSTree root) {
        /*Find the leftest node in the tree with a loop.*/
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void levelOrder(BSTree BSTree) {
        /*A queue is used here. Nodes in a level is inserted into the queue and 'printed' after reading */
        if (BSTree == null) {
            return;
        }
        Queue<BSTree> queue = new ArrayDeque<>();
        queue.add(BSTree);

        while (!queue.isEmpty()) {
            BSTree node = queue.poll();
            System.out.print(" " + node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

    }

    public static BSTree insertion(BSTree BSTree, int value) {
        /*To insert a value to binary tree. First the correct place should be found. It's done by traversing the tree from the root.*/
        if (BSTree == null) {
            return new BSTree(value);
        }
        if (value < BSTree.val) {
            BSTree.left = insertion(BSTree.left, value);
        } else {
            BSTree.right = insertion(BSTree.right, value);
        }
        return BSTree;
    }

    public static BSTree search(BSTree BSTree, int value) {
        /*Searching is very similar to insertion. Data is compared with the node that the pointer shows.*/
        if (BSTree == null || BSTree.val == value) {
            return BSTree;
        }
        if (BSTree.val > value) {
            return search(BSTree.left, value);
        } else {
            return search(BSTree.right, value);
        }
    }

    public static void traverse(BSTree root) {
        /*There are different traverse algorithm for different purposes. This one is called Inorder traversing.*/
        if (root == null) {
            return;
        }
        traverse(root.left);
        System.out.print(" " + root.val);
        traverse(root.right);
    }

    public static void traverseIterative(BSTree root) {
        Stack<BSTree> stack = new Stack<>();
        BSTree current = root;

        while (current!=null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.println(current.val);
            current = current.right;
        }
    }

    public static BSTree generateDummy() {
        BSTree root = new BSTree(10);

        root.left = new BSTree(5);
        root.right = new BSTree(15);

        root.left.left = new BSTree(3);
        root.left.right = new BSTree(7);
        root.right.left = new BSTree(12);
        root.right.right = new BSTree(18);

        root.left.left.left = new BSTree(1);
        root.left.left.right = new BSTree(4);
        root.left.right.left = new BSTree(6);
        root.left.right.right = new BSTree(8);
        root.right.left.left = new BSTree(11);
        root.right.left.right = new BSTree(13);
        root.right.right.left = new BSTree(17);
        root.right.right.right = new BSTree(20);

        return root;
    }
}


class BSTree {
    public int val;
    public BSTree left;
    public BSTree right;

    public BSTree(int val) {
        this.val = val;
    }
}
