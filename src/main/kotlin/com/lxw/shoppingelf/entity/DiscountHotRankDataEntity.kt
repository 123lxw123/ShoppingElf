package com.lxw.shoppingelf.entity

import java.io.Serializable
import java.sql.Timestamp

data class DiscountHotRankDataEntity(
        val discountHotRankDate: String,
        val uid: String,
        val url: String,
        val throughUrl: String,
        val rank: String,
        val title: String,
        val price: String,
        val description: String,
        val source: String,
        val image: String?,
        val date: String,
        val isMinPrice: String?,

        var category: String? = null,
        var introducer: String? = null,
        var label: String? = null,
        var shareCount: Int = 0,
        var collectCount: Int = 0,
        var likeCount: Int = 0,

        val id: Long = 0,
        val createTime: Timestamp? = null,
        val updateTime: Timestamp? = null
) : Serializable