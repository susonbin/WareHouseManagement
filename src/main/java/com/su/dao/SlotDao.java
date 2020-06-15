package com.su.dao;

import com.su.domain.Slot;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotDao {

    /**
     * 初始化建表
     */
    void initTable();

    /**
     * 根据服装id查询货位
     * @param id
     * @return
     */
    Slot findSlotByClotheId(Integer id);
}
