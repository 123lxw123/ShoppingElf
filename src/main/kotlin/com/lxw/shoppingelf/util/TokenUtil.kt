package com.lxw.shoppingelf.util

import java.io.File
import java.io.FileReader
import javax.script.Invocable
import javax.script.ScriptEngineManager

object TokenUtil {
    fun getHistoryToken(url: String): String {
        val engine = ScriptEngineManager().getEngineByName("nashorn")
        engine.eval(FileReader(File("com/lxw/shoppingelf/util/TokenUtil.js")))
        val invocable = engine as Invocable
        return invocable.invokeFunction("getHistoryToken", url).toString()
    }
}