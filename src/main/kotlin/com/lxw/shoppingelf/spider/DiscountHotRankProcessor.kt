package com.lxw.shoppingelf.spider

import com.lxw.shoppingelf.base.BaseProcessor
import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import com.lxw.shoppingelf.entity.DiscountHotRankEntity
import com.lxw.shoppingelf.repository.DiscountHotRankRepository
import com.lxw.shoppingelf.util.getContentFromHTML
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import us.codecraft.webmagic.Page

@Component
class DiscountHotRankProcessor : BaseProcessor(){

    private lateinit var date: String
    @Autowired
    private lateinit var discountHotRankRepository: DiscountHotRankRepository

    fun setDate(date: String) {
        this.date = date
    }

    override fun process(page: Page) {
        val html = page.html
        val title = html.css("div.t").get()
                .replace("<span>", "(")
                .replace("</span>", ")")
        val item = html.css("li.item")
        val ids = item.css("span.gobuy").regex("tobuy\\('(.*?)'\\)").all()
        val ranks = item.css("i.ic_top").all().map { it.getContentFromHTML() }
        val titles = item.css("h2").css("a").all().filter { !it.contains("highlight") }.map { it.getContentFromHTML() }
        val prices = item.css("h2").css("a.highlight").all().map { it.getContentFromHTML() }
        val descs = item.css("div.descripe").all().map { it.getContentFromHTML() }
        val sources = item.css("span.mall").all().map { it.getContentFromHTML() }
        val images = item.css("img", "src").all()
        val dates = item.css("span.time").all().map { it.getContentFromHTML() }
        val discountHotRankDataEntities = mutableListOf<DiscountHotRankDataEntity>()
        ids.forEachIndexed { index, _ ->
            val discountHotRankDataEntity = DiscountHotRankDataEntity(
                    ids[index],
                    ranks[index],
                    titles[index],
                    prices[index],
                    descs[index],
                    sources[index],
                    images[index],
                    dates[index]
            )
            discountHotRankDataEntities.add(discountHotRankDataEntity)
        }
        val discountHotRankEntity = DiscountHotRankEntity(date, title, discountHotRankDataEntities)
        discountHotRankRepository.save(discountHotRankEntity)
    }

}