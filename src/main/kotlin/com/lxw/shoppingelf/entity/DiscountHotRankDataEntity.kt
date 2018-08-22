package com.lxw.shoppingelf.entity

import java.util.*
import java.io.Serializable

data class DiscountHotRankDataEntity(
        val discountHotRankDate: String,
        val discountHotId: Int,
        val uid: String,
        val url: String,
        val rank: String,
        val title: String,
        val price: String,
        val description: String,
        val source: String,
        val image: String?,
        val date: String,

        val category: String,
        val id: Int = 0,
        val createTime: Date =  Calendar.getInstance().time,
        val updateTime: Date =  Calendar.getInstance().time
) : Serializable