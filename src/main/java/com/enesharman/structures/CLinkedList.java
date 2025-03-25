package com.enesharman.structures;


public class CLinkedList {
    public static void main(String[] args) {
        var head = generateList();
        iterate(head);
        search(head, 14);
        reverseIteratively(head);
        reverseRecursive(head);
        detectCycle(head);
        findMiddle(head);
        removeNthNodeFromEnd(head, 3);
    }

    public static void removeNthNodeFromEnd(Node node, int nth) {
        /* The idea is to create two different pointers and placing the one to n steps ahead of the other.
        When the forward one reaches the end, second one shows the element that we want to remove. */
        var dummy = new Node(0); // Dummy node to handle edge cases
        dummy.next = node;

        var slow = dummy;
        var fast = dummy;

        while (nth > 0) {
            fast = fast.next;
            nth--;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        iterate(dummy.next);
    }

    public static void findMiddle(Node node) {
        /* The purpose is to put two pointers at the beginning of the list and move one at half the speed of the other.
        When the fast one reaches the end, the slow one points in the middle. */

        var slow = node;
        var fast = node;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        System.out.println("The middle of the list is "+ slow.value);
    }

    public static void detectCycle(Node node) {
        /* The algorithm called Floyd's Tortoise and Hare is used here. Basically, we have two pointers; one moves 1 node while others 2.
         If they become equal at some point, that means there is a cycle. */
        if (node == null || node.next == null) {
            System.out.println("No cycle's been detected!");
            return;
        }

        var hare = node;
        var tortoise = node;

        while (hare != null && hare.next != null) {
            hare = hare.next.next;
            tortoise = tortoise.next;
            if (hare == tortoise) {
                System.out.println("Cycle's been detected!");
                return;
            }
        }
        System.out.println("No cycle's been detected!");
    }

    public static Node reverseRecursive(Node head) {
        /* To reverse a Linked List with a recursive method, we set a base case to find the last node of the list.
         This node is returned as newHead. Other parts are similar to a normal recursive function. */
        if (head == null || head.next == null) {
            return head;
        }

        var newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void reverseIteratively(Node head) {
        /* In iterative approach, two additional pointers are used to handle connection. This one have better space complexity than recursive algorithm. */
        Node prev = null;
        Node next = head;

        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        iterate(prev);
    }

    public static void iterate(Node head) {
        /* Simple iteration on a linked list. */
        while (head != null) {
            System.out.print(" " + head.value);
            head = head.next;
        }
        System.out.println("\n");
    }

    public static void search(Node node, int value) {
        /* Iterate and stop when the node with desired value is found. */
        while (node != null) {
            if (node.value == value) {
                System.out.println("Value is in the list!");
                return;
            }
            node = node.next;
        }
        System.out.println("Value is NOT in the list!");
    }

    public static Node generateList() {
        var node1 = new Node(51);
        var node2 = new Node(42);
        var node3 = new Node(14);
        var node4 = new Node(87);
        var node5 = new Node(48);
        var node6 = new Node(25);
        var node7 = new Node(79);
        var node8 = new Node(15);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        return node1;
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

}
