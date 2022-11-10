package com.rocky.ao.protocols;

/**
 * @author yun.ao
 * @date 2022/11/10 15:04
 * @description
 */
public interface Listable<E> {
    /**
     * 获取集合大小
     * @return 整型
     */
    int size();

    /**
     * 获取是否是空集合
     * @return bool
     */
    boolean isEmpty();

    /**
     * 查询是否包含某个元素
     * @param element 元素
     * @return bool
     */
    boolean contains(E element);

    /**
     * 获取某个位置上的元素
     * @param index 位置节点
     * @return 该节点上的元素
     */
    E get(int index);

    /**
     * 设置元素
     * @param index 目标位置
     * @param element 元素
     * @return 设置的元素本身
     */
    E set(int index, E element);

    /**
     * 在末尾添加元素
     * @param element 元素
     */
    void add(E element);

    /**
     * 在指定位置添加元素
     * @param index 位置
     * @param element 元素
     */
    void add(int index, E element);

    /**
     * 删除指定位置的元素
     * @param index 要删除元素的位置
     * @return
     */
    E remove(int index);

    /**
     * 查询元素所在下标
     * @param element 元素
     * @return 元素下标
     */
    int indexOf(E element);

    /**
     * 清空数组
     */
    void clear();
}
