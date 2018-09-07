package com.lxw.shoppingelf.spider

import com.lxw.shoppingelf.base.BaseProcessor
import com.lxw.shoppingelf.base.BaseURL
import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import com.lxw.shoppingelf.entity.DiscountHotRankEntity
import com.lxw.shoppingelf.mapper.DiscountHotRankDataMapper
import com.lxw.shoppingelf.mapper.DiscountHotRankMapper
import com.lxw.shoppingelf.util.getContentFromHTML
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Spider

@Component
class DiscountHotRankProcessor : BaseProcessor(){

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
        val detailUrls = mutableListOf<String>()
        val historyUrls = mutableListOf<String>()
        val title = html.css("div.t").get()
                .replace("<span>", "(")
                .replace("</span>", ")")
                .getContentFromHTML()
        val discountHotRankEntity = DiscountHotRankEntity(date, title)
        try{
            discountHotRankMapper.insert(discountHotRankEntity)
        } catch (e : Exception) {
            val oldDiscountHotRankEntity = discountHotRankMapper.selectByTitle(title)
            date = oldDiscountHotRankEntity.date
            e.printStackTrace()
        }
        val item = html.css("li.item")
        val uids = item.css("span.gobuy").regex("tobuy\\('(.*?)'\\)").all()
        val ranks = item.css("i.ic_top").all().map { it.getContentFromHTML() }
        val titles = item.css("h2").css("a").all().filter { !it.contains("highlight") }.map { it.getContentFromHTML() }
        val prices = item.css("h2").css("a.highlight").all().map { it.getContentFromHTML() }
        val descriptions = item.css("div.descripe").all().map { it.getContentFromHTML() }
        val sources = item.css("span.mall").all().map { it.getContentFromHTML() }
        val images = item.css("img", "src").all()
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
                    ranks[index],
                    titles[index],
                    prices[index],
                    descriptions[index],
                    sources[index],
                    images[index],
                    dates[index]
            )
            try {
                discountHotRankDataMapper.insert(discountHotRankDataEntity)
            } catch (e: Exception) {
                discountHotRankDataMapper.update(discountHotRankDataEntity)
                e.printStackTrace()
            }

            detailUrls.add(detailUrl  + "?date=${date.replace(" ", "$$$")}")
            historyUrls.add(historyUrl)
        }
        Spider.create(discountHotRankDetailProcessor).thread(3)
                .addUrl(*detailUrls.toTypedArray())
                .run()
        Spider.create(discountHotRankHistoryProcessor).thread(3)
                .addUrl(*historyUrls.toTypedArray())
                .run()
    }

}