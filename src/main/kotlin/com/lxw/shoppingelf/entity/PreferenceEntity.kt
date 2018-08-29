package com.lxw.shoppingelf.entity

import java.io.Serializable
import java.sql.Timestamp

data class PreferenceEntity (
        var url: String,
        val spname: String,
        val spprice: String,
        val dt: String,
        val infoid: Int,
        val infotype: String,
        val sppic: String,

        val id: Long = 0,
        val createTime: Timestamp? = null,
        val updateTime: Timestamp? = null
) : Serializable