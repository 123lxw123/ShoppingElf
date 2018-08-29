package com.lxw.shoppingelf.mapper

import com.lxw.shoppingelf.entity.PreferenceEntity
import org.apache.ibatis.annotations.*

interface PreferenceMapper {

    @Select("SELECT * FROM preference WHERE url=#{url}")
    fun selectByUrl(url : String): List<PreferenceEntity>?

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert("INSERT INTO preference(url, spname, spprice, dt, infoid, infotype, sppic) values (#{url}, #{spname}, #{spprice}, #{dt}, #{infoid}, #{infotype}, #{sppic})")
    fun insert(instance : PreferenceEntity): Int

    @Update("UPDATE preference SET url=#{url}, spname=#{spname}, spprice=#{spprice}, dt=#{dt}, infoid=#{infoid}, infotype=#{infotype} ,sppic=#{sppic}")
    fun update(instance : PreferenceEntity): Int

    @Delete("DELETE FROM preference WHERE url=#{url}")
    fun delete(url : String): Int
}