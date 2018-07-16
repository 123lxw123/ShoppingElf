package com.lxw.shoppingelf.mapper

import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import org.apache.ibatis.annotations.Insert

interface DiscountHotRankDataMapper {

    @Insert("insert into discount_hot_rank_data(discount_hot_rank_date, uid, rank, title, price, description, source, image, date, time) values (#{discountHotRankDate}, #{uid}, #{rank}, #{title}, #{price}, #{description}, #{source}, #{image}, #{date}, #{time})")
    fun insert(instance : DiscountHotRankDataEntity): Int
}