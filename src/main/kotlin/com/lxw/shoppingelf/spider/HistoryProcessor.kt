package com.lxw.shoppingelf.spider

import com.lxw.shoppingelf.base.BaseProcessor
import com.lxw.shoppingelf.base.BaseURL
import com.lxw.shoppingelf.entity.HistoryEntity
import com.lxw.shoppingelf.mapper.HistoryMapper
import com.lxw.shoppingelf.util.GsonUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import us.codecraft.webmagic.Page

@Component
class HistoryProcessor : BaseProcessor() {

    @Autowired
    private lateinit var historyMapper: HistoryMapper

    override fun process(page: Page) {
        var url = page.url.toString()
        if (url.contains("&w=310")) {
            val uid = page.url.toString().split("&zkid=")[1].replace("&w=310", "")
            url = BaseURL.DISCOUNT_HOT_RANK_DETAIL.replace("{uid}", uid)
        }
        val historyEntity = GsonUtil.json2Bean(page.rawText, HistoryEntity::class.java)
        historyEntity?.url = url
        if (historyEntity != null) historyMapper.insert(historyEntity)
    }

}