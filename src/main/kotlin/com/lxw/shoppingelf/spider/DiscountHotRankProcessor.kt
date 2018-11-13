package com.lxw.shoppingelf.spider

import com.lxw.shoppingelf.base.BaseProcessor
import com.lxw.shoppingelf.base.BaseURL
import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import com.lxw.shoppingelf.entity.DiscountHotRankEntity
import com.lxw.shoppingelf.mapper.DiscountHotRankDataMapper
import com.lxw.shoppingelf.mapper.DiscountHotRankMapper
import com.lxw.shoppingelf.util.getContentFromHTML
import org.jsoup.helper.StringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Spider

@Component
class DiscountHotRankProcessor : BaseProcessor() {

    @Autowired
    private lateinit var discountHotRankMapper: DiscountHotRankMapper
    @Autowired
    private lateinit var discountHotRankDataMapper: DiscountHotRankDataMapper
    @Autowired
    private lateinit var discountHotRankDetailProcessor: DiscountHotRankDetailProcessor
    @Autowired
    private lateinit var discountHotRankHistoryProcessor: HistoryProcessor

    private lateinit var date: String

    fun setDate(date: String) {
        this.date = date
    }

    override fun process(page: Page) {
        val html = page.html
        val url = page.url.toString()
        val detailUrls = mutableListOf<String>()
        val historyUrls = mutableListOf<String>()
        var title = ""
        try {
            title = html.css("div.t").get()
                    .replace("<span>", "(")
                    .replace("</span>", ")")
                    .getContentFromHTML()
        } catch (e: Exception) {

        }
        try {
            if (url.contains(BaseURL.DISCOUNT_NEW_LIST) || (!url.contains(BaseURL.DISCOUNT_NEW_LIST) && title.subSequence(10, 12) == date.subSequence(11, 13))) {
                if (!url.contains(BaseURL.DISCOUNT_NEW_LIST)) {
                    try {
                        title = title.split("(")[0] + "(" + date.subSequence(0, 10) + " " + title.split("(")[1]
                        val discountHotRankEntity = DiscountHotRankEntity(date, title)
                        discountHotRankMapper.insert(discountHotRankEntity)
                    } catch (e: Exception) {
                        val oldDiscountHotRankEntity = discountHotRankMapper.selectByTitle(title)
                        date = oldDiscountHotRankEntity.date
                        e.printStackTrace()
                    }
                }
                val item = html.css("li.item")
                val uids = item.css("span.gobuy").regex("tobuy\\('(.*?)'\\)").all()
                val ranks = item.css("i.ic_top").all().map { it?.getContentFromHTML() }
                val titles = item.css("h2").css("a").all().filter { !it.contains("highlight") }.map { it.getContentFromHTML() }
                val prices = item.css("h2").css("a.highlight").all().map { it.getContentFromHTML() }
                val descriptions = item.css("div.descripe").all().map { it.getContentFromHTML() }
                val sources = item.css("span.mall").all().map { it.getContentFromHTML() }
                val images = item.css("img", "original").all()
                val dates = item.css("span.time").all().map { it.getContentFromHTML() }
                uids.forEachIndexed { index, _ ->
                    val detailUrl = BaseURL.DISCOUNT_HOT_RANK_DETAIL.replace("{uid}", uids[index])
                    val historyUrl = BaseURL.DISCOUNT_HOT_RANK_HISTORY.replace("{uid}", uids[index])
                    val throughUrl = BaseURL.DISCOUNT_HOT_RANK_THROUGH.replace("{uid}", uids[index])
                    val discountHotRankDataEntity = DiscountHotRankDataEntity(
                            date,
                            uids[index],
                            detailUrl,
                            throughUrl,
                            if (ranks.size == uids.size) ranks[index] else "",
                            titles[index],
                            prices[index],
                            descriptions[index],
                            sources[index],
                            images[index],
                            dates[index],
                            item.nodes()[index].css("div.item_isMinPrice").get()?.getContentFromHTML()
                    )
                    try {
                        discountHotRankDataMapper.insert(discountHotRankDataEntity)
                    } catch (e: Exception) {
                        discountHotRankDataMapper.update(discountHotRankDataEntity)
                        e.printStackTrace()
                    }

                    detailUrls.add(detailUrl + "?date=${date.replace(" ", "$$$")}")
                    historyUrls.add(historyUrl)
                }
                Spider.create(discountHotRankDetailProcessor).thread(3)
                        .addUrl(*detailUrls.toTypedArray())
                        .run()
                Spider.create(discountHotRankHistoryProcessor).thread(3)
                        .addUrl(*historyUrls.toTypedArray())
                        .run()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}