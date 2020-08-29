package com.su.service;

import com.su.domain.Shelf;

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

    /**
     * 根据编号查找货架
     * @param character
     * @return
     */
    Shelf findShelfByName(Character character);
}
