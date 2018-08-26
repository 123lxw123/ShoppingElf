package com.lxw.shoppingelf.mapper

import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface DiscountHotRankDataMapper {

    @Select("SELECT * FROM discount_hot_rank_data WHERE url=#{url}")
    fun selectByUrl(url : String): DiscountHotRankDataEntity

    @Select("SELECT * FROM discount_hot_rank_data WHERE discount_hot_rank_date=#{discountHotRankDate}")
    fun selectByDiscountHotRankDate(discountHotRankDate : String): DiscountHotRankDataEntity

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert("INSERT INTO discount_hot_rank_data(discount_hot_rank_date, uid, url, through_url, rank, title, price, description, source, image, date, category, introducer, label, share_count, collect_count, like_count) values (#{discountHotRankDate}, #{uid}, #{url}, #{throughUrl}, #{rank}, #{title}, #{price}, #{description}, #{source}, #{image}, #{date}, #{category}, #{introducer}, #{label}, #{shareCount}, #{collectCount}, #{likeCount})")
    fun insert(instance : DiscountHotRankDataEntity): Int

    @Update("UPDATE discount_hot_rank_data SET discount_hot_rank_date=#{discountHotRankDate}, uid=#{uid}, url=#{url}, through_url=#{throughUrl}, rank=#{rank}, title=#{title} ,price=#{price}, description=#{description}, source=#{source}, image=#{image}, date=#{date}, category=#{category}, introducer=#{introducer}, label=#{label}, share_count=#{shareCount}, collect_count=#{collectCount}, like_count=#{likeCount} WHERE id=#{id}")
    fun update(instance : DiscountHotRankDataEntity): Int
}