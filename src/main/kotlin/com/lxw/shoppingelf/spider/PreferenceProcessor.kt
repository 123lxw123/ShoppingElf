package com.lxw.shoppingelf.spider

import com.lxw.shoppingelf.base.BaseProcessor
import com.lxw.shoppingelf.entity.PreferenceInfoEntity
import com.lxw.shoppingelf.mapper.PreferenceMapper
import com.lxw.shoppingelf.util.GsonUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site

@Component
class PreferenceProcessor : BaseProcessor() {

    @Autowired
    private lateinit var preferenceMapper: PreferenceMapper

    override fun getSite(): Site = Site.me()
            .setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.91 Safari/537.36")
            .setSleepTime(1000)
            .setTimeOut(10000)
            .setRetryTimes(3)
            .setCharset("utf-8")

    override fun process(page: Page) {
        val url = page.url.toString().split("&p_url=")[1]
        val preferenceInfoEntity = GsonUtil.json2Bean(page.rawText, PreferenceInfoEntity::class.java)
        if (preferenceInfoEntity?.zklist != null) {
            preferenceInfoEntity.zklist.forEach {
                it.url = url
                try {
                    preferenceMapper.insert(it)
                } catch (e: Exception) {
                    preferenceMapper.update(it)
                    e.printStackTrace()
                }
            }
        }
    }
}