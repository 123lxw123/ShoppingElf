package com.lxw.shoppingelf.config

import com.lxw.shoppingelf.base.BaseCommonResponse

object Constants {

    const val SEARCH_URL_TIMEOUT = 8000 // 搜索 url 超时时间
    const val ORDER_TYPE_ASC = "ASC" // 正序
    const val ORDER_TYPE_DESC = "DESC" // 倒序

    object CommonResponse {
        val Success = BaseCommonResponse(1000, "success")
        val NoData = BaseCommonResponse(1001, "暂无数据，请稍后重试")
        val NoMoreData = BaseCommonResponse(1002, "暂无更多数据")

        val ParamError = BaseCommonResponse(2000, "参数错误")
    }
}