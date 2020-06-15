package com.su.service;

public interface ShelfService {

    /**
     * 初始化建表
     */
    void initTable();

    /**
     * 拓展货架
     */
    void expandShelf();

    /**
     * 移除货架
     */
    void removeShelfById(Integer id);
}
