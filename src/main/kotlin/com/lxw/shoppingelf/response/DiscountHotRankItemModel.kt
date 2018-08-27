package com.lxw.shoppingelf.response

import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import com.lxw.shoppingelf.entity.HistoryEntity
import java.io.Serializable

data class DiscountHotRankItemModel(
        val detail: DiscountHotRankDataEntity,
        val history: HistoryEntity? = null
) : Serializable