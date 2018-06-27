package com.lxw.shoppingelf.entity

import java.util.*
import java.io.Serializable

data class DiscountHotRankDataEntity(
        val discountHotRankDate: String,
        val uid: String,
        val rank: String,
        val title: String,
        val price: String,
        val desc: String,
        val source: String,
        val image: String,
        val date: String,
        val id: Long = 0,
        val timestamp: Date = Calendar.getInstance().time
) : Serializable