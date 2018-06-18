package com.lxw.shoppingelf.util

import java.text.SimpleDateFormat
import java.util.*


object UtilDate {
    fun getCurrentDate(format: String = "yyyy-MM-dd HH:mm:ss"): String {
        val date = Date()
        val simpleDateFormat = SimpleDateFormat(format)
        return simpleDateFormat.format(date)
    }
}