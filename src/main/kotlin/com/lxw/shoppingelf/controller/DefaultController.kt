package com.lxw.shoppingelf.controller

import com.lxw.shoppingelf.base.BaseResponse
import com.lxw.shoppingelf.config.Constants.ErrorResponse.NoDataError
import com.lxw.shoppingelf.config.Constants.ErrorResponse.NoMoreDataError
import com.lxw.shoppingelf.mapper.DiscountHotRankDataMapper
import com.lxw.shoppingelf.mapper.DiscountHotRankMapper
import com.lxw.shoppingelf.mapper.HistoryMapper
import com.lxw.shoppingelf.response.DiscountHotRankItemModel
import com.lxw.shoppingelf.response.DiscountHotRankListModel
import com.lxw.shoppingelf.util.GsonUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DefaultController {

    @Autowired
    private lateinit var discountHotRankMapper: DiscountHotRankMapper
    @Autowired
    private lateinit var discountHotRankDataMapper: DiscountHotRankDataMapper
    @Autowired
    private lateinit var historyMapper: HistoryMapper

    @GetMapping(value = ["/discountHotRank/list"])
    fun getDiscountHotRankList(@RequestParam("id") id: Long?): String {
        val discountHotRankEntity = discountHotRankMapper.selectById(id)
        if (discountHotRankEntity == null) {
            val response = BaseResponse(DiscountHotRankListModel(id), NoMoreDataError.code, NoMoreDataError.message)
            return GsonUtil.bean2json(response)
        }
        val details = discountHotRankDataMapper.selectByDiscountHotRankDate(discountHotRankEntity.date)
        if (details == null || details.isEmpty()) {
            val response = BaseResponse(DiscountHotRankListModel(id), NoDataError.code, NoDataError.message)
            return GsonUtil.bean2json(response)
        }
        val items = details.map {
            val history = historyMapper.selectByUrl(it.url)
            DiscountHotRankItemModel(it, history)
        }
        val response = BaseResponse(DiscountHotRankListModel(id, discountHotRankEntity.date, items))
        return GsonUtil.bean2json(response)
    }

}