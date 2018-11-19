package com.lxw.shoppingelf.response

import com.lxw.shoppingelf.entity.DiscountHotRankEntity
import java.io.Serializable

data class DiscountHotRankListModel(
        val discountHotRankEntity: DiscountHotRankEntity? = null,
        val list: List<DiscountHotRankItemModel>? = listOf()
) : Serializable