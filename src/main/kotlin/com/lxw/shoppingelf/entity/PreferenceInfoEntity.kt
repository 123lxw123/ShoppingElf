package com.lxw.shoppingelf.entity

import java.io.Serializable
import java.sql.Timestamp

data class PreferenceInfoEntity(
        val ok: Int,
        val icount: Int,
        val zklist: List<PreferenceEntity>?
        ) : Serializable