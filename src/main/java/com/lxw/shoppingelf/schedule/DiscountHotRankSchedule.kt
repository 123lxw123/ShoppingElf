package com.lxw.shoppingelf.schedule

import com.lxw.shoppingelf.base.BaseURL
import com.lxw.shoppingelf.spider.DiscountHotRankProcessor
import com.lxw.shoppingelf.util.UtilDate
import org.springframework.stereotype.Service
import org.springframework.scheduling.annotation.Scheduled
import us.codecraft.webmagic.Spider




/**
 * 国内折扣热度排行榜
 */
@Service
class DiscountHotRankSchedule {
    @Scheduled(cron = "0 0 * * * *")
    fun getDiscountHotRank(dateContent: String = UtilDate.getCurrentDate("yyyy/MM/dd HH:mm:ss")) {
        val date = dateContent.substring(0, 10)
        val hour = dateContent.substring(12, 14)
        val url = BaseURL.DISCOUNT_HOT_RANK.replace("{date}", date)
                .replace("{hour}", hour)
        Spider.create(DiscountHotRankProcessor()).thread(1)
                .addUrl(url)
                .run()
    }

}