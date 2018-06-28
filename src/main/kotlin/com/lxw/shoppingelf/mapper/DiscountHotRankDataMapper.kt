package com.lxw.shoppingelf.mapper

import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import org.apache.ibatis.annotations.Insert

interface DiscountHotRankDataMapper {

    @Insert("insert into discount_hot_rank_data(discountHotRankDate, uid, rank, title, price, desc, source, image, date)" +
            " values(#{discountHotRankDate}, #{uid}, #{rank}, #{title}, #{price}, #{desc}, #{source}, #{image}, #{date})")
    fun insert(instance : DiscountHotRankDataEntity): Int
}