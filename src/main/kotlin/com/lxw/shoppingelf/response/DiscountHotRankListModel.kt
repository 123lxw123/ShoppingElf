package com.lxw.shoppingelf.response

import java.io.Serializable

data class DiscountHotRankListModel(
        val id: Long?,
        val date: String? = null,
        val list: List<DiscountHotRankItemModel>? = listOf()
) : Serializable