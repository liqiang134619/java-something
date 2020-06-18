package com.luni724.datac.tree;

import lombok.Data;

/**
 * @author liq
 * @date 2020/5/26
 */
@Data
public class BinarySearchTree<T extends Comparable<? super T>> implements TreeOperation<T> {

    private TreeNode<T> root;

    public BinarySearchTree() {
        root = null;
    }


    @Override
    public void markEmpty() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insert(T element) {
        this.root = insert(element,root);
    }



    @Override
    public void remove(T element) {
        this.root = remove(element,root);
    }

    /**
     * 打印树
     */
    public void printTree() {
        if(root !=null) {
        }
    }


    /**
     * 是否包含某一个元素
     * @param element 需要判断的元素值
     * @return true or false
     */
    public boolean contains(T element) {
        return contains(element,root);
    }




    private TreeNode<T> remove(T element, TreeNode<T> root) {

        if (root == null) {
            return root;
        }
        int compareTo = element.compareTo(root.getElement());
        if (compareTo < 0) {
            root.setLeft(remove(element,root.getLeft()));
        } else if (compareTo > 0) {
            root.setRight(remove(element,root.getRight()));
        } else if (root.getLeft() != null && root.getRight() != null) {
            // 有左右两个孩子
            // 查询删除节点右子树的最小节点
            TreeNode<T> min = findMin(root.getRight());
            // 设置当前节点为右子树的最小节点
            root.setElement(min.getElement());

            // 删除右子树上的节点
            root.setRight(remove(root.getElement(),root.getRight()));


        } else {
            root = (root.getLeft() != null )? root.getLeft() : root.getRight();
        }
        return  root;
    }

    /**
     * 查找最小节点 递归实现
     * @param root
     * @return
     */
    private TreeNode<T> findMin(TreeNode<T> root ) {
        if (root == null) {
            return null;
        } else if (root.getLeft() == null) {
            return root;
        }
        return findMin(root.getLeft());
    }

    private TreeNode<T> findMax2(TreeNode<T> root) {
        if (root == null) {
            return null;
        } else if (root.getRight() ==null) {
            return root;
        }
        return findMax2(root.getRight());
    }

    /**
     * 查找最小节点，非递归实现
     * @param root
     * @return
     */
    private TreeNode<T> findMin2(TreeNode<T> root) {
        if (root != null) {
            while(root.getLeft() != null) {
                root = root.getLeft();
            }
        }
        return root;
    }

    private TreeNode<T> findMax(TreeNode<T> root) {
        if (root != null) {
            while(root.getRight() != null) {
                root = root.getRight();
            }
        }
        return root;
    }

    private TreeNode<T> insert(T element, TreeNode<T> root) {
        if (root == null) {
            return new TreeNode<T>(element);
        }
        int compareTo = element.compareTo(root.getElement());
        // 插入左子树
        if (compareTo < 0) {
            root.setLeft(insert(element,root.getLeft()));
            // 插入右子树
        } else  if (compareTo > 0) {
            root.setRight(insert(element,root.getRight()));
        } else {
            // Duplicate do nothing
        }

        return root;
    }


    private boolean contains(T element, TreeNode<T> root) {

        if (root == null) {
            return false;
        }
        int compareTo = element.compareTo(root.getElement());
        // 小于 搜索左子树
        if (compareTo < 0) {
            return contains(element,root.getLeft());
            // 大于 搜索右子树
        } else if (compareTo > 0) {
            return contains(element,root.getRight());
        }
        // match
        return true;
    }


}
