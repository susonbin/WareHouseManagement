package com.su.dao;

import com.su.domain.Shelf;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Mapper
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

    /**
     * 根据货位id查询货架
     * @param id
     * @return
     */
    @Select("select * from shelf where id = #{id}")
    Shelf findShelfById(Integer id);

    /**
     * 根据编号查询货架
     * @param character
     * @return
     */
    @Select("select * from shelf where name = #{name}")
    @Results(id="shMap",value = {
            @Result(property="id",column="id"),
            @Result(property = "slots",column = "id", many = @Many(select = "com.su.dao.SlotDao.findSlotByShId", fetchType = FetchType.LAZY))
    })
    Shelf findShelfByName(Character character);

}
