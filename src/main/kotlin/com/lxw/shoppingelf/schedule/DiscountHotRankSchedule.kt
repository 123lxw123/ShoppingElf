package com.lxw.shoppingelf.schedule

import com.lxw.shoppingelf.base.BaseURL
import com.lxw.shoppingelf.spider.DiscountHotRankProcessor
import com.lxw.shoppingelf.util.DateUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import us.codecraft.webmagic.Spider




/**
 * 国内折扣热度排行榜
 */
@Component
class DiscountHotRankSchedule {
    @Autowired
    private lateinit var discountHotRankProcessor: DiscountHotRankProcessor

    @Scheduled(cron = "0 55 23 * * ?")
    fun getDiscountHotRank() {
        val dateContent = DateUtil.getCurrentDate("yyyy/MM/dd HH:mm:ss")
        val date = dateContent.substring(0, 10)
        val hour = dateContent.substring(11, 13)
        val url = BaseURL.DISCOUNT_HOT_RANK.replace("{date}", date)
                .replace("{hour}", hour)
        discountHotRankProcessor.setDate(dateContent)
        Spider.create(discountHotRankProcessor).thread(1)
                .addUrl(url)
                .run()
    }
}