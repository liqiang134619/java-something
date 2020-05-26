package com.luni724.datac.tree;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liq
 * @date 2020/5/26
 */
@Data
public class TreeNode<T> {

    public TreeNode(T element) {
        this(element,null,null);
    }

    public TreeNode(T element, TreeNode<T> left, TreeNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    private T element;

    private TreeNode<T> left;

    private TreeNode<T> right;
}
