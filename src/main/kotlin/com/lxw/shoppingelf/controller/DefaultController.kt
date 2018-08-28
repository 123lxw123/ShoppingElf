package com.lxw.shoppingelf.controller

import com.lxw.shoppingelf.base.BaseResponse
import com.lxw.shoppingelf.base.BaseURL
import com.lxw.shoppingelf.config.Constants.CommonResponse.NoData
import com.lxw.shoppingelf.config.Constants.CommonResponse.NoMoreData
import com.lxw.shoppingelf.config.Constants.CommonResponse.ParamError
import com.lxw.shoppingelf.mapper.DiscountHotRankDataMapper
import com.lxw.shoppingelf.mapper.DiscountHotRankMapper
import com.lxw.shoppingelf.mapper.HistoryMapper
import com.lxw.shoppingelf.response.DiscountHotRankItemModel
import com.lxw.shoppingelf.response.DiscountHotRankListModel
import com.lxw.shoppingelf.spider.DiscountHotRankProcessor
import com.lxw.shoppingelf.spider.HistoryProcessor
import com.lxw.shoppingelf.util.GsonUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import us.codecraft.webmagic.Spider

@RestController
class DefaultController {

    @Autowired
    private lateinit var discountHotRankMapper: DiscountHotRankMapper
    @Autowired
    private lateinit var discountHotRankDataMapper: DiscountHotRankDataMapper
    @Autowired
    private lateinit var historyMapper: HistoryMapper
    @Autowired
    private lateinit var historyProcessor: HistoryProcessor

    @GetMapping(value = ["/discountHotRank/list/"])
    fun getDiscountHotRankList(@RequestParam("id") id: Long?): String {
        if (id != null && id <= 0) {
            val response = BaseResponse(null, ParamError.code, ParamError.message + " id 不能小于等于 0")
            return GsonUtil.bean2json(response)
        }
        val discountHotRankEntity = discountHotRankMapper.selectById(id)
        if (discountHotRankEntity == null) {
            val response = BaseResponse(null, NoMoreData.code, NoMoreData.message)
            return GsonUtil.bean2json(response)
        }
        val details = discountHotRankDataMapper.selectByDiscountHotRankDate(discountHotRankEntity.date)
        if (details == null || details.isEmpty()) {
            val response = BaseResponse(DiscountHotRankListModel(discountHotRankEntity), NoData.code, NoData.message)
            return GsonUtil.bean2json(response)
        }
        val items = details.map {
            val history = historyMapper.selectByUrl(it.url)
            DiscountHotRankItemModel(it, history)
        }
        val response = BaseResponse(DiscountHotRankListModel(discountHotRankEntity, items))
        return GsonUtil.bean2json(response)
    }

    @GetMapping(value = ["/searchUrl/"])
    fun getSearchUrlResult(@RequestParam("url") url: String?): String {
        if (url.isNullOrBlank()) {
            val response = BaseResponse(null, ParamError.code, ParamError.message + " url 不能为空")
            return GsonUtil.bean2json(response)
        }
        val pageUrl = BaseURL.SEARCH_URL_HISTORY.replace("{url}", url!!)
        Spider.create(historyProcessor).thread(1)
                .addUrl(pageUrl)
                .run()
        return ""
    }
}