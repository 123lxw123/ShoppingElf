package com.lxw.shoppingelf.mapper

import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface DiscountHotRankDataMapper {

    @Select("SELECT * FROM discount_hot_rank_data WHERE id=#{id}")
    fun select(id : String): DiscountHotRankDataEntity

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert("INSERT INTO discount_hot_rank_data(discount_hot_rank_date, discount_hot_rank_id, uid, url, rank, title, price, description, source, image, date) values (#{discountHotRankDate}, #{discount_hot_rank_id}, #{uid}, #{url}, #{rank}, #{title}, #{price}, #{description}, #{source}, #{image}, #{date})")
    fun insert(instance : DiscountHotRankDataEntity): Int

    @Update("UPDATE discount_hot_rank_data SET discount_hot_rank_date=#{discount_hot_rank_date}, discount_hot_rank_id=#{discount_hot_rank_id}, uid=#{uid}, url=#{url}, rank=#{rank}, title=#{title} ,price=#{price}, description=#{description}, source=#{source}, image=#{image}, date=#{date}")
    fun update(instance : DiscountHotRankDataEntity): Int
}