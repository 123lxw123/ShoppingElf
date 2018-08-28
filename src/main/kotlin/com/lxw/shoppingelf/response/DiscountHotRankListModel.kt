package com.lxw.shoppingelf.response

import com.lxw.shoppingelf.entity.DiscountHotRankEntity
import java.io.Serializable

data class DiscountHotRankListModel(
        val discountHotRankEntity: DiscountHotRankEntity,
        val list: List<DiscountHotRankItemModel>? = listOf()
) : Serializable