package com.lxw.shoppingelf.mapper

import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import com.lxw.shoppingelf.entity.DiscountHotRankEntity
import org.apache.ibatis.annotations.*

interface DiscountHotRankMapper {

    @Select("<script> " +
            "SELECT * FROM discount_hot_rank " +
            "<if test=\"id != null\"> WHERE id &lt; #{id} </if> " +
            "ORDER BY id DESC " +
            "LIMIT 1 " +
            " </script> "
    )
    fun selectById(@Param("id")id : Long?): DiscountHotRankEntity?

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert("INSERT INTO discount_hot_rank(date, title) values (#{date}, #{title})")
    fun insert(instance : DiscountHotRankEntity): Int

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Update("UPDATE discount_hot_rank SET date=#{date}, title=#{title} WHERE id=#{id}")
    fun update(instance : DiscountHotRankEntity): Int
}