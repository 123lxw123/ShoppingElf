package com.lxw.shoppingelf.mapper

import com.lxw.shoppingelf.entity.HistoryEntity
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface HistoryMapper {

    @Select("SELECT * FROM history WHERE url=#{url}")
    fun selectByUrl(url : String): HistoryEntity?

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert("INSERT INTO history(url, bjid, chang_price_remark, change_price_count, current_price, date_price, from_type, lower_date, lower_price, runtime, site_id, site_name, sp_name, sp_pic, sp_url, spbh, zou_shi, zou_shi_test) values (#{url}, #{bjid}, #{changPriceRemark}, #{changePriceCount}, #{currentPrice}, #{datePrice}, #{fromType}, #{lowerDate}, #{lowerPrice}, #{runtime}, #{siteId}, #{siteName}, #{spName}, #{spPic}, #{spUrl}, #{spbh}, #{zouShi}, #{zouShi_test})")
    fun insert(instance : HistoryEntity): Int

    @Update("UPDATE history SET url=#{url}, bjid=#{bjid}, chang_price_remark=#{changPriceRemark}, change_price_count=#{changePriceCount}, current_price=#{currentPrice}, date_price=#{datePrice} ,from_type=#{fromType}, lower_date=#{lowerDate}, lower_price=#{lowerPrice}, runtime=#{runtime}, site_id=#{siteId}, site_name=#{siteName}, sp_name=#{spName}, sp_pic=#{spPic}, sp_url=#{spUrl}, spbh=#{spbh}, zou_shi=#{zouShi}, zou_shi_test=#{zouShi_test} WHERE id=#{id}")
    fun update(instance : HistoryEntity): Int
}