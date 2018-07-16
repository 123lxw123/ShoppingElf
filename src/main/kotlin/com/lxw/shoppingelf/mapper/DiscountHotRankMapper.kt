package com.lxw.shoppingelf.mapper

import com.lxw.shoppingelf.entity.DiscountHotRankEntity
import org.apache.ibatis.annotations.Insert

interface DiscountHotRankMapper {

    @Insert("insert into discount_hot_rank(date, title, time) values (#{date}, #{title}, #{time})")
    fun insert(instance : DiscountHotRankEntity): Int
}