package com.lxw.shoppingelf.base

object BaseURL {
    const val DISCOUNT_HOT_RANK = "http://zhekou.manmanbuy.com/DefaultSharehot.aspx?d={date}%20{hour}:00:00"// 国内折扣热度排行榜
    const val DISCOUNT_HOT_RANK_THROUGH = "http://zhekou.manmanbuy.com/wgoto_{uid}.aspx" // 国内折扣直达链接
    const val DISCOUNT_HOT_RANK_DETAIL = "http://zhekou.manmanbuy.com/Sharedetailed_{uid}.aspx" // 国内折扣排行详情
    const val DISCOUNT_HOT_RANK_HISTORY = "http://tool.manmanbuy.com/history.aspx?DA=1&action=gethistory&zkid={uid}&w=310" // 国内折扣历史数据
    const val SEARCH_HISTORY_DETAIL = "http://tool.manmanbuy.com/history.aspx?DA=1&action=gethistory&url={url}&w=951" // 国内折扣历史数据
}