package com.lxw.shoppingelf.repository

import com.lxw.shoppingelf.entity.DiscountHotRankEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscountHotRankRepository : JpaRepository<DiscountHotRankEntity, String>