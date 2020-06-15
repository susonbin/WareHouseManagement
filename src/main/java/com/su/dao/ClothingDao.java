package com.su.dao;

import com.su.domain.Clothing;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingDao {

    /**
     * 初始化建表
     */
    void initTable();

    /**
     * 添加服装
     * @param clothing
     */
    void addClothing(Clothing clothing);

    /**
     * 根据服装id移除服装
     * @param integer
     */
    void removeClothingById(Integer integer);

    /**
     * 更新服装的状态
     * @param clothing
     */
    void updateClothing(Clothing clothing);

    /**
     * 根据条形码查询服装
     * @param barcode
     * @return
     */
    Clothing findClothingByBarcode(String barcode);

    /**
     * 根据服装id查询服装
     * @param id
     * @return
     */
    Clothing findClothingById(Integer id);
}
