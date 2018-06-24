package com.lxw.shoppingelf.entity

import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import javax.persistence.*

@Entity
data class DiscountHotRankEntity(
        @Id
        val date: String = "",
        val title: String = "",
        @ElementCollection
        val data: List<DiscountHotRankDataEntity> = listOf(),
        @LastModifiedDate
        val timestamp: Date = Calendar.getInstance().time
)