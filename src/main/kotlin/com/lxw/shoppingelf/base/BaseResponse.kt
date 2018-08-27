package com.lxw.shoppingelf.base

import java.io.Serializable

data class BaseResponse(
        val body: Any?,
        val code: Int = 200,
        val message: String? = null
) : Serializable