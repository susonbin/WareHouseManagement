package com.su.dao;

import com.su.domain.Clothing;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ClothingDao {

    /**
     * 初始化建表
     */
    void initTable();

    /**
     * 添加服装
     * @param clothing
     */
    @Insert("insert clothing(barcode,slotId,quantity)value(#{barcode},#{slotId},#{quantity})")
    void addClothing(Clothing clothing);

    /**
     * 根据服装id移除服装
     * @param id
     */
    @Delete("delete from clothing where id=#{id}")
    void removeClothingById(Integer id);

    /**
     * 更新服装的状态
     * @param clothing
     */
    @Update("update clothing set barcode=#{barcode},slotId=#{slotId},quantity=#{quantity} where id=#{id}")
    void updateClothing(Clothing clothing);

    /**
     * 根据条形码来模糊查询服装
     * @param barcode
     * @return
     */
    @Select("select * from clothing where barcode like #{barcode}")
    @ResultMap("slotMap")
    List<Clothing> findClothesByBarcode(String barcode);

    /**
     * 根据具体条形码来查询服装
     * @param barcode
     * @return
     */
    @Select("select * from clothing where barcode = #{barcode}")
    @ResultMap("slotMap")
    Clothing findClothingByBarcode(String barcode);

    /**
     * 根据货位id来查询服装
     * @param slotId
     * @return
     */
    @Select("select * from clothing where slotId = #{slotId}")
    @ResultMap("slotMap")
    Clothing findClothingBySlId(Integer slotId);

    /**
     * 根据服装id查询服装
     * @param id
     * @return
     */
    @Select("select * from clothing where id = #{id}")
    @Results(id="slotMap",value = {
            @Result(property="slotId",column="slotId"),
            @Result(property = "slot",column = "slotId", one=@One(select="com.su.dao.SlotDao.findSlotById", fetchType= FetchType.EAGER))
    })
    Clothing findClothingById(Integer id);
}
