package com.lxw.shoppingelf.response

import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import java.io.Serializable
import java.util.*

data class DiscountHotRankModel(
        val id: Long,
        val date: String,
        val title: String,
        val data: List<DiscountHotRankDataEntity>,
        val timestamp: Date = Calendar.getInstance().time
) : Serializable