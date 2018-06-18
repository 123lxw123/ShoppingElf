package com.lxw.shoppingelf.base

import us.codecraft.webmagic.Site
import us.codecraft.webmagic.processor.PageProcessor

abstract class BaseProcessor : PageProcessor {

    override fun getSite(): Site = Site.me()
            .setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.91 Safari/537.36")
            .setSleepTime(1000)
            .setTimeOut(10000)
            .setRetryTimes(3)
            .setCharset("gb2312")
}