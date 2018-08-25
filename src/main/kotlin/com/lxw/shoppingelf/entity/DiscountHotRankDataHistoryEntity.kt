package com.lxw.shoppingelf.entity

import java.util.*
import java.io.Serializable

data class DiscountHotRankDataHistoryEntity(
        val discountHotId: Int,
        val uid: String,

        val bjid: Long,
        val changPriceRemark: String,
        val changePriceCount: Int,
        val currentPrice: Int, // 2399
        val datePrice: String, // "[Date.UTC(2018,7,23),2399],[Date.UTC(2018,7,24),2398.00]"
        val fromType: String,
        val lowerDate: String, // "/Date(1535040000000-0000)/"
        val lowerPrice: Int, // 2398
        val runtime: Int,
        val siteId: Int,
        val siteName: String, // "京东商城"
        val spName: String, // "酷乐视 COOLUX Z6S 便携投影仪 投影机办公( 4LED高亮 3D 迷你 /微型手机投影 自动聚焦 充电宝)"
        val spPic: String, // "http://img14.360buyimg.com/n7/jfs/t24367/76/2035916396/86913/da8aa7e4/5b70eae2N24173231.jpg"
        val spUrl: String, // "http://item.jd.com/8636434.html"
        val spbh: String,
        val zouShi: Int,
        val zouShi_test: Int = 0,

        val id: Int = 0,
        val createTime: Date =  Calendar.getInstance().time,
        val updateTime: Date =  Calendar.getInstance().time
) : Serializable