package com.luni724.datac.tree;

/**
 * @author liq
 * @date 2020/5/26
 */
public interface TreeOperation<T> {

    /**
     * 树置空
     */
    void markEmpty();

    /**
     * 判断是否非空树
     * @return
     */
    boolean isEmpty();


    /**
     * 添加元素
     * @param t
     */
    void insert(T t);

    /**
     * 删除元素
     * @param t
     */
    void remove(T t);



}
