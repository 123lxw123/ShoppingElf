package com.lxw.shoppingelf.base

import com.lxw.shoppingelf.config.Constants
import java.io.Serializable

data class BaseResponse(
        val body: Any?,
        val code: Int = Constants.CommonResponse.Success.code,
        val message: String? = null
) : Serializable