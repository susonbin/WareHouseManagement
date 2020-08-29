package com.su.service;

import com.su.domain.Clothing;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClothingService {

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
     * 根据条形码模糊查询服装
     * @param barcode
     * @return
     */
    List<Clothing> findClothesByBarcode(String barcode);

    /**
     * 根据具体条形码来查询服装
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
