package com.su.dao;

import com.su.domain.Slot;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
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

    /**
     * 根据id查询货位
     * @param id
     * @return
     */
    @Select("select * from slot where id=#{id}")
    @Results(id="shelfMap",value = {
            @Result(property="shelfId",column="shelfId"),
            @Result(property = "shelf",column = "shelfId", one=@One(select="com.su.dao.ShelfDao.findShelfById", fetchType = FetchType.EAGER)),
    })

    Slot findSlotById(Integer id);

    /**
     * 根据货架id查询货位
     * @param shelfId
     * @return
     */
    @Select("select * from slot where shelfId=#{shelfId}")
    @Results(id="shelfMap1",value = {
            @Result(property="id",column="id"),
            @Result(property="shelfId",column="shelfId"),
            @Result(property = "clothing",column = "id", one=@One(select="com.su.dao.ClothingDao.findClothingBySlId", fetchType = FetchType.EAGER))
    })
    List<Slot> findSlotByShId(Integer shelfId);

}
