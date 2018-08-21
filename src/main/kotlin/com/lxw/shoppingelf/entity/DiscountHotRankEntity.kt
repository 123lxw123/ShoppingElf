package com.lxw.shoppingelf.entity

import java.util.*
import java.io.Serializable

data class DiscountHotRankEntity(
        val date: String,
        val title: String,
        val id: Long = 0,
        val createTime: Date = Calendar.getInstance().time,
        val updateTime: Date = Calendar.getInstance().time
) : Serializable