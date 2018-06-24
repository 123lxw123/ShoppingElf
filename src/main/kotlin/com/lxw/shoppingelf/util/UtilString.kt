package com.lxw.shoppingelf.util

import java.util.regex.Matcher
import java.util.regex.Pattern


fun String.getContentFromHTML(): String {
    var contentString = this
    val p_script: Pattern
    val m_script: Matcher
    val p_style: Pattern
    val m_style: Matcher
    val p_html: Pattern
    val m_html: Matcher
    val p_special: Pattern
    val m_special: Matcher
    try {
        //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
        val regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"
        //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
        val regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"
        // 定义HTML标签的正则表达式
        val regEx_html = "<[^>]+>"
        // 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        val regEx_special = "\\&[a-zA-Z]{1,10};"

        p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE)
        m_script = p_script.matcher(contentString)
        contentString = m_script.replaceAll("") // 过滤script标签
        p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE)
        m_style = p_style.matcher(contentString)
        contentString = m_style.replaceAll("") // 过滤style标签
        p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE)
        m_html = p_html.matcher(contentString)
        contentString = m_html.replaceAll("") // 过滤html标签
        p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE)
        m_special = p_special.matcher(contentString)
        contentString = m_special.replaceAll("") // 过滤特殊标签
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return contentString// 返回文本字符串
}