package com.lxw.shoppingelf.response

import com.lxw.shoppingelf.entity.HistoryEntity
import com.lxw.shoppingelf.entity.PreferenceEntity
import java.io.Serializable

data class SearchUrlModel(
        val history: HistoryEntity,
        val preferences: List<PreferenceEntity>? = listOf()
) : Serializable