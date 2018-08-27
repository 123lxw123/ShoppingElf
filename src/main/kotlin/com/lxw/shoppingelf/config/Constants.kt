package com.lxw.shoppingelf.config

import com.lxw.shoppingelf.base.BaseErrorResponse

object Constants {

    object ErrorResponse {
        val ParamError = BaseErrorResponse(1000, "参数错误")
        val NoDataError = BaseErrorResponse(1001, "暂无数据，请稍后重试")
        val NoMoreDataError = BaseErrorResponse(1002, "暂无更多数据")
    }
}