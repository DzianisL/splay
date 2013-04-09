/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lisouski.dzianis;

/**
 *
 * @author DenisLesovsky
 */
public class SplayTree<K, V> {
    
    private Node<K, V> root;
    
    private static final class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
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
    
    public void add(K key, V value) {
        
    }
    
}
