package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

class Node {
    String courseName;
    Node left;
    Node right;

    public Node(String courseName) {
        this.courseName = courseName;
        this.left = null;
        this.right = null;
    }
}

public class BST {
    Node root;

    public BST() {
        this.root = null;
    }

    public void insert(String courseName) {
        Node newNode = new Node(courseName);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent;

        while (true) {
            parent = current;

            // To compare the first Char of the course name
            if (courseName.charAt(0) < current.courseName.charAt(0)) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else if (courseName.charAt(0) > current.courseName.charAt(0)) {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            } else {
                // If the first Char of the course name are same, then compare the number
                int currentNumber = Integer.parseInt(current.courseName.substring(current.courseName.lastIndexOf(" ") + 1));
                int newNumber = Integer.parseInt(courseName.substring(courseName.lastIndexOf(" ") + 1));

                if (newNumber < currentNumber) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }
}
