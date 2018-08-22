package com.lxw.shoppingelf.mapper

import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import com.lxw.shoppingelf.entity.DiscountHotRankEntity
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface DiscountHotRankMapper {

    @Select("SELECT * FROM discount_hot_rank WHERE id=#{id}")
    fun select(id : String): DiscountHotRankEntity

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert("INSERT INTO discount_hot_rank(date, title) values (#{date}, #{title})")
    fun insert(instance : DiscountHotRankEntity): Int

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Update("UPDATE discount_hot_rank_data SET discount_hot_rank_date=#{discount_hot_rank_date}, uid=#{uid}, url=#{url}, rank=#{rank}, title=#{title} ,price=#{price}, description=#{description}, source=#{source}, image=#{image}, date=#{date}")
    fun update(instance : DiscountHotRankDataEntity): Int
}