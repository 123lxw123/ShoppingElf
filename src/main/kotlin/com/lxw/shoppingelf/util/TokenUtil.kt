package com.lxw.shoppingelf.util

import javax.script.Invocable
import javax.script.ScriptEngineManager

object TokenUtil {
    fun getHistoryToken(url: String): String {
        val engine = ScriptEngineManager().getEngineByName("nashorn")
        engine.eval("load('src/main/resources/TokenUtil.js')")
        val invocable = engine as Invocable
        return invocable.invokeFunction("getHistoryToken", url).toString()
    }
}