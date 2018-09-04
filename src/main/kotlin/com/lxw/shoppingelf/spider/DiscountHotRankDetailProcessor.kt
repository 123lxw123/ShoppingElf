package com.lxw.shoppingelf.spider

import com.lxw.shoppingelf.base.BaseProcessor
import com.lxw.shoppingelf.mapper.DiscountHotRankDataMapper
import com.lxw.shoppingelf.util.getContentFromHTML
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import us.codecraft.webmagic.Page

@Component
class DiscountHotRankDetailProcessor : BaseProcessor() {

    @Autowired
    private lateinit var discountHotRankDataMapper: DiscountHotRankDataMapper

    override fun process(page: Page) {
        val paths = page.url.toString().split("?date=")
        val url = paths[0]
        val date = paths[1].replace("$$$", " ")
        val discountHotRankDataEntity = discountHotRankDataMapper.selectOne(url, date)
        val html = page.html
        val category = html.css("div.cu-breadcrumbs").css("a").all()[2].getContentFromHTML()
        val infs = html.css("div.inf").css("p").all()
        val introducer = infs[0].getContentFromHTML().replace("推荐人：", "")
        var label = infs[2].replace("</a>", ",</a>")
                .getContentFromHTML()
                .replace("标签：", "")
        label = if (label.isNotBlank()) "" else label.substring(0, label.length - 1)
        val shareCount = try {
            html.css("a#share_${discountHotRankDataEntity.uid}").css("span").get().getContentFromHTML().toInt()
        } catch (e: Exception) {
            0
        }
        val collectCount = try {
            html.css("a#fav_${discountHotRankDataEntity.uid}").css("span").get().getContentFromHTML().toInt()
        } catch (e: Exception) {
            0
        }
        val likeCount = try {
            html.css("a#zan_${discountHotRankDataEntity.uid}").css("span").get().getContentFromHTML().toInt()
        } catch (e: Exception) {
            0
        }
        discountHotRankDataEntity.category = category
        discountHotRankDataEntity.introducer = introducer
        discountHotRankDataEntity.label = label
        discountHotRankDataEntity.shareCount = shareCount
        discountHotRankDataEntity.collectCount = collectCount
        discountHotRankDataEntity.likeCount = likeCount
        discountHotRankDataMapper.update(discountHotRankDataEntity)
    }
}