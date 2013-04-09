/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lisouski.dzianis;

/**
 *
 * @author DenisLesovsky
 */
public class SplayTree<K extends Comparable<K>, V> {
    
    private Node<K, V> root;
    
    private static final class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    // Public methods
    
    public V insert(K key, V value) {
        Node newNode = new Node(key, value);
        
        if (root == null) {
            root = newNode;
            return null;
        }
        
        Node curNode = root;
        V returnValue = null;
        
        for(;;) {
            int compareResult = curNode.key.compareTo(key);
            if (compareResult == 0) {
                if (curNode.parent != null) {
                    if (curNode.parent.left == curNode) {
                        curNode.parent.left = newNode;
                    } else {
                        curNode.parent.right = newNode;
                    }
                }
                newNode.left = curNode.left;
                newNode.right = curNode.right;
                returnValue = (V) curNode.value;
                break;
            } else if (compareResult > 0) {
                if (curNode.left == null) {
                    curNode.left = newNode;
                    newNode.parent = curNode;
                    break;
                }
                curNode = curNode.left;
            } else {
                if (curNode.right == null) {
                    curNode.right = newNode;
                    newNode.parent = curNode;
                    break;
                }
                curNode = curNode.parent;
            }
        }
        
        splay(newNode);
        
        return returnValue;
    }
    
    public V find (K key) {
        Node curNode = root;
        V returnValue = null;
        
        while (curNode != null) {
            int compareResult = curNode.key.compareTo(key);
            if (compareResult == 0) {
                returnValue = (V) curNode.value;
            } else if (compareResult > 0) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }
        
        return returnValue;
    }
    
    // Rotations
    private void rightRotation(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x.parent.left == x) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.right = x;
        x.parent = y;
    }
    
    private void leftRotation(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x.parent.left == x) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }
    
    // Splay operation
    private void splay(Node x) {
        while (x.parent != null) {
            if (x.parent.parent == null) { // Zig
                if (x.parent.left == x) {
                    rightRotation(x);
                } else {
                    leftRotation(x);
                }
            } else if (x.parent.left == x && x.parent.parent.left == x.parent) { // ZigZig 1
                rightRotation(x.parent.parent);
                rightRotation(x.parent);
            } else if (x.parent.right == x && x.parent.parent.right == x.parent) { // ZigZig 2
                leftRotation(x.parent.parent);
                leftRotation(x.right);
            } else if (x.parent.left == x) {
                rightRotation(x.parent);
                leftRotation(x.parent);
            } else {
                leftRotation(x.parent);
                rightRotation(x.parent);
            }
        }
    }
    
}
