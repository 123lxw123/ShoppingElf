package com.lxw.shoppingelf.mapper

import com.lxw.shoppingelf.entity.DiscountHotRankEntity
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

interface DiscountHotRankMapper {

    @Select("<script> " +
            "SELECT * FROM discount_hot_rank " +
            "<if test=\"id != null and orderType == 'DESC'\"> WHERE id &lt; #{id} ORDER BY id DESC </if> " +
            "<if test=\"id != null and orderType == 'ASC'\"> WHERE id &gt; #{id} ORDER BY id ASC </if> " +
            "<if test=\"id == null\"> ORDER BY id DESC </if> " +
            "LIMIT 1 " +
            " </script> "
    )
    fun selectById(@Param("id")id : Long?, @Param("orderType")orderType: String): DiscountHotRankEntity?

    @Select("SELECT * FROM discount_hot_rank WHERE title=#{title}")
    fun selectByTitle(title : String): DiscountHotRankEntity

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert("INSERT INTO discount_hot_rank(date, title) values (#{date}, #{title})")
    fun insert(instance : DiscountHotRankEntity): Int
}