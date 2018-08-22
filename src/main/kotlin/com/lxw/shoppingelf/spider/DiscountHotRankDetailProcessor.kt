package com.lxw.shoppingelf.spider

import com.lxw.shoppingelf.base.BaseProcessor
import com.lxw.shoppingelf.entity.DiscountHotRankDataEntity
import com.lxw.shoppingelf.entity.DiscountHotRankEntity
import com.lxw.shoppingelf.mapper.DiscountHotRankDataMapper
import com.lxw.shoppingelf.mapper.DiscountHotRankMapper
import com.lxw.shoppingelf.util.getContentFromHTML
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import us.codecraft.webmagic.Page

@Component
class DiscountHotRankDetailProcessor : BaseProcessor(){

    @Autowired
    private lateinit var discountHotRankDataMapper: DiscountHotRankDataMapper
    private lateinit var discountHotId: String

    fun setDiscountHotId(discountHotId: String) {
        this.discountHotId = discountHotId
    }

    override fun process(page: Page) {
        val discountHotRankDataEntity = discountHotRankDataMapper.select(discountHotId)
        val html = page.html
        val category = html.css("div.cu-breadcrumbs").css("a").all()[1].getContentFromHTML()
        val introducer = html.css("div.inf").css()
        discountHotRankDataMapper.insert(discountHotRankDataEntity)
        }

    }

}