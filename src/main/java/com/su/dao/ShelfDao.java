package com.su.dao;

import com.su.domain.Shelf;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfDao {

    /**
     * 初始化建表
     */
    void initTable();

    /**
     * 拓展货架
     * @param shelf
     */
    void expandShelf(Shelf shelf);

    /**
     * 移除货架
     * @param id
     */
    void removeShelfById(Integer id);

    /**
     * 根据货位id查询货架
     * @param id
     * @return
     */
    Shelf findShelfBySlotId(Integer id);

}
