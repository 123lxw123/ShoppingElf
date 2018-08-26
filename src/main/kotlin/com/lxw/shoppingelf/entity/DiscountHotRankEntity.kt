package com.lxw.shoppingelf.entity

import java.io.Serializable
import java.sql.Timestamp

data class DiscountHotRankEntity(
        val date: String,
        val title: String,
        val id: Long = 0,
        val createTime: Timestamp? = null,
        val updateTime: Timestamp? = null
        ) : Serializable