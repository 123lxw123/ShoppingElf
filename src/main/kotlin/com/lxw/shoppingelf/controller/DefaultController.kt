package com.lxw.shoppingelf.controller

import com.lxw.shoppingelf.base.BaseResponse
import com.lxw.shoppingelf.base.BaseURL
import com.lxw.shoppingelf.config.Constants.CommonResponse.NoData
import com.lxw.shoppingelf.config.Constants.CommonResponse.NoMoreData
import com.lxw.shoppingelf.config.Constants.CommonResponse.ParamError
import com.lxw.shoppingelf.config.Constants.SEARCH_URL_TIMEOUT
import com.lxw.shoppingelf.entity.HistoryEntity
import com.lxw.shoppingelf.entity.PreferenceEntity
import com.lxw.shoppingelf.mapper.DiscountHotRankDataMapper
import com.lxw.shoppingelf.mapper.DiscountHotRankMapper
import com.lxw.shoppingelf.mapper.HistoryMapper
import com.lxw.shoppingelf.mapper.PreferenceMapper
import com.lxw.shoppingelf.response.DiscountHotRankItemModel
import com.lxw.shoppingelf.response.DiscountHotRankListModel
import com.lxw.shoppingelf.response.SearchUrlModel
import com.lxw.shoppingelf.spider.DiscountHotRankProcessor
import com.lxw.shoppingelf.spider.HistoryProcessor
import com.lxw.shoppingelf.spider.PreferenceProcessor
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
    @Autowired
    private lateinit var preferenceMapper: PreferenceMapper
    @Autowired
    private lateinit var preferenceProcessor: PreferenceProcessor

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
        val historyUrl = BaseURL.SEARCH_URL_HISTORY.replace("{url}", url!!)
        val preferenceUrl = BaseURL.SEARCH_URL_PREFERENCE.replace("{url}", url)
        historyMapper.delete(url)
        preferenceMapper.delete(url)
        Spider.create(historyProcessor).thread(1)
                .addUrl(historyUrl)
                .run()
        Spider.create(preferenceProcessor).thread(1)
                .addUrl(preferenceUrl)
                .run()
        var totalTime = 0
        var history: HistoryEntity? = null
        var preferences: List<PreferenceEntity>? = null
        while (totalTime < SEARCH_URL_TIMEOUT) {
            totalTime += 1000
            history = historyMapper.selectByUrl(url)
            preferences = preferenceMapper.selectByUrl(url)
            if (history != null && preferences != null && preferences.isNotEmpty()) {
                val response = BaseResponse(SearchUrlModel(history, preferences))
                return GsonUtil.bean2json(response)
            }
            Thread.sleep(1000)
        }
        return if (history != null) {
            val response = BaseResponse(SearchUrlModel(history, preferences))
            GsonUtil.bean2json(response)
        } else {
            val response = BaseResponse(null, NoData.code, NoData.message)
            GsonUtil.bean2json(response)
        }
    }
}