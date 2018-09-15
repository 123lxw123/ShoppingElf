package com.lxw.shoppingelf.util

import org.springframework.util.ResourceUtils
import java.io.FileReader
import javax.script.Invocable
import javax.script.ScriptEngineManager

object TokenUtil {
    fun getHistoryToken(url: String): String {
        val engine = ScriptEngineManager().getEngineByName("nashorn")
        engine.eval(FileReader( ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "TokenUtil.js")))
        val invocable = engine as Invocable
        return invocable.invokeFunction("getHistoryToken", url).toString()
    }
}