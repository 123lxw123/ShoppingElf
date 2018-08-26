package com.lxw.shoppingelf.entity

import java.io.Serializable
import java.sql.Timestamp

data class HistoryEntity(
        var url: String,
        val bjid: Long? = null,
        val changPriceRemark: String,
        val changePriceCount: Int,
        val currentPrice: Double, // 2399
        val datePrice: String, // "[Date.UTC(2018,7,23),2399],[Date.UTC(2018,7,24),2398.00]"
        val fromType: String,
        val lowerDate: String, // "/Date(1535040000000-0000)/"
        val lowerPrice: Double, // 2398
        val runtime: Int,
        val siteId: Int? = null,
        val siteName: String? = null, // "京东商城"
        val spName: String? = null, // "酷乐视 COOLUX Z6S 便携投影仪 投影机办公( 4LED高亮 3D 迷你 /微型手机投影 自动聚焦 充电宝)"
        val spPic: String ? = null, // "http://img14.360buyimg.com/n7/jfs/t24367/76/2035916396/86913/da8aa7e4/5b70eae2N24173231.jpg"
        val spUrl: String, // "http://item.jd.com/8636434.html"
        val spbh: String? = null,
        val zouShi: Int? = null,
        val zouShi_test: Int? = null,

        val id: Long = 0,
        val createTime: Timestamp? = null,
        val updateTime: Timestamp? = null
        ) : Serializable