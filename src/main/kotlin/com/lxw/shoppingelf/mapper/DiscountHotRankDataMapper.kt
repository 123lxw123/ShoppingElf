package com.lxw.shoppingelf.mapper

import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import org.apache.ibatis.annotations.*

interface DiscountHotRankDataMapper {

    @Select("SELECT * FROM discount_hot_rank_data WHERE url=#{url} AND discount_hot_rank_date = #{discountHotRankDate}")
    fun selectOne(@Param("url")url : String,  @Param("discountHotRankDate")discountHotRankDate : String): DiscountHotRankDataEntity

    @Select("SELECT * FROM discount_hot_rank_data WHERE discount_hot_rank_date=#{discountHotRankDate}")
    fun selectByDiscountHotRankDate(discountHotRankDate : String): List<DiscountHotRankDataEntity>?

    @Select("SELECT * FROM discount_hot_rank_data WHERE id IN (id<#{id} AND ID IN (SELECT MAX(id) FROM discount_hot_rank_data WHERE id < #{id} GROUP BY url ORDER BY id DESC) LIMIT #{limit}")
    fun selectByIdAndLimit(id : Long, limit: Int): List<DiscountHotRankDataEntity>?

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert("INSERT INTO discount_hot_rank_data(discount_hot_rank_date, uid, url, through_url, rank, title, price, description, source, image, date, is_min_price, category, introducer, label, share_count, collect_count, like_count) values (#{discountHotRankDate}, #{uid}, #{url}, #{throughUrl}, #{rank}, #{title}, #{price}, #{description}, #{source}, #{image}, #{date}, #{isMinPrice}, #{category}, #{introducer}, #{label}, #{shareCount}, #{collectCount}, #{likeCount})")
    fun insert(instance : DiscountHotRankDataEntity): Int

    @Update("UPDATE discount_hot_rank_data SET discount_hot_rank_date=#{discountHotRankDate}, uid=#{uid}, url=#{url}, through_url=#{throughUrl}, rank=#{rank}, title=#{title} ,price=#{price}, description=#{description}, source=#{source}, image=#{image}, date=#{date}, is_min_price=#{isMinPrice}, category=#{category}, introducer=#{introducer}, label=#{label}, share_count=#{shareCount}, collect_count=#{collectCount}, like_count=#{likeCount} WHERE url=#{url} AND discount_hot_rank_date = #{discountHotRankDate}")
    fun update(instance : DiscountHotRankDataEntity): Int
}