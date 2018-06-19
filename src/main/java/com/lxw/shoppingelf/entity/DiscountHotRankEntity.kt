package com.lxw.shoppingelf.entity

import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class DiscountHotRankEntity(
        @Id
        val date: String,
        val title: String,
        val data: List<DiscountHotRankDataEntity>,
        @UpdateTimestamp
        val timestamp: Timestamp
)