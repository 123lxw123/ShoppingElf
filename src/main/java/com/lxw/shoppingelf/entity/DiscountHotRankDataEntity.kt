package com.lxw.shoppingelf.entity

import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class DiscountHotRankDataEntity(
        @Id
        val id: String,
        val rank: String,
        val title: String,
        val price: String,
        val desc: String,
        val source: String,
        val image: String,
        val date: String,
        @UpdateTimestamp
        val timestamp: Timestamp
)