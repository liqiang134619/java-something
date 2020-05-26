package com.luni724.datac.tree;

import javax.jws.WebParam;

/**
 * @author liq
 * @date 2020/5/26
 */
public class TreeTest {

    public static void main(String[] args) {

        BinarySearchTree<Integer> node = new BinarySearchTree<>();

        node.insert(6);
        node.insert(2);
        node.insert(8);
        node.insert(1);
        node.insert(4);
        node.insert(3);

        node.remove(4);
        System.out.println("xx");
    }
}
